# [2198. Number of Single Divisor Triplets](https://leetcode-cn.com/problems/number-of-single-divisor-triplets)

[English Version](/solution/2100-2199/2198.Number%20of%20Single%20Divisor%20Triplets/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given a <strong>0-indexed</strong> array of positive integers <code>nums</code>. A triplet of three <strong>distinct</strong> indices <code>(i, j, k)</code> is called a <strong>single divisor triplet</strong> of <code>nums</code> if <code>nums[i] + nums[j] + nums[k]</code> is divisible by <strong>exactly one</strong> of <code>nums[i]</code>, <code>nums[j]</code>, or <code>nums[k]</code>.</p>
Return <em>the number of <strong>single divisor triplets</strong> of </em><code>nums</code><em>.</em>
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,6,7,3,2]
<strong>Output:</strong> 12
<strong>Explanation:
</strong>The triplets (0, 3, 4), (0, 4, 3), (3, 0, 4), (3, 4, 0), (4, 0, 3), and (4, 3, 0) have the values of [4, 3, 2] (or a permutation of [4, 3, 2]).
4 + 3 + 2 = 9 which is only divisible by 3, so all such triplets are single divisor triplets.
The triplets (0, 2, 3), (0, 3, 2), (2, 0, 3), (2, 3, 0), (3, 0, 2), and (3, 2, 0) have the values of [4, 7, 3] (or a permutation of [4, 7, 3]).
4 + 7 + 3 = 14 which is only divisible by 7, so all such triplets are single divisor triplets.
There are 12 single divisor triplets in total.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2]
<strong>Output:</strong> 6
<strong>Explanation:</strong>
The triplets (0, 1, 2), (0, 2, 1), (1, 0, 2), (1, 2, 0), (2, 0, 1), and (2, 1, 0) have the values of [1, 2, 2] (or a permutation of [1, 2, 2]).
1 + 2 + 2 = 5 which is only divisible by 1, so all such triplets are single divisor triplets.
There are 6 single divisor triplets in total.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
There are no single divisor triplets.
Note that (0, 1, 2) is not a single divisor triplet because nums[0] + nums[1] + nums[2] = 3 and 3 is divisible by nums[0], nums[1], and nums[2].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

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
