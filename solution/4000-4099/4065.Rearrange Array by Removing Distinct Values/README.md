---
comments: true
difficulty: 简单
---

<!-- problem:start -->

# [4065. 移除不同值重排数组](https://leetcode.cn/problems/rearrange-array-by-removing-distinct-values)

[English Version](/solution/4000-4099/4065.Rearrange%20Array%20by%20Removing%20Distinct%20Values/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>初始时，你有一个&nbsp;<strong>空</strong>&nbsp;数组 <code>ans</code>。重复执行以下操作，直到 <code>nums</code> 变为&nbsp;<strong>空&nbsp;</strong>：</p>

<ul>
	<li>找出当前 <code>nums</code> 中<strong>&nbsp;所有不同&nbsp;</strong>的值。</li>
	<li>将当前 <code>nums</code> 中每个&nbsp;<strong>不同</strong>&nbsp;的值各移除<strong>一个</strong>，并按<strong>&nbsp;升序&nbsp;</strong>将这些值依次添加到 <code>ans</code> 中。</li>
</ul>

<p>返回数组 <code>ans</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,1,3,2,1,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,2,3,1,3,3]</span></p>

<p><strong>解释：</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th scope="col" style="text-align:center;">操作</th>
			<th scope="col" style="text-align:center;">添加到 <code>ans</code> 的值</th>
			<th scope="col" style="text-align:center;">操作后的 <code>nums</code></th>
			<th scope="col" style="text-align:center;">操作后的 <code>ans</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">1, 2, 3</td>
			<td style="text-align:center;"><code>[3, 1, 3]</code></td>
			<td style="text-align:center;"><code>[1, 2, 3]</code></td>
		</tr>
		<tr>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">1, 3</td>
			<td style="text-align:center;"><code>[3]</code></td>
			<td style="text-align:center;"><code>[1, 2, 3, 1, 3]</code></td>
		</tr>
		<tr>
			<td style="text-align:center;">3</td>
			<td style="text-align:center;">3</td>
			<td style="text-align:center;"><code>[]</code></td>
			<td style="text-align:center;"><code>[1, 2, 3, 1, 3, 3]</code></td>
		</tr>
	</tbody>
</table>

<p>此时 <code>nums</code> 已为空，因此答案为 <code>[1, 2, 3, 1, 3, 3]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [7,7,4,4,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">[4,7,4,7,4]</span></p>

<p><strong>解释：</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th scope="col" style="text-align:center;">操作</th>
			<th scope="col" style="text-align:center;">添加到 <code>ans</code> 的值</th>
			<th scope="col" style="text-align:center;">操作后的 <code>nums</code></th>
			<th scope="col" style="text-align:center;">操作后的 <code>ans</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">4, 7</td>
			<td style="text-align:center;"><code>[7, 4, 4]</code></td>
			<td style="text-align:center;"><code>[4, 7]</code></td>
		</tr>
		<tr>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">4, 7</td>
			<td style="text-align:center;"><code>[4]</code></td>
			<td style="text-align:center;"><code>[4, 7, 4, 7]</code></td>
		</tr>
		<tr>
			<td style="text-align:center;">3</td>
			<td style="text-align:center;">4</td>
			<td style="text-align:center;"><code>[]</code></td>
			<td style="text-align:center;"><code>[4, 7, 4, 7, 4]</code></td>
		</tr>
	</tbody>
</table>

<p>此时 <code>nums</code> 已为空，因此答案为 <code>[4, 7, 4, 7, 4]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
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
