import java.io.Serializable;

public class Input implements Serializable {
    private String target;
    private int start;
    private int finish;

    public Input(String target, int s, int f) {
        this.target = target;
        this.start = s;
        this.finish = f;
    }

    public String getTarget() {
        return target;
    }

    public int getStart() {
        return start;
    }

    public int getFinish() {
        return finish;
    }
}
