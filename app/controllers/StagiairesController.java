package controllers;

import javax.servlet.http.HttpSession;
import models.Stagiaire;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import play.mvc.Http.Context;
import play.mvc.Http.Response;
import play.mvc.Http.Session;

public class StagiairesController extends Controller {

	static Form<models.Stagiaire> theForm = Form.form(models.Stagiaire.class);

	public static Result show() {
		Session session = Http.Context.current().session();
		String id = session.get("login");

		if (id != null) {
			Stagiaire s = models.Stagiaire.findById(Long.parseLong(id));
			if (s == null){
				return ok(views.html.stagiaire.sessiontest.render());
			}else{
				return ok(views.html.stagiaire.show.render(s));
			}
		}else{
			return ok(views.html.stagiaire.sessiontest.render());
		}
	}

	public static Result sessiontest() {
		Session session = Http.Context.current().session();
		DynamicForm requestData = Form.form().bindFromRequest();
		String id = requestData.get("login");
		session.put("login", id );
		return redirect(controllers.routes.StagiairesController.show());
	}

	public static Result disconnect() {
		Session session = Http.Context.current().session();
		session.remove("login");
		return redirect(controllers.routes.StagiairesController.show());
	}
}
