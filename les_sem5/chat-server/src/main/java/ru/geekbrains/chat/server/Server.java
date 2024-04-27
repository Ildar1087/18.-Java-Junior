package ru.geekbrains.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    //region Поля
    private final ServerSocket serverSocket;


    //endregion

    //region Конструкторы
    public Server(ServerSocket serverSocket) {

        this.serverSocket = serverSocket;
    }



    //endregion

    //region Методы
    /**
     * Метод для вызова сервера
      */
    public void runServer(){
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ClientManager clientManager = new ClientManager(socket);
                System.out.println("Подключен новый клиент!");
                Thread thread = new Thread(clientManager);
                thread.start();

            }
        } catch(IOException e){
            closeSocket();
        }
    }

    /**
     * Метод заглушка, в случае возникновения ошибок
     */
    private void closeSocket(){
        try{
            if(serverSocket != null)
                serverSocket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    //endregion

}
