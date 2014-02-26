package controllers.admin;

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

	public static Result edit(Long id) {
	  Form<models.Qcm> QcmForm = Form.form(models.Qcm.class).fill(models.Qcm.find.byId(id));
		return ok(views.html.admin.Qcm.edit.render(id, QcmForm));
	}

	public static Result update(Long id) {
	  Form<models.Qcm> QcmForm = Form.form(models.Qcm.class).bindFromRequest();
		if(QcmForm.hasErrors()) {
			return badRequest(views.html.admin.Qcm.edit.render(id, QcmForm));
		}
		QcmForm.get().update(id);
		flash("success", "QCM " + QcmForm.get().question + " à été mis à jour");
		return redirect(controllers.admin.routes.QcmController.index());
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

	public static Result delete(Long id) {
		models.Qcm.find.ref(id).delete();
		return redirect(controllers.admin.routes.QcmController.index());
	}

}
