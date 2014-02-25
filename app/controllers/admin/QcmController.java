package controllers.admin;

import play.*;
import play.mvc.*;
import play.data.*;
import models.*;
import views.html.*;
import java.util.*;
import play.data.validation.Constraints.*;
import static play.data.Form.*;

public class QcmController extends Controller {

	static Form<Qcm> theForm = Form.form(Qcm.class);

	public static Result index() {
		return redirect(routes.QcmController.qcms());
	}
	public static Result qcms() {
		return ok(views.html.index.render(Qcm.all(), theForm));
	}

	public static Result newQcm() {
		Form<Qcm> filledForm = theForm.bindFromRequest();
		if(filledForm.hasErrors()) {
			return badRequest(
					views.html.index.render(Qcm.all(), filledForm)
					);
		} else {
			Qcm.create(filledForm.get());
			return redirect(routes.QcmController.qcms());  
		}
	}

	public static Result deleteQcm(Long id) {
		Qcm.delete(id);
		return redirect(routes.QcmController.qcms());
	}

}
