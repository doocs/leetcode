# [1510. Stone Game IV](https://leetcode.com/problems/stone-game-iv)

[中文文档](/solution/1500-1599/1510.Stone%20Game%20IV/README.md)

## Description

<p>Alice and Bob take turns playing a game, with Alice starting first.</p>

<p>Initially, there are <code>n</code> stones in a pile.&nbsp; On each player&#39;s turn, that player makes a&nbsp;<em>move</em>&nbsp;consisting of removing <strong>any</strong> non-zero <strong>square number</strong> of stones in the pile.</p>

<p>Also, if a player cannot make a move, he/she loses the game.</p>

<p>Given a positive&nbsp;integer <code>n</code>.&nbsp;Return&nbsp;<code>True</code>&nbsp;if and only if Alice wins the game otherwise return <code>False</code>, assuming both players play optimally.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> true
<strong>Explanation: </strong>Alice can remove 1 stone winning the game because Bob doesn&#39;t have any moves.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> false
<strong>Explanation: </strong>Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -&gt; 1 -&gt; 0).</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> true
<strong>Explanation:</strong> n is already a perfect square, Alice can win with one move, removing 4 stones (4 -&gt; 0).
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> n = 7
<strong>Output:</strong> false
<strong>Explanation: </strong>Alice can&#39;t win the game if Bob plays optimally.
If Alice starts removing 4 stones, Bob will remove 1 stone then Alice should remove only 1 stone and finally Bob removes the last one (7 -&gt; 3 -&gt; 2 -&gt; 1 -&gt; 0). 
If Alice starts removing 1 stone, Bob will remove 4 stones then Alice only can remove 1 stone and finally Bob removes the last one (7 -&gt; 6 -&gt; 2 -&gt; 1 -&gt; 0).</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> n = 17
<strong>Output:</strong> false
<strong>Explanation: </strong>Alice can&#39;t win the game if Bob plays optimally.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
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
