package controllers.admin;
import play.libs.*;

import java.util.*;

import play.mvc.*;
import play.data.*;
import play.db.jpa.Transactional;
import play.db.jpa.JPA;

import models.Qcm;
import models.Question;
import models.Stagiaire;
public class GeneratorController extends AdminController {

	@Transactional
	public static Result generateQcms() {
		//Qcm q = new Qcm();
		//q.setName("mon nom");
		//q.setDescription("A desc");
		//q.setNumberOfQuestions(17L);
		//q.save();
		Map<String,ArrayList<Qcm>> all = (Map<String,ArrayList<Qcm>>)Yaml.load("initial-data.yml");
		for(Qcm qcm : all.get("qcms")){
			System.out.println(qcm.name);
			qcm.name = qcm.name + rand(1,1000);
			qcm.save();
		}
		return redirect(controllers.admin.routes.QcmController.index());
	}

	@Transactional
	public static Result generateStagiaires() {
		Map<String,ArrayList<Stagiaire>> all = (Map<String,ArrayList<Stagiaire>>)Yaml.load("initial-data.yml");
		for(Stagiaire st : all.get("stagiaires")){
			st.nom = st.nom + rand(1, 1000);
			st.save();
		}
		return redirect(controllers.admin.routes.StagiairesController.index());
	}

	@Transactional
	public static Result generateQuestions() {
		Map<String,ArrayList<Question>> all = (Map<String,ArrayList<Question>>)Yaml.load("initial-data.yml");
		for(Question st : all.get("questions")){
			st.question = st.question + rand(1, 1000);
			st.save();
		}
		return redirect(controllers.admin.routes.QuestionsController.index());
	}

	public static String rand(int lower, int higher){
	  return " " + (int)((Math.random() * (higher-lower)) + lower);
	}

}
