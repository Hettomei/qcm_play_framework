package controllers.admin;

import play.mvc.*;

public class AdminController extends Controller {
	public static Result index() {
		return ok(views.html.admin.Admin.index.render());
	}
}
