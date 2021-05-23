# CosineDistanceCalculator
Program compares two text files for similarity by cosine distance. Comparison based on hash code maps. Final project for Advanced OOP module.

The purpose of this program is to identify how similar two text files are based on their content.
It calculates the dot product and magnitude based on the frequency of occurence of words, k-mers or shingles in each text.
Then applies a mathematical formula to calculate how similar they are.

The program runs as follows:<br>
1 User selects whether to compare based on individual words, 5 character k-mers or shingles of two words.<br>
2 User is asked to enter a query file followed by a subject file.<br>
3 The program generates hashcode based maps for both files.<br>
4 Maps are then compared based on the setting chosen at step 1.<br>
<br>
To run this application follow these steps:<br>
1 Download the src folder which contains the uncompiled .java file and text files.<br>
2 Open a command prompt at src/ie/gmit/dip folder.<br>
3 Enter the following command.. javac *.java<br>
4 Navigate back to the src folder in command prompt.<br>
5 Enter the following command.. java ie.gmit.dip.Runner<br>
6 Program will run.<br>
