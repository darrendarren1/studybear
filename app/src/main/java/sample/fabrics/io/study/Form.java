package sample.fabrics.io.study;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/*
 * Created by earlybirdcamp on 6/15/16.
 */
@ParseClassName("Form")
public class Form extends ParseObject{

    public Form() {

    }

    public String getLocation() {
        return getString("location");
    }

    public void setLocation(String location) {
        put("location", location);
    }

    public String getGroup() {
        return getString("group");
    }

    public void setGroup(String group) {
        put("group", group);
    }

    public String getProductivity() {
        return getString("productivity");
    }

    public void setProductivity(String productivity) {
        put("productivity", productivity);
    }

    public String getNotes() {
        return getString("notes");
    }

    public void setNotes(String notes) {
        put("notes", notes);
    }

    public void setTimer(String time){ put("time", time); }

    public String getTimer(){ return getString("time"); }

    public ParseUser getAuthor(){ return getParseUser("author"); }

    public void setAuthor(){ put("author", ParseUser.getCurrentUser());}

}

