# [1908. Game of Nim](https://leetcode.com/problems/game-of-nim)

[中文文档](/solution/1900-1999/1908.Game%20of%20Nim/README.md)

## Description

<p>Alice and Bob take turns playing a game with <strong>Alice starting first</strong>.</p>

<p>In this game, there are <code>n</code> piles of stones. On each player&#39;s turn, the player should remove any <strong>positive</strong> number of stones from a non-empty pile <strong>of his or her choice</strong>. The first player who cannot make a move loses, and the other player wins.</p>

<p>Given an integer array <code>piles</code>, where <code>piles[i]</code> is the number of stones in the <code>i<sup>th</sup></code> pile, return <code>true</code><em> if Alice wins, or </em><code>false</code><em> if Bob wins</em>.</p>

<p>Both Alice and Bob play <strong>optimally</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> piles = [1]
<strong>Output:</strong> true
<strong>Explanation:</strong> There is only one possible scenario:
- On the first turn, Alice removes one stone from the first pile. piles = [0].
- On the second turn, there are no stones left for Bob to remove. Alice wins.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> piles = [1,1]
<strong>Output:</strong> false
<strong>Explanation:</strong> It can be proven that Bob will always win. One possible scenario is:
- On the first turn, Alice removes one stone from the first pile. piles = [0,1].
- On the second turn, Bob removes one stone from the second pile. piles = [0,0].
- On the third turn, there are no stones left for Alice to remove. Bob wins.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> piles = [1,2,3]
<strong>Output:</strong> false
<strong>Explanation:</strong> It can be proven that Bob will always win. One possible scenario is:
- On the first turn, Alice removes three stones from the third pile. piles = [1,2,0].
- On the second turn, Bob removes one stone from the second pile. piles = [1,1,0].
- On the third turn, Alice removes one stone from the first pile. piles = [0,1,0].
- On the fourth turn, Bob removes one stone from the second pile. piles = [0,0,0].
- On the fifth turn, there are no stones left for Alice to remove. Bob wins.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == piles.length</code></li>
	<li><code>1 &lt;= n &lt;= 7</code></li>
	<li><code>1 &lt;= piles[i] &lt;= 7</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow-up:</strong> Could you find a linear time solution? Although the linear time solution may be beyond the scope of an interview, it could be interesting to know.</p>

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
