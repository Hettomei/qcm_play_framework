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

	public static Finder<Long,Question> find = new Finder(Long.class, Question.class);

	public static List<Question> all() {
		return find.all();
	}

}
