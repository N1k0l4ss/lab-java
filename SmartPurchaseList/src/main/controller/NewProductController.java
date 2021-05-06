package main.controller;

import dao.ProductDB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class NewProductController {
    @FXML private Button cncBtn;
    @FXML private Button okBtn;
    @FXML private TextField nameField;
    @FXML private TextField priceField;
    private int id;
    private Connection connection;
    private ProductDB productDB;
    private Controller controller;

    public void initData(int id, Connection connection, ProductDB productDB, Controller controller) {
        this.productDB = productDB;
        this.id = id;
        this.connection = connection;
        this.controller = controller;
    }

    public void initialize(){
        digitFilterDouble(priceField);
    }

    private void digitFilterDouble(TextField field) {
        Pattern p = Pattern.compile("(\\d+[\\.,]?\\d*)?");
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!p.matcher(newValue).matches()) field.setText(oldValue);
            else field.setText(newValue.replaceAll(",", "."));
        });
    }

    public void closeStage() {
        Stage stage = (Stage) cncBtn.getScene().getWindow();
        stage.close();
    }

    public void okPressed() {
        try {
            productDB.createProduct(nameField.getText(), Double.parseDouble(priceField.getText()), id, connection);
            controller.updateTables(2);
            closeStage();
        } catch (SQLException throwables) {
            controller.displayError(throwables.toString());
        }
    }
}
