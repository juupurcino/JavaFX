package com.desktopapp; 

import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.stage.Stage;

public class App extends Application { // Define a classe App que estende a classe Application

    public static void main(String[] args) { 
        launch(args); // Chama o método launch para iniciar o JavaFX
    }

    @Override
    public void start(Stage primaryStage) throws Exception { // Método chamado ao iniciar a aplicação
        
        Scene scene = MainController.CreateScene(0); // Cria uma cena usando um método da classe MainController
        primaryStage.setScene(scene); // Define a cena criada para a janela principal
        primaryStage.show(); 
    }
}
