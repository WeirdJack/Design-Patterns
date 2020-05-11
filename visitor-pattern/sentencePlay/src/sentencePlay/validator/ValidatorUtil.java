package validator;

import java.io.File;
import java.io.FileNotFoundException;

public class ValidatorUtil {

    private String inputFilepath, acceptableWordsFilepath, topKOutputFileName, spellCheckOutputFileName;
    private int topKSize;


    public ValidatorUtil(String[] args) throws Exception {

        try{
            topKSize = Integer.parseInt(args[2]);
        }catch (NumberFormatException e){
            System.err.println("Incorrect number format! Accepts only Integers.");
        }
        inputFilepath = args[0];
        acceptableWordsFilepath = args[1];
        topKOutputFileName = args[3];
        spellCheckOutputFileName = args[4];

        ValidatorUtil.validate("failed",ValidatorFetcher.fileNameValidator(this),
                ValidatorFetcher.topKSizeValidator(this));
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
                    File inputFile = new File(vu.inputFilepath);
                    File acceptableWordsFile = new File(vu.inputFilepath);
                    File topKOutputFile = new File(vu.topKOutputFileName);
                    File spellCheckOutputFile = new File(vu.spellCheckOutputFileName);
                    if ((inputFile.isDirectory() && !inputFile.isFile() && !inputFile.exists()) ||
                            (acceptableWordsFile.isDirectory() && !acceptableWordsFile.isFile() && !acceptableWordsFile.exists()) ||
                            (topKOutputFile.isDirectory() && !topKOutputFile.isFile() && !topKOutputFile.exists()) ||
                            (spellCheckOutputFile.isDirectory() && !spellCheckOutputFile.isFile() && !spellCheckOutputFile.exists())
                       ){

                        System.err.println("Not a File");
                    }
                    if (vu.inputFilepath == "" || vu.acceptableWordsFilepath == "" || vu.topKOutputFileName == "" || vu.spellCheckOutputFileName == "") {
                        throw new FileNotFoundException("File path cannot be empty!");
                    }
                    if(!vu.inputFilepath.endsWith(".txt") || !vu.acceptableWordsFilepath.endsWith(".txt") || !vu.topKOutputFileName.endsWith(".txt") || !vu.spellCheckOutputFileName.endsWith(".txt")){
                        throw new  Exception("File extension not txt!");
                    }
                }
            };
        }


        public static Validator topKSizeValidator(ValidatorUtil vu) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if (vu.topKSize < 0) {
                        throw new NumberFormatException("Top K size  out of range! Should be between greater than 0");
                    }
                }
            };
        }

    }
}