<%@page import="modeles.*" %>
<div class="container-fluid pt-4 px-4">
    <div class="row g-4">
        <div class="col-sm-12 col-xl-6">
            <div class="bg-secondary rounded h-100 p-4">
                <h6 class="mb-4">Liste de Look</h6>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col"> id</th>

<th scope="col"> nom</th>

<th scope="col"> heuretravail</th>


                        </tr>
                    </thead>
                    <tbody>
                        <% for(Look o:liste){ %>
                            <tr>
                                <td><%= o.getId() %></td>

<td><%= o.getNom() %></td>

<td><%= o.getHeuretravail() %></td>


                                <td>
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modifiermodal">
                                        <p><i class="bi bi-pencil"></i>Modifier</p>
                                    </button>
                                    <div class="modal fade" id="modifiermodal" tabindex="-1" aria-labelledby="modifiermodalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                        <form action="/Poketra/updateLook.do" method="put">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="modifiermodalLabel">Modifier un Look</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="mb-3">
    <label for="idinput" class="form-label">Id</label>
    <input type="number" step="1.0" class="form-control" id="idinput"
        aria-describedby="idhelp" name="id" value="<%= o.getId() %>">
</div>

<div class="mb-3">
    <label for="nominput" class="form-label">Nom</label>
    <input type="text" class="form-control" id="nominput"
        aria-describedby="nomhelp" name="nom" value="<%= o.getNom() %>">
</div>

<div class="mb-3">
    <label for="heuretravailinput" class="form-label">Heuretravail</label>
    <input type="number" step="0.01" class="form-control" id="heuretravailinput"
        aria-describedby="heuretravailhelp" name="heuretravail" value="<%= o.getHeuretravail() %>">
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
                                <td><form action="/Poketra/deleteLook.do" method="delete">
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
            <form action="/Poketra/insertLook.do" method="post">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="ajoutmodalLabel">Ajouter un Look</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3">
    <label for="idinput" class="form-label">Id</label>
    <input type="number" step="1.0" class="form-control" id="idinput"
        aria-describedby="idhelp" name="id">
    <div id="idhelp" class="form-text">[fieldhelp]
    </div>
</div>

<div class="mb-3">
    <label for="nominput" class="form-label">Nom</label>
    <input type="text" class="form-control" id="nominput"
        aria-describedby="nomhelp" name="nom">
    <div id="nomhelp" class="form-text">[fieldhelp]
    </div>
</div>

<div class="mb-3">
    <label for="heuretravailinput" class="form-label">Heuretravail</label>
    <input type="number" step="0.01" class="form-control" id="heuretravailinput"
        aria-describedby="heuretravailhelp" name="heuretravail">
    <div id="heuretravailhelp" class="form-text">[fieldhelp]
    </div>
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
