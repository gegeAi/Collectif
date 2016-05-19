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


            for (i = 0; i < activites.length; i++) {

                contenuHtml += ("<div class='col-sm-2 typeActivite'> <div class='carre'>  <img src='img/carre.gif' alt='' title='"
                    + "Nombre de joueurs: " + activites[i].nbParticipants + "&#013;Par équipe: " + activites[i].parEquipe + "' style='width:50px;height:50px;'/> </div>"
                    + activites[i].denomination + "</div>");
            }
            $('#listeActivites').html(contenuHtml);


        })
        .fail(function () {
            $('#listeActivites').html('ERREUR de chargement');
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

