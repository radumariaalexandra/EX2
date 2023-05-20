import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static ArrayList<String> getSocialSecurityNumberFromFile(String filename){
        ArrayList<String> securityNumbers = new ArrayList<>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                securityNumbers.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return securityNumbers;
    }

    public static void main(String[] args) {
        ArrayList<String> socialSecurityNumbers = getSocialSecurityNumberFromFile("CNPPERS.txt");
        ArrayList<String> socialSecurituNumbers2 = getSocialSecurityNumberFromFile("Cnp2.txt");

        for (int i =0; i < socialSecurityNumbers.size(); i++) {
            String cnp = socialSecurityNumbers.get(i);
            System.out.println(cnp.charAt(0));
            // In Romania, the first character of the social number
            // represents the biological sex of a person.
            //Below, we take from the social securitu number the first character
            //and check whether they re male or female.
            char gender = ' ';
            String prefix = "";
            switch (cnp.charAt(0)){
                case '1', '2' -> prefix = "19";
                case '3', '4' -> prefix = "18";
                case '5', '6' -> prefix = "20";
            }


            System.out.println(cnp.substring(1, 7));
            int year = Integer.parseInt(prefix + cnp.substring(1,3));
            int month = Integer.parseInt(cnp.substring(3,5));
            int day = Integer.parseInt(cnp.substring(5,7));
            String birthDate = year + "-" + month + "-" + day;

            String currentDate = "20230601";
            int currentYear = Integer.parseInt(currentDate.substring(0,4));
            int currentMonth = Integer.parseInt(currentDate.substring(4,6));
            int currentDay = Integer.parseInt(currentDate.substring(6,8));
            System.out.println(currentYear + "-" + currentMonth + "-" + currentDay);

            int age = currentYear - year;
            if (month > currentMonth){
                --age;
            } else if (month == currentMonth);{
                if (day >= currentDay){
                    --age;
                }
            }
            int county = Integer.parseInt(cnp.substring(7,9));
            String stringCounty = "";
            switch (county) {
                case 1 -> stringCounty = "Alba";
                case 2 -> stringCounty = "Arad";
                case 3 -> stringCounty = "Arges";
                case 4 -> stringCounty = "Bacau";
                case 5 -> stringCounty = "Bihor";
                case 6 -> stringCounty = "Bistrita";
                case 7 -> stringCounty = "Botosani";
                case 8 -> stringCounty = "Brasov";
                case 9 -> stringCounty = "Braila";
                case 10 -> stringCounty = "Buzau";
            }
            System.out.println("Your CNP is: " + cnp);
            System.out.println("Gender: " + gender);
            System.out.println("Birth date: " + birthDate);
            System.out.println("Age: " + age);
            System.out.println("County: " + county);
        }

    }
}