# [699. Falling Squares](https://leetcode.com/problems/falling-squares)

[中文文档](/solution/0600-0699/0699.Falling%20Squares/README.md)

## Description

<p>There are several squares being dropped onto the X-axis of a 2D plane.</p>

<p>You are given a 2D integer array <code>positions</code> where <code>positions[i] = [left<sub>i</sub>, sideLength<sub>i</sub>]</code> represents the <code>i<sup>th</sup></code> square with a side length of <code>sideLength<sub>i</sub></code> that is dropped with its left edge aligned with X-coordinate <code>left<sub>i</sub></code>.</p>

<p>Each square is dropped one at a time from a height above any landed squares. It then falls downward (negative Y direction) until it either lands <strong>on the top side of another square</strong> or <strong>on the X-axis</strong>. A square brushing the left/right side of another square does not count as landing on it. Once it lands, it freezes in place and cannot be moved.</p>

<p>After each square is dropped, you must record the <strong>height of the current tallest stack of squares</strong>.</p>

<p>Return <em>an integer array </em><code>ans</code><em> where </em><code>ans[i]</code><em> represents the height described above after dropping the </em><code>i<sup>th</sup></code><em> square</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0699.Falling%20Squares/images/fallingsq1-plane.jpg" style="width: 500px; height: 505px;" />
<pre>
<strong>Input:</strong> positions = [[1,2],[2,3],[6,1]]
<strong>Output:</strong> [2,5,5]
<strong>Explanation:</strong>
After the first drop, the tallest stack is square 1 with a height of 2.
After the second drop, the tallest stack is squares 1 and 2 with a height of 5.
After the third drop, the tallest stack is still squares 1 and 2 with a height of 5.
Thus, we return an answer of [2, 5, 5].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> positions = [[100,100],[200,100]]
<strong>Output:</strong> [100,100]
<strong>Explanation:</strong>
After the first drop, the tallest stack is square 1 with a height of 100.
After the second drop, the tallest stack is either square 1 or square 2, both with heights of 100.
Thus, we return an answer of [100, 100].
Note that square 2 only brushes the right side of square 1, which does not count as landing on it.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= positions.length &lt;= 1000</code></li>
	<li><code>1 &lt;= left<sub>i</sub> &lt;= 10<sup>8</sup></code></li>
	<li><code>1 &lt;= sideLength<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
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
