---
comments: true
difficulty: 简单
---

<!-- problem:start -->

# [4048. 统计等间距出现整数数目 I](https://leetcode.cn/problems/count-values-with-equally-spaced-occurrences-i)

[English Version](/solution/4000-4099/4048.Count%20Values%20With%20Equally%20Spaced%20Occurrences%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>如果一个整数 <code>x</code> 满足以下条件，则被称为 <strong>特别</strong> 的：</p>

<ul>
	<li><code>x</code> 在 <code>nums</code> 中 <strong>恰好出现三次</strong>。</li>
	<li><code>x</code> 的 <strong>所有</strong> 三次出现，在 <code>nums</code> 中都是 <strong>等间隔</strong> 的。换句话说，如果 <code>x</code> 的所有出现位置的下标为 <code>i<sub>1</sub> &lt; i<sub>2</sub> &lt; i<sub>3</sub></code>，那么 <code>i<sub>2</sub> - i<sub>1</sub> = i<sub>3</sub> - i<sub>2</sub></code>。</li>
</ul>

<p>返回 <code>nums</code> 中 <strong>不同</strong> 特别整数的数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [1,8,1,5,1,5,8,5]</span></p>

<p><strong>输出:</strong> <span class="example-io">2</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>1 是特别的，因为它恰好出现三次，且出现的等间隔下标为 0、2 和 4。</li>
	<li>5 是特别的，因为它恰好出现三次，且出现的等间隔下标为 3、5 和 7。</li>
	<li>8 不是特别的，因为它只出现了两次。</li>
</ul>

<p>因此，答案是 2。</p>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [8,8,8,8]</span></p>

<p><strong>输出:</strong>&nbsp;0</p>

<p><strong>解释:</strong></p>

<p>8 不是特别的，因为它出现的次数不是恰好三次。因此，答案是 0。</p>
</div>

<p><strong class="example">示例 3:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [8,6,6,8,8]</span></p>

<p><strong>输出:</strong> <span class="example-io">0</span></p>

<p><strong>解释:</strong></p>

<p>8 出现的下标为 0、3 和 4，这些下标不是等间隔的。6 只出现了两次。因此，没有整数是特别的。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
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
