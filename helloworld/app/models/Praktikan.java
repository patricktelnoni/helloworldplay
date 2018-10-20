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
    public String name;
  
    @ManyToOne
    Kelas kelas;
    
    public static final Finder<Long, Praktikan> find = new Finder<>(Praktikan.class);
}