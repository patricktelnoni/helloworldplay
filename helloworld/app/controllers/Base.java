package controllers;

import models.AuthorisedUser;
import models.Dosen;
import play.mvc.*;

import play.data.*;
import play.libs.concurrent.HttpExecutionContext;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.inject.*;

import views.html.restricted;


public class Base extends Action.Simple{
   

    @Inject HttpExecutionContext ec;
    private String userid;
    
    @Override
    public CompletionStage<Result> call(Http.Context ctx) {
        String connected = ctx.session().get("email");
        this.userid = ctx.session().get("userid");
        if(connected == null)
            return CompletableFuture.supplyAsync(() -> checkLogin(connected))
                                .thenApplyAsync(user -> ok(restricted.render(user)),
                                                ec.current());
        else
            return delegate.call(ctx);
    }
    public boolean checkLogin(String connected){
        if(connected != null)
            return true;

        return false;
    }




}
