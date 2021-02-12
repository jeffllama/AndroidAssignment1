package ca.bcit.assignment1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class People {

    @SerializedName("people")
    @Expose
    private ArrayList<People> people = new ArrayList<People>();

    public ArrayList<People> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<People> people) {
        this.people = people;
    }

    @SerializedName("id")
    @Expose
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @SerializedName("fullName")
    @Expose
    private String fullName;
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @SerializedName("currentAge")
    @Expose
    private int currentAge;
    public int getCurrentAge() {
        return currentAge;
    }
    public void setCurrentAge(int currentAge) {
        this.currentAge = currentAge;
    }

    @SerializedName("nationality")
    @Expose
    private String nationality;
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @SerializedName("currentTeam")
    @Expose
    private Team team;
    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }

    @SerializedName("primaryPosition")
    @Expose
    private PrimaryPosition primaryPosition;
    public PrimaryPosition getPrimaryPosition() {
        return primaryPosition;
    }
    public void setPrimaryPosition(PrimaryPosition position) {
        this.primaryPosition = position;
    }

}
