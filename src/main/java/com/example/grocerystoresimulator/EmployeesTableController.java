package com.example.grocerystoresimulator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class EmployeesTableController implements Initializable {
    @FXML private TableColumn<Employees, String> addressColumn;
    @FXML private TableColumn<Employees, String> bloodGroupColumn;
    @FXML private TableColumn<Employees, Integer> contactColumn;
    @FXML private TableColumn<Employees, String> dateofbirthColumn;
    @FXML private TableColumn<Employees, Integer> empIDColumn;
    @FXML private TableView<Employees> employeesTable;
    @FXML private TableColumn<Employees, String> empnameColumn;
    @FXML private TableColumn<Employees, String> genderColumn;
    @FXML private TableColumn<Employees, String> hiredateColumn;
    @FXML private TableColumn<Employees, Integer> salaryColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        empIDColumn.setCellValueFactory(new PropertyValueFactory<>("eid"));
        empnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        hiredateColumn.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        bloodGroupColumn.setCellValueFactory(new PropertyValueFactory<>("bloogGrp"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        dateofbirthColumn.setCellValueFactory(new PropertyValueFactory<>("dOfbirth"));
        employeesTable.setItems(getEmployees());

    }

    public static ObservableList<Employees> getEmployees() {
        ObservableList<Employees> employeesList = FXCollections.observableArrayList();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = "select * from employees order by employee_id";
            ResultSet rs = oc.searchDB(query);
            while (rs.next()) {
                Employees e = new Employees(
                        rs.getInt("employee_id"), rs.getString("name"), rs.getInt("contact_no"),
                        rs.getString("date_of_birth"), rs.getString("gender"), rs.getString("blood_group"),
                        rs.getString("Address"), rs.getInt("salary"), rs.getString("hire_date")
                );
                System.out.println(".\n.\n.\n.\n"+e.eid+"\n"+e.name);
                employeesList.add(e);
            }
        } catch (Exception e) {
            System.out.println("Exception in listEmployees: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return employeesList;
    }

    public void onBackBtnClick(ActionEvent event) {
        try {
            Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
            st.close();
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Hello!");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setX(0);
            stage.setY(0);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
