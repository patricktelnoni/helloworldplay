package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import com.fasterxml.jackson.databind.JsonNode;
import io.ebean.Ebean;
import io.ebean.SqlUpdate;
import models.Kelas;
import models.Praktikan;

import org.apache.poi.ss.usermodel.*;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;

@Restrict({@Group("Laboran")})
public class PraktikanController extends Controller {
    @Inject FormFactory formFactory;
    @Inject Kelas kelas;
    @Inject Praktikan praktikan;
    public Result index(Integer page, String filter){
        return ok(views.html.praktikan.list.render(praktikan.praktikanList(page, filter),
                                                    filter));

    }
    public Result cariList(String filter){
        return ok(Json.toJson(praktikan.praktikanListJson(filter)));

    }
    public Result form(){
//        ValuePaginator paginator = new ValuePaginator(realList);
        Form<Praktikan> praktikanForm = formFactory.form(Praktikan.class);
        return ok(
                views.html.praktikan.tambah.render(kelas.find.all(), praktikanForm)
        );

    }
    public Result upload(){
        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        DynamicForm requestData    = formFactory.form().bindFromRequest();
        Http.MultipartFormData.FilePart<File> filePraktikan = body.getFile("praktikan");
        String idkelas = requestData.get("kelas");

        //return ok("Kosongin dulu");

        if (filePraktikan != null) {
            praktikan.find.query().where().eq("id_kelas", idkelas).delete();
            try {
                Workbook workbook       = WorkbookFactory.create(new FileInputStream(filePraktikan.getFile()));
                Sheet sheet             = workbook.getSheetAt(0);


                for (Row row : sheet) {
                    if(row.getRowNum() > 0 ) {
                        SqlUpdate insert = Ebean.createSqlUpdate("" +
                        "INSERT INTO praktikan (`nim_praktikan`, `nama_praktikan`, `id_kelas`) " +
                        "VALUES (:nim, :nama, :kelas)");
                        insert.setParameter("nim", row.getCell(1).toString());
                        insert.setParameter("nama", row.getCell(2).toString());
                        insert.setParameter("kelas", idkelas);
                        insert.execute();

//                        Logger.debug("=> " + row.getRowNum() + ", " + row.getCell(0).getStringCellValue());
//                        Praktikan dataPraktikan = new Praktikan();
//                        dataPraktikan.setNim_praktikan(Long.parseLong(row.getCell(1).toString()));
//                        dataPraktikan.nama_praktikan= row.getCell(2).toString();
//                        dataPraktikan.setKelas(Kelas.find.ref(Long.parseLong(idkelas)));
//                        dataPraktikan.save();

                    }
                }
                return redirect(routes.PraktikanController.index(1, " "));
            }catch (Exception e){
                return badRequest();
            }
        } else {
            flash("error", "Missing file");
            return badRequest();
        }
    }
    public Result formUpload(){
        Form<Praktikan> praktikanForm = formFactory.form(Praktikan.class);
        return ok(
                views.html.praktikan.upload.render(kelas.find.all(), praktikanForm)
        );

    }
    public Result tambah(){
        DynamicForm  requestData    = formFactory.form().bindFromRequest();

//        praktikan.setNim_praktikan(Long.parseLong(requestData.get("nim_praktikan")));
//        praktikan.nama_praktikan    = requestData.get("nama_praktikan");
//        praktikan.setKelas(Kelas.find.ref(Long.parseLong(requestData.get("kelas"))));
//        praktikan.save();
        String nim = requestData.get("nim_praktikan");
        String nama = requestData.get("nama_praktikan");
        String kelas = requestData.get("kelas");


        SqlUpdate insert = Ebean.createSqlUpdate("" +
                "   INSERT INTO praktikan (`nim_praktikan`, `nama_praktikan`, `id_kelas`) " +
                "VALUES (:nim, :nama, :kelas)");
        insert.setParameter("nim", nim);
        insert.setParameter("nama", nama);
        insert.setParameter("kelas", kelas);
        insert.execute();



        return redirect(routes.PraktikanController.index(1, ""));

    }
    public Result edit(Long id){

        Form<Praktikan> asprakForm              = formFactory.form(Praktikan.class);
        Form<Praktikan> filledPraktikanForm     = asprakForm.fill(praktikan.find.byId(id));
        return ok(
                views.html.praktikan.edit.render(id, kelas.find.all(), filledPraktikanForm)
        );

    }
    public Result update(Long id){
        DynamicForm  requestData = formFactory.form().bindFromRequest();
        praktikan.nim_praktikan     = id;
        praktikan.nama_praktikan    = requestData.get("nama_praktikan");
        praktikan.setKelas(Kelas.find.ref(Long.parseLong(requestData.get("kelas.id_kelas"))));
        praktikan.update();
        return redirect(routes.PraktikanController.index(1, ""));

    }
    public Result hapus(Long id){
        Ebean.beginTransaction();
        praktikan.find.ref(id).delete();
        Ebean.commitTransaction();
        return redirect(routes.PraktikanController.index(1, ""));
    }
}
