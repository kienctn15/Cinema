package com.example.falcon.cinema;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by Admin on 4/7/2017.
 */

public class Config {
    public Socket mSocket;
    public Config(){
        {
            try {
                mSocket = IO.socket("http://192.168.0.84:3000/");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void Connect(){
        mSocket.connect();
    }



}
