# [2282. Number of People That Can Be Seen in a Grid](https://leetcode.com/problems/number-of-people-that-can-be-seen-in-a-grid)

[中文文档](/solution/2200-2299/2282.Number%20of%20People%20That%20Can%20Be%20Seen%20in%20a%20Grid/README.md)

## Description

<p>You are given an <code>m x n</code> <strong>0-indexed</strong> 2D array of positive integers <code>heights</code> where <code>heights[i][j]</code> is the height of the person standing at position <code>(i, j)</code>.</p>

<p>A person standing at position <code>(row<sub>1</sub>, col<sub>1</sub>)</code> can see a person standing at position <code>(row<sub>2</sub>, col<sub>2</sub>)</code> if:</p>

<ul>
	<li>The person at <code>(row<sub>2</sub>, col<sub>2</sub>)</code> is to the right <strong>or</strong> below the person at <code>(row<sub>1</sub>, col<sub>1</sub>)</code>. More formally, this means that either <code>row<sub>1</sub> == row<sub>2</sub></code> and <code>col<sub>1</sub> &lt; col<sub>2</sub></code> <strong>or</strong> <code>row<sub>1</sub> &lt; row<sub>2</sub></code> and <code>col<sub>1</sub> == col<sub>2</sub></code>.</li>
	<li>Everyone in between them is shorter than <strong>both</strong> of them.</li>
</ul>

<p>Return<em> an </em><code>m x n</code><em> 2D array of integers </em><code>answer</code><em> where </em><code>answer[i][j]</code><em> is the number of people that the person at position </em><code>(i, j)</code><em> can see.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2282.Number%20of%20People%20That%20Can%20Be%20Seen%20in%20a%20Grid/images/image-20220524180458-1.png" style="width: 700px; height: 164px;" />
<pre>
<strong>Input:</strong> heights = [[3,1,4,2,5]]
<strong>Output:</strong> [[2,1,2,1,0]]
<strong>Explanation:</strong>
- The person at (0, 0) can see the people at (0, 1) and (0, 2).
  Note that he cannot see the person at (0, 4) because the person at (0, 2) is taller than him.
- The person at (0, 1) can see the person at (0, 2).
- The person at (0, 2) can see the people at (0, 3) and (0, 4).
- The person at (0, 3) can see the person at (0, 4).
- The person at (0, 4) cannot see anybody.
</pre>

<p><strong>Example 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2282.Number%20of%20People%20That%20Can%20Be%20Seen%20in%20a%20Grid/images/image-20220523113533-2.png" style="width: 400px; height: 249px;" />
<pre>
<strong>Input:</strong> heights = [[5,1],[3,1],[4,1]]
<strong>Output:</strong> [[3,1],[2,1],[1,0]]
<strong>Explanation:</strong>
- The person at (0, 0) can see the people at (0, 1), (1, 0) and (2, 0).
- The person at (0, 1) can see the person at (1, 1).
- The person at (1, 0) can see the people at (1, 1) and (2, 0).
- The person at (1, 1) can see the person at (2, 1).
- The person at (2, 0) can see the person at (2, 1).
- The person at (2, 1) cannot see anybody.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= heights.length &lt;= 400</code></li>
	<li><code>1 &lt;= heights[i].length &lt;= 400</code></li>
	<li><code>1 &lt;= heights[i][j] &lt;= 10<sup>5</sup></code></li>
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
