package com.desktopapp;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainController {

    public static Scene CreateScene() throws Exception {
        URL sceneUrl = MainController.class.getResource("LoginScreen.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);
        return scene;
    }

    @FXML
    protected Button btLogin;

    @FXML
    protected TextField nome;

    @FXML
    protected PasswordField senha;

    @FXML
    protected CheckBox verSenha;

    @FXML
    protected void juju(MouseEvent e){

    }

}
