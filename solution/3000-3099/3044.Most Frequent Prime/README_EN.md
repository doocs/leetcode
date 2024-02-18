# [3044. Most Frequent Prime](https://leetcode.com/problems/most-frequent-prime)

[中文文档](/solution/3000-3099/3044.Most%20Frequent%20Prime/README.md)

## Description

<p>You are given a <code>m x n</code> <strong>0-indexed </strong>2D<strong> </strong>matrix <code>mat</code>. From every cell, you can create numbers in the following way:</p>

<ul>
	<li>There could be at most <code>8</code> paths from the cells namely: east, south-east, south, south-west, west, north-west, north, and north-east.</li>
	<li>Select a path from them and append digits in this path to the number being formed by traveling in this direction.</li>
	<li>Note that numbers are generated at every step, for example, if the digits along the path are <code>1, 9, 1</code>, then there will be three numbers generated along the way: <code>1, 19, 191</code>.</li>
</ul>

<p>Return <em>the most frequent <span data-keyword="prime-number">prime number</span> <strong>greater</strong> than </em><code>10</code><em> out of all the numbers created by traversing the matrix or </em><code>-1</code><em> if no such prime number exists. If there are multiple prime numbers with the highest frequency, then return the <b>largest</b> among them.</em></p>

<p><strong>Note:</strong> It is invalid to change the direction during the move.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3044.Most%20Frequent%20Prime/images/south" style="width: 641px; height: 291px;" /> </strong>

<pre>
<strong>
Input:</strong> mat = [[1,1],[9,9],[1,1]]
<strong>Output:</strong> 19
<strong>Explanation:</strong> 
From cell (0,0) there are 3 possible directions and the numbers greater than 10 which can be created in those directions are:
East: [11], South-East: [19], South: [19,191].
Numbers greater than 10 created from the cell (0,1) in all possible directions are: [19,191,19,11].
Numbers greater than 10 created from the cell (1,0) in all possible directions are: [99,91,91,91,91].
Numbers greater than 10 created from the cell (1,1) in all possible directions are: [91,91,99,91,91].
Numbers greater than 10 created from the cell (2,0) in all possible directions are: [11,19,191,19].
Numbers greater than 10 created from the cell (2,1) in all possible directions are: [11,19,19,191].
The most frequent prime number among all the created numbers is 19.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> mat = [[7]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> The only number which can be formed is 7. It is a prime number however it is not greater than 10, so return -1.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> mat = [[9,7,8],[4,6,5],[2,8,6]]
<strong>Output:</strong> 97
<strong>Explanation:</strong> 
Numbers greater than 10 created from the cell (0,0) in all possible directions are: [97,978,96,966,94,942].
Numbers greater than 10 created from the cell (0,1) in all possible directions are: [78,75,76,768,74,79].
Numbers greater than 10 created from the cell (0,2) in all possible directions are: [85,856,86,862,87,879].
Numbers greater than 10 created from the cell (1,0) in all possible directions are: [46,465,48,42,49,47].
Numbers greater than 10 created from the cell (1,1) in all possible directions are: [65,66,68,62,64,69,67,68].
Numbers greater than 10 created from the cell (1,2) in all possible directions are: [56,58,56,564,57,58].
Numbers greater than 10 created from the cell (2,0) in all possible directions are: [28,286,24,249,26,268].
Numbers greater than 10 created from the cell (2,1) in all possible directions are: [86,82,84,86,867,85].
Numbers greater than 10 created from the cell (2,2) in all possible directions are: [68,682,66,669,65,658].
The most frequent prime number among all the created numbers is 97.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 6</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 9</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
