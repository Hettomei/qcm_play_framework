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
	@ManyToMany
	public List<Question> questions;

	public static Finder<Long,Qcm> find = new Finder(Long.class, Qcm.class);

	public static List<Qcm> all() {
		return find.all();
	}

	public String all_question_id(){
		StringBuilder sb = new StringBuilder();

		for(Question s: questions) {
			sb.append(s.id.toString()).append(',');
		}

		if(sb.length() > 0){
			sb.deleteCharAt(sb.length()-1); //delete last comma
		}

		return sb.toString();
	}

}
