package Duckies;



//import javafx.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.function.Consumer;

public class ServerFX extends Application{

	
	private Server  conn =  createServer() ;
	private TextArea messages = new TextArea();
	
	private Parent createContent() {
		messages.setPrefHeight(600);
		
		VBox root = new VBox(20, messages);
		root.setPrefSize(600, 600);
		
		return root;
		
				
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setScene(new Scene(createContent()));
		primaryStage.show();
		
	}
	
	@Override
	public void init() throws Exception{
		conn.startConn();
	}
	
	@Override
	public void stop() throws Exception{
		conn.closeConn();
	}
	
	private Server createServer() {
		return new Server(5555, new Consumer<Serializable>() {
			@Override
			public void accept(Serializable data) {
				Platform.runLater(() -> {
					messages.appendText(data.toString() + "\n");
				});
			}
		});
	}

}
