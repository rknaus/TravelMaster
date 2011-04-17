package ch.netgeek.travelmaster.route;

public class Line {
    
    private int number;
    private String type;

    public Line(int number, String type) {
        setNumber(number);
        setType(type);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
