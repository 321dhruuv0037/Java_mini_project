package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.demo.HelloController.setUsername;

public class ForgotPasswordController {

    @FXML
    private TextField email;

    @FXML
    private Label error;

    @FXML
    private Label errorEmail;

    @FXML
    private Label errorFavAnimal;

    @FXML
    private Label errorMobNo;

    @FXML
    private Label errorPassword;

    @FXML
    private Label errorUsername;

    @FXML
    private TextField favanimal;

    @FXML
    private TextField mobno;

    @FXML
    private TextField new_password;

    @FXML
    private PasswordField new_password1;

    @FXML
    private TextField username;

    public void onSubmitButtonClick(ActionEvent event) throws IOException {
        System.out.println("Button clicked!");
        if (!username.getText().isBlank() && !email.getText().isBlank() && !mobno.getText().isBlank()
                && !favanimal.getText().isBlank() && !new_password.getText().isBlank() && !new_password1.getText().isBlank()){
            forgotPassword(event);
        }
        else {

            if(username.getText().isBlank()){
                errorUsername.setText("⚠ Please enter username!");
                username.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorUsername.setText(null);
                username.setStyle(null);
            }
            if(new_password.getText().isBlank() || new_password1.getText().isBlank()){
                errorPassword.setText("⚠ Please enter password!");
                new_password.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                new_password1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorPassword.setText(null);
                new_password.setStyle(null);
                new_password1.setStyle(null);
            }
            if(email.getText().isBlank()){
                errorEmail.setText("⚠ Please enter email-id!");
                email.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorEmail.setText(null);
                email.setStyle(null);
            }
            if(mobno.getText().isBlank()){
                errorMobNo.setText("⚠ Please enter your mobile no!");
                mobno.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorMobNo.setText(null);
                mobno.setStyle(null);
            }
            if(favanimal.getText().isBlank()){
                errorFavAnimal.setText("⚠ Please enter your fav animal!");
                favanimal.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorFavAnimal.setText(null);
                favanimal.setStyle(null);
            }

        }
    }

    public void forgotPassword(ActionEvent event) {
        System.out.println(new_password1.getText());
        System.out.println(new_password.getText());
        String l = new_password.getText();
        String m = new_password1.getText();
        if (l.equals(m)){
            DBConnect connectnow = new DBConnect();
            Connection connectdb = connectnow.getConnection();
            System.out.println("inside if");
            String verifylogin = "select count(1) from demo.accountdetails where Username = '" + username.getText() + "' and Email  = '" + email.getText() + "' and Mobile_no = '" + mobno.getText() + "' and Favourite_Animal = '" + favanimal.getText() + "'";
            Statement statement = null;
            try {
                statement = connectdb.createStatement();
                ResultSet queryResult = statement.executeQuery(verifylogin);
                while(queryResult.next()){
                    System.out.println("inside while");
                    if (queryResult.getInt(1)==1){
                        System.out.println("inside if");
                        try {
                            System.out.println("Inside try");
                            String insertDetails = "UPDATE `demo`.`accountdetails` SET `Password` = '"+new_password.getText()+"' WHERE (`Username` = '"+username.getText()+"')\n";
                            String insertUserDetails = "UPDATE `demo`.`userdetails` SET `Password` = '"+new_password.getText()+"' WHERE (`Username` = '"+username.getText()+"')\n";
                            statement = connectdb.createStatement();
                            int a = statement.executeUpdate(insertDetails);
                            int b = statement.executeUpdate(insertUserDetails);
                            if (a == 1 && b == 1) {
                                System.out.println("Inserted data!");
                            }
                            else{
                                System.out.println("Failed to insert data");
                            }
                            String u = (username.getText());
                            root = FXMLLoader.load(getClass().getResource("menu.fxml")); //pass scene name here
                            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                            System.out.println(u);
                            setUsername(String.valueOf(u));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    else {
                        error.setText("⚠ Invalid User!");
                        errorUsername.setText(null);
                        errorPassword.setText(null);
                        errorEmail.setText(null);
                        errorMobNo.setText(null);
                        errorFavAnimal.setText(null);
                        username.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                        username.setText("");
                        email.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                        email.setText("");
                        favanimal.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                        favanimal.setText("");
                        mobno.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                        mobno.setText("");
                        new_password.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                        new_password.setText("");
                        new_password1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                        new_password1.setText("");

                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            errorPassword.setText("⚠ Both password fields should have same value");
            new_password.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            new_password1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
        }
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
