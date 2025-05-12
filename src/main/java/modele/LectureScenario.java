package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LectureScenario {
    public static Scenario LectureScenario(File fichier) throws IDException, FileNotFoundException {
        Scenario scenario = new Scenario();

        Scanner scannerFile = new Scanner(fichier);
        Scanner scannerLine;

        while(scannerFile.hasNextLine()){
            String line = scannerFile.nextLine();
            scannerLine = new Scanner(line).useDelimiter(" ");
            String vendeur = scannerLine.next();
            scannerLine.next(); // "->"
            String acheteur = scannerLine.next();
            System.out.println(vendeur + "->" + acheteur);
            scannerLine.close();
        }
        scannerFile.close();
        return scenario;
    }
}
