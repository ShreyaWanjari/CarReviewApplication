package com.example.carreview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MarutiController implements Initializable {

	@FXML
	private Button bck;


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}
	@FXML
	public void backAction(ActionEvent e){
		bck.getScene().getWindow().hide();
	}
}
