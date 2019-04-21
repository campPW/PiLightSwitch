package pi.pilightswitch;
import android.os.AsyncTask;
import java.io.IOError;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectToServerTask extends AsyncTask<String,Void, String> {
     @Override
     protected String doInBackground(String... params) {
         String input = params[0];
         try {
             Socket socket = new Socket("10.0.0.50", 5091);

             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
             printWriter.print(input);
             printWriter.flush();

         }
         catch (Exception e) {
             e.printStackTrace();
         }
         catch (IOError ioError){
             ioError.printStackTrace();
         }
         return null;
         }
     }
