var client = (function(){

    return {
        captureData: function(info, callback) {
            var promise = $.post({
        		url: "/stat",
        		data: JSON.stringify(info),
        		contentType: "application/json"
            });
        	promise.then(function(data){
        		callback(null, JSON.parse(data));
        	}, function(error) {
        		callback(error, null);
        	});
        }
    }
})();