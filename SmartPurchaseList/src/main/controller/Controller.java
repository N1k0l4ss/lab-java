package main.controller;

import dao.ProductDB;
import dao.ShoppingListDB;
import dbObjects.Product;
import dbObjects.ShoppingList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class Controller {
    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Product, String> productNameColumn;
    @FXML private TableColumn<Product, Double> productPrice;
    @FXML private TableColumn<Product, Boolean> productIsSelected;
    //
    @FXML private TableView<ShoppingList> shoppingListTable;
    @FXML private TableColumn<ShoppingList, String> listName;
    @FXML private TableColumn<ShoppingList, Double> fullPrice;
    @FXML private TableColumn<ShoppingList, Double> selectedPrice;
    //
    private Connection connection;
    List<ShoppingList> shoppingLists;
    ShoppingListDB shoppingListDB;
    //
    List<Product> productList;
    ProductDB productDB;

    public void initialize(){
        shoppingListDB = new ShoppingListDB();
        productDB = new ProductDB(shoppingListDB);
        Properties props = new Properties();
        props.setProperty("user", "default");
        props.setProperty("password", "default");
        connectToDb(props);
        updateTables(1);
        // DEBUG
        for (ShoppingList shoppingList : shoppingLists) {
            try {
                shoppingListDB.calcPrices(connection, shoppingList.getId());
            } catch (SQLException throwables) {
                displayError(throwables.toString());
            }
        }
        updateTables(2);
    }
    // Getting shopping lists and filling it
    public void updateTables(int start){ // First call from initialize or other
        try {
            shoppingLists = shoppingListDB.getShoppingLists(connection);
        } catch (SQLException throwables) {
            displayError(throwables.toString());
        }
        fillListTable();
        if (start != 1) // Cause first call from initialize throws exception
            listsClicked();
    }
    // Getting the connection
    private void connectToDb(Properties props) {
        try {
            if (connection == null)
                connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/smart_shopping_list", props);
        } catch (SQLException e) {
            displayError(e.toString());
        }
    }
    // Filling product table
    private void fillProductTable() {
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
//        productIsSelected.setCellValueFactory(new PropertyValueFactory<>("isSelected"));
        productIsSelected.setCellValueFactory(new PropertyValueFactory<>("selected"));
        productIsSelected.setCellFactory(CheckBoxTableCell.forTableColumn(productIsSelected));
        productIsSelected.setEditable(true);
        productTable.setEditable(true);
        productTable.setItems(FXCollections.observableArrayList(productList));
    }
    // Filling shopping list table
    private void fillListTable() {
        listName.setCellValueFactory(new PropertyValueFactory<>("name"));
        fullPrice.setCellValueFactory(new PropertyValueFactory<>("listPrice"));
        selectedPrice.setCellValueFactory(new PropertyValueFactory<>("selectedPrice"));
        shoppingListTable.setItems(FXCollections.observableArrayList(shoppingLists));
    }
    // error window
    public void displayError(String error){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error!");
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.showAndWait();
    }
    // List selected(clicked) listener, opens the list
    public void listsClicked() {
        try {
            productList = productDB.getProductList(
                    connection,
                    shoppingListTable.getFocusModel().getFocusedItem().getId()
                    );
        } catch (SQLException throwables) {
            displayError(throwables.toString());
        }
        fillProductTable();
    }
    // Opening new fxml windows and adding a product to the list
    public void newProductClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../newProduct.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Добавить товар");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            //
            NewProductController newProductController = fxmlLoader.getController();
            newProductController.initData(
                    shoppingListTable.getFocusModel().getFocusedItem().getId(),
                    connection,
                    productDB,
                    this
            );
            stage.show();
        }
        catch (Exception e){
            displayError(e.toString());
        }
    }
    // Delete product button clicked
    public void delProductClicked() {
        int id = productTable.getFocusModel().getFocusedItem().getId();
        int listId = shoppingListTable.getFocusModel().getFocusedItem().getId();
        try {
            productDB.deleteProduct(connection, id, listId);
        } catch (SQLException throwables) {
            displayError(throwables.toString());
        }
        updateTables(2);
    }
    // Deleting the shopping list
    public void delListClicked() {
        try {
            shoppingListDB.removeList(connection, shoppingListTable.getFocusModel().getFocusedItem().getId());
            updateTables(2);
        } catch (SQLException throwables) {
            displayError(throwables.toString());
        }
    }
    // Creating a new shopping list
    public void newListClicked() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Создать список");
        dialog.setHeaderText("Введите название списка");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            try {
                shoppingListDB.createNewList(connection, result.get());
                updateTables(2);
            } catch (SQLException throwables) {
                displayError(throwables.toString());
            }
        }
    }
    // Refreshing prices
    public void refreshPricesButton() {
        int listId = shoppingListTable.getFocusModel().getFocusedItem().getId();
        ObservableList<Product> collect = productTable.getItems();
        try {
            productDB.updateSelected(connection, listId, collect, shoppingListDB);
        } catch (SQLException throwables) {
            displayError(throwables.toString());
        }
        updateTables(2);
    }
}
