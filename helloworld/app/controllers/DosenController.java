package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;

import models.AuthorisedUser;
import models.SecurityRole;
import play.mvc.*;
import play.data.*;
import models.Dosen;
import javax.inject.*;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.*;




@Restrict({@Group("Laboran")})
public class DosenController extends Controller{
    @Inject FormFactory formFactory;
    @Inject Dosen dosen;
    @Inject AuthorisedUser user;
    @Inject SecurityRole securityRole;

    public static Result index() {
        return Results.TODO;
    }


    public Result daftarDosen(){
        Form<Dosen> DosenForm = formFactory.form(Dosen.class);
        return ok(
                views.html.dosen.listdosen.render(Dosen.find.all(), DosenForm)
        );

    }

    public Result formDosen(){
        Form<Dosen> DosenForm = formFactory.form(Dosen.class);
        return ok(views.html.dosen.tambahdosen.render(DosenForm));

    }

    public Result tambahDosen(){
        DynamicForm  requestData    = formFactory.form().bindFromRequest();
        List<SecurityRole> roleuser = new ArrayList<SecurityRole>();
        roleuser.add(securityRole.findByName("Dosen"));
        user.userName               = requestData.get("Nim");
        String hashed               = BCrypt.hashpw(requestData.get("Password"), BCrypt.gensalt());
        user.password               = hashed;

        user.setRoles(roleuser);
        user.save();

        dosen.nim_dosen             = Long.parseLong(requestData.get("Nim"));
        dosen.name                  = requestData.get("Nama");
        dosen.setAuthorisedUser(user);
        dosen.save();
        return redirect(routes.DosenController.daftarDosen());

    }

    public Result hapusDosen(Long id){
        dosen.find.ref(id).delete();
        return redirect(routes.DosenController.daftarDosen());
    }

    public Result editDosen(Long id){
        Form<Dosen> DosenForm         = formFactory.form(Dosen.class);
        Form<Dosen> filledDosenForm   = DosenForm.fill(dosen.find.byId(id));
        return ok(
                views.html.dosen.editdosen.render(id, filledDosenForm)
        );
    }

    public Result updateDosen(Long id){
        DynamicForm  requestData = formFactory.form().bindFromRequest();
        dosen.nim_dosen   = Long.parseLong(requestData.get("Nim_dosen"));
        dosen.name        = requestData.get("Name");

        dosen.update();
        return redirect(routes.DosenController.daftarDosen());

    }

}
