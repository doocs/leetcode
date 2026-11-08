---
comments: true
difficulty: 困难
---

<!-- problem:start -->

# [4109. 统计有效后缀排序的数量 II](https://leetcode.cn/problems/count-valid-suffix-sorts-ii)

[English Version](/solution/4100-4199/4109.Count%20Valid%20Suffix%20Sorts%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数数组 <code>nums</code> 和 <code>target</code>，长度分别为 <code>n</code> 和 <code>m</code>，其中 <code>n &lt;= m</code>。</p>

<p>对于每个下标 <code>i</code>，考虑将原数组的&nbsp;<strong>后缀</strong> <code>nums[i..n - 1]</code> 按<strong>&nbsp;非递增顺序&nbsp;</strong>排序。</p>

<p>返回一个长度为 <code>m - n + 1</code> 的整数数组 <code>ans</code>，其中 <code>ans[k]</code> 表示使排序后的数组等于 <code>target[k..k + n - 1]</code> 的下标 <code>i</code> 的数量。</p>

<p><strong>子数组&nbsp;</strong>是数组中一个连续的<strong>&nbsp;非空</strong>&nbsp;元素序列。</p>

<p>数组的&nbsp;<strong>后缀</strong>&nbsp;是从任意下标开始并延伸至数组末尾的子数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,2,3], target = [2,2,3,2,1,3,2,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,1,0,0,0,1]</span></p>

<p><strong>解释：</strong></p>

<p>分别对从下标 0、1 和 2 开始的后缀排序，会依次得到 <code>[3, 2, 2]</code>、<code>[2, 3, 2]</code> 和 <code>[2, 2, 3]</code>。</p>

<ul>
	<li><code>k = 0</code>：选择 <code>i = 2</code> 可以得到 <code>[2, 2, 3]</code>。</li>
	<li><code>k = 1</code>：选择 <code>i = 1</code> 可以得到 <code>[2, 3, 2]</code>。</li>
	<li><code>k = 2, 3, 4</code>：均无法得到对应的子数组。</li>
	<li><code>k = 5</code>：选择 <code>i = 0</code> 可以得到 <code>[3, 2, 2]</code>。</li>
</ul>

<p>因此，答案为 <code>[1,1,0,0,0,1]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [5,6,5], target = [5,6,5,5,6,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">[2,1,0,2]</span></p>

<p><strong>解释：</strong></p>

<p>对从下标 0 开始的后缀排序，会得到 <code>[6, 5, 5]</code>；对从下标 1 或 2 开始的后缀排序，都会得到 <code>[5, 6, 5]</code>。</p>

<ul>
	<li><code>k = 0</code>：选择 <code>i = 1</code> 或 <code>i = 2</code> 可以得到 <code>[5, 6, 5]</code>。</li>
	<li><code>k = 1</code>：选择 <code>i = 0</code> 可以得到 <code>[6, 5, 5]</code>。</li>
	<li><code>k = 2</code>：无法得到 <code>[5, 5, 6]</code>。</li>
	<li><code>k = 3</code>：选择 <code>i = 1</code> 或 <code>i = 2</code> 可以得到 <code>[5, 6, 5]</code>。</li>
</ul>

<p>因此，答案为 <code>[2, 1, 0, 2]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>m == target.length</code></li>
	<li><code>1 &lt;= n &lt;= m &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], target[i] &lt;= 10<sup>5</sup></code></li>
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
