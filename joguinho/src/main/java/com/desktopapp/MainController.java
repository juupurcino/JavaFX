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

public class MainController { 

    private Timer timer; // Atributo para o timer

    private Integer acerto = 0; 
    private Integer erro = 0; 
    private Integer time = 3000; // Valor padrão para o tempo

    public static Scene CreateScene(Integer acerto, Integer time, Integer erro) throws Exception { 
        URL sceneUrl = MainController.class.getResource("LoginScreen.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Random randomX = new Random();
        Random randomY = new Random();

        MainController controller = loader.getController();
        controller.setAcerto(acerto); 
        controller.setTime(time); 
        controller.setErros(erro); 
        controller.pontos.setText(acerto.toString());
        controller.erros.setText(erro.toString());

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

    public void setAcerto(Integer acerto) { 
        this.acerto = acerto;
    }

    public void setErros(Integer erro) { 
        this.erro = erro;
    }

    public void setTime(Integer time) { 
        this.time = time;
    }

    @FXML 
    protected void mudarPosicao(MouseEvent e) throws Exception {
        cancelarTimer(); // Cancela o timer antes de mudar de cena
        verificarTime();

        Stage crrStage = (Stage) elemento.getScene().getWindow();
        Scene newScene = MainController.CreateScene(this.acerto + 1, time - 100, this.erro);
        crrStage.setScene(newScene);
    } 

    @FXML 
    protected void decrementar(MouseEvent e) throws Exception {
        cancelarTimer(); // Cancela o timer antes de mudar de cena
        verificarTime();

        Stage crrStage = (Stage) area.getScene().getWindow();
        Scene newScene = MainController.CreateScene(this.acerto, this.time, this.erro+1);
        crrStage.setScene(newScene);
    } 

    private void cancelarTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge(); // Limpa as tarefas canceladas
            timer = null; 
        }
    }

    private void verificarTime() {
        if (time <= 100) { 
            time += 100; 
        }
    }

    @FXML 
    protected Circle elemento; 

    @FXML 
    protected Label erros; 
    
    @FXML 
    protected Label pontos; 

    @FXML 
    protected Pane area; 
}
