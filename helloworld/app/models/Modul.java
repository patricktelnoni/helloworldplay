package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Modul extends Model {

    @Id
    @Constraints.Min(10)
    public Long id_modul;

    @Constraints.Required
    public String nama_modul;

    @Column(columnDefinition = "TEXT")
    public String tp;

    @Column(columnDefinition = "TEXT")
    public String jurnal;
    
    @Column(columnDefinition = "TEXT")
    public String tugas_akhir;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date deadline = new Date();

    @OneToOne
    public Matakuliah matakuliah;

    public void setNama_modul(String nama_modul) {
        this.nama_modul = nama_modul;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public void setJurnal(String jurnal) {
        this.jurnal = jurnal;
    }

    public void setTugas_akhir(String tugas_akhir) {
        this.tugas_akhir = tugas_akhir;
    }

    public void setMatakuliah(Matakuliah matakuliah) {
        this.matakuliah = matakuliah;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public static final Finder<Long, Modul> find = new Finder<>(Modul.class);

    public static List<Modul> getByMataKuliah(String kodematakuliah){
        return find.query().
                fetch("matakuliah").
                where().eq("kode_matakuliah", kodematakuliah).
                findList();
    }
}