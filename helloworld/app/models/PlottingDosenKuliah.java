package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class PlottingDosenKuliah extends Model {
    @Id
    @Constraints.Min(10)
    public Long id_plotting;

    @ManyToOne
    private Dosen dosen;

    @ManyToOne
    private Matakuliah matakuliah;

    public Dosen getDosen() {
        return dosen;
    }

    public Matakuliah getMatakuliah() {
        return matakuliah;
    }

    public void setMatakuliah(Matakuliah matakuliah) {
        this.matakuliah = matakuliah;
    }

    public void setDosen(Dosen dosen) {
        this.dosen = dosen;
    }

    public static final Finder<Long, PlottingDosenKuliah> find = new Finder<>(PlottingDosenKuliah.class);

    public static List<PlottingDosenKuliah> getListMataKuliahDosen(String nim)
    {

        return find.query().fetch("matakuliah").
                where().
                eq("dosen_nim_dosen", nim).findList();
    }
}
