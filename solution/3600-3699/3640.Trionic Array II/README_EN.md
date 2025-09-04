---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3640.Trionic%20Array%20II/README_EN.md
rating: 2277
source: Weekly Contest 461 Q4
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3640. Trionic Array II](https://leetcode.com/problems/trionic-array-ii)

[中文文档](/solution/3600-3699/3640.Trionic%20Array%20II/README.md)

## Description

<!-- description:start -->

<p data-end="191" data-start="0">You are given an integer array <code data-end="61" data-start="55">nums</code> of length <code data-end="75" data-start="72">n</code>.</p>

<p data-end="191" data-start="0">A <strong data-end="99" data-is-only-node="" data-start="79">trionic subarray</strong> is a contiguous subarray <code data-end="136" data-start="125">nums[l...r]</code> (with <code data-end="158" data-start="143">0 &lt;= l &lt; r &lt; n</code>) for which there exist indices <code>l &lt; p &lt; q &lt; r</code> such that:</p>

<ul>
	<li data-end="267" data-start="230"><code data-end="241" data-start="230">nums[l...p]</code> is <strong>strictly</strong> increasing,</li>
	<li data-end="307" data-start="270"><code data-end="281" data-start="270">nums[p...q]</code> is <strong>strictly</strong> decreasing,</li>
	<li data-end="347" data-start="310"><code data-end="321" data-start="310">nums[q...r]</code> is <strong>strictly</strong> increasing.</li>
</ul>

<p data-end="609" data-is-last-node="" data-is-only-node="" data-start="349">Return the <strong>maximum</strong> sum of any trionic subarray in <code data-end="417" data-start="411">nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,-2,-1,-3,0,2,-1]</span></p>

<p><strong>Output:</strong> <span class="example-io">-4</span></p>

<p><strong>Explanation:</strong></p>

<p data-end="129" data-start="72">Pick <code data-end="99" data-start="92">l = 1</code>, <code data-end="108" data-start="101">p = 2</code>, <code data-end="117" data-start="110">q = 3</code>, <code data-end="126" data-start="119">r = 5</code>:</p>

<ul>
	<li data-end="203" data-start="132"><code data-end="166" data-start="132">nums[l...p] = nums[1...2] = [-2, -1]</code> is strictly increasing (<code data-end="200" data-start="191">-2 &lt; -1</code>).</li>
	<li data-end="277" data-start="206"><code data-end="240" data-start="206">nums[p...q] = nums[2...3] = [-1, -3]</code> is strictly decreasing (<code data-end="274" data-start="265">-1 &gt; -3</code>)</li>
	<li data-end="396" data-start="280"><code data-end="316" data-start="280">nums[q...r] = nums[3...5] = [-3, 0, 2]</code> is strictly increasing (<code data-end="353" data-start="341">-3 &lt; 0 &lt; 2</code>).</li>
	<li data-end="396" data-start="280">Sum = <code>(-2) + (-1) + (-3) + 0 + 2 = -4</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,4,2,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">14</span></p>

<p><strong>Explanation:</strong></p>

<p data-end="519" data-start="462">Pick <code data-end="489" data-start="482">l = 0</code>, <code data-end="498" data-start="491">p = 1</code>, <code data-end="507" data-start="500">q = 2</code>, <code data-end="516" data-start="509">r = 3</code>:</p>

<ul>
	<li data-end="589" data-start="522"><code data-end="554" data-start="522">nums[l...p] = nums[0...1] = [1, 4]</code> is strictly increasing (<code data-end="586" data-start="579">1 &lt; 4</code>).</li>
	<li data-end="659" data-start="592"><code data-end="624" data-start="592">nums[p...q] = nums[1...2] = [4, 2]</code> is strictly decreasing (<code data-end="656" data-start="649">4 &gt; 2</code>).</li>
	<li data-end="754" data-is-last-node="" data-start="662"><code data-end="694" data-start="662">nums[q...r] = nums[2...3] = [2, 7]</code> is strictly increasing (<code data-end="726" data-start="719">2 &lt; 7</code>).</li>
	<li data-end="754" data-is-last-node="" data-start="662">Sum = <code>1 + 4 + 2 + 7 = 14</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li data-end="883" data-start="851"><code data-end="881" data-start="851">4 &lt;= n = nums.length &lt;= 10<sup>5</sup></code></li>
	<li data-end="914" data-start="886"><code data-end="912" data-start="886">-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li data-end="978" data-is-last-node="" data-start="917">It is guaranteed that at least one trionic subarray exists.</li>
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
