package ca.bcit.assignment1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BaseRoster {

    @SerializedName("roster")
    @Expose
    private ArrayList<Person> roster = new ArrayList<>();

    public ArrayList<Person> getRoster() {
        return roster;
    }

    public void setRoster(ArrayList<Person> roster) {
        this.roster = roster;
    }
}
