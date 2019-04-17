package kz.damir211.followers;

import android.graphics.Bitmap;

// Класс погоды
public class followers {
    private String[] logname = new String[200];
    private String[] logurl = new String[200];
    private Bitmap iconData; // Иконка

    //private String city; // Город
    //private Bitmap iconData; // Иконка

    public void setLogname(String name, int index) {
        this.logname[index] = name;
    }
    public void setLogurl(String name, int index) {
        this.logurl[index] = name;
    }
    public void setIconData(Bitmap iconData) {
        this.iconData = iconData;
    }

    public String getLogname(int index) {
        return logname[index];
    }
    public String getLogurl(int index) {
        return logurl[index];
    }
    public Bitmap getIconData() {
        return iconData;
    }
}
