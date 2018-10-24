package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

@Entity
public class Dosen extends Model {
    @Id
    @Constraints.Min(10)
    public Long nim_dosen;

    @Constraints.Required
    public String name;

    @OneToOne
    @JoinColumn(name="user_id")
    AuthorisedUser authorisedUser;

    public static final Finder<Long, Dosen> find = new Finder<>(Dosen.class);


}
