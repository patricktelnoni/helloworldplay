package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class Laboran extends Model {
    @Id
    @Constraints.Min(10)
    public Long nim_praktikan;

    @Constraints.Required
    public String name;

    @OneToOne
    @JoinColumn(name="user_id")
    AuthorisedUser authorisedUser;

    public static final Finder<Long, Laboran> find = new Finder<>(Laboran.class);
}
