package com.desktopapp; 

import com.desktopapp.model.Produto;
import com.desktopapp.model.User;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application { 

    public static void main(String[] args) { 

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception { 

        User user = new User();
        Produto produto = new Produto();

        user.setName("ju");
        user.setPassword("123");

        produto.setId(1234l);
        produto.setName("pão de forma");
        produto.settipo("padaria");
        produto.setValor(5.99f);
        produto.setQtd(32);

        Context ctx = new Context();

        ctx.begin();
        ctx.save(user);
        ctx.save(produto);
        ctx.commit();
        
        Scene scene = LoginScreenController.CreateScene();
        primaryStage.setScene(scene); 
        primaryStage.show(); 
    }
}
