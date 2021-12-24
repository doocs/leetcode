# [1535. Find the Winner of an Array Game](https://leetcode.com/problems/find-the-winner-of-an-array-game)

[中文文档](/solution/1500-1599/1535.Find%20the%20Winner%20of%20an%20Array%20Game/README.md)

## Description

<p>Given an integer array <code>arr</code> of <strong>distinct</strong> integers and an integer <code>k</code>.</p>

<p>A game will be played between the first two elements of the array (i.e. <code>arr[0]</code> and <code>arr[1]</code>). In each round of the game, we compare <code>arr[0]</code> with <code>arr[1]</code>, the larger integer&nbsp;wins and remains at position <code>0</code> and the smaller integer moves to the end of the array. The game ends when an integer wins <code>k</code> consecutive rounds.</p>

<p>Return <em>the integer which will win the game</em>.</p>

<p>It is <strong>guaranteed</strong> that there will be a winner of the game.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> arr = [2,1,3,5,4,6,7], k = 2

<strong>Output:</strong> 5

<strong>Explanation:</strong> Let&#39;s see the rounds of the game:

Round |       arr       | winner | win_count

  1   | [2,1,3,5,4,6,7] | 2      | 1

  2   | [2,3,5,4,6,7,1] | 3      | 1

  3   | [3,5,4,6,7,1,2] | 5      | 1

  4   | [5,4,6,7,1,2,3] | 5      | 2

So we can see that 4 rounds will be played and 5 is the winner because it wins 2 consecutive games.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> arr = [3,2,1], k = 10

<strong>Output:</strong> 3

<strong>Explanation:</strong> 3 will win the first 10 rounds consecutively.

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> arr = [1,9,8,2,3,7,6,4,5], k = 7

<strong>Output:</strong> 9

</pre>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input:</strong> arr = [1,11,22,33,44,55,66,77,88,99], k = 1000000000

<strong>Output:</strong> 99

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 10^5</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10^6</code></li>
	<li><code>arr</code> contains <b>distinct</b>&nbsp;integers.</li>
	<li><code>1 &lt;= k &lt;= 10^9</code></li>
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
