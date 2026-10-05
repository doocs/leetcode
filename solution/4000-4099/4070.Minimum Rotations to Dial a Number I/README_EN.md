---
comments: true
difficulty: Easy
---

<!-- problem:start -->

# [4070. Minimum Rotations to Dial a Number I](https://leetcode.com/problems/minimum-rotations-to-dial-a-number-i)

[中文文档](/solution/4000-4099/4070.Minimum%20Rotations%20to%20Dial%20a%20Number%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> of length 10 consisting of digits.</p>

<p>The dial contains the digits 0 through 9 in order and is <strong>circular</strong>, so 0 and 9 are adjacent. The pointer initially points to 0.</p>

<p>To dial each digit of <code>s</code> <strong>in order</strong>, rotate the pointer until it points to that digit. Each rotation moves the pointer to an <strong>adjacent</strong> digit, and you may rotate in <strong>either</strong> direction. Dialing a digit that the pointer already points to requires no rotations.</p>

<p>Return the <strong>minimum</strong> total number of rotations needed to dial every digit of <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;0192837465&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">25</span></p>

<p><strong>Explanation:</strong></p>

<table style="border-collapse: collapse; text-align: center;">
	<thead>
		<tr>
			<th style="border: 1px solid #ccc; padding: 5px;">Step</th>
			<th style="border: 1px solid #ccc; padding: 5px;">From</th>
			<th style="border: 1px solid #ccc; padding: 5px;">To</th>
			<th style="border: 1px solid #ccc; padding: 5px;">Rotations</th>
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

<p>The total is <code>0 + 1 + 2 + 3 + 4 + 5 + 4 + 3 + 2 + 1 = 25</code>, which is the minimum total number of rotations.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1200210200&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<table style="border-collapse: collapse; text-align: center;">
	<thead>
		<tr>
			<th style="border: 1px solid #ccc; padding: 5px;">Step</th>
			<th style="border: 1px solid #ccc; padding: 5px;">From</th>
			<th style="border: 1px solid #ccc; padding: 5px;">To</th>
			<th style="border: 1px solid #ccc; padding: 5px;">Rotations</th>
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

<p>The total is <code>1 + 1 + 2 + 0 + 2 + 1 + 1 + 2 + 2 + 0 = 12</code>, which is the minimum total number of rotations.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>s.length == 10</code></li>
	<li><code>s</code> consists only of digits <code>&#39;0&#39;</code> to <code>&#39;9&#39;</code></li>
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
