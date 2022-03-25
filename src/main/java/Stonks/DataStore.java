package Stonks;

import java.io.*;
import java.util.Vector;

public class DataStore<T> {

    Vector<T> init(String FileName){

        try{
            File fr = new File(FileName);
            fr.createNewFile();
            FileInputStream fos = new FileInputStream(FileName);
            ObjectInputStream oos = new ObjectInputStream(fos);
            Vector<T> vec = (Vector<T>) oos.readObject();
            fos.close();
            oos.close();
            return vec;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(FileName);
            return new Vector<T>();
        }

    }
    void close(String FileName, Vector<T> vec){
        try{
            File fr = new File(FileName);
            fr.createNewFile();
            FileOutputStream fos = new FileOutputStream(FileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(vec);
            oos.close();
            fos.close();
        } catch (IOException e) {
        }
    }

}