package controllers.admin;

import play.mvc.*;
import play.data.*;

public class StagiairesController extends AdminController {

	static Form<models.Stagiaire> theForm = Form.form(models.Stagiaire.class);

	public static Result index() {
		return ok(views.html.admin.Stagiaire.index.render(models.Stagiaire.all(), theForm));
	}

	public static Result deleteStagiaire(Long id) {
		models.Stagiaire.delete(id);
		return redirect(controllers.admin.routes.StagiairesController.index());
	}

}
