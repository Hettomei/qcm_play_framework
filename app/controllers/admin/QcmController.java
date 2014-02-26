package controllers.admin;

import play.Play;
import play.mvc.*;
import play.data.*;

public class QcmController extends AdminController {

	public static Result index() {
		return ok(views.html.admin.Qcm.index.render(models.Qcm.all()));
	}

	public static Result create() {
		Form<models.Qcm> QcmForm = Form.form(models.Qcm.class);
		return ok(views.html.admin.Qcm.create.render(QcmForm));
	}

	public static Result save() {
		Form<models.Qcm> QcmForm = Form.form(models.Qcm.class);
		Form<models.Qcm> filledForm = QcmForm.bindFromRequest();
		if(filledForm.hasErrors()) {
			return badRequest(views.html.admin.Qcm.create.render(filledForm));
		} else {
			filledForm.get().save();
			return redirect(controllers.admin.routes.QcmController.index());
		}
	}

}
