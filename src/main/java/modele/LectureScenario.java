package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Classe LectureScenario permettant de :
 *     Lire un scénario de transactions entre vendeurs et acheteurs
 *     Associer les membres à leurs villes
 *     Regrouper les membres par ville<
 *     Construire un graphe de livraison avec suffixes + et -
 *     Représenter ce graphe sous forme de tableau d’adjacence
 *
 */
public class LectureScenario {

    /**
     * Lit un fichier scénario et extrait deux listes :
     * une pour les vendeurs et une pour les acheteurs.
     *
     * @param scenario Le nom du fichier scénario (ex : "scenario_0.txt")
     * @return Un tableau contenant [listeVendeur, listeAcheteur]
     * @throws IDException si une erreur personnalisée est levée
     * @throws FileNotFoundException si le fichier est introuvable
     */
    public static List[] lectureScenario(String scenario) throws IDException, FileNotFoundException {
        File file = new File("Scenario" + File.separator + scenario);
        Scanner scannerFile = new Scanner(file);
        Scanner scannerLine;

        ArrayList<String> listeVendeur = new ArrayList<>();
        ArrayList<String> listeAcheteur = new ArrayList<>();

        while (scannerFile.hasNextLine()) {
            String line = scannerFile.nextLine();
            scannerLine = new Scanner(line).useDelimiter(" ");
            String vendeur = scannerLine.next();
            listeVendeur.add(vendeur);
            scannerLine.next(); // "->"
            String acheteur = scannerLine.next();
            listeAcheteur.add(acheteur);
            scannerLine.close();
        }
        scannerFile.close();
        return new List[]{listeVendeur, listeAcheteur};
    }

    /**
     * Extrait les villes associées à une liste de membres à partir du fichier membres_APPLI.txt.
     *
     * @param liste Liste d'identifiants de membres
     * @return Une Map (membre -> ville) uniquement pour les membres spécifiés
     * @throws IDException si une erreur personnalisée est levée
     * @throws FileNotFoundException si le fichier est introuvable
     */
    public static Map<String, String> lectureVilleMembreCible(ArrayList<String> liste) throws IDException, FileNotFoundException {
        File file = new File("Membre" + File.separator + "membres_APPLI.txt");

        Scanner scannerFile = new Scanner(file);
        Scanner scannerLine;
        Map<String, String> membreVille = new HashMap<>();
        Map<String, String> membreVilleCible = new HashMap<>();

        while (scannerFile.hasNextLine()) {
            String line = scannerFile.nextLine();
            scannerLine = new Scanner(line).useDelimiter(" ");
            String membre = scannerLine.next();
            String ville = scannerLine.next();
            membreVille.put(membre, ville);
            scannerLine.close();
        }

        for (String s : liste) {
            if (membreVille.containsKey(s)) {
                membreVilleCible.put(s, membreVille.get(s));
            }
        }

        scannerFile.close();
        return membreVilleCible;
    }

    /**
     * Regroupe une liste de membres par leur ville.
     *
     * @param membreVille Liste d’identifiants de membres
     * @return Map (ville -> liste de membres)
     * @throws IDException si une erreur personnalisée est levée
     * @throws FileNotFoundException si le fichier est introuvable
     */
    public static Map<String, ArrayList<String>> regroupementParVille(ArrayList<String> membreVille) throws IDException, FileNotFoundException {
        Map<String, String> regroupementVille = lectureVilleMembreCible(membreVille);
        Map<String, ArrayList<String>> regroupementVilleCible = new HashMap<>();

        for (String membre : membreVille) {
            String ville = regroupementVille.get(membre);
            regroupementVilleCible.computeIfAbsent(ville, k -> new ArrayList<>()).add(membre);
        }
        return regroupementVilleCible;
    }

    /**
     * Regroupe vendeurs et acheteurs d’un scénario par ville.
     *
     * @param scenario Le nom du fichier scénario
     * @return Une ArrayList contenant deux maps : [ville -> acheteurs], [ville -> vendeurs]
     * @throws IDException si une erreur personnalisée est levée
     * @throws FileNotFoundException si le fichier est introuvable
     */
    public static ArrayList<Map> regrouperParVille(String scenario) throws IDException, FileNotFoundException {
        List[] acheteurVendeur = lectureScenario(scenario);
        List acheteurL = acheteurVendeur[0];
        List vendeurL = acheteurVendeur[1];

        ArrayList<String> acheteur = new ArrayList<>();
        ArrayList<String> vendeur = new ArrayList<>();

        for (int i = 0; i < acheteurL.size(); i++) {
            acheteur.add(acheteurL.get(i).toString());
            vendeur.add(vendeurL.get(i).toString());
        }

        Map<String, ArrayList<String>> ListeVilleAcheteur = regroupementParVille(acheteur);
        Map<String, ArrayList<String>> ListeVilleVendeur = regroupementParVille(vendeur);

        ArrayList<Map> regroupementVille = new ArrayList<>();
        regroupementVille.add(ListeVilleAcheteur);
        regroupementVille.add(ListeVilleVendeur);

        return regroupementVille;
    }

    /**
     * Remplace les noms des membres par leur ville dans les listes vendeurs et acheteurs.
     *
     * @param scenario Le nom du fichier scénario
     * @return Un tableau contenant [vendeursVille, acheteursVille]
     * @throws IDException si une erreur personnalisée est levée
     * @throws FileNotFoundException si le fichier est introuvable
     */
    public static List[] remplacementNomVille(String scenario) throws IDException, FileNotFoundException {
        List[] acheteurVendeur = lectureScenario(scenario);
        List<String> vendeurL = acheteurVendeur[0];
        List<String> acheteurL = acheteurVendeur[1];

        ArrayList<String> tousLesMembres = new ArrayList<>();
        tousLesMembres.addAll(vendeurL);
        tousLesMembres.addAll(acheteurL);

        Map<String, String> ListeVilleAcheteur = lectureVilleMembreCible(tousLesMembres);
        List<String> vendeursVille = new ArrayList<>();
        List<String> acheteursVille = new ArrayList<>();

        for (String vendeur : vendeurL) {
            vendeursVille.add(ListeVilleAcheteur.get(vendeur));
        }

        for (String acheteur : acheteurL) {
            acheteursVille.add(ListeVilleAcheteur.get(acheteur));
        }

        return new List[]{vendeursVille, acheteursVille};
    }

    /**
     * Construit un graphe orienté représentant les livraisons de cartes avec suffixes "+" et "-".
     * Le graphe contient aussi les sommets Vélizy+ et Vélizy- comme début et fin.
     *
     * @param scenario Le nom du fichier scénario
     * @return Un graphe sous forme Map (ville+ -> [ville-])
     * @throws IDException si une erreur personnalisée est levée
     * @throws FileNotFoundException si le fichier est introuvable
     */
    public static Map<String, List<String>> grapheAvecSuffixes(String scenario) throws IDException, FileNotFoundException {
        List[] donnees = lectureScenario(scenario);
        List<String> vendeurs = donnees[0];
        List<String> acheteurs = donnees[1];

        ArrayList<String> tous = new ArrayList<>();
        tous.addAll(vendeurs);
        tous.addAll(acheteurs);

        Map<String, String> membreVersVille = lectureVilleMembreCible(tous);
        Map<String, List<String>> grapheTemp = new HashMap<>();

        for (int i = 0; i < vendeurs.size(); i++) {
            String villeVendeur = membreVersVille.get(vendeurs.get(i));
            String villeAcheteur = membreVersVille.get(acheteurs.get(i));

            String cle = villeVendeur + "+";
            String valeur = villeAcheteur + "-";

            grapheTemp.computeIfAbsent(cle, k -> new ArrayList<>()).add(valeur);
        }

        Set<String> villesAcheteursAvecMoins = new HashSet<>();
        for (String acheteur : acheteurs) {
            String ville = membreVersVille.get(acheteur);
            villesAcheteursAvecMoins.add(ville + "-");
        }

        Map<String, List<String>> graphe = new LinkedHashMap<>();
        graphe.put("Vélizy+", new ArrayList<>(villesAcheteursAvecMoins));
        graphe.putAll(grapheTemp);

        for (String villeAcheteurMoins : villesAcheteursAvecMoins) {
            List<String> successeurs = graphe.computeIfAbsent(villeAcheteurMoins, k -> new ArrayList<>());
            if (!successeurs.contains("Vélizy-")) {
                successeurs.add("Vélizy-");
            }
        }

        graphe.put("Vélizy-", new ArrayList<>());
        return graphe;
    }

    /**
     * Transforme le graphe orienté avec suffixes en un tableau de voisins (graphe sous forme d'adjacence).
     *
     * @param scenario Le nom du fichier scénario
     * @return Tableau d’adjacence : chaque ligne contient les indices des sommets voisins
     * @throws IDException si une erreur personnalisée est levée
     * @throws FileNotFoundException si le fichier est introuvable
     */
    public static int[][] grapheAvecSuffixesEnTabVoisins(String scenario) throws IDException, FileNotFoundException {
        Map<String, List<String>> graphe = grapheAvecSuffixes(scenario);

        Set<String> sommets = new HashSet<>();
        sommets.addAll(graphe.keySet());
        for (List<String> voisins : graphe.values()) {
            sommets.addAll(voisins);
        }

        List<String> sommetList = new ArrayList<>(sommets);
        Collections.sort(sommetList); // Tri pour cohérence
        Map<String, Integer> sommetVersIndex = new HashMap<>();
        for (int i = 0; i < sommetList.size(); i++) {
            sommetVersIndex.put(sommetList.get(i), i);
        }

        int[][] tabVoisins = new int[sommetList.size()][];

        for (int i = 0; i < sommetList.size(); i++) {
            String sommet = sommetList.get(i);
            List<String> voisins = graphe.getOrDefault(sommet, new ArrayList<>());

            int[] ligne = new int[voisins.size()];
            for (int j = 0; j < voisins.size(); j++) {
                ligne[j] = sommetVersIndex.get(voisins.get(j));
            }
            tabVoisins[i] = ligne;
        }

        return tabVoisins;
    }
}
