package controllers;

import models.*;
import forms.*;

import play.Logger;
import play.mvc.*;
import play.data.*;

import javax.inject.*;
import org.mindrot.jbcrypt.*;



public class FreeController extends Controller{
    @Inject FormFactory formFactory;    
    
    public Result cekLogin(){
        String user = session("email");   
        if(user == null)
            return redirect(routes.FreeController.index());
        return ok(user);
        

    }
    public Result index() {
        Form<LoginForm> loginForm = formFactory.form(LoginForm.class);
        //Object user = ctx().args.get("user");        
        return ok(views.html.user.render(loginForm));
        
        //return ok(Json.toJson(user));
    }

    public Result dashboard() {
        Form<LoginForm> loginForm = formFactory.form(LoginForm.class);
        return ok(views.html.index.render(loginForm));
    }

    
    public Result doLogin(){        
        DynamicForm  requestData = formFactory.form().bindFromRequest();
        AuthorisedUser as= AuthorisedUser.findByUserName(requestData.get("nim"));
        //return (Result) ok(as.permissions.get(0).value);
        Logger.info("BCrypt Check password", String.valueOf(as.password));
        if(BCrypt.checkpw(requestData.get("password"), as.password)){
//if(as.password.equals(requestData.get("password"))){
            session("userid", String.valueOf(as.id));
            session("email", requestData.get("nim"));
            session("akses", as.roles.get(0).getName());
            session("loggedin", String.valueOf(true));
        }
        if(as.roles.get(0).getName().equals("Dosen"))
            return redirect(routes.ModulController.index());
        else
            return redirect(routes.HomeController.daftarAsprak());

    }

    public Result logout() {
        session().remove("email");
        session().remove("akses");
        return redirect(routes.FreeController.index());
    }

    
}
