package controllers.admin;

import play.Play;
import play.mvc.*;
import play.data.*;
import java.util.*;

public class QcmController extends AdminController {

	public static Result index() {
		return ok(views.html.admin.Qcm.index.render(models.Qcm.all()));
	}

	public static Result create() {
		Form<models.Qcm> qcmForm = Form.form(models.Qcm.class);
		return ok(views.html.admin.Qcm.create.render(qcmForm));
	}

	public static Result save() {
		Form<models.Qcm> qcmForm = Form.form(models.Qcm.class);
		Form<models.Qcm> filledForm = qcmForm.bindFromRequest();
		if(filledForm.hasErrors()) {
			return badRequest(views.html.admin.Qcm.create.render(filledForm));
		} else {
			filledForm.get().save();
			return redirect(controllers.admin.routes.QcmController.index());
		}
	}

	public static Result edit(Long id) {
	  Form<models.Qcm> qcmForm = Form.form(models.Qcm.class).fill(models.Qcm.find.byId(id));
		return ok(views.html.admin.Qcm.edit.render(id, qcmForm));
	}

	public static Result delete(Long id) {
		models.Qcm.find.ref(id).delete();
		return redirect(controllers.admin.routes.QcmController.index());
	}

	public static Result update(Long id) {
	  Form<models.Qcm> qcmForm = Form.form(models.Qcm.class).bindFromRequest();
		if(qcmForm.hasErrors()) {
			return badRequest(views.html.admin.Qcm.edit.render(id, qcmForm));
		}
		qcmForm.get().update(id);
		flash("success", "QCM " + qcmForm.get().name + " à été mis à jour");
		return redirect(controllers.admin.routes.QcmController.show(id));
	}

	public static Result add_question(Long id) {
		DynamicForm requestData = Form.form().bindFromRequest();
		long question_id = Long.parseLong(requestData.get("question_id"),10);
	  models.Qcm qcm = models.Qcm.find.byId(id);
	  models.Question question = models.Question.find.byId(question_id);

		qcm.questions.add(question);
		qcm.save();

		return redirect(controllers.admin.routes.QcmController.show(id));
	}

	public static Result remove_question(Long id) {
		DynamicForm requestData = Form.form().bindFromRequest();
		long question_id = Long.parseLong(requestData.get("question_id"),10);
	  models.Qcm qcm = models.Qcm.find.byId(id);
	  models.Question question = models.Question.find.byId(question_id);

		qcm.questions.remove(question);
		qcm.save();

		return redirect(controllers.admin.routes.QcmController.show(id));
	}

	public static Result show(Long id) {
	  models.Qcm qcm = models.Qcm.find.byId(id);
		return ok(
				views.html.admin.Qcm.show.render(
					qcm,
					allQuestionNotInQcm(qcm)
					)
				);
	}

	public static List allQuestionNotInQcm(models.Qcm qcm){
		return models.Question.find.where("id NOT IN (" + qcm.allQuestionIds() + ")").findList();
	}
}
