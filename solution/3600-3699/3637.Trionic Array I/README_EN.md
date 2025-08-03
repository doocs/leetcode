---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3637.Trionic%20Array%20I/README_EN.md
---

<!-- problem:start -->

# [3637. Trionic Array I](https://leetcode.com/problems/trionic-array-i)

[中文文档](/solution/3600-3699/3637.Trionic%20Array%20I/README.md)

## Description

<!-- description:start -->

<p data-end="128" data-start="0">You are given an integer array <code data-end="37" data-start="31">nums</code> of length <code data-end="51" data-start="48">n</code>.</p>

<p data-end="128" data-start="0">An array is <strong data-end="76" data-start="65">trionic</strong> if there exist indices <code data-end="117" data-start="100">0 &lt; p &lt; q &lt; n &minus; 1</code> such that:</p>

<ul>
	<li data-end="170" data-start="132"><code data-end="144" data-start="132">nums[0...p]</code> is <strong>strictly</strong> increasing,</li>
	<li data-end="211" data-start="173"><code data-end="185" data-start="173">nums[p...q]</code> is <strong>strictly</strong> decreasing,</li>
	<li data-end="252" data-start="214"><code data-end="228" data-start="214">nums[q...n &minus; 1]</code> is <strong>strictly</strong> increasing.</li>
</ul>

<p data-end="315" data-is-last-node="" data-is-only-node="" data-start="254">Return <code data-end="267" data-start="261">true</code> if <code data-end="277" data-start="271">nums</code> is trionic, otherwise return <code data-end="314" data-start="307">false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,5,4,2,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>Pick <code data-end="91" data-start="84">p = 2</code>, <code data-end="100" data-start="93">q = 4</code>:</p>

<ul>
	<li><code data-end="130" data-start="108">nums[0...2] = [1, 3, 5]</code> is strictly increasing (<code data-end="166" data-start="155">1 &lt; 3 &lt; 5</code>).</li>
	<li><code data-end="197" data-start="175">nums[2...4] = [5, 4, 2]</code> is strictly decreasing (<code data-end="233" data-start="222">5 &gt; 4 &gt; 2</code>).</li>
	<li><code data-end="262" data-start="242">nums[4...5] = [2, 6]</code> is strictly increasing (<code data-end="294" data-start="287">2 &lt; 6</code>).</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no way to pick <code>p</code> and <code>q</code> to form the required three segments.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li data-end="41" data-start="26"><code data-end="39" data-start="26">3 &lt;= n &lt;= 100</code></li>
	<li data-end="70" data-start="44"><code data-end="70" data-start="44">-1000 &lt;= nums[i] &lt;= 1000</code></li>
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
