package models;
import java.util.*;
import play.db.ebean.*;
import play.data.validation.Constraints.*;
import javax.persistence.*;

@Entity
public class Stagiaire extends Model {
	@Id
	public Long id;
	@Required
	public String nom;
	@Required
	public String prenom;
	@Required
	public String promotion;

	@ManyToMany(mappedBy="stagiaires")
	public List<Qcm> qcms;

	public static Finder<Long,Stagiaire> find = new Finder(Long.class, Stagiaire.class);

	public static List<Stagiaire> all() {
		return find.all();
	}

}
