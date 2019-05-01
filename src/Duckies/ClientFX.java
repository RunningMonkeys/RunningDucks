package Duckies;



//import javafx.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.Random;

public class ClientFX extends Application{

	
	private Client  conn = createClient();
	private TextArea messages = new TextArea();
	private boolean[][] clues = {{true,false,true,false,false},{true,false,false,false,false},{false,false,true,false,false},{true,false,true,true,false},{true,false,false,false,false}};
	private GridPane a;
	private Button up = new Button("up");
	private Button down = new Button("down");
	private Button left = new Button("left");
	private Button right = new Button("right");

	private Parent createContent() {
		messages.setPrefHeight(200);
		a = showGrid();
		a.setPrefSize(350,350);
		TextField input = new TextField();
		


		input.setOnAction(event -> {
			String message = input.getText();
			input.clear();
			
			messages.appendText(message + "\n");
			try {
				conn.send(message);
			}
			catch(Exception e) {
				
			}
			
		});

		VBox root = new VBox(20,messages,a,input);
//		root.getChildren().add(a);
		root.setPrefSize(600, 600);
		
		return root;
		
	}


	private GridPane showGrid(){
		GridPane grid = new GridPane();
		int rowNum = 5;
		int colNum = 5;


		for (int row = 0; row < rowNum; row++) {
			for (int col = 0; col < colNum; col++) {
				Rectangle rec = new Rectangle();
				rec.setWidth(70);
				rec.setHeight(70);
				if(clues[row][col]){
					rec.setFill(Color.BLACK);
				}
				else{
					rec.setFill(Color.WHITE);
				}

				GridPane.setRowIndex(rec, row);
				GridPane.setColumnIndex(rec, col);
				grid.getChildren().addAll(rec);
			}
		}


		return grid;
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
//


	}
	
	@Override
	public void init() throws Exception{
		conn.startConn();
	}
	
	@Override
	public void stop() throws Exception{
		conn.closeConn();
	}
	
	
	private Client createClient() {
		return new Client("127.0.0.1", 5555, data -> {
			Platform.runLater(()->{
				messages.appendText(data.toString() + "\n");
			});
		});
	}

}