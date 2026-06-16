package com.example.interactivechessguinea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label wrongLoginLabel;
    public void loginButton(ActionEvent event) throws IOException {
        String login = usernameField.getText() + " " + passwordField.getText();
        Scanner scanner;
        try {
            scanner = new Scanner(new FileInputStream("Logins.txt"));
        } catch (FileNotFoundException ex){
            throw new RuntimeException(ex);
        }
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.equals(login)) {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ChessGUI.fxml")));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 800, 800);
                stage.setScene(scene);
                stage.show();
            } else {
                wrongLoginLabel.setText("Incorrect Login Details");
            }
        }
    }
    public void createAccountButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("createAccountGUI.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 500, 400);
        stage.setScene(scene);
        stage.show();
    }
}