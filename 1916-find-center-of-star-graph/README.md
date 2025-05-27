<h2><a href="https://leetcode.com/problems/find-center-of-star-graph">Find Center of Star Graph</a></h2> <img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' /><hr><p>There is an undirected <strong>star</strong> graph consisting of <code>n</code> nodes labeled from <code>1</code> to <code>n</code>. A star graph is a graph where there is one <strong>center</strong> node and <strong>exactly</strong> <code>n - 1</code> edges that connect the center node with every other node.</p>

<p>You are given a 2D integer array <code>edges</code> where each <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates that there is an edge between the nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>. Return the center of the given star graph.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/24/star_graph.png" style="width: 331px; height: 321px;" />
<pre>
<strong>Input:</strong> edges = [[1,2],[2,3],[4,2]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> As shown in the figure above, node 2 is connected to every other node, so 2 is the center.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> edges = [[1,2],[5,1],[1,3],[1,4]]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>1 &lt;= u<sub>i,</sub> v<sub>i</sub> &lt;= n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li>The given <code>edges</code> represent a valid star graph.</li>
</ul>
