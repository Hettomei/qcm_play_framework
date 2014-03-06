package models;

import java.util.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import play.data.validation.Constraints.*;
import play.db.jpa.JPA;

import exceptions.FrozenException;

@Entity
@Table(name="qcms")
public class Qcm{

	@Id
	@GeneratedValue
	public Long id;

	@Required
	public String name;

	@Required
	public String description;

	@Required
	public Long numberOfQuestions;

	@ManyToMany
	public List<Question> questions;

	@Column(nullable=false)
	public Boolean frozen = false;

	public static List<Qcm> all() {
		CriteriaBuilder builder = JPA.em().getCriteriaBuilder();
		CriteriaQuery<Qcm> criteria = builder.createQuery( Qcm.class );
		Root<Qcm> qcmRoot = criteria.from( Qcm.class );
		criteria.select( qcmRoot );
		List<Qcm> qcms = JPA.em().createQuery( criteria ).getResultList();
		return qcms;
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

	public void update(Qcm other) throws FrozenException{
		if(frozen){
			throw new FrozenException();
		}
		name = other.name;
		description = other.description;
		numberOfQuestions = other.numberOfQuestions;
		JPA.em().merge(this);
	}

	public static Qcm findById(Long id) {
		return JPA.em().find(Qcm.class, id);
	}

	public List<Stagiaire> getStagiaires(){
		return Evaluation.findStagiairesByQcm(this);
	}
}
