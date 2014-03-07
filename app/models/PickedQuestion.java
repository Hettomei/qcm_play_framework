package models;

import java.util.*;

import play.data.validation.Constraints.*;
import play.db.jpa.JPA;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import exceptions.FrozenException;

@Entity
@Table(name="picked_questions")
public class PickedQuestion{

	@Id
	@GeneratedValue
	public Long id;

	@OneToOne
	public Question question;
	public Integer answer;

	public void save() {
		JPA.em().persist(this);
	}

	public String displayStatus(){
		if(answer == null){
			return "Non répondu";
		}
			return "Répondu";
	}
}
