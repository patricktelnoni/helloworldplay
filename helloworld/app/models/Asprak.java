package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Asprak extends Model {
    @Id
    @Constraints.Min(5)
    public Long nim_asprak;

    @Constraints.Required
    public String nama;

    public String role;

    public boolean active;

    @OneToOne
    @JoinColumn(name="user_id")
    AuthorisedUser authorisedUser;

    @OneToMany(cascade = CascadeType.ALL)
    public List<PlotingAsprak> plotingAsprak = new ArrayList<>();        

    public static final Finder<Long, Asprak> find = new Finder<>(Asprak.class);

    public static void delete(Long id) {
		find.ref(id).delete();
    }

    public void setAuthorisedUser(AuthorisedUser authorisedUser) {
        this.authorisedUser = authorisedUser;
    }

    public static List<Asprak> all() {
		//return new ArrayList<Task>();
	    return find.all();
    }
}