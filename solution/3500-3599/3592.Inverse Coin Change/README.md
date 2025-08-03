---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3592.Inverse%20Coin%20Change/README.md
rating: 1700
source: 第 455 场周赛 Q2
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [3592. 硬币面值还原](https://leetcode.cn/problems/inverse-coin-change)

[English Version](/solution/3500-3599/3592.Inverse%20Coin%20Change/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;<strong>从 1 开始计数&nbsp;</strong>的整数数组 <code>numWays</code>，其中 <code>numWays[i]</code> 表示使用某些&nbsp;<strong>固定&nbsp;</strong>面值的硬币（每种面值可以使用无限次）凑出总金额 <code>i</code> 的方法数。每种面值都是一个&nbsp;<strong>正整数&nbsp;</strong>，并且其值&nbsp;<strong>最多&nbsp;</strong>为 <code>numWays.length</code>。</p>

<p>然而，具体的硬币面值已经&nbsp;<strong>丢失&nbsp;</strong>。你的任务是还原出可能生成这个 <code>numWays</code> 数组的面值集合。</p>

<p>返回一个按从小到大顺序排列的数组，其中包含所有可能的&nbsp;<strong>唯一&nbsp;</strong>整数面值。</p>

<p>如果不存在这样的集合，返回一个&nbsp;<strong>空&nbsp;</strong>数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">numWays = [0,1,0,2,0,3,0,4,0,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">[2,4,6]</span></p>

<p><strong>解释：</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">金额</th>
			<th style="border: 1px solid black;">方法数</th>
			<th style="border: 1px solid black;">解释</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">无法用硬币凑出总金额 1。</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">唯一的方法是 <code>[2]</code>。</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">无法用硬币凑出总金额 3。</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">可以用 <code>[2, 2]</code> 或 <code>[4]</code>。</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">无法用硬币凑出总金额 5。</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">可以用 <code>[2, 2, 2]</code>、<code>[2, 4]</code> 或 <code>[6]</code>。</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">无法用硬币凑出总金额 7。</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">8</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">可以用 <code>[2, 2, 2, 2]</code>、<code>[2, 2, 4]</code>、<code>[2, 6]</code> 或 <code>[4, 4]</code>。</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">9</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">无法用硬币凑出总金额 9。</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">10</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">可以用 <code>[2, 2, 2, 2, 2]</code>、<code>[2, 2, 2, 4]</code>、<code>[2, 4, 4]</code>、<code>[2, 2, 6]</code> 或 <code>[4, 6]</code>。</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">numWays = [1,2,2,3,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,2,5]</span></p>

<p><strong>解释：</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">金额</th>
			<th style="border: 1px solid black;">方法数</th>
			<th style="border: 1px solid black;">解释</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">唯一的方法是 <code>[1]</code>。</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">可以用 <code>[1, 1]</code> 或 <code>[2]</code>。</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">可以用 <code>[1, 1, 1]</code> 或 <code>[1, 2]</code>。</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">可以用 <code>[1, 1, 1, 1]</code>、<code>[1, 1, 2]</code> 或 <code>[2, 2]</code>。</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">可以用 <code>[1, 1, 1, 1, 1]</code>、<code>[1, 1, 1, 2]</code>、<code>[1, 2, 2]</code> 或 <code>[5]</code>。</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">numWays = [1,2,3,4,15]</span></p>

<p><strong>输出：</strong> <span class="example-io">[]</span></p>

<p><strong>解释：</strong></p>

<p>没有任何面值集合可以生成该数组。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= numWays.length &lt;= 100</code></li>
	<li><code>0 &lt;= numWays[i] &lt;= 2 * 10<sup>8</sup></code></li>
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
