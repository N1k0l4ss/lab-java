package findLabel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Controller;

public class FindLableController {

    @FXML
    private TextField search;

    @FXML
    private Button searchBtn;

    @FXML
    void clicked(MouseEvent event) {
        Controller.setParametr(search.getText());

    }

}
