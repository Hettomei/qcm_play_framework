package controllers.admin;

import java.util.*;

import play.mvc.*;
import play.data.*;
import play.db.jpa.Transactional;

import models.Stagiaire;
import models.Qcm;
import models.Evaluation;
import javax.persistence.NoResultException;

public class StagiairesController extends AdminController {


	@Transactional
	public static Result index() {
		return ok(views.html.admin.Stagiaire.index.render(Stagiaire.all()));
	}

	@Transactional
	public static Result show(Long id) {
		Stagiaire stagiaire = Stagiaire.findById(id);
		return ok(
				views.html.admin.Stagiaire.show.render(
					stagiaire,
					Qcm.all()
					)
				);
	}

	@Transactional
	public static Result add_evaluation(Long id) {
		DynamicForm requestData = Form.form().bindFromRequest();
		long qcm_id = Long.parseLong(requestData.get("qcm_id"),10);
		Stagiaire stagiaire = Stagiaire.findById(id);
		Qcm qcm = Qcm.findById(qcm_id);
		qcm.frozen = true;
		qcm.save();

		try{
			Evaluation eval = Evaluation.findByStagiaireAndQcm(stagiaire, qcm);
			flash("danger", "Evaluation déjà dans la liste, id : " + eval.id);
		} catch(NoResultException nre){
			Evaluation eval = new Evaluation(stagiaire, qcm);
			eval.save();
			flash("success", "Evaluation ajoutée pour le stagiaire " + id + " et le qcm " + qcm_id);
		}

		return redirect(controllers.admin.routes.StagiairesController.show(id));
	}

	@Transactional
	public static Result remove_qcm(Long id) {
		DynamicForm requestData = Form.form().bindFromRequest();
		long qcm_id = Long.parseLong(requestData.get("qcm_id"),10);
		Stagiaire stagiaire = Stagiaire.findById(id);
		Qcm qcm = Qcm.findById(qcm_id);

		try{
			Evaluation eval = Evaluation.findByStagiaireAndQcm(stagiaire, qcm);
			eval.delete();
			flash("success", "Evaluation supprimée pour le stagiaire " + id + " et le qcm " + qcm_id);
		} catch(NoResultException nre){
			flash("danger", "Aucune evaluation trouvé pour le stagiaire " + id + " et le qcm " + qcm_id);
		}

		return redirect(controllers.admin.routes.StagiairesController.show(id));
	}

}
