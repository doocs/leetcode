# [2946. Matrix Similarity After Cyclic Shifts](https://leetcode.com/problems/matrix-similarity-after-cyclic-shifts)

[中文文档](/solution/2900-2999/2946.Matrix%20Similarity%20After%20Cyclic%20Shifts/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> <code>m x n</code> integer matrix <code>mat</code> and an integer <code>k</code>. You have to cyclically <strong>right</strong> shift <strong>odd</strong> indexed rows <code>k</code> times and cyclically <strong>left</strong> shift <strong>even</strong> indexed rows <code>k</code> times.</p>

<p>Return <code>true</code> <em>if the initial and final matrix are exactly the same and </em><code>false</code> <em>otherwise.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> mat = [[1,2,1,2],[5,5,5,5],[6,3,6,3]], k = 2
<strong>Output:</strong> true
<strong>Explanation:</strong>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2946.Matrix%20Similarity%20After%20Cyclic%20Shifts/images/similarmatrix.png" style="width: 500px; height: 117px;" />

Initially, the matrix looks like the first figure. 
Second figure represents the state of the matrix after one right and left cyclic shifts to even and odd indexed rows.
Third figure is the final state of the matrix after two cyclic shifts which is similar to the initial matrix.
Therefore, return true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> mat = [[2,2],[2,2]], k = 3
<strong>Output:</strong> true
<strong>Explanation:</strong> As all the values are equal in the matrix, even after performing cyclic shifts the matrix will remain the same. Therefeore, we return true.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> mat = [[1,2]], k = 1
<strong>Output:</strong> false
<strong>Explanation:</strong> After one cyclic shift, mat = [[2,1]] which is not equal to the initial matrix. Therefore we return false.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= mat.length &lt;= 25</code></li>
	<li><code>1 &lt;= mat[i].length &lt;= 25</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 25</code></li>
	<li><code>1 &lt;= k &lt;= 50</code></li>
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
