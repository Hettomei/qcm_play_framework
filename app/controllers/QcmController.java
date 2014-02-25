package controllers;

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
		return ok("ok");
	}
}
