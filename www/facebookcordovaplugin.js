"use strict";

var exec = require("cordova/exec");

var facebookCordovaPlugin = {
	login: function(scopes, sc, ec) {
		exec(sc, ec, "FacebookCordovaPlugin", "login", scopes);
	},
	getAccessToken: function(sc, ec) {
		exec(sc, ec, "FacebookCordovaPlugin", "get_access_token", []);	
	},
	logout: function(sc, ec) {
		exec(sc, ec, "FacebookCordovaPlugin", "logout", []);	
	}
};

module.exports = facebookCordovaPlugin;