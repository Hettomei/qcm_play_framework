package controllers.admin;

import play.mvc.*;
import play.data.*;

public class QuestionController extends AdminController {

	static Form<models.Question> theForm = Form.form(models.Question.class);

	public static Result index() {
		return ok(views.html.admin.Question.index.render(models.Question.all(), theForm));
	}

	public static Result newQuestion() {
		Form<models.Question> filledForm = theForm.bindFromRequest();
		if(filledForm.hasErrors()) {
			return badRequest(views.html.admin.Question.index.render(models.Question.all(), filledForm));
		} else {
			models.Question.create(filledForm.get());
			return redirect(controllers.admin.routes.QuestionController.index());
		}
	}

	public static Result deleteQuestion(Long id) {
		models.Question.delete(id);
		return redirect(controllers.admin.routes.QuestionController.index());
	}

}
