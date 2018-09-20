package com.codex.tourmate.event_class;

public class Moments {
    private String momentImage;
    private String momentCaption;
    private String momentKey;
    private String momentdateTime;
    private String eventKey;

    public Moments() {
    }

    public Moments(String momentImage, String momentCaption, String momentKey, String momentdateTime, String eventKey) {
        this.momentImage = momentImage;
        this.momentCaption = momentCaption;
        this.momentKey = momentKey;
        this.momentdateTime = momentdateTime;
        this.eventKey = eventKey;
    }

    public String getMomentImage() {
        return momentImage;
    }

    public String getMomentCaption() {
        return momentCaption;
    }

    public String getMomentKey() {
        return momentKey;
    }

    public String getMomentdateTime() {
        return momentdateTime;
    }

    public String getEventKey() {
        return eventKey;
    }
}
