# [486. Predict the Winner](https://leetcode.com/problems/predict-the-winner)

## Description
<p>Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins. </p>



<p>Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score. </p>



<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> [1, 5, 2]

<b>Output:</b> False

<b>Explanation:</b> Initially, player 1 can choose between 1 and 2. <br/>If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). <br/>So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. <br/>Hence, player 1 will never be the winner and you need to return False.

</pre>

</p>



<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> [1, 5, 233, 7]

<b>Output:</b> True

<b>Explanation:</b> Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.<br />Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.

</pre>

</p>



<p><b>Note:</b><br>

<ol>

<li>1 <= length of the array <= 20. </li>

<li>Any scores in the given array are non-negative integers and will not exceed 10,000,000.</li>

<li>If the scores of both players are equal, then player 1 is still the winner.</li>

</ol>

</p>


## Solutions


<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**
```

```

<!-- tabs:end -->