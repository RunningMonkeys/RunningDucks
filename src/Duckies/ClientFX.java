package Duckies;



//import javafx.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ClientFX extends Application{

	
	private Client  conn = createClient();
	private TextArea messages = new TextArea();
	private boolean[][] clues;
	private GridPane a;
	private Button up = new Button("up");
	private Button down = new Button("down");
	private Button left = new Button("left");
	private Button right = new Button("right");
	int pnum;

	private EventHandler<ActionEvent> vote = new EventHandler<ActionEvent>(){
		public void handle(ActionEvent event){
			
			Button b = (Button)event.getSource();
			String v = b.getText();
			messages.appendText(v + "\n");
			try {
				conn.send(v);
			}
			catch(Exception e) {

			}

		}
	};


	private Parent createContent() {
//		messages.setPrefHeight(200);
		pnum = 1;
		a = new GridPane();
		clues = new boolean[1][1];
		clues[0][0]= false;
		showGrid(a);
		a.setPrefSize(350,350);
		TextField input = new TextField();

		up.setOnAction(vote);
		down.setOnAction(vote);
		left.setOnAction(vote);
		right.setOnAction(vote);

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

		HBox buttons = new HBox (10, up, down, left, right);
		VBox root = new VBox(20,messages,a,input,buttons);
//		root.getChildren().add(a);
		root.setPrefSize(600, 600);
		
		return root;
		
	}


	private void showGrid(GridPane grid){
		int rowNum = clues.length;
		int colNum = clues[0].length;
		System.out.println("Player" + pnum + ".jpg");
		Image img = new Image("Player" + pnum + ".jpg");

		for (int row = 0; row < rowNum; row++) {
			for (int col = 0; col < colNum; col++) {
				Rectangle rec = new Rectangle();
				
				rec.setWidth(70);
				rec.setHeight(70);
				if(clues[row][col]){
					rec.setFill(new ImagePattern(img));
				}
				else{
					rec.setFill(Color.WHITE);
				}

				GridPane.setRowIndex(rec, row);
				GridPane.setColumnIndex(rec, col);
				grid.getChildren().addAll(rec);
			}
		}


		
	}


	private boolean[][] strToArr(String clue){
		System.out.println(clue);
		int comma = clue.indexOf(',');
		int r = Integer.parseInt(clue.substring(0,comma));
		int semi = clue.indexOf(':');
		int c = Integer.parseInt(clue.substring(comma+2,semi));

		clue = clue.substring(semi+1);
		boolean [][] result = new boolean[r][c];

		for (int i=0; i<r; i++) {
			for(int j=0; j<c; j++){
				result [i][j] = clue.charAt(0) == '1'? true: false;
				clue =  clue.substring(1);

			}

		}

		return result;
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
				String strData = data.toString();
				if(strData.startsWith("Maze Here:"))
				{
					strData = strData.substring(strData.indexOf(':')+1);
					clues = strToArr(strData);
					showGrid(a);
				}
				else if(strData.startsWith("welcome player "))
				{
					strData = strData.substring(strData.indexOf('r')+1);
					strData = strData.trim();
					pnum = Integer.parseInt(strData);
				}
				else
				{
					messages.appendText(data.toString() + "\n");
				}
				
			});
		});
	}

}
