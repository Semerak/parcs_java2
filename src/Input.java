import java.io.Serializable;

public class Input implements Serializable {
    private String target;
    private Integer start;
    private Integer finish;

    public Input(String target, Integer start, Integer finish) {
        this.target = target;
        this.start = start;
        this.finish = finish;
    }

    public String getTarget() {
        return this.target;
    }

    public Integer getStart() {
        return this.start;
    }

    public Integer getFinish() {
        return this.finish;
    }
}
