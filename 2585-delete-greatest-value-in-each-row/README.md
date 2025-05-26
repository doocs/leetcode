<h2><a href="https://leetcode.com/problems/delete-greatest-value-in-each-row">Delete Greatest Value in Each Row</a></h2> <img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' /><hr><p>You are given an <code>m x n</code> matrix <code>grid</code> consisting of positive integers.</p>

<p>Perform the following operation until <code>grid</code> becomes empty:</p>

<ul>
	<li>Delete the element with the greatest value from each row. If multiple such elements exist, delete any of them.</li>
	<li>Add the maximum of deleted elements to the answer.</li>
</ul>

<p><strong>Note</strong> that the number of columns decreases by one after each operation.</p>

<p>Return <em>the answer after performing the operations described above</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/10/19/q1ex1.jpg" style="width: 600px; height: 135px;" />
<pre>
<strong>Input:</strong> grid = [[1,2,4],[3,3,1]]
<strong>Output:</strong> 8
<strong>Explanation:</strong> The diagram above shows the removed values in each step.
- In the first operation, we remove 4 from the first row and 3 from the second row (notice that, there are two cells with value 3 and we can remove any of them). We add 4 to the answer.
- In the second operation, we remove 2 from the first row and 3 from the second row. We add 3 to the answer.
- In the third operation, we remove 1 from the first row and 1 from the second row. We add 1 to the answer.
The final answer = 4 + 3 + 1 = 8.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/10/19/q1ex2.jpg" style="width: 83px; height: 83px;" />
<pre>
<strong>Input:</strong> grid = [[10]]
<strong>Output:</strong> 10
<strong>Explanation:</strong> The diagram above shows the removed values in each step.
- In the first operation, we remove 10 from the first row. We add 10 to the answer.
The final answer = 10.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 100</code></li>
</ul>
