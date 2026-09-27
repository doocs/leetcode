---
comments: true
difficulty: 简单
---

<!-- problem:start -->

# [4061. 皇后到达目标格子的最少移动步数](https://leetcode.cn/problems/minimum-queen-moves-to-reach-target)

[English Version](/solution/4000-4099/4061.Minimum%20Queen%20Moves%20to%20Reach%20Target/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个 <code>8 x 8</code> 的空棋盘，其行和列的<strong>下标从 1 开始</strong>。</p>

<p>给你一个数组 <code>source = [sr, sc]</code> 表示&nbsp;<strong>皇后&nbsp;</strong>的初始位置，以及一个数组 <code>target = [tr, tc]</code> 表示目标位置。</p>

<p>在一步移动中，皇后可以在棋盘范围内，沿着单条&nbsp;<strong>行&nbsp;</strong>、&nbsp;<strong>列&nbsp;</strong>或&nbsp;<strong>对角线&nbsp;</strong>移动一个或多个方格。</p>

<p>返回皇后移动到&nbsp;<strong>恰好&nbsp;</strong>落在 <code>target</code> 位置所需的&nbsp;<strong>最小&nbsp;</strong>移动次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">source = [8,1], target = [1,8]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4061.Minimum%20Queen%20Moves%20to%20Reach%20Target/images/111.png" style="width: 300px; height: 303px;" /></strong></p>

<p>单次对角线移动即可让皇后直接从 <code>(8, 1)</code> 移动到 <code>(1, 8)</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">source = [4,2], target = [1,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4061.Minimum%20Queen%20Moves%20to%20Reach%20Target/images/1e602ed4-c525-4be4-a6a9-6804cb7d2a55.png" style="width: 300px; height: 305px;" />​​​​​​​</p>

<p>皇后先从 <code>(4, 2)</code> 移动到 <code>(4, 3)</code>，然后从 <code>(4, 3)</code> 移动到 <code>(1, 3)</code>，共用 2 步移动到达目标位置。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">source = [1,1], target = [1,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>皇后已经处于目标位置，因此不需要任何移动。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong>​​​​​​​</p>

<ul>
	<li><code>source == [sr, sc]</code></li>
	<li><code>target == [tr, tc]</code></li>
	<li><code>1 &lt;= sr, sc, tr, tc &lt;= 8</code></li>
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
