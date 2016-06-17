/**
 * 
 */

var citrya;

(function(citrya) {	
	var jqueryLoaded = false;
	var requests = [];
	
	var template = /{([^}]+)}/g;	
	
	var request = (function() {
		function request(url, payload, callback) {
			this.url = url;
			this.payload = payload;
			this.callbackl = callback;
		}		
		return request;
	})();
	
	function makeRestCall(url, payload, callback) {
		var templateNames = [];
		var match;
		var templateName;
		var targetUrl = url; 
		
		while(match = template.exec(url)){
			templateName = match[1];
			if(payload.templateName) {
				targetUrl = targetUrl.replace('{'+templateName+'}', payload[templateName]);
			}
		}
		
		var req = new request(url,payload, callback);
		if(jqueryLoaded) {
			callRestApi(req);
		}else {
			requests.push(new request(url, payload, callback));
		}
	}	
	
	function callRestApi(request) {		
	    $.ajax({
	        url: request.url,
	        type : request.payload.type,
	        data : request.payload.data,
	        complete : request.complete,
	        datatype: 'json'
	    });	
	}
	
	function onLoaded() {
		for(var i = 0; i < requests.length; i++){
			var request = requests.shift();
			makeRestCall(request);
		}
	}
	
	(function checkAndLoadJquery() {
		if(!window.jQuery || !$) {
		    // Load the script
		    var script = document.createElement("SCRIPT");
		    script.src = 'https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js';
		    script.type = 'text/javascript';
		    document.getElementsByTagName("head")[0].appendChild(script);
	
		    // Poll for jQuery to come into existance
		    var checkReady = function(callback) {
		        if (window.jQuery) {
		            callback(jQuery);
		        }
		        else {
		            window.setTimeout(function() { checkReady(callback); }, 100);
		        }
		    };
	
		    // Start polling...
		    checkReady(function($) {
		       jqueryLoaded = true;
		       onLoaded();
		    });
		}else {
			jqueryLoaded = true;
		}
	})();
	
	
	citrya.rest = { call : makeRestCall,
					request : request
				};
	
})(citrya || (citrya = {}));


