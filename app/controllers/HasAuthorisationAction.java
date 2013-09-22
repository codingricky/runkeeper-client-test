package controllers;

import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.SimpleResult;

public class HasAuthorisationAction extends Action.Simple {

    public F.Promise<SimpleResult> call(Http.Context ctx) throws Throwable {
        if (!ctx.session().containsKey("code")) {
            return F.Promise.promise(new F.Function0<SimpleResult>() {
                @Override
                public SimpleResult apply() throws Throwable {
                    return redirect(controllers.routes.Application.index());
                }
            });
        }
        return delegate.call(ctx);
    }
}