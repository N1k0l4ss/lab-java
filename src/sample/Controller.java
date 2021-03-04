package sample;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import myclasses.Customer;

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
    private Button saveFile;
    @FXML
    private Button openFile;
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
    @FXML
    private Button btnReset;

    private static ObservableList<Customer> customersObservableList = FXCollections.observableArrayList();
    private static String _parametr; // Static might be cleared
    String path = "Customers.txt";

    static public void newCustomer(Customer cstmr){ customersObservableList.add(cstmr); }

//    static public void setParametr(String parametr){ _parametr = parametr; }

    private void removeCustomer(){
        customersObservableList.remove(table.getFocusModel().getFocusedItem());
        table.setItems(customersObservableList);
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
//        dialog.setContentText("Please enter your name:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            _parametr = result.get();
        }


//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../findLabel/findLable.fxml"));
//            Parent root1 = (Parent) fxmlLoader.load();
//            Stage stage = new Stage();
//            stage.setTitle("Поиск");
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.setScene(new Scene(root1));
//            stage.showAndWait();
//        }
//        catch (Exception e) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Ошибка!");
//            alert.setHeaderText(null);
//            alert.setContentText("Ошибка загрузки окна FXML!\n" + e.toString());
//            alert.showAndWait();
//        }
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
        ObservableList<Customer> sortedByName = FXCollections.observableArrayList(MySorts.sortA(customersObservableList, _parametr));
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
        ObservableList<Customer> sorted = FXCollections.observableArrayList(MySorts.sortB(customersObservableList, _parametr));
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
        ObservableList<Customer> sortedObservable = FXCollections.observableArrayList(MySorts.sortC(customersObservableList));
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
        ObservableList<Customer> sortedObservable = FXCollections.observableArrayList(MySorts.sortD(customersObservableList));
        table.setItems(sortedObservable);
    }
    @FXML
    void eClicked(ActionEvent event) {
        Set sorted = MySorts.sortE(customersObservableList);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        if (sorted.toString() == "[]")
            alert.setContentText("Список пуст!");
        else
            alert.setContentText(sorted.toString());
        alert.showAndWait();
    }
    @FXML
    void fClicked(ActionEvent event) {
        Map<Integer, Customer> sortedMap = MySorts.sortF(customersObservableList);
        ObservableList<Customer> sorted = FXCollections.observableArrayList();
        for (Customer value : sortedMap.values()) {
            sorted.add(value);
        }
        table.setItems(sorted);
    }
    @FXML
    void newCustomerClicked(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../newCustomer/newCustomer.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Новый покупатель");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Ошибка загрузки окна FXML!\n" + e.toString());
            alert.showAndWait();
        }
    }
    @FXML
    void saveFileClicked(MouseEvent event) {
        try (PrintWriter printWriter = new PrintWriter(new File(path))){
            printWriter.println(customersObservableList.size());
            for (Customer tmp: customersObservableList) {
                printWriter.print(tmp.fileString());
            }
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Ошибка при сохранении\n" + e.toString());
            alert.showAndWait();
        }
    }
    @FXML
    void loadFileClicked(MouseEvent event) {
        if (customersObservableList.size() > 0)
                customersObservableList.remove(0, customersObservableList.size());
        try(BufferedReader bufferedReader = new BufferedReader(Files.newBufferedReader(Paths.get(path)))){
            int length = Integer.parseInt(bufferedReader.readLine());
            final int n = 8;
            String[] str = new String[n];
            for (int j = 0; j < length; j++) {
                for (int i = 0; i < n; i++) {
                    str[i] = bufferedReader.readLine();
                }
                customersObservableList.add(
                        new Customer(Integer.parseInt(str[0]), str[1], str[2], str[3], Integer.parseInt(str[4]), str[5], Long.parseLong(str[6]), Double.parseDouble(str[7]))
                );
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Ошибка при чтении файла\n" + e.toString());
            alert.showAndWait();
        }
    }
    @FXML
    void initialize() {
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
}
