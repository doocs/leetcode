# [3067. Count Pairs of Connectable Servers in a Weighted Tree Network](https://leetcode.com/problems/count-pairs-of-connectable-servers-in-a-weighted-tree-network)

[中文文档](/solution/3000-3099/3067.Count%20Pairs%20of%20Connectable%20Servers%20in%20a%20Weighted%20Tree%20Network/README.md)

<!-- tags: -->

## Description

<p>You are given an unrooted weighted tree with <code>n</code> vertices representing servers numbered from <code>0</code> to <code>n - 1</code>, an array <code>edges</code> where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>, weight<sub>i</sub>]</code> represents a bidirectional edge between vertices <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> of weight <code>weight<sub>i</sub></code>. You are also given an integer <code>signalSpeed</code>.</p>

<p>Two servers <code>a</code> and <code>b</code> are <strong>connectable</strong> through a server <code>c</code> if:</p>

<ul>
	<li><code>a &lt; b</code>, <code>a != c</code> and <code>b != c</code>.</li>
	<li>The distance from <code>c</code> to <code>a</code> is divisible by <code>signalSpeed</code>.</li>
	<li>The distance from <code>c</code> to <code>b</code> is divisible by <code>signalSpeed</code>.</li>
	<li>The path from <code>c</code> to <code>b</code> and the path from <code>c</code> to <code>a</code> do not share any edges.</li>
</ul>

<p>Return <em>an integer array</em> <code>count</code> <em>of length</em> <code>n</code> <em>where</em> <code>count[i]</code> <em>is the <strong>number</strong> of server pairs that are <strong>connectable</strong> through</em> <em>the server</em> <code>i</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3067.Count%20Pairs%20of%20Connectable%20Servers%20in%20a%20Weighted%20Tree%20Network/images/example22.png" style="width: 438px; height: 243px; padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>Input:</strong> edges = [[0,1,1],[1,2,5],[2,3,13],[3,4,9],[4,5,2]], signalSpeed = 1
<strong>Output:</strong> [0,4,6,6,4,0]
<strong>Explanation:</strong> Since signalSpeed is 1, count[c] is equal to the number of pairs of paths that start at c and do not share any edges.
In the case of the given path graph, count[c] is equal to the number of servers to the left of c multiplied by the servers to the right of c.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3067.Count%20Pairs%20of%20Connectable%20Servers%20in%20a%20Weighted%20Tree%20Network/images/example11.png" style="width: 495px; height: 484px; padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>Input:</strong> edges = [[0,6,3],[6,5,3],[0,3,1],[3,2,7],[3,1,6],[3,4,2]], signalSpeed = 3
<strong>Output:</strong> [2,0,0,0,0,0,2]
<strong>Explanation:</strong> Through server 0, there are 2 pairs of connectable servers: (4, 5) and (4, 6).
Through server 6, there are 2 pairs of connectable servers: (4, 5) and (0, 5).
It can be shown that no two servers are connectable through servers other than 0 and 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>, weight<sub>i</sub>]</code><!-- notionvc: a2623897-1bb1-4c07-84b6-917ffdcd83ec --></li>
	<li><code>1 &lt;= weight<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= signalSpeed &lt;= 10<sup>6</sup></code></li>
	<li>The input is generated such that <code>edges</code> represents a valid tree.</li>
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
