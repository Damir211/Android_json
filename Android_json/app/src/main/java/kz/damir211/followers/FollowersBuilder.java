package kz.damir211.followers;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONObject;

import static kz.damir211.followers.HttpClient.getHTMLData;
import static kz.damir211.followers.HttpClient.getHTMLImage;

// СОЗДАТЕЛЬ ПОГОДЫ
public class FollowersBuilder {

    // Получение JSON html-данных погоды по городу и языку
    private static String getFollowersData() {
        String url = "https://api.github.com/users/proffix4/followers";
        return getHTMLData(url);
    }

    // Получение с web bitmap картинки погоды по ее коду
    private static Bitmap getFollowersImage() {
        String url = "http://www.stickpng.com/assets/images/5847f98fcef1014c0b5e48c0.png";
        return getHTMLImage(url);
    }

    // Парсинг даты в формате JSON с созданием объекта погоды
    private static followers dataParsing(String json) {
        followers followers = new followers();
        try {
            JSONArray _obj = new JSONArray(json);
            for(int i = 0;i < _obj.length();i++) {
                JSONObject _person = _obj.getJSONObject(i);
                followers.setLogname(_person.getString("login"),i);
                followers.setLogurl(_person.getString("html_url"),i);
            }
        } catch (Exception ignore) {
        }
        return followers;
    }

    // Получение готового объекта погоды по городу и языку
    public static followers buildFollowers () {
        followers followers = dataParsing(getFollowersData());
        followers.setIconData(getFollowersImage());
        return followers;
    }
}