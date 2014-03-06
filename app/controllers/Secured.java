package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;

public class Secured extends Security.Authenticator {

	@Override
	public String getUsername(Context ctx) {
		return ctx.session().get("id");
	}

	@Override
	public Result onUnauthorized(Context ctx) {
		return redirect(routes.Application.login());
	}

	//public static boolean isOwnerOf(Long eval_id) {
		//return Evaluation.isOwner(
				//eval_id,
				//Context.current().request().username()
				//);
	//}

}
