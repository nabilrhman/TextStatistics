import java.io.File;
import java.io.FileNotFoundException;
/**
 * The driver class for the TextStatistics class.
 * 
 * Computes text statistics for all the files
 * passed through command line arguments.
 * 
 * @author Nabil Rahman
 *
 */
public class ProcessText 
{
	public static void main(String[] args)
	{

        if (!(args.length > 0)) 
        {
            System.err.println("Usage: java ProcessText file1 [file2 ...]");
            System.exit(1);
        }

        for (int i=0; i<args.length; i++)
        {
            try
            {
            	File file = new File(args[i]);
            	if(file.exists())
            	{
            		TextStatistics textStatistics = new TextStatistics(file);
            		System.out.println(textStatistics.toString());
            	}
            }
            catch(Exception e)
            {
            	System.out.println("Invalid file path: " + args[i]);
            }
        }

	}
}
