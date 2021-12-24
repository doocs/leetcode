# [1561. Maximum Number of Coins You Can Get](https://leetcode.com/problems/maximum-number-of-coins-you-can-get)

[中文文档](/solution/1500-1599/1561.Maximum%20Number%20of%20Coins%20You%20Can%20Get/README.md)

## Description

<p>There are 3n&nbsp;piles of coins of&nbsp;varying size, you and your friends will take piles of coins as follows:</p>

<ul>
	<li>In each step, you will choose <strong>any&nbsp;</strong>3 piles of coins (not necessarily consecutive).</li>
	<li>Of your choice,&nbsp;Alice&nbsp;will pick&nbsp;the pile with the maximum number of coins.</li>
	<li>You will pick the next pile with maximum number of coins.</li>
	<li>Your friend Bob will pick the last pile.</li>
	<li>Repeat&nbsp;until&nbsp;there are no more piles of coins.</li>
</ul>

<p>Given an array of integers <code>piles</code>&nbsp;where <code>piles[i]</code> is the number of coins in the <code>i<sup>th</sup></code> pile.</p>

<p>Return the maximum number of coins which you can have.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> piles = [2,4,1,2,7,8]
<strong>Output:</strong> 9
<strong>Explanation: </strong>Choose the triplet (2, 7, 8), Alice Pick the pile with 8 coins, you the pile with <strong>7</strong> coins and Bob the last one.
Choose the triplet (1, 2, 4), Alice Pick the pile with 4 coins, you the pile with <strong>2</strong> coins and Bob the last one.
The maximum number of coins which you can have are: 7 + 2 = 9.
On the other hand if we choose this arrangement (1, <strong>2</strong>, 8), (2, <strong>4</strong>, 7) you only get 2 + 4 = 6 coins which is not optimal.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> piles = [2,4,5]
<strong>Output:</strong> 4
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> piles = [9,8,7,6,5,1,2,3,4]
<strong>Output:</strong> 18
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= piles.length &lt;= 10^5</code></li>
	<li><code>piles.length % 3 == 0</code></li>
	<li><code>1 &lt;= piles[i] &lt;= 10^4</code></li>
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
