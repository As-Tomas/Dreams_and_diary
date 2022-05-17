package bance.eutvikling.dreamsanddiary;

public class Dream {
    private String date;
    private String title;
    private String dreamsNotice;
    private String dayNotice;
    private String[] tags;
    private String sleepQuantity;
    private String moodDream;
    private String clarityDream;

    public Dream(String date) {
        this.date = date;
    }

    public Dream(String date, String title, String dreamsNotice, String dayNotice, String[] tags,
                 String sleepQuantity, String moodDream, String clarityDream) {
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

    public String getSleepQuantity() {
        return sleepQuantity;
    }

    public void setSleepQuantity(String sleepQuantity) {
        this.sleepQuantity = sleepQuantity;
    }

    public String getMoodDream() {
        return moodDream;
    }

    public void setMoodDream(String moodDream) {
        this.moodDream = moodDream;
    }

    public String getClarityDream() {
        return clarityDream;
    }

    public void setClarityDream(String clarityDream) {
        this.clarityDream = clarityDream;
    }
}
