package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Praktikan extends Model {

    @Id
    @Constraints.Min(10)
    public Long nim_praktikan;

    @Constraints.Required
    public String nama_praktikan;

    public String ttl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_kelas")
    public Kelas kelas;
  
    @OneToOne
    @JoinColumn(name="user_id")
    AuthorisedUser authorisedUser;
    
    public static final Finder<Long, Praktikan> find = new Finder<>(Praktikan.class);

    public void setKelas(Kelas kelas) {
        this.kelas = kelas;
    }

    public static List<Praktikan> praktikanList(){
        return find.query().fetch("kelas").findList();
    }
}