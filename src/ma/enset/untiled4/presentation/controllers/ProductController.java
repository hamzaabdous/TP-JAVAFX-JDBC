package ma.enset.untiled4.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    @FXML
    private TextField textNom;
    @FXML
    private TextField textRef;
    @FXML
    private TextField textPrix;
    @FXML
    private ComboBox comboCategorie;
    @FXML
    private ListView listviewPro ;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
