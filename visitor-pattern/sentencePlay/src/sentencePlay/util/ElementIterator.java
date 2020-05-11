package util;

import elements.ElementI;

import java.util.Iterator;
import java.util.List;

public class ElementIterator implements Iterator {

    int index;
    List<ElementI> elementsList;

    public ElementIterator(List<ElementI> elementsListIn) {

        elementsList = elementsListIn;
    }

    @Override
    public boolean hasNext() {

        if(index < elementsList.size()){
            return true;
        }
        return false;
    }

    @Override
    public Object next() {

        if(this.hasNext()){
            return elementsList.get(index++);
        }
        return null;
    }
}
