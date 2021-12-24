# [1406. Stone Game III](https://leetcode.com/problems/stone-game-iii)

[中文文档](/solution/1400-1499/1406.Stone%20Game%20III/README.md)

## Description

<p>Alice and Bob continue their&nbsp;games with piles of stones. There are several stones&nbsp;<strong>arranged in a row</strong>, and each stone has an associated&nbsp;value which is an integer given in the array&nbsp;<code>stoneValue</code>.</p>

<p>Alice and Bob take turns, with <strong>Alice</strong> starting first. On each player&#39;s turn, that player&nbsp;can take <strong>1, 2 or 3 stones</strong>&nbsp;from&nbsp;the <strong>first</strong> remaining stones in the row.</p>

<p>The score of each player is the sum of values of the stones taken. The score of each player is <strong>0</strong>&nbsp;initially.</p>

<p>The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie. The game continues until all the stones have been taken.</p>

<p>Assume&nbsp;Alice&nbsp;and Bob&nbsp;<strong>play optimally</strong>.</p>

<p>Return <em>&quot;Alice&quot;</em> if&nbsp;Alice will win, <em>&quot;Bob&quot;</em> if Bob will win or <em>&quot;Tie&quot;</em> if they end the game with the same score.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> values = [1,2,3,7]

<strong>Output:</strong> &quot;Bob&quot;

<strong>Explanation:</strong> Alice will always lose. Her best move will be to take three piles and the score become 6. Now the score of Bob is 7 and Bob wins.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> values = [1,2,3,-9]

<strong>Output:</strong> &quot;Alice&quot;

<strong>Explanation:</strong> Alice must choose all the three piles at the first move to win and leave Bob with negative score.

If Alice chooses one pile her score will be 1 and the next move Bob&#39;s score becomes 5. The next move Alice will take the pile with value = -9 and lose.

If Alice chooses two piles her score will be 3 and the next move Bob&#39;s score becomes 3. The next move Alice will take the pile with value = -9 and also lose.

Remember that both play optimally so here Alice will choose the scenario that makes her win.

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> values = [1,2,3,6]

<strong>Output:</strong> &quot;Tie&quot;

<strong>Explanation:</strong> Alice cannot win this game. She can end the game in a draw if she decided to choose all the first three piles, otherwise she will lose.

</pre>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input:</strong> values = [1,2,3,-1,-2,-3,7]

<strong>Output:</strong> &quot;Alice&quot;

</pre>

<p><strong>Example 5:</strong></p>

<pre>

<strong>Input:</strong> values = [-1,-2,-3]

<strong>Output:</strong> &quot;Tie&quot;

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= values.length &lt;= 50000</code></li>
	<li><code>-1000&nbsp;&lt;= values[i] &lt;= 1000</code></li>
</ul>

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
