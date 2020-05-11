package numberPlay.subject;

import numberPlay.filter.FilterI;
import numberPlay.observer.ObserverI;

import java.io.IOException;
import java.util.List;

public interface SubjectI {

    void registerObserver(ObserverI observer, List<FilterI> filterI);

    void unregisterObserver(ObserverI observer, List<FilterI> filterI);

    void notifyObservers(Object numberIn, Enum enumType) throws IOException;
}