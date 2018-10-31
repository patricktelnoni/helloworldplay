package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import be.objectify.deadbolt.java.actions.SubjectPresent;
import models.Asprak;
import models.AuthorisedUser;
import models.SecurityRole;
import forms.*;
import play.libs.Json;
import play.mvc.*;
import play.data.*;
import javax.inject.*;
import java.util.List;
import java.util.ArrayList;
import org.mindrot.jbcrypt.*;




@Restrict({@Group("Laboran")})
public class HomeController extends Controller{ 
    @Inject FormFactory formFactory;
    @Inject Asprak asprak;
    @Inject AuthorisedUser user;
    @Inject SecurityRole securityRole;
    
    public Result index() {
         Form<LoginForm> loginForm = formFactory.form(LoginForm.class);
         Object user = ctx().args.get("user");
         session("connected", "user@gmail.com");    
         // return ok(views.html.user.render(loginForm));
         return ok(Json.toJson(user));

    }

    
    public Result doLogin(){        
        DynamicForm  requestData = formFactory.form().bindFromRequest();
        Asprak asprak            = Asprak.find.byId(Long.parseLong(requestData.get("nim")));        
               
        return ok("Hello " + requestData.get("nim") + " " + requestData.get("password"));

    }
    @Restrict({@Group("Laboran")})
    public Result daftarAsprak(){
         Form<LoginForm> loginForm = formFactory.form(LoginForm.class);
         Form<Asprak> asprakForm = formFactory.form(Asprak.class);
         return ok(
             views.html.asprak.listasprak.render(Asprak.all(), asprakForm)
         );
//        String user = session("email");
//        Logger.info("Calling action for {}", user);
//        return ok(Json.toJson(user));
        
    }

    public Result formAsprak(){   
           
        Form<Asprak> asprakForm = formFactory.form(Asprak.class);
        return ok(views.html.asprak.tambahasprak.render(asprakForm));

    }

    public Result tambahAsprak(){
        DynamicForm  requestData = formFactory.form().bindFromRequest();
        List<SecurityRole> roleuser = new ArrayList<SecurityRole>();
        roleuser.add(securityRole.findByName("Asprak"));
        user.userName               = requestData.get("Nim");
        String hashed               = BCrypt.hashpw(requestData.get("Password"), BCrypt.gensalt());
        user.password               = hashed;

        user.setRoles(roleuser);
        user.save();

        asprak.nim_asprak   = Long.parseLong(requestData.get("Nim"));
        asprak.nama         = requestData.get("Nama");
        asprak.setAuthorisedUser(user);

        asprak.save();
        return redirect(routes.HomeController.daftarAsprak());

    }

    public Result hapusAsprak(Long id){
        Asprak.delete(id);
        return redirect(routes.HomeController.daftarAsprak());
    }

    public Result editAsprak(Long id){
        Asprak asprak                   = Asprak.find.byId(id);        
        Form<Asprak> asprakForm         = formFactory.form(Asprak.class);
        Form<Asprak> filledAsprakForm   = asprakForm.fill(asprak);
        return ok(
            views.html.asprak.editasprak.render(id, filledAsprakForm)
        );
    }

    public Result updateAsprak(Long id){
        DynamicForm  requestData = formFactory.form().bindFromRequest();
        Asprak asprak       = new Asprak();
        asprak.nim_asprak   = Long.parseLong(requestData.get("Nim_asprak"));
        asprak.nama         = requestData.get("Nama");

        asprak.update();
        return redirect(routes.HomeController.daftarAsprak());

    }

}
