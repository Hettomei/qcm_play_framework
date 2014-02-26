package controllers.admin;

import play.mvc.*;
import play.data.*;

public class QcmController extends AdminController {

	static Form<models.Qcm> theForm = Form.form(models.Qcm.class);

	public static Result index() {
		return ok(views.html.admin.Qcm.index.render(models.Qcm.all(), theForm));
	}

	public static Result newQcm() {
		Form<models.Qcm> filledForm = theForm.bindFromRequest();
		if(filledForm.hasErrors()) {
			return badRequest(
					views.html.admin.Qcm.index.render(models.Qcm.all(), filledForm)
					);
		} else {
			models.Qcm.create(filledForm.get());
			return redirect(controllers.admin.routes.QcmController.index());
		}
	}

	public static Result deleteQcm(Long id) {
		models.Qcm.delete(id);
		return redirect(controllers.admin.routes.QcmController.index());
	}

}
