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

public class LoginScreenController {
    public static Scene CreateScene() throws Exception {
        URL sceneUrl = LoginScreenController.class
                .getResource("LoginScreen.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);
        return scene;
    }

    @FXML
    protected Button btlogar;
    @FXML
    protected TextField name;
    @FXML
    protected PasswordField password;
    @FXML
    protected Button register;

    @FXML
    protected void submit(ActionEvent e) throws Exception {
        
        Context ctx = new Context();

        var users = ctx.find(User.class,
                "SELECT u FROM User u WHERE u.name = :arg0",
                name.getText());

        if (users.isEmpty()) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Usuário não está cadastrado!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }

        var user = users.get(0);
        if (!password.getText().equals(user.getPassword())) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Senha incorreta!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }
        
        var crrStage = (Stage) btlogar
                .getScene().getWindow();
        crrStage.close();
        var stage = new Stage();
        var scene = VizuProdutoScreenController.CreateScene();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    protected void goToRegister(ActionEvent e) throws Exception {
        var stage = (Stage) register.getScene().getWindow(); 
        var scene = RegisterScreenController.CreateScene();
        stage.setScene(scene);
        stage.show(); 
    }
}