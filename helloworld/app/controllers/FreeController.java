package controllers;

import models.*;
import forms.*;

import play.mvc.*;
import play.data.*;

import javax.inject.*;




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
        Asprak asprak            = Asprak.find.byId(Long.parseLong(requestData.get("nim")));        
        session("email", requestData.get("nim"));
        session("akses", "asprak");

        return redirect(routes.FreeController.dashboard());

    }

    
}
