package cordova.plugins.barsmargin;

import android.os.Build;
import android.graphics.Insets;
import android.view.WindowInsets;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BarsMargin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("get")) {
            this.get(callbackContext);

            return true;
        }

        return false;
    }

    private void get(CallbackContext callbackContext) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "This plugin only works on Android 11 and above"));
            
            return;
        }

        final Insets insets = this.webView.getView().getRootWindowInsets().getInsets(WindowInsets.Type.systemBars());
        final float density = this.cordova.getActivity().getResources().getDisplayMetrics().density;

        JSONObject jsonResponse = new JSONObject();

        try {
            jsonResponse.put("top", insets.top / density);
            jsonResponse.put("bottom", insets.bottom / density);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jsonResponse));
    }
}