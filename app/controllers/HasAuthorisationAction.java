package controllers;

import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

public class HasAuthorisationAction extends Action.Simple {

    public Result call(Http.Context ctx) throws Throwable {
        if (!ctx.session().containsKey("code")) {
            return redirect(routes.Application.index());
        }
        return delegate.call(ctx);
    }
}