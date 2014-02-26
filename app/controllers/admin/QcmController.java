package controllers.admin;

import play.Play;
import play.mvc.*;
import play.data.*;

public class QcmController extends AdminController {

	static Form<models.Qcm> theForm = Form.form(models.Qcm.class);

	public static Result index() {
		return ok(views.html.admin.Qcm.index.render(models.Qcm.all()));
	}

	public static Result save() {
		play.Logger.info("test log 1 ");
		Form<models.Qcm> filledForm = theForm.bindFromRequest();
		if(filledForm.hasErrors()) {
			return badRequest(views.html.admin.Qcm.create.render(filledForm));
		} else {
			models.Qcm.create(filledForm.get());
			return redirect(controllers.admin.routes.QcmController.index());
		}
	}


	public static Result create() {
		return ok(views.html.admin.Qcm.create.render(theForm));
	}
		
	public static Result deleteQcm(Long id) {
		models.Qcm.find.ref(id).delete();
		return redirect(controllers.admin.routes.QcmController.index());
	}

}
