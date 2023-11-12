# [2931. Maximum Spending After Buying Items](https://leetcode.com/problems/maximum-spending-after-buying-items)

[中文文档](/solution/2900-2999/2931.Maximum%20Spending%20After%20Buying%20Items/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> <code>m * n</code> integer matrix <code>values</code>, representing the values of <code>m * n</code> different items in <code>m</code> different shops. Each shop has <code>n</code> items where the <code>j<sup>th</sup></code> item in the <code>i<sup>th</sup></code> shop has a value of <code>values[i][j]</code>. Additionally, the items in the <code>i<sup>th</sup></code> shop are sorted in non-increasing order of value. That is, <code>values[i][j] &gt;= values[i][j + 1]</code> for all <code>0 &lt;= j &lt; n - 1</code>.</p>

<p>On each day, you would like to buy a single item from one of the shops. Specifically, On the <code>d<sup>th</sup></code> day you can:</p>

<ul>
	<li>Pick any shop <code>i</code>.</li>
	<li>Buy the rightmost available item <code>j</code> for the price of <code>values[i][j] * d</code>. That is, find the greatest index <code>j</code> such that item <code>j</code> was never bought before, and buy it for the price of <code>values[i][j] * d</code>.</li>
</ul>

<p><strong>Note</strong> that all items are pairwise different. For example, if you have bought item <code>0</code> from shop <code>1</code>, you can still buy item <code>0</code> from any other shop.</p>

<p>Return <em>the <strong>maximum amount of money that can be spent</strong> on buying all </em> <code>m * n</code> <em>products</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> values = [[8,5,2],[6,4,1],[9,7,3]]
<strong>Output:</strong> 285
<strong>Explanation:</strong> On the first day, we buy product 2 from shop 1 for a price of values[1][2] * 1 = 1.
On the second day, we buy product 2 from shop 0 for a price of values[0][2] * 2 = 4.
On the third day, we buy product 2 from shop 2 for a price of values[2][2] * 3 = 9.
On the fourth day, we buy product 1 from shop 1 for a price of values[1][1] * 4 = 16.
On the fifth day, we buy product 1 from shop 0 for a price of values[0][1] * 5 = 25.
On the sixth day, we buy product 0 from shop 1 for a price of values[1][0] * 6 = 36.
On the seventh day, we buy product 1 from shop 2 for a price of values[2][1] * 7 = 49.
On the eighth day, we buy product 0 from shop 0 for a price of values[0][0] * 8 = 64.
On the ninth day, we buy product 0 from shop 2 for a price of values[2][0] * 9 = 81.
Hence, our total spending is equal to 285.
It can be shown that 285 is the maximum amount of money that can be spent buying all m * n products. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> values = [[10,8,6,4,2],[9,7,5,3,2]]
<strong>Output:</strong> 386
<strong>Explanation:</strong> On the first day, we buy product 4 from shop 0 for a price of values[0][4] * 1 = 2.
On the second day, we buy product 4 from shop 1 for a price of values[1][4] * 2 = 4.
On the third day, we buy product 3 from shop 1 for a price of values[1][3] * 3 = 9.
On the fourth day, we buy product 3 from shop 0 for a price of values[0][3] * 4 = 16.
On the fifth day, we buy product 2 from shop 1 for a price of values[1][2] * 5 = 25.
On the sixth day, we buy product 2 from shop 0 for a price of values[0][2] * 6 = 36.
On the seventh day, we buy product 1 from shop 1 for a price of values[1][1] * 7 = 49.
On the eighth day, we buy product 1 from shop 0 for a price of values[0][1] * 8 = 64
On the ninth day, we buy product 0 from shop 1 for a price of values[1][0] * 9 = 81.
On the tenth day, we buy product 0 from shop 0 for a price of values[0][0] * 10 = 100.
Hence, our total spending is equal to 386.
It can be shown that 386 is the maximum amount of money that can be spent buying all m * n products.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m == values.length &lt;= 10</code></li>
	<li><code>1 &lt;= n == values[i].length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= values[i][j] &lt;= 10<sup>6</sup></code></li>
	<li><code>values[i]</code> are sorted in non-increasing order.</li>
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
