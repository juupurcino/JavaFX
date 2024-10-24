package com.desktopapp;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class EditarProdutoScreenController {

    public static Scene CreateScene() throws Exception {
        URL sceneUrl = VizuProdutoScreenController.class
                .getResource("EditarScreen.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);
        return scene;
    }
    
}
