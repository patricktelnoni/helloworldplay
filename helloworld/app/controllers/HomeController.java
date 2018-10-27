package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import be.objectify.deadbolt.java.actions.SubjectPresent;
import models.*;
import forms.*;
import play.libs.Json;
import play.mvc.*;
import play.data.*;
import javax.inject.*;



@SubjectPresent
@Restrict({@Group("Laboran")})
public class HomeController extends Controller{ 
    @Inject FormFactory formFactory;    
    
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
        Asprak asprak       = new Asprak();
        asprak.nim_asprak   = Long.parseLong(requestData.get("Nim"));
        asprak.nama         = requestData.get("Nama");

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
