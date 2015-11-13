"use strict";

var exec = require("cordova/exec");

var facebookCordovaPlugin = {
	login: function(sc, ec, permissions) {
		exec(sc, ec, "FacebookCordovaPlugin", "login", permissions);
	}
};

module.exports = facebookCordovaPlugin;