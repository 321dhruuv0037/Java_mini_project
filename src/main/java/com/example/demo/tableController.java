package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.demo.HelloController.getUsername;

public class tableController implements Initializable {

    @FXML
    private TableColumn<table, String> investedCol;

    @FXML
    private TableView<table> portfolioTable;
    @FXML
    private TableColumn<table, String> idCol;

    @FXML
    private TableColumn<table, String> rateCol;

    @FXML
    private TableColumn<table, String> returnsCol;

    @FXML
    private TableColumn<table, String> timeCol;

    @FXML
    private TableColumn<table, String> typeCol;
    @FXML
    private Label totalInvestment;

    @FXML
    private Label totalReturns;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String username = getUsername();
    Float total_invested, total_return;

    final ObservableList<table> listview = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb){
        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        investedCol.setCellValueFactory(new PropertyValueFactory<>("Invested"));
        rateCol.setCellValueFactory(new PropertyValueFactory<>("Rate"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("Time"));
        returnsCol.setCellValueFactory(new PropertyValueFactory<>("Returns"));
        try{
            DBConnect connectnow = new DBConnect();
            Connection connectdb = connectnow.getConnection();

            String sql = "select *  from demo.portfolio where Username = '" +username+ "' ";
            Statement s = connectdb.createStatement();
            ResultSet resultSet = s.executeQuery(sql);

            while (resultSet.next()){
                /*
                System.out.println(
                                resultSet.getString("Type")+" "+
                                resultSet.getString("Invested")+" "+
                                resultSet.getString("Rate")+" "+
                                resultSet.getString("Time")+" "+
                                resultSet.getString("Returns"));

                 */

                listview.add(new table(resultSet.getString("Id"),
                        resultSet.getString("Type"),
                        resultSet.getString("Invested"),
                        resultSet.getString("Rate"),
                        resultSet.getString("Time"),
                        resultSet.getString("Returns")));
            }
            portfolioTable.setItems(listview);

            String sqlInvested = "SELECT sum(invested) as Total_investment from demo.portfolio group by Username having username= '"+username+"'";
            Statement s1 = null;
            try {
                s1 = connectdb.createStatement();
                ResultSet resultSet1 = s1.executeQuery(sqlInvested);
                if(resultSet1.next()) {
                    total_invested = Float.valueOf(resultSet1.getString(1));
                    System.out.println(total_invested);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String sqlReturn = "SELECT sum(returns) as Total_investment from demo.portfolio group by Username having username= '"+username+"'";
            Statement s2 = null;
            try {
                s2 = connectdb.createStatement();
                ResultSet resultSet2 = s2.executeQuery(sqlReturn);
                if(resultSet2.next()) {
                    total_return = Float.valueOf(resultSet2.getString(1));
                    System.out.println(total_return);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            totalInvestment.setText("Total Investment : " +total_invested);
            totalReturns.setText("Total Returns : " +total_return);
        }
        catch (Exception e){
            e.printStackTrace();

        }

    }





    public void loadData(ActionEvent event) throws SQLException, ClassNotFoundException {
        listview.clear();
        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        investedCol.setCellValueFactory(new PropertyValueFactory<>("Invested"));
        rateCol.setCellValueFactory(new PropertyValueFactory<>("Rate"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("Time"));
        returnsCol.setCellValueFactory(new PropertyValueFactory<>("Returns"));
        try{
            DBConnect connectnow = new DBConnect();
            Connection connectdb = connectnow.getConnection();

            String sql = "select *  from demo.portfolio where Username = '" +username+"' ";
            Statement s = connectdb.createStatement();
            ResultSet resultSet = s.executeQuery(sql);

            while (resultSet.next()){
                /*
                System.out.println(
                                resultSet.getString("Type")+" "+
                                resultSet.getString("Invested")+" "+
                                resultSet.getString("Rate")+" "+
                                resultSet.getString("Time")+" "+
                                resultSet.getString("Returns"));

                 */

                listview.add(new table(resultSet.getString("Id"),
                        resultSet.getString("Type"),
                        resultSet.getString("Invested"),
                        resultSet.getString("Rate"),
                        resultSet.getString("Time"),
                        resultSet.getString("Returns")));
            }
            portfolioTable.setItems(listview);

            String sqlInvested = "SELECT sum(invested) as Total_investment from demo.portfolio group by Username having username= '"+username+"'";
            Statement s1 = null;
            try {
                s1 = connectdb.createStatement();
                ResultSet resultSet1 = s1.executeQuery(sqlInvested);
                if(resultSet1.next()) {
                    total_invested = Float.valueOf(resultSet1.getString(1));
                    System.out.println(total_invested);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String sqlReturn = "SELECT sum(returns) as Total_investment from demo.portfolio group by Username having username= '"+username+"'";
            Statement s2 = null;
            try {
                s2 = connectdb.createStatement();
                ResultSet resultSet2 = s2.executeQuery(sqlReturn);
                if(resultSet2.next()) {
                    total_return = Float.valueOf(resultSet2.getString(1));
                    System.out.println(total_return);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            totalInvestment.setText("Total Investment : " +total_invested);
            totalReturns.setText("Total Returns : " +total_return);
        }
        catch (Exception e){
            e.printStackTrace();

        }
    }
    Parent root;
    Stage stage;
    Scene scene;
    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("menu.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToAddData(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("addData.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToDeleteData(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("deleteData.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}