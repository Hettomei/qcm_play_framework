package controllers;

import play.data.Form;
import play.mvc.*;

public class StagiairesController extends Controller {

	static Form<models.Stagiaire> theForm = Form.form(models.Stagiaire.class);
	
	public static Result index() {
		   return ok(views.html.stagiaire.index.render(theForm));
	}
	
	public static Result disconnect() {
		   return ok("Déconnexion ! ahah");
	}
}
