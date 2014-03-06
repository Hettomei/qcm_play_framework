package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;


public class Application extends Controller {

	// -- Authentication
	public static class Login {
		public String id;
		public String validate() {
			if(Stagiaire.authenticate(id) == null) {
				return "Invalid user or password";
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
	public static Result authenticate() {
		Form<Login> loginForm = form(Login.class).bindFromRequest();
		if(loginForm.hasErrors()) {
			return badRequest(views.html.login.render(loginForm));
		} else {
			session("id", loginForm.get().id.toString());
			return redirect(routes.StagiairesController.index());
		}
	}

	/**
	 * Logout and clean the session.
	 */
	public static Result logout() {
		session().clear();
		flash("success", "Vous avez été déconnecté");
		return redirect(routes.Application.login());
	}

}
