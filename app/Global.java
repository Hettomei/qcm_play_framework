import play.Application;
import play.Logger;
import play.GlobalSettings;
import play.api.mvc.EssentialFilter;
import models.*;
import play.*;
import play.libs.*;

import java.util.*;

import com.avaje.ebean.*;

public class Global extends GlobalSettings {

	public void onStart(Application app) {
		InitialData.insert(app);
	}

	static class InitialData {

		public static void insert(Application app) {
			if(Ebean.find(Stagiaire.class).findRowCount() == 0) {

				@SuppressWarnings("unchecked")
				Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("initial-data.yml");

				// Insert users first
				Ebean.save(all.get("stagiaires"));

				// Insert projects
				Ebean.save(all.get("qcms"));

			}
		}

	}
}
