package com.desktopapp;

import java.net.URL;

import com.desktopapp.model.Produto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class VizuProdutoScreenController {
    public static Scene CreateScene() throws Exception {
        URL sceneUrl = VizuProdutoScreenController.class
                .getResource("VizuProdutoScreen.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);
        return scene;
    }

    @FXML
    protected TableView<Produto> tabela;

    @FXML
    protected TableColumn<Produto, Long> idCol;
    
    @FXML
    private TableColumn<Produto, Void> deleteCol;

    @FXML
    protected TableColumn<Produto, String> nomeCol;

    @FXML
    protected TableColumn<Produto, String> tipoCol;

    @FXML
    protected TableColumn<Produto, Integer> qtdCol;

    @FXML
    protected TableColumn<Produto, Float> valorCol;

    @FXML
    protected Button register;

    @FXML
    protected void goToCadastrarProdutos(ActionEvent e) throws Exception {
        var stage = (Stage) register.getScene().getWindow();
        var scene = CadastroProdutoScreenController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void abrir(ActionEvent e) throws Exception {
        var stage = (Stage) register.getScene().getWindow();
        var scene = CadastroProdutoScreenController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {

        Context ctx = new Context();

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        qtdCol.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        valorCol.setCellValueFactory(new PropertyValueFactory<>("valor"));

        //Deletar
        deleteCol.setCellFactory(column -> new TableCell<Produto, Void>() {
            private final Button deleteButton = new Button("Excluir");
    
            {
                deleteButton.setOnAction(event -> {
                    Produto produto = getTableView().getItems().get(getIndex());
                    ctx.delete(produto); 
                    getTableView().getItems().remove(produto); 
                });
            }
    
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null); 
                } else {
                    setGraphic(deleteButton); 
                }
            }
        });

        nomeCol.setCellFactory(
                TextFieldTableCell.forTableColumn());
        tipoCol.setCellFactory(
                TextFieldTableCell.forTableColumn());

        nomeCol.setOnEditCommit(event -> {
            Produto produto = event.getRowValue();
            produto.setName(event.getNewValue());
            ctx.updtade(produto);

        });

        tipoCol.setOnEditCommit(event -> {
            Produto produto = event.getRowValue();
            produto.settipo(event.getNewValue());
            ctx.updtade(produto);

        });

        tabela.setItems(ctx.listaproduto());


    }
    



}