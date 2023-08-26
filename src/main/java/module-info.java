module com.example.carreview {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;


	opens com.example.carreview to javafx.fxml;
	exports com.example.carreview;
}