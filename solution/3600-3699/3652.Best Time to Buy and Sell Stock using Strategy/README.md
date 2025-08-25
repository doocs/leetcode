---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3652.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20using%20Strategy/README.md
rating: 1556
source: 第 463 场周赛 Q1
tags:
    - 数组
    - 前缀和
    - 滑动窗口
---

<!-- problem:start -->

# [3652. 按策略买卖股票的最佳时机](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-using-strategy)

[English Version](/solution/3600-3699/3652.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20using%20Strategy/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数数组 <code>prices</code> 和 <code>strategy</code>，其中：</p>

<ul>
	<li><code>prices[i]</code> 表示第 <code>i</code> 天某股票的价格。</li>
	<li><code>strategy[i]</code> 表示第 <code>i</code> 天的交易策略，其中：
	<ul>
		<li><code>-1</code> 表示买入一单位股票。</li>
		<li><code>0</code> 表示持有股票。</li>
		<li><code>1</code> 表示卖出一单位股票。</li>
	</ul>
	</li>
</ul>

<p>同时给你一个&nbsp;<strong>偶数&nbsp;</strong>整数 <code>k</code>，你可以对 <code>strategy</code> 进行&nbsp;<strong>最多一次&nbsp;</strong>修改。一次修改包括：</p>

<ul>
	<li>选择 <code>strategy</code> 中恰好 <code>k</code> 个&nbsp;<strong>连续&nbsp;</strong>元素。</li>
	<li>将前 <code>k / 2</code> 个元素设为 <code>0</code>（持有）。</li>
	<li>将后 <code>k / 2</code> 个元素设为 <code>1</code>（卖出）。</li>
</ul>

<p><strong>利润&nbsp;</strong>定义为所有天数中 <code>strategy[i] * prices[i]</code> 的&nbsp;<strong>总和&nbsp;</strong>。</p>

<p>返回你可以获得的&nbsp;<strong>最大&nbsp;</strong>可能利润。</p>

<p><strong>注意：</strong> 没有预算或股票持有数量的限制，因此所有买入和卖出操作均可行，无需考虑过去的操作。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">prices = [4,2,8], strategy = [-1,0,1], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">10</span></p>

<p><strong>解释：</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">修改</th>
			<th style="border: 1px solid black;">策略</th>
			<th style="border: 1px solid black;">利润计算</th>
			<th style="border: 1px solid black;">利润</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">原始</td>
			<td style="border: 1px solid black;">[-1, 0, 1]</td>
			<td style="border: 1px solid black;">(-1 × 4) + (0 × 2) + (1 × 8) = -4 + 0 + 8</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">修改 [0, 1]</td>
			<td style="border: 1px solid black;">[0, 1, 1]</td>
			<td style="border: 1px solid black;">(0 × 4) + (1 × 2) + (1 × 8) = 0 + 2 + 8</td>
			<td style="border: 1px solid black;">10</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">修改 [1, 2]</td>
			<td style="border: 1px solid black;">[-1, 0, 1]</td>
			<td style="border: 1px solid black;">(-1 × 4) + (0 × 2) + (1 × 8) = -4 + 0 + 8</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
	</tbody>
</table>

<p>因此，最大可能利润是 10，通过修改子数组 <code>[0, 1]</code> 实现。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">prices = [5,4,3], strategy = [1,1,0], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">9</span></p>

<p><strong>解释：</strong></p>

<div class="example-block">
<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">修改</th>
			<th style="border: 1px solid black;">策略</th>
			<th style="border: 1px solid black;">利润计算</th>
			<th style="border: 1px solid black;">利润</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">原始</td>
			<td style="border: 1px solid black;">[1, 1, 0]</td>
			<td style="border: 1px solid black;">(1 × 5) + (1 × 4) + (0 × 3) = 5 + 4 + 0</td>
			<td style="border: 1px solid black;">9</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">修改 [0, 1]</td>
			<td style="border: 1px solid black;">[0, 1, 0]</td>
			<td style="border: 1px solid black;">(0 × 5) + (1 × 4) + (0 × 3) = 0 + 4 + 0</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">修改 [1, 2]</td>
			<td style="border: 1px solid black;">[1, 0, 1]</td>
			<td style="border: 1px solid black;">(1 × 5) + (0 × 4) + (1 × 3) = 5 + 0 + 3</td>
			<td style="border: 1px solid black;">8</td>
		</tr>
	</tbody>
</table>

<p>因此，最大可能利润是 9，无需任何修改即可达成。</p>
</div>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= prices.length == strategy.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>-1 &lt;= strategy[i] &lt;= 1</code></li>
	<li><code>2 &lt;= k &lt;= prices.length</code></li>
	<li><code>k</code> 是偶数</li>
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
