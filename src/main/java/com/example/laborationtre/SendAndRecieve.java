package com.example.laborationtre;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class SendAndRecieve {



     public MyShape recieveShape(String string){
        String[] strings = string.split("\"");
        for (String line: strings
             ) {
            System.out.println(line);
        }
         double x = Double.parseDouble(strings[1]);
        double y = Double.parseDouble(strings[2]);
        double size = Double.parseDouble(strings[3]);

        //color doesn't work great.
        int redString = (int) (Double.parseDouble(strings[4])*255);
        int greenString = (int)Double.parseDouble(strings[5])*255;
        int blueString = (int)Double.parseDouble(strings[6])*255;
        System.out.println(redString);

        Color color = Color.rgb(redString,greenString,blueString);

        if (strings[0].equals("MyCircle")){
            return new MyCircle(x+50,y,size, color); //remove extra numbers when actually using, just a test for now

        }
//        else if (strings[0].equals("Line")){
//
//        }
//        else if(strings[0].equals("Path")){
//
//        }

        return new MyCircle(x+50,y+50,size, color);
    }
}
