import java.util.Scanner;
import java.util.ArrayList;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;


public class PersonGenerator
{
    public static void main(String[] args) {
        String IDNum;
        String firstName;
        String lastName;
        String title;
        int YOB;

        String fileName;
        Scanner fileNameInput = new Scanner(System.in);
        fileName = SafeInput.getNonZeroLenString(fileNameInput, "Input Desired File Name (Don't include .txt)");

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + fileName + ".txt");

        boolean adding;
        String cont;

        ArrayList<String> people = new ArrayList<String>();
        adding = true;
        cont = "y";



        //Get input from user
        while(adding){
            Scanner IDNumInput = new Scanner(System.in);
            IDNum = SafeInput.getNonZeroLenString(IDNumInput, "IDNum");
            Scanner firstNameInput = new Scanner(System.in);
            firstName = SafeInput.getNonZeroLenString(firstNameInput, "First Name");
            Scanner lastNameInput = new Scanner(System.in);
            lastName = SafeInput.getNonZeroLenString(lastNameInput, "Last Name");
            Scanner titleInput = new Scanner(System.in);
            title = SafeInput.getNonZeroLenString(titleInput, "Title");
            Scanner YOBInput = new Scanner(System.in);
            YOB = SafeInput.getInt(YOBInput, "Year of Birth");
            people.add(IDNum);
            people.add(firstName);
            people.add(lastName);
            people.add(title);
            people.add(String.valueOf(YOB));

            //Ask user if they wish to enter another person
            Scanner continueInput = new Scanner(System.in);
            adding = SafeInput.getYNConfirm(continueInput, "Add Another Person? (Y or N)");

            int counter = 0;

            try
            {
                OutputStream out =
                        new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                BufferedWriter writer =
                        new BufferedWriter(new OutputStreamWriter(out));

                for(String person : people)
                {
                    writer.write(person, 0, person.length());
                    counter ++;
                    if(counter % 5 == 0)
                    {
                        writer.newLine();
                    }
                    else
                    {
                        writer.write(", ");
                    }
                }
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

}
