package com.desktopapp;

import java.net.URL;
import java.util.ResourceBundle;

import java.util.Random;
import javafx.beans.binding.IntegerBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MainController { 

    public static Scene CreateScene(Integer id) throws Exception { 
        
        URL sceneUrl = MainController.class // Obtém o URL do arquivo FXML para a tela de login
            .getResource("LoginScreen.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl); // Cria um carregador para o arquivo FXML
        Parent root = loader.load(); // Carrega o conteúdo do FXML

        Scene scene = new Scene(root); // Cria uma nova cena a partir do conteúdo carregado
        Random randomX = new Random();
        Random randomY = new Random();

        int x = randomX.nextInt((560) + 40);
        int y = randomY.nextInt((360) + 100);

        MainController controller = loader.getController(); // Obtém o controlador associado ao FXML
        controller.setId(id); 
        controller.pontos.setText(id.toString()); 
        controller.elemento.setLayoutX(x);
        controller.elemento.setLayoutY(y);
        
        return scene; // Retorna a cena criada
    }

    private Integer id; 
    
    public void setId(Integer id) { 
        this.id = id;
    }

    @FXML 
    protected void mudarPosicao(MouseEvent e) throws Exception{

        Stage crrStage = (Stage)elemento // Obtém a janela atual a partir do botão
            .getScene()
            .getWindow();
        
        Scene newScene = MainController.CreateScene(this.id + 1); // Cria uma nova cena com ID incrementado
        crrStage.setScene(newScene); // Define a nova cena na janela atual

    } 

    @FXML 
    protected void decrementar(MouseEvent e) throws Exception{

        Stage crrStage = (Stage)area // Obtém a janela atual a partir do botão
            .getScene()
            .getWindow();
        
        Scene newScene = MainController.CreateScene(this.id - 1); // Cria uma nova cena com ID incrementado
        crrStage.setScene(newScene); // Define a nova cena na janela atual

    } 
    
    @FXML 
    protected Circle elemento; 

    @FXML 
    protected Label pontos; 
    
    @FXML 
    protected Pane area ; 
    

}  
