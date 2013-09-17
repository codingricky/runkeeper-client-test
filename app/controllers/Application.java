package controllers;

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
        return ok(authorized.render());
    }
  
}
