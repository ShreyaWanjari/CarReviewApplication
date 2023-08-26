package com.example.carreview;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.Stack;



public class LoginController implements Initializable {

	@FXML
	 private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button signup;
	@FXML
	private Button login;
	@FXML
	private Button forgot;
	@FXML
	private CheckBox remember;
	@FXML
	private ImageView progress;
	private static LoginController instance;

	public LoginController(){
		instance = this;

	}

	public static LoginController getInstance(){
		return instance;
	}
	public String username(){
		return  username.getText();
	}
private DBHandler handler;
private Connection conn;
	private PreparedStatement pst;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		progress.setVisible(false);
	username.setStyle("-fx-text-inner-color:   #00003d;-fx-background-color : #FFFFFF;-fx-background-radius:20");
	password.setStyle("-fx-text-inner-color:   #00003d;-fx-background-color : #FFFFFF;-fx-background-radius:20");

	handler= new DBHandler();

	}
	@FXML
	public void loginAction(ActionEvent e) throws Exception{
		progress.setVisible(true);
		PauseTransition pt = new PauseTransition();
		pt.setDuration(Duration.seconds(3));
		pt.setOnFinished(event -> {
			System.out.println("login success");
		});
		pt.play();

		//retrive data from db
		conn = handler.getConnection();
		String q1= "Select * from users where name =? and password =?  ";
		pst  = conn.prepareStatement(q1);
		pst.setString(1,username.getText());
		pst.setString(2,password.getText());
		ResultSet rs = pst.executeQuery();
		int coumt = 0;
		while (rs.next()){
			coumt = coumt+1;
		}
		if(coumt ==1){
			login.getScene().getWindow().hide();
			Stage stage=new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
			Scene scene=new Scene(root);
			stage.setScene(scene);
			stage.show();

		}else{
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Username and password is not correct");
			alert.show();
			progress.setVisible(false);
		}
		conn.close();
		pst.close();
	}
	@FXML
	public void SignUp(ActionEvent e1) throws IOException {
		login.getScene().getWindow().hide();
		Stage signUp = new Stage();
		Parent root = FXMLLoader.load(LoginController.class.getResource("Signup.fxml"));
		Scene scene  = new Scene(root);
		signUp.setScene(scene);
		signUp.show();
		signUp.setResizable(false);

	}

}