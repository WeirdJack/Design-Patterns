package monopoly.validator;

import java.io.FileNotFoundException;

public final class ValidatorUtil {

    private String inputFilepath, itemsFilepath;
    private int runningAverageWindowSize;


    public ValidatorUtil(String[] args) throws Exception {

        try{
            runningAverageWindowSize = Integer.parseInt(args[2]);
        }catch (NumberFormatException e){
            System.err.println("Incorrect number format! Accepts only Integers.");
        }
        inputFilepath = args[0];
        itemsFilepath = args[1];
        ValidatorUtil.validate("failed",ValidatorFetcher.fileNameValidator(this),
                ValidatorFetcher.runningAverageWindowSizeValidator(this));
    }

    public static void validate(String baseErrMsg, Validator... validators) throws Exception {
        for (Validator v : validators) {
            try {
                v.run();
            } catch (Exception e) {
                throw new Exception(baseErrMsg.concat(": " + e.getMessage()), e);
            }
        }

    }

    private static class ValidatorFetcher {
        public static Validator fileNameValidator(ValidatorUtil vu) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if (vu.inputFilepath == "" || vu.itemsFilepath == "") {
                        throw new FileNotFoundException("File path cannot be empty!");
                    }
                    if(!vu.inputFilepath.endsWith(".txt") || !vu.itemsFilepath.endsWith(".txt")){
                        throw new  Exception("File extension not txt!");
                    }
                }
            };
        }


        public static Validator runningAverageWindowSizeValidator(ValidatorUtil vu) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if (vu.runningAverageWindowSize < 0) {
                        throw new NumberFormatException("Window size  out of range! Should be between greater than 0");
                    }
                }
            };
        }

    }
}
