package controllers;

import com.github.codingricky.runkeeperclient.Authorisation;
import com.github.codingricky.runkeeperclient.Client;
import com.github.codingricky.runkeeperclient.model.User;
import play.Configuration;
import play.Play;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.authorized;
import views.html.index;

public class Application extends Controller {

    public static Result index() {
        Configuration configuration = Play.application().configuration();
        String clientId = configuration.getString("rk.clientId");
        String redirectUri = configuration.getString("rk.redirectUrl");
        return ok(index.render(clientId, redirectUri));
    }

    public static Result authorized() {
        String code = request().getQueryString("code");
        Configuration configuration = Play.application().configuration();
        String clientId = configuration.getString("rk.clientId");
        String clientSecret = configuration.getString("rk.clientSecret");
        String redirectUri = configuration.getString("rk.redirectUrl");

        String accessCode = new Authorisation().convertToken(code, clientId, clientSecret, redirectUri);
        session().put("code", accessCode);
        User user = new Client(accessCode).getUser();
        return ok(authorized.render(user));
    }

}
