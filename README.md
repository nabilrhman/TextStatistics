# Project 4: Text Statistics 

* Author: Nabil Rahman
* Class: CS121 Section 4
* Semester: Spring 2017

## Overview

This Java application analyzes text files and generates
useful statistics. 

## Compiling and Using

To compile, execute the following command in the main project directory:
```
$ javac ProcessText.java
```

Run the compiled class with the command passing the file name using command-line argument:
```
$ java ProcessText file.txt
```

To process multiple files, run the compiled class with the command passing the file names using command-line argument:
```
$ java ProcessText file1.txt file2.txt
```

It will then print the statistics for each of the files.

## Discussion

At first, I wrote the ProcessText class so that it takes input through command-line 
arguments. I made sure to validate the number of command line arguments so that 
it accepts the right inputs.

Then, I implemented the provided interface named TextStatisticsInterface to my
TextStatistics class. Then, I created all the instance variables by having a look 
at the auto-generated methods. 

After I was done writing the instance variables for the TextStatistics class, I wrote 
the accessor and mutator methods for all the variables. Then, I wrote the 
constructor for the class. I used two scanners to scan through the file and used the
provided delimiters to separate the words. While scanning, I used the counters for
lines and words to calculate the total lines and words in the file.

Then, I used some logic to store the number of words of each length that appears
in the file. In this case, I assumed the maximum word length to be 23 and created 
an array to store the length of those words. In addition to that, I used another
array to store the number of each letter that appears in the file. Using the array
that contains the number of each letters, I calculated the frequency of the words
of specific length converting all characters to lower case before counting. By
using the length and the frequency of the words, I wrote the formula to calculate
the average word length for the file. Most of the logics were inside the TextStatistics
constructor. After I was done writing the constructor, I wrote the toString method
overriding the regular ones to get a result string according to the provided sample.

As my constructor class was getting much complicated, I decided to wrote a separate 
method to find the longest word and the line numbers that contains the word. It was 
much easier to implement and manage.

In my opinion, this project was a lot different than the previous projects. The instructions
were not mentioned that clearly on the website. I had to guess the functionalities
by having a look at the method names and the provided sample output. For that reason,
It was a bit challenging for me to implement all the functionalities so that it provides
exactly the same result as the sample. The provided grader script was very handy.
It helped me to see if I was going towards the right direction from time to time. 

## Testing

I tested the program with autograder script provided on the project website. I matched
my result with the generated ones and found that my program showed the results as expected.

## Extra Credit

For extra credit, I implemented the logic to find and report all the line numbers at which
the longest word in a file appears. I wrote a separate method to do that.

## Sources used

- I gathered several concepts from Stack Overflow.
[Stack Overflow](http://www.stackoverflow.com)
- I studies several Java Tutorials made by Oracle.
[The Java™ Tutorials](https://docs.oracle.com/javase/tutorial/)