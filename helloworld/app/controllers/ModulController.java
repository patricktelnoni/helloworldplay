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
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
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
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");

        modul.setNama_modul(requestData.get("Nama"));
        modul.setTp(requestData.get("TP"));
        modul.setJurnal(requestData.get("Jurnal"));
        modul.setDurasi_pengerjaan_jurnal(requestData.get("durasi_jurnal"));
        modul.setTugas_akhir(requestData.get("TA"));
        modul.setMatakuliah(matakuliah.getByKode(kodematakuliah));
        modul.setDeadline_tugas_akhir(dateFormat.parse(requestData.get("deadline_ta"), new ParsePosition(5)));
        modul.save();
        return redirect(routes.ModulController.getModulByMataKuliah(kodematakuliah));

    }
    public Result editModul(Long id, String matakuliah){
        Form<Modul> ModulForm         = formFactory.form(Modul.class);
        Form<Modul> filledForm        = ModulForm.fill(modul.find.byId(id));
        return ok(
                views.html.modul.edit.render(id, matakuliah, filledForm)
        );

    }
    public Result updateModul(Long id, String matakuliah){
        DynamicForm requestData    = formFactory.form().bindFromRequest();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");

        modul.setNama_modul(requestData.get("nama_modul"));
        modul.setTp(requestData.get("tp"));
        modul.setJurnal(requestData.get("jurnal"));
        modul.setDurasi_pengerjaan_jurnal(requestData.get("durasi_pengerjaan_jurnal"));
        modul.setTugas_akhir(requestData.get("tugas_akhir"));
        modul.setId_modul(id);
//        modul.setDeadline_tugas_akhir(dateFormat.parse(requestData.get("deadline_ta"), new ParsePosition(5)));
        modul.update();
        return redirect(routes.ModulController.getModulByMataKuliah(matakuliah));

    }
    public Result hapusModul(Long id){
        return ok(Json.toJson("Hello"));
    }

}
