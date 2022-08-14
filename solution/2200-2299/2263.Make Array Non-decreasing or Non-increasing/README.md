# [2263. Make Array Non-decreasing or Non-increasing](https://leetcode.cn/problems/make-array-non-decreasing-or-non-increasing)

[English Version](/solution/2200-2299/2263.Make%20Array%20Non-decreasing%20or%20Non-increasing/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. In one operation, you can:</p>

<ul>
	<li>Choose an index <code>i</code> in the range <code>0 &lt;= i &lt; nums.length</code></li>
	<li>Set <code>nums[i]</code> to <code>nums[i] + 1</code> <strong>or</strong> <code>nums[i] - 1</code></li>
</ul>

<p>Return <em>the <strong>minimum</strong> number of operations to make </em><code>nums</code><em> <strong>non-decreasing</strong> or <strong>non-increasing</strong>.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,4,5,0]
<strong>Output:</strong> 4
<strong>Explanation:</strong>
One possible way to turn nums into non-increasing order is to:
- Add 1 to nums[1] once so that it becomes 3.
- Subtract 1 from nums[2] once so it becomes 3.
- Subtract 1 from nums[3] twice so it becomes 3.
After doing the 4 operations, nums becomes [3,3,3,3,0] which is in non-increasing order.
Note that it is also possible to turn nums into [4,4,4,4,0] in 4 operations.
It can be proven that 4 is the minimum number of operations needed.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,3,4]
<strong>Output:</strong> 0
<strong>Explanation:</strong> nums is already in non-decreasing order, so no operations are needed and we return 0.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [0]
<strong>Output:</strong> 0
<strong>Explanation:</strong> nums is already in non-decreasing order, so no operations are needed and we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Can you solve it in <code>O(n*log(n))</code> time complexity?</p>

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
