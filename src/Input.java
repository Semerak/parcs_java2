import java.io.Serializable;

public class Input implements Serializable {
    private  String target;
    private  String  start;
    private  String  finish;

    public Input(String target, String start, String finish) {
        this.target = target;
        this.start = start;
        this.finish = finish;
    }

    public String getTarget() {
        return this.target;
    }

    public String getStart() {
        return this.start;
    }

    public String getFinish() {
        return this.finish;
    }
}
