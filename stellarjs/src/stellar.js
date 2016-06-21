var citrya;

(function(citrya) {
	var currentUser;
	var stellar = (function() {
		function stellar() {

		}

		stellar.prototype.userLogon = function(userInfo, callback) {
			validateUser(userInfo, function(loggedInUser) {
				console.log(loggedInUser);
				this.currentUser = loggedInUser;
				callback(loggedInUser);
			}.bind(this));
		};

		stellar.prototype.raise = function(event, callback) {
			if (event && this.currentUser != null) {
				event.userId = this.currentUser.id;
				createEvent(event, callback);
			}
		};

		var requestUrls = {
			validateUser : {
				"POST" : {
					url : "http://localhost:8080/stellar/ws/users/validate"
				}
			},
			event : {
				"POST" : {
					url : "http://localhost:8080/stellar/ws/events"
				},
				"GET" : {
					url : "http://localhost:8080/stellar/ws/events"
				}
			},
			user : {
				"GET" : {
					url : "http://localhost:8080/stellar/ws/users/{userId}"
				},
				"POST" : {
					url : "http://localhost:8080/stellar/ws/users"
				},
				"PUT" : {
					url : "http://localhost:8080/stellar/ws/users/{userId}"
				},
				"DELETE" : {
					url : "http://localhost:8080/stellar/ws/users/{userId}"
				}
			}
		}

		function createEvent(event, callback) {
			var url = requestUrls.event.POST;
			url.method = "POST";
			var payload = {};
			payload.data = event;
			makeRestCall(url, payload, callback);
		}

		function validateUser(user, callback) {
			var url = requestUrls.validateUser.POST;
			url.method = "POST";
			var payload = {};
			payload.data = user;
			makeRestCall(url, payload, callback);
		}

		function createUser(user, callback) {
			makeRestCall(requestUrls.event.POST, user, callback);
		}

		function updateUser(user) {
			makeRestCall(requestUrls.event.PUT, user, callback);
		}

		function deleteUser(user) {
			makeRestCall(requestUrls.event.DELETE, user, callback);
		}
		
		function getProduct(variantId) {
			makeRestCall(requestUrls.shopifyGetVariant.GET, {variantId : variantId}, function(variant){
				if(variant.product_id){
					makeRestCall(requestUrls.shopifyGetProduct.GET, {productId : productId}, function(product){
						console.log(product);
					});
				}
			});
		}
		
		return stellar;
	})();

	citrya.stellar = new stellar();

	//Rest code

 //shopifyApi	
	var shopifyURLS = {
		product: {
			"GET" : {
				url : "https://a0893edce80033e4954edb0a648121fe:523b473315fd5f895196256b7673cf7d@delta-systems.myshopify.com/admin/products/{productId}.json?fields=id,images,title"
			}
		},
    variant : {
			"GET" : {
				url:"https://delta-systems.myshopify.com/admin/variants/{variantId}.json"				
			}
		}
	}
	
	function getProduct(variantId) {
		makeRestCall({url: shopifyURLS.variant.GET.url, method : "GET"}, {data: {variantId : variantId}}, function(variant){
			if(variant.product_id){
				makeRestCall({url: requestUrls.product.GET.url, method: "GET"},{data: {productId : productId}}, function(product){
					console.log(product);
				});
			}
		});
	}
	

	var jqueryLoaded = false;
	var requests = [];

	var template = /{([^}]+)}/g;

	var request = (function() {
		function request(url, payload, callback) {
			this.url = url.url;
			this.payload = payload;
			this.complete = callback;
			this.method = url.method;
		}
		return request;
	})();

	function makeRestCall(url, payload, callback) {
		var templateNames = [];
		var match;
		var templateName;
		var targetUrl = url.url;

		while (match = template.exec(targetUrl)) {
			templateName = match[1];
      var replaceWith = payload.data[templateName];
			if (replaceWith) {
				targetUrl = targetUrl.replace('{' + templateName + '}',
						replaceWith);
			}
		}
    
    url.url = targetUrl;
		
    var req = new request(url, payload, callback);
		if (jqueryLoaded) {
			callRestApi(req);
		} else {
			requests.push(new request(url, payload, callback));
		}
	}

	function callRestApi(request) {
		$.ajax({
			url : request.url,
			data : JSON.stringify(request.payload.data),
			error : function(jqXHR, status) {
				console.log("Failure");
				console.log(jqXHR);
				console.log(status);
			},
			contentType : "application/json",
			datatype : 'json',
			type : request.method
		}).then(function(data) {
			request.complete(data);
		});
	}

	function onLoaded() {
		for (var i = 0; i < requests.length; i++) {
			var request = requests.shift();
			makeRestCall(request);
		}
	}

	(function checkAndLoadJquery() {
		if (!window.jQuery || !$) {
			// Load the script
			var script = document.createElement("SCRIPT");
			script.src = 'https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js';
			script.type = 'text/javascript';
			document.getElementsByTagName("head")[0].appendChild(script);

			// Poll for jQuery to come into existance
			var checkReady = function(callback) {
				if (window.jQuery) {
					callback(jQuery);
				} else {
					window.setTimeout(function() {
						checkReady(callback);
					}, 100);
				}
			};

			// Start polling...
			checkReady(function($) {
				jqueryLoaded = true;
				onLoaded();
			});
		} else {
			jqueryLoaded = true;
		}
	})();

	function onLoaded() {		
		for (var i = 0; i < requests.length; i++) {
			var request = requests.shift();
			makeRestCall(request);
		}
	}

	(function checkAndLoadJquery() {		
		if (!window.jQuery || !$) {
			// Load the script
			var script = document.createElement("SCRIPT");
			script.src = 'https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js';
			script.type = 'text/javascript';
			document.getElementsByTagName("head")[0].appendChild(script);

			// Poll for jQuery to come into existance
			var checkReady = function(callback) {
				if (window.jQuery) {
					callback(jQuery);
				} else {
					window.setTimeout(function() {
						checkReady(callback);
					}, 100);
				}
			};

			// Start polling...
			checkReady(function($) {
				jqueryLoaded = true;
				onLoaded();
			});
		} else {
			jqueryLoaded = true;
		}
	})();

	function addLoadEvent(func) {
		var oldonload = window.onload;
		if (typeof window.onload != 'function') {
			window.onload = func;
		} else {
			window.onload = function() {
				if (oldonload) {
					oldonload();
				}
				func();
			}
		}
	}

	addLoadEvent(function() {
		//Register for click events

		//1. Add to cart
		var addToCart = document.getElementById("addToCart");
		if (addToCart) {
			var addToCart = document.getElementById("addToCart");
			if (addToCart) {
				addToCart.addEventListener('click', function(param, param1, param2) {
          debugger;
					console.log("add to cart clicked");
					var variantId;
					var productId;
					var select = document.getElementById("productSelect")
					if(select){
						var selected = select.selectedOptions;
						if(selected && selected.length > 0) {
							variantId = selected[0].value;
              getProduct(variantId);
						}
					}
					
				})
			}
		}

		//2. Register
		var buttons  = document.getElementsByClassName("btn");
		for(var i = 0; i < buttons.length; i++){
			var callback = null;
			switch(buttons[i].value) {
				case "create":
					callback  = function(param1, param2, param3) {
						console.log("Create clicked");
					}
					break;
				case "Sign In":
					callback  = function(param1, param2, param3) {
						console.log("Sign In clicked");
					}
					break;
				default:
					break;
			}
			if(callback) {
				buttons[i].addEventListener('click', callback);
			}
		} 
	});
  
  (function() {
    	var addToCart = document.getElementById("addToCart");
		if (addToCart) {
			var addToCart = document.getElementById("addToCart");
			if (addToCart) {
				addToCart.addEventListener('click', function(param, param1, param2) {
          debugger;
					console.log("add to cart clicked");
					var variantId;
					var productId;
					var select = document.getElementById("productSelect")
					if(select){
						var selected = select.selectedOptions;
						if(selected && selected.length > 0) {
							variantId = selected[0].value;
              getProduct(variantId);
						}
					}
					
				})
			}
		}
  })();
})(citrya || (citrya = {}));