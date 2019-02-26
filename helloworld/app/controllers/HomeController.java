package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import be.objectify.deadbolt.java.actions.SubjectPresent;
import com.fasterxml.jackson.databind.JsonNode;
import models.Asprak;
import models.AuthorisedUser;
import models.SecurityRole;
import forms.*;
import play.libs.Json;
import play.mvc.*;
import play.data.*;
import javax.inject.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

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

    public Result jsonListAsprak(){
        ArrayList data = new ArrayList();
        for (Asprak asprak:Asprak.all()) {
            HashMap<String, Object> result = new HashMap<String, Object>();
            result.put("nim", asprak.nim_asprak);
            result.put("nama", asprak.nama);
            result.put("role", asprak.role);
            data.add(result);
        }
        return ok(Json.toJson(data));

    }

    public Result daftarAsprak(){
         Form<Asprak> asprakForm = formFactory.form(Asprak.class);
         return ok(
             views.html.asprak.listasprak.render(asprakForm)
         );
//        String user = session("email");
//        Logger.info("Calling action for {}", user);
//        return ok(Json.toJson(user));
        
    }

    public Result formAsprak(){   
           
        Form<Asprak> asprakForm = formFactory.form(Asprak.class);
        return ok(views.html.asprak.tambahasprak.render(asprakForm));

    }
    @BodyParser.Of(BodyParser.Json.class)
    public Result tambahAsprak(){

        JsonNode json       = request().body().asJson();
        String nama         = json.findPath("nama").textValue();
        String nim          = json.findPath("nim").textValue();
        String password     = json.findPath("password").textValue();
        List<SecurityRole> roleuser = new ArrayList<SecurityRole>();
        roleuser.add(securityRole.findByName("Asprak"));
        user.userName               = nim;
        String hashed               = BCrypt.hashpw(password, BCrypt.gensalt());
        user.password               = hashed;

        user.setRoles(roleuser);
        user.save();

        asprak.nim_asprak   = Long.parseLong(nim);
        asprak.nama         = nama;
        asprak.setAuthorisedUser(user);

        asprak.save();
        return ok("Success");
//        return redirect(routes.HomeController.daftarAsprak());

    }

    public Result hapusAsprak(Long id){
        Asprak.delete(id);
        return redirect(routes.HomeController.daftarAsprak());
    }

    public Result editAsprak(Long id){
        Asprak asprak                   = Asprak.find.byId(id);
        ArrayList data = new ArrayList();
            HashMap<String, Object> result = new HashMap<String, Object>();
            result.put("nim", asprak.nim_asprak);
            result.put("nama", asprak.nama);
            result.put("role", asprak.role);
            data.add(result);

        return ok(Json.toJson(result));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result updateAsprak(Long id){

        JsonNode json       = request().body().asJson();
        String nama         = json.findPath("nama").textValue();
        Asprak asprak       = new Asprak();
        asprak.nim_asprak   = id;
        asprak.nama         = nama;
        asprak.update();
        return ok("Success");


    }

}
