package application;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	static FoodData foodData;
    private static List<FoodItem> mealListFood = new ArrayList<FoodItem>();
    static TableView<FoodItem> mealListTable = new TableView<FoodItem>();
    static TableView<FoodItem> foodListTable = new TableView<FoodItem>();
	
	public static void main(String[] args) {
		foodData = new FoodData();
		launch(args);

	}

	@Override

	public void start(Stage primaryStage) {
		Stage stage = new Stage();

		BorderPane primaryPane = new BorderPane();

		primaryPane.setTop(addFood()); // Creates a stage for add food//
		primaryPane.setCenter(foodList());
		primaryPane.setRight(mealList());
		primaryPane.setBottom(nutritionInformation());
		primaryPane.setLeft(filters());

		Scene scene = new Scene(primaryPane, 1000, 650);
		stage.setTitle("Food Query and Meal Analysis");

		stage.setScene(scene);
		stage.show();
	}

	public static ToolBar nutritionInformation() {
		ToolBar grid = null;
		TextField calField = new TextField();
		TextField fatField = new TextField();
		TextField carbField = new TextField();
		TextField fiberField = new TextField();
		TextField proteinField = new TextField();
		TextField ingredientField = new TextField();
		try {
			calField.setPrefWidth(75);
			fatField.setPrefWidth(50);
			carbField.setPrefWidth(50);
			fiberField.setPrefWidth(50);
			proteinField.setPrefWidth(50);
			ingredientField.setPrefWidth(200);

			grid = new ToolBar(new Label("Calories:"), calField, new Label("Fat (g):"), fatField,
					new Label("Carbs (g):"), carbField, new Label("Fiber (g):"), fiberField, new Label("Protein (g):"),
					proteinField, new Separator(), new Label("Ingredients:"), ingredientField);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return grid;
	}

	public static ToolBar addFood() {

		Button button = new Button("Add Food");
		new HBox(10);
	

		ToolBar addFood = null;
		TextField foodNameField = new TextField();
		TextField calField = new TextField();
		TextField fatField = new TextField();
		TextField carbField = new TextField();
		TextField fiberField = new TextField();
		TextField proteinField = new TextField();

		try {
			foodNameField.setPrefWidth(150);
			calField.setPrefWidth(50);
			fatField.setPrefWidth(50);
			carbField.setPrefWidth(50);
			fiberField.setPrefWidth(50);
			proteinField.setPrefWidth(50);
			addFood = new ToolBar(
					// Instantiates new text boxes for each required field in addFood//
					new Label("Food Name"), foodNameField, new Separator(),

					new Label("Calories"), calField, new Label("g."),

					new Label("Fat"), fatField, new Label("g."),

					new Label("Carbs"), carbField, new Label("g."),

					new Label("Fiber"), fiberField, new Label("g."),

					new Label("Protein"), proteinField, new Label("g."), button);
			
			button.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					
					FoodItem(foodNameField.getText());
					addNutrient("Calories", Double.parseDouble(calField.getText()));
					addNutrient("Fat", Double.parseDouble(fatField.getText()));
					addNutrient("Carbs", Double.parseDouble(carbField.getText()));
					addNutrient("Fiber", Double.parseDouble(fiberField.getText()));
					addNutrient("Protein", Double.parseDouble(proteinField.getText()));
					
					foodNameField.clear();
					calField.clear();
					fatField.clear();
					carbField.clear();
					fiberField.clear();
					proteinField.clear();
					
				}

			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		return addFood;
	}

// foodList method that holds the code for the Food List scene made by Charlie
    public static Pane foodList() {
        
        
        Pane root2 = new Pane(new Group());

        try {
            Button addNewFoodFile = new Button();
            addNewFoodFile.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                   foodData.loadFoodItems("foodItems.csv");
                   ObservableList<FoodItem> data =
                                   FXCollections.observableArrayList(foodData.getAllFoodItems());
                   foodListTable.setItems(data);
                }
            });

            addNewFoodFile.setLayoutX(8);
            addNewFoodFile.setLayoutY(480);
            addNewFoodFile.setText("Load New Food File");

            Button saveToFile = new Button();
            saveToFile.setText("Save Food List");
            saveToFile.setLayoutX(160);
            saveToFile.setLayoutY(480);
            
            saveToFile.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                     foodData.saveFoodItems("foodItems.csv");
                }   
              }  
            );
            
            Button addToMealList = new Button();
            addToMealList.setText("Add Selected Item to Meal List");
            addToMealList.setLayoutX(8);
            addToMealList.setLayoutY(510);
            
            addToMealList.setOnAction(new EventHandler<ActionEvent>() {
                
                
                @Override
                public void handle(ActionEvent event) {
                    FoodItem foodItem = (FoodItem) foodListTable.getSelectionModel().getSelectedItem();
                    mealListFood.add(foodItem);
                    ObservableList<FoodItem> mealListData =
                                    FXCollections.observableArrayList(mealListFood);
                    mealListTable.setItems(mealListData);
                    //System.out.println(mealListFood.toString());
                    
                }
              });
            
            final Label label = new Label("Food List");
            label.setFont(new Font("Arial", 20));

            TableColumn foodNameCol = new TableColumn("Food Name");
            foodNameCol.setCellValueFactory(
                            new PropertyValueFactory<>("name"));
            
            foodNameCol.setMinWidth(300);
           
            //TableColumn quantityCol = new TableColumn("Quanity");

            foodListTable.getColumns().addAll(foodNameCol);

            final VBox vbox = new VBox();

            vbox.setSpacing(5);
            vbox.setPadding(new Insets(45, 0, 0, 10));
            vbox.getChildren().addAll(label, foodListTable);
            root2.getChildren().addAll(vbox);
            root2.getChildren().add(addNewFoodFile);
            root2.getChildren().add(saveToFile);
            root2.getChildren().add(addToMealList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        root2.setPrefWidth(300);
        root2.setPrefHeight(400);
        return root2;
    }

	// filters method that generates a place to input filter criteria

	// made by Andrew

	public static Pane filters() {
		// create grid pane

		GridPane grid = new GridPane();

		try {

			grid.setHgap(15);

			grid.setVgap(15);

			grid.setPadding(new Insets(50, 30, 0, 30)); // (top, bottom, right, left)

			Label headerLabel = new Label("Filters");

			headerLabel.setFont(new Font("Arial", 20));

			grid.addRow(0, headerLabel);

			Label containsLabel = new Label("Contains: ");

			grid.add(containsLabel, 0, 1); // child, col, row

			TextField containsIn = new TextField();

			containsIn.setPromptText("Enter an ingredient.. ");

			grid.add(containsIn, 1, 1, 2, 1); // span 3 cols, 1 row

			// create labels for the top of "table (grid)" to specify what should go in

			// each input

			Label minLabel = new Label("Min");
			
			GridPane.setHalignment(minLabel, HPos.CENTER);

			Label maxLabel = new Label("Max");

			GridPane.setHalignment(maxLabel, HPos.LEFT);

			Label equalsLabel = new Label("Exact");

			GridPane.setHalignment(equalsLabel, HPos.LEFT);

			grid.add(minLabel, 1, 3);

			grid.add(maxLabel, 2, 3);

			grid.add(equalsLabel, 3, 3);

			// calorie row

			Label calLabel = new Label("Calories: ");

			TextField minCalIn = new TextField();
			minCalIn.setMaxWidth(50);
			TextField maxCalIn = new TextField();
			maxCalIn.setMaxWidth(50);
			TextField equalsCalIn = new TextField();
			equalsCalIn.setMaxWidth(50);
			grid.addRow(4, calLabel, minCalIn, maxCalIn, equalsCalIn);

			// fat row

			Label fatLabel = new Label("Fat: ");

			TextField minFatIn = new TextField();
			minFatIn.setMaxWidth(50);
			TextField maxFatIn = new TextField();
			maxFatIn.setMaxWidth(50);
			TextField equalsFatIn = new TextField();
			equalsFatIn.setMaxWidth(50);
			grid.addRow(5, fatLabel, minFatIn, maxFatIn, equalsFatIn);

			// carb row

			Label carbLabel = new Label("Carbs: ");

			TextField minCarbIn = new TextField();
			minCarbIn.setMaxWidth(50);
			TextField maxCarbIn = new TextField();
			maxCarbIn.setMaxWidth(50);
			TextField equalsCarbIn = new TextField();
			equalsCarbIn.setMaxWidth(50);
			grid.addRow(6, carbLabel, minCarbIn, maxCarbIn, equalsCarbIn);

			// fiber row

			Label fiberLabel = new Label("Fiber: ");

			TextField minFiberIn = new TextField();
			minFiberIn.setMaxWidth(50);
			TextField maxFiberIn = new TextField();
			maxFiberIn.setMaxWidth(50);
			TextField equalsFiberIn = new TextField();
			equalsFiberIn.setMaxWidth(50);
			grid.addRow(7, fiberLabel, minFiberIn, maxFiberIn, equalsFiberIn);

			// protein row

			Label proteinLabel = new Label("Protein: ");
			TextField minProteinIn = new TextField();
			minProteinIn.setMaxWidth(50);
			TextField maxProteinIn = new TextField();
			maxProteinIn.setMaxWidth(50);
			TextField equalsProteinIn = new TextField();
			equalsProteinIn.setMaxWidth(50);
			grid.addRow(8, proteinLabel, minProteinIn, maxProteinIn, equalsProteinIn);

			// create button to actually execute program with filter input

			Button doFilter = new Button();

			doFilter.setText("Filter");

			GridPane.setHalignment(doFilter, HPos.RIGHT);

			grid.add(doFilter, 3, 9);
			
			doFilter.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			        ArrayList<String> filters = new ArrayList<>();
			        if(minCalIn.getText() != null)
			            filters.add("calories >= " + minCalIn.getText());
			        if(maxCalIn.getText() != null)
			            filters.add("calories <= " + maxCalIn.getText());
			        if(equalsCalIn.getText() != null)
			            filters.add("calories == " + equalsCalIn.getText());
			        
			        if(minFatIn.getText() != null)
			            filters.add("fat >= " + minFatIn.getText());
			        if(maxFatIn.getText() != null)
			            filters.add("fat <= " + maxFatIn.getText());
			        if(equalsFatIn.getText() != null)
			            filters.add("fat == " + equalsFatIn.getText());
			        
			        if(minCarbIn.getText() != null)
			            filters.add("carbs >= " + minCarbIn.getText());
			        if(maxCarbIn.getText() != null)
			            filters.add("carbs <= " + maxCarbIn.getText());
			        if(equalsCarbIn.getText() != null)
			            filters.add("carbs == " + equalsCarbIn.getText());
			        
			        if(minFiberIn.getText() != null)
			            filters.add("fiber >= " + minFiberIn.getText());
			        if(maxFiberIn.getText() != null)
			            filters.add("fiber <= " + maxFiberIn.getText());
			        if(equalsFiberIn.getText() != null)
			            filters.add("fiber == " + equalsFiberIn.getText());
			        
			        if(minProteinIn.getText() != null)
			            filters.add("protein >= " + minProteinIn.getText());
			        if(maxProteinIn.getText() != null)
			            filters.add("protein <= " + maxProteinIn.getText());
			        if(equalsProteinIn.getText() != null)
			            filters.add("protein == " + equalsProteinIn.getText());
			        
			        // still need to implement filter by name, filter by ingredient? and
			        // call filter method on FoodData Instance
			    }
			});

		} catch (Exception e) {

			e.printStackTrace();

		}
		return grid;

	}

	public static Pane mealList() {
        Pane root2 = new Pane();

        try {

            Button analyzeFoodButton = new Button();
            Button removeFood = new Button();

            analyzeFoodButton.setLayoutX(180);
            analyzeFoodButton.setLayoutY(480);
            analyzeFoodButton.setText("Analyze");
            
            removeFood.setLayoutX(100);
            removeFood.setLayoutY(480);
            removeFood.setText("Remove");
            
            removeFood.setOnAction(new EventHandler<ActionEvent>() {
            
            
            @Override
            public void handle(ActionEvent event) {
                FoodItem mealItem = (FoodItem) mealListTable.getSelectionModel().getSelectedItem();
                mealListFood.remove(mealItem);
                ObservableList<FoodItem> mealListData =
                                FXCollections.observableArrayList(mealListFood);
                mealListTable.setItems(mealListData);
                }
            });
            
            final Label label = new Label("Meal List");
            
            label.setFont(new Font("Arial", 20));
            TableColumn foodNameCol = new TableColumn("Food Name");
            foodNameCol.setMinWidth(300);
            foodNameCol.setCellValueFactory(
                            new PropertyValueFactory<>("name"));
            
            mealListTable.getColumns().addAll(foodNameCol);
       
            final VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(45, 0, 0, 10));
            vbox.getChildren().addAll(label, mealListTable);
            root2.getChildren().addAll(vbox);
            root2.getChildren().add(analyzeFoodButton);
            root2.getChildren().add(removeFood);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return root2;
    }
}
