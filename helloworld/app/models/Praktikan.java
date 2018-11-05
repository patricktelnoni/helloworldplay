package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import io.ebean.annotation.JsonIgnore;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Praktikan extends Model {

    @Id
    @JsonIgnore
    public Long id_praktikan;

    @Constraints.Required
    @Column(columnDefinition = "TEXT")
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

    public void setNim_praktikan(Long nim_praktikan) {
        this.nim_praktikan = nim_praktikan;
    }


    public Long getNim_praktikan() {
        return nim_praktikan;
    }

    public static PagedList<Praktikan> praktikanList(Integer page, String filter){
        return find.query().fetch("kelas").
                where().
                like("nim_praktikan", ""+filter+"%").or().
                like("nama_praktikan", ""+filter+"%").or().
                like("kelas", ""+filter+"%").

                setFirstRow(page *10).
                setMaxRows(10).
                findPagedList();
    }

    public static List<Praktikan> praktikanListJson(String filter){
        return find.query().fetch("kelas").
                where().or().
                like("nim_praktikan", "%"+filter+"%").or().
                like("nama_praktikan", "%"+filter+"%").or().
                like("kelas", "%"+filter+"%").
                findList();
    }
}