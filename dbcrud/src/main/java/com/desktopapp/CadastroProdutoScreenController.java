package com.desktopapp;

import java.net.URL;

import com.desktopapp.model.Produto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroProdutoScreenController {
    public static Scene CreateScene() throws Exception {
        URL sceneUrl = CadastroProdutoScreenController.class
                .getResource("CadastroProdutoScreen.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);
        return scene;
    }

    @FXML
    protected Button cadastrarProduto;
    
    @FXML
    protected Button voltar;
    
    @FXML
    protected TextField idProduto;
    
    @FXML
    protected TextField nomeProduto;
    
    @FXML
    protected TextField tipoProduto;
    
    @FXML
    protected TextField valorProduto;
    
    @FXML
    protected TextField qtdProduto;

    @FXML
    protected void cadastrarProduto(ActionEvent e) throws Exception {
        
        Context ctx = new Context();

        var produtos = ctx.find(Produto.class,
                "SELECT u FROM Produto u WHERE u.id = :arg0",
                idProduto.getText());

        if (!produtos.isEmpty()) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Produto já cadastrado!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }

        
        Produto produto = new Produto();

        long id = Long.parseLong(idProduto.getText());
        int qtd = Integer.parseInt(qtdProduto.getText());
        float valor = Float.parseFloat(valorProduto.getText());

        produto.setId(id);
        produto.setName(nomeProduto.getText());
        produto.setQtd(qtd);
        produto.settipo(tipoProduto.getText());
        produto.setValor(valor);

        ctx.begin();
        ctx.save(produto);
        ctx.commit();
        
        var crrStage = (Stage) cadastrarProduto
                .getScene().getWindow();
        crrStage.close();
        var stage = new Stage();
        var scene = CadastroProdutoScreenController.CreateScene();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    protected void goToProdutos(ActionEvent e) throws Exception {
        var stage = (Stage) voltar.getScene().getWindow(); 
        var scene = VizuProdutoScreenController.CreateScene();
        stage.setScene(scene);
        stage.show(); 
    }
}