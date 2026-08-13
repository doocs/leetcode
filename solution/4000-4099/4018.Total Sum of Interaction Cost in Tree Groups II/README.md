---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4018.Total%20Sum%20of%20Interaction%20Cost%20in%20Tree%20Groups%20II/README.md
---

<!-- problem:start -->

# [4018. 树组的交互代价总和 II 🔒](https://leetcode.cn/problems/total-sum-of-interaction-cost-in-tree-groups-ii)

[English Version](/solution/4000-4099/4018.Total%20Sum%20of%20Interaction%20Cost%20in%20Tree%20Groups%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数 <code>n</code> 和一棵以节点 0 为根的无向树，树中共有 <code>n</code> 个节点，编号从 0 到 <code>n - 1</code>。该树由一个长度为 <code>n - 1</code> 的二维整数数组 <code>edges</code> 表示，其中 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> 表示节点 <code>u<sub>i</sub></code> 和节点 <code>v<sub>i</sub></code> 之间存在一条无向边。</p>

<p>同时给定一个长度为 <code>n</code> 的整数数组 <code>group</code>，其中 <code>group[i]</code> 表示节点 <code>i</code> 所属的组标签。</p>

<ul>
	<li>当且仅当 <code>group[u] == group[v]</code> 时，节点 <code>u</code> 和 <code>v</code> 属于同一组。</li>
	<li>两个节点之间的&nbsp;<strong>交互代价&nbsp;</strong>为它们在树中的&nbsp;<strong>最短距离</strong>。</li>
</ul>

<p>返回所有满足 <code>0 &lt;= u &lt; v &lt; n</code> 且 <code>group[u] == group[v]</code> 的节点对 <code>(u, v)</code> 的交互代价之和。</p>

<p>两个节点之间的&nbsp;<strong>最短距离&nbsp;</strong>是连接它们的唯一路径上的边数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 3, edges = [[0,1],[1,2]], group = [1,1,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4018.Total%20Sum%20of%20Interaction%20Cost%20in%20Tree%20Groups%20II/images/screenshot-2026-05-05-at-40329am.png" style="width: 300px; height: 64px;" /></p>

<p>所有节点都属于组 1。各节点对之间的交互代价为：</p>

<ul>
	<li>节点 <code>[0, 1]</code>：1</li>
	<li>节点 <code>[1, 2]</code>：1</li>
	<li>节点 <code>[0, 2]</code>：2</li>
</ul>

<p>因此，总交互代价为 <code>1 + 1 + 2 = 4</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 3, edges = [[0,1],[1,2]], group = [3,2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4018.Total%20Sum%20of%20Interaction%20Cost%20in%20Tree%20Groups%20II/images/screenshot-2026-05-05-at-40416am.png" style="width: 300px; height: 60px;" /></p>

<ul>
	<li>节点 0 和节点 2 属于组 3，它们之间的交互代价为 2。</li>
	<li>节点 1 属于不同的组，因此无法与其他节点组成符合条件的节点对。</li>
</ul>

<p>因此，总交互代价为 2。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 4, edges = [[0,1],[0,2],[0,3]], group = [1,1,4,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4018.Total%20Sum%20of%20Interaction%20Cost%20in%20Tree%20Groups%20II/images/screenshot-2026-05-05-at-40819am.png" style="width: 300px; height: 199px;" /></p>

<p>属于相同组的节点及其交互代价如下：</p>

<ul>
	<li>组 1：节点 <code>[0, 1]</code>：1</li>
	<li>组 4：节点 <code>[2, 3]</code>：2</li>
</ul>

<p>因此，总交互代价为 <code>1 + 2 = 3</code>。</p>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 2, edges = [[0,1]], group = [1,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>所有节点都属于不同的组，因此不存在符合条件的节点对。总交互代价为 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>约束：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>group.length == n</code></li>
	<li><code>1 &lt;= group[i] &lt;= n</code></li>
	<li>输入数据保证 <code>edges</code> 表示一棵合法的树。</li>
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
