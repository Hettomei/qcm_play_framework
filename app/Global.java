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
			@SuppressWarnings("unchecked")
			Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("initial-data.yml");

			if(Ebean.find(Stagiaire.class).findRowCount() == 0) {
				Ebean.save(all.get("stagiaires"));
			}
			if(Ebean.find(Qcm.class).findRowCount() == 0) {
				Ebean.save(all.get("qcms"));
			}
			if(Ebean.find(Question.class).findRowCount() == 0) {
				Ebean.save(all.get("questions"));
			}
		}

	}
}
