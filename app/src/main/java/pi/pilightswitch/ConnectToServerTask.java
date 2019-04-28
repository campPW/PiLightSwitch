package pi.pilightswitch;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

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
             Socket socket = new Socket("10.0.0.50", 5091);
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
             printWriter.print(input);
             printWriter.flush();
         }
         catch (SocketException se) {
            connectionResult = se.getMessage();
         }
         catch (UnknownHostException uhe) {
             connectionResult = uhe.getMessage();
         }
         catch (IOException ioe){
             System.out.println("INSIDE IO EXCEPTION");
             connectionResult = ioe.getMessage();
         }
         catch (Exception e) {
             connectionResult = e.getMessage();
         }
         System.out.println("AFter try catch stuff");
         return connectionResult;
     }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // display toast containing the exception message
        if(result != "") {
            CharSequence text = result;
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
