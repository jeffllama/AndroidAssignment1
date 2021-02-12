package ca.bcit.assignment1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("person")
    @Expose
    private PersonDetails person;
    public PersonDetails getPersonDetails() {
        return person;
    }
    public void setPerson(PersonDetails person) {
        this.person = person;
    }


}
