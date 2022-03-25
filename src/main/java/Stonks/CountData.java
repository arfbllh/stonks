package Stonks;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CountData {
    public static int userCount;
    public static int recordCount;
    public static int entryCount;
    static void close()  {
        try{
            Path path = Paths.get("CountData");
            String data = userCount + " " + recordCount + " " + entryCount;
            Files.writeString(path, data);

            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static  void init() {
        try{
            File fr = new File("CountData");
            fr.createNewFile();
            FileReader fw = new FileReader(fr);
            Scanner sc = new Scanner(fr);
            if(sc.hasNextLine()){
                userCount = sc.nextInt();
                recordCount = sc.nextInt();
                entryCount = sc.nextInt();
                System.out.println(userCount + " " + recordCount + " " + entryCount);
            }
            else{
                userCount = 0;
                recordCount = 0;
                entryCount = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
