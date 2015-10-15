import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {


    public static void main(String[] args) {
        HashMap<String, ArrayList<Country>> countryGroup = new HashMap();
        String postContent = readFile("countries.txt");
        String[] lines = postContent.split("\n");
        Scanner scanner = new Scanner(System.in);


        for (String line : lines) {
            String[] columns = line.split("\\|");
            String firstLetter = String.valueOf(line.charAt(0));
            ArrayList<Country> list = countryGroup.get(firstLetter);

            String abv = columns[0];
            String name = columns[1];
            Country country = new Country(abv, name);



            if (list == null){
                list = new ArrayList();
                list.add(country);
                countryGroup.put(firstLetter, list);
            } else {
                list.add(country);
            }
        }


        System.out.println("Type a letter");
        String tester = scanner.nextLine().toUpperCase();
        String newFileName = String.format("%s_countries.txt",tester);

        if (countryGroup.containsKey(tester)){
            String newLine = "";
            for (Country newCountry : countryGroup.get(tester)) {
                newLine += String.format("%s %s\n", newCountry.abv, newCountry.name);

                writeFile(newFileName, newLine);
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
            fw.close();

        } catch (Exception e){

        }
    }
}
