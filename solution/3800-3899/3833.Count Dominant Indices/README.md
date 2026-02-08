---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3833.Count%20Dominant%20Indices/README.md
---

<!-- problem:start -->

# [3833. 统计主导元素下标数](https://leetcode.cn/problems/count-dominant-indices)

[English Version](/solution/3800-3899/3833.Count%20Dominant%20Indices/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>。</p>

<p>当下标&nbsp;<code>i</code> 满足以下条件时，该下标处的元素被称为&nbsp;<strong>主导元素</strong>：<code>nums[i] &gt; average(nums[i + 1], nums[i + 2], ..., nums[n - 1])</code></p>

<p>你的任务是统计数组中<strong>&nbsp;主导元素&nbsp;</strong>的下标数。</p>

<p><strong>平均值</strong>&nbsp;是指一组数的总和除以该组数的个数得到的值。</p>

<p><strong>注意</strong>：数组的<strong>&nbsp;最右边元素&nbsp;</strong>不算作<strong>&nbsp;主导元素</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [5,4,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>在下标&nbsp;<code>i = 0</code> 处，值 5 是主导元素，因为 <code>5 &gt; average(4, 3) = 3.5</code>。</li>
	<li>在下标&nbsp;<code>i = 1</code> 处，值 4 是主导元素，相对于子数组 <code>[3]</code>。</li>
	<li>下标&nbsp;<code>i = 2</code> 不是主导元素，因为它右侧没有元素。因此答案是 2。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [4,1,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>在下标&nbsp;<code>i = 0</code> 处，值 4 是主导元素，相对于子数组 <code>[1, 2]</code>。</li>
	<li>在下标&nbsp;<code>i = 1</code> 处，值 1 不是主导元素。</li>
	<li>下标&nbsp;<code>i = 2</code> 不是主导元素，因为它右侧没有元素。因此答案是 1。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code>​​​​​​​</li>
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
