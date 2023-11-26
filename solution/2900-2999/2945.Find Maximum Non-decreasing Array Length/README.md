# [2945. 找到最大非递减数组的长度](https://leetcode.cn/problems/find-maximum-non-decreasing-array-length)

[English Version](/solution/2900-2999/2945.Find%20Maximum%20Non-decreasing%20Array%20Length/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>你可以执行任意次操作。每次操作中，你需要选择一个 <strong>子数组</strong>&nbsp;，并将这个子数组用它所包含元素的 <strong>和</strong>&nbsp;替换。比方说，给定数组是&nbsp;<code>[1,3,5,6]</code>&nbsp;，你可以选择子数组&nbsp;<code>[3,5]</code>&nbsp;，用子数组的和 <code>8</code>&nbsp;替换掉子数组，然后数组会变为&nbsp;<code>[1,8,6]</code>&nbsp;。</p>

<p>请你返回执行任意次操作以后，可以得到的 <strong>最长非递减</strong>&nbsp;数组的长度。</p>

<p><strong>子数组</strong>&nbsp;指的是一个数组中一段连续 <strong>非空</strong>&nbsp;的元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [5,2,2]
<b>输出：</b>1
<strong>解释：</strong>这个长度为 3 的数组不是非递减的。
我们有 2 种方案使数组长度为 2 。
第一种，选择子数组 [2,2] ，对数组执行操作后得到 [5,4] 。
第二种，选择子数组 [5,2] ，对数组执行操作后得到 [7,2] 。
这两种方案中，数组最后都不是 <strong>非递减</strong>&nbsp;的，所以不是可行的答案。
如果我们选择子数组 [5,2,2] ，并将它替换为 [9] ，数组变成非递减的。
所以答案为 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,4]
<b>输出：</b>4
<b>解释：</b>数组已经是非递减的。所以答案为 4 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [4,3,2,6]
<b>输出：</b>3
<b>解释：</b>将 [3,2] 替换为 [5] ，得到数组 [4,5,6] ，它是非递减的。
最大可能的答案为 3 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
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

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
