---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3603.Minimum%20Cost%20Path%20with%20Alternating%20Directions%20II/README.md
tags:
    - 数组
    - 动态规划
    - 矩阵
---

<!-- problem:start -->

# [3603. 交替方向的最小路径代价 II](https://leetcode.cn/problems/minimum-cost-path-with-alternating-directions-ii)

[English Version](/solution/3600-3699/3603.Minimum%20Cost%20Path%20with%20Alternating%20Directions%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数 <code>m</code> 和 <code>n</code>，分别表示网格的行数和列数。</p>

<p>进入单元格 <code>(i, j)</code> 的成本定义为 <code>(i + 1) * (j + 1)</code>。</p>

<p>另外给你一个二维整数数组 <code>waitCost</code>，其中 <code>waitCost[i][j]</code> 定义了在该单元格&nbsp;<strong>等待&nbsp;</strong>的成本。</p>

<p>路径始终从第 1 步进入单元格 <code>(0, 0)</code>&nbsp;并支付入场花费开始。</p>

<p>每一步，你都遵循交替模式：</p>

<ul>
	<li>在&nbsp;<strong>奇数秒&nbsp;</strong>，你必须向&nbsp;<strong>右&nbsp;</strong>或向&nbsp;<strong>下&nbsp;</strong>移动到&nbsp;<strong>相邻&nbsp;</strong>的单元格，并支付其进入成本。</li>
	<li>在&nbsp;<strong>偶数秒&nbsp;</strong>，你必须原地&nbsp;<strong>等待</strong><strong>恰好</strong>&nbsp;1 秒并在 1 秒期间支付 <code>waitCost[i][j]</code>。</li>
</ul>

<p>返回到达 <code>(m - 1, n - 1)</code> 所需的&nbsp;<strong>最小&nbsp;</strong>总成本。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">m = 1, n = 2, waitCost = [[1,2]]</span></p>

<p><strong>输出：</strong><span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>最佳路径为：</p>

<ul>
	<li>从第 1 秒开始在单元格 <code>(0, 0)</code>，进入成本为 <code>(0 + 1) * (0 + 1) = 1</code>。</li>
	<li><strong>第 1 秒</strong>：向右移动到单元格 <code>(0, 1)</code>，进入成本为 <code>(0 + 1) * (1 + 1) = 2</code>。</li>
</ul>

<p>因此，总成本为 <code>1 + 2 = 3</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">m = 2, n = 2, waitCost = [[3,5],[2,4]]</span></p>

<p><strong>输出：</strong><span class="example-io">9</span></p>

<p><strong>解释：</strong></p>

<p>最佳路径为：</p>

<ul>
	<li>从第 1 秒开始在单元格 <code>(0, 0)</code>，进入成本为 <code>(0 + 1) * (0 + 1) = 1</code>。</li>
	<li><strong>第 1 秒</strong>：向下移动到单元格 <code>(1, 0)</code>，进入成本为 <code>(1 + 1) * (0 + 1) = 2</code>。</li>
	<li><strong>第 2 秒</strong>：在单元格 <code>(1, 0)</code> 等待，支付 <code>waitCost[1][0] = 2</code>。</li>
	<li><strong>第 3 秒</strong>：向右移动到单元格 <code>(1, 1)</code>，进入成本为 <code>(1 + 1) * (1 + 1) = 4</code>。</li>
</ul>

<p>因此，总成本为 <code>1 + 2 + 2 + 4 = 9</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">m = 2, n = 3, waitCost = [[6,1,4],[3,2,5]]</span></p>

<p><strong>输出：</strong><span class="example-io">16</span></p>

<p><strong>解释：</strong></p>

<p>最佳路径为：</p>

<ul>
	<li>从第 1 秒开始在单元格 <code>(0, 0)</code>，进入成本为 <code>(0 + 1) * (0 + 1) = 1</code>。</li>
	<li><strong>第 1 秒</strong>：向右移动到单元格 <code>(0, 1)</code>，进入成本为 <code>(0 + 1) * (1 + 1) = 2</code>。</li>
	<li><strong>第 2 秒</strong>：在单元格 <code>(0, 1)</code> 等待，支付 <code>waitCost[0][1] = 1</code>。</li>
	<li><strong>第 3 秒</strong>：向下移动到单元格 <code>(1, 1)</code>，进入成本为 <code>(1 + 1) * (1 + 1) = 4</code>。</li>
	<li><strong>第 4 秒</strong>：在单元格 <code>(1, 1)</code> 等待，支付 <code>waitCost[1][1] = 2</code>。</li>
	<li><strong>第 5 秒</strong>：向右移动到单元格 <code>(1, 2)</code>，进入成本为 <code>(1 + 1) * (2 + 1) = 6</code>。</li>
</ul>

<p>因此，总成本为 <code>1 + 2 + 1 + 4 + 2 + 6 = 16</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>waitCost.length == m</code></li>
	<li><code>waitCost[0].length == n</code></li>
	<li><code>0 &lt;= waitCost[i][j] &lt;= 10<sup>5</sup></code></li>
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
