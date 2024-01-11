package com.example.giocodellamemoria;

import java.util.ArrayList;

public class Actions {
    public ArrayList<Integer> ids;
    private static int id_activity;

    public Actions(ArrayList<Integer> ids){
        this.ids = ids;
    }

    public int positionAtClick(int id){
        int position = -1;
        for(int i = 0; i<ids.size(); i++){
            if(ids.get(i) == id){
                position = i;
            }
        }
        return position;
    }

    public boolean vittoria(ArrayList<Integer> arrayList){
        for (int i : arrayList) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
    public static int getId_activity() {
        return id_activity;
    }

    public static void setId_activity(int id_activity) {
        Actions.id_activity = id_activity;
    }
}
