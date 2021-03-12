var app = (function () {

    var tableConstructor = function(error, data) {
        if (error != null) {
            alert("Ocurrio un error al cargar los datos");
            return;
        }
        var stringTable = table(data);
        $("#idTable").html(stringTable);
    }

    var table = function(dataTable) {
        console.log("--------------------------------------------");
        console.log(dataTable);
		var tabla = "<center> <table class='table table-bordered table-dark' style = 'width:50%; text-align: center;'" +
						"<thead>" +
							"<tr>" +
								"<th scope='col'> Mensaje </th>" +
								"<th scope='col'> Fecha </th>" +
							"</tr>" +
						"</thead>" +
                        "<tbody>";
            dataTable.forEach(function(mensaje) {
                tabla += "<tr>" +
                            "<td>" + mensaje.mensaje + "</td>" +
                            "<td>" + mensaje.fecha + "</td>" +
                        "</tr>";
            });

        tabla += "</tbody> </table> </center>";
        return tabla;
    }

    var createMessage = function() {
        var mensaje = $("#idMessage").val();
        if (mensaje == "") {
            alert("El campo es obligatorio!");
            return;
        }

        var jsonMenssage = {"mensaje": mensaje, "fecha": "fecha hoy"};
        console.log(jsonMenssage);
        console.log(JSON.stringify(jsonMenssage));
        flag = false;
        apiclient.addMensaje(jsonMenssage, showAll);
        if (flag == true) return;
        showAll(null, "");
    }

    var flag;
    var showAll = function(error, datos) {
        if(error != null){
            alert("Error, verifique que la entrada tiene el formato establecido.");
            flag = true;
            return;
        }
        apiclient.loadTableMessages(tableConstructor);
        refresAll();
    }

    var refresAll = function() {
        $("#idMessage").val("");
    }


    return {
        loadTableMessages: function() {
            apiclient.loadTableMessages(tableConstructor);
        },
        addMensaje: function() {
            createMessage();
        }
    }
})();