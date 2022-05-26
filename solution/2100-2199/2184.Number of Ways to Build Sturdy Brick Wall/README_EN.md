# [2184. Number of Ways to Build Sturdy Brick Wall](https://leetcode.com/problems/number-of-ways-to-build-sturdy-brick-wall)

[中文文档](/solution/2100-2199/2184.Number%20of%20Ways%20to%20Build%20Sturdy%20Brick%20Wall/README.md)

## Description

<p>You are given integers <code>height</code> and <code>width</code> which specify the dimensions of a brick wall you are building. You are also given a <strong>0-indexed</strong> array of <strong>unique</strong> integers <code>bricks</code>, where the <code>i<sup>th</sup></code> brick has a height of <code>1</code> and a width of <code>bricks[i]</code>. You have an <strong>infinite </strong>supply of each type of brick and bricks may <strong>not</strong> be rotated.</p>

<p>Each row in the wall must be exactly <code>width</code> units long. For the wall to be <strong>sturdy</strong>, adjacent rows in the wall should <strong>not </strong>join bricks at the same location, except at the ends of the wall.</p>

<p>Return <em>the number of ways to build a <strong>sturdy </strong>wall.</em> Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2184.Number%20of%20Ways%20to%20Build%20Sturdy%20Brick%20Wall/images/image-20220220190749-1.png" style="width: 919px; height: 250px;" />
<pre>
<strong>Input:</strong> height = 2, width = 3, bricks = [1,2]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
The first two walls in the diagram show the only two ways to build a sturdy brick wall.
Note that the third wall in the diagram is not sturdy because adjacent rows join bricks 2 units from the left.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> height = 1, width = 1, bricks = [5]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
There are no ways to build a sturdy wall because the only type of brick we have is longer than the width of the wall.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= height &lt;= 100</code></li>
	<li><code>1 &lt;= width &lt;= 10</code></li>
	<li><code>1 &lt;= bricks.length &lt;= 10</code></li>
	<li><code>1 &lt;= bricks[i] &lt;= 10</code></li>
	<li>All the values of <code>bricks</code> are <strong>unique</strong>.</li>
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
