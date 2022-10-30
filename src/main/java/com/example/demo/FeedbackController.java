package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import static com.example.demo.HelloController.getUsername;

public class FeedbackController extends NullPointerException{


    @FXML
    private Label errorFeedback;

    @FXML
    private Label errorRating;

    @FXML
    private Label errorSubject;

    @FXML
    private TextField feedback;

    @FXML
    private TextField rating;

    @FXML
    private TextField subject;

    public String username = getUsername();
    public void onSubmitButtonClick(ActionEvent event) {
        System.out.println("Button clicked");
        if (!rating.getText().isBlank() && !subject.getText().isBlank() && !feedback.getText().isBlank()){
            System.out.println("validating");
            eventFeedback(event);
        }
        else {
            if (rating.getText().isBlank()){
                errorRating.setText("⚠ Invalid input!");
                rating.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorRating.setText(null);
                rating.setStyle(null);
            }
            if (subject.getText().isBlank()){
                errorSubject.setText("⚠ Please enter subject!");
                subject.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorSubject.setText(null);
                subject.setStyle(null);
            }
            if (feedback.equals(null)){
                errorFeedback.setText("⚠ Please enter feedback!");
                feedback.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorFeedback.setText(null);
                feedback.setStyle(null);
            }
        }
    }

    private void eventFeedback(ActionEvent event) {
        System.out.println("Inside function signup");
        DBConnect connectnow = new DBConnect();
        Connection connectdb = connectnow.getConnection();
        Statement statement = null;
        try {
            int rat = Integer.parseInt(rating.getText());

            if (rat>-1 && rat<6) {
                System.out.println(username);
                String insertDetails = "INSERT INTO demo.feedback (`Username`, `Rating`, `Subject`, `Feedback`) VALUES ('" + username + "', '" + rating.getText() + "', '" + subject.getText() + "', '" + feedback.getText() + "'\n)";
                try {
                    System.out.println("inside try");
                    statement = connectdb.createStatement();
                    int a = statement.executeUpdate(insertDetails);
                    if (a == 1) {
                        System.out.println("Inserted data!");
                    } else {
                        System.out.println("Failed to insert data");
                    }
                    Parent root = FXMLLoader.load(getClass().getResource("menu.fxml")); //pass scene name here
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getCause();
                }
            }
            else {
                errorRating.setText("⚠ Invalid input!");
                rating.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
        }catch (NumberFormatException e){
            errorRating.setText("⚠ Invalid input!");
            rating.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
        }
    }


    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("menu.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
