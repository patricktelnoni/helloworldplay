package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import models.Matakuliah;
import javax.inject.Inject;

@Restrict({@Group("Laboran")})
public class MataKuliahController extends Controller {
    @Inject Matakuliah mataKuliah;
    @Inject
    FormFactory formFactory;

    public Result index(){
        return ok(views.html.matakuliah.list.render(mataKuliah.find.all()));

    }
    public Result form(){
        Form<Matakuliah> form = formFactory.form(Matakuliah.class);
        return ok(views.html.matakuliah.tambah.render(form));

    }
    public Result tambah(){
        DynamicForm requestData     = formFactory.form().bindFromRequest();
        mataKuliah.nama_matakuliah  = requestData.get("nama_matakuliah");
        mataKuliah.kode_matakuliah  = requestData.get("kode_matakuliah");
        mataKuliah.save();
        return redirect(routes.MataKuliahController.index());

    }
    public Result edit(Long id){
        Form<Matakuliah> dataForm       = formFactory.form(Matakuliah.class);
        Form<Matakuliah> filledForm     = dataForm.fill(Matakuliah.find.byId(id));
        return ok(
                views.html.matakuliah.edit.render(id, filledForm)
        );

    }
    public Result update(Long id){
        DynamicForm  requestData    = formFactory.form().bindFromRequest();

        mataKuliah.nama_matakuliah  = requestData.get("nama_matakuliah");
        mataKuliah.kode_matakuliah  = requestData.get("kode_matakuliah");
        mataKuliah.setId_matakuliah(id);
        mataKuliah.update();
        return redirect(routes.MataKuliahController.index());

    }
    public Result hapus(Long id){
        mataKuliah.find.ref(id).delete();
        return redirect(routes.MataKuliahController.index());
    }
}
