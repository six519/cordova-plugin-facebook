"use strict";

var exec = require("cordova/exec");

var facebookCordovaPlugin = {
	login: function(sc, ec) {
		exec(sc, ec, "FacebookCordovaPlugin", "login", []);
	}
};

module.exports = facebookCordovaPlugin;