import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JFileChooser;


public class PersonReader
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File desiredFile;
        String rec = "";

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                desiredFile = chooser.getSelectedFile();
                Path file = desiredFile.toPath();
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                int line = 0;
                while (reader.ready()) {
                    rec = reader.readLine();
                    line++;
                    System.out.printf("\nLine %4d %-60s ", line, rec);
                }
                reader.close();
                System.out.println("\n\nData file read!");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println(("File not found"));
            e.printStackTrace();
        }
        catch(IOException e)
        {
            System.out.println(("File not found"));
            e.printStackTrace();
        }
    }
}
