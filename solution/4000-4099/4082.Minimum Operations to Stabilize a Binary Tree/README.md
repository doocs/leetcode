---
comments: true
difficulty: 困难
---

<!-- problem:start -->

# [4082. 稳定二叉树的最小操作次数 🔒](https://leetcode.cn/problems/minimum-operations-to-stabilize-a-binary-tree)

[English Version](/solution/4000-4099/4082.Minimum%20Operations%20to%20Stabilize%20a%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一棵&nbsp;<strong>二叉树&nbsp;</strong>的根节点 <code>root</code>。每个节点都包含一个整数值，表示其当前状态。</p>

<p>如果对于每个节点 <code>u</code>，都满足以下条件，则称这棵二叉树是&nbsp;<strong>稳定的</strong>：</p>

<ul>
	<li>如果 <code>u.left</code> 存在，那么 <code>|u.val - u.left.val| &lt;= 1</code>。</li>
	<li>如果 <code>u.right</code> 存在，那么 <code>|u.val - u.right.val| &lt;= 1</code>。</li>
</ul>

<p>在一次操作中，你可以选择任意一个节点，将其值&nbsp;<strong>增加&nbsp;</strong>或&nbsp;<strong>减少</strong> 1。</p>

<p>返回使整棵二叉树变为&nbsp;<strong>稳定的&nbsp;</strong>所需的&nbsp;<strong>最少&nbsp;</strong>操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">root = [5,8,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4082.Minimum%20Operations%20to%20Stabilize%20a%20Binary%20Tree/images/screenshot-2026-02-16-at-21240pm.png" style="width: 180px; height: 153px;" /></p>

<ul>
	<li>设 <code>u</code> 为值为 5 的根节点。通过 2 次操作，将左子节点的值从 8 减少到 6。此时 <code>|u.val - u.left.val| = |5 - 6| = 1</code>。</li>
	<li>通过 1 次操作，将右子节点的值从 3 增加到 4。此时 <code>|u.val - u.right.val| = |5 - 4| = 1</code>。</li>
	<li>因此，答案为 <code>2 + 1 = 3</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">root = [2,-1,-4]</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4082.Minimum%20Operations%20to%20Stabilize%20a%20Binary%20Tree/images/screenshot-2026-02-16-at-21340pm.png" style="width: 170px; height: 148px;" /></p>

<ul>
	<li>设 <code>u</code> 为值为 2 的根节点。通过 4 次操作，将 <code>u</code> 的值从 2 减少到 −2。</li>
	<li>通过 1 次操作，将右子节点的值从 −4 减少到 −3。此时 <code>|u.val - u.right.val| = |−2 - (−3)| = 1</code>。</li>
	<li>因此，答案为 <code>4 + 1 = 5</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">root = [0,-2,2,-3,null,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4082.Minimum%20Operations%20to%20Stabilize%20a%20Binary%20Tree/images/screenshot-2026-02-16-at-21826pm.png" style="width: 180px; height: 201px;" /></p>

<ul>
	<li>设 <code>u</code> 为值为 0 的根节点。通过 1 次操作，将左子节点的值从 −2 增加到 −1；再通过 1 次操作，将右子节点的值从 2 减少到 1。</li>
	<li>此时 <code>|u.val - u.left.val| = |0 - (−1)| = 1</code>，且 <code>|u.val - u.right.val| = |0 - 1| = 1</code>。</li>
	<li>设 <code>v</code> 为 <code>u</code> 的左子节点，其当前值为 −1。通过 1 次操作，将 <code>v</code> 的左子节点的值从 −3 增加到 −2，使得 <code>|v.val - v.left.val| = |−1 - (−2)| = 1</code>。</li>
	<li>右子树已经满足条件，因为 <code>|1 - 1| = 0</code>。</li>
	<li>因此，答案为 <code>1 + 1 + 1 = 3</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中的节点数在范围 <code>[1, 5 * 10<sup>4</sup>]</code> 内。</li>
	<li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
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
