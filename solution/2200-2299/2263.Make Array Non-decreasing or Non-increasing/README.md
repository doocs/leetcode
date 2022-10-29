# [2263. 数组变为有序的最小操作次数](https://leetcode.cn/problems/make-array-non-decreasing-or-non-increasing)

[English Version](/solution/2200-2299/2263.Make%20Array%20Non-decreasing%20or%20Non-increasing/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 。在一步操作中，你可以：</p>

<ul>
	<li>在范围&nbsp;<code>0 &lt;= i &lt; nums.length</code> 内选出一个下标 <code>i</code></li>
	<li>将 <code>nums[i]</code> 的值变为 <code>nums[i] + 1</code> <strong>或</strong> <code>nums[i] - 1</code></li>
</ul>

<p>返回将数组 <code>nums</code> 变为 <strong>非递增</strong> 或<strong> 非递减 </strong>所需的 <strong>最小</strong> 操作次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,4,5,0]
<strong>输出：</strong>4
<strong>解释：</strong>
一种可行的操作顺序，能够将 nums 变为非递增排列：
- nums[1] 加 1 一次，使其变为 3 。
- nums[2] 减 1 一次，使其变为 3 。
- nums[3] 减 1 两次，使其变为 3 。
经过 4 次操作后，nums 变为 [3,3,3,3,0] ，按非递增顺序排列。
注意，也可以用 4 步操作将 nums 变为 [4,4,4,4,0] ，同样满足题目要求。
可以证明最少需要 4 步操作才能将数组变为非递增或非递减排列。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,3,4]
<strong>输出：</strong>0
<strong>解释：</strong>数组已经是按非递减顺序排列了，无需执行任何操作，返回 0 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [0]
<strong>输出：</strong>0
<strong>解释：</strong>数组已经是按非递减顺序排列了，无需执行任何操作，返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以设计并实现时间复杂度为 <code>O(n*log(n))</code> 的解法吗?</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
