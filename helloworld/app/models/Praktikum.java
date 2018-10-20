package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Praktikum extends Model {

    @Id
    @Constraints.Min(10)
    public Long idpraktikum;

    @Constraints.Required
    public String name;

    public boolean done;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date tanggal_praktikum = new Date();

    @ManyToOne
    Kelas kelas;
    
    public static final Finder<Long, Praktikum> find = new Finder<>(Praktikum.class);
}