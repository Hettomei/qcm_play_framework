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
@Table(name="questions")
public class Question{

	@Id
	@GeneratedValue
	public Long id;

	@Required
	public String question;

	@Required
	public String reponse;

	@Column(nullable=false)
	public Boolean frozen = false;

	@ManyToMany(mappedBy="questions")
	public List<Qcm> qcms;

	public static List<Question> all() {
		CriteriaBuilder builder = JPA.em().getCriteriaBuilder();
		CriteriaQuery<Question> criteria = builder.createQuery( Question.class );
		Root<Question> questionRoot = criteria.from( Question.class );
		criteria.select( questionRoot );
		List<Question> questions = JPA.em().createQuery( criteria ).getResultList();
		return questions;
	}

	public static Question findById(Long id) {
		return JPA.em().find(Question.class, id);
	}

	public void update(Question other) throws FrozenException{
		if(frozen){
			throw new FrozenException();
		}
		this.question = other.question;
		this.reponse = other.reponse;
		JPA.em().merge(this);
	}

	public void save() {
		JPA.em().persist(this);
	}

	public void delete() throws FrozenException{
		if(frozen){
			throw new FrozenException();
		}
		JPA.em().remove(this);
	}
}
