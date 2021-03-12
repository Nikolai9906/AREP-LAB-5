var app = (function () {

	var numbers = function (error, info){
		if (error != null){
			alert("Datos erróneos");
			return;
		}
		$("#mean").text(info.mean);
		$("#stddev").text(info.stddev);
	}

	var send = function (){
		var strings = $("#capture").val();
		var listNumbers= strings.split(",");
		client.captureData(listNumbers, numbers);
	}


    return {
		captureData : function(){
			send();
		}
    }
})();