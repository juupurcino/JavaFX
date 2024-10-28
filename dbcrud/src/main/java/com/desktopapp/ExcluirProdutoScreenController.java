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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ExcluirProdutoScreenController {

    public static Scene CreateScene() throws Exception {
        URL sceneUrl = ExcluirProdutoScreenController.class.getResource("ExcluirScreen.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);
        return scene;
    }

    @FXML
    protected TextField idExcluir;

    @FXML
    protected Label statusLabel;

    @FXML
    protected void excluir(ActionEvent e) throws Exception {
        String id = idExcluir.getText();

        if (id.isEmpty()) {
            statusLabel.setText("Por favor, digite um ID válido.");
            return;
        }

        long idachado = Long.parseLong(id);
        Context ctx = new Context();
        Produto produto = ctx.find(Produto.class, idachado);

        if (produto != null) {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Exclusão");
            alert.setHeaderText("Você tem certeza que deseja excluir este produto?");
            alert.setContentText("ID: " + produto.getId()
                    + "\nNome: " + produto.getName()
                    + "\nTipo: " + produto.getTipo()
                    + "\nQuantidade: " + produto.getQtd()
                    + "\nValor: " + produto.getValor());

            alert.getButtonTypes().setAll(javafx.scene.control.ButtonType.YES, javafx.scene.control.ButtonType.NO);

            alert.showAndWait().ifPresent(response -> {
                if (response == javafx.scene.control.ButtonType.YES) {
                    ctx.delete(produto);
                    statusLabel.setText("Produto excluído com sucesso.");
                } else {
                    statusLabel.setText("Exclusão cancelada.");
                }
            });

        } else {
            statusLabel.setText("Produto não encontrado.");
        }
    }

}
