package com.example.carreview;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

	@FXML
	AnchorPane holderPane;
	AnchorPane home;
	@FXML
	TextField wel;
	@FXML
	AnchorPane anchor;
	@FXML
	ImageView dotbtn;
	@FXML
	Button btn;

	 static HomePageController instance;

	public HomePageController(){
		instance = this;
	}
	public static HomePageController getInstance(){
		return instance;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			wel.setStyle("-fx-text-inner-color: #FFFFFF ;-fx-background-color :transparent;");

			createPage(holderPane,"Home.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setUsername(LoginController.getInstance().username());
	}

	public void setUsername(String username){
		this.wel.setText("Welcome ,"+ username);
	}
	public void btnclicked(ActionEvent e) throws IOException {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Exit Or Logout Actions");
		alert.setContentText("Do you really want to exit..");
		ButtonType exitButton = new ButtonType("Exit");
		ButtonType logbtn = new ButtonType("Log out");
		alert.getButtonTypes().setAll(exitButton,logbtn);
		Optional<ButtonType> clickedBtn = alert.showAndWait();

		if(clickedBtn.isPresent() && clickedBtn.get()  == exitButton){
			//System.out.println("yes btn");
		Platform.exit();
		System.exit(0);
		}
		if(clickedBtn.isPresent() && clickedBtn.get() == logbtn){
			//System.out.println("no btn clicked");

			btn.getScene().getWindow().hide();

			Stage stage =new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene  = new Scene(root);
			stage.setScene(scene);
			stage.show();

			stage.setResizable(false);
		}

	}
	private void setNode(Node node){
		holderPane.getChildren().clear();
		holderPane.getChildren().add((Node) node);
		FadeTransition ft = new FadeTransition(Duration.millis(1500));
		ft.setNode(node);
		ft.setFromValue(0.1);
		ft.setToValue(1);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		ft.play();
	}
	 void createPage(AnchorPane anchor ,String loc) throws Exception {
		 System.out.println("hkhk,");
		anchor = FXMLLoader.load(HomePageController.class.getResource(loc));
		setNode(anchor);

	}
}
