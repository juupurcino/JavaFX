package com.desktopapp;

import java.net.URL;

import com.desktopapp.model.Produto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarProdutoScreenController {

    public static Scene CreateScene() throws Exception {
        URL sceneUrl = EditarProdutoScreenController.class.getResource("EditarScreen.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);
        return scene;
    }

    @FXML
    protected TextField idProduto;

    @FXML
    protected TextField nomeEditar;

    @FXML
    protected TextField qtdEditar;
    
    @FXML
    protected TextField valorEditar;

    @FXML
    protected TextField tipoEditar;
    
    @FXML
    protected Button voltar;

    private Produto produtoSelecionado; // Armazena o produto encontrado

    @FXML
    protected void pesquisar(ActionEvent e) {
        String id = idProduto.getText();

        if (id.isEmpty()) {
            showAlert("Erro", "Por favor, digite um ID válido.");
            return;
        }

        try {
            long idLong = Long.parseLong(id);
            Context ctx = new Context();
            produtoSelecionado = ctx.find(Produto.class, idLong);

            if (produtoSelecionado != null) {
                nomeEditar.setText(produtoSelecionado.getName());
                tipoEditar.setText(produtoSelecionado.getTipo());
                qtdEditar.setText(String.valueOf(produtoSelecionado.getQtd()));
                valorEditar.setText(String.valueOf(produtoSelecionado.getValor()));
            } else {
                showAlert("Produto não encontrado", "Nenhum produto encontrado com o ID fornecido.");
            }
        } catch (NumberFormatException ex) {
            showAlert("Erro", "ID inválido. Por favor, insira um número.");
        } catch (Exception ex) {
            showAlert("Erro", "Erro ao buscar produto: " + ex.getMessage());
        }
    }

    @FXML
    protected void editar(ActionEvent e) {
        if (produtoSelecionado == null) {
            showAlert("Erro", "Pesquise um produto antes de editar.");
            return;
        }

        try {

            String nome = nomeEditar.getText();
            String tipo = tipoEditar.getText();
            int quantidade = Integer.parseInt(qtdEditar.getText());
            float valor = Float.parseFloat(valorEditar.getText());

 
            produtoSelecionado.setName(nome);
            produtoSelecionado.settipo(tipo);
            produtoSelecionado.setQtd(quantidade);
            produtoSelecionado.setValor(valor);

            Context ctx = new Context();
            ctx.updtade(produtoSelecionado);

            showAlert("Sucesso", "Produto atualizado com sucesso.");
 
            limparCampos();
        } catch (NumberFormatException ex) {
            showAlert("Erro", "Por favor, insira valores válidos para quantidade e valor.");
        } catch (Exception ex) {
            showAlert("Erro", "Erro ao atualizar produto: " + ex.getMessage());
        }
    }

    private void limparCampos() {
        idProduto.clear();
        nomeEditar.clear();
        tipoEditar.clear();
        qtdEditar.clear();
        valorEditar.clear();
        produtoSelecionado = null; 
    }

    @FXML
    protected void voltar(ActionEvent e) throws Exception {
        var stage = (Stage) voltar.getScene().getWindow(); 
        var scene = VizuProdutoScreenController.CreateScene();
        stage.setScene(scene);
        stage.show(); 
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    } 
}
