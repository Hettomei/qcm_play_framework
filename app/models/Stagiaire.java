package models;

import java.util.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import play.data.validation.Constraints.*;
import play.db.jpa.JPA;

@Entity
@Table(name="stagiaires")
public class Stagiaire{
	@Id
	@GeneratedValue
	public Long id;

	@Required
	public String nom;

	@Required
	public String prenom;

	@Required
	public String promotion;

	public static List<Stagiaire> all() {
		CriteriaBuilder builder = JPA.em().getCriteriaBuilder();
		CriteriaQuery<Stagiaire> criteria = builder.createQuery( Stagiaire.class );
		Root<Stagiaire> stagiaireRoot = criteria.from( Stagiaire.class );
		criteria.select( stagiaireRoot );
		List<Stagiaire> stagiaires = JPA.em().createQuery( criteria ).getResultList();
		return stagiaires;
	}

	public static Stagiaire findById(Long id) {
		return JPA.em().find(Stagiaire.class, id);
	}

	public void save() {
		JPA.em().persist(this);
	}

	public List<Evaluation> getEvaluations(){
		return Evaluation.findByStagiaire(this);
	}

	public List<Qcm> getQcms(){
		return Evaluation.findQcmsByStagiaire(this);
	}

}
