package com.mcnblue.cordovaplugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import com.zebra.android.discovery.*;
import com.zebra.sdk.comm.Connection;
import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.comm.TcpConnection;
import com.zebra.sdk.printer.discovery.*;
import com.zebra.sdk.printer.ZebraPrinterFactory;
import com.zebra.sdk.printer.ZebraPrinterLanguageUnknownException;
/**
 * This class echoes a string called from JavaScript.
 */
public class ZplWifiPrinter extends CordovaPlugin {
    CallbackContext callbackContext;
    PluginResult result;
    JSONObject params;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        try {
            switch (action) {
                case "print":
                    params = args.getJSONObject(0);
                    this.print();
                    return true;
                case "find":
                    this.find();
                    return true;
                default:
                    return false;
            }
        }catch(Exception e){
            callbackContext.error("action error: "+e.getMessage());
            return false;
        }
    }

    public void print() throws ConnectionException,JSONException {
        // Instantiate connection for ZPL TCP port at given address
        JSONObject c = params;
        System.out.println("print");
        Connection thePrinterConn;
        String ip = c.getString("ip");
        int port = c.has("port")?c.getInt("port"):TcpConnection.DEFAULT_ZPL_TCP_PORT;
        String zpl = c.getString("zpl");
        System.out.println(ip+":"+port);
        thePrinterConn = new TcpConnection(ip,port);
        try {
            // Open the connection - physical connection is established here.
            thePrinterConn.open();
            thePrinterConn.write(zpl.getBytes());
        } catch (ConnectionException e) {
            // Handle communications error here.

            System.out.println("ConnectionException");
            e.printStackTrace();
            callbackContext.error(e.getMessage());
        } catch (Exception e) {
            // Handle communications error here.

            System.out.println("Exception");
            e.printStackTrace();
            callbackContext.error(e.getMessage());
        } finally {
            // Close the connection to release resources.
            thePrinterConn.close();
        }

        callbackContext.success("sent");
    }

    DiscoveryHandler discoveryHandler = new DiscoveryHandler() {
        List<DiscoveredPrinter> printers = new ArrayList<DiscoveredPrinter>();
        public void foundPrinter(DiscoveredPrinter printer) {
            System.out.println(printer);
            printers.add(printer);
        }
        public void discoveryFinished() {
            JSONArray arr = new JSONArray();
            JSONObject r = new JSONObject();
            try {
                if(printers.size()==0){
                    r.put("message","no printers found");
                }else{
                    r.put("message","printers were found");
                    for (DiscoveredPrinter printer : printers) {
                        System.out.println(printer);
                        arr.put(printer);
                    }
                    r.put("printers",arr);
                }
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, r));
                //callbackContext.success(arr);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
                callbackContext.error(e.getMessage());
            }
        }
        public void discoveryError(String message) {
            System.out.println(message);
            callbackContext.error(message);
        }
    };

    public void find() {
        try {
            System.out.println("findPrinter");
            NetworkDiscoverer.findPrinters(discoveryHandler);
        } catch (Exception e) {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
        }
    }
}
