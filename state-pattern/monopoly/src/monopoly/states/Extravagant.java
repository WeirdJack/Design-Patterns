package monopoly.states;

import monopoly.util.Results;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @author Bhargav Choudhury
 * This class represents the EXTRAVAGANT state
 */
public class Extravagant implements SpendingStateI {

    String itemName;

    @Override
    public void decisionToPurchase(String item, HashMap<String, String> itemsMap) {

        if (itemsMap.containsKey(item)){
            itemName = item;
        }
    }

    @Override
    public void update() {

        Results.list.add(States.EXTRAVAGANT + "::" + itemName + "--YES");
    }

    @Override
    public SpendingStateI setCurrentState(BigDecimal currentRunningAverage) {

        if(currentRunningAverage.doubleValue() < 10000){

            return new Basic();
        } else if (currentRunningAverage.doubleValue() >= 10000 && currentRunningAverage.doubleValue() < 50000){

            return new Luxurious();
        }

        return new Extravagant();
    }
}
