---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4107. 统计有效后缀排序的数量 I](https://leetcode.cn/problems/count-valid-suffix-sorts-i)

[English Version](/solution/4100-4199/4107.Count%20Valid%20Suffix%20Sorts%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个长度均为 <code>n</code> 的整数数组 <code>nums</code> 和 <code>target</code>。</p>

<p>对于每个下标 <code>i</code>，考虑将原数组的<strong>&nbsp;后缀</strong> <code>nums[i..n - 1]</code> 按<strong>&nbsp;非递增顺序</strong>&nbsp;排序。</p>

<p>返回使排序后的数组等于 <code>target</code> 的下标 <code>i</code> 的数量。</p>

<p><strong>子数组&nbsp;</strong>是数组中一个连续的<strong>&nbsp;非空&nbsp;</strong>元素序列。</p>

<p>数组的&nbsp;<strong>后缀&nbsp;</strong>是从任意下标开始并延伸至数组末尾的子数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3], target = [3,2,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>i = 0</code>：排序后的数组为 <code>[3, 2, 1]</code>，等于 <code>target</code>。</li>
	<li><code>i = 1</code>：排序后的数组为 <code>[1, 3, 2]</code>。</li>
	<li><code>i = 2</code>：排序后的数组为 <code>[1, 2, 3]</code>。</li>
</ul>

<p>只有 <code>i = 0</code> 符合要求，因此答案为 1。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,5,6,4,6], target = [1,5,6,6,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>选择 <code>i = 2</code> 或 <code>i = 3</code>，排序后都会得到 <code>[1, 5, 6, 6, 4]</code>。其他下标均无法得到 <code>target</code>。因此，答案为 2。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [7,6,8], target = [8,6,7]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>分别对从下标 0、1 和 2 开始的后缀排序，会依次得到 <code>[8, 7, 6]</code>、<code>[7, 8, 6]</code> 和 <code>[7, 6, 8]</code>。它们均不等于 <code>target</code>，因此答案为 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length == target.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], target[i] &lt;= 10<sup>​​​​​​​5</sup></code>​​​​​​​​​​​​​​</li>
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
