# [2137. Pour Water Between Buckets to Make Water Levels Equal](https://leetcode.com/problems/pour-water-between-buckets-to-make-water-levels-equal)

[中文文档](/solution/2100-2199/2137.Pour%20Water%20Between%20Buckets%20to%20Make%20Water%20Levels%20Equal/README.md)

## Description

<p>You have <code>n</code> buckets each containing some gallons of water in it, represented by a <strong>0-indexed</strong> integer array <code>buckets</code>, where the <code>i<sup>th</sup></code> bucket contains <code>buckets[i]</code> gallons of water. You are also given an integer <code>loss</code>.</p>

<p>You want to make the amount of water in each bucket equal. You can pour any amount of water from one bucket to another bucket (not necessarily an integer). However, every time you pour <code>k</code> gallons of water, you spill <code>loss</code> <strong>percent</strong> of <code>k</code>.</p>

<p>Return <em>the <strong>maximum</strong> amount of water in each bucket after making the amount of water equal. </em>Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> buckets = [1,2,7], loss = 80
<strong>Output:</strong> 2.00000
<strong>Explanation:</strong> Pour 5 gallons of water from buckets[2] to buckets[0].
5 * 80% = 4 gallons are spilled and buckets[0] only receives 5 - 4 = 1 gallon of water.
All buckets have 2 gallons of water in them so return 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> buckets = [2,4,6], loss = 50
<strong>Output:</strong> 3.50000
<strong>Explanation:</strong> Pour 0.5 gallons of water from buckets[1] to buckets[0].
0.5 * 50% = 0.25 gallons are spilled and buckets[0] only receives 0.5 - 0.25 = 0.25 gallons of water.
Now, buckets = [2.25, 3.5, 6].
Pour 2.5 gallons of water from buckets[2] to buckets[0].
2.5 * 50% = 1.25 gallons are spilled and buckets[0] only receives 2.5 - 1.25 = 1.25 gallons of water.
All buckets have 3.5 gallons of water in them so return 3.5.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> buckets = [3,3,3,3], loss = 40
<strong>Output:</strong> 3.00000
<strong>Explanation:</strong> All buckets already have the same amount of water in them.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= buckets.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= buckets[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= loss &lt;= 99</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
