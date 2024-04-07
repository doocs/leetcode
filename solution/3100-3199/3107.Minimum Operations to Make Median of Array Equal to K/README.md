# [3107. 使数组中位数等于 K 的最少操作数](https://leetcode.cn/problems/minimum-operations-to-make-median-of-array-equal-to-k)

[English Version](/solution/3100-3199/3107.Minimum%20Operations%20to%20Make%20Median%20of%20Array%20Equal%20to%20K/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个 <strong>非负</strong>&nbsp;整数&nbsp;<code>k</code>&nbsp;。</p>

<p>一次操作中，你可以选择任一下标&nbsp;<code>i</code>&nbsp;，然后将&nbsp;<code>nums[i]</code>&nbsp;加&nbsp;<code>1</code>&nbsp;或者减&nbsp;<code>1</code>&nbsp;。</p>

<p>请你返回将 <code>nums</code>&nbsp;<strong>中位数</strong>&nbsp;变为 <code>k</code>&nbsp;所需要的 <strong>最少</strong>&nbsp;操作次数。</p>

<p>一个数组的 <strong>中位数</strong>&nbsp;指的是数组按 <strong>非递减</strong> 顺序排序后最中间的元素。如果数组长度为偶数，我们选择中间两个数的较大值为中位数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [2,5,6,8,5], k = 4</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><b>解释：</b>我们将&nbsp;<code>nums[1]</code> 和&nbsp;<code>nums[4]</code>&nbsp;减 <code>1</code>&nbsp;得到&nbsp;<code>[2, 4, 6, 8, 4]</code>&nbsp;。现在数组的中位数等于&nbsp;<code>k</code>&nbsp;。所以答案为 2 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [2,5,6,8,5], k = 7</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><b>解释：</b>我们将&nbsp;<code>nums[1]</code>&nbsp;增加 1 两次，并且将&nbsp;<code>nums[2]</code>&nbsp;增加 1 一次，得到&nbsp;<code>[2, 7, 7, 8, 5]</code>&nbsp;。结果数组的中位数等于&nbsp;<code>k</code>&nbsp;。所以答案为 3 。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,3,4,5,6], k = 4</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><b>解释：</b>数组中位数已经等于 <code>k</code>&nbsp;了，所以不需要进行任何操作。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

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

<!-- end -->
