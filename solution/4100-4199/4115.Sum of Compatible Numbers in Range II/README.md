---
comments: true
difficulty: 困难
---

<!-- problem:start -->

# [4115. 区间内的兼容数字之和 II 🔒](https://leetcode.cn/problems/sum-of-compatible-numbers-in-range-ii)

[English Version](/solution/4100-4199/4115.Sum%20of%20Compatible%20Numbers%20in%20Range%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数 <code>n</code> 和 <code>k</code>。</p>

<p>如果一个<strong>&nbsp;正&nbsp;</strong>整数 <code>x</code> 同时满足以下两个条件，则称其为&nbsp;<strong>兼容</strong>：</p>

<ul>
	<li><code>abs(n - x) &lt;= k</code></li>
	<li><code>(n &amp; x) == 0</code></li>
</ul>

<p>返回所有&nbsp;<strong>兼容&nbsp;</strong>整数 <code>x</code> 的总和。</p>

<p>由于答案可能很大，请返回其对 <code>10<sup>9</sup> + 7</code> 取模后的结果。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>这里，<code>&amp;</code> 表示&nbsp;<strong>按位与</strong>&nbsp;运算符。</li>
	<li>整数 <code>i</code> 和 <code>j</code> 之间的&nbsp;<strong>绝对&nbsp;</strong>差定义为 <code>abs(i - j)</code>。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 2, k = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">10</span></p>

<p><strong>解释：</strong></p>

<p>兼容整数为：</p>

<ul>
	<li><code>x = 1</code>，因为 <code>abs(2 - 1) = 1</code> 且 <code>2 &amp; 1 = 0</code>。</li>
	<li><code>x = 4</code>，因为 <code>abs(2 - 4) = 2</code> 且 <code>2 &amp; 4 = 0</code>。</li>
	<li><code>x = 5</code>，因为 <code>abs(2 - 5) = 3</code> 且 <code>2 &amp; 5 = 0</code>。</li>
</ul>

<p>因此，答案为 <code>1 + 4 + 5 = 10</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 5, k = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>区间 <code>[4, 6]</code> 中没有兼容整数。因此，答案为 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>15</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>15</sup></code></li>
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
