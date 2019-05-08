package pi.pilightswitch;

public class SwitchStateTracker {
    String state;

    public SwitchStateTracker() {
        this("OFF"); // default to off
    }
    public SwitchStateTracker(String state) {
        this.setState(state);
    }
    public void setState(String state) {
//        if(state != "OFF" || state != "ON")
//            throw new IllegalArgumentException();
//        else
            this.state = state;
    }
    public String getState() {
        return this.state;
    }
}
