"use strict";

var exec = require("cordova/exec");

var facebookCordovaPlugin = {
	login: function(sc, ec, permissions) {
		exec(sc, ec, "FacebookCordovaPlugin", "login", permissions);
	},
	getAccessToken: function(sc, ec) {
		exec(sc, ec, "FacebookCordovaPlugin", "get_access_token", []);	
	}
};

module.exports = facebookCordovaPlugin;