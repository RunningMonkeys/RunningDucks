package Duckies;



import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.List;
import java.util.function.Consumer;

public class Client {
    private String ip;
    private int port;
    private Consumer<Serializable> callback;
    private ConnThread connthread = new ConnThread();




    public Client(String ip, int port, Consumer<Serializable> callback) {
        this.callback = callback;
        this.ip = ip;
        this.port = port;
        connthread.setDaemon(true);
    }






    public void startConn() throws Exception{
        connthread.start();
    }



    public void closeConn() throws Exception{
        connthread.socket.close();
    }


    protected boolean isServer() {
        return false;
    }

    protected String getIP() {
        // TODO Auto-generated method stub
        return this.ip;
    }

    protected int getPort() {
        // TODO Auto-generated method stub
        return this.port;
    }

    public void send(Serializable data) throws Exception{
        //Client sends out to server

        connthread.out.writeObject(data);
    }

    class ConnThread extends Thread{
        private Socket socket;
        private ObjectOutputStream out;



        public void run() {
            //if it wasn't a server its the client
            try
            {
                Socket socket = new Socket(getIP(),getPort());
                ObjectOutputStream out = new ObjectOutputStream( socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                this.socket = socket;
                this.out = out;
                socket.setTcpNoDelay(true);



                while(true) {
                    Serializable data = (Serializable) in.readObject();
                    callback.accept(data);
                }
            }
            catch(Exception e)
            {
            	
            }

        }
    }

}
