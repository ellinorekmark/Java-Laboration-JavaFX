package com.example.laborationtre;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;



public class ArtListStuff {

    static public ArrayList<BaseShape> artMemory = new ArrayList<BaseShape>();
    static public ArrayList<GraphicsContext> contextList = new ArrayList<GraphicsContext>();

    public static void addContext(GraphicsContext context){
        contextList.add(context);
    }



    public static void addArtMemory(BaseShape art){
        artMemory.add(art);

    }

    public static BaseShape getLastArtMemory(){
        return artMemory.get(artMemory.size());
    }


}
