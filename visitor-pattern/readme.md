
# CSX42: Visitor Pattern
## Name: Bhargav Choudhury

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in sentencePlay/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

```Command: 
ant -buildfile <absolute-path>/build.xml clean
```
  
Note: Provide absolute path for the build.xml file

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

```command line

ant -buildfile <absolute-path>/build.xml
```
Note: Provide absolute path for the build.xml file
Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

#### Use the below command to run the program.

```commandline
ant -buildfile src/build.xml run -Dinput="<absolute-path>/input.txt" -DacceptableWordsFile="<absolute-path>/acceptable_words.txt" -Dk=2 -DtopKOutputFile="topk_output.txt" -DspellCheckOutputFile="spellcheck_output.txt"
```
Note: Provide absolute path for the input.txt and availableItems.txt file

-----------------------------------------------------------------------

## Data Structures

```
ArrayList (Results)
Adding item O(1)
Retrieval of data O(n)

Hashmap
Adding item O(1)
```

-----------------------------------------------------------------------

## Sample Output

```
bchoudh4@remote05:~/csx42-spring-2020-assign5-WeirdJack/sentencePlay$ ant -buildfile src/build.xml run -Dinput="/home/bchoudh4/csx42-spring-2020-assign5-WeirdJack/sentencePlay/input.txt" -DacceptableWordsFile="/home/bchoudh4/csx42-spring-2020-assign5-WeirdJack/sentencePlay/acceptable_words.txt" -Dk=2 -DtopKOutputFile="topk_output.txt" -DspellCheckOutputFile="spellcheck_output.txt"
Buildfile: /import/linux/home1/bchoudh4/csx42-spring-2020-assign5-WeirdJack/sentencePlay/src/build.xml

jar:
    [mkdir] Created dir: /import/linux/home1/bchoudh4/csx42-spring-2020-assign5-WeirdJack/sentencePlay/src/BUILD/jar
      [jar] Building jar: /import/linux/home1/bchoudh4/csx42-spring-2020-assign5-WeirdJack/sentencePlay/src/BUILD/jar/sentencePlay.jar

run:
     [java] [the, down]
     [java] [the, she]
     [java] [walkink::[walking]]
     [java] [aisly::[aisle]]
     [java] [way::[was]]

BUILD SUCCESSFUL
Total time: 0 seconds

```

-----------------------------------------------------------------------
## Description:
A simple Java program to implement the visitor pattern to run different analytics on the given input.

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [05/03/2020]
