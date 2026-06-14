---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3916.Number%20of%20ZigZag%20Arrays%20III/README.md
tags:
    - 数学
    - 动态规划
    - 前缀和
---

<!-- problem:start -->

# [3916. 锯齿形数组的总数 III 🔒](https://leetcode.cn/problems/number-of-zigzag-arrays-iii)

[English Version](/solution/3900-3999/3916.Number%20of%20ZigZag%20Arrays%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你三个整数 <code>n</code>、<code>l</code> 和 <code>r</code>。</p>

<p>长度为 <code>n</code> 的锯齿形数组定义如下：</p>

<ul>
	<li>每个元素的取值范围为 <code>[l, r]</code>。</li>
	<li>任意&nbsp;<strong>两个&nbsp;</strong>相邻的元素都不相等。</li>
	<li>任意&nbsp;<strong>三个&nbsp;</strong>连续的元素不能构成一个&nbsp;<strong>严格递增&nbsp;</strong>或&nbsp;<strong>严格递减&nbsp;</strong>的序列。</li>
</ul>

<p>返回满足条件的锯齿形数组的总数。</p>

<p>由于答案可能很大，请将结果对 <code>10<sup>9</sup> + 7</code> 取余数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span>n = 3, l = 4, r = 5</span></p>

<p><strong>输出：</strong><span>2</span></p>

<p><strong>解释：</strong></p>

<p>在取值范围 <code>[4, 5]</code> 内，长度为 <code>n = 3</code> 的锯齿形数组只有 2 种：</p>

<ul>
	<li><code>[4, 5, 4]</code></li>
	<li><code>[5, 4, 5]</code></li>
</ul>
</div>

<p><strong>示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span>n = 3, l = 1, r = 3</span></p>

<p><strong>输出：</strong><span>10</span></p>

<p><strong>解释：</strong></p>

<p>在取值范围 <code>[1, 3]</code> 内，长度为 <code>n = 3</code> 的锯齿形数组共有 10 种：</p>

<ul>
	<li><code>[1, 2, 1]</code>, <code>[1, 3, 1]</code>, <code>[1, 3, 2]</code></li>
	<li><code>[2, 1, 2]</code>, <code>[2, 1, 3]</code>, <code>[2, 3, 1]</code>, <code>[2, 3, 2]</code></li>
	<li><code>[3, 1, 2]</code>, <code>[3, 1, 3]</code>, <code>[3, 2, 3]</code></li>
</ul>

<p>所有数组均符合锯齿形条件。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 200</code></li>
	<li><code>1 &lt;= l &lt; r &lt;= 10<sup>9</sup></code></li>
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
