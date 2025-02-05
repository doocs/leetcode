---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3428.Maximum%20and%20Minimum%20Sums%20of%20at%20Most%20Size%20K%20Subsequences/README.md
rating: 2028
source: 第 433 场周赛 Q2
tags:
    - 数组
    - 数学
    - 动态规划
    - 组合数学
    - 排序
---

<!-- problem:start -->

# [3428. 最多 K 个元素的子序列的最值之和](https://leetcode.cn/problems/maximum-and-minimum-sums-of-at-most-size-k-subsequences)

[English Version](/solution/3400-3499/3428.Maximum%20and%20Minimum%20Sums%20of%20at%20Most%20Size%20K%20Subsequences/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和一个正整数 <code>k</code>，返回所有长度最多为 <code>k</code> 的 <strong>子序列</strong> 中&nbsp;<strong>最大值&nbsp;</strong>与&nbsp;<strong>最小值&nbsp;</strong>之和的总和。</p>

<p><strong>非空子序列</strong>&nbsp;是指从另一个数组中删除一些或不删除任何元素（且不改变剩余元素的顺序）得到的数组。</p>

<p>由于答案可能非常大，请返回对 <code>10<sup>9</sup> + 7</code> 取余数的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3], k = 2</span></p>

<p><strong>输出：</strong> 24</p>

<p><strong>解释：</strong></p>

<p>数组 <code>nums</code> 中所有长度最多为 2 的子序列如下：</p>

<table style="border: 1px solid black; border-collapse: collapse;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">子序列</th>
			<th style="border: 1px solid black;">最小值</th>
			<th style="border: 1px solid black;">最大值</th>
			<th style="border: 1px solid black;">和</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;"><code>[1]</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[2]</code></td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[3]</code></td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">6</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 2]</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 3]</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[2, 3]</code></td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">5</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><strong>总和</strong></td>
			<td style="border: 1px solid black;">&nbsp;</td>
			<td style="border: 1px solid black;">&nbsp;</td>
			<td style="border: 1px solid black;">24</td>
		</tr>
	</tbody>
</table>

<p>因此，输出为 24。</p>
</div>

<p><strong>示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [5,0,6], k = 1</span></p>

<p><strong>输出：</strong> 22</p>

<p><strong>解释：</strong></p>

<p>对于长度恰好为 1 的子序列，最小值和最大值均为元素本身。因此，总和为 <code>5 + 5 + 0 + 0 + 6 + 6 = 22</code>。</p>
</div>

<p><strong>示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,1,1], k = 2</span></p>

<p><strong>输出：</strong> 12</p>

<p><strong>解释：</strong></p>

<p>子序列 <code>[1, 1]</code> 和 <code>[1]</code> 各出现 3 次。对于所有这些子序列，最小值和最大值均为 1。因此，总和为 12。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= min(100, nums.length)</code></li>
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
