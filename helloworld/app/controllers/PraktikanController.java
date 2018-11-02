package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import models.Kelas;
import models.Praktikan;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Restrict({@Group("Laboran")})
public class PraktikanController extends Controller {
    @Inject FormFactory formFactory;
    @Inject Kelas kelas;
    @Inject Praktikan praktikan;
    public Result index(){
        return ok(views.html.praktikan.list.render(praktikan.praktikanList()));

    }
    public Result form(){
        Form<Praktikan> praktikanForm = formFactory.form(Praktikan.class);
        return ok(
                views.html.praktikan.tambah.render(kelas.find.all(), praktikanForm)
        );

    }
    public Result tambah(){
        DynamicForm  requestData    = formFactory.form().bindFromRequest();

        praktikan.nim_praktikan     = Long.parseLong(requestData.get("nim_praktikan"));
        praktikan.nama_praktikan    = requestData.get("nama_praktikan");
        praktikan.setKelas(Kelas.find.ref(Long.parseLong(requestData.get("kelas"))));
        praktikan.save();
        return redirect(routes.PraktikanController.index());

    }
    public Result edit(Long id){

        Form<Praktikan> asprakForm              = formFactory.form(Praktikan.class);
        Form<Praktikan> filledPraktikanForm     = asprakForm.fill(praktikan.find.byId(id));
        return ok(
                views.html.praktikan.edit.render(id, kelas.find.all(), filledPraktikanForm)
        );

    }
    public Result update(Long id){
        DynamicForm  requestData = formFactory.form().bindFromRequest();
        praktikan.nim_praktikan     = Long.parseLong(requestData.get("nim_praktikan"));
        praktikan.nama_praktikan    = requestData.get("nama_praktikan");
        praktikan.setKelas(Kelas.find.ref(Long.parseLong(requestData.get("kelas.id_kelas"))));
        praktikan.update();
        return redirect(routes.PraktikanController.index());

    }
    public Result hapus(Long id){

        praktikan.find.ref(id).delete();
        return redirect(routes.PraktikanController.index());
    }
}
