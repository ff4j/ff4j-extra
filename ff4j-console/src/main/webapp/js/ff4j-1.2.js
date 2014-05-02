
/*
 * Main definition of the WebClient JavaScript.
 */
Ff4j.WebClient = function(url, authenticated, apiKey) {
	this.url = url;
	this.authenticated = authenticated;
	this.apiKey = apiKey;
};


Ff4j.WebClient.Feature = {
	enable : function(uid) {
		alert(uid);
	},
	disable : function(uid) {
		alert(uid);
	}
};



