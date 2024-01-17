<%@page import="modeles.*" %>
<%
    Salaire[] liste=(Salaire[])request.getAttribute("objets");
%>
<div class="container-fluid pt-4 px-4">
    <div class="row g-4">
        <div class="col-sm-12 col-xl-6">
            <div class="bg-secondary rounded h-100 p-4">
                <h6 class="mb-4">Liste de Salaire</h6>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col"> id</th>

<th scope="col"> datesalaire</th>

<th scope="col"> valeur</th>


                        </tr>
                    </thead>
                    <tbody>
                        <% for(Salaire o:liste){ %>
                            <tr>
                                <td><%= o.getId() %></td>

<td><%= o.getDatesalaire() %></td>

<td><%= o.getValeur() %></td>


                                <td>
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modifiermodal">
                                        <p><i class="bi bi-pencil"></i>Modifier</p>
                                    </button>
                                    <div class="modal fade" id="modifiermodal" tabindex="-1" aria-labelledby="modifiermodalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                        <form action="/Poketra/updatesalaire.do" method="put">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="modifiermodalLabel">Modifier un Salaire</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <input type="hidden" name="id" value="<%= o.getId() %>">
                                            <div class="mb-3">
    <label for="datesalaireinput" class="form-label">Datesalaire</label>
    <input type="datetime-local" class="form-control" id="datesalaireinput"
        aria-describedby="datesalairehelp" name="datesalaire" value="<%= o.getDatesalaire() %>">
</div>

<div class="mb-3">
    <label for="valeurinput" class="form-label">Valeur</label>
    <input type="number" step="0.01" class="form-control" id="valeurinput"
        aria-describedby="valeurhelp" name="valeur" value="<%= o.getValeur() %>">
</div>


                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-primary" data-bs-dismiss="modal">Valider</button>
                                        </div>
                                        </form>
                                        </div>
                                    </div>
                                    </div>
                                </td>
                                <td><form action="/Poketra/deletesalaire.do" method="delete">
                                    <input type="hidden" name="id" value="<%= o.getId() %>">
                                    <button type="submit" class="btn btn-danger">
                                        <p><i class="bi bi-trash"></i>Supprimer</p>
                                    </button>
                                </td></form>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- Button modal ajout -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#ajoutmodal">
            <p><i class="bi bi-plus"></i>Ajouter</p>
        </button>

        <!-- Modal Ajout -->
        <div class="modal fade" id="ajoutmodal" tabindex="-1" aria-labelledby="ajoutmodalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
            <form action="/Poketra/insertsalaire.do" method="post">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="ajoutmodalLabel">Ajouter un Salaire</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3">
    <label for="datesalaireinput" class="form-label">Datesalaire</label>
    <input type="datetime-local" class="form-control" id="datesalaireinput"
        aria-describedby="datesalairehelp" name="datesalaire">
</div>

<div class="mb-3">
    <label for="valeurinput" class="form-label">Valeur</label>
    <input type="number" step="0.01" class="form-control" id="valeurinput"
        aria-describedby="valeurhelp" name="valeur">
</div>


            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary" data-bs-dismiss="modal">Valider</button>
            </div>
            </form>
            </div>
        </div>
        </div>
    </div>
</div>
