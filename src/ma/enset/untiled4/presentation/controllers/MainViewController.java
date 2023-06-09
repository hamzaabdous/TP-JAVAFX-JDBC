package ma.enset.untiled4.presentation.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import javafx.util.Callback;
import ma.enset.untiled4.dao.entities.Category;
import ma.enset.untiled4.dao.entities.Product;
import ma.enset.untiled4.dao.implementation.CategoryRepositoryImpl;
import ma.enset.untiled4.dao.implementation.ProductRepositoryImpl;

import ma.enset.untiled4.presentation.ApplicationJavafx;
import ma.enset.untiled4.service.CatalogService;
import ma.enset.untiled4.service.implementation.CatalogServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @FXML private GridPane gridPaneId;
    @FXML private TextField textFieldName;
    @FXML private TextField textFieldRef;
    @FXML private TextField textFieldPrice;
    @FXML private ComboBox<Category> comboBoxCategory;
    @FXML private TextField textFieldSearch;
    @FXML private TableView<Product> tableViewProducts;
    @FXML private TableColumn<Long, Product> tableColumnId;
    @FXML private TableColumn<String, Product> tableColumnName;
    @FXML private TableColumn<String, Product> tableColumnRef;
    @FXML private TableColumn<Double, Product> tableColumnPrice;
    @FXML private TableColumn<Category, Product> tableColumnCategory;

    ObservableList<Product> productsData = FXCollections.observableArrayList();
    ObservableList<Category> categoriesData = FXCollections.observableArrayList();

    private CatalogService catalogService;



    private void configureTableColumns(){
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnRef.setCellValueFactory(new PropertyValueFactory<>("reference"));
        tableColumnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableColumnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureTableColumns();
        catalogService = new CatalogServiceImpl(new ProductRepositoryImpl(), new CategoryRepositoryImpl());

        //initialize productsData and categoriesData
        categoriesData.setAll(catalogService.findAllCategories());
        productsData.setAll(catalogService.findAllProducts());

        //set models to the view

      /*  comboBoxCategory.setCellFactory(new Callback<ListView<Category>, ListCell<Category>>() {
            @Override
            public ListCell<Category> call(ListView<Category> param) {
                return new ListCell<Category>(){
                    @Override
                    protected void updateItem(Category item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item == null || empty){
                            setGraphic(null);
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        });*/
        comboBoxCategory.setItems(categoriesData);
        tableViewProducts.setItems(productsData);



        tableViewProducts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, product) -> {
            if (product != null) {
                textFieldName.setText(product.getName());
                textFieldRef.setText(product.getReference());
                textFieldPrice.setText(String.valueOf(product.getPrice()));
                comboBoxCategory.setValue(product.getCategory());
            }
        });

    }

    @FXML
    void onAddProduct() {
        validation();
        Product product = new Product();
        product.setName(textFieldName.getText());
        product.setReference(textFieldRef.getText());
        product.setPrice((float) Double.parseDouble(textFieldPrice.getText()));
        product.setCategory(comboBoxCategory.getSelectionModel().getSelectedItem());
        this.catalogService.addProduct(product);
        onClearData();
        loadData();
    }
    @FXML
    void onDeleteProduct() {
        Product product = getSelectedProduct();
        catalogService.deleteProduct(product.getId());
        onClearData();
        loadData();
    }
    @FXML
    void onUpdateProduct() {
        validation();
        Product product = getSelectedProduct();
        product.setName(textFieldName.getText());
        product.setReference(textFieldRef.getText());
        product.setPrice((float) Double.parseDouble(textFieldPrice.getText()));
        product.setCategory(comboBoxCategory.getSelectionModel().getSelectedItem());
        catalogService.updateProduct(product);
        onClearData();
        loadData();
    }

    @FXML
    void onChangeText() {
        String keyword = textFieldSearch.getText();
        List<Product> productsByQuery = catalogService.findProductsByQuery(keyword);
        productsData.setAll(productsByQuery);
    }

    @FXML
    void onClearData() {
        textFieldPrice.setText("");
        textFieldRef.setText("");
        textFieldName.setText("");
        comboBoxCategory.setValue(null);
    }

    void loadData()  {
        List<Product> allProducts = catalogService.findAllProducts();
        productsData.clear();
        productsData.addAll(allProducts);
    }
    @FXML
    void getScene() throws IOException {
    }
    private Product getSelectedProduct(){
        Product product = tableViewProducts.getSelectionModel().getSelectedItem();
        if(product == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("no product is selected");
            alert.show();
            throw new RuntimeException("no selected product") ;
        }
        return product;
    }

    private void validation(){
        String name = textFieldName.getText();
        String ref = textFieldRef.getText();
        String price = textFieldPrice.getText();
        Category category = comboBoxCategory.getSelectionModel().getSelectedItem();


        if(name.isEmpty()  ||
            ref.isEmpty()  ||
            price.isEmpty()  || category == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("name=["+name+"] ref=["+ref+"] price=["+price+"]");
            alert.show();
            throw new IllegalArgumentException();
        }

        try{
            Double.parseDouble(price);
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
            throw new IllegalArgumentException();
        }


    }


}
