package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="matakuliah")
public class MataKuliah extends Model {
    @Id
    @Constraints.Min(10)
    public Long id_matakuliah;

    @Constraints.Required
    public String nama_matakuliah;

    public String sks;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<Praktikum> praktikum = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    public List<PlotingAsprak> plotAsprak = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    public List<Praktikan> praktikan = new ArrayList<>();

    public static final Finder<Long, Kelas> find = new Finder<>(Kelas.class);
}
