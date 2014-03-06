package controllers.admin;
import play.libs.*;

import java.util.*;

import play.Play;
import play.mvc.*;
import play.data.*;
import play.db.jpa.Transactional;
import play.db.jpa.JPA;

import models.Qcm;
import models.Question;
import exceptions.FrozenException;
public class QcmController extends AdminController {

	@Transactional
	public static Result index() {
		return ok(views.html.admin.Qcm.index.render(Qcm.all()));
	}

	@Transactional
	public static Result create() {
		Form<Qcm> qcmForm = Form.form(Qcm.class);
		return ok(views.html.admin.Qcm.create.render(qcmForm));
	}

	@Transactional
	public static Result save() {
		Form<Qcm> qcmForm = Form.form(Qcm.class).bindFromRequest();
		if(qcmForm.hasErrors()) {
			return badRequest(views.html.admin.Qcm.create.render(qcmForm));
		} else {
			qcmForm.get().save();
			return redirect(controllers.admin.routes.QcmController.index());
		}
	}

	@Transactional
	public static Result edit(Long id) {
		Form<Qcm> qcmForm = Form.form(Qcm.class).fill(Qcm.findById(id));
		return ok(views.html.admin.Qcm.edit.render(id, qcmForm));
	}

	@Transactional
	public static Result delete(Long id) {
		Qcm qcm = Qcm.findById(id);
		try{
			qcm.delete();
		}catch(FrozenException e){
			flash("warning", "QCM " + qcm.id + " ne peux pas etre modifié car il est dans une evaluation");
			return redirect(controllers.admin.routes.QcmController.show(id));
		}
		return redirect(controllers.admin.routes.QcmController.index());
	}

	@Transactional
	public static Result update(Long id) {
		Form<Qcm> qcmForm = Form.form(Qcm.class).bindFromRequest();
		Qcm qcm = Qcm.findById(id);
		if(qcmForm.hasErrors()) {
			return badRequest(views.html.admin.Qcm.edit.render(id, qcmForm));
		}

		try{
			qcm.update(qcmForm.get());
			flash("success", "QCM " + qcmForm.get().name + " à été mis à jour");
		}catch(FrozenException e){
			flash("warning", "QCM " + qcm.id + " ne peux pas etre modifié car il est dans une evaluation");
		}
		return redirect(controllers.admin.routes.QcmController.show(id));
	}

	@Transactional
	public static Result addQuestion(Long id) {
		DynamicForm requestData = Form.form().bindFromRequest();
		long question_id = Long.parseLong(requestData.get("question_id"),10);
		Qcm qcm = Qcm.findById(id);
		Question question = Question.findById(question_id);

		if(!qcm.questions.contains(question)){
			qcm.questions.add(question);
			qcm.save();
		}

		return redirect(controllers.admin.routes.QcmController.editQuestions(id));
	}

	@Transactional
	public static Result removeQuestion(Long id) {
		DynamicForm requestData = Form.form().bindFromRequest();
		long question_id = Long.parseLong(requestData.get("question_id"),10);
		Qcm qcm = Qcm.findById(id);
		Question question = Question.findById(question_id);

		qcm.questions.remove(question);
		qcm.save();

		return redirect(controllers.admin.routes.QcmController.editQuestions(id));
	}

	@Transactional
	public static Result show(Long id) {
		Qcm qcm = Qcm.findById(id);
		return ok(views.html.admin.Qcm.show.render(qcm));
	}

	@Transactional
	public static Result editQuestions(Long id){
		Qcm qcm = Qcm.findById(id);
		return ok(
				views.html.admin.Qcm.editQuestions.render(
					qcm,
					//Question.allNotInList(qcm.allQuestionIds())
					Question.all()
					)
				);
	}

}
