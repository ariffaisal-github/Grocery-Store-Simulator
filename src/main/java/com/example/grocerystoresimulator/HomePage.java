package com.example.grocerystoresimulator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.ResultSet;

public class HomePage {

    @FXML private TextField empIDtoFire;
    @FXML private TextField eid, ename, ephnno, edateofb, egender, ebloodg, eaddress, esalary, ehiredate;
    @FXML private Label empAlreadyExist, empNotFound;
    @FXML
    protected void onLogoutBtnClick(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("GROCERY STORE");
            stage.setScene(scene);
            stage.setX(400);
            stage.setY(150);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onHireBtnClick(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HireEmployee.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("GROCERY STORE");
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setX(0);
            stage.setY(0);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onBackBtnClick(ActionEvent event) {
        try {
            Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
            st.close();
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("GROCERY STORE");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setX(0);
            stage.setY(0);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onHireCurrentEmpBtnClick(ActionEvent event) {
        // add this employee to database
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from employees where employee_id = %d", Integer.parseInt(eid.getText()));
            ResultSet rs = oc.searchDB(query);
            if (rs.next()) {
                empAlreadyExist.setText("Employee with this Id already exists");
            } else {
                String insertQuery = String.format(
                        "insert into employees values (%d, '%s', %d, '%s', '%s', '%s', '%s', %d, '%s' )",
                        Integer.parseInt(eid.getText()), ename.getText(), Integer.parseInt(ephnno.getText()),
                        edateofb.getText(), egender.getText(), ebloodg.getText(), eaddress.getText(), Integer.parseInt(esalary.getText()),
                        ehiredate.getText());
                // System.out.println(insertQuery);
                oc.updateDB(insertQuery);
                eid.clear(); ename.clear(); ephnno.clear(); edateofb.clear(); egender.clear(); ebloodg.clear(); eaddress.clear(); esalary.clear(); ehiredate.clear();
                empAlreadyExist.setText("Employee has been Hired");
            }
        } catch (Exception e) {
            empAlreadyExist.setText("Exception in adding employees: " + e);
            System.out.println("Exception in adding employees: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onEmployeesBtnClick(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Employees.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Employees");
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setX(0);
            stage.setY(0);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onFireBtnClick(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FireEmployee.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Fire an Employee");
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setX(0);
            stage.setY(0);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onFireCurrentEmpBtnClick(ActionEvent event) {
        int empID = Integer.parseInt(empIDtoFire.getText());
        OracleConnect oc = null;
        empIDtoFire.setText("");
        try {
            oc = new OracleConnect();
            String query = String.format("select * from employees where employee_id = %d", empID);
            ResultSet rs = oc.searchDB(query);
            if (!rs.next()) {
                empNotFound.setText("Employee not found");
            } else {
                String dltQuery = String.format(
                        "delete from employees where employee_id = %d", empID);
                oc.updateDB(dltQuery);
                empNotFound.setText("Employee has been Fired");
            }
        } catch (Exception e) {
            empAlreadyExist.setText("Exception in firing employees: " + e);
            System.out.println("Exception in firing employees: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onSupplierBtnClick(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SupplierPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Order items for the store");
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setX(0);
            stage.setY(0);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

