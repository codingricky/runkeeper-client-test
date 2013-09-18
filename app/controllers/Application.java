package controllers;

import com.github.codingricky.runkeeperclient.Authorisation;
import com.github.codingricky.runkeeperclient.Client;
import com.github.codingricky.runkeeperclient.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import play.Configuration;
import play.Play;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import views.html.index;
import views.html.response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application extends Controller {

    public static Result index() {
        Configuration configuration = Play.application().configuration();
        String clientId = configuration.getString("rk.clientId");
        String redirectUri = configuration.getString("rk.redirectUrl");
        return ok(index.render(clientId, redirectUri));
    }

    public static Result authorized() {
        if (!session().containsKey("code")) {
            String code = request().getQueryString("code");
            Configuration configuration = Play.application().configuration();
            String clientId = configuration.getString("rk.clientId");
            String clientSecret = configuration.getString("rk.clientSecret");
            String redirectUri = configuration.getString("rk.redirectUrl");

            String accessCode = new Authorisation().convertToken(code, clientId, clientSecret, redirectUri);
            session().put("code", accessCode);
        }
        return redirect(routes.Application.user());
    }

    @With(HasAuthorisationAction.class)
    public static Result user() {
        User user = new Client(session().get("code")).getUser();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String userAsJson = gson.toJson(user);

        Pattern pattern = Pattern.compile("/[a-zA-Z]+");
        Matcher matcher = pattern.matcher(userAsJson);
        String s = matcher.replaceAll("<a href='/client$0'>$0</a>");
        return ok(response.render(s));
    }

    public static Result logout() {
        session().clear();
        return redirect(routes.Application.index());
    }
}
