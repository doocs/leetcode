# [1042. Flower Planting With No Adjacent](https://leetcode.com/problems/flower-planting-with-no-adjacent)

[中文文档](/solution/1000-1099/1042.Flower%20Planting%20With%20No%20Adjacent/README.md)

## Description

<p>You have <code>n</code> gardens, labeled from <code>1</code> to <code>n</code>, and an array <code>paths</code> where <code>paths[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> describes a bidirectional path between garden <code>x<sub>i</sub></code> to garden <code>y<sub>i</sub></code>. In each garden, you want to plant one of 4 types of flowers.</p>

<p>All gardens have <strong>at most 3</strong> paths coming into or leaving it.</p>

<p>Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.</p>

<p>Return <em><strong>any</strong> such a choice as an array </em><code>answer</code><em>, where </em><code>answer[i]</code><em> is the type of flower planted in the </em><code>(i+1)<sup>th</sup></code><em> garden. The flower types are denoted </em><code>1</code><em>, </em><code>2</code><em>, </em><code>3</code><em>, or </em><code>4</code><em>. It is guaranteed an answer exists.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, paths = [[1,2],[2,3],[3,1]]
<strong>Output:</strong> [1,2,3]
<strong>Explanation:</strong>
Gardens 1 and 2 have different types.
Gardens 2 and 3 have different types.
Gardens 3 and 1 have different types.
Hence, [1,2,3] is a valid answer. Other valid answers include [1,2,4], [1,4,2], and [3,2,1].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4, paths = [[1,2],[3,4]]
<strong>Output:</strong> [1,2,1,2]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
<strong>Output:</strong> [1,2,3,4]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= paths.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>paths[i].length == 2</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= n</code></li>
	<li><code>x<sub>i</sub> != y<sub>i</sub></code></li>
	<li>Every garden has <strong>at most 3</strong> paths coming into or leaving it.</li>
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
