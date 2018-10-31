package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Matakuliah extends Model {
    @Id
    @Constraints.Min(10)
    public Long id_matakuliah;

    @Constraints.Required
    @Column(columnDefinition = "TEXT")
    public String kode_matakuliah;

    @Constraints.Required
    public String nama_matakuliah;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<Praktikum> praktikum = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    public List<PlotingAsprak> plotAsprak = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    public List<Praktikan> praktikan = new ArrayList<>();

    public static final Finder<Long, Matakuliah> find = new Finder<>(Matakuliah.class);

    public void setId_matakuliah(Long id_matakuliah) {
        this.id_matakuliah = id_matakuliah;
    }
}
