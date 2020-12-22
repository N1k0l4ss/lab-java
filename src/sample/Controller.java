package sample;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import myclasses.Students;

public class Controller {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TableView<Students> table;
    @FXML
    private TableColumn<Students, Integer> id;
    @FXML
    private TableColumn<Students, String> lName;
    @FXML
    private TableColumn<Students, String> fName;
    @FXML
    private TableColumn<Students, String> patronymic;
    @FXML
    private TableColumn<Students, LocalDate> birthday;
    @FXML
    private TableColumn<Students, String> adres;
    @FXML
    private TableColumn<Students, String> faqult;
    @FXML
    private TableColumn<Students, Integer> course;
    @FXML
    private TableColumn<Students, Integer> group;
    @FXML
    private TableColumn<Students, Integer> diplomNumber;
    @FXML
    private Button newMagister;
    @FXML
    private Button newBachelor;
    @FXML
    private Button saveFile;
    @FXML
    private Button openFile;

    private static ObservableList<Students> studentsObservableList = FXCollections.observableArrayList();
    String path = "Students.bin";

    @FXML
    void onTableKeyClick(KeyEvent event) { if (event.getCode() == KeyCode.DELETE) removeStudent(); }

    @FXML
    void onDelClicked(MouseEvent event) { removeStudent(); }

    private void removeStudent(){ studentsObservableList.remove(table.getFocusModel().getFocusedIndex()); }

    public static void addStudent(Students st) { studentsObservableList.add(st); }

    @FXML
    void newBachelorClicked(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../newstudent/BachelorWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Добавить студента бакалавра");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Ошибка загрузки окна FXML");
            alert.showAndWait();
        }
    }

    @FXML
    void newMagisterClicked(MouseEvent event){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../newstudent/MagisterWindow.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Добавить студента магистра");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root1));
                stage.show();
            }
            catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Ошибка загрузки окна FXML");
                alert.showAndWait();
            }
        }

    @FXML
    void saveFileClicked(MouseEvent event) {
        try{
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeInt(studentsObservableList.size());
            for (int i = 0; i < studentsObservableList.size(); i++) {
                oos.writeObject(studentsObservableList.get(i));
            }
            oos.close();
        } catch (IOException ioe) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка записи в файл");
            alert.setHeaderText(null);
            alert.setContentText(ioe.toString());
            alert.showAndWait();
        }
    }

    @FXML
    void loadFileClicked(MouseEvent event) {
        try{
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);

            int filesize = ois.readInt();
            for (int i = 0; i < filesize; i++) {
                studentsObservableList.add((Students) ois.readObject());
            }

            ois.close();
        } catch (IOException ioe) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка чтения из файла");
            alert.setHeaderText(null);
            alert.setContentText(ioe.toString());
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка чтения из файла");
            alert.setHeaderText(null);
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }

    @FXML
    void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<Students, Integer>("ID"));
        lName.setCellValueFactory(new PropertyValueFactory<Students, String>("lastName"));
        fName.setCellValueFactory(new PropertyValueFactory<Students, String>("firstName"));
        patronymic.setCellValueFactory(new PropertyValueFactory<Students, String>("patronymic"));
        birthday.setCellValueFactory(new PropertyValueFactory<Students, LocalDate>("birthday"));
        adres.setCellValueFactory(new PropertyValueFactory<Students, String>("adres"));
        faqult.setCellValueFactory(new PropertyValueFactory<Students, String>("faqult"));
        course.setCellValueFactory(new PropertyValueFactory<Students, Integer>("course"));
        group.setCellValueFactory(new PropertyValueFactory<Students, Integer>("group"));
        diplomNumber.setCellValueFactory(new PropertyValueFactory<Students, Integer>("diplomNumber"));
        table.setItems(studentsObservableList);
    }
}
