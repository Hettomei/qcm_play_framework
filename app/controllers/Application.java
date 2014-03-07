package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;

import play.db.jpa.Transactional;

public class Application extends Controller {

	// -- Authentication
	public static class Login {
		public String id = "";
		public String validate() {
			if(id.equals("")) {
				return "Ne peut pas être vide";
			}
			if(Stagiaire.authenticate(id) == null) {
				return "Id stagiaire inexistant";
			}
			return null;
		}
	}

	/**
	 * Login page.
	 */
	public static Result login() {
		return ok(views.html.login.render(form(Login.class)));
	}

	/**
	 * Handle login form submission.
	 */
	@Transactional
	public static Result authenticate() {
		Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
		if(loginForm.hasErrors()) {
			flash("warning", "Impossible d'authentifier");
			return badRequest(views.html.login.render(loginForm));
		} else {
			session("id", loginForm.get().id);
			flash("info", "Vous êtes connecté");
			return redirect(routes.StagiairesController.index());
		}
	}

	/**
	 * Logout and clean the session.
	 */
	public static Result logout() {
		session().clear();
		flash("info", "Vous avez été déconnecté");
		return redirect(routes.Application.login());
	}

}
