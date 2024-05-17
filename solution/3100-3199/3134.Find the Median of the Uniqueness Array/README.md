---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3134.Find%20the%20Median%20of%20the%20Uniqueness%20Array/README.md
rating: 2451
source: 第 395 场周赛 Q4
tags:
    - 数组
    - 哈希表
    - 二分查找
    - 滑动窗口
---

<!-- problem:start -->

# [3134. 找出唯一性数组的中位数](https://leetcode.cn/problems/find-the-median-of-the-uniqueness-array)

[English Version](/solution/3100-3199/3134.Find%20the%20Median%20of%20the%20Uniqueness%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 。数组 <code>nums</code> 的<strong> 唯一性数组</strong> 是一个按元素从小到大排序的数组，包含了 <code>nums</code> 的所有<span data-keyword="subarray-nonempty">非空子数组中</span>不同元素的个数。</p>

<p>换句话说，这是由所有 <code>0 &lt;= i &lt;= j &lt; nums.length</code> 的 <code>distinct(nums[i..j])</code> 组成的递增数组。</p>

<p>其中，<code>distinct(nums[i..j])</code> 表示从下标 <code>i</code> 到下标 <code>j</code> 的子数组中不同元素的数量。</p>

<p>返回 <code>nums</code> <strong>唯一性数组 </strong>的 <strong>中位数 </strong>。</p>

<p><strong>注意</strong>，数组的 <strong>中位数 </strong>定义为有序数组的中间元素。如果有两个中间元素，则取值较小的那个。<!-- notionvc: 7e0f5178-4273-4a82-95ce-3395297921dc --></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,2,3]</span></p>

<p><strong>输出：</strong><span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code> 的唯一性数组为 <code>[distinct(nums[0..0]), distinct(nums[1..1]), distinct(nums[2..2]), distinct(nums[0..1]), distinct(nums[1..2]), distinct(nums[0..2])]</code>，即 <code>[1, 1, 1, 2, 2, 3]</code> 。唯一性数组的中位数为 1 ，因此答案是 1 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [3,4,3,4,5]</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code> 的唯一性数组为 <code>[1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3]</code> 。唯一性数组的中位数为 2 ，因此答案是 2 。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [4,3,5,4]</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code> 的唯一性数组为 <code>[1, 1, 1, 1, 2, 2, 2, 3, 3, 3]</code> 。唯一性数组的中位数为 2 ，因此答案是 2 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
