---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3692.Majority%20Frequency%20Characters/README.md
---

<!-- problem:start -->

# [3692. 众数频率字符](https://leetcode.cn/problems/majority-frequency-characters)

[English Version](/solution/3600-3699/3692.Majority%20Frequency%20Characters/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由小写英文字母组成的字符串 <code>s</code>。</p>

<p>对于一个值 <code>k</code>，<strong>频率组</strong> 是在 <code>s</code> 中恰好出现 <code>k</code> 次的字符集合。</p>

<p><strong>众数频率组</strong> 是包含 <strong>不同&nbsp;</strong>字符数量最多的频率组。</p>

<p>返回一个字符串，包含众数频率组中的所有字符，字符的顺序 <strong>不限&nbsp;</strong>。如果两个或多个频率组的大小并列最大，则选择其频率 <code>k</code> <strong>较大&nbsp;</strong>的那个组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "aaabbbccdddde"</span></p>

<p><strong>输出:</strong> <span class="example-io">"ab"</span></p>

<p><strong>解释:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">频率 (k)</th>
			<th style="border: 1px solid black;">组中不同字符</th>
			<th style="border: 1px solid black;">组大小</th>
			<th style="border: 1px solid black;">是否众数?</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">{d}</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">否</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">{a, b}</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><strong>是</strong></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">{c}</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">否</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">{e}</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">否</td>
		</tr>
	</tbody>
</table>

<p>字符 <code>'a'</code> 和 <code>'b'</code> 的频率相同，都为 3，它们在众数频率组中。</p>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "abcd"</span></p>

<p><strong>输出:</strong> <span class="example-io">"abcd"</span></p>

<p><strong>解释:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">频率 (k)</th>
			<th style="border: 1px solid black;">组中不同字符</th>
			<th style="border: 1px solid black;">组大小</th>
			<th style="border: 1px solid black;">是否众数?</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">{a, b, c, d}</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;"><strong>是</strong></td>
		</tr>
	</tbody>
</table>

<p>所有字符的频率都相同，都为 1，它们都在众数频率组中。</p>
</div>

<p><strong class="example">示例 3:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "pfpfgi"</span></p>

<p><strong>输出:</strong> <span class="example-io">"fp"</span></p>

<p><strong>解释:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">频率 (k)</th>
			<th style="border: 1px solid black;">组中不同字符</th>
			<th style="border: 1px solid black;">组大小</th>
			<th style="border: 1px solid black;">是否众数?</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">{p, f}</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><strong>是</strong></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">{g, i}</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">否 (组大小并列，选择频率更大的 k = 2)</td>
		</tr>
	</tbody>
</table>

<p>字符 <code>'p'</code> 和 <code>'f'</code> 的频率相同，都为 2，它们在众数频率组中。频率为 1 的组大小并列，但我们选择频率更高的组 2。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 只包含小写英文字母。</li>
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
