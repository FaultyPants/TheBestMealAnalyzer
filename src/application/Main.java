package application;

    

import javafx.application.Application;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;

import javafx.geometry.Insets;

import javafx.geometry.Pos;

import javafx.stage.Stage;

import javafx.scene.Group;

import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;

import javafx.scene.layout.Pane;

import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;

import javafx.scene.text.Font;

import javafx.scene.text.FontWeight;

import javafx.scene.text.Text;

import javafx.geometry.HPos;


public class Main extends Application {
	
    public static void main(String[] args) {

        launch(args);

    }

    @Override

    public void start(Stage primaryStage) {
    	Stage stage = new Stage();
  
    	BorderPane primaryPane = new BorderPane();
        
        primaryPane.setBottom(addFood());  	//Creates a stage for add food//
        primaryPane.setLeft(foodList());
        primaryPane.setCenter(mealList());
        primaryPane.setRight(nutritionInformation());
        primaryPane.setTop(filters());
        
        Scene scene = new Scene(primaryPane, 1920, 1080);
        stage.setTitle("Meal Analysis and Query");
        
        stage.setScene(scene);
        stage.show();
    }


    public static Pane nutritionInformation()
    {
    	GridPane grid = new GridPane();
    try
	{
		
		grid.setHgap(15);

        grid.setVgap(20);

        grid.setPadding(new Insets(20, 20, 20, 20));
        Label calorieLabel = new Label("Calories:");
        Label fatLabel = new Label("Fat (g):");
        Label carbLabel = new Label("Carbs (g):");
        Label fiberLabel = new Label("Fiber (g):");
        Label proteinLabel = new Label("Protein (g):");
        Label ingredientLabel = new Label("Ingredients:");
        
        TextField calorieField = new TextField();
        TextField fatField = new TextField();
        TextField carbField = new TextField();
        TextField fiberField = new TextField();
        TextField proteinField = new TextField();
        TextField ingredientField = new TextField();
        
        grid.add(calorieLabel, 0, 0);
        grid.add(fatLabel, 0, 1);
        grid.add(carbLabel, 0, 2);
        grid.add(fiberLabel, 0, 3);
        grid.add(proteinLabel, 0, 4);
        grid.add(ingredientLabel, 0, 5);
        
        grid.add(calorieField, 1, 0);
        grid.add(fatField, 1, 1);
        grid.add(carbField, 1, 2);
        grid.add(fiberField, 1, 3);
        grid.add(proteinField, 1, 4);
        grid.add(ingredientField, 1, 5);
        
       
        
	}
	catch (Exception e)
	{
		e.printStackTrace();
}
	return grid;
    }


    public static ToolBar addFood() {
    	
    	Button button = new Button("Add Food");
        new HBox(10);


        final Text actiontarget = new Text();

        button.setOnAction(new EventHandler<ActionEvent>() {   

            @Override
            public void handle(ActionEvent e) {

                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Analysis button pressed");

            }

        });
        
        Label paneTitle = new Label("Add Food");

        paneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
       
    	 ToolBar addFood = new ToolBar(
         paneTitle,
         //Instantiates new text boxes for each required field in addFood//
         new Label("Food Name"),
             new TextField(),
             new Separator(),

         new Label("Calories"), 
             new TextField(),
             new Label("g."),

         new Label("Fat"),
             new TextField(),
             new Label("g."),

         new Label("Carbohydrates"),    
             new TextField(),
             new Label("g."),
         

         new Label("Fiber"),
             new TextField(),
             new Label("g."),

         new Label("Protein"), 
             new TextField(),
             new Label("g."),
             button
    			 );
    	 
         return addFood;
    }
    

    //foodList method that holds the code for the Food List scene made by Charlie

    public static Pane foodList() {
    	
        Pane root2 = new Pane(new Group());
        
        try {


            
              Button filter = new Button();

              filter.setText("Add Filter");

              Button addNewFoodFile = new Button();

              addNewFoodFile.setLayoutX(360);

              addNewFoodFile.setLayoutY(470);

              addNewFoodFile.setText("Load New Food File");

              Button addFood = new Button();

              addFood.setText("Add Food Item");

              addFood.setLayoutX(79);

              addFood.setLayoutY(0);

              

              Button saveToFile = new Button();

              saveToFile.setText("Save Food List");

              saveToFile.setLayoutX(390);

              

              TableView table = new TableView();

              

              final Label label = new Label("Food List");

              label.setFont(new Font("Arial", 20));

              TableColumn foodNameCol = new TableColumn("Food Name"); 

              TableColumn quantityCol = new TableColumn("Quanity");

              table.getColumns().addAll(foodNameCol, quantityCol);

              

              final VBox vbox = new VBox();

              vbox.setSpacing(5);

              vbox.setPadding(new Insets(45, 0, 0, 10));

              vbox.getChildren().addAll(label, table);

              

              root2.getChildren().addAll(vbox);



            root2.getChildren().add(filter);

            root2.getChildren().add(addNewFoodFile);

            root2.getChildren().add(addFood);

            root2.getChildren().add(saveToFile);
            

        } catch(Exception e) {

            e.printStackTrace();

        }
        return root2;
       
    }

    

    //  filters method that generates a place to input filter criteria

    //  made by Andrew

    public static Pane filters() {
    	   //  create grid pane

        GridPane grid = new GridPane();
        
        try {

            grid.setHgap(15);

            grid.setVgap(15);

            grid.setPadding(new Insets(30, 30, 30, 30)); //  (top, bottom, right, left)

            

            Label headerLabel = new Label("FILTERS");

            headerLabel.setFont(new Font("Arial", 16));

            grid.addRow(0, headerLabel);

            

            Label containsLabel = new Label("Contains: ");

            grid.add(containsLabel, 0, 1);     //  child, col, row

            TextField containsIn = new TextField();

            containsIn.setPromptText("Enter an ingredient.. ");

            grid.add(containsIn, 1, 1, 2, 1);     //  span 3 cols, 1 row

            

            //  create labels for the top of "table (grid)" to specify what should go in 

            //  each input

            Label minLabel = new Label("Minimum");

            GridPane.setHalignment(minLabel, HPos.CENTER);

            Label maxLabel = new Label("Maximum");

            GridPane.setHalignment(maxLabel, HPos.CENTER);

            Label equalsLabel = new Label("Exact");

            GridPane.setHalignment(equalsLabel, HPos.CENTER);

            grid.add(minLabel, 1, 3);

            grid.add(maxLabel, 2, 3);

            grid.add(equalsLabel, 3, 3);

            

            //  calorie row

            Label calLabel = new Label("Calories: ");

            TextField minCalIn = new TextField();

            TextField maxCalIn = new TextField();

            TextField equalsCalIn = new TextField();

            grid.addRow(4, calLabel, minCalIn, maxCalIn, equalsCalIn);

            

            //  fat row

            Label fatLabel = new Label("Fat: ");

            TextField minFatIn = new TextField();

            TextField maxFatIn = new TextField();

            TextField equalsFatIn = new TextField();

            grid.addRow(5, fatLabel, minFatIn, maxFatIn, equalsFatIn);

            

            //  carb row

            Label carbLabel = new Label("Carbs: ");

            TextField minCarbIn = new TextField();

            TextField maxCarbIn = new TextField();

            TextField equalsCarbIn = new TextField();

            grid.addRow(6, carbLabel, minCarbIn, maxCarbIn, equalsCarbIn);

            

            //  fiber row

            Label fiberLabel = new Label("Fiber: ");

            TextField minFiberIn = new TextField();

            TextField maxFiberIn = new TextField();

            TextField equalsFiberIn = new TextField();

            grid.addRow(7, fiberLabel, minFiberIn, maxFiberIn, equalsFiberIn);

            

            //  protein row

            Label proteinLabel = new Label("Protein: ");
            TextField minProteinIn = new TextField();
            TextField maxProteinIn = new TextField();
            TextField equalsProteinIn = new TextField();
            grid.addRow(8, proteinLabel, minProteinIn, maxProteinIn, equalsProteinIn);

            

            //  create button to actually execute program with filter input

            Button doFilter = new Button();

            doFilter.setText("Filter");

            GridPane.setHalignment(doFilter, HPos.RIGHT);

            grid.add(doFilter, 3, 9);

            

        } catch(Exception e) {

            e.printStackTrace();

        }
        return grid;

    }
    
    public static Pane mealList() {
    	Pane root2 = new Pane(new Group());
    	
        try {
   

              Button addNewFoodFile = new Button();

              	addNewFoodFile.setLayoutX(360);
              	addNewFoodFile.setLayoutY(470);
              	addNewFoodFile.setText("Analyze");

              Button addFood = new Button();
              	addFood.setText("Add Food Item");
              	addFood.setLayoutX(79);
              	addFood.setLayoutY(0);

              
              Button saveToFile = new Button();
              	saveToFile.setText("Save Food List");
              	saveToFile.setLayoutX(390);

              TableView table = new TableView();
            
              final Label label = new Label("Meal List");

              label.setFont(new Font("Arial", 20));

              TableColumn foodNameCol = new TableColumn("Food Name"); 

              TableColumn quantityCol = new TableColumn("remove");

              table.getColumns().addAll(foodNameCol, quantityCol);
              foodNameCol.setCellValueFactory(
                      new PropertyValueFactory<>("firstName"));
           

              final VBox vbox = new VBox();

              vbox.setSpacing(5);

              vbox.setPadding(new Insets(45, 0, 0, 10));

              vbox.getChildren().addAll(label, table);
             

              root2.getChildren().addAll(vbox);


            root2.getChildren().add(addNewFoodFile);
         

        } catch(Exception e) {

            e.printStackTrace();
        }
        
        return root2;
    }
}