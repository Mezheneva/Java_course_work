public class Event {
    private String dayNum;
    private String monthNum;
    private String yearNum;
    private String time;
    private String event;

    public Event(String dayNum, String monthNum, String yearNum, String time, String event) {
        this.dayNum = dayNum;
        this.monthNum = monthNum;
        this.yearNum = yearNum;
        this.time = time;
        this.event = event;
    }

    public String getDayNum() {
        return dayNum;
    }

    public void setDayNum(String dayNum) {
        this.dayNum = dayNum;
    }

    public String getMonthNum() {
        return monthNum;
    }

    public void setMonthNum(String monthNum) {
        this.monthNum = monthNum;
    }

    public String getYearNum() {
        return yearNum;
    }

    public void setYearNum(String yearNum) {
        this.yearNum = yearNum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
