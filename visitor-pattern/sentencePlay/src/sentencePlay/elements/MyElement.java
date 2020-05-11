package elements;

import visitors.VisitorI;

public class MyElement implements ElementI {

    public String currentSentence;

    public MyElement(String sentence){

        currentSentence = sentence.trim();
    }

    @Override
    public void accept(VisitorI visitor) {

        visitor.visit(this);
    }


}
