# [2857. Count Pairs of Points With Distance k](https://leetcode.com/problems/count-pairs-of-points-with-distance-k)

[中文文档](/solution/2800-2899/2857.Count%20Pairs%20of%20Points%20With%20Distance%20k/README.md)

## Description

<p>You are given a <strong>2D</strong> integer array <code>coordinates</code> and an integer <code>k</code>, where <code>coordinates[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> are the coordinates of the <code>i<sup>th</sup></code> point in a 2D plane.</p>

<p>We define the <strong>distance</strong> between two points <code>(x<sub>1</sub>, y<sub>1</sub>)</code> and <code>(x<sub>2</sub>, y<sub>2</sub>)</code> as <code>(x1 XOR x2) + (y1 XOR y2)</code> where <code>XOR</code> is the bitwise <code>XOR</code> operation.</p>

<p>Return <em>the number of pairs </em><code>(i, j)</code><em> such that </em><code>i &lt; j</code><em> and the distance between points </em><code>i</code><em> and </em><code>j</code><em> is equal to </em><code>k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> coordinates = [[1,2],[4,2],[1,3],[5,2]], k = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can choose the following pairs:
- (0,1): Because we have (1 XOR 4) + (2 XOR 2) = 5.
- (2,3): Because we have (1 XOR 5) + (3 XOR 2) = 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> coordinates = [[1,3],[1,3],[1,3],[1,3],[1,3]], k = 0
<strong>Output:</strong> 10
<strong>Explanation:</strong> Any two chosen pairs will have a distance of 0. There are 10 ways to choose two pairs.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= coordinates.length &lt;= 50000</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li><code>0 &lt;= k &lt;= 100</code></li>
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
