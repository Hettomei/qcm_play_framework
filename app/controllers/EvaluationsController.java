package controllers;

import play.mvc.*;
import models.Evaluation;
import play.db.jpa.Transactional;

public class EvaluationsController extends Controller {

	@Transactional
	public static Result show(Long id) {
		Evaluation eval = Evaluation.findById(id);
		if(eval.status == Evaluation.State.NEW){
			eval.generateQuestion();
		}
		return ok(views.html.evaluation.show.render(eval));
	}

}
