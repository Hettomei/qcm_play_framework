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
	public String name;
	@Required
	public String description;
	@Required
	public Long numberOfQuestions;
	@ManyToMany
	public List<Stagiaire> stagiaires;

	//save and delete cascaded
	@OneToMany(cascade=CascadeType.ALL)
	public List<Question> questions;

	public static Finder<Long,Qcm> find = new Finder(Long.class, Qcm.class);

	public static List<Qcm> all() {
		return find.all();
	}

}
