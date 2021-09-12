package com.example.safeway.model;

public class Translation {

    boolean english = false;

    /*
    /////////////////////////////////////////////////////////////
        Singleton Support (Thread Safe)
     */
    private static volatile Translation instance;
    private static Object mutex = new Object();

    private Translation() {
    }

    public void init() {
        this.english = false;
    }

    public static Translation getInstance() {
        Translation result = instance;
        if (instance == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new Translation();
            }
        }
        return instance;
    }
    /////////////////////////////////////////////////////////////

    public String getLanguage() {
        if (this.english)
            return "EN";
        return "RO";
    }

    public void setLanguage() {
        this.english = !this.english;
    }
}
