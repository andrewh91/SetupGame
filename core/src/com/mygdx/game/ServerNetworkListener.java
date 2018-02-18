package com.mygdx.game;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * Created by Andrew Hughes on 15/02/2018.
 */

public class ServerNetworkListener extends Listener {

    public ServerNetworkListener(){

    }
    public void connected(Connection c){
        System.out.println("someone has connected");
    }
    public void disconnected(Connection c){
        System.out.println("someone has disconnected");

    }
    public void received(Connection c,Object o){

        if(o instanceof  Packets.Packet00Request)
        {
            Packets.Packet01RequestAnswer answer = new Packets.Packet01RequestAnswer();
            answer.accepted = true;
            c.sendTCP(answer);
        }

        if(o instanceof Packets.Packet02Message)
        {
            Packets.Packet02Message p = (Packets.Packet02Message) o;
            System.out.println("["+ p.clientName+"] >> " + p.message);
        }

    }
}
