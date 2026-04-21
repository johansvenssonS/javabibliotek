package model;

import java.util.Date;

public class Author implements TableRow {
    private int id ;
    private String firstName;
    private String lastName;
    private String nationality;
    private Date birthdDate;

    public Author(int id, String firstName, Date birthdDate, String nationality, String lastName) {
        this.id = id;
        this.lastName = lastName;
        this.birthdDate = birthdDate;
        this.nationality = nationality;
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getBirthdDate() {
        return birthdDate;
    }

    public void setBirthdDate(Date birthdDate) {
        this.birthdDate = birthdDate;
    }
    @Override
    public  String[] getColumnNamn(){
        return new String[]{"Förnamn", "Efternamn", "Nationalitet",
                "Födelsedatum"};
    }
    @Override
    public Object[] toRow(){
        return new Object[]{
                getFirstName(), getLastName(),getNationality(),getBirthdDate(),
        };
    }


}
