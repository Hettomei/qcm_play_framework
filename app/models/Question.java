package models;
import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Question extends Model {
	@Id
	public Long id;
	@Required
	public String question;
	@Required
	public String reponse;
	//if true, can't delete or modify it
	public Boolean readOnly = false;

	@ManyToMany(mappedBy="questions")
	public List<Qcm> qcms;

	public static Finder<Long,Question> find = new Finder(Long.class, Question.class);

	public static List<Question> all() {
		return find.all();
	}

}
