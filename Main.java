package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	
	@Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Food Query and Meal Analysis");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 500, 350);
        primaryStage.setScene(scene);
        
        Text scenetitle = new Text("Add Food");
        
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 1, 1);

        //Instantiates new text boxes for each required field in addFood//
        Label foodName = new Label("Food Name");
        grid.add(foodName, 0, 1);
        	TextField foodTextField = new TextField();
        	grid.add(foodTextField, 1, 1);

        Label calCount = new Label("Calories");
        grid.add(calCount, 0, 2); 
        	TextField calTextField = new TextField();
        	grid.add(calTextField, 1, 2);
        
        Label fatCount = new Label("Fat");
        grid.add(fatCount, 0, 3);  
        	TextField fatTextField = new TextField();
        	grid.add(fatTextField, 1, 3);
        
        Label carbCount = new Label("Carbohydrates");
        grid.add(carbCount, 0, 4);     
        	TextField carbTextField = new TextField();
        	grid.add(carbTextField, 1, 4);
        
        Label fiberCount = new Label("Fiber");
        grid.add(fiberCount, 0, 5);   
        	TextField fiberTextField = new TextField();
        	grid.add(fiberTextField, 1, 5);
        
        Label proteinCount = new Label("Protein");
        grid.add(proteinCount, 0, 6); 
        	TextField proteinTextField = new TextField();
        	grid.add(proteinTextField, 1, 6);
        //**********************************************//
        
        //Unit Labels for the text boxes//
        Label calType = new Label("g.");
        grid.add(calType, 3, 2);
        
        Label fatType = new Label("g.");
        grid.add(fatType, 3, 3);
        
        Label carbType = new Label("g.");
        grid.add(carbType, 3, 4);
        
        Label fiberType = new Label("g.");
        grid.add(fiberType, 3, 5);
        
        Label proteinType = new Label("g.");
        grid.add(proteinType, 3, 6);
       //*********************************//
        
        Button btn = new Button("Add New Food");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 3, 7);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Analysis button pressed");
            }
        });
      
        primaryStage.show();
    }

	
	public static void main(String[] args) {
		launch(args);
	}
}
