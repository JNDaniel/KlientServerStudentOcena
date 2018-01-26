/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverklient;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.InputMismatchException;
import java.util.Scanner;
import klasy.Student;

/**
 *
 * @author student
 */
public class Klient {
    public static void main(String[] args) throws Exception {
        

            Socket s = new Socket("localhost",6543);
            OutputStream os = s.getOutputStream();
            
            PrintWriter pw =  new PrintWriter(os); //pisanie do strumienia 
            System.out.println("Podaj numer indeksu");
            Scanner sc = new Scanner(System.in);
            pw.println(sc.nextLine()); //wyslanie numeru indeksu do serwera 
            pw.flush();
            
             
             System.out.println("Server odpowiedzial: ");
             Student student;
             try
             {
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream()); //strumien odbierajacy obiekt studenta
                student = (Student)ois.readObject(); //czytanie obiektu studenta
                System.out.println(student);
                 
                System.out.println("Chcesz dodać ocene ? T/N"); //pytanie czy dodac ocene
                String answer = sc.next();
                 if(answer.equalsIgnoreCase("t"))
                 {
                    System.out.println("Podaj ocene");
 
                        Double ocena;
                        do
                        {
                           while ( !sc.hasNextDouble()) sc.next(); //dopoki nie podam Double bedzie probowalo wczytac
                            ocena = sc.nextDouble();
                            if(ocena>5 || ocena<2) //ocena w przedziale (2..5)
                            {
                                System.out.println("Blad ocena nie moze byc "+ocena);
                            }
                        }while(ocena>5 || ocena<2);
                        System.out.println("Ocena to "+ocena);
                        System.out.println("Dodaje ocene studentowi "+student.getId());
                        student.dodajOcene(ocena);                                                                      //dodanie oceny studentowi
                        System.out.println("Oceny studenta id "+student.getId()+" to teraz "+student.getOceny());   //druk nowych ocen studenta
                        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream()); //strumien do wyslania zmodyfikowanego studenta
                        oos.writeObject(student);                                             //wyslanie obiektu studenta
                    }
                 else if(answer.equalsIgnoreCase("n"))
                 {
                     System.out.println("Brak zmian");
                     ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                     oos.writeObject(student); //odeslanie tego samego studenta
                 }
                 else
                 {
                     System.out.println("Zle polecenie koncze program");
                 }
                 
             }
             catch(SocketException e)     //rzucany gdy  objectInputStream nie moze odebrac obiektu od servera z powodu np blednego indeksu                 
             {
                System.out.println("Bledny numer indeksu"); 
                //e.printStackTrace();
             }


            s.close();
       
        
    }
}
