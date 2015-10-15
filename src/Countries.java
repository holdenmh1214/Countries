import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Countries {


    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> countryGroup = new HashMap();
        String postContent = readFile("countries.txt");
        String[] lines = postContent.split("\n");


        for (String line : lines) {
            String[] columns = line.split("\\|");
            String firstLetter = String.valueOf(line.charAt(0));
            ArrayList<String> list = countryGroup.get(firstLetter);
            String abv = columns[0];
            String name = columns[1];
            Country country = new Country(abv, name);
            countryGroup.put(firstLetter, list);

            if (list == null){
                list = new ArrayList();
                country = new Country(abv, name);
                list.add(country);
                countryGroup.put(firstLetter, list);
            } else {
                list.add(country);
            }
        }



    }


    static String readFile(String fileName){
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] fileContent = new char[fileSize];
            fr.read(fileContent);
            return new String(fileContent);
        } catch (Exception e){
            return null;
        }
    }

    static void writeFile(String fileName, String fileContent){
        File f = new File(fileName);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(fileContent);

        } catch (Exception e){

        }
    }

}
