package controllers;

import models.Modul;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class SoalController extends Controller {
    @Inject
    FormFactory formFactory;
    @Inject
    Modul modul;

    public Result index(){
        return ok(views.html.soal.list.render(modul.find.all()));
    }

}
