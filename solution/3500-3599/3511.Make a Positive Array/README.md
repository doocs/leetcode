---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3511.Make%20a%20Positive%20Array/README.md
---

<!-- problem:start -->

# [3511. 构造正数组 🔒](https://leetcode.cn/problems/make-a-positive-array)

[English Version](/solution/3500-3599/3511.Make%20a%20Positive%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个数组&nbsp;<code>nums</code>。一个数组被认为是 <strong>正</strong> 的，如果每个包含 <strong>超过两个</strong> 元素的 <strong><span data-keyword="subarray">子数组</span></strong> 的所有数字之和都是正数。</p>

<p>您可以执行以下操作任意多次：</p>

<ul>
	<li>用 -10<sup>18</sup> 和 10<sup>18&nbsp;</sup>之间的任意整数替换&nbsp;<code>nums</code>&nbsp;中的 <strong>一个</strong>&nbsp;元素。</li>
</ul>

<p>找到使 <code>nums</code> 变为正数组所需的最小操作数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [-10,15,-12]</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<p>唯一有超过 2 个元素的子数组是这个数组本身。所有元素的和是&nbsp;<code>(-10) + 15 + (-12) = -7</code>。通过将&nbsp;<code>nums[0]</code>&nbsp;替换为 0，新的和变为&nbsp;<code>0 + 15 + (-12) = 3</code>。因此，现在数组是正的。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [-1,-2,3,-1,2,6]</span></p>

<p><strong>输出：</strong><span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>具有 2 个以上元素且和非正的子数组是：</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">子数组下标</th>
			<th style="border: 1px solid black;">子数组</th>
			<th style="border: 1px solid black;">和</th>
			<th style="border: 1px solid black;">替换后的子数组（令 nums[1] = 1）</th>
			<th style="border: 1px solid black;">新的和</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[0...2]</td>
			<td style="border: 1px solid black;">[-1, -2, 3]</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">[-1, 1, 3]</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[0...3]</td>
			<td style="border: 1px solid black;">[-1, -2, 3, -1]</td>
			<td style="border: 1px solid black;">-1</td>
			<td style="border: 1px solid black;">[-1, 1, 3, -1]</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[1...3]</td>
			<td style="border: 1px solid black;">[-2, 3, -1]</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">[1, 3, -1]</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
	</tbody>
</table>

<p>因此，<code>nums</code>&nbsp;在一次操作后是正的。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,3]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<p>数组已经是正的，所以不需要操作。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
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
