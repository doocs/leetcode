---
comments: true
difficulty: 简单
---

<!-- problem:start -->

# [4070. 拨号的最少旋转次数 I](https://leetcode.cn/problems/minimum-rotations-to-dial-a-number-i)

[English Version](/solution/4000-4099/4070.Minimum%20Rotations%20to%20Dial%20a%20Number%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 10、由数字组成的字符串 <code>s</code>。</p>

<p>拨号盘上的数字 0 到 9 按顺序排列，且拨号盘是<strong>环形</strong>的，因此 0 和 9 相邻。指针最初指向 0。</p>

<p>要<strong>按顺序</strong>拨出 <code>s</code> 中的每个数字，需要旋转指针，直到它指向该数字。每次旋转都会将指针移动到一个<strong>相邻</strong>的数字，你可以向<strong>任一</strong>方向旋转。如果指针已经指向要拨出的数字，则无需旋转。</p>

<p>返回拨出 <code>s</code> 中所有数字所需的<strong>最少</strong>总旋转次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "0192837465"</span></p>

<p><strong>输出：</strong> <span class="example-io">25</span></p>

<p><strong>解释：</strong></p>

<table style="border-collapse: collapse; text-align: center;">
	<thead>
		<tr>
			<th style="border: 1px solid #ccc; padding: 5px;">步骤</th>
			<th style="border: 1px solid #ccc; padding: 5px;">起始数字</th>
			<th style="border: 1px solid #ccc; padding: 5px;">目标数字</th>
			<th style="border: 1px solid #ccc; padding: 5px;">旋转次数</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">3</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">9</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">4</td>
			<td style="border: 1px solid #ccc; padding: 5px;">9</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">5</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">8</td>
			<td style="border: 1px solid #ccc; padding: 5px;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">6</td>
			<td style="border: 1px solid #ccc; padding: 5px;">8</td>
			<td style="border: 1px solid #ccc; padding: 5px;">3</td>
			<td style="border: 1px solid #ccc; padding: 5px;">5</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">7</td>
			<td style="border: 1px solid #ccc; padding: 5px;">3</td>
			<td style="border: 1px solid #ccc; padding: 5px;">7</td>
			<td style="border: 1px solid #ccc; padding: 5px;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">8</td>
			<td style="border: 1px solid #ccc; padding: 5px;">7</td>
			<td style="border: 1px solid #ccc; padding: 5px;">4</td>
			<td style="border: 1px solid #ccc; padding: 5px;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">9</td>
			<td style="border: 1px solid #ccc; padding: 5px;">4</td>
			<td style="border: 1px solid #ccc; padding: 5px;">6</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">10</td>
			<td style="border: 1px solid #ccc; padding: 5px;">6</td>
			<td style="border: 1px solid #ccc; padding: 5px;">5</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
		</tr>
	</tbody>
</table>

<p>总旋转次数为 <code>0 + 1 + 2 + 3 + 4 + 5 + 4 + 3 + 2 + 1 = 25</code>，这是最少的总旋转次数。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "1200210200"</span></p>

<p><strong>输出：</strong> <span class="example-io">12</span></p>

<p><strong>解释：</strong></p>

<table style="border-collapse: collapse; text-align: center;">
	<thead>
		<tr>
			<th style="border: 1px solid #ccc; padding: 5px;">步骤</th>
			<th style="border: 1px solid #ccc; padding: 5px;">起始数字</th>
			<th style="border: 1px solid #ccc; padding: 5px;">目标数字</th>
			<th style="border: 1px solid #ccc; padding: 5px;">旋转次数</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">3</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">4</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">5</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">6</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">7</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">8</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">9</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">10</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
		</tr>
	</tbody>
</table>

<p>总旋转次数为 <code>1 + 1 + 2 + 0 + 2 + 1 + 1 + 2 + 2 + 0 = 12</code>，这是最少的总旋转次数。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>s.length == 10</code></li>
	<li><code>s</code> 仅由数字 <code>'0'</code> 到 <code>'9'</code> 组成</li>
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
