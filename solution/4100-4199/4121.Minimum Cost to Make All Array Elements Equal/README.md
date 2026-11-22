---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4121. 使数组中所有元素相等的最小成本](https://leetcode.cn/problems/minimum-cost-to-make-all-array-elements-equal)

[English Version](/solution/4100-4199/4121.Minimum%20Cost%20to%20Make%20All%20Array%20Elements%20Equal/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>。</p>

<p>你可以从以下操作中 <strong>任意</strong> 选择，执行 <strong>任意</strong> 次，顺序 <strong>任意</strong>。</p>

<ul>
	<li><strong>前缀操作</strong>：选择一个满足 <code>0 &lt; i &lt; n - 1</code> 的下标 <code>i</code>，将下标从 0 到 <code>i - 1</code> 的 <strong>所有</strong> 元素设为 <code>nums[i]</code>。该操作的成本为 <code>i * nums[i]</code>。</li>
	<li><strong>后缀操作</strong>：选择一个满足 <code>0 &lt; i &lt; n - 1</code> 的下标 <code>i</code>，将下标从 <code>i + 1</code> 到 <code>n - 1</code> 的 <strong>所有</strong> 元素设为 <code>nums[i]</code>。该操作的成本为 <code>(n - i - 1) * nums[i]</code>。</li>
</ul>

<p>操作的成本根据 <code>nums[i]</code> 的 <strong>当前</strong> 值计算。所选前缀或后缀中的每个元素都计入成本，即使该元素已经等于 <code>nums[i]</code>。</p>

<p>返回使 <code>nums</code> 中 <strong>所有</strong> 元素都相等所需的 <strong>最小总成本</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>以下是一种达到最小成本的操作序列：</p>

<table style="border: 1px solid black; text-align: center;">
	<thead style="text-align: center;">
		<tr>
			<th style="border: 1px solid black;">nums</th>
			<th style="border: 1px solid black;">操作</th>
			<th style="border: 1px solid black;">i</th>
			<th style="border: 1px solid black;">修改后的 nums</th>
			<th style="border: 1px solid black;">成本</th>
		</tr>
	</thead>
	<tbody style="text-align: center;">
		<tr>
			<td style="border: 1px solid black;">[1, 2, 3]</td>
			<td style="border: 1px solid black;">后缀</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">[1, 2, 2]</td>
			<td style="border: 1px solid black;">(3 - 1 - 1) * 2 = 2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">[1, 2, 2]</td>
			<td style="border: 1px solid black;">前缀</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">[2, 2, 2]</td>
			<td style="border: 1px solid black;">1 * 2 = 2</td>
		</tr>
	</tbody>
</table>

<p>因此，总成本为 <code>2 + 2 = 4</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,3,3,6,3,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">9</span></p>

<p><strong>解释：</strong></p>

<p>以下是一种达到最小成本的操作序列：</p>

<table style="border: 1px solid black; text-align: center;">
	<thead style="text-align: center;">
		<tr>
			<th style="border: 1px solid black;">nums</th>
			<th style="border: 1px solid black;">操作</th>
			<th style="border: 1px solid black;">i</th>
			<th style="border: 1px solid black;">修改后的 nums</th>
			<th style="border: 1px solid black;">成本</th>
		</tr>
	</thead>
	<tbody style="text-align: center;">
		<tr>
			<td style="border: 1px solid black;">[3, 3, 3, 6, 3, 3]</td>
			<td style="border: 1px solid black;">后缀</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">[3, 3, 3, 3, 3, 3]</td>
			<td style="border: 1px solid black;">(6 - 2 - 1) * 3 = 9</td>
		</tr>
	</tbody>
</table>

<p>因此，总成本为 9。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,4,4,5,4,4,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">20</span></p>

<p><strong>解释：</strong></p>

<p>以下是一种达到最小成本的操作序列：</p>

<table style="border: 1px solid black; text-align: center;">
	<thead style="text-align: center;">
		<tr>
			<th style="border: 1px solid black;">nums</th>
			<th style="border: 1px solid black;">操作</th>
			<th style="border: 1px solid black;">i</th>
			<th style="border: 1px solid black;">修改后的 nums</th>
			<th style="border: 1px solid black;">成本</th>
		</tr>
	</thead>
	<tbody style="text-align: center;">
		<tr>
			<td style="border: 1px solid black;">[3, 4, 4, 5, 4, 4, 3]</td>
			<td style="border: 1px solid black;">前缀</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">[4, 4, 4, 4, 4, 4, 3]</td>
			<td style="border: 1px solid black;">4 * 4 = 16</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">[4, 4, 4, 4, 4, 4, 3]</td>
			<td style="border: 1px solid black;">后缀</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">[4, 4, 4, 4, 4, 4, 4]</td>
			<td style="border: 1px solid black;">(7 - 5 - 1) * 4 = 4</td>
		</tr>
	</tbody>
</table>

<p>因此，总成本为 <code>16 + 4 = 20</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
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
