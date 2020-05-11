package monopoly.states;

import monopoly.util.Categories;
import monopoly.util.Results;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @author Bhargav Choudhury
 * This class represents the BASIC state
 */
public class Basic implements SpendingStateI {

    String itemName;
    Boolean decision;

    @Override
    public void decisionToPurchase(String item, HashMap<String, String> itemsMap) {

        if (itemsMap.containsKey(item)){
            itemName = item;
            if (itemsMap.get(item).equals(Categories.BASIC.getCategory())){
                decision = true;
            }else {
                decision = false;
            }
        }
    }

    @Override
    public void update() {

        if (decision){
            Results.list.add(States.BASIC + "::" + itemName + "--YES");
        }else {
            Results.list.add(States.BASIC + "::" + itemName + "--NO");
        }
    }

    /**
     * Sets current state according to the running average
     *
     * @param  currentRunningAverage  current running average
     */
    @Override
    public SpendingStateI setCurrentState(BigDecimal currentRunningAverage){

        if (currentRunningAverage.doubleValue() >= 10000 && currentRunningAverage.doubleValue() < 50000){

            return new Luxurious();
        } else if (currentRunningAverage.doubleValue() >= 50000){

            return new Extravagant();
        }

        return new Basic();
    }
}