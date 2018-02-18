package com.mygdx.game;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import sun.rmi.runtime.Log;

/**
 * Created by Andrew Hughes on 15/02/2018.
 */

public class ClientNetworkListener extends Listener {
    private Client client;

    String userName = "Client";

    public void init(Client client)
    {
        this.client = client;
    }

    public void connected(Connection c)
    {
        System.out.println("[CLIENT] >> you have connected.");
        client.sendTCP(new Packets.Packet00Request());
        //prepare and send a message to server
        /*
        Packets.Packet02Message firstMessage = new Packets.Packet02Message();
        firstMessage.message = "Hello, Server. How are you?";
        firstMessage.clientName = "And";
        client.sendTCP(firstMessage);
        */
    }
    public void disconnected(Connection c)
    {
        System.out.println("[CLIENT] >> you have disconnected.");

    }
    public void received(Connection c,Object o)
    {
        if(o instanceof  Packets.Packet01RequestAnswer)
        {
            boolean serverAnswer = ((Packets.Packet01RequestAnswer) o).accepted;

            if(serverAnswer==true)
            {
                while(true)
                {
                    if(ClientSide.scanner.hasNext())
                    {
                        Packets.Packet02Message messagePacket = new Packets.Packet02Message();
                        messagePacket.clientName=userName;
                        messagePacket.message = ClientSide.scanner.nextLine();
                        c.sendTCP(messagePacket);
                    }
                }
            }
            else
            {
                c.close();

            }
        }
        if(o instanceof Packets.Packet02Message)
        {
            Packets.Packet02Message p = (Packets.Packet02Message) o;
            System.out.println("[SERVER] >> " + p.message);
        }
    }
}
