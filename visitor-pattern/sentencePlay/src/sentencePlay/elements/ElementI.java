package elements;

import visitors.VisitorI;

public interface ElementI {

    void accept(VisitorI visitor);
}
