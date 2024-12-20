package com.desktopapp;

import java.net.URL;
import java.util.List;

import com.desktopapp.model.Produto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

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
    protected Button buttonExcluir;

    @FXML
    protected Button buttonEditar;

    @FXML
    protected TextField pesquisa;

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
    protected void buttonExcluir(ActionEvent e) throws Exception {
        var stage = (Stage) buttonExcluir.getScene().getWindow();
        var scene = ExcluirProdutoScreenController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void buttonEditar(ActionEvent e) throws Exception {
        var stage = (Stage) buttonEditar.getScene().getWindow();
        var scene = EditarProdutoScreenController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void pesquisa(ActionEvent e) throws Exception {

        Context ctx = new Context();

        List<Produto> produtosEncontrados = ctx.find(
                Produto.class,
                "SELECT u FROM Produto u WHERE u.name LIKE :arg0 OR u.tipo LIKE :arg1",
                "%" + pesquisa.getText() + "%",
                "%" + pesquisa.getText() + "%"
        );

        ObservableList<Produto> produtosList = FXCollections.observableArrayList(produtosEncontrados);
        tabela.setItems(produtosList);

    }

    @FXML
    public void initialize() {
        Context ctx = new Context();

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        qtdCol.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        valorCol.setCellValueFactory(new PropertyValueFactory<>("valor"));
        qtdCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        valorCol.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

        // Deletar
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
                setGraphic(empty ? null : deleteButton);
            }
        });

        nomeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        tipoCol.setCellFactory(TextFieldTableCell.forTableColumn());

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

        qtdCol.setCellFactory(column -> new TableCell<Produto, Integer>() {
            private final Button addButton = new Button("+");
            private final Button minusButton = new Button("-");
            private final Label quantidadeLabel = new Label();
            private final HBox hbox = new HBox(5, minusButton, quantidadeLabel, addButton);

            {
                addButton.setOnAction(event -> {
                    Produto produto = getTableView().getItems().get(getIndex());
                    if (produto != null) {
                        produto.setQtd(produto.getQtd() + 1); // Incrementa a quantidade
                        quantidadeLabel.setText(String.valueOf(produto.getQtd())); // Atualiza o label
                        ctx.updtade(produto); // Atualiza no banco de dados
                        getTableView().refresh(); // Atualiza a tabela
                    }
                });

                minusButton.setOnAction(event -> {
                    Produto produto = getTableView().getItems().get(getIndex());
                    if (produto != null && produto.getQtd() > 0) { // Evita quantidade negativa
                        produto.setQtd(produto.getQtd() - 1); // Decrementa a quantidade
                        quantidadeLabel.setText(String.valueOf(produto.getQtd())); // Atualiza o label
                        ctx.updtade(produto); // Atualiza no banco de dados
                        getTableView().refresh(); // Atualiza a tabela
                    }
                });
            }

            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableView() == null) {
                    setGraphic(null);
                } else {
                    Produto produto = getTableView().getItems().get(getIndex());
                    if (produto != null) {
                        quantidadeLabel.setText(String.valueOf(produto.getQtd())); // Atualiza a quantidade no label
                        setGraphic(hbox);
                    }
                }
            }
        });

        // Adiciona os produtos à tabela
        tabela.setItems(ctx.listaproduto());
    }

}
