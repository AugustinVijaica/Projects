package Model;

import java.util.ArrayList;

public interface IPersistente<A,B> {


    public boolean salvare(A a,B b);
    public boolean actualizare(A oldA, A newA, B b);
    public boolean stergere(A a);
    public ArrayList<A> filtrare(B b);
    public A cautare(B b);




}
