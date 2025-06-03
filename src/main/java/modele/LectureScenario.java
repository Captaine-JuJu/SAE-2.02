package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class LectureScenario {
    public static List[] lectureScenario(String scenario) throws IDException, FileNotFoundException {
        File file = new File("Scenario" + File.separator + scenario);

//        Scenario scenario = new Scenario();

        Scanner scannerFile = new Scanner(file);
        Scanner scannerLine;
        ArrayList<String> listeVendeur = new ArrayList<>();
        ArrayList<String> listeAcheteur = new ArrayList<>();

        while(scannerFile.hasNextLine()){
            String line = scannerFile.nextLine();
            scannerLine = new Scanner(line).useDelimiter(" ");
            String vendeur = scannerLine.next();
            listeVendeur.add(vendeur);
            scannerLine.next(); // "->"
            String acheteur = scannerLine.next();
            listeAcheteur.add(acheteur);
            System.out.println(vendeur + "->" + acheteur);
            scannerLine.close();
        }
        scannerFile.close();
        return new List[]{listeVendeur, listeAcheteur};
    }

//    public static TreeMap<String, String> lectureVilleMembre() throws IDException, FileNotFoundException {
//        File file = new File("Membre" + File.separator + "membres_APPLI.txt");
//
//        Scanner scannerFile = new Scanner(file);
//        Scanner scannerLine;
//        TreeMap<String, String> membreVille = new TreeMap<>();
//
//        while(scannerFile.hasNextLine()){
//            String line = scannerFile.nextLine();
//            scannerLine = new Scanner(line).useDelimiter(" ");
//            String membre = scannerLine.next();
//            String ville = scannerLine.next();
//            membreVille.put(membre, ville);
//            //System.out.println(membre + "->" + ville);
//            scannerLine.close();
//        }
//        scannerFile.close();
//        System.out.println(membreVille);
//        return membreVille;
//    }

    public static Map lectureVilleMembreCible(ArrayList<String> liste) throws IDException, FileNotFoundException {
        File file = new File("Membre" + File.separator + "membres_APPLI.txt");

        Scanner scannerFile = new Scanner(file);
        Scanner scannerLine;
        Map<String, String> membreVille = new HashMap<>();
        Map<String, String> membreVilleCible = new HashMap<>();

        while(scannerFile.hasNextLine()){
            String line = scannerFile.nextLine();
            scannerLine = new Scanner(line).useDelimiter(" ");
            String membre = scannerLine.next();
            String ville = scannerLine.next();
            membreVille.put(membre, ville);
            //System.out.println(membre + "->" + ville);
            scannerLine.close();
        }

        for (int i = 0; i < liste.size(); i++) {
            if (membreVille.containsKey(liste.get(i))) {
                membreVilleCible.put(liste.get(i), membreVille.get(liste.get(i)));
            }
        }

        scannerFile.close();
        System.out.println(membreVilleCible);
        return membreVilleCible;
    }

    public static Map regroupementParVille(ArrayList<String> membreVille) throws IDException, FileNotFoundException {
        Map regroupementVille = lectureVilleMembreCible(membreVille);
        Map<String, ArrayList> regroupementVilleCible = new HashMap<>();

        for (int i = 0; i < membreVille.size(); i++) {
            String ville = regroupementVille.get(membreVille.get(i)).toString();
            if (!regroupementVilleCible.containsKey(ville)) {  
                regroupementVilleCible.put(ville, new ArrayList<>());
            }
            regroupementVilleCible.get(regroupementVille.get(membreVille.get(i))).add(membreVille.get(i));
        }
        return regroupementVilleCible;
    }

    public static ArrayList<Map> regrouperParVille(String scenario) throws IDException, FileNotFoundException {
        List[] acheteurVendeur = lectureScenario(scenario);
        List achteurL = acheteurVendeur[0];
        List vendeurL = acheteurVendeur[1];

        ArrayList<String> achteur = new ArrayList<>();
        ArrayList<String> vendeur = new ArrayList<>();

        for (int i = 0; i < achteurL.size(); i++) {
            achteur.add(achteurL.get(i).toString());
            vendeur.add(vendeurL.get(i).toString());
        }
        Map<String, ArrayList<String>> ListeVilleAcheteur;
        Map<String, ArrayList<String>> ListeVilleVendeur;
        ListeVilleAcheteur = regroupementParVille(achteur);
        ListeVilleVendeur = regroupementParVille(vendeur);

        ArrayList<Map> regroupementVille = new ArrayList<>();
        regroupementVille.add(ListeVilleAcheteur);
        regroupementVille.add(ListeVilleVendeur);

        return regroupementVille;
    }

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



    public static int[][] grapheAvecSuffixesEnTabVoisins(String scenario) throws IDException, FileNotFoundException {
        Map<String, List<String>> graphe = grapheAvecSuffixes(scenario);

        Set<String> sommets = new HashSet<>();
        sommets.addAll(graphe.keySet());
        for (List<String> voisins : graphe.values()) {
            sommets.addAll(voisins);
        }

        List<String> sommetList = new ArrayList<>(sommets);
        Collections.sort(sommetList); // Optionnel : tri pour cohérence
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

        System.out.println("Index des sommets :");
        for (int i = 0; i < sommetList.size(); i++) {
            System.out.println(i + ": " + sommetList.get(i));
        }

        return tabVoisins;
    }


}
