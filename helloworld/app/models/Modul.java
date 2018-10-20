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
    private String jurnal;
    
    @Column(columnDefinition = "TEXT")
    private String tugas_akhir;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date deadline = new Date();

    public static final Finder<Long, Modul> find = new Finder<>(Modul.class);
}