package View;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ViewSalesStage extends Stage {
    //sets the title of the stage
        ViewSalesStage() {
            Label label = new Label("hello world");

            this.setTitle("Add New Dog");
            this.setScene(new Scene( label,275, 200));
            this.show();
        }
}
