//package Vue;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.File;
//
//public class PokeApp extends Application {
//
//    public void start(Stage stage) {
//
//        stage.setTitle("Calendrier");
//        HBoxRoot root = new HBoxRoot();
//        Scene scene = new Scene(root,900, 400);
//        File[] premiercss = new File("css").listFiles();
//        for(File fichier : premiercss){
//            scene.getStylesheets().add(fichier.toURI().toString());
//        }
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        Application.launch(args);
//    }
//
//}
