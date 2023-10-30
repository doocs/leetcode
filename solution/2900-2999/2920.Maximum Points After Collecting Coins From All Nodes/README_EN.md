# [2920. Maximum Points After Collecting Coins From All Nodes](https://leetcode.com/problems/maximum-points-after-collecting-coins-from-all-nodes)

[中文文档](/solution/2900-2999/2920.Maximum%20Points%20After%20Collecting%20Coins%20From%20All%20Nodes/README.md)

## Description

<p>There exists an undirected tree rooted at node <code>0</code> with <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>. You are given a 2D <strong>integer</strong> array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree. You are also given a <strong>0-indexed</strong> array <code>coins</code> of size <code>n</code> where <code>coins[i]</code> indicates the number of coins in the vertex <code>i</code>, and an integer <code>k</code>.</p>

<p>Starting from the root, you have to collect all the coins such that the coins at a node can only be collected if the coins of its ancestors have been already collected.</p>

<p>Coins at <code>node<sub>i</sub></code> can be collected in one of the following ways:</p>

<ul>
	<li>Collect all the coins, but you will get <code>coins[i] - k</code> points. If <code>coins[i] - k</code> is negative then you will lose <code>abs(coins[i] - k)</code> points.</li>
	<li>Collect all the coins, but you will get <code>floor(coins[i] / 2)</code> points. If this way is used, then for all the <code>node<sub>j</sub></code> present in the subtree of <code>node<sub>i</sub></code>, <code>coins[j]</code> will get reduced to <code>floor(coins[j] / 2)</code>.</li>
</ul>

<p>Return <em>the <strong>maximum points</strong> you can get after collecting the coins from <strong>all</strong> the tree nodes.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2920.Maximum%20Points%20After%20Collecting%20Coins%20From%20All%20Nodes/ex1-copy.png" style="width: 60px; height: 316px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem;" />
<pre>
<strong>Input:</strong> edges = [[0,1],[1,2],[2,3]], coins = [10,10,3,3], k = 5
<strong>Output:</strong> 11                        
<strong>Explanation:</strong> 
Collect all the coins from node 0 using the first way. Total points = 10 - 5 = 5.
Collect all the coins from node 1 using the first way. Total points = 5 + (10 - 5) = 10.
Collect all the coins from node 2 using the second way so coins left at node 3 will be floor(3 / 2) = 1. Total points = 10 + floor(3 / 2) = 11.
Collect all the coins from node 3 using the second way. Total points = 11 + floor(1 / 2) = 11.
It can be shown that the maximum points we can get after collecting coins from all the nodes is 11. 
</pre>

<p><strong class="example">Example 2:</strong></p>
<strong class="example"> <img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2920.Maximum%20Points%20After%20Collecting%20Coins%20From%20All%20Nodes/ex2.png" style="width: 140px; height: 147px; padding: 10px; background: #fff; border-radius: .5rem;" /></strong>

<pre>
<strong>Input:</strong> edges = [[0,1],[0,2]], coins = [8,4,4], k = 0
<strong>Output:</strong> 16
<strong>Explanation:</strong> 
Coins will be collected from all the nodes using the first way. Therefore, total points = (8 - 0) + (4 - 0) + (4 - 0) = 16.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == coins.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code><font face="monospace">0 &lt;= coins[i] &lt;= 10<sup>4</sup></font></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code><font face="monospace">0 &lt;= edges[i][0], edges[i][1] &lt; n</font></code></li>
	<li><code><font face="monospace">0 &lt;= k &lt;= 10<sup>4</sup></font></code></li>
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
