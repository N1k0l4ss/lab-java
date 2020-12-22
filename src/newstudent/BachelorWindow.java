package newstudent;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import myclasses.Bachelor;
import sample.Controller;

public class BachelorWindow {
        @FXML
        private ResourceBundle resources;
        @FXML
        private URL location;
        @FXML
        private TextField fName;
        @FXML
        private TextField lName;
        @FXML
        private TextField patronymic;
        @FXML
        private DatePicker birthday;
        @FXML
        private TextField adres;
        @FXML
        private TextField faqult;
        @FXML
        private TextField group;
        @FXML
        private ChoiceBox<Integer> course;
        @FXML
        private Button ok;

        @FXML
        void okClicked(MouseEvent event) {
                int group_int = Integer.parseInt(group.getText());
                Controller.addStudent(
                        new Bachelor(lName.getText(), fName.getText(), patronymic.getText(), birthday.getValue(), adres.getText(), faqult.getText(), course.getValue(), group_int)
                );
            }
        @FXML
        void initialize(){
            for (int i = 0; i < 4; i++)
                course.getItems().add(i+1);
        }
}
