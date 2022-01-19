package com.example.etlap;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.sql.SQLException;
import java.util.List;

public class HelloController extends Controller {

    @FXML
    private TableColumn kiirKateg;
    @FXML
    private TableColumn kiirAr;
    @FXML
    private TableColumn kiirNev;
    @FXML
    private TextArea listazasTxt;
    @FXML
    private TableView etelekView;


    @FXML
    public void emelesPercBtnClick() {
    }

    @FXML
    public void emelesFtBtnClick() {
    }

    @FXML
    public void ujEtelBtnClick() {
    }

    @FXML
    public void torlesBtnClick() {
    }

    private EtlapDb db;

    private void etlapListaFeltolt(){
        try {
            List<Etel> etelList = db.getEtlap();
            etelekView.getItems().clear();
            for(Etel etel: etelList){
                etelekView.getItems().add(etel);
            }
        } catch (SQLException e) {
            hibaKiir(e);
        }
    }
}