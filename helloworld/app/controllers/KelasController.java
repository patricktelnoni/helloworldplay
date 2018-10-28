package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;

import models.Kelas;

import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;

import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

@Restrict({@Group("Laboran")})
public class KelasController extends Controller {
    @Inject
    FormFactory formFactory;
    @Inject Kelas kelas;
    public Result index(){
        return ok(views.html.kelas.list.render(Kelas.find.all()));

    }
    public Result form(){
        Form<Kelas> form = formFactory.form(Kelas.class);
        return ok(views.html.kelas.tambah.render(form));

    }
    public Result tambah(){
        DynamicForm requestData     = formFactory.form().bindFromRequest();
        kelas.nama_kelas            = requestData.get("nama_kelas");
        kelas.save();
        return redirect(routes.KelasController.index());

    }
    public Result edit(Long id){
        Form<Kelas> dataForm     = formFactory.form(Kelas.class);
        Form<Kelas> filledForm   = dataForm.fill(kelas.find.byId(id));
        return ok(
                views.html.kelas.edit.render(id, filledForm)
        );

    }
    public Result update(Long id){
        DynamicForm  requestData    = formFactory.form().bindFromRequest();
        kelas.nama_kelas            = requestData.get("nama_kelas");
        kelas.id_kelas = id;
        kelas.update();
        return redirect(routes.KelasController.index());

    }
    public Result hapus(Long id){
        kelas.find.byId(id).delete();
        return redirect(routes.KelasController.index());
    }
}
