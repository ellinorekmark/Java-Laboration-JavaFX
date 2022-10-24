package com.example.laborationtre;

import com.example.laborationtre.ArtObject;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;



public class ArtListStuff {

    static public ArrayList<ArtObject> artMemory = new ArrayList<ArtObject>();
    static public ArrayList<GraphicsContext> contextList = new ArrayList<GraphicsContext>();

    public static void addContext(GraphicsContext context){
        contextList.add(context);
    }



    public static void addArtMemory(ArtObject art){
        artMemory.add(art);

    }

    public static ArtObject getLastArtMemory(){
        return artMemory.get(artMemory.size());
    }


}
