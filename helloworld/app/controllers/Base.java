package controllers;

import forms.*;
import models.*;
import play.*;
import play.mvc.*;
import play.Logger;
import play.data.*;
import play.libs.concurrent.HttpExecutionContext;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.inject.*;
import java.util.List;
import play.mvc.Security.*;
import java.util.*;
import java.lang.annotation.*;
import play.cache.*;
import views.html.index;
import views.html.restricted;
import be.objectify.deadbolt.java.actions.SubjectPresent;
import be.objectify.deadbolt.java.actions.Unrestricted;


public class Base extends Action.Simple{
   
    @Inject FormFactory formFactory;
    @Inject HttpExecutionContext ec;   
    
    @Override
    public CompletionStage<Result> call(Http.Context ctx) {
        
        // return CompletableFuture.supplyAsync(() -> AuthorisedUser.findByUserName("Laxus"))
        //                         .thenApplyAsync(user -> ok(index.render(user)),
        //                                         ec.current());
        ctx.args.put("user", "Suanggi");
        Logger.info("Calling action for {}", ctx);
        return CompletableFuture.supplyAsync(() -> AuthorisedUser.findByUserName("Laxus"))
                                .thenApplyAsync(user -> ok(restricted.render(user)),
                                                ec.current());
    }

}
