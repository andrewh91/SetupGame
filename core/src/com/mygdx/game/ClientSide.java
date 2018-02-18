package com.mygdx.game;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Andrew Hughes on 07/12/2017.
 */

public class ClientSide {

    public Client client;
    private ClientNetworkListener cnl;

    public static Scanner scanner;
    public ClientSide()
    {
        //initialise
        client= new Client();
        cnl= new ClientNetworkListener();
        scanner = new Scanner(System.in);
        //add stuff
        cnl.init(client);
        registerPackets();
        client.addListener(cnl);

        new Thread(client).start();

        try{

            client.connect(9999,"127.0.0.1",54556, 54777);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void registerPackets(){
        Kryo kryo = client.getKryo();

        kryo.register(Packets.Packet00Request.class);
        kryo.register(Packets.Packet01RequestAnswer.class);
        kryo.register(Packets.Packet02Message.class);
    }
    public static void main(String[] args){
        new ClientSide();
    }

}
