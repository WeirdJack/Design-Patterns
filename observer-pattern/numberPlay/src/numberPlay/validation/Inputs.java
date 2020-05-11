package numberPlay.validation;

import numberPlay.util.ValidatorUtil;

public class Inputs {

    private String filename;
    private String filepath;
    private int windowSize;
    private int k;

    private static class ValidatorFetcher {

        public static Validator filepathValidator(Inputs p) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if (p.filepath == "") {
                        throw new Exception("empty file path");
                    }
                }
            };
        }

        public static Validator filenameValidator(Inputs p) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if (p.filename == "") {
                        throw new Exception("empty file name");
                    }
                }
            };
        }

        public static Validator windowSizeValidator(Inputs p) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if (p.windowSize > 150) {
                        throw new Exception("window size too long");
                    }
                }
            };
        }

        public static Validator topKValidator(Inputs p) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if (p.k > 150) {
                        throw new Exception("top K elements too long");
                    }
                }
            };
        }

        public static Validator extensionValidator(Inputs p) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if (!p.filename.endsWith(".txt")) {
                        throw new Exception("invalid extension");
                    }
                }
            };
        }
    }

    public Inputs(int windowSizeIn, int topKIn) throws Exception {

        windowSize = windowSizeIn;
        k = topKIn;

        // Validating members.
        ValidatorUtil.validate("failed",
                ValidatorFetcher.windowSizeValidator(this),
                ValidatorFetcher.topKValidator(this));

    }

    public Inputs(String name) throws Exception {
        filename = name;
        filepath = name;
        // Validating members.
        ValidatorUtil.validate("failed",
                ValidatorFetcher.filenameValidator(this),
                ValidatorFetcher.filepathValidator(this),
                ValidatorFetcher.extensionValidator(this));

    }
}
