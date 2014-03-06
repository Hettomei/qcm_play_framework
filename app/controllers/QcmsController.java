package controllers;

import play.mvc.*;
import play.mvc.Http.Session;

public class QcmsController extends Controller {

	public static Result index() {
		Session session = Http.Context.current().session();
		String id = session.get("login");
		//models.Stagiaire stagiaire = models.Stagiaire.find.byId(Long.parseLong(id, 10));
		return ok(views.html.qcm.index.render(null));
	}

}
