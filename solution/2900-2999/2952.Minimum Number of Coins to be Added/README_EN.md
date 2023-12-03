# [2952. Minimum Number of Coins to be Added](https://leetcode.com/problems/minimum-number-of-coins-to-be-added)

[中文文档](/solution/2900-2999/2952.Minimum%20Number%20of%20Coins%20to%20be%20Added/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>coins</code>, representing the values of the coins available, and an integer <code>target</code>.</p>

<p>An integer <code>x</code> is <strong>obtainable</strong> if there exists a subsequence of <code>coins</code> that sums to <code>x</code>.</p>

<p>Return <em>the<strong> minimum</strong> number of coins <strong>of any value</strong> that need to be added to the array so that every integer in the range</em> <code>[1, target]</code><em> is <strong>obtainable</strong></em>.</p>

<p>A <strong>subsequence</strong> of an array is a new <strong>non-empty</strong> array that is formed from the original array by deleting some (<strong>possibly none</strong>) of the elements without disturbing the relative positions of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> coins = [1,4,10], target = 19
<strong>Output:</strong> 2
<strong>Explanation:</strong> We need to add coins 2 and 8. The resulting array will be [1,2,4,8,10].
It can be shown that all integers from 1 to 19 are obtainable from the resulting array, and that 2 is the minimum number of coins that need to be added to the array. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> coins = [1,4,10,5,7,19], target = 19
<strong>Output:</strong> 1
<strong>Explanation:</strong> We only need to add the coin 2. The resulting array will be [1,2,4,5,7,10,19].
It can be shown that all integers from 1 to 19 are obtainable from the resulting array, and that 1 is the minimum number of coins that need to be added to the array. 
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> coins = [1,1,1], target = 20
<strong>Output:</strong> 3
<strong>Explanation:</strong> We need to add coins 4, 8, and 16. The resulting array will be [1,1,1,4,8,16].
It can be shown that all integers from 1 to 20 are obtainable from the resulting array, and that 3 is the minimum number of coins that need to be added to the array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= target &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= coins.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= coins[i] &lt;= target</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
