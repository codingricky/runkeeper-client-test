package controllers;

import com.github.codingricky.runkeeperclient.Client;
import com.github.codingricky.runkeeperclient.model.WeightFeed;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import views.html.response;

import static controllers.JsonHelper.toJson;

public class ClientController extends Controller {

    @With(HasAuthorisationAction.class)
    public static Result weight() {
        String code = session().get("code");
        WeightFeed weightFeed = new Client(code).getWeightFeed();
        return ok(response.render(toJson(weightFeed)));
    }
//
//    @With(HasAuthorisationAction.class)
//    public static Result weight(Integer id) {
//        String code = session().get("code");
//        Client client = new Client(code);
//        WeightFeed weightFeed = client.getWeightFeed();
//        client.get
//        return ok(response.render(toJson(weightFeed)));
//    }

}
