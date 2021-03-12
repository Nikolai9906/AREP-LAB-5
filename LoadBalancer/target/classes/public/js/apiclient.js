var apiclient = (function(){

    return {
        loadTableMessages: function(callback) {
            var promise = $.get({
        		url: "/mensajes"
            });
        	promise.then(function(data){
        		callback(null, JSON.parse(data));
        	}, function(error) {
        		callback(error, null);
        	});
		},
		addMensaje: function(jsonMessage, callback) {

			var promise = $.post({
        		url: "/addMensaje",
        		data: JSON.stringify(jsonMessage)
            });
        	promise.then(function(data){
        		callback(null, JSON.parse(data));
        	}, function(error) {
        		callback(error, null);
			});
		}
    }
})();