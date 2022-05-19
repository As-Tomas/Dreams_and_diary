package bance.eutvikling.dreamsanddiary;

public class Dream {
    private String date;
    private String title;
    private String dreamsNotice;
    private String dayNotice;
    private String[] tags;
    private int sleepQuantity;
    private int moodDream;
    private int clarityDream;

    public Dream(String date) {
        this.date = date;
    }

    public Dream(String date, String title, String dreamsNotice, String dayNotice, String[] tags, int sleepQuantity, int moodDream, int clarityDream) {
        this.date = date;
        this.title = title;
        this.dreamsNotice = dreamsNotice;
        this.dayNotice = dayNotice;
        this.tags = tags;
        this.sleepQuantity = sleepQuantity;
        this.moodDream = moodDream;
        this.clarityDream = clarityDream;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDreamsNotice() {
        return dreamsNotice;
    }

    public void setDreamsNotice(String dreamsNotice) {
        this.dreamsNotice = dreamsNotice;
    }

    public String getDayNotice() {
        return dayNotice;
    }

    public void setDayNotice(String dayNotice) {
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
