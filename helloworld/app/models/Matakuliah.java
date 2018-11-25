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

    @OneToMany(cascade = CascadeType.ALL)
    public List<PlottingDosenKuliah> plottingDosenKuliah= new ArrayList<>();

    public Long getId_matakuliah() {
        return id_matakuliah;
    }

    public String getNama_matakuliah() {
        return nama_matakuliah;
    }

    public String getKode_matakuliah() {
        return kode_matakuliah;
    }

    @OneToOne
    public Modul modul;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<Praktikum> praktikum = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    public List<Dosen> dosen= new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    public List<PlotingAsprak> plotAsprak = new ArrayList<>();


    public static final Finder<Long, Matakuliah> find = new Finder<>(Matakuliah.class);

    public void setId_matakuliah(Long id_matakuliah) {
        this.id_matakuliah = id_matakuliah;
    }

    public static Matakuliah getByKode(String kodematakuliah)
    {

        return find.query().where()
                .eq("kode_matakuliah",
                        kodematakuliah)
                .findOne();
    }



}
