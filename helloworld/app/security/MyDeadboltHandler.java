package security;

import be.objectify.deadbolt.java.AbstractDeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import be.objectify.deadbolt.java.ExecutionContextProvider;
import be.objectify.deadbolt.java.models.Subject;
import controllers.routes;
import models.AuthorisedUser;
import play.mvc.Http;
import play.mvc.Result;
import views.html.accessFailed;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class MyDeadboltHandler extends AbstractDeadboltHandler
{
    public MyDeadboltHandler(ExecutionContextProvider ecProvider)
    {
        super(ecProvider);
    }

    public CompletionStage<Optional<Result>> beforeAuthCheck(final Http.Context context)
    {
        // returning null means that everything is OK.  Return a real result if you want a redirect to a login page or
        // somewhere else
        return CompletableFuture.completedFuture(Optional.empty());
    }

    public CompletionStage<Optional<? extends Subject>> getSubject(final Http.Context context)
    {
        // in a real application, the user name would probably be in the session following a login process
        String user = context.session().get("email");
        return CompletableFuture.supplyAsync(() -> Optional.ofNullable(AuthorisedUser.findByUserName(user)),
                                             (Executor) executionContextProvider.get());
    }

    public CompletionStage<Optional<DynamicResourceHandler>> getDynamicResourceHandler(final Http.Context context)
    {
        return CompletableFuture.completedFuture(Optional.of(new MyDynamicResourceHandler()));
    }

    @Override
    public CompletionStage<Result> onAuthFailure(final Http.Context context,
                                                 final Optional<String> content)
    {
        // you can return any result from here - forbidden, etc
//        String user = session("email");
//        return redirect(controllers.routes.FreeController.index());
        return CompletableFuture.completedFuture(redirect(routes.FreeController.index()));
//        return CompletableFuture.completedFuture(ok(accessFailed.render()));
    }
}
