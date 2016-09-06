package challenge15.hard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	private static final String hostName = "localhost";
	private static final int portNumber = 8000;

	private static class Server {
		private ServerSocket serverSocket;
		private Socket clientSocket;

		public Server() {
			try {
				serverSocket = new ServerSocket(portNumber);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void startListening() {
			if (serverSocket == null) {
				System.out.println("SERVER: Socket is not set");
				return;
			}

			try {
				System.out.println("SERVER IS AWAITING FOR CLIENT ...");
				clientSocket = serverSocket.accept();
				System.out.println("SERVER HAS ACCEPTED A CONNECTION.");

				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

				new Thread(new Runnable() {
					@Override
					public void run() {
						String line;
						try {
							while ((line = in.readLine()) != null) {
								if (line.startsWith("/r")) {
									line = new StringBuilder(line.subSequence(2, line.length())).reverse().toString();
								}
								out.println("<SERVER ECHO> " + line);
							}
						} catch (IOException e) {
						} finally {
							try {
								System.out.println("SERVER HAS STOPPED LISTENING.");
								out.close();
								in.close();
								clientSocket.close();
								serverSocket.close();
							} catch (Exception e) {
							}
						}
					}
				}).start();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static class Client {
		private Socket socket;

		public Client() {
			try {
				socket = new Socket(hostName, portNumber);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void startSending() {
			if (socket == null) {
				System.out.println("Client: Socket is not set");
				return;
			}

			try {
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader myInput = new BufferedReader(new InputStreamReader(System.in));
				BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				String myLine;
				System.out.println("ENTER !q TO QUIT");
				while (true) {
					System.out.print(">> ");
					myLine = myInput.readLine();
					if (myLine.equals("!q"))
						break;
					out.println(myLine);
					System.out.println(fromServer.readLine());
				}
			} catch (IOException e) {
			} finally {
				try {
					socket.close();
				} catch (Exception e) {
				}
			}
		}

	}

	public static void main(String[] args) {
		Server server = new Server();
		Client client = new Client();

		server.startListening();
		client.startSending();

		System.out.println("PROGRAM END.");
	}

}