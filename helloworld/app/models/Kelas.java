package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Kelas extends Model {

    @Id
    @Constraints.Min(10)
    public Long id_kelas;

    @Constraints.Required
    public String nama_kelas;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Praktikum> praktikum = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL)
    public List<PlotingAsprak> plotAsprak = new ArrayList<>();

    @OneToMany(mappedBy="kelas", cascade = CascadeType.ALL)
    public List<Praktikan> praktikan = new ArrayList<>();

    public void setPraktikan(List<Praktikan> praktikan) {
        this.praktikan = praktikan;
    }

    public List<Praktikan> getPraktikan() {
        return praktikan;
    }

    public static final Finder<Long, Kelas> find = new Finder<>(Kelas.class);
}