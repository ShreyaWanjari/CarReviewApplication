package com.example.carreview;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;


public class SignUpController implements Initializable {
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button login;
	@FXML
	private Button signup;
	@FXML
	private ImageView progress;
	@FXML
	private RadioButton male;
	@FXML
	private RadioButton female;
	@FXML
	private ToggleGroup group;
	@FXML
	private AnchorPane parent;
	@FXML
	private TextField loc;
	private Connection connection;
	private DBHandler handler;
	private PreparedStatement pst;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		progress.setVisible(false);
		username.setStyle("-fx-text-inner-color: #00003d;-fx-background-color : #FFFFFF;-fx-background-radius:20 ");
		password.setStyle("-fx-text-inner-color:   #00003d;-fx-background-color : #FFFFFF;-fx-background-radius:20 ");
		loc.setStyle("-fx-text-inner-color:   #00003d;-fx-background-color : #FFFFFF;-fx-background-radius:20 ");
		handler = new DBHandler();
	}
	@FXML
	public void signup(ActionEvent e) throws Exception {
		progress.setVisible(true);
		PauseTransition pt = new PauseTransition();
		pt.setDuration(Duration.seconds(3));
		pt.setOnFinished(event -> {
			System.out.println("signup success");
		});
		pt.play();

		//saving data
		String insert = "insert into users(name,password,gender,loc) values(?,?,?,?)";
		connection = handler.getConnection();
		pst = connection.prepareStatement(insert);
		pst.setString(1, username.getText());
		pst.setString(2,password.getText());
		pst.setString(3,setGender());
		pst.setString(4,loc.getText());

		pst.executeUpdate();

		connection.close();
		pst.close();
	}
	@FXML
	public void loginAction(ActionEvent e1) throws IOException {
		signup.getScene().getWindow().hide();
		Stage signUp = new Stage();
		Parent root = FXMLLoader.load(SignUpController.class.getResource("Login.fxml"));
		Scene scene  = new Scene(root);
		signUp.setScene(scene);
		signUp.show();
		signUp.setResizable(false);
	}
	public String setGender(){
		String gen ="";
		if(male.isSelected()){
			gen = "Male";
		}else{
			gen= "Female";
		}
		return gen;
	}

}
