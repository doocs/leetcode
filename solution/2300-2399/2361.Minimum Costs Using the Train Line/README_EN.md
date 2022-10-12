# [2361. Minimum Costs Using the Train Line](https://leetcode.com/problems/minimum-costs-using-the-train-line)

[中文文档](/solution/2300-2399/2361.Minimum%20Costs%20Using%20the%20Train%20Line/README.md)

## Description

<p>A train line going through a city has two routes, the regular route and the express route. Both routes go through the <strong>same</strong> <code>n + 1</code> stops labeled from <code>0</code> to <code>n</code>. Initially, you start on the regular route at stop <code>0</code>.</p>

<p>You are given two <strong>1-indexed</strong> integer arrays <code>regular</code> and <code>express</code>, both of length <code>n</code>. <code>regular[i]</code> describes the cost it takes to go from stop <code>i - 1</code> to stop <code>i</code> using the regular route, and <code>express[i]</code> describes the cost it takes to go from stop <code>i - 1</code> to stop <code>i</code> using the express route.</p>

<p>You are also given an integer <code>expressCost</code> which represents the cost to transfer from the regular route to the express route.</p>

<p>Note that:</p>

<ul>
	<li>There is no cost to transfer from the express route back to the regular route.</li>
	<li>You pay <code>expressCost</code> <strong>every</strong> time you transfer from the regular route to the express route.</li>
	<li>There is no extra cost to stay on the express route.</li>
</ul>

<p>Return <em>a <strong>1-indexed</strong> array </em><code>costs</code><em> of length </em><code>n</code><em>, where </em><code>costs[i]</code><em> is the <strong>minimum</strong> cost to reach stop </em><code>i</code><em> from stop </em><code>0</code>.</p>

<p>Note that a stop can be counted as <strong>reached</strong> from either route.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2361.Minimum%20Costs%20Using%20the%20Train%20Line/images/ex1drawio.png" style="width: 442px; height: 150px;" />
<pre>
<strong>Input:</strong> regular = [1,6,9,5], express = [5,2,3,10], expressCost = 8
<strong>Output:</strong> [1,7,14,19]
<strong>Explanation:</strong> The diagram above shows how to reach stop 4 from stop 0 with minimum cost.
- Take the regular route from stop 0 to stop 1, costing 1.
- Take the express route from stop 1 to stop 2, costing 8 + 2 = 10.
- Take the express route from stop 2 to stop 3, costing 3.
- Take the regular route from stop 3 to stop 4, costing 5.
The total cost is 1 + 10 + 3 + 5 = 19.
Note that a different route could be taken to reach the other stops with minimum cost.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2361.Minimum%20Costs%20Using%20the%20Train%20Line/images/ex2drawio.png" style="width: 346px; height: 150px;" />
<pre>
<strong>Input:</strong> regular = [11,5,13], express = [7,10,6], expressCost = 3
<strong>Output:</strong> [10,15,24]
<strong>Explanation:</strong> The diagram above shows how to reach stop 3 from stop 0 with minimum cost.
- Take the express route from stop 0 to stop 1, costing 3 + 7 = 10.
- Take the regular route from stop 1 to stop 2, costing 5.
- Take the express route from stop 2 to stop 3, costing 3 + 6 = 9.
The total cost is 10 + 5 + 9 = 24.
Note that the expressCost is paid again to transfer back to the express route.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == regular.length == express.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= regular[i], express[i], expressCost &lt;= 10<sup>5</sup></code></li>
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
