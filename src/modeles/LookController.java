package modeles;

import eriq.flamework.annotations.Controller;
import eriq.flamework.annotations.Singleton;
import eriq.flamework.annotations.URLMapping;
import eriq.flamework.model.ModelRedirect;
import eriq.flamework.model.ModelView;
import eriq.flamework.servlet.ServletEntity;
import veda.godao.DAO;
import handyman.Utils;

@Controller
@Singleton
public class LookController {

  public LookController() {}

  @URLMapping("insertlook.do")
  public ModelRedirect save(ServletEntity entity) throws Exception {
    DAO dao = new DAO("poketra", "localhost", "5432", "eriq", "root", false, 2);
    Look objet = new Look();
    objet.setId(Utils.fromJson(Integer.class, "\"" + entity.getData().get("id") + "\""));

    objet.setNom(Utils.fromJson(String.class, "\"" + entity.getData().get("nom") + "\""));

    objet.setHeuretravail(
        Utils.fromJson(Double.class, "\"" + entity.getData().get("heuretravail") + "\""));

    dao.insertWithoutPrimaryKey(null, objet);
    ModelRedirect model = new ModelRedirect("toinsertlook.do");
    return model;
  }

  @URLMapping("toinsertlook.do")
  public ModelView toSave(ServletEntity entity) throws Exception {
    ModelView model = new ModelView();
    model.setView("pages/layout/layout.jsp");
    model.addItem("viewpage", "toinsert_look.jsp");
    model.addItem("title", "Inserer un look");
    return model;
  }

  @URLMapping("tolistelook.do")
  public ModelView toList(ServletEntity entity) throws Exception {
    ModelView model = new ModelView();
    model.setView("pages/layout/layout.jsp");
    model.addItem("viewpage", "toinsert_look.jsp");
    model.addItem("title", "Inserer un look");
    DAO dao = new DAO("poketra", "localhost", "5432", "eriq", "root", false, 2);
    Look[] objets = dao.select(null, Look.class);
    model.addItem("objets", objets);
    return model;
  }

  @URLMapping("updatelook.do")
  public ModelRedirect update(ServletEntity entity) throws Exception {
    DAO dao = new DAO("poketra", "localhost", "5432", "eriq", "root", false, 2);
    Look where = new Look();
    where.setId(Integer.parseInt(entity.getData().get("id")));
    Look objet = new Look();
    objet.setId(Utils.fromJson(Integer.class, "\"" + entity.getData().get("id") + "\""));

    objet.setNom(Utils.fromJson(String.class, "\"" + entity.getData().get("nom") + "\""));

    objet.setHeuretravail(
        Utils.fromJson(Double.class, "\"" + entity.getData().get("heuretravail") + "\""));

    dao.update(null, objet, where);
    ModelRedirect model = new ModelRedirect("toupdatelook.do");
    return model;
  }

  @URLMapping("toupdatelook.do")
  public ModelView toUpdate(ServletEntity entity) throws Exception {
    ModelView model = new ModelView();
    model.setView("pages/layout/layout.jsp");
    model.addItem("viewpage", "toupdate_look.jsp");
    model.addItem("title", "Modifier un look");
    return model;
  }

  @URLMapping("deletelook.do")
  public ModelRedirect delete(ServletEntity entity) throws Exception {
    DAO dao = new DAO("poketra", "localhost", "5432", "eriq", "root", false, 2);
    Look where = new Look();
    where.setId(Integer.parseInt(entity.getData().get("id")));
    dao.delete(null, where);
    ModelRedirect model = new ModelRedirect("toinsertlook.do");
    return model;
  }
}
