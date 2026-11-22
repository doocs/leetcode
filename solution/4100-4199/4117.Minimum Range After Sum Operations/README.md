---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4117. 求和操作以后的最小极差](https://leetcode.cn/problems/minimum-range-after-sum-operations)

[English Version](/solution/4100-4199/4117.Minimum%20Range%20After%20Sum%20Operations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你三个整数 <code>a</code>、<code>b</code> 和 <code>c</code>。</p>

<p>你可以执行以下操作 <strong>任意</strong> 次（包括零次）：</p>

<ul>
	<li>选择三个整数中的一个，并将其 <strong>替换</strong> 为另外两个整数之和。另外两个整数保持不变。</li>
</ul>

<p>三个整数的 <strong>极差</strong> 定义为 <strong>最大值</strong> 与 <strong>最小值</strong> 之差。</p>

<p>返回执行任意次操作后可以得到的 <strong>最小极差</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">a = 9, b = 2, c = 5</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p>一种得到最小极差的操作序列如下：</p>

<ul>
	<li>将 <code>a</code> 设为 <code>b + c = 2 + 5 = 7</code>。此时三个整数为 <code>a = 7</code>，<code>b = 2</code>，<code>c = 5</code>。</li>
	<li>三个整数的极差为 <code>max(7, 2, 5) - min(7, 2, 5) = 7 - 2 = 5</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">a = 5, b = 7, c = 6</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>不执行任何操作，三个整数的极差为 <code>max(5, 7, 6) - min(5, 7, 6) = 7 - 5 = 2</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">a = 3, b = 3, c = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>不执行任何操作，三个整数的极差为 <code>max(3, 3, 3) - min(3, 3, 3) = 3 - 3 = 0</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= a, b, c &lt;= 100</code></li>
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
