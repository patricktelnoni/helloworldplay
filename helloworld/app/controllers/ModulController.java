package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import models.Modul;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import play.mvc.With;

@With(Base.class)
@Restrict({@Group("Dosen")})
public class ModulController extends Controller {

    public Result index(){
        return ok(views.html.modul.listmodul.render(Modul.find.all()));

    }
    public Result formModul(){
        return ok(Json.toJson("Hello"));

    }
    public Result tambahModul(){
        return ok(Json.toJson("Hello"));

    }
    public Result editModul(Long id){
        return ok(Json.toJson("Hello"));

    }
    public Result updateModul(Long id){
        return ok(Json.toJson("Hello"));

    }
    public Result hapusModul(Long id){
        return ok(Json.toJson("Hello"));
    }

}
