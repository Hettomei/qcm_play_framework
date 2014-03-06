package controllers;

import models.Stagiaire;

import play.data.DynamicForm;
import play.data.Form.*;

import play.mvc.*;

import play.db.jpa.Transactional;

import java.util.*;

@Security.Authenticated(Secured.class)
public class StagiairesController extends Controller {

	@Transactional
	public static Result index() {
		Stagiaire stagiaire = Stagiaire.authenticate(request().username());
		return ok(views.html.stagiaire.index.render(stagiaire));
	}

}
