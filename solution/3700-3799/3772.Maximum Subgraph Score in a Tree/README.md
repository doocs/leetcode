---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3772.Maximum%20Subgraph%20Score%20in%20a%20Tree/README.md
rating: 2234
source: 第 479 场周赛 Q4
tags:
    - 树
    - 深度优先搜索
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [3772. 树中子图的最大得分](https://leetcode.cn/problems/maximum-subgraph-score-in-a-tree)

[English Version](/solution/3700-3799/3772.Maximum%20Subgraph%20Score%20in%20a%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;<strong>无向树&nbsp;</strong>，它包含 <code>n</code> 个节点，编号从 0 到 <code>n - 1</code>。树由一个长度为 <code>n - 1</code> 的二维整数数组 <code>edges</code> 描述，其中 <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 表示在节点 <code>a<sub>i</sub></code> 和节点 <code>b<sub>i</sub></code> 之间有一条边。</p>

<p>另给你一个长度为 <code>n</code> 的整数数组 <code>good</code>，其中 <code>good[i]</code> 为 1 表示第 <code>i</code> 个节点是好节点，为 0 表示它是坏节点。</p>

<p>定义&nbsp;<strong>子图&nbsp;</strong>的&nbsp;<strong>得分&nbsp;</strong>为子图中好节点的数量减去坏节点的数量。</p>

<p>对于每个节点 <code>i</code>，找到包含节点 <code>i</code> 的所有&nbsp;<strong>连通子图&nbsp;</strong>中可能的最大得分。</p>

<p>返回一个长度为 <code>n</code> 的整数数组，其中第 <code>i</code> 个元素是节点 <code>i</code> 的&nbsp;<strong>最大得分&nbsp;</strong>。</p>

<p><strong>子图&nbsp;</strong>是原图的一个子集，其顶点和边均来自原图。</p>

<p><strong>连通子图&nbsp;</strong>是一个子图，其中每一对顶点都可以通过该子图的边相互到达。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="Tree Example 1" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3700-3799/3772.Maximum%20Subgraph%20Score%20in%20a%20Tree/images/tree1fixed.png" style="width: 271px; height: 51px;" /></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 3, edges = [[0,1],[1,2]], good = [1,0,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,1,1]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>绿色节点是好节点，红色节点是坏节点。</li>
	<li>对于每个节点，包含它的最佳连通子图是整棵树，该树有 2 个好节点和 1 个坏节点，得分为 1。</li>
	<li>包含某个节点的其他连通子图可能有相同的得分。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<p><img alt="Tree Example 2" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3700-3799/3772.Maximum%20Subgraph%20Score%20in%20a%20Tree/images/tree2.png" style="width: 211px; height: 231px;" /></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 5, edges = [[1,0],[1,2],[1,3],[3,4]], good = [0,1,0,1,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">[2,3,2,3,3]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>节点 0：最佳连通子图由节点 <code>0, 1, 3, 4</code> 组成，其中有 3 个好节点和 1 个坏节点，得分为 <code>3 - 1 = 2</code>。</li>
	<li>节点 1、3 和 4：最佳连通子图由节点 <code>1, 3, 4</code> 组成，其中有 3 个好节点，得分为 3。</li>
	<li>节点 2：最佳连通子图由节点 <code>1, 2, 3, 4</code> 组成，其中有 3 个好节点和 1 个坏节点，得分为 <code>3 - 1 = 2</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<p><img alt="Tree Example 3" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3700-3799/3772.Maximum%20Subgraph%20Score%20in%20a%20Tree/images/tree3.png" style="width: 161px; height: 51px;" /></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 2, edges = [[0,1]], good = [0,0]</span></p>

<p><strong>输出：</strong> <span class="example-io">[-1,-1]</span></p>

<p><strong>解释：</strong></p>

<p>对于每个节点，包含另一节点只会增加一个坏节点，因此每个节点的最佳得分为 -1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>good.length == n</code></li>
	<li><code>0 &lt;= good[i] &lt;= 1</code></li>
	<li>输入保证 <code>edges</code> 表示一棵有效树。</li>
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
