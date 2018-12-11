import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;

/**
 * This class represents the backend for managing all 
 * the operations associated with FoodItems
 * 
 * @author sapan (sapan@cs.wisc.edu)
 */
public class FoodData implements FoodDataADT<FoodItem> {
    
    // List of all the food items.
    private List<FoodItem> foodItemList;

    // Map of nutrients and their corresponding index
    private HashMap<String, BPTree<Double, FoodItem>> indexes;
    
    
    /**
     * Public constructor
     */
    public FoodData() {
        setFoodItemList(new ArrayList<FoodItem>());
        indexes = new HashMap<String, BPTree<Double, FoodItem>>();
    }
    
    
    /*
     * (non-Javadoc)
     * @see skeleton.FoodDataADT#loadFoodItems(java.lang.String)
     */
    @Override
    public void loadFoodItems(String filePath) {
	    
	    if(foodItemList.size() > 0) {
            foodItemList.clear();
        }
	    
    	String line;
        try {
        	FileReader reader = new FileReader(filePath);
        	BufferedReader bufferedReader = new BufferedReader(reader);
        	
        	while((line = bufferedReader.readLine()) != null) 
        	{
               String[] foodInfo = line.split(",");
               if (foodInfo.length != 12)
               {
            	   break;
               }
               String id = foodInfo[0];
               String name = foodInfo[1];
               getFoodItemList().add(new FoodItem(id, name));
               FoodItem current = getFoodItemList().get(getFoodItemList().size() - 1);
               for(int i = 2; i < foodInfo.length; i+=2)
               {
            	   double value = Double.parseDouble(foodInfo[i+1]);
            	   current.addNutrient(foodInfo[i], value);
               }
            }  
        	Collections.sort(getFoodItemList(), new customComparator());
        	bufferedReader.close();
         }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    }
    
    private class customComparator implements Comparator<FoodItem>
    {
    	public int compare(FoodItem o1, FoodItem o2) {
            String nameOne = o1.getName().toLowerCase();
    		String nameTwo = o2.getName().toLowerCase();
            return nameOne.compareTo(nameTwo);
        }
    }

    /*
     * (non-Javadoc)
     * @see skeleton.FoodDataADT#filterByName(java.lang.String)
     */
    @Override
    public List<FoodItem> filterByName(String substring) {
        
        ArrayList<FoodItem> filteredFoodList = new ArrayList<FoodItem>();
        
        for(int i = 0; i < getFoodItemList().size(); i++)
        {
        	FoodItem currentFood = getFoodItemList().get(i);
        	if (currentFood.getName().toLowerCase().contains(substring))
        	{
        		System.out.println(currentFood.getName());
        		filteredFoodList.add(currentFood);
        	}
        }
        return filteredFoodList;
    }

/*
     * (non-Javadoc)
     * @see skeleton.FoodDataADT#filterByNutrients(java.util.List)
     */
    @Override
    public List<FoodItem> filterByNutrients(List<String> rules) {
        // TODO : Complete
        ArrayList<FoodItem> filteredList = new ArrayList<FoodItem>();
        HashSet<String> filteredBy = new HashSet<String>();
        
        for(int i = 0; i < rules.size(); i++) {
            String ruleLine = rules.get(i);
            
            //  should return array size 3 - [<nutrient>, <comparator>, <value>]
            String[] rule = ruleLine.split(" ");
            //  check for not null?
            String nutrient = rule[0].trim();
            String comparator = rule[1].trim();
            String value = rule[2].trim();
            
            //  nutrient has already been filtered and just need to further filter 
            //  existing filtered list
            if(filteredBy.contains(nutrient)) {
                for( int j = 0; j < filteredList.size(); j++) {
                    FoodItem currItem = filteredList.get(j);
                    // probably shouldn't ever have two filter criteria with one being ==
                    if(comparator.equals("==")) {
                        if(currItem.getNutrientValue(nutrient) != Double.valueOf(value)) {
                            filteredList.remove(currItem);
                        }
                    } else if(comparator.equals("<=")) {
                        if(currItem.getNutrientValue(nutrient) > Double.valueOf(value)) {
                            filteredList.remove(currItem);
                        }
                    } else if(comparator.equals(">=")) {
                        if(currItem.getNutrientValue(nutrient) < Double.valueOf(value)) {
                            filteredList.remove(currItem);
                        }
                    }
                }
            } else {  //  if haven't filtered for nutrient yet look through entire list
                filteredBy.add(nutrient);
                for(int j = 0; j < getFoodItemList().size(); j++) {
                    
                    FoodItem currItem = getFoodItemList().get(j);
                    if(comparator.equals("==")) {
                        if(currItem.getNutrientValue(nutrient) == Double.valueOf(value)) {
                            filteredList.add(currItem);
                        }
                    } else if(comparator.equals("<=")) {
                        if(currItem.getNutrientValue(nutrient) <= Double.valueOf(value)) {
                            filteredList.add(currItem);
                        }
                    } else if(comparator.equals(">=")) {
                        if(currItem.getNutrientValue(nutrient) >= Double.valueOf(value)) {
                            filteredList.add(currItem);
                        }
                    }
                }
            }
        }
        
        return filteredList;
    }

    /*
     * (non-Javadoc)
     * @see skeleton.FoodDataADT#addFoodItem(skeleton.FoodItem)
     */
    @Override
    public void addFoodItem(FoodItem foodItem) {
        getFoodItemList().add(foodItem);
	Collections.sort(getFoodItemList(), new customComparator());
    }

    /*
     * (non-Javadoc)
     * @see skeleton.FoodDataADT#getAllFoodItems()
     */
    @Override
    public List<FoodItem> getAllFoodItems() {
        return getFoodItemList();
    }



	@Override
	public void saveFoodItems(String filename) {
		try {
			
			PrintWriter writer = new PrintWriter(filename);	
			for(int i = 0; i < getFoodItemList().size(); i++)
			{
				String foodInfo = "";
				FoodItem currentFood = getFoodItemList().get(i);
				HashMap<String, Double> nutrients = currentFood.getNutrients();
				
				//calories,100,fat,0,carbohydrate,0,fiber,0,protein,3
				foodInfo += currentFood.getID() + ",";
				foodInfo += currentFood.getName() +",";
				foodInfo += "calories," + nutrients.get("calories")+",";
				foodInfo += "fat," + nutrients.get("fat")+",";
				foodInfo += "carbohydrate," + nutrients.get("carbohydrate")+",";
				foodInfo += "fiber," + nutrients.get("fiber")+",";
				foodInfo += "protein," + nutrients.get("protein") + "\n";

				writer.write(foodInfo);
				
			}
			writer.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

	public ObservableList<FoodItem> sortList(ObservableList<FoodItem> foodList){
		
		 Collections.sort(foodList, new customComparator());
		
		return foodList;
	}
	public List<FoodItem> getFoodItemList() {
		return foodItemList;
	}


	public void setFoodItemList(List<FoodItem> foodItemList) {
		this.foodItemList = foodItemList;
	}

}
