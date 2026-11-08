---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4108. 使红色节点连通的最小代价](https://leetcode.cn/problems/minimum-cost-to-make-red-nodes-connected)

[English Version](/solution/4100-4199/4108.Minimum%20Cost%20to%20Make%20Red%20Nodes%20Connected/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code>，表示一棵有 <code>n</code> 个节点的树，节点编号从 0 到 <code>n - 1</code>，并且树以节点 0 为&nbsp;<strong>根节点</strong>。这棵树由一个长度为 <code>n - 1</code> 的二维数组 <code>edges</code> 表示，其中 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> 表示节点 <code>u<sub>i</sub></code> 和 <code>v<sub>i</sub></code> 之间存在一条&nbsp;<strong>无向&nbsp;</strong>边。</p>

<p>另给你一个长度为 <code>n</code> 的字符串 <code>colors</code>，其中 <code>colors[i]</code> 表示节点 <code>i</code> 的颜色：<code>'R'</code> 表示红色，<code>'B'</code> 表示蓝色。</p>

<p>你可以&nbsp;<strong>翻转&nbsp;</strong>任意节点的颜色：从 <code>'R'</code> 变为 <code>'B'</code>，或从 <code>'B'</code> 变为 <code>'R'</code>。翻转节点 <code>u</code> 的代价等于以节点 <code>u</code> 为根的子树的大小。</p>

<p>你的目标是通过翻转颜色，使所有<strong>&nbsp;红色</strong>&nbsp;节点在树中形成<strong>&nbsp;一个</strong><strong>连通分量&nbsp;</strong>。蓝色节点可以不连通。没有红色节点的配置也视为有效。</p>

<p>返回实现这一目标所需的<strong>&nbsp;最小总代价&nbsp;</strong>。</p>

<p>在有根树中，节点 <code>v</code> 的子树是由所有到根节点的路径经过 <code>v</code> 的节点组成的集合。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 3, colors = "RBR", edges = [[0,1],[1,2]]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4100-4199/4108.Minimum%20Cost%20to%20Make%20Red%20Nodes%20Connected/images/screenshot-2026-07-11-at-33714am.png" style="height: 60px; width: 237px;" /></p>

<ul>
	<li>有两个红色节点（0 和 2），它们彼此不连通。</li>
	<li>可以将节点 2 从 <code>'R'</code>（红色）翻转为 <code>'B'</code>（蓝色）。节点 2 的子树大小为 1，因此代价为 1。</li>
	<li>翻转后，只有节点 0 仍为红色，恰好形成一个红色连通分量。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 3, colors = "RBR", edges = [[0,1],[0,2]]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong>​​​​​​​</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4100-4199/4108.Minimum%20Cost%20to%20Make%20Red%20Nodes%20Connected/images/screenshot-2026-07-11-at-33754am.png" style="width: 214px; height: 150px;" /></p>

<ul>
	<li>有两个红色节点（0 和 2），它们已经彼此连通。</li>
	<li>所有红色节点已经形成一个连通分量，因此无须翻转。最小总代价为 0。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 4, colors = "BRRR", edges = [[0,1],[0,2],[0,3]]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong>​​​​​​​</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4100-4199/4108.Minimum%20Cost%20to%20Make%20Red%20Nodes%20Connected/images/screenshot-2026-07-11-at-33832am.png" style="width: 242px; height: 150px;" /></p>

<ul>
	<li>有三个红色节点（1、2 和 3），它们彼此不连通。</li>
	<li>这三个红色节点都是叶节点，且互不连通。</li>
	<li>为了得到一个红色连通分量，保留一个红色叶节点，并将另外两个翻转为蓝色。</li>
	<li>每个叶节点的子树大小均为 1，因此翻转其中任意两个的总代价为 2。</li>
</ul>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 3, colors = "BBB", edges = [[0,1],[1,2]]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p><strong>​​​​​​​</strong><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4100-4199/4108.Minimum%20Cost%20to%20Make%20Red%20Nodes%20Connected/images/screenshot-2026-07-11-at-33900am.png" style="width: 277px; height: 55px;" /></p>

<ul>
	<li>所有节点均为蓝色，因此没有红色节点。</li>
	<li>空的红色节点集合也视为一个连通分量，因此当前状态已经有效，代价为 0。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong>​​​​​​​</p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n == colors.length</code></li>
	<li><code>colors[i]</code> 为 <code>'R'</code> 或 <code>'B'</code>。</li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] =&nbsp;[u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li>输入保证 <code>edges</code> 表示一棵有效的树。</li>
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
