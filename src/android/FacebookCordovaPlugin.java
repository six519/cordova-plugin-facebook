package com.ferdinandsilva.facebook;

import android.content.Context;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.widget.AppInviteDialog;

public class FacebookCordovaPlugin extends CordovaPlugin {
    public static final String TAG = "FacebookCordovaPlugin";
    public static Context ctx;

    public FacebookCordovaPlugin() {
    }

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        FacebookCordovaPlugin.ctx = cordova.getActivity().getApplicationContext();
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        return true;
    }

    public void onResume(boolean multitasking) {
    	AppEventsLogger.activateApp(FacebookCordovaPlugin.ctx);
    }

    public void onPause(boolean multitasking) {
    	AppEventsLogger.deactivateApp(FacebookCordovaPlugin.ctx);
    }

}
