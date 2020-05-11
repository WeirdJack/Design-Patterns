package elements;

import util.ElementIterator;
import util.FileProcessor;
import visitors.VisitorI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyArrayList implements ElementI {

    public static List<ElementI> elementsList;
    private String[] inputText;
    private FileProcessor fileProcessor;
    public Iterator<ElementI> myElementIterator;

    public MyArrayList(Builder builder) {

        this.inputText = builder.inputText;
        this.fileProcessor = builder.fileProcessor;
        this.myElementIterator = builder.myElementIterator;
    }

    @Override
    public void accept(VisitorI visitor) {
        myElementIterator = new ElementIterator(elementsList);
        while (getIterator().hasNext()){
            getIterator().next().accept(visitor);
        }

    }

    public Iterator<ElementI> getIterator(){

        return myElementIterator;
    }

    public static class Builder{

        private String[] inputText;
        private FileProcessor fileProcessor;
        private Iterator<ElementI> myElementIterator;

        public Builder withFileProcessor(FileProcessor fileProcessorIn) {

            fileProcessor = fileProcessorIn;
            return this;
        }

        public MyArrayList build(){

            try {
                elementsList = new ArrayList<ElementI>();
                inputText = fileProcessor.poll().split("[.]");
                for (String sentence : inputText){
                    elementsList.add(new MyElement(sentence));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return new MyArrayList(this);
        }
    }
}
