package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public static String Username = null;
    public static String getUsername() {
        return Username;
    }

    public static void setUsername(String username) {
        Username = username;
        System.out.println(username);
    }




    public void switchToHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("menu.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToInvestment(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("investment.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLoan(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Loan.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void switchToFD(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("FD.fxml")); //pass scene name here
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
    }
    public void switchToLumpSum(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("LumpSum.fxml")); //pass scene name here
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
    }
    public void switchToGold(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("Gold.fxml")); //pass scene name here
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
    }
    public void switchToInflation(ActionEvent event) throws IOException {
                root = FXMLLoader.load(getClass().getResource("Inflation.fxml")); //pass scene name here
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
    }

    public void switchToSIP(ActionEvent event) throws IOException {
                root = FXMLLoader.load(getClass().getResource("SIP.fxml")); //pass scene name here
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
    }
    public void switchToFinancialAdvisor(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FinancialAdvice.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

     public void switchToFeedback(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("Feedback.fxml")); //pass scene name here
         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
    }


}