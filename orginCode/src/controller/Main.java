package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.*;



public class Main extends Application {
	
	public static Stage parentWindow;

	@Override
	public void start(Stage primaryStage) throws Exception{
			parentWindow = primaryStage;
			Parent login = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			Scene loginscene = new Scene(login);
			primaryStage.setScene(loginscene);
			//primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setTitle("신호등 가계부");
			primaryStage.getIcons().add(new Image("file:image/BookIcon.png"));
			primaryStage.show();
			primaryStage.setResizable(false);
	}

	public static void main(String[] args) {
		
		launch(args);
	}
}
