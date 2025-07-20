---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3553.Minimum%20Weighted%20Subgraph%20With%20the%20Required%20Paths%20II/README.md
rating: 2410
source: 第 450 场周赛 Q4
tags:
    - 树
    - 深度优先搜索
    - 数组
---

<!-- problem:start -->

# [3553. 包含给定路径的最小带权子树 II](https://leetcode.cn/problems/minimum-weighted-subgraph-with-the-required-paths-ii)

[English Version](/solution/3500-3599/3553.Minimum%20Weighted%20Subgraph%20With%20the%20Required%20Paths%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;<strong>无向带权&nbsp;</strong>树，共有 <code>n</code> 个节点，编号从 <code>0</code> 到 <code>n - 1</code>。这棵树由一个二维整数数组 <code>edges</code> 表示，长度为 <code>n - 1</code>，其中 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> 表示存在一条连接节点 <code>u<sub>i</sub></code> 和 <code>v<sub>i</sub></code> 的边，权重为 <code>w<sub>i</sub></code>。</p>

<p>此外，给你一个二维整数数组 <code>queries</code>，其中 <code>queries[j] = [src1<sub>j</sub>, src2<sub>j</sub>, dest<sub>j</sub>]</code>。</p>

<p>返回一个长度等于 <code>queries.length</code>&nbsp;的数组 <code>answer</code>，其中 <code>answer[j]</code> 表示一个子树的&nbsp;<strong>最小总权重&nbsp;</strong>，使用该子树的边可以从 <code>src1<sub>j</sub></code> 和 <code>src2<sub>j</sub></code> 到达 <code>dest<sub>j</sub></code><sub>&nbsp;</sub>。</p>

<p>这里的&nbsp;<strong>子树&nbsp;</strong>是指原树中任意节点和边组成的连通子集形成的一棵有效树。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">edges = [[0,1,2],[1,2,3],[1,3,5],[1,4,4],[2,5,6]], queries = [[2,3,4],[0,2,5]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[12,11]</span></p>

<p><strong>解释：</strong></p>

<p>蓝色边表示可以得到最优答案的子树之一。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3553.Minimum%20Weighted%20Subgraph%20With%20the%20Required%20Paths%20II/images/tree1-4.jpg" style="width: 531px; height: 322px;" /></p>

<ul>
	<li>
	<p><code>answer[0]</code>：在选出的子树中，从 <code>src1 = 2</code> 和 <code>src2 = 3</code> 到 <code>dest = 4</code> 的路径总权重为 <code>3 + 5 + 4 = 12</code>。</p>
	</li>
	<li>
	<p><code>answer[1]</code>：在选出的子树中，从 <code>src1 = 0</code> 和 <code>src2 = 2</code> 到 <code>dest = 5</code> 的路径总权重为 <code>2 + 3 + 6 = 11</code>。</p>
	</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">edges = [[1,0,8],[0,2,7]], queries = [[0,1,2]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[15]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3553.Minimum%20Weighted%20Subgraph%20With%20the%20Required%20Paths%20II/images/tree1-5.jpg" style="width: 270px; height: 80px;" /></p>

<ul>
	<li><code>answer[0]</code>：选出的子树中，从 <code>src1 = 0</code> 和 <code>src2 = 1</code> 到 <code>dest = 2</code> 的路径总权重为 <code>8 + 7 = 15</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[j].length == 3</code></li>
	<li><code>0 &lt;= src1<sub>j</sub>, src2<sub>j</sub>, dest<sub>j</sub> &lt; n</code></li>
	<li><code>src1<sub>j</sub></code>、<code>src2<sub>j</sub></code> 和 <code>dest<sub>j</sub></code>&nbsp;互不不同。</li>
	<li>输入数据保证 <code>edges</code> 表示的是一棵有效的树。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
