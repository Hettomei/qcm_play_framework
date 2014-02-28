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
		return ok(views.html.admin.Stagiaire.show.render(s, models.Qcm.all()));
	}
	
	public static Result add_qcm(Long id) {
		DynamicForm requestData = Form.form().bindFromRequest();
		long qcm_id = Long.parseLong(requestData.get("qcm_id"),10);
		models.Stagiaire sta = models.Stagiaire.find.byId(id);
		models.Qcm qcm = models.Qcm.find.byId(qcm_id);
		sta.qcms.add(qcm);
		sta.save();

		return redirect(controllers.admin.routes.StagiairesController.show(id));
	}

	public static Result remove_qcm(Long id) {
		DynamicForm requestData = Form.form().bindFromRequest();
		long qcm_id = Long.parseLong(requestData.get("qcm_id"),10);
		models.Stagiaire sta = models.Stagiaire.find.byId(id);
		models.Qcm qcm = models.Qcm.find.byId(qcm_id);
		
		sta.qcms.remove(qcm);
		sta.save();

		return redirect(controllers.admin.routes.StagiairesController.show(id));
	}
}
