
# CSX42: Assignment 4
## Name: Bhargav Choudhury

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in monopoly/src folder.

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

```commandline
ant -buildfile monopoly/src/build.xml all
```
Note: Provide absolute path for the build.xml file
Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

#### Use the below command to run the program.

```commandline
ant run -buildfile monopoly/src/build.xml \
-DinputFile="<input file path>" \
-DavailableItemsFile="<Available items file.>" \
-DrunningAverageWindowSize="<Window size for running average calculation.>" \
-DDoutputFile="<Output filename.>"
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
bchoudh4@remote02:~/csx42-spring-2020-assign4-WeirdJack/monopoly/src$ ant -DinputFile="/home/bchoudh4/csx42-spring-2020-assign4-WeirdJack/monopoly/input.txt" -DavailableItemsFile="/home/bchoudh4/csx42-spring-2020-assign4-WeirdJack/monopoly/availableItems.txt" -DrunningAverageWindowSize="2" -DoutputFile="output.txt" run
Buildfile: /import/linux/home1/bchoudh4/csx42-spring-2020-assign4-WeirdJack/monopoly/src/build.xml

jar:

run:
     [java] BASIC::cinema--NO
     [java] BASIC::medicine--YES
     [java] BASIC::householdItem--YES
     [java] BASIC::medicine--YES
     [java] BASIC::householdItem--YES
     [java] LUXURIOUS::yacht--NO
     [java] LUXURIOUS::vacationTrip--YES
     [java] LUXURIOUS::medicine--YES
     [java] LUXURIOUS::householdItem--YES
     [java] EXTRAVAGANT::yacht--YES

BUILD SUCCESSFUL
Total time: 0 seconds

```

-----------------------------------------------------------------------
## Description:
A simple Java program

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [04/19/2020]
