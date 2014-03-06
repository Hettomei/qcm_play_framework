package models;
import java.util.*;

import play.data.validation.Constraints.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
import play.db.jpa.JPA;

@Entity
@Table(name="evaluations")
public class Evaluation{

	@Id
	@GeneratedValue
	public Long id;

	@Required
	@ManyToOne
	@JoinColumn(name = "stagiaire_id")
	public Stagiaire stagiaire;

	@Required
	@ManyToOne
	@JoinColumn(name = "qcm_id")
	public Qcm qcm;

	@Required
	@Column(nullable=false)
	public State status = State.NEW ;
	public enum State {
		NEW, //No answer
		CONTINUE, //At least one answer
		FINISHED // all questions answered
	}

	public Evaluation(){
	}

	public Evaluation(Stagiaire stagiaire, Qcm qcm){
		this.stagiaire = stagiaire;
		this.qcm = qcm;
	}

	public static List<Evaluation> all() {
		CriteriaBuilder builder = JPA.em().getCriteriaBuilder();
		CriteriaQuery<Evaluation> criteria = builder.createQuery( Evaluation.class );
		Root<Evaluation> evaluationRoot = criteria.from( Evaluation.class );
		criteria.select( evaluationRoot );
		List<Evaluation> evaluations = JPA.em().createQuery( criteria ).getResultList();
		return evaluations;
	}

	public static List<Evaluation> findByStagiaire(Stagiaire stagiaire) {
		CriteriaBuilder builder            = JPA.em().getCriteriaBuilder();
		CriteriaQuery<Evaluation> criteria = builder.createQuery(Evaluation.class);
		Root<Evaluation> evaluationRoot    = criteria.from(Evaluation.class);
		Predicate condition                = builder.equal(evaluationRoot.get("stagiaire"), stagiaire);
		criteria.where(condition);
		List<Evaluation> evaluations = JPA.em().createQuery(criteria).getResultList();
		return evaluations;
	}

	public static Evaluation findByStagiaireAndQcm(Stagiaire stagiaire, Qcm qcm) {
		CriteriaBuilder builder            = JPA.em().getCriteriaBuilder();
		CriteriaQuery<Evaluation> criteria = builder.createQuery(Evaluation.class);
		Root<Evaluation> evaluationRoot    = criteria.from(Evaluation.class);
		Predicate condition1               = builder.equal(evaluationRoot.get("stagiaire"), stagiaire);
		Predicate condition2               = builder.equal(evaluationRoot.get("qcm"), qcm);
		Predicate condition3               = builder.and(condition1, condition2);
		criteria.where(condition3);
		return JPA.em().createQuery(criteria).getSingleResult();
	}

	public static List<Qcm> findQcmsByStagiaire(Stagiaire stagiaire) {
		CriteriaBuilder builder            = JPA.em().getCriteriaBuilder();
		CriteriaQuery<Evaluation> criteria = builder.createQuery(Evaluation.class);
		Root<Evaluation> evaluationRoot    = criteria.from(Evaluation.class);
		Predicate condition                = builder.equal(evaluationRoot.get("stagiaire"), stagiaire);
		criteria.where(condition);
		List<Evaluation> evaluations = JPA.em().createQuery(criteria).getResultList();


		List<Qcm> qcms = new ArrayList<Qcm>();

		for (Evaluation eval : evaluations) {
			qcms.add(eval.qcm);
		}
		return qcms;
	}

	public static List<Stagiaire> findStagiairesByQcm(Qcm qcm) {
		CriteriaBuilder builder            = JPA.em().getCriteriaBuilder();
		CriteriaQuery<Evaluation> criteria = builder.createQuery(Evaluation.class);
		Root<Evaluation> evaluationRoot    = criteria.from(Evaluation.class);
		Predicate condition                = builder.equal(evaluationRoot.get("qcm"), qcm);
		criteria.where(condition);
		List<Evaluation> evaluations = JPA.em().createQuery(criteria).getResultList();


		List<Stagiaire> stagiaires = new ArrayList<Stagiaire>();

		for (Evaluation eval : evaluations) {
			stagiaires.add(eval.stagiaire);
		}
		return stagiaires;
	}

	public void save() {
		JPA.em().persist(this);
	}

	public void delete() {
		JPA.em().remove(this);
	}

}
