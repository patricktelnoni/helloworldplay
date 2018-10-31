package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class PlotingAsprak extends Model {

    @Id
    @Constraints.Min(10)
    public Long id_plotting;

    @Constraints.Required
    public String name;
  
    @OneToMany(cascade = CascadeType.ALL)
    public List<Praktikum> praktikum = new ArrayList<>();
    
    @ManyToOne(cascade = CascadeType.ALL)
    Asprak asprak;  

    @ManyToOne
    Kelas kelas;

    @ManyToOne
    Matakuliah matakuliah;

    public static final Finder<Long, PlotingAsprak> find = new Finder<>(PlotingAsprak.class);
}