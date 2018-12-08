import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
        foodItemList = new ArrayList<FoodItem>();
        indexes = new HashMap<String, BPTree<Double, FoodItem>>();
    }
    
    
    /*
     * (non-Javadoc)
     * @see skeleton.FoodDataADT#loadFoodItems(java.lang.String)
     */
    @Override
    public void loadFoodItems(String filePath) {
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
               foodItemList.add(new FoodItem(id, name));
               FoodItem current = foodItemList.get(foodItemList.size() - 1);
               for(int i = 2; i < foodInfo.length; i+=2)
               {
            	   double value = Double.parseDouble(foodInfo[i+1]);
            	   current.addNutrient(foodInfo[i], value);
               }
            }  
        	bufferedReader.close();
         }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * @see skeleton.FoodDataADT#filterByName(java.lang.String)
     */
    @Override
    public List<FoodItem> filterByName(String substring) {
        
        ArrayList<FoodItem> filteredFoodList = new ArrayList<FoodItem>();
        
        for(int i = 0; i < foodItemList.size(); i++)
        {
        	FoodItem currentFood = foodItemList.get(i);
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
        return null;
    }

    /*
     * (non-Javadoc)
     * @see skeleton.FoodDataADT#addFoodItem(skeleton.FoodItem)
     */
    @Override
    public void addFoodItem(FoodItem foodItem) {
        // TODO : Complete
    }

    /*
     * (non-Javadoc)
     * @see skeleton.FoodDataADT#getAllFoodItems()
     */
    @Override
    public List<FoodItem> getAllFoodItems() {
        // TODO : Complete
        return null;
    }


	@Override
	public void saveFoodItems(String filename) {
		// TODO Auto-generated method stub
		
	}

}
