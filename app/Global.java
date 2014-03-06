import play.api.mvc.EssentialFilter;
import models.*;
import play.*;
import play.libs.*;

import java.util.*;

public class Global extends GlobalSettings {

	public void onStart(Application app) {
		InitialData.insert(app);
	}

	static class InitialData {

		public static void insert(Application app) {
		}

	}
}
