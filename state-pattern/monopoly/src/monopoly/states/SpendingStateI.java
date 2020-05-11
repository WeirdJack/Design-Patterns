package monopoly.states;

import java.math.BigDecimal;
import java.util.HashMap;

public interface SpendingStateI {

    void decisionToPurchase(String itemName, HashMap<String, String> itemsMap);

    void update();

    SpendingStateI setCurrentState(BigDecimal currentRunningAverage);
}
