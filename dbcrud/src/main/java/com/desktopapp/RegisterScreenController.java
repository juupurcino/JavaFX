package com.desktopapp;

import java.net.URL;

import com.desktopapp.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class RegisterScreenController {
    public static Scene CreateScene() throws Exception {
        URL sceneUrl = LoginScreenController.class
                .getResource("RegisterScreen.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);
        return scene;
    }

    @FXML
    protected Button registerCreate;
    
    @FXML
    protected TextField nameCreate;
    
    @FXML
    protected PasswordField passwordCreate;

    @FXML
    protected void register(ActionEvent e) throws Exception {
        
        Context ctx = new Context();

        var users = ctx.find(User.class,
                "SELECT u FROM User u WHERE u.name = :arg0",
                nameCreate.getText());

        if (!users.isEmpty()) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Usuário já está cadastrado!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }

        
        User user = new User();
        user.setName(nameCreate.getText());
        user.setPassword(passwordCreate.getText());

        ctx.begin();
        ctx.save(user);
        ctx.commit();
        
        var crrStage = (Stage) registerCreate
                .getScene().getWindow();
        crrStage.close();
        var stage = new Stage();
        var scene = LoginScreenController.CreateScene();
        stage.setScene(scene);
        stage.show();

    }
}