package controllers.admin;

import play.mvc.*;
import play.data.*;
import play.db.jpa.Transactional;

import models.Question;
import exceptions.FrozenException;

public class QuestionsController extends AdminController {

	@Transactional
	public static Result index() {
		return ok(views.html.admin.Question.index.render(Question.all()));
	}

	@Transactional
	public static Result create() {
		Form<Question> questionForm = Form.form(Question.class);
		return ok(views.html.admin.Question.create.render(questionForm));
	}

	@Transactional
	public static Result edit(Long id) {
		Form<Question> questionForm = Form.form(Question.class).fill(Question.findById(id));
		return ok(views.html.admin.Question.edit.render(id, questionForm));
	}

	@Transactional
	public static Result update(Long id) {
		Form<Question> questionForm = Form.form(Question.class).bindFromRequest();
		if(questionForm.hasErrors()) {
			return badRequest(views.html.admin.Question.edit.render(id, questionForm));
		}

		Question question = Question.findById(id);
		try{
			question.update(questionForm.get());
			flash("success", "La question <<" + question.id + ">> a été mise à jour");
		}catch(FrozenException e){
			flash("danger", "La question " + id + " ne peut pas être modifiée.");
		}

		return redirect(controllers.admin.routes.QuestionsController.index());
	}

	@Transactional
	public static Result save() {
		Form<Question> questionForm = Form.form(Question.class).bindFromRequest();
		if(questionForm.hasErrors()) {
			return badRequest(views.html.admin.Question.create.render(questionForm));
		} else {
			questionForm.get().save();
			return redirect(controllers.admin.routes.QuestionsController.index());
		}
	}

	@Transactional
	public static Result delete(Long id) {
		Question question = Question.findById(id);

		try{
			question.delete();
			flash("info", "La question " + id + " a été supprimée.");
		}catch(FrozenException e){
			flash("danger", "La question " + id + " ne peut pas être supprimée.");
		}
		return redirect(controllers.admin.routes.QuestionsController.index());
	}

	@Transactional
	public static Result show(Long id) {
		Question question = Question.findById(id);
		return ok(views.html.admin.Question.show.render(question));
	}

}
