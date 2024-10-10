package com.desktopapp;

import java.net.URL;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class GameOverController {

    URL sceneUrl = MainController.class.getResource("GameOver.fxml");
    FXMLLoader loader = new FXMLLoader(sceneUrl);
    Parent root = loader.load();

    Scene scene = new Scene(root);
    Random randomX = new Random();
    Random randomY = new Random();

    MainController controller = loader.getController();

    controller.pontos.setRecord(record.toString());

    controller.timer = new Timer(); // Inicializa o timer
    
    controller.timer.scheduleAtFixedRate(new TimerTask() {
        public void run() {
            
            int x = randomX.nextInt(600); // Gera nova posição X
            int y = randomY.nextInt(400); // Gera nova posição Y
            controller.elemento.setLayoutX(x);
            controller.elemento.setLayoutY(y);
        }
    }, 0, time);

    return scene;
    
}
