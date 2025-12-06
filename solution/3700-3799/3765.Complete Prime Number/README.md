---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3765.Complete%20Prime%20Number/README.md
---

<!-- problem:start -->

# [3765. 完全质数](https://leetcode.cn/problems/complete-prime-number)

[English Version](/solution/3700-3799/3765.Complete%20Prime%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>num</code>。</p>

<p>如果一个数 <code>num</code> 的每一个 <strong>前缀</strong> 和每一个 <strong>后缀</strong> 都是 <strong>质数</strong>，则称该数为 <strong>完全质数</strong>。</p>

<p>如果 <code>num</code> 是完全质数，返回 <code>true</code>，否则返回 <code>false</code>。</p>

<p><strong>注意</strong>：</p>

<ul>
	<li>一个数的 <strong>前缀</strong> 是由该数的 <strong>前</strong> <code>k</code> 位数字构成的。</li>
	<li>一个数的 <strong>后缀</strong> 是由该数的 <strong>后</strong> <code>k</code> 位数字构成的。</li>
	<li><strong>质数</strong> 是大于 1 且只有两个因子（1 和它本身）的自然数。</li>
	<li>个位数只有在它是 <strong>质数</strong> 时才被视为完全质数。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">num = 23</span></p>

<p><strong>输出：</strong><span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>num = 23</code> 的前缀是 2 和 23，它们都是质数。</li>
	<li><code>num = 23</code> 的后缀是 3 和 23，它们都是质数。</li>
	<li>所有的前缀和后缀都是质数，所以 23 是完全质数，答案是 <code>true</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">num = 39</span></p>

<p><strong>输出：</strong><span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>num = 39</code> 的前缀是 3 和 39。3 是质数，但 39 不是质数。</li>
	<li><code>num = 39</code> 的后缀是 9 和 39。9 和 39 都不是质数。</li>
	<li>至少有一个前缀或后缀不是质数，所以 39 不是完全质数，答案是 <code>false</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">num = 7</span></p>

<p><strong>输出：</strong><span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>7 是质数，所以它的所有前缀和后缀都是质数，答案是 <code>true</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10<sup>9</sup></code></li>
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
