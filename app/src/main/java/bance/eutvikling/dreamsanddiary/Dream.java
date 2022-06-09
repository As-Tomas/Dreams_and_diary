package bance.eutvikling.dreamsanddiary;

public class Dream {
    private CharSequence date;
    private CharSequence time;
    private CharSequence title;
    private CharSequence dreamsNotice;
    private CharSequence dayNotice;
    private String[] tags;
    private int sleepQuantity;
    private int moodDream;
    private int clarityDream;

    public Dream(){};

    public Dream(CharSequence date) {
        this.date = date;
    }

    public Dream(CharSequence date, CharSequence time, CharSequence title, CharSequence dreamsNotice, CharSequence dayNotice, String[] tags, int sleepQuantity, int moodDream, int clarityDream) {
        this.date = date;
        this.time = time;
        this.title = title;
        this.dreamsNotice = dreamsNotice;
        this.dayNotice = dayNotice;
        this.tags = tags;
        this.sleepQuantity = sleepQuantity;
        this.moodDream = moodDream;
        this.clarityDream = clarityDream;
    }

    public CharSequence getDate() {
        return date;
    }
    public CharSequence getDateAndTime() {
        String d = date.toString();
        String t = time.toString();
        String n = d + t;
        return n;
    }

    public void setDate(CharSequence date) {
        this.date = date;
    }

    public CharSequence getTime() {
        return time;
    }

    public void setTime(CharSequence time) {
        this.time = time;
    }

    public CharSequence getTitle() {
        return title;
    }

    public void setTitle(CharSequence title) {
        this.title = title;
    }

    public CharSequence getDreamsNotice() {
        return dreamsNotice;
    }

    public void setDreamsNotice(CharSequence dreamsNotice) {
        this.dreamsNotice = dreamsNotice;
    }

    public CharSequence getDayNotice() {
        return dayNotice;
    }

    public void setDayNotice(CharSequence dayNotice) {
        this.dayNotice = dayNotice;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public int getSleepQuantity() {
        return sleepQuantity;
    }

    public void setSleepQuantity(int sleepQuantity) {
        this.sleepQuantity = sleepQuantity;
    }

    public int getMoodDream() {
        return moodDream;
    }

    public void setMoodDream(int moodDream) {
        this.moodDream = moodDream;
    }

    public int getClarityDream() {
        return clarityDream;
    }

    public void setClarityDream(int clarityDream) {
        this.clarityDream = clarityDream;
    }
}
