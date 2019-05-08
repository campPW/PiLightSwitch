package pi.pilightswitch;

public class SwitchStateTracker {
    int state; // 0 = off, 1 = on

    public SwitchStateTracker() {
        this(0); // default to off
    }
    public SwitchStateTracker(int state) {
        this.setState(state);
    }
    public void setState(int state) {
        if(state != 0 || state != 1)
            throw new IllegalArgumentException();
        else
            this.state = state;
    }
    public int getState() {
        return this.state;
    }
}
