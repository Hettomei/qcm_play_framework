package controllers.admin;

import play.mvc.*;
import play.data.*;

public class QuestionsController extends AdminController {

	public static Result index() {
		return ok(views.html.admin.Question.index.render(models.Question.all()));
	}

	public static Result create() {
		Form<models.Question> QuestionForm = Form.form(models.Question.class);
		return ok(views.html.admin.Question.create.render(QuestionForm));
	}

	public static Result edit(Long id) {
	  Form<models.Question> QuestionForm = Form.form(models.Question.class).fill(models.Question.find.byId(id));
		return ok(views.html.admin.Question.edit.render(id, QuestionForm));
	}

	public static Result update(Long id) {
	  Form<models.Question> QuestionForm = Form.form(models.Question.class).bindFromRequest();
		if(QuestionForm.hasErrors()) {
			return badRequest(views.html.admin.Question.edit.render(id, QuestionForm));
		}
		QuestionForm.get().update(id);
		flash("success", "La question <<" + QuestionForm.get().question + ">> a été mise à jour");
		return redirect(controllers.admin.routes.QuestionsController.index());
	}

	public static Result save() {
		Form<models.Question> QuestionForm = Form.form(models.Question.class);
		Form<models.Question> filledForm = QuestionForm.bindFromRequest();
		if(filledForm.hasErrors()) {
			return badRequest(views.html.admin.Question.create.render(filledForm));
		} else {
			filledForm.get().save();
			return redirect(controllers.admin.routes.QuestionsController.index());
		}
	}

	public static Result delete(Long id) {
		models.Question.find.ref(id).delete();
		flash("info", "La question " + id + " a été supprimée.");
		return redirect(controllers.admin.routes.QuestionsController.index());
	}

	public static Result show(Long id) {
	  models.Question question = models.Question.find.byId(id);
		return ok(views.html.admin.Question.show.render(question));
	}

}
