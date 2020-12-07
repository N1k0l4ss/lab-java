package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import logic.Lab2;

public class Controller {
    @FXML
    private TextField x1Textbox;
    @FXML
    private TextField x2Textbox;
    @FXML
    private TextField deltaXTextbox;
    @FXML
    private Label minYLabel;
    @FXML
    private Label maxYLabel;
    @FXML
    private Label averageYLabel;
    @FXML
    private Label iterationsLabel;
    @FXML
    private Button btCalc;
    @FXML
    private Label sumLabel1;

    private double x1;
    private double x2;
    private double deltaX;
    private double [] y;

    @FXML
    void initialize()
    {
        btCalc.setOnAction(event -> {
            x1 = Double.parseDouble(x1Textbox.getText());
            x2 = Double.parseDouble(x2Textbox.getText());
            deltaX = Double.parseDouble(deltaXTextbox.getText());
            if (x2 > x1)
            {
                Lab2 l2 = new Lab2();
                l2.fillX(x1, x2, deltaX);
                l2.fillY(x1, x2, deltaX);
                y = l2.getY();
                maxYLabel.setText("Max Y = " + String.valueOf(y[l2.maxY(y)]));
                minYLabel.setText("Min Y = " + String.valueOf(y[l2.minY(y)]));
                averageYLabel.setText("Average Y = " + String.valueOf(l2.averageY(y)));
                iterationsLabel.setText("Times of iterations = " + String.valueOf(l2.findSize(x1, x2, deltaX)));
                sumLabel1.setText("Sum of Y = " + String.valueOf(l2.sumOf(y)));
            }
            else
            {
                maxYLabel.setText("x2 can't be less than x1!");
                minYLabel.setText("");
                averageYLabel.setText("");
                iterationsLabel.setText("");
                sumLabel1.setText("");
            }

        });
    }
}

