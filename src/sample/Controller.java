package sample;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import myclasses.Customer;
import myclasses.CustomerDB;

public class Controller {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TableView<Customer> table;
    @FXML
    private TableColumn<Customer, Integer> id;
    @FXML
    private TableColumn<Customer, String> lName;
    @FXML
    private TableColumn<Customer, String> fName;
    @FXML
    private TableColumn<Customer, String> patronymic;
    @FXML
    private TableColumn<Customer, String> adres;
    @FXML
    private TableColumn<Customer, Integer> numberCard;
    @FXML
    private TableColumn<Customer, Double> balance;
    @FXML
    private TableColumn<Customer, Integer> birthYear;
    @FXML
    private Button newCustomer;
    @FXML
    private Button updateBtn;
    @FXML
    private MenuItem menuA;
    @FXML
    private MenuItem menuB;
    @FXML
    private MenuItem menuC;
    @FXML
    private MenuItem menuD;
    @FXML
    private MenuItem menuE;
    @FXML
    private MenuItem menuF;
    private CustomerDB customerDB;

    private static ObservableList<Customer> customersObservableList = FXCollections.observableArrayList();
    private String _parametr;
    String path = "Customers.txt"; //

    static public void newCustomer(Customer cstmr){ customersObservableList.add(cstmr); }

    private void removeCustomer(){
        Customer customer = table.getFocusModel().getFocusedItem();
        try {
            customerDB.removeCustomer(customer.getId());
            customersObservableList = FXCollections.observableArrayList(customerDB.getCustomersFromDB());
            table.setItems(customersObservableList);
        } catch (SQLException throwables) {
            displayError(throwables.toString());
        }
    }

    private void searchWindow(int a){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Поиск");
        switch (a){
            case 1:
                dialog.setHeaderText("Введите имя");
                break;
            case 2:
                dialog.setHeaderText("Введите диапозон кредитных карт, разделяя через дефиз");
                break;
        }

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            _parametr = result.get();
        }
    }

    @FXML
    void onTableKeyClick(KeyEvent event) { if (event.getCode() == KeyCode.DELETE) removeCustomer(); }
    @FXML
    void onDelClicked(MouseEvent event) { removeCustomer(); }
    @FXML
    void resetClicked(MouseEvent event) { table.setItems(customersObservableList); }
    @FXML
    void aClicked(ActionEvent event) {
        searchWindow(1);
        if (_parametr == null)
            return;
        ObservableList<Customer> sortedByName = null;
        try {
            sortedByName = FXCollections.observableArrayList(customerDB.sortA(_parametr));
        } catch (SQLException throwables) {
            displayError(throwables.toString());
        }
        if(sortedByName.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Не найдено ни одного совпадения по запросу \"" + _parametr + "\"");
            alert.showAndWait();
        }
        else
            table.setItems(sortedByName);
    }
    @FXML
    void bClicked(ActionEvent event) {
        searchWindow(2);
        if (_parametr == null)
            return;
        ObservableList<Customer> sorted = null;
        try {
            sorted = FXCollections.observableArrayList(customerDB.sortB(_parametr));
        } catch (SQLException throwables) {
            displayError(throwables.toString());
        }
        if(sorted.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Не найдено ни одного совпадения в указанном диапазоне");
            alert.showAndWait();
        }
        else
            table.setItems(sorted);

    }
    @FXML
    void cClicked(ActionEvent event) {
        ObservableList<Customer> sortedObservable = null;
        try {
            sortedObservable = FXCollections.observableArrayList(customerDB.sortC());
        } catch (SQLException throwables) {
            displayError(throwables.toString());
        }
        if(sortedObservable.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Должники не найдены");
            alert.showAndWait();
        }
        else
            table.setItems(sortedObservable);
    }
    @FXML
    void dClicked(ActionEvent event) {
        ObservableList<Customer> sortedObservable = null;
        try {
            sortedObservable = FXCollections.observableArrayList(customerDB.sortD());
        } catch (SQLException throwables) {
            displayError(throwables.toString());
        }
        table.setItems(sortedObservable);
    }
    @FXML
    void eClicked(ActionEvent event) {
        String sorted = null;
        try {
            sorted = customerDB.sortE();
        } catch (SQLException throwables) {
            displayError(throwables.toString());
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        if (sorted.toString() == "")
            alert.setContentText("Список пуст!");
        else
            alert.setContentText(sorted);
        alert.showAndWait();
    }
    @FXML
    void fClicked(ActionEvent event) {
        ObservableList<Customer> sorted = null;
        try {
            sorted = FXCollections.observableArrayList(customerDB.sortF());
        } catch (SQLException throwables) {
            displayError(throwables.toString());
        }
        table.setItems(sorted);
    }
    @FXML
    void updateClicked(MouseEvent event) {
        try {
            customersObservableList = FXCollections.observableArrayList(customerDB.getCustomersFromDB());
            table.setItems(customersObservableList);
        } catch (SQLException throwables) {
            displayError(throwables.toString());
        }
    }
    @FXML
    void initialize() {
        try {
            customerDB = new CustomerDB();
        } catch (SQLException throwables) {
            displayError(throwables.toString());
        }
        id.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        lName.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        fName.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
        patronymic.setCellValueFactory(new PropertyValueFactory<Customer, String>("patronymic"));
        adres.setCellValueFactory(new PropertyValueFactory<Customer, String>("adres"));
        numberCard.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("numberCard"));
        balance.setCellValueFactory(new PropertyValueFactory<Customer, Double>("balance"));
        birthYear.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("birthYear"));
        table.setItems(customersObservableList);
    }

    private void displayError(String error){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка!");
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.showAndWait();
    }
}
