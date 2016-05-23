function listeActivites() {

    $.ajax({
        url: './ControlerServlet',
        type: 'POST',
        data: {
            todo: 'listeActivite'
        },
        dataType: 'json'
    })
        .always(function () {
            $('#listeActivites').html('chargement');
        })

        .done(function (data) {
            var activites = data.activites;
            var contenuHtml = '';
            var i;
            var choixActivite = '<select id="act" required>';

            for (i = 0; i < activites.length; i++) {

                contenuHtml += ("<div class='col-sm-2 typeActivite'> <div class='carre'>  <img src='img/carre.gif' alt='' title='"
                    + "Nombre de joueurs: " + activites[i].nbParticipants + "&#013;Par équipe: " + activites[i].parEquipe + "' style='width:50px;height:50px;'/> </div>"
                    + activites[i].denomination + "</div>");

                choixActivite += ('<option value ="' + activites[i].id + '">' + activites[i].denomination + '</option>');
            }
            $('#listeActivites').html(contenuHtml);
            choixActivite += '</select>';
            $('#choixAct').html(choixActivite);
           
        })
        .fail(function () {
            $('#listeActivites').html('ERREUR de chargement');
        });
}

function Accueil() {

    // // Get the form.
    // var form = $('#formCo');
    // 
    // // Serialize the form data.
    // var formData = $(form).serialize();
    
    $.ajax({
        url: './ControlerServlet',
        type: 'POST',
        dataType: 'json'
    })
        .always(function () {
            //$('#Connexion').html('chargement');
        })

        .done(function (data) {
            var adherent = data.adherent;
            var contenuHtml = '';

            if (adherent.existe) {
                $("#formCo").setAttr("style");
                document.getElementById("formCo").setAttribute("style", "visibility:hidden"); 
                
            }
            else {
                
                //contenuHtml += '<form> <input id="datepicker" class="hasDatepicker"/> </form>';
                //contenuHtml += '<script> $("#datepicker").datepicker(); </script>';
                //contenuHtml += '<font color="red">Erreur de connexion</font>';
               // $('#ErreurCo').html(contenuHtml);
            }

        })
        .fail(function () {
            $('#Connexion').html('ERREUR de chargement');
        });
}

function ConnexionAdherent() {

    // // Get the form.
    // var form = $('#formCo');
    // 
    // // Serialize the form data.
    // var formData = $(form).serialize();
    
    $.ajax({
        url: './ControlerServlet',
        type: 'POST',
        data: {
            todo: 'ConnexionAdherent',
            mail: $("#mail")[0].value
        },
        dataType: 'json'
    })
        .always(function () {
            //$('#Connexion').html('chargement');
        })

        .done(function (data) {
            var adherent = data.adherent;
            var contenuHtml = '';

            if (adherent.existe) {
                $("#datepicker").removeAttr("style");
                $("#timepick").removeAttr("style");
                $("#demande").removeAttr("style");
                $("#DemandeBouton").removeAttr("style");
                $("#choixAct").removeAttr("style");
                $("#signOut").removeAttr("style");
                
                //contenuHtml += '<form><input id="datepicker"  class="form-control btn-block" /></form><form class="tipePick"><select type="text" id="picker" class="form-control btn-block" ></form>';
                $('#ConnexionReussie').html(contenuHtml);
            }
            else {
                
                //contenuHtml += '<form> <input id="datepicker" class="hasDatepicker"/> </form>';
                //contenuHtml += '<script> $("#datepicker").datepicker(); </script>';
                contenuHtml += '<font color="red">Erreur de connexion</font>';
                $('#ErreurCo').html(contenuHtml);
            }

        })
        .fail(function () {
            $('#Connexion').html('ERREUR de chargement');
        });
}

function ConnexionResponsable() {

    // // Get the form.
    // var form = $('#formCo');
    // 
    // // Serialize the form data.
    // var formData = $(form).serialize();
    var div = document.getElementById("ConnexionReussie");
    $.ajax({
        url: './ControlerServlet',
        type: 'POST',
        data: {
            todo: 'ConnexionResponsable',
            mail: $("#mail")[0].value,
            mdp: $('#mdp')[0].value
        },
        dataType: 'json'
    })
        .always(function () {
            //$('#Connexion').html('chargement');
        })

        .done(function (data) {
            var responsable = data.responsable;
            var contenuHtml = '';
            if (responsable.existe) {
                $('#ConnexionReussie').html(contenuHtml);
                var requete = new XMLHttpRequest();
                requete.open("GET", "AffectationLieux.html", false);
                requete.send(null);
                $('#ConnexionReussie').html(requete.responseText);

            }
            else {

                $('#ConnexionReussie').html(contenuHtml);
            }

        })
        .fail(function () {
            $('#Connexion').html('ERREUR de chargement');
        });
}

function InscriptionAdherent() {
    // Get the form.
    // var form = $('#formCo');
    // 
    // Serialize the form data.
    // var formData = $(form).serialize();
    
    $.ajax({
        url: './ControlerServlet',
        type: 'POST',
        data: {
            todo: 'InscriptionAdherent',
            mail: $("#mail")[0].value,
            adresse: $("#adresse")[0].value,
            nom: $("#nom")[0].value,
            prenom: $("#prenom")[0].value
        },
        dataType: 'json'
    })
        .always(function () {
            $('#Connexion').html('chargement');
        })

        .done(function (data) {
            var adherent = data.adherent;
            if (adherent.reussite) {
                document.location.href = "Accueil.html";
                
                $('#inscriptionReussie').html('Inscription effectuée');
                // TODO : afficher sur accueil cool bro
                //javascript:q=(document.location.href);void(open('Accueil.html','_self','resizable,location,menubar,toolbar,scrollbars,status'));
            }
            else {
                $('#Connexion').html('Email déjà utilisée');
            }

        })
        .fail(function () {
            $('#Connexion').html('ERREUR de chargement');
        });
}

function creerDemande() {
    
    // Get the form.
    // var form = $('#formCo');
    // 
    // Serialize the form data.
    // var formData = $(form).serialize();
    
    $.ajax({
        url: './ControlerServlet',
        type: 'POST',
        data: {
            todo: 'Demande',
            heure: $("#timepick")[0].value,
            date: $("#datepicker")[0].value,
            activite: $("#act")[0].value
        },
        dataType: 'json'
    })
        
        .done(function (data) {
            
             //document.location.href = "Accueil.html";
             var demande = data.adherent;
             var contenuHTML = '';
             if (demande.reussite) {
                contenuHTML = '<p>Demande effectuée</p>';
             }
             else{
                 contenuHTML = '<p>Demande non effectuée, veuillez choisir une date correcte</p>';
             }
             
             //contenuHTML += '<form action ="javascript:ConnexionAdherent()"> <button type="submit" class="btn btn-default btn-blockDemande""> Effectuer demande </button> </form>';
             //contenuHTML += '<form action ="javascript:Deconnexion()"> <button type="submit" class="btn btn-default btn-blockDemande""> Deconnexion </button> </form>';
                // TODO : afficher sur accueil cool bro
                //javascript:q=(document.location.href);void(open('Accueil.html','_self','resizable,location,menubar,toolbar,scrollbars,status'));
             $('#demandeFaite').html(contenuHTML);

        })
        .fail(function () {
            $('#Connexion').html('ERREUR de chargement');
        });
}

function Deconnexion() {

    $.ajax({
        url: './ControlerServlet',
        type: 'POST',
        data: {
            todo: 'Deconnexion'
        },
    })

        .done(function () {
            document.location.href = "Accueil.html";
        });


}

function listeEvenementsPlanifies() {

    $.ajax({
        url: './ControlerServlet',
        type: 'POST',
        data: {
            todo: 'listeNonPlanifies'
        },
        dataType: 'json'
    })

        .done(function (data) {
            var evenements = data.events;

            for (i = 0; i < evenements.length; i++) {

                var texte = evenements[i].id + " " + evenements[i].date + " " + evenements[i].nbParticipants;
                document.getElementById("listePlanifies").option[i].value = texte;
                
            }
           
        })
}

function listeLieux() {

    $.ajax({
        url: './ControlerServlet',
        type: 'POST',
        data: {
            todo: 'listeLieux'
        },
        dataType: 'json'
    })

        .done(function (data) {
            var lieux = data.lieux;

            for (i = 0; i < lieux.length; i++) {

                document.getElementById("lieux").option[i].value = lieux.id + " " + lieux.denomination;
                
            }
           
        })
}

function affecte() {

    $.ajax({
        url: './ControlerServlet',
        type: 'POST',
        data: {
            todo: 'listeLieux',
            lieu: document.getElementById("lieux").option[document.getElementById("lieux").selectedIndex].value(),
            event: document.getElementById("listePlanifies").option[document.getElementById("listePlanifies").selectedIndex].value()
        },
        dataType: 'json'
    })

        .done(function (data) {
            alert("Evènement traité");
           
        })
}

































function testConnexionClient() {
    
    // Get the form.
    // var form = $('#formCo');
    // 
    // Serialize the form data.
    // var formData = $(form).serialize();
    
    $.ajax({
        url: './ControlerServlet',
        type: 'POST',
        data: {
            todo: 'TestCo',
        },
    })
        
        .done(function (data) {
            
             //document.location.href = "Accueil.html";
             var demande = data.adherent;
             var contenuHTML = '';
             if (demande.reussite) {
                contenuHTML = '<p>Demande effectuée</p>';
             }
             else{
                 contenuHTML = '<p>Demande non effectuée, veuillez choisir une date correcte</p>';
             }
             
             //contenuHTML += '<form action ="javascript:ConnexionAdherent()"> <button type="submit" class="btn btn-default btn-blockDemande""> Effectuer demande </button> </form>';
             //contenuHTML += '<form action ="javascript:Deconnexion()"> <button type="submit" class="btn btn-default btn-blockDemande""> Deconnexion </button> </form>';
                // TODO : afficher sur accueil cool bro
                //javascript:q=(document.location.href);void(open('Accueil.html','_self','resizable,location,menubar,toolbar,scrollbars,status'));
             $('#demandeFaite').html(contenuHTML);

        })
        .fail(function () {
            $('#Connexion').html('ERREUR de chargement');
        });
}