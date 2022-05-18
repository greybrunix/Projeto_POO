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

    public FileLoadingSaving(){
    }


    public void save(String fileName) throws 
                        IOException
    {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        try{
            oos.writeObject(this);
            oos.flush();
        } catch (Exception e) {
            oos.close();
            throw new IOException("Error saving to file. ");
        }
        oos.close();
    }
    public Model loadState(String fileName) throws
                                FileNotFoundException,
                                IOException
    {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
        } catch (Exception e){
            throw new FileNotFoundException("Error file not found. ");
        }
        ObjectInputStream ois = new ObjectInputStream(fis);
        Model sys = null;
        try{
            sys = (Model) ois.readObject();
        } catch (Exception e) {
            ois.close();
            throw new IOException("Error Loading File. "); 
        }
        ois.close();
        return sys;
    }
}
