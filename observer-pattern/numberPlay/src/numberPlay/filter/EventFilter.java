package numberPlay.filter;

import numberPlay.driver.Driver;

import java.util.ArrayList;
import java.util.List;

public class EventFilter implements FilterI{

    public Enum EVENT;
    static List<Enum> enumsList = new ArrayList<>();

    public EventFilter(Driver.Events eventIn) {

        EVENT = eventIn;
        enumsList.add(eventIn);
    }


    @Override
    public Enum getEvent(){

        return EVENT;
    }

    @Override
    public boolean check(Enum filterType) {//check for valid filter

        if (enumsList.contains(filterType)){
            //EVENT = filterType;
            return true;
        }
        else{
            return false;
        }
    }
}
