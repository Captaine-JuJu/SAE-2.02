package Vue;

import javafx.scene.layout.VBox;

public class VBoxRoot extends VBox {
    ChoixScenarioHBox choixScenarioHBox;
    public VBoxRoot() {
        super(30);
        choixScenarioHBox = new ChoixScenarioHBox();
        this.getChildren().add(choixScenarioHBox);
    }

    public ChoixScenarioHBox getChoixScenarioHBox() {
        return choixScenarioHBox;
    }
}
