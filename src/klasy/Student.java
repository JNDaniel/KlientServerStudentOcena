/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author student
 */
public class Student implements Serializable 
{
    private String imie;
    private String nazwisko;
    private Long id;
    List<Double> oceny = new ArrayList<>();
    private static final long serialVersionUID = 1L; //musi byc takie samo w obydwu klasach po stronie klienta i servera
    
    public Student(){}
    
    public Student(String i,String n,Long idx,ArrayList<Double> o)
    {
        this.imie=i;
        this.nazwisko=n;
        this.id=idx;
        this.oceny=o;
    }
        public Student(String i,String n,Long idx)
    {
        this.imie=i;
        this.nazwisko=n;
        this.id=idx;
    }
    public void dodajOcene(Double o)
    {
        this.oceny.add(o);
    }
    
    @Override
    public String toString()
    {
        return "Imie: "+this.imie+ System.lineSeparator()+
                "Nazwisko: "+this.nazwisko+System.lineSeparator()+
                "Nr indeksu: "+this.id+System.lineSeparator()+
                "Oceny: "+oceny.toString()+System.lineSeparator();
                
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Double> getOceny() {
        return oceny;
    }

    public void setOceny(List<Double> oceny) {
        this.oceny = oceny;
    }
    
    
    
}
