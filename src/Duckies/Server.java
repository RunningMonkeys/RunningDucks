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
	String dataOne, dataTwo, dataThree, dataFour;
	boolean clientFour;
	ClientThread playerOne, playerTwo, playerThree, playerFour;
	boolean activeGame;
	Maze maze;
	
	public Server(int port, Consumer<Serializable> callback) {
		this.callback = callback;
		connthread.setDaemon(true);
		ct = new ArrayList<ClientThread>();
		this.port = port;
		maze = new Maze();
	}
	
	public void startConn() throws Exception{
		connthread.start();
	}



	public void closeConn() throws Exception{
		connthread.socket.close();
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
		if(activeGame)
		{
			
			//write back to the clients who won and set the clients to false
			for(ClientThread t : ct)
			{
				try
				{
					t.tout.writeObject(data);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
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
				while(number <= 4)
				{
					ClientThread t1 = new ClientThread(server.accept(),number, true);
					ct.add(t1);
					t1.start();
					switch(number)
					{
					case 1:
						playerOne = t1;
						break;
					case 2:
						playerTwo = t1;
						break;
					case 3:
						playerThree = t1;
						break;
					case 4:
						playerFour = t1;
						activeGame = true;
						break;
					default:
						break;
					}
					number++;
				}
				playerOne.tout.writeObject("Maze Here:" + maze.getPlayerMaze(1));
				playerTwo.tout.writeObject("Maze Here:" + maze.getPlayerMaze(2));
				playerThree.tout.writeObject("Maze Here:" + maze.getPlayerMaze(3));
				playerFour.tout.writeObject("Maze Here:" + maze.getPlayerMaze(4));

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
		
		private boolean checkInput(String in)
		{
			return in.equals("up")|| in.equals("left")|| in.equals("right")|| in.equals("down");	
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
						String input = data.toString().toLowerCase();
						if(checkInput(input))
						{
							if(number == 4) 
							{
								dataFour = input;
								clientFour = true;
								if(maze.moveDuck(dataFour))
								{
									maze.printMaze();
									send("YOU WIN");
								}
								else
								{
									maze.printMaze();
									playerFour.tout.writeObject("Maze Here:");
									send("Keep Going");
								}
							}
							else
							{
								send(data);
							}
						}
						else
						{
							send(data);
						}
					}
					else
					{
						
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
