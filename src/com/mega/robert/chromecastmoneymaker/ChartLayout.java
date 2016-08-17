package com.mega.robert.chromecastmoneymaker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by RTingirica on 17.08.2016.
 */
public class ChartLayout {
    public String name;
    public String columns;
    public String rows;
    public JSONArray elements;

    public ChartLayout(String name, String columns, String rows) { //, JSONArray elements) {
        this.name = name ;
        this.columns = columns;
        this.rows = rows;
//        this.elements = elements;
    }
}
