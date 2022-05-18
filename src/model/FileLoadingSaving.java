package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class FileLoadingSaving implements Serializable
{
    public void save(String fileName) throws 
                        FileNotFoundException,
                        IOException
    {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(this);
        oos.flush();
        oos.close();
    }
    public static State loadState(String fileName) throws
                                FileNotFoundException,
                                IOException,
                                ClassNotFoundException                            
    {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        State sys = (State) ois.readObject();
        ois.close();
        return sys;
    }
}
