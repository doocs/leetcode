# [1140. Stone Game II](https://leetcode.com/problems/stone-game-ii)

[中文文档](/solution/1100-1199/1140.Stone%20Game%20II/README.md)

## Description

<p>Alice and Bob continue their&nbsp;games with piles of stones.&nbsp; There are a number of&nbsp;piles&nbsp;<strong>arranged in a row</strong>, and each pile has a positive integer number of stones&nbsp;<code>piles[i]</code>.&nbsp; The objective of the game is to end with the most&nbsp;stones.&nbsp;</p>

<p>Alice&nbsp;and Bob take turns, with Alice starting first.&nbsp; Initially, <code>M = 1</code>.</p>

<p>On each player&#39;s turn, that player&nbsp;can take <strong>all the stones</strong> in the <strong>first</strong> <code>X</code> remaining piles, where <code>1 &lt;= X &lt;= 2M</code>.&nbsp; Then, we set&nbsp;<code>M = max(M, X)</code>.</p>

<p>The game continues until all the stones have been taken.</p>

<p>Assuming Alice and Bob play optimally, return the maximum number of stones Alice&nbsp;can get.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> piles = [2,7,9,4,4]
<strong>Output:</strong> 10
<strong>Explanation:</strong>  If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 piles in total. If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 piles in total. So we return 10 since it&#39;s larger. 
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> piles = [1,2,3,4,5,100]
<strong>Output:</strong> 104
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= piles.length &lt;= 100</code></li>
	<li><code>1 &lt;= piles[i]&nbsp;&lt;= 10<sup>4</sup></code></li>
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
