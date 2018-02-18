package com.mygdx.game;

/**
 * Created by Andrew Hughes on 15/02/2018.
 */

public class Packets {

    public static class Packet00Request{}
    public static class Packet01RequestAnswer {boolean  accepted=true;}
    public static class Packet02Message {public String message;public String clientName;}


}
