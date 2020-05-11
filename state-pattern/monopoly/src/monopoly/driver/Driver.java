package monopoly.driver;

import monopoly.states.PurchaseItems;
import monopoly.validator.ValidatorUtil;

/**
 * @author Bhargav Choudhury
 */
public class Driver {

    public static void main(String[] args) throws Exception {

        /*
         * As the build.xml specifies the arguments as argX, in case the
         * argument value is not given java takes the default value specified in
         * build.xml. To avoid that, below condition is used
         * FIXME Refactor commandline validation using the validation design taught in class.
         */
        final int REQUIRED_NUMBER_OF_ARGS = 4;
        try {
            if ((args.length != REQUIRED_NUMBER_OF_ARGS) ||
                    (args[0].equals("${inputFile}")) ||
                    (args[1].equals("${availableItemsFile}")) ||
                    (args[2].equals("${runningAverageWindowSize}")) ||
                    (args[3].equals("${outputFile}"))) {

                System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_ARGS);
                System.exit(0);
            }
        }catch (NumberFormatException nfe){
            nfe.printStackTrace();
        }catch (NullPointerException npe){
            npe.printStackTrace();
        }

        new ValidatorUtil(args);

        PurchaseItems purchaseItems = new PurchaseItems();
        purchaseItems.setGoodsList(args[1]);
        purchaseItems.startPurchase(args[0], args[2]);
        purchaseItems.getResults(args[3]);

    }
}
