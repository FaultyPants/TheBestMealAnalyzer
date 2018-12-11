package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
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
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;

public class Main extends Application {
    static String fileName = "foodItems.csv";
    static FoodData foodData;
    private static List<FoodItem> mealListFood = new ArrayList<FoodItem>();
    static TableView<FoodItem> mealListTable = new TableView<FoodItem>();
    static TableView foodTable = new TableView();
    static  int loadButtonCounter = 0;
    static ObservableList<FoodItem> data = null;
    
    static ToolBar grid = null;
    static TextField calField = new TextField();
    static TextField fatField = new TextField();
    static TextField carbField = new TextField();
    static TextField fiberField = new TextField();
    static TextField proteinField = new TextField();
    static TextField ingredientField = new TextField();

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
                    FoodItem newFood = new FoodItem( UUID.randomUUID().toString() , foodNameField.getText());
                    
                    if(calField.getText().equals("")|| fatField.getText().equals("") || carbField.getText().equals("") 
                            || fiberField.getText().equals("") || proteinField.getText().equals("")) {
                        
                        return;
                    }
                            
                    
                    newFood.addNutrient("calories", Double.parseDouble(calField.getText()));
                    newFood.addNutrient("fat", Double.parseDouble(fatField.getText()));
                    newFood.addNutrient("carbohydrate", Double.parseDouble(carbField.getText()));
                    newFood.addNutrient("fiber", Double.parseDouble(fiberField.getText()));
                    newFood.addNutrient("protein", Double.parseDouble(proteinField.getText()));

                    foodData.addFoodItem(newFood);
                    
                    ObservableList<FoodItem> data =
                                       FXCollections.observableArrayList(foodData.getAllFoodItems());           
                    
                    data = foodData.sortList(data);
                   
                    foodTable.setItems(data);
                    
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
                
                    if (fileExists(fileName)) {
                       
                   foodData.loadFoodItems(fileName);
                   data =
                                   FXCollections.observableArrayList(foodData.getAllFoodItems());
                   
                    }
                    else {
                        JFileChooser chooser = new JFileChooser();
                        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                "Text Files", "csv", "txt");
                        chooser.setFileFilter(filter);
                        int returnVal = chooser.showOpenDialog(null);
                        if(returnVal == JFileChooser.APPROVE_OPTION) {
                            
                        fileName = chooser.getSelectedFile().getAbsolutePath();
                        
                        foodData.loadFoodItems(chooser.getSelectedFile().getAbsolutePath());
                        data =
                                FXCollections.observableArrayList(foodData.getAllFoodItems());
                        
                    }
                        
                    }
                    foodTable.setItems(data);
                       loadButtonCounter++;
                }

                private boolean fileExists(String fileName) {
                    File checkFile = new File(fileName);
                    if (checkFile.exists()) {
                        return true;
                    }
                    return false;
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
                    FoodItem foodItem = (FoodItem) foodTable.getSelectionModel().getSelectedItem();
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
            foodTable.getColumns().addAll(foodNameCol);

            final VBox vbox = new VBox();

            vbox.setSpacing(5);
            vbox.setPadding(new Insets(45, 0, 0, 10));
            vbox.getChildren().addAll(label, foodTable);
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

            Label nameLabel = new Label("Name: ");

            grid.add(nameLabel, 0, 1); // child, col, row

            TextField nameIn = new TextField();

            nameIn.setPromptText("Enter name (or substring) ");

            grid.add(nameIn, 1, 1, 2, 1); // span 3 cols, 1 row

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

            

            
            Button removeFilter = new Button();
            removeFilter.setText("Remove Filter");
            removeFilter.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent event) {
                    data = FXCollections.observableArrayList(foodData.getAllFoodItems());
                    foodTable.setItems(data);
                }
            });
            
         // create button to actually execute program with filter input
            Button doFilter = new Button();
            doFilter.setText("Filter");

            GridPane.setHalignment(doFilter, HPos.RIGHT);

            grid.add(doFilter, 3, 9);
            grid.add(removeFilter, 2, 9);
            
            doFilter.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    ArrayList<String> filters = new ArrayList<>();
                    if(minCalIn.getText() != null && (!minCalIn.getText().trim().isEmpty()))
                        filters.add("calories >= " + minCalIn.getText());
                    if(maxCalIn.getText() != null && (!maxCalIn.getText().trim().isEmpty()))
                        filters.add("calories <= " + maxCalIn.getText());
                    if(equalsCalIn.getText() != null && (!equalsCalIn.getText().trim().isEmpty()))
                        filters.add("calories == " + equalsCalIn.getText());
                    
                    if(minFatIn.getText() != null && (!minFatIn.getText().trim().isEmpty()))
                        filters.add("fat >= " + minFatIn.getText());
                    if(maxFatIn.getText() != null && (!maxFatIn.getText().trim().isEmpty()))
                        filters.add("fat <= " + maxFatIn.getText());
                    if(equalsFatIn.getText() != null && (!equalsFatIn.getText().trim().isEmpty()))
                        filters.add("fat == " + equalsFatIn.getText());
                    
                    if(minCarbIn.getText() != null && (!minCarbIn.getText().trim().isEmpty()))
                        filters.add("carbs >= " + minCarbIn.getText());
                    if(maxCarbIn.getText() != null && (!maxCarbIn.getText().trim().isEmpty()))
                        filters.add("carbs <= " + maxCarbIn.getText());
                    if(equalsCarbIn.getText() != null && (!equalsCarbIn.getText().trim().isEmpty()))
                        filters.add("carbs == " + equalsCarbIn.getText());
                    
                    if(minFiberIn.getText() != null && (!minFiberIn.getText().trim().isEmpty()))
                        filters.add("fiber >= " + minFiberIn.getText());
                    if(maxFiberIn.getText() != null && (!maxFiberIn.getText().trim().isEmpty()))
                        filters.add("fiber <= " + maxFiberIn.getText());
                    if(equalsFiberIn.getText() != null && (!equalsFiberIn.getText().trim().isEmpty()))
                        filters.add("fiber == " + equalsFiberIn.getText());
                    
                    if(minProteinIn.getText() != null && (!minProteinIn.getText().trim().isEmpty()))
                        filters.add("protein >= " + minProteinIn.getText());
                    if(maxProteinIn.getText() != null && (!maxProteinIn.getText().trim().isEmpty()))
                        filters.add("protein <= " + maxProteinIn.getText());
                    if(equalsProteinIn.getText() != null && (!equalsProteinIn.getText().trim().isEmpty()))
                        filters.add("protein == " + equalsProteinIn.getText());
                    String substring;
                    substring = nameIn.getText();
                    
                    List<FoodItem> nutrientFilteredList = foodData.filterByNutrients(filters);
                    List<FoodItem> nameFilteredList = foodData.filterByName(substring);
                    List<FoodItem> finalFilteredList = new ArrayList<>();
                    //only want to find intersection if name filter wasn't empty otherwise
                    //nameFilter returns emptySet
                    if(substring != null && (!substring.trim().isEmpty())) {
                        for (FoodItem nameItem : nameFilteredList) {
                            if(nutrientFilteredList.contains(nameItem)) {
                                finalFilteredList.add(nameItem);
                            }
                        }
                    } else {
                        finalFilteredList = nutrientFilteredList;
                    }
                    ObservableList<FoodItem> data =
                                    FXCollections.observableArrayList(finalFilteredList);
                    foodTable.setItems(data);
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

            analyzeFoodButton.setLayoutX(245);
            analyzeFoodButton.setLayoutY(480);
            analyzeFoodButton.setText("Analyze");
            
            removeFood.setLayoutX(10);
            removeFood.setLayoutY(480);
            removeFood.setText("Remove Selected Food Item");
            
            analyzeFoodButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                double calories = 0;
                double fat = 0;
                double fiber = 0;
                double carbohydrate = 0;
                double protein = 0;
                String allFood = "";
                for(int i = 0; i < mealListTable.getItems().size(); i++)
                {
                    ObservableList<FoodItem> foodList = mealListTable.getItems();
                    FoodItem currentFoodItem = foodList.get(i);
                    HashMap<String, Double> nutrients = currentFoodItem.getNutrients();
                    calories += nutrients.get("calories");
                    fiber += nutrients.get("fiber");
                    fat += nutrients.get("fat");
                    carbohydrate += nutrients.get("carbohydrate");
                    protein += nutrients.get("protein");
                    allFood += currentFoodItem.getName() + ", ";                    
                }
                
                calField.setText(Double.toString(calories));
                fatField.setText(Double.toString(fat));
                fiberField.setText(Double.toString(fiber));
                proteinField.setText(Double.toString(protein));
                carbField.setText(Double.toString(carbohydrate));
                ingredientField.setText(allFood);
            }           
            });
            
            
            
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
