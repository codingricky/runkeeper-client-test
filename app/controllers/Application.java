package controllers;

import com.github.codingricky.runkeeperclient.Authorisation;
import play.*;
import play.mvc.*;

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

        new Authorisation().convertToken(code, clientId, clientSecret, redirectUri);
        return ok(authorized.render());
    }
  
}
