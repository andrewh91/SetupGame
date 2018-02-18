package com.mygdx.game;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

/**
 * Created by Andrew Hughes on 07/12/2017.
 */

public class ServerSide {

    Server server;
    ServerNetworkListener snl;

    public ServerSide()
    {
        server=new Server();
        snl = new ServerNetworkListener();
        registerPackets();

        server.addListener(snl);
        try {
            server.start();

            server.bind(54556,54777);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void registerPackets()
    {
        Kryo kryo = server.getKryo();

        kryo.register(Packets.Packet00Request.class);
        kryo.register(Packets.Packet01RequestAnswer.class);
        kryo.register(Packets.Packet02Message.class);
    }

    public static void main(String[] args)
    {
        new ServerSide();

    }

}
