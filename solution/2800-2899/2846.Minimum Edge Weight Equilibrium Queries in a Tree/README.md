# [2846. 边权重均等查询](https://leetcode.cn/problems/minimum-edge-weight-equilibrium-queries-in-a-tree)

[English Version](/solution/2800-2899/2846.Minimum%20Edge%20Weight%20Equilibrium%20Queries%20in%20a%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现有一棵由 <code>n</code> 个节点组成的无向树，节点按从 <code>0</code> 到 <code>n - 1</code> 编号。给你一个整数 <code>n</code> 和一个长度为 <code>n - 1</code> 的二维整数数组 <code>edges</code> ，其中 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> 表示树中存在一条位于节点 <code>u<sub>i</sub></code> 和节点 <code>v<sub>i</sub></code> 之间、权重为 <code>w<sub>i</sub></code> 的边。</p>

<p>另给你一个长度为 <code>m</code> 的二维整数数组 <code>queries</code> ，其中 <code>queries[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 。对于每条查询，请你找出使从 <code>a<sub>i</sub></code> 到 <code>b<sub>i</sub></code> 路径上每条边的权重相等所需的 <strong>最小操作次数</strong> 。在一次操作中，你可以选择树上的任意一条边，并将其权重更改为任意值。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>查询之间 <strong>相互独立</strong> 的，这意味着每条新的查询时，树都会回到 <strong>初始状态</strong> 。</li>
	<li>从 <code>a<sub>i</sub></code> 到 <code>b<sub>i</sub></code>的路径是一个由 <strong>不同</strong> 节点组成的序列，从节点 <code>a<sub>i</sub></code> 开始，到节点 <code>b<sub>i</sub></code> 结束，且序列中相邻的两个节点在树中共享一条边。</li>
</ul>

<p>返回一个长度为 <code>m</code> 的数组 <code>answer</code> ，其中 <code>answer[i]</code> 是第 <code>i</code> 条查询的答案。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2023/08/11/graph-6-1.png" style="width: 339px; height: 344px;" />
<pre>
<strong>输入：</strong>n = 7, edges = [[0,1,1],[1,2,1],[2,3,1],[3,4,2],[4,5,2],[5,6,2]], queries = [[0,3],[3,6],[2,6],[0,6]]
<strong>输出：</strong>[0,0,1,3]
<strong>解释：</strong>第 1 条查询，从节点 0 到节点 3 的路径中的所有边的权重都是 1 。因此，答案为 0 。
第 2 条查询，从节点 3 到节点 6 的路径中的所有边的权重都是 2 。因此，答案为 0 。
第 3 条查询，将边 [2,3] 的权重变更为 2 。在这次操作之后，从节点 2 到节点 6 的路径中的所有边的权重都是 2 。因此，答案为 1 。
第 4 条查询，将边 [0,1]、[1,2]、[2,3] 的权重变更为 2 。在这次操作之后，从节点 0 到节点 6 的路径中的所有边的权重都是 2 。因此，答案为 3 。
对于每条查询 queries[i] ，可以证明 answer[i] 是使从 a<sub>i</sub> 到 b<sub>i</sub> 的路径中的所有边的权重相等的最小操作次数。
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2023/08/11/graph-9-1.png" style="width: 472px; height: 370px;" />
<pre>
<strong>输入：</strong>n = 8, edges = [[1,2,6],[1,3,4],[2,4,6],[2,5,3],[3,6,6],[3,0,8],[7,0,2]], queries = [[4,6],[0,4],[6,5],[7,4]]
<strong>输出：</strong>[1,2,2,3]
<strong>解释：</strong>第 1 条查询，将边 [1,3] 的权重变更为 6 。在这次操作之后，从节点 4 到节点 6 的路径中的所有边的权重都是 6 。因此，答案为 1 。
第 2 条查询，将边 [0,3]、[3,1] 的权重变更为 6 。在这次操作之后，从节点 0 到节点 4 的路径中的所有边的权重都是 6 。因此，答案为 2 。
第 3 条查询，将边 [1,3]、[5,2] 的权重变更为 6 。在这次操作之后，从节点 6 到节点 5 的路径中的所有边的权重都是 6 。因此，答案为 2 。
第 4 条查询，将边 [0,7]、[0,3]、[1,3] 的权重变更为 6 。在这次操作之后，从节点 7 到节点 4 的路径中的所有边的权重都是 6 。因此，答案为 3 。
对于每条查询 queries[i] ，可以证明 answer[i] 是使从 a<sub>i</sub> 到 b<sub>i</sub> 的路径中的所有边的权重相等的最小操作次数。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 26</code></li>
	<li>生成的输入满足 <code>edges</code> 表示一棵有效的树</li>
	<li><code>1 &lt;= queries.length == m &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
