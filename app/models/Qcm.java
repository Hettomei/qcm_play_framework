package models;
import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Qcm extends Model {
	@Id
	public Long id;
	@Required
	public String question;
	@Required
	public String reponse;

	public static Finder<Long,Qcm> find = new Finder(Long.class, Qcm.class);

	public static List<Qcm> all() {
		return find.all();
	}

	public static void create(Qcm qcm) {
		qcm.save();
	}

}
