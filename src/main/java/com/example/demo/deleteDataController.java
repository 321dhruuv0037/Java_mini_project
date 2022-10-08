package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class deleteDataController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToPortfolio(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("table.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
}
