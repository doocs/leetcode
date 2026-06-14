---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3959.Check%20Good%20Integer/README.md
---

<!-- problem:start -->

# [3959. 判定好整数](https://leetcode.cn/problems/check-good-integer)

[English Version](/solution/3900-3999/3959.Check%20Good%20Integer/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数 <code>n</code>。</p>

<p>令 <code>digitSum</code> 表示 <code>n</code> 的各位数字之和，令 <code>squareSum</code> 表示 <code>n</code> 的各位数字平方之和。</p>

<p>如果一个整数满足 <code>squareSum - digitSum &gt;= 50</code>，则称它是&nbsp;<strong>好整数&nbsp;</strong>。</p>

<p>如果 <code>n</code> 是好整数，返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 1000</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>1000 的数字为 1、0、0 和 0。</li>
	<li><code>digitSum</code> 为 <code>1 + 0 + 0 + 0 = 1</code>。</li>
	<li><code>squareSum</code> 为 <code>1<sup>2</sup> + 0<sup>2</sup> + 0<sup>2</sup> + 0<sup>2</sup> = 1</code>。</li>
	<li><code>squareSum - digitSum</code> 为 <code>1 - 1 = 0</code>。由于 0 小于 50，因此输出 <code>false</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 19</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>19 的数字为 1 和 9。</li>
	<li><code>digitSum</code> 为 <code>1 + 9 = 10</code>。</li>
	<li><code>squareSum</code> 为 <code>1<sup>2</sup> + 9<sup>2</sup> = 1 + 81 = 82</code>。</li>
	<li><code>squareSum - digitSum</code> 为 <code>82 - 10 = 72</code>。由于 72 大于等于 50，因此输出 <code>true</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
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
