package ru.avalon.java.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.*;

/**
 * Упражнение на выаботку умений связанных с получением сообщений,
 * отправленных с использованием протокола TCP.
 *
 * @author Daniel Alpatov
 */
public final class TcpReceiver {

    public static void main(String[] args) throws IOException {
        // 1. Определяем порт, на котором ожидается соединение.
        final int port = 0;
        // 2. Подготавливаем серверный сокет.
        final ServerSocket listener = prepareServerSocket(port);
        // 3. Принимаем соединение.
        Socket socket = listener.accept();
        // 4. Полоучаем сообщение.
        final String message = receive(socket);
        // 5. Закрываем соединение.
        socket.close();
        // 6. Закрываем серверный сокет.
        listener.close();
    }

    /**
     * Возвращает серверный сокет, связанный с портом, описанным
     * параметром {@code port}.
     *
     * @param port порт, на котором предполагается получать входящие
     *             соединения.
     *
     * @return серверный сокет, связанный с портом {@code port}.
     */
    private static ServerSocket prepareServerSocket(int port) {
        /*
         * TODO Реализовать метод prepareServerSocket класса TcpReceiver
         */
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            return serverSocket;
        } catch (IOException ex) {
            Logger.getLogger(TcpReceiver.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Возвращает сообщение, прочитанное из входящего потока,
     * указанного сокета.
     *
     * @param socket сокет, описывающий сетевое соединение.
     *
     * @return строковое сообщение.
     */
    private static String receive(Socket socket) {
        /*
         * TODO Реализовать метод receive класса TcpReceiver
         */
        StringBuilder message = null;
        try {
            try (InputStream in = socket.getInputStream();
                 Reader reader = new InputStreamReader(in);
                 BufferedReader buf = new BufferedReader(reader)) {
                char[] cbuf = new char[1024];
                while ((buf.read(cbuf)) != -1) {
                    message.append(cbuf);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(TcpReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message.toString();
    }

}
