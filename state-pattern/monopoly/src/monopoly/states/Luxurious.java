package monopoly.states;

import monopoly.util.Categories;
import monopoly.util.Results;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @author Bhargav Choudhury
 * This class represents the LUXURIOUS state
 */
public class Luxurious implements SpendingStateI {

    String itemName;
    Boolean decision;

    @Override
    public void decisionToPurchase(String item, HashMap<String, String> itemsMap) {

        if (itemsMap.containsKey(item)){
            itemName = item;
            if (!itemsMap.get(item).equals(Categories.SUPER_EXPENSIVE.getCategory())){
                decision = true;
            }else {
                decision = false;
            }
        }
    }

    @Override
    public void update() {

        if (decision){
            Results.list.add(States.LUXURIOUS + "::" + itemName + "--YES");
        }else {
            Results.list.add(States.LUXURIOUS + "::" + itemName + "--NO");
        }
    }

    @Override
    public SpendingStateI setCurrentState(BigDecimal currentRunningAverage) {

        if(currentRunningAverage.doubleValue() < 10000){

            return new Basic();
        } else if (currentRunningAverage.doubleValue() >= 50000){

            return new Extravagant();
        }

        return new Luxurious();
    }
}