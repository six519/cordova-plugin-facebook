package com.ferdinandsilva.facebook;

import java.util.Arrays;

import android.content.Context;
import android.content.Intent;
import android.app.Activity;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.widget.AppInviteDialog;
import com.facebook.FacebookSdk;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

public class FacebookCordovaPlugin extends CordovaPlugin {
    public static final String TAG = "FacebookCordovaPlugin";
    public static Context ctx;
    public static CallbackManager cbackmanager;
    public static Activity thisActivity;

    public FacebookCordovaPlugin() {
    }

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        FacebookCordovaPlugin.ctx = cordova.getActivity().getApplicationContext();
        FacebookCordovaPlugin.thisActivity = cordova.getActivity();
        FacebookSdk.sdkInitialize(FacebookCordovaPlugin.ctx);
        FacebookCordovaPlugin.cbackmanager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(FacebookCordovaPlugin.cbackmanager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if ("login".equals(action)) {

            LoginManager.getInstance().logInWithReadPermissions(FacebookCordovaPlugin.thisActivity, Arrays.asList("public_profile", "user_friends"));

            JSONObject r = new JSONObject();
            r.put("ok", "ok");
            callbackContext.success(r);
        } else {
            return false;
        }

        return true;
    }

    public void onResume(boolean multitasking) {
    	AppEventsLogger.activateApp(FacebookCordovaPlugin.ctx);
    }

    public void onPause(boolean multitasking) {
    	AppEventsLogger.deactivateApp(FacebookCordovaPlugin.ctx);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        FacebookCordovaPlugin.cbackmanager.onActivityResult(requestCode, resultCode, intent);
    }

}
