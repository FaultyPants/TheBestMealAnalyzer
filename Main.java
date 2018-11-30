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

import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
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
    	Stage foodListStage = new Stage();
    	Stage filterStage = new Stage();
    	Stage mealListStage = new Stage();
    	Stage addFoodStage = new Stage();
    	Stage nutriInfoStage = new Stage();

      //Created a second stage so that multiple stages can be seen from this class

        Stage secondaryStage = new Stage();

      //Call to the foodList GUI scene made by Charlie

        foodList(secondaryStage);       

        //  same thing Charlie did but for Andrew's filter GUI

        filters(filterStage);
        
        Stage thirdStage = new Stage();

        mealList(thirdStage);
        
        addFood(addFoodStage);
        
        //Uncomment and rename if needed when implemented
        //analyzeMeal(nutriInfoStage);

        primaryStage.setTitle("Food Query and Meal Analysis");        

       
    }



    
    public static void analyzeMeal(Stage primaryStage) {
    	
    }


    public static void addFood(Stage foodStage) {
    	 GridPane grid = new GridPane();

         grid.setAlignment(Pos.CENTER);

         grid.setHgap(10);

         grid.setVgap(10);

         grid.setPadding(new Insets(25, 25, 25, 25));

         Scene scene = new Scene(grid, 500, 350);

         foodStage.setScene(scene);
         
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

         

         Button button = new Button("Add New Food");
         HBox hbBtn = new HBox(10);

         hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
         hbBtn.getChildren().add(button);
         grid.add(hbBtn, 3, 7);

         final Text actiontarget = new Text();
         grid.add(actiontarget, 1, 7);

         button.setOnAction(new EventHandler<ActionEvent>() {   

             @Override
             public void handle(ActionEvent e) {

                 actiontarget.setFill(Color.FIREBRICK);
                 actiontarget.setText("Analysis button pressed");

             }

         });

       

         foodStage.show();
    }
    

    //foodList method that holds the code for the Food List scene made by Charlie

    public static void foodList(Stage primaryStage) {

        try {

            Pane root2 = new Pane(new Group());

            

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

            

            primaryStage.setScene(new Scene (root2, 500, 500));

            primaryStage.setTitle("Food List");

            primaryStage.show();

            

            

        } catch(Exception e) {

            e.printStackTrace();

        }

    }

    

    //  filters method that generates a place to input filter criteria

    //  made by Andrew

    public static void filters(Stage primaryStage) {

        try {

            //  create grid pane

            GridPane grid = new GridPane();

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

            

            

            primaryStage.setScene(new Scene(grid));

            primaryStage.setTitle("Filter");

            primaryStage.show();

            

            

        } catch(Exception e) {

            e.printStackTrace();

        }

    }
    
    public static void mealList(Stage primaryStage) {

        try {

            Pane root2 = new Pane(new Group());
   

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
         

            primaryStage.setScene(new Scene (root2, 500, 500));
            primaryStage.setTitle("Meal List");
            primaryStage.show();
        

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}