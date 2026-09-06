---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4042.Valid%20K-Unique%20Subarrays%20II/README_EN.md
---

<!-- problem:start -->

# [4042. Valid K-Unique Subarrays II 🔒](https://leetcode.com/problems/valid-k-unique-subarrays-ii)

[中文文档](/solution/4000-4099/4042.Valid%20K-Unique%20Subarrays%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and an integer <code>k</code>.</p>

<p>You are also given integers <code>l0</code> and <code>r0</code>, which define the first query, and an integer <code>q</code>, representing the total number of queries to process.</p>

<p>A <strong><span data-keyword="subarray-nonempty">subarray</span></strong> <code>nums[l<sub>i</sub>..r<sub>i</sub>]</code> is considered <strong>valid</strong> if:</p>

<ul>
	<li>It contains <strong>exactly</strong> <code>k</code> <strong>distinct</strong> numbers, and</li>
	<li>Every distinct number in it occurs an <strong>even</strong> number of times.</li>
</ul>

<p>For query 0, set <code>l<sub>0</sub> = l0</code> and <code>r<sub>0</sub> = r0</code>.</p>

<p>Let <code>ans<sub>i</sub></code> denote the result of the <code>i<sup>th</sup></code> query, where <code>ans<sub>i</sub> = 1</code> if <code>nums[l<sub>i</sub>..r<sub>i</sub>]</code> is <strong>valid</strong>, and <code>ans<sub>i</sub> = 0</code> otherwise.</p>

<p>For each <code>i &gt; 0</code>, generate the next query as follows:</p>

<ul>
	<li>If <code>ans<sub>i-1</sub> = 1</code>, set <code>g<sub>i-1</sub> = l<sub>i-1</sub> + r<sub>i-1</sub></code>. Otherwise, set <code>g<sub>i-1</sub> = r<sub>i-1</sub> - l<sub>i-1</sub></code>.</li>
	<li>Compute <code>l<sub>i</sub> = (l<sub>i-1</sub> XOR g<sub>i-1</sub>) % n</code> and <code>r<sub>i</sub> = (r<sub>i-1</sub> XOR g<sub>i-1</sub>) % n</code>.</li>
	<li>If <code>l<sub>i</sub> &gt; r<sub>i</sub></code>, swap them.</li>
</ul>

<p>Return a boolean array <code>ans</code>, where <code>ans[i]</code> is <code>true</code> if <code>ans<sub>i</sub> = 1</code>, and <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,2,1], k = 2, l0 = 1, r0 = 2, q = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[false,true]</span></p>

<p><strong>Explanation:</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th><code>i</code></th>
			<th><code>[l<sub>i</sub>, r<sub>i</sub>]</code></th>
			<th>Subarray</th>
			<th>Distinct numbers</th>
			<th>Counts</th>
			<th>Validity check</th>
			<th><code>ans[i]</code></th>
			<th><code>[l<sub>i+1</sub>, r<sub>i+1</sub>]</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>0</td>
			<td>[1, 2]</td>
			<td>[2, 2]</td>
			<td>{2} &rarr; 1</td>
			<td>{2:2}</td>
			<td><code>false</code>: The subarray contains fewer than <code>k</code> distinct numbers.</td>
			<td><code>ans<sub>0</sub> = 0</code></td>
			<td><code>g<sub>0</sub> = 2 - 1 = 1<br />
			l<sub>1</sub> = (1 XOR 1) % 4 = 0<br />
			r<sub>1</sub> = (2 XOR 1) % 4 = 3</code></td>
		</tr>
		<tr>
			<td>1</td>
			<td>[0, 3]</td>
			<td>[1, 2, 2, 1]</td>
			<td>{1,2} &rarr; 2</td>
			<td>{1:2,2:2}</td>
			<td><code>true</code>: The subarray contains exactly <code>k</code> distinct numbers, each occurring an even number of times.</td>
			<td><code>ans<sub>1</sub> = 1</code></td>
			<td>-</td>
		</tr>
	</tbody>
</table>

<p>Thus, <code>ans = [false, true]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,3,4], k = 1, l0 = 2, r0 = 3, q = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[true,false]</span></p>

<p><strong>Explanation:</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th><code>i</code></th>
			<th><code>[l<sub>i</sub>, r<sub>i</sub>]</code></th>
			<th>Subarray</th>
			<th>Distinct numbers</th>
			<th>Counts</th>
			<th>Validity check</th>
			<th><code>ans[i]</code></th>
			<th><code>[l<sub>i+1</sub>, r<sub>i+1</sub>]</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>0</td>
			<td>[2, 3]</td>
			<td>[3, 3]</td>
			<td>{3} &rarr; 1</td>
			<td>{3:2}</td>
			<td><code>true</code>: The subarray contains exactly <code>k</code> distinct numbers, each occurring an even number of times.</td>
			<td><code>ans<sub>0</sub> = 1</code></td>
			<td><code>g<sub>0</sub> = 2 + 3 = 5<br />
			l<sub>1</sub> = (2 XOR 5) % 5 = 7 % 5 = 2<br />
			r<sub>1</sub> = (3 XOR 5) % 5 = 6 % 5 = 1</code><br />
			Since <code>l<sub>1</sub> &gt; r<sub>1</sub></code>, swap them to obtain <code>[l<sub>1</sub>, r<sub>1</sub>] = [1, 2]</code>.</td>
		</tr>
		<tr>
			<td>1</td>
			<td>[1, 2]</td>
			<td>[2, 3]</td>
			<td>{2,3} &rarr; 2</td>
			<td>{2:1,3:1}</td>
			<td><code>false</code>: The subarray contains 2 distinct numbers instead of exactly <code>k = 1</code>.</td>
			<td><code>ans<sub>1</sub> = 0</code></td>
			<td>-</td>
		</tr>
	</tbody>
</table>

<p>Thus, <code>ans = [true, false]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 5 &times; 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 5 &times; 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li><code>0 &lt;= l<sub>0</sub> &lt; r<sub>0</sub> &lt;= n - 1</code></li>
	<li><code>1 &lt;= q &lt;= 5 &times; 10<sup>5</sup></code></li>
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
