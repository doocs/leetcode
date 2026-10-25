---
comments: true
difficulty: 简单
---

<!-- problem:start -->

# [4092. 二进制位得分](https://leetcode.cn/problems/binary-digit-score)

[English Version](/solution/4000-4099/4092.Binary%20Digit%20Score/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数 <code>a</code> 和 <code>b</code>。</p>

<p>设 <code>s1</code> 和 <code>s2</code> 分别为 <code>a</code> 和 <code>b</code> 的二进制表示中<strong>置位</strong>的数量。</p>

<p>这两个整数的<strong>得分</strong>定义如下：</p>

<ul>
	<li>如果 <code>s1 &gt;= s2</code>，则得分为 <code>s1 * s2</code>。</li>
	<li>否则，得分为 <code>s2 / s1</code>，其中除法采用整数除法。</li>
</ul>

<p>返回一个整数，表示 <code>a</code> 和 <code>b</code> 的得分。</p>

<p><strong>置位</strong>是指数的二进制表示中值为 1 的位。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">a = 14, b = 6</span></p>

<p><strong>输出：</strong> <span class="example-io">6</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>a = 14</code> 的二进制表示为 <code>"1110"</code>，因此 <code>s1 = 3</code>。</li>
	<li><code>b = 6</code> 的二进制表示为 <code>"110"</code>，因此 <code>s2 = 2</code>。</li>
	<li>由于 <code>s1 &gt; s2</code>，<code>a</code> 和 <code>b</code> 的得分为 <code>s1 * s2 = 3 * 2 = 6</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">a = 5, b = 15</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>a = 5</code> 的二进制表示为 <code>"101"</code>，因此 <code>s1 = 2</code>。</li>
	<li><code>b = 15</code> 的二进制表示为 <code>"1111"</code>，因此 <code>s2 = 4</code>。</li>
	<li>由于 <code>s1 &lt; s2</code>，<code>a</code> 和 <code>b</code> 的得分为 <code>s2 / s1 = 4 / 2 = 2</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">a = 10, b = 12</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>a = 10</code> 的二进制表示为 <code>"1010"</code>，因此 <code>s1 = 2</code>。</li>
	<li><code>b = 12</code> 的二进制表示为 <code>"1100"</code>，因此 <code>s2 = 2</code>。</li>
	<li>由于 <code>s1 == s2</code>，<code>a</code> 和 <code>b</code> 的得分为 <code>s1 * s2 = 2 * 2 = 4</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= a, b &lt;= 100</code></li>
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
