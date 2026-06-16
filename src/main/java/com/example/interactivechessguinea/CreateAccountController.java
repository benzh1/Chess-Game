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

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class CreateAccountController {
    @FXML
    private TextField newUsernameField;
    @FXML
    private TextField newPasswordField;
    @FXML
    private TextField confirmPasswordField;
    @FXML
    private Label wrongCreateAccountLabel;
    public void createAccountButton(ActionEvent event) {
        boolean flag = false;
        if (newPasswordField.getText().equals(confirmPasswordField.getText()) && (!newUsernameField.getText().isBlank() || newPasswordField.getText().isBlank())) {
            if (!(newPasswordField.getText().contains(" ") || newUsernameField.getText().contains(" "))) {
                if (isPasswordSecure()) {
                    Scanner scanner;
                    try {
                        scanner = new Scanner(new FileInputStream("Logins.txt"));
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    String line;
                    while (scanner.hasNextLine()) {
                        line = scanner.nextLine();
                        int loginSplit = 0;
                        for (int i = 0; i < line.length(); i++) {
                            if (line.charAt(i) == ' ') {
                                loginSplit = i;
                                break;
                            }
                        }
                        StringBuilder fileUsername = new StringBuilder();
                        for (int i = 0; i < loginSplit; i++) {
                            fileUsername.append(line.charAt(i));
                        }
                        if (newUsernameField.getText().contentEquals(fileUsername)) {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        try {
                            File file = new File("Logins.txt");
                            FileWriter writer = new FileWriter(file, true);
                            writer.append(newUsernameField.getText()).append(" ").append(newPasswordField.getText()).append("\n");
                            writer.close();
                            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginGUI.fxml")));
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = new Scene(root, 500, 400);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ignore) {
                        }
                    } else {
                        wrongCreateAccountLabel.setText("Username not unique");
                    }
                }
            } else {
                wrongCreateAccountLabel.setText("Fields contain invalid characters");
            }
        } else {
            wrongCreateAccountLabel.setText("Fields incorrectly filled in");
        }
    }
    private boolean isPasswordSecure() {
        String password = newPasswordField.getText();
        boolean containsNumber = false;
        boolean containsSymbol = false;
        boolean containsUppercase = true;
        boolean isLength = password.length() >= 6;
        if (!isLength) {
            wrongCreateAccountLabel.setText("Password must be longer than 5 characters");
        }
        if (password.toLowerCase().equals(password)) {
            wrongCreateAccountLabel.setText("Password must contain an uppercase character");
            containsUppercase = false;
        }
        for (int i = 0; i < password.length(); i++) {
            int asciiCharVal = password.charAt(i);
            if (asciiCharVal >= 48 && asciiCharVal <= 57) {
                containsNumber = true;
            } else if ((asciiCharVal >= 33 && asciiCharVal <= 47) || (asciiCharVal >= 58 && asciiCharVal <= 64) || (asciiCharVal >= 91 && asciiCharVal <= 96) || (asciiCharVal >= 123 && asciiCharVal <= 126)) {
                containsSymbol = true;
            }
        }
        if (!containsNumber) {
            wrongCreateAccountLabel.setText("Password must contain a number");
        }
        if (!containsSymbol) {
            wrongCreateAccountLabel.setText("Password must contain a symbol");
        }
        return containsNumber && containsSymbol && containsUppercase && isLength;
    }
}