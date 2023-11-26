# [2944. Minimum Number of Coins for Fruits](https://leetcode.com/problems/minimum-number-of-coins-for-fruits)

[中文文档](/solution/2900-2999/2944.Minimum%20Number%20of%20Coins%20for%20Fruits/README.md)

## Description

<p>You are at a fruit market with different types of exotic fruits on display.</p>

<p>You are given a <strong>1-indexed</strong> array <code>prices</code>, where <code>prices[i]</code> denotes the number of coins needed to purchase the <code>i<sup>th</sup></code> fruit.</p>

<p>The fruit market has the following offer:</p>

<ul>
	<li>If you purchase the <code>i<sup>th</sup></code> fruit at <code>prices[i]</code> coins, you can get the next <code>i</code> fruits for free.</li>
</ul>

<p><strong>Note</strong> that even if you <strong>can</strong> take fruit <code>j</code> for free, you can still purchase it for <code>prices[j]</code> coins to receive a new offer.</p>

<p>Return <em>the <strong>minimum</strong> number of coins needed to acquire all the fruits</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [3,1,2]
<strong>Output:</strong> 4
<strong>Explanation:</strong> You can acquire the fruits as follows:
- Purchase the 1<sup>st</sup> fruit with 3 coins, you are allowed to take the 2<sup>nd</sup> fruit for free.
- Purchase the 2<sup>nd</sup> fruit with 1 coin, you are allowed to take the 3<sup>rd</sup> fruit for free.
- Take the 3<sup>rd</sup> fruit for free.
Note that even though you were allowed to take the 2<sup>nd</sup> fruit for free, you purchased it because it is more optimal.
It can be proven that 4 is the minimum number of coins needed to acquire all the fruits.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [1,10,1,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> You can acquire the fruits as follows:
- Purchase the 1<sup>st</sup> fruit with 1 coin, you are allowed to take the 2<sup>nd</sup> fruit for free.
- Take the 2<sup>nd</sup> fruit for free.
- Purchase the 3<sup>rd</sup> fruit for 1 coin, you are allowed to take the 4<sup>th</sup> fruit for free.
- Take the 4<sup>t</sup><sup>h</sup> fruit for free.
It can be proven that 2 is the minimum number of coins needed to acquire all the fruits.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 1000</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
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
