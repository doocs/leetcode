---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4096. 模拟炸弹爆炸和掉落箱子 🔒](https://leetcode.cn/problems/simulate-bomb-explosions-and-falling-boxes)

[English Version](/solution/4000-4099/4096.Simulate%20Bomb%20Explosions%20and%20Falling%20Boxes/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个大小为 <code>m x n</code> 的二维字符数组 <code>grid</code>，其中每个单元格包含以下符号之一：</p>

<ul>
	<li><code>'#'</code>：一个箱子。</li>
	<li><code>'-'</code>：一个空单元格。</li>
	<li><code>'*'</code>：一颗炸弹。</li>
</ul>

<p>每一秒，以下事件会<strong>按顺序</strong>发生：</p>

<ul>
	<li><strong>爆炸：</strong>如果一颗炸弹与至少一个箱子&nbsp;<strong>上下左右相邻</strong>（即共用一条边），则该炸弹会爆炸。爆炸的炸弹会摧毁其&nbsp;<strong>周围 8 个相邻单元格&nbsp;</strong>中的所有箱子，包括对角线方向相邻的箱子，并将这些单元格变为空单元格。所有爆炸&nbsp;<strong>同时发生</strong>，并且均基于该秒开始时的网格状态。空单元格和炸弹保持不变。</li>
	<li><strong>重力：</strong>爆炸结束后，所有&nbsp;<strong>非静止&nbsp;</strong>的箱子会&nbsp;<strong>同时&nbsp;</strong>向下移动恰好一行。如果一个箱子位于&nbsp;<strong>最下一行</strong>、紧挨着一颗炸弹的正上方，或者紧挨着另一个&nbsp;<strong>静止&nbsp;</strong>箱子的正上方，则该箱子是&nbsp;<strong>静止&nbsp;</strong>的。</li>
</ul>

<p>炸弹永远不会移动或消失，同一颗炸弹可以多次爆炸。位于非静止箱子上方的箱子可以与其一起下落。</p>

<p>返回经过 10<sup>100</sup> 秒后的最终 <code>grid</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [["#","#","#"],["-","-","*"],["-","-","-"]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[["-","-","-"],["-","-","*"],["#","-","-"]]</span></p>

<p><strong>解释：</strong></p>

<p>用 <code>(r, c)</code> 表示从上往下第 <code>r</code> 行、从左往右第 <code>c</code> 列的单元格。例如，<code>(0, 0)</code> 表示左上角的单元格。</p>

<p>第 1 秒：</p>

<ul>
	<li>由于位于 <code>(1, 2)</code> 的炸弹与位于 <code>(0, 2)</code> 的箱子上下左右相邻，因此位于 <code>(0, 1)</code> 和 <code>(0, 2)</code> 的箱子被摧毁，变为空单元格。</li>
	<li>位于 <code>(0, 0)</code> 的箱子下落至 <code>(1, 0)</code>。</li>
</ul>

<p>第 2 秒：</p>

<ul>
	<li>位于 <code>(1, 2)</code> 的炸弹没有与任何箱子上下左右相邻，因此没有发生爆炸。</li>
	<li>位于 <code>(1, 0)</code> 的箱子下落至 <code>(2, 0)</code>。</li>
</ul>

<p>此后不再发生任何变化。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [["-","*"],["#","-"]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[["-","*"],["#","-"]]</span></p>

<p><strong>解释：</strong></p>
由于炸弹没有与任何箱子上下左右相邻，因此不会发生任何变化。</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [["-","*","#"],["#","-","-"]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[["-","*","-"],["-","-","-"]]</span></p>

<p><strong>解释：</strong></p>

<p>第 1 秒：</p>

<ul>
	<li>位于 <code>(0, 1)</code> 的炸弹与位于 <code>(0, 2)</code> 的箱子上下左右相邻。因此，位于 <code>(0, 2)</code> 和 <code>(1, 0)</code> 的箱子被摧毁，变为空单元格。</li>
</ul>

<p>此后不再发生任何变化。</p>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [["#","*","-","#"],["-","-","*","-"]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[["-","*","-","-"],["-","-","*","-"]]</span></p>

<p><strong>解释：</strong></p>

<p>第 1 秒：</p>

<ul>
	<li>由于位于 <code>(0, 1)</code> 的炸弹与位于 <code>(0, 0)</code> 的箱子上下左右相邻，因此位于 <code>(0, 0)</code> 的箱子被摧毁，变为空单元格。另一颗位于 <code>(1, 2)</code> 的炸弹没有与任何箱子上下左右相邻。</li>
	<li>位于 <code>(0, 3)</code> 的箱子下落至 <code>(1, 3)</code>。</li>
</ul>

<p>第 2 秒：</p>

<ul>
	<li>由于位于 <code>(1, 2)</code> 的炸弹与位于 <code>(1, 3)</code> 的箱子上下左右相邻，因此位于 <code>(1, 3)</code> 的箱子被摧毁，变为空单元格。另一颗位于 <code>(0, 1)</code> 的炸弹没有与任何箱子上下左右相邻。</li>
</ul>

<p>此后不再发生任何变化。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m == grid.length &lt;= 100</code></li>
	<li><code>1 &lt;= n == grid[i].length &lt;= 100</code></li>
	<li><code>grid[i][j]</code> 为 <code>'#'</code>、<code>'-'</code> 或 <code>'*'</code>。</li>
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
