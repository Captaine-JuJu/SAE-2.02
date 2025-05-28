package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LectureScenario {
    public static List[] lectureScenario(String scenario) throws IDException, FileNotFoundException {
        File file = new File("Scenario" + File.separator + scenario);

//        Scenario scenario = new Scenario();
        ArrayList acheteurs = new ArrayList();
        ArrayList vendeurs = new ArrayList();
        Scanner scannerFile = new Scanner(file);
        Scanner scannerLine;

        while(scannerFile.hasNextLine()){
            String line = scannerFile.nextLine();
            scannerLine = new Scanner(line).useDelimiter(" ");
            String vendeur = scannerLine.next();
            vendeurs.add(vendeur);
            scannerLine.next(); // "->"
            String acheteur = scannerLine.next();
            acheteurs.add(acheteur);
            System.out.println(vendeur + "->" + acheteur);
            scannerLine.close();
        }
        scannerFile.close();

        return new List[] {acheteurs, vendeurs};
    }

    public static TreeMap<String, String> lectureVilleMembre() throws IDException, FileNotFoundException {
        File file = new File("Membre" + File.separator + "membre_APPLI.txt");

        Scanner scannerFile = new Scanner(file);
        Scanner scannerLine;
        TreeMap<String, String> membreVille = new TreeMap<>();

        while(scannerFile.hasNextLine()){
            String line = scannerFile.nextLine();
            scannerLine = new Scanner(line).useDelimiter(" ");
            String membre = scannerLine.next();
            String ville = scannerLine.next();
            membreVille.put(membre, ville);
            //System.out.println(membre + "->" + ville);
            scannerLine.close();
        }
        scannerFile.close();
        System.out.println(membreVille);
        return membreVille;
    }



}
