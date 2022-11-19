import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class phoneBook {
 
    private static HashMap<String, String> phones = new HashMap<>();
    private static void addTS(String phone, String name) {
        phones.put(phone, name);
        for(Map.Entry<String,String> k: phones.entrySet()){
            System.out.println(k.getValue()+": "+ k.getKey());
        }
    }
    
    private static void delTS(String phone) {
        phones.remove(phone);
        for(Map.Entry<String,String> k: phones.entrySet()){
            System.out.println(k.getValue()+": "+ k.getKey());
        }
    }
 
    private static void saveTS() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("phone.txt")));
        for(Map.Entry<String,String> k: phones.entrySet()){
            writer.write(k.getKey() + " " + k.getValue()+System.lineSeparator());
        }
        writer.close();
    }
    
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getAnonymousLogger();
        BufferedReader reader = new BufferedReader(new FileReader(new File("phone.txt")));
        String act;
        while ((act=reader.readLine())!=null) {  
            String[] dat = act.split(" ");
            phones.put(dat[0], dat[1]);
        }
        reader.close();
        for(Map.Entry<String,String> k: phones.entrySet()){
            System.out.println(k.getValue()+": "+ k.getKey());
        }
        logger.info("Choose actions: (add)add data, (del)delete data, (save)save data, (exit)quit the program");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        act = bf.readLine();
        while(!act.equals("exit")){
            if(act.equals("add")){
                System.out.println("Text name:");
                String name = bf.readLine();
                System.out.println("Text phone:");
                String phone = bf.readLine();
                addTS(phone,name);
            }else{
                if(act.equals("del")){
                    logger.info("Text phobe:");
                    String phone = bf.readLine();
                    delTS(phone);
                }else{
                   if(act.equals("save")){
                       saveTS();                       
                   }
                }
            }
            logger.info("Choose actions: (add)add data, (del)delete data, (save)save data, (exit)quit the program");
            act=bf.readLine();
        }
    }
}