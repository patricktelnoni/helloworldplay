package controllers;

import be.objectify.deadbolt.java.actions.SubjectPresent;
import models.AuthorisedUser;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import org.mindrot.jbcrypt.*;

import javax.inject.Inject;

@SubjectPresent
public class UserController extends Controller {
    @Inject
    FormFactory formFactory;
    public Result changePassword(){
        Form<AuthorisedUser> PasswordForm = formFactory.form(AuthorisedUser.class);
        return ok(
                views.html.gantipassword.render(PasswordForm)
        );
    }

    public Result doChangePassword(){
        DynamicForm requestData     = formFactory.form().bindFromRequest();
        AuthorisedUser user         = new AuthorisedUser();
        String hashed               = BCrypt.hashpw(requestData.get("password"), BCrypt.gensalt());
        user.id                     = Long.parseLong(session().get("userid"));
        user.password               = hashed;
        user.update();
        return redirect(routes.DosenController.daftarDosen());
    }
}
