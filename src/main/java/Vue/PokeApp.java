package Vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class PokeApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VBoxRoot root = new VBoxRoot();
        Scene scene = new Scene(root, 600, 400);
        File[] premiercss = new File("css").listFiles();
        for (File f : premiercss) {
            scene.getStylesheets().add(f.toURI().toString());
        }
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
