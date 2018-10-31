package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import models.Praktikan;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

@Restrict({@Group("Laboran")})
public class PraktikanController extends Controller {
    @Inject
    FormFactory formFactory;
    @Inject Praktikan praktikan;
    public Result index(){
        return ok(views.html.praktikan.list.render(praktikan.find.all()));

    }
    public Result form(){
        return ok(Json.toJson("Hello"));

    }
    public Result tambah(){
        return ok(Json.toJson("Hello"));

    }
    public Result edit(Long id){
        return ok(Json.toJson("Hello"));

    }
    public Result update(Long id){
        return ok(Json.toJson("Hello"));

    }
    public Result hapus(Long id){
        return ok(Json.toJson("Hello"));
    }
}
