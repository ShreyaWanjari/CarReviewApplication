package com.example.carreview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
	@FXML
	private AnchorPane homeAnchor;
	@FXML
	private AnchorPane MarutiPAne;


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}
	@FXML
	public void marutiAction(ActionEvent e) throws Exception {
		System.out.println("jfkh");
		try {
			loadFxml("Maruti.fxml","Maruti Suzuki");
		}catch (Exception e1){
			e1.getMessage();
		}
		//HomePageController.getInstance().createPage(MarutiPAne,"Marurti.fxml");
	}

	private void loadFxml(String s, String s1) throws Exception {


		homeAnchor = FXMLLoader.load(getClass().getResource(s));
		//homeAnchor.getChildren().clear();

		Stage stage=new Stage(StageStyle.DECORATED);
		stage.setScene(new Scene(homeAnchor));
		stage.setTitle(s1);
		stage.show();
	}
}
