---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3637.Trionic%20Array%20I/README.md
---

<!-- problem:start -->

# [3637. 三段式数组 I](https://leetcode.cn/problems/trionic-array-i)

[English Version](/solution/3600-3699/3637.Trionic%20Array%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p data-end="128" data-start="0">给你一个长度为 <code data-end="51" data-start="48">n</code> 的整数数组 <code data-end="37" data-start="31">nums</code>。</p>

<p data-end="128" data-start="0">如果存在索引 <code data-end="117" data-start="100">0 &lt; p &lt; q &lt; n − 1</code>，使得数组满足以下条件，则称其为 <strong data-end="76" data-start="65">三段式数组（trionic）</strong>：</p>

<ul>
	<li data-end="170" data-start="132"><code data-end="144" data-start="132">nums[0...p]</code>&nbsp;<strong>严格</strong> 递增，</li>
	<li data-end="211" data-start="173"><code data-end="185" data-start="173">nums[p...q]</code>&nbsp;<strong>严格</strong> 递减，</li>
	<li data-end="252" data-start="214"><code data-end="228" data-start="214">nums[q...n − 1]</code>&nbsp;<strong>严格</strong> 递增。</li>
</ul>

<p data-end="315" data-is-last-node="" data-is-only-node="" data-start="254">如果 <code data-end="277" data-start="271">nums</code> 是三段式数组，返回 <code data-end="267" data-start="261">true</code>；否则，返回 <code data-end="314" data-start="307">false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [1,3,5,4,2,6]</span></p>

<p><strong>输出:</strong> <span class="example-io">true</span></p>

<p><strong>解释:</strong></p>

<p>选择 <code data-end="91" data-start="84">p = 2</code>, <code data-end="100" data-start="93">q = 4</code>：</p>

<ul>
	<li><code data-end="130" data-start="108">nums[0...2] = [1, 3, 5]</code> 严格递增&nbsp;(<code data-end="166" data-start="155">1 &lt; 3 &lt; 5</code>)。</li>
	<li><code data-end="197" data-start="175">nums[2...4] = [5, 4, 2]</code> 严格递减&nbsp;(<code data-end="233" data-start="222">5 &gt; 4 &gt; 2</code>)。</li>
	<li><code data-end="262" data-start="242">nums[4...5] = [2, 6]</code> 严格递增&nbsp;(<code data-end="294" data-start="287">2 &lt; 6</code>)。</li>
</ul>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [2,1,3]</span></p>

<p><strong>输出:</strong> <span class="example-io">false</span></p>

<p><strong>解释:</strong></p>

<p>无法选出能使数组满足三段式要求的&nbsp;<code>p</code> 和 <code>q</code> 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li data-end="41" data-start="26"><code data-end="39" data-start="26">3 &lt;= n &lt;= 100</code></li>
	<li data-end="70" data-start="44"><code data-end="70" data-start="44">-1000 &lt;= nums[i] &lt;= 1000</code></li>
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
