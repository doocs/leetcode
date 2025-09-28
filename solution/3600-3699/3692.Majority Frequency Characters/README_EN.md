---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3692.Majority%20Frequency%20Characters/README_EN.md
---

<!-- problem:start -->

# [3692. Majority Frequency Characters](https://leetcode.com/problems/majority-frequency-characters)

[中文文档](/solution/3600-3699/3692.Majority%20Frequency%20Characters/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of lowercase English letters.</p>

<p>The <strong>frequency group</strong> for a value <code>k</code> is the set of characters that appear exactly <code>k</code> times in s.</p>

<p>The <strong>majority frequency group</strong> is the frequency group that contains the largest number of <strong>distinct</strong> characters.</p>

<p>Return a string containing all characters in the majority frequency group, in <strong>any</strong> order. If two or more frequency groups tie for that largest size, pick the group whose frequency <code>k</code> is <strong>larger</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aaabbbccdddde&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;ab&quot;</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Frequency (k)</th>
			<th style="border: 1px solid black;">Distinct characters in group</th>
			<th style="border: 1px solid black;">Group size</th>
			<th style="border: 1px solid black;">Majority?</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">{d}</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">No</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">{a, b}</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><strong>Yes</strong></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">{c}</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">No</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">{e}</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">No</td>
		</tr>
	</tbody>
</table>

<p>Both characters <code>&#39;a&#39;</code> and <code>&#39;b&#39;</code> share the same frequency 3, they are in the majority frequency group. <code>&quot;ba&quot;</code> is also a valid answer.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcd&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;abcd&quot;</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Frequency (k)</th>
			<th style="border: 1px solid black;">Distinct characters in group</th>
			<th style="border: 1px solid black;">Group size</th>
			<th style="border: 1px solid black;">Majority?</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">{a, b, c, d}</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;"><strong>Yes</strong></td>
		</tr>
	</tbody>
</table>

<p>All characters share the same frequency 1, they are all in the majority frequency group.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;pfpfgi&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;fp&quot;</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Frequency (k)</th>
			<th style="border: 1px solid black;">Distinct characters in group</th>
			<th style="border: 1px solid black;">Group size</th>
			<th style="border: 1px solid black;">Majority?</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">{p, f}</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><strong>Yes</strong></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">{g, i}</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">No (tied size, lower frequency)</td>
		</tr>
	</tbody>
</table>

<p>Both characters <code>&#39;p&#39;</code> and <code>&#39;f&#39;</code> share the same frequency 2, they are in the majority frequency group. There is a tie in group size with frequency 1, but we pick the higher frequency: 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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
