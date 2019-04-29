package pi.pilightswitch;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;


public class ConnectToServerTask extends AsyncTask<String,Void, String> {
        private String connectionResult = "";
        private Context context;

        public ConnectToServerTask(Context mainContext) {
            this.context = mainContext;
        }
     @Override
     protected String doInBackground(String... params) {
         String input = params[0];
         try {
             InetSocketAddress inetSocketAddress = new InetSocketAddress("10.0.0.50", 5091);
             Socket socket = new Socket();
             socket.connect(inetSocketAddress, 1000);

             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
             printWriter.print(input);
             printWriter.flush();
         }
         catch (SocketException se) {
            connectionResult = se.getMessage();
         }
         catch (IOException ioe){
             connectionResult = ioe.getMessage();
         }
         catch (Exception e){
             connectionResult = e.getMessage();
         }
         return connectionResult;
     }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // display toast containing the exception message
        Toast errorToast;
        CharSequence toastMsg;
        if(result != "") {
            if(result.contains("ECONNREFUSED")) {
                toastMsg =  "Server Not Listening: Run Server Script";
            }
            else if (result.contains("after")){
                // 'after' included in exception message indicates network timeout
                toastMsg = "Server Down: Turn Pi On";
            }
            else {
                toastMsg = result; // generic exception message
            }
            errorToast = Toast.makeText(context, toastMsg, Toast.LENGTH_LONG);
            errorToast.show();
        }
    }
}
