# [656. Coin Path](https://leetcode.com/problems/coin-path)

[中文文档](/solution/0600-0699/0656.Coin%20Path/README.md)

## Description

<p>You are given an integer array <code>coins</code> (<strong>1-indexed</strong>) of length <code>n</code> and an integer <code>maxJump</code>. You can jump to any index <code>i</code> of the array <code>coins</code> if <code>coins[i] != -1</code> and you have to pay <code>coins[i]</code> when you visit index <code>i</code>. In addition to that, if you are currently at index <code>i</code>, you can only jump to any index <code>i + k</code> where <code>i + k &lt;= n</code> and <code>k</code> is a value in the range <code>[1, maxJump]</code>.</p>

<p>You are initially positioned at index <code>1</code> (<code>coins[1]</code> is not <code>-1</code>). You want to find the path that reaches index n with the minimum cost.</p>

<p>Return an integer array of the indices that you will visit in order so that you can reach index n with the minimum cost. If there are multiple paths with the same cost, return the <strong>lexicographically smallest</strong> such path. If it is not possible to reach index n, return an empty array.</p>

<p>A path <code>p1 = [Pa<sub>1</sub>, Pa<sub>2</sub>, ..., Pa<sub>x</sub>]</code> of length <code>x</code> is <strong>lexicographically smaller</strong> than <code>p2 = [Pb<sub>1</sub>, Pb<sub>2</sub>, ..., Pb<sub>x</sub>]</code> of length <code>y</code>, if and only if at the first <code>j</code> where <code>Pa<sub>j</sub></code> and <code>Pb<sub>j</sub></code> differ, <code>Pa<sub>j</sub> &lt; Pb<sub>j</sub></code>; when no such <code>j</code> exists, then <code>x &lt; y</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> coins = [1,2,4,-1,2], maxJump = 2
<strong>Output:</strong> [1,3,5]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> coins = [1,2,4,-1,2], maxJump = 1
<strong>Output:</strong> []
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= coins.length &lt;= 1000</code></li>
	<li><code>-1 &lt;= coins[i] &lt;= 100</code></li>
	<li><code>coins[1] != -1</code></li>
	<li><code>1 &lt;= maxJump &lt;= 100</code></li>
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
