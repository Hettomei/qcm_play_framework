package models;

import java.util.*;

import javax.persistence.*;

import play.data.validation.Constraints.*;
import play.db.jpa.*;

import javax.persistence.criteria.CriteriaBuilder;
import org.hibernate.criterion.Restrictions;
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
		return JPA.em().createQuery("from Question order by id").getResultList();
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
