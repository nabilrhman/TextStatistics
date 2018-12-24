import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class computes all the required text statistics.
 * 
 * Computes the total number of characters, 
 * the total number of words, the total number of lines,
 * the number of usage for each letter, the number of words 
 * of each length, the average word length and finds the number 
 * of lines that contains the longest word used in the given file.
 * 
 * @author Nabil Rahman
 *
 */
public class TextStatistics implements TextStatisticsInterface{
	
	private int charCount;
	private int wordCount;
	private int lineCount;
	private int[] wordLengthCount;
	private int[] letterCount;
	private String longestWord; 
	private File file;
	private static final String DELIMITERS = "[\\W\\d_]+";
	
	/**
	 * Constructor: Instantiates text statistics and computes all the 
	 * required statistics.
	 * @param file A file object that is to be analyzed.
	 */
	TextStatistics(File file)
	{
		this.file = file;
		try
		{
			Scanner fileScanner = new Scanner(file);
					
			wordLengthCount = new int[24];
			charCount = 0;
			letterCount = new int[26];
			int otherCharactersCount = 0;
			
			longestWord = " ";
			int longestWordLength = 0;
			
			while(fileScanner.hasNextLine())
			{
				
				lineCount++;
				String line = fileScanner.nextLine();
				
				// Counts and stores the number of characters
			    char currentCharacter; 
				for (int i = 0; i < line.length(); i++) 
				{
					currentCharacter = line.toLowerCase().charAt(i);
					if(currentCharacter >= 'a' && currentCharacter <= 'z')
					{
						letterCount[currentCharacter - 'a']++;
					}
					else
						otherCharactersCount++;
				}
						
				
				Scanner lineScanner = new Scanner(line);
				
				lineScanner.useDelimiter(DELIMITERS);			
				
				// Calculates length and frequency of the words
				while(lineScanner.hasNext())
				{
					wordCount++;
					
					String word = lineScanner.next();
					
					if(word.length() > longestWordLength)
					{
						longestWord = word;
						longestWordLength = word.length();
					}
					
					for(int i = 0; i < wordLengthCount.length; i++)
					{
						if(i == word.length())
						{
							wordLengthCount[i]++;
						}
					}
									
				}
				
				lineScanner.close();
			}
			fileScanner.close();
			
			// Adds other characters to the character counter 
			for(int letterNumber : letterCount)
			{
				charCount += letterNumber;
			}
			charCount += otherCharactersCount + lineCount;
			
		}
		catch(FileNotFoundException e)
		{
			
			System.out.println("Error: " + e.getMessage());
			System.exit(1);
		}
	}
	
	@Override
	public int getCharCount()
	{
		return charCount;
	}
	
	@Override
	public int getWordCount() 
	{
		return wordCount;
	}
	
	@Override
	public int getLineCount() 
	{
		return lineCount;
	}
	
	@Override
	public int[] getLetterCount() 
	{
		return letterCount;
	}
	
	@Override
	public int[] getWordLengthCount()
	{
		return wordLengthCount;
	}
	
	@Override
	public double getAverageWordLength() 
	{
		int result = 0;
		int totalFrequency = 0;
		for(int i = 1; i < wordLengthCount.length; i++)
		{
			if(wordLengthCount[i] != 0)
			{
				result += i * wordLengthCount[i];
				totalFrequency += wordLengthCount[i];
			}
		}
		
		// Converts to two decimal points
		double averageWordLength = (double) result / totalFrequency;
		averageWordLength = (double) Math.round(averageWordLength * 100) / 100;
		
		return averageWordLength;
	}
	
	@Override
	public String toString()
	{	
		DecimalFormat decimalFormatter = new DecimalFormat("#.00");
		String result = "";
		result += "Statistics for " + file.getName() + "\n";
		result += "==========================================================" + "\n";
		result += lineCount + " lines" + "\n";
		result += wordCount + " words" + "\n";
		result += charCount + " characters" + "\n";
		result += "------------------------------" + "\n";
		for(int i = 0; i < letterCount.length/2; i++)
		{
			result += " " + String.format("%-15s", (char) (i + 'a') + " = " + letterCount[i]) +  
					  (char) (i + 'n') + " = " + letterCount[i + ('n'-'a')] + "\n";
		}
		result += "------------------------------" + "\n";
		result += " " + "length" + String.format("%11s", "frequency") + "\n";
		result += " " + "------" + String.format("%11s", "---------") + "\n";
		
		for(int i = 1; i < wordLengthCount.length; i++)
		{
			if(wordLengthCount[i] != 0)
			{
				result += " " + String.format("%6d", i) + String.format("%11d", wordLengthCount[i]) + "\n";
			}
		}
		result += "\n" + "Average word length = " + decimalFormatter.format(getAverageWordLength()) + "\n";
		result += "Longest word: " + longestWord + "\n";
		result += "Line numbers with the longest word: ";
		
		ArrayList<Integer> lineNumbersOfLongestWord = getLineNumbersOfWord(file, longestWord);
		for(int i = 0; i < lineNumbersOfLongestWord.size(); i++)
		{
			result += lineNumbersOfLongestWord.get(i);
			if(i < lineNumbersOfLongestWord.size()-1)
			{
				result += ", ";
			}
		}
		result += "\n" + "==========================================================" + "\n";
		return result;
	}
	
	/**
	 * A method that find the number of lines that contains the passed in word.
	 * @param file The file that is to be searched.
	 * @param mWord The word that is to be looked for.
	 * @return An array list of integer that contains the number of lines which contains the given word.
	 */
	private ArrayList<Integer> getLineNumbersOfWord(File file, String mWord)
	{
		ArrayList<Integer> lineNumbersWithWord = new ArrayList<Integer>();
		try
		{
			Scanner fileScanner = new Scanner(file);
			
			int lineCount = 0;
			int wordCount = 0;
			
			while(fileScanner.hasNextLine())
			{
				lineCount++;
				String line = fileScanner.nextLine();
				Scanner lineScanner = new Scanner(line);
				
				lineScanner.useDelimiter(DELIMITERS);
				
				while(lineScanner.hasNext())
				{
					wordCount++;
					
					String word = lineScanner.next();
					
					if(word.equalsIgnoreCase(mWord))
					{
						lineNumbersWithWord.add(lineCount);
					}
				}
				
				lineScanner.close();
			}
			fileScanner.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		return lineNumbersWithWord;
	}
		
}
