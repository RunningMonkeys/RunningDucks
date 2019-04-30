package Duckies;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;


public class Server {
	private ConnThread connthread = new ConnThread();
	private Consumer<Serializable> callback;
	ArrayList<ClientThread> ct;
	private int port;
	boolean clientOne,clientTwo, clientThree, clientFour;
	String dataOne, dataTwo, dataThree, dataFour;
	ClientThread playerOne, playerTwo, playerThree, playerFour;
	boolean activeGame;
	
	public Server(int port, Consumer<Serializable> callback) {
		this.callback = callback;
		connthread.setDaemon(true);
		ct = new ArrayList<ClientThread>();
		this.port = port;
	}

	
	protected boolean isServer() {
		// TODO Auto-generated method stub
		return true;
	}

	
	protected String getIP() {
		// TODO Auto-generated method stub
		return null;
	}

	
	protected int getPort() {
		// TODO Auto-generated method stub
		return port;
	}
	
	
	public void send(Serializable data) throws Exception{
		//if both have sent a something call compare and send it back to the client
		if(isServer())
		{
			String message = data.toString();
			//write back to the clients who won and set the clients to false
			for(ClientThread t : ct)
			{
				try
				{
					t.tout.writeObject(message);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			clientOne = false;
			clientTwo = false;
		}
	}
	
	
	class ConnThread extends Thread{
		private Socket socket;
		private ObjectOutputStream out;



		public void run() {
			int number = 1;
			ServerSocket server = null;
			try
			{
				server = new ServerSocket(getPort());
				//added when trying out observer
				while(true)
				{
					ClientThread t1 = new ClientThread(server.accept(),number, true);
					ct.add(t1);
					t1.start();
					
					if(number >= 4)
					{
						activeGame = true;
					}
					number++;
				}

			}
			catch(Exception e)
			{
				
			}
			

		}
	}

	private class ClientThread extends Thread
	{
		Socket s;
		int number;
		ObjectOutputStream tout;
		ObjectInputStream tin;
		boolean playable;

		ClientThread(Socket socket, int num, boolean p)
		{
			this.s = socket;
			this.number = num;
			playable = p;

		}
		
		public int getNumber()
		{
			return number;
		}
		
		public void run()
		{
			try( ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
				 ObjectInputStream in = new ObjectInputStream(s.getInputStream())){
				s.setTcpNoDelay(true);
				this.tout = out;
				this.tin = in;
				tout.writeObject("welcome player " + number);
				while(true)
				{
					Serializable data = (Serializable) in.readObject();
					//Got something in from client do something with it.
					if(activeGame)
					{
						//update info
					}
				
				}
				
			}
			catch(Exception e)
			{
				callback.accept("Connection Closed with player " + number);
			}
	
		}
	}
}
