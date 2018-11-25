package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import models.Matakuliah;
import models.Modul;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import play.mvc.With;

import javax.inject.Inject;
import java.util.Date;


@Restrict({@Group("Dosen"), @Group("Asprak") })
public class ModulController extends Controller {
    @Inject
    FormFactory formFactory;
    @Inject Modul modul;
    @Inject
    Matakuliah matakuliah;

    public Result index(){
        return ok(views.html.modul.listmodul.render(Modul.find.all()));

    }

    public Result getModulByMataKuliah(String kodematakuliah){
        return ok(views.html.modul.listmodul.render(Modul.getByMataKuliah(kodematakuliah)));
    }
    public Result formModul(String kodematakuliah){
        Form<Modul> ModulForm = formFactory.form(Modul.class);
        return ok(views.html.modul.tambah.render(kodematakuliah, ModulForm));

    }
    public Result tambahModul(String kodematakuliah){
        DynamicForm requestData    = formFactory.form().bindFromRequest();

        modul.setNama_modul(requestData.get("Nama"));
        modul.setTp(requestData.get("TP"));
        modul.setJurnal(requestData.get("Jurnal"));
        modul.setTugas_akhir(requestData.get("TA"));
        modul.setMatakuliah(matakuliah.getByKode(kodematakuliah));
        modul.setDeadline(new Date());
        modul.save();
        return redirect(routes.ModulController.getModulByMataKuliah(kodematakuliah));

    }
    public Result editModul(Long id){
        return ok(Json.toJson("Hello"));

    }
    public Result updateModul(Long id){
        return ok(Json.toJson("Hello"));

    }
    public Result hapusModul(Long id){
        return ok(Json.toJson("Hello"));
    }

}
