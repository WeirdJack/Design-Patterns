package monopoly.states;

public enum States {

    BASIC ("BASIC"),
    LUXURIOUS("LUXURIOUS"),
    EXTRAVAGANT("EXTRAVAGANT");

    private String state;

    States(String stateIn)
    {
        state = stateIn;
    }
}
