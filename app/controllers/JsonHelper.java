package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonHelper {
    public static <T> String toJson(T object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(object);

        Pattern pattern = Pattern.compile("/[a-zA-Z/0-9]+");
        Matcher matcher = pattern.matcher(json);
        return matcher.replaceAll("<a id='$0' href='/client$0'>$0</a>");
    }
}
