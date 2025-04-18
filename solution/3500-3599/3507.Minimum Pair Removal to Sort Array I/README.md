---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3507.Minimum%20Pair%20Removal%20to%20Sort%20Array%20I/README.md
tags:
    - 数组
    - 哈希表
    - 链表
    - 双向链表
    - 有序集合
    - 模拟
    - 堆（优先队列）
---

<!-- problem:start -->

# [3507. 移除最小数对使数组有序 I](https://leetcode.cn/problems/minimum-pair-removal-to-sort-array-i)

[English Version](/solution/3500-3599/3507.Minimum%20Pair%20Removal%20to%20Sort%20Array%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组 <code>nums</code>，你可以执行以下操作任意次数：</p>

<ul>
	<li>选择 <strong>相邻&nbsp;</strong>元素对中 <strong>和最小</strong> 的一对。如果存在多个这样的对，选择最左边的一个。</li>
	<li>用它们的和替换这对元素。</li>
</ul>

<p>返回将数组变为&nbsp;<strong>非递减&nbsp;</strong>所需的&nbsp;<strong>最小操作次数&nbsp;</strong>。</p>

<p>如果一个数组中每个元素都大于或等于它前一个元素（如果存在的话），则称该数组为<strong>非递减</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [5,2,3,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>元素对 <code>(3,1)</code> 的和最小，为 4。替换后&nbsp;<code>nums = [5,2,4]</code>。</li>
	<li>元素对 <code>(2,4)</code> 的和为 6。替换后&nbsp;<code>nums = [5,6]</code>。</li>
</ul>

<p>数组 <code>nums</code> 在两次操作后变为非递减。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>数组 <code>nums</code> 已经是非递减的。</p>
</div>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>-1000&nbsp;&lt;= nums[i] &lt;= 1000</code></li>
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
