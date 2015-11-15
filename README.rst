Installation
============

``cordova plugin add https://github.com/six519/cordova-plugin-facebook.git --variable APP_ID="<FACEBOOK_APP_ID>"``


Usage
=====

    Login
    -----

    ::

        window.facebookCordovaPlugin.login(["public_profile", "user_friends"], function(e){
            //sucess callback
        }, function(e){
            //error callback
            alert('The error is: ' + e);
        });

Warning
=======

Still under development