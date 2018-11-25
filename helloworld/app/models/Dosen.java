package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

@Entity
public class Dosen extends Model {
    @Id
    @Constraints.Min(10)
    public Long nim_dosen;

    @Constraints.Required
    public String name;

    @OneToMany
    public PlottingDosenKuliah plottingDosenKuliah;

    @OneToOne
    @JoinColumn(name="user_id")
    public AuthorisedUser authorisedUser;

    public String getName() {
        return name;
    }

    public Long getNim_dosen() {
        return nim_dosen;
    }

    public static final Finder<Long, Dosen> find = new Finder<>(Dosen.class);

    public void setAuthorisedUser(AuthorisedUser authorisedUser) {
        this.authorisedUser = authorisedUser;
    }

    public AuthorisedUser getAuthorisedUser() {
        return authorisedUser;
    }

    public static List<Dosen> getListMataKuliahDosen(String nim)
    {

        return find.query().fetch("plottingDosenKuliah").
                where().
                eq("nim_dosen", nim).findList();
    }
}
