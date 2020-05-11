package numberPlay.subject;

import numberPlay.driver.Driver;
import numberPlay.filter.EventFilter;
import numberPlay.filter.FilterI;
import numberPlay.observer.ObserverI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * registers and notifies the observes for the corresponding events
 *
 * @exception NullPointerException for null number
 */
public class EventTracker implements SubjectI {

    Map<FilterI, List<ObserverI>> observers;

    public EventTracker() {
        observers = new HashMap<FilterI, List<ObserverI>>();
    }

    @Override
    public void registerObserver(ObserverI observer, List<FilterI> list) {

        for (FilterI filter : list){

            if (!observers.containsKey(filter)) {
                observers.put(filter, new ArrayList<ObserverI>());
            }
            observers.get(filter).add(observer);
        }
    }

    @Override
    public void unregisterObserver(ObserverI observer, List<FilterI> list) {

        for (FilterI filter : list){

            observers.get(filter).remove(observer);
        }
    }

    @Override
    public void notifyObservers(Object number, Enum enumF) throws IOException {

        try {
            if (!enumF.equals(Driver.Events.PROCESSING_COMPLETE)){
                for (Map.Entry<FilterI, List<ObserverI>> entry : observers.entrySet()) {
                    if (entry.getKey().check(enumF) && entry.getKey().getEvent().equals(enumF)) {
                        for (ObserverI o : entry.getValue()) {
                            o.update(number);
                        }
                    }
                }
            } else {
                for (Map.Entry<FilterI, List<ObserverI>> entry : observers.entrySet()) {
                    if (entry.getKey().check(enumF) && entry.getKey().getEvent().equals(enumF)) {
                        for (ObserverI o : entry.getValue()) {
                            o.update(null);
                        }
                    }
                }
            }


        } catch (NullPointerException npe){

            npe.printStackTrace();
        }
    }
}
