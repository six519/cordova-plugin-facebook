Installation
============

``cordova plugin add https://github.com/six519/cordova-plugin-facebook.git --variable APP_ID="<FACEBOOK_APP_ID>"``


Usage
=====
::

    //logging in
    window.facebookCordovaPlugin.login(["public_profile", "user_friends"], function(e){
        //sucess callback
    }, function(e){
        //error callback
        alert('The error is: ' + e);
    });

    //logging out
    window.facebookCordovaPlugin.logout(function(e){
        //success callback
    }, function(e){
        //error callback
    });

Warning
=======

Still under development