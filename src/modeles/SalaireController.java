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
public class SalaireController {

  public SalaireController() {}

  @URLMapping("insertsalaire.do")
  public ModelRedirect save(ServletEntity entity) throws Exception {
    DAO dao = new DAO("poketra", "localhost", "5432", "eriq", "root", false, 2);
    Salaire objet = new Salaire();
    objet.setId(Utils.fromJson(Integer.class, "\"" + entity.getData().get("id") + "\""));

    objet.setDatesalaire(
        Utils.fromJson(
            java.time.LocalDateTime.class, "\"" + entity.getData().get("datesalaire") + "\""));

    objet.setValeur(Utils.fromJson(Double.class, "\"" + entity.getData().get("valeur") + "\""));

    dao.insertWithoutPrimaryKey(null, objet);
    ModelRedirect model = new ModelRedirect("tocrudsalaire.do");
    return model;
  }

  @URLMapping("tocrudsalaire.do")
  public ModelView toCrud(ServletEntity entity) throws Exception {
    ModelView model = new ModelView();
    model.setView("pages/layout/layout.jsp");
    model.addItem("viewpage", "salaire.jsp");
    model.addItem("title", "Salaire");
    DAO dao = new DAO("poketra", "localhost", "5432", "eriq", "root", false, 2);
    Salaire[] objets = dao.select(null, Salaire.class);
    model.addItem("objets", objets);
    return model;
  }

  @URLMapping("updatesalaire.do")
  public ModelRedirect update(ServletEntity entity) throws Exception {
    DAO dao = new DAO("poketra", "localhost", "5432", "eriq", "root", false, 2);
    Salaire where = new Salaire();
    where.setId(Integer.parseInt(entity.getData().get("id")));
    Salaire objet = new Salaire();
    objet.setId(Utils.fromJson(Integer.class, "\"" + entity.getData().get("id") + "\""));

    objet.setDatesalaire(
        Utils.fromJson(
            java.time.LocalDateTime.class, "\"" + entity.getData().get("datesalaire") + "\""));

    objet.setValeur(Utils.fromJson(Double.class, "\"" + entity.getData().get("valeur") + "\""));

    dao.update(null, objet, where);
    ModelRedirect model = new ModelRedirect("tocrudsalaire.do");
    return model;
  }

  @URLMapping("deletesalaire.do")
  public ModelRedirect delete(ServletEntity entity) throws Exception {
    DAO dao = new DAO("poketra", "localhost", "5432", "eriq", "root", false, 2);
    Salaire where = new Salaire();
    where.setId(Integer.parseInt(entity.getData().get("id")));
    dao.delete(null, where);
    ModelRedirect model = new ModelRedirect("tocrudsalaire.do");
    return model;
  }
}
