---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4112. 将子树置零后的最大得分](https://leetcode.cn/problems/maximize-score-after-zeroing-subtrees)

[English Version](/solution/4100-4199/4112.Maximize%20Score%20After%20Zeroing%20Subtrees/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>parent</code>，表示一棵节点编号从 0 到 <code>n - 1</code> 的有根树，其中 <code>parent[i]</code> 表示节点 <code>i</code> 的父节点。树的根节点为节点 0，因此 <code>parent[0] = -1</code>。</p>

<p>另给你两个长度均为 <code>n</code> 的整数数组 <code>op</code> 和 <code>score</code>，其中 <code>op[i]</code> 决定节点 <code>i</code> 允许执行的操作，<code>score[i]</code> 表示该节点的分数。</p>

<p>你需要按照以下规则处理每个&nbsp;<strong>非叶节点</strong> <code>i</code>：</p>

<ul>
	<li>如果 <code>op[i] = 0</code>，你可以选择节点 <code>i</code> 的<strong>&nbsp;至多&nbsp;</strong>一个子节点，并将以该子节点为根的&nbsp;<strong>子树</strong>&nbsp;中所有节点的分数设为 0。</li>
	<li>如果 <code>op[i] = 1</code>，你必须选择节点 <code>i</code> 的<strong>&nbsp;恰好&nbsp;</strong>一个子节点，并将以该子节点为根的子树中所有节点的分数设为 0。</li>
</ul>

<p>每个非叶节点都必须恰好处理一次。你可以按任意顺序处理这些节点。</p>

<p>返回执行这些操作后，所有节点分数之和的<strong>&nbsp;最大可能值</strong>。</p>

<p><strong>叶节点&nbsp;</strong>是没有子节点的节点。</p>

<p>以某个节点为根的<strong>&nbsp;子树</strong>&nbsp;由该节点及其所有后代节点组成。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4100-4199/4112.Maximize%20Score%20After%20Zeroing%20Subtrees/images/1794630276-Yulmik-1000061694.gif" style="width: 533px; height: 300px;" />​​​​​​​</p>

<p><strong>输入：</strong> <span class="example-io">parent = [-1,0,0,0,1,2,3,3], op = [1,1,0,0,1,1,0,1], score = [5,6,1,2,3,2,3,-3]</span></p>

<p><strong>输出：</strong> <span class="example-io">16</span></p>

<p><strong>解释：</strong></p>

<p>一种最优方案是按以下顺序处理节点：</p>

<ul>
	<li>节点 4、5、6 和 7<strong>：</strong>它们都是叶节点，因此不对它们执行任何操作。</li>
	<li>节点 1<strong>：</strong>由于 <code>op[1] = 1</code>，我们必须选择它的一个子节点。选择节点 4，将以它为根的子树中所有节点（节点 4）的分数设为 0。</li>
	<li>节点 2：由于 <code>op[2] = 0</code>，我们可以选择一个子节点，也可以不执行任何操作。对于该节点，我们不执行任何操作。</li>
	<li>节点 3：由于 <code>op[3] = 0</code>，我们可以选择一个子节点，也可以不执行任何操作。选择节点 7，将以它为根的子树中所有节点（节点 7）的分数设为 0。</li>
	<li>节点 0：由于 <code>op[0] = 1</code>，我们必须选择它的一个子节点。选择节点 2，将以它为根的子树中所有节点（节点 2 和 5）的分数设为 0。</li>
</ul>

<p>执行这些操作后，各节点的分数为 <code>[5, 6, 0, 2, 0, 0, 3, 0]</code>，总和为 16。</p>

<p>不存在能够得到更大分数总和的合法操作序列。因此，答案为 16。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4100-4199/4112.Maximize%20Score%20After%20Zeroing%20Subtrees/images/1794630276-FcQkDj-1000061692.png" style="width: 220px; height: 180px;" />​​​​​​​</p>

<p><strong>输入：</strong> <span class="example-io">parent = [-1,0,0], op = [0,0,0], score = [1,2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">6</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>我们只需要处理节点 0，因为节点 1 和节点 2 都是叶节点。</li>
	<li>由于 <code>op[0] = 0</code>，我们可以选择不执行任何操作。</li>
	<li>因此，所有节点的分数都得以保留，得到的最大分数总和为 6。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4100-4199/4112.Maximize%20Score%20After%20Zeroing%20Subtrees/images/1794630276-xLiVcP-1000061693.png" style="width: 139px; height: 60px;" />​​​​​​​</p>

<p><strong>输入：</strong> <span class="example-io">parent = [-1], op = [1], score = [-10]</span></p>

<p><strong>输出：</strong> <span class="example-io">-10</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>树中唯一的节点是叶节点，因此无法对任何节点执行操作。</li>
	<li>因此，最大分数总和为 -10。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n == parent.length == op.length == score.length</code></li>
	<li><code>parent[0] == -1</code></li>
	<li>对于所有 <code>i</code> 在 <code>[1, n - 1]</code> 范围内，均有 <code>0 &lt;= parent[i] &lt;= n - 1</code></li>
	<li><code>0 &lt;= op[i] &lt;= 1</code></li>
	<li><code>-10<sup>4</sup> &lt;= score[i] &lt;= 10<sup>4</sup></code></li>
	<li>输入保证数组 <code>parent</code> 表示一棵以节点 0 为根的合法树。</li>
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
