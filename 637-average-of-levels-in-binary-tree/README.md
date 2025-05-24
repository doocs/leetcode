<h2><a href="https://leetcode.com/problems/average-of-levels-in-binary-tree">Average of Levels in Binary Tree</a></h2> <img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' /><hr>Given the <code>root</code> of a binary tree, return <em>the average value of the nodes on each level in the form of an array</em>. Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/09/avg1-tree.jpg" style="width: 277px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [3,9,20,null,null,15,7]
<strong>Output:</strong> [3.00000,14.50000,11.00000]
Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
Hence return [3, 14.5, 11].
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/09/avg2-tree.jpg" style="width: 292px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [3,9,20,15,7]
<strong>Output:</strong> [3.00000,14.50000,11.00000]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-2<sup>31</sup> &lt;= Node.val &lt;= 2<sup>31</sup> - 1</code></li>
</ul>
