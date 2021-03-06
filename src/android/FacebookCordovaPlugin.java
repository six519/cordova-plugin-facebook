package com.ferdinandsilva.facebook;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

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
import com.facebook.AccessTokenTracker;
import com.facebook.AccessToken;

public class FacebookCordovaPlugin extends CordovaPlugin {
    public static final String TAG = "FacebookCordovaPlugin";
    public static Context ctx;
    public static CallbackManager cbackmanager;
    public static Activity thisActivity;
    public static AccessTokenTracker aatokentracker;
    public static AccessToken atoken;

    public FacebookCordovaPlugin() {
    }

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        FacebookCordovaPlugin.ctx = cordova.getActivity().getApplicationContext();
        FacebookCordovaPlugin.thisActivity = cordova.getActivity();
        FacebookSdk.sdkInitialize(FacebookCordovaPlugin.ctx);
        FacebookCordovaPlugin.cbackmanager = CallbackManager.Factory.create();

        FacebookCordovaPlugin.aatokentracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                FacebookCordovaPlugin.atoken = currentAccessToken;
            }
        };

        FacebookCordovaPlugin.aatokentracker.startTracking();

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

        FacebookCordovaPlugin.atoken = AccessToken.getCurrentAccessToken();

    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        JSONObject r = new JSONObject();

        if ("login".equals(action)) {

            List<String> ls = new ArrayList<String>();

            for(int x=0; x < args.length(); x++) {
                String arg = args.get(x).toString();
                ls.add(arg);
            }

            //LoginManager.getInstance().logInWithReadPermissions(FacebookCordovaPlugin.thisActivity, Arrays.asList("public_profile", "user_friends"));
            LoginManager.getInstance().logInWithReadPermissions(FacebookCordovaPlugin.thisActivity, ls);

            
            r.put("ok", "ok");
            callbackContext.success(r);
        } else if ("get_access_token".equals(action)){
            
            r.put("ok","ok");

            try {
                r.put("access_token", FacebookCordovaPlugin.atoken.getToken());
            }
            catch(Exception e) {
                r.put("access_token", "");
            }
            
            callbackContext.success(r);
        } else if("logout".equals(action)) {
            LoginManager.getInstance().logOut();
            r.put("ok","ok");
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
        //super.onActivityResult(requestCode, resultCode, intent);
        FacebookCordovaPlugin.cbackmanager.onActivityResult(requestCode, resultCode, intent);
    }

    public void onDestroy() {
        FacebookCordovaPlugin.aatokentracker.stopTracking();
    }

}
