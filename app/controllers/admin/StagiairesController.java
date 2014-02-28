package controllers.admin;

import models.Stagiaire;
import play.mvc.*;
import play.data.*;

public class StagiairesController extends AdminController {


	public static Result index() {
		return ok(views.html.admin.Stagiaire.index.render(models.Stagiaire.all()));
	}

	public static Result delete(Long id) {
		models.Stagiaire.find.ref(id).delete();
		return redirect(controllers.admin.routes.StagiairesController.index());
	}

	public static Result show(Long id) {
		Stagiaire s = models.Stagiaire.find.ref(id);
		return ok(views.html.admin.Stagiaire.show.render(s));
	}
}
