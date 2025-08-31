---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3669.Balanced%20K-Factor%20Decomposition/README.md
---

<!-- problem:start -->

# [3669. K 因数分解](https://leetcode.cn/problems/balanced-k-factor-decomposition)

[English Version](/solution/3600-3699/3669.Balanced%20K-Factor%20Decomposition/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数 <code>n</code> 和 <code>k</code>，将数字 <code>n</code> 恰好分割成 <code>k</code> 个正整数，使得这些整数的&nbsp;<strong>乘积&nbsp;</strong>等于 <code>n</code>。</p>

<p>返回一个分割方案，使得这些数字中&nbsp;<strong>最大值&nbsp;</strong>和&nbsp;<strong>最小值&nbsp;</strong>之间的&nbsp;<strong>差值&nbsp;</strong>最小化。结果可以以&nbsp;<strong>任意顺序</strong>&nbsp;返回。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 100, k = 2</span></p>

<p><strong>输出：</strong><span class="example-io">[10,10]</span></p>

<p><strong>解释：</strong></p>

<p>分割方案 <code>[10, 10]</code> 的结果是 <code>10 * 10 = 100</code>，且最大值与最小值的差值为 0，这是最小可能值。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 44, k = 3</span></p>

<p><strong>输出：</strong><span class="example-io">[2,2,11]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>分割方案 <code>[1, 1, 44]</code> 的差值为 43</li>
	<li>分割方案 <code>[1, 2, 22]</code> 的差值为 21</li>
	<li>分割方案 <code>[1, 4, 11]</code> 的差值为 10</li>
	<li>分割方案 <code>[2, 2, 11]</code> 的差值为 9</li>
</ul>

<p>因此，<code>[2, 2, 11]</code> 是最优分割方案，其差值最小，为 9。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>4 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= k &lt;= 5</code></li>
	<li><code>k</code> 严格小于 <code>n</code> 的正因数的总数。</li>
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
