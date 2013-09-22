package controllers;

import com.github.codingricky.runkeeperclient.Client;
import com.github.codingricky.runkeeperclient.model.FitnessActivityFeed;
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
        return ok(response.render("Weight", toJson(weightFeed)));
    }

    @With(HasAuthorisationAction.class)
    public static Result fitnessActivities() {
        String code = session().get("code");
        FitnessActivityFeed fitnessActivities = new Client(code).getFitnessActivities();
        return ok(response.render("Fitness Activities", toJson(fitnessActivities)));
    }
}
