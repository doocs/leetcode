---
comments: true
difficulty: 简单
---

<!-- problem:start -->

# [4074. 和为目标值的最大乘积对](https://leetcode.cn/problems/maximum-product-pair-with-target-sum)

[English Version](/solution/4000-4099/4074.Maximum%20Product%20Pair%20With%20Target%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>target</code>。</p>

<p>当一个由&nbsp;<strong>不同&nbsp;</strong>下标组成的二元组 <code>(i, j)</code>&nbsp;满足以下条件，称它是&nbsp;<strong>有效的&nbsp;</strong>：</p>

<ul>
	<li><code>nums[i] + nums[j] == target</code></li>
	<li><code>nums[i] &gt; nums[j]</code></li>
</ul>

<p>返回一个&nbsp;<strong>有效的&nbsp;</strong>二元组 <code>[i, j]</code>，使其&nbsp;<strong>乘积</strong> <code>nums[i] * nums[j]</code> 在所有有效二元组中是&nbsp;<strong>最大&nbsp;</strong>的。如果不存在有效的二元组，返回 <code>[-1, -1]</code>。</p>

<p>如果有多个有效二元组都能达到&nbsp;<strong>最大&nbsp;</strong>乘积，你可以返回其中的&nbsp;<strong>任意一个&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3,4], target = 5</span></p>

<p><strong>输出：</strong> <span class="example-io">[2,1]</span></p>

<p><strong>解释：</strong></p>

<p>存在 2 个有效的二元组：</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th scope="col" style="text-align:center;">序号</th>
			<th scope="col" style="text-align:center;"><code>(i, j)</code></th>
			<th scope="col" style="text-align:center;"><code>nums[i]</code></th>
			<th scope="col" style="text-align:center;"><code>nums[j]</code></th>
			<th scope="col" style="text-align:center;">和</th>
			<th scope="col" style="text-align:center;">积</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">(3, 0)</td>
			<td style="text-align:center;">4</td>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">5</td>
			<td style="text-align:center;">4</td>
		</tr>
		<tr>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">(2, 1)</td>
			<td style="text-align:center;">3</td>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">5</td>
			<td style="text-align:center;">6</td>
		</tr>
	</tbody>
</table>

<p>两个二元组的和均为 5 且满足 <code>nums[i] &gt; nums[j]</code>。第二个二元组的乘积更大，<code>3 * 2 = 6</code>，因此答案是 <code>[2, 1]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [-3,-1,4,2], target = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">[3,1]</span></p>

<p><strong>解释：</strong></p>

<p>存在 2 个有效的二元组：</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th scope="col" style="text-align:center;">序号</th>
			<th scope="col" style="text-align:center;"><code>(i, j)</code></th>
			<th scope="col" style="text-align:center;"><code>nums[i]</code></th>
			<th scope="col" style="text-align:center;"><code>nums[j]</code></th>
			<th scope="col" style="text-align:center;">和</th>
			<th scope="col" style="text-align:center;">积</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">(2, 0)</td>
			<td style="text-align:center;">4</td>
			<td style="text-align:center;">-3</td>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">-12</td>
		</tr>
		<tr>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">(3, 1)</td>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">-1</td>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">-2</td>
		</tr>
	</tbody>
</table>

<p>由于 <code>-2 &gt; -12</code>，因此选择下标 <code>(3, 1)</code> 对应的二元组，答案是 <code>[3, 1]</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,3,5], target = 6</span></p>

<p><strong>输出：</strong> <span class="example-io">[-1,-1]</span></p>

<p><strong>解释：</strong></p>

<p>不存在有效的二元组，因为 <code>nums[0]</code> 和 <code>nums[1]</code> 的和虽然为 6，但它们是相等的。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-100 &lt;= nums[i], target &lt;= 100</code></li>
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
