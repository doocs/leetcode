# [682. Baseball Game](https://leetcode.com/problems/baseball-game)

## Description
<p>
You're now a baseball game point recorder.
</p>

<p>
Given a list of strings, each string can be one of the 4 following types:
<ol>
<li><code>Integer</code> (one round's score): Directly represents the number of points you get in this round.</li>
<li><code>"+"</code> (one round's score): Represents that the points you get in this round are the sum of the last two <code>valid</code> round's points.</li>
<li><code>"D"</code> (one round's score): Represents that the points you get in this round are the doubled data of the last <code>valid</code> round's points.</li>
<li><code>"C"</code> (an operation, which isn't a round's score): Represents the last <code>valid</code> round's points you get were invalid and should be removed.</li>
</ol>
</p>

<p>
Each round's operation is permanent and could have an impact on the round before and the round after.
</p>

<p>
You need to return the sum of the points you could get in all the rounds.
</p>

<p><b>Example 1:</b><br />
<pre>
<b>Input:</b> ["5","2","C","D","+"]
<b>Output:</b> 30
<b>Explanation:</b> 
Round 1: You could get 5 points. The sum is: 5.
Round 2: You could get 2 points. The sum is: 7.
Operation 1: The round 2's data was invalid. The sum is: 5.  
Round 3: You could get 10 points (the round 2's data has been removed). The sum is: 15.
Round 4: You could get 5 + 10 = 15 points. The sum is: 30.
</pre>
</p>

<p><b>Example 2:</b><br />
<pre>
<b>Input:</b> ["5","-2","4","C","D","9","+","+"]
<b>Output:</b> 27
<b>Explanation:</b> 
Round 1: You could get 5 points. The sum is: 5.
Round 2: You could get -2 points. The sum is: 3.
Round 3: You could get 4 points. The sum is: 7.
Operation 1: The round 3's data is invalid. The sum is: 3.  
Round 4: You could get -4 points (the round 3's data has been removed). The sum is: -1.
Round 5: You could get 9 points. The sum is: 8.
Round 6: You could get -4 + 9 = 5 points. The sum is 13.
Round 7: You could get 9 + 5 = 14 points. The sum is 27.
</pre>
</p>

<p><b>Note:</b><br />
<li>The size of the input list will be between 1 and 1000.</li>
<li>Every integer represented in the list will be between -30000 and 30000.</li>
</p>


## Solutions


### Python3

```python

```

### Java

```java

```

### ...
```

```
