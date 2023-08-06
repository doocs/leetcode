# [2808. 使循环数组所有元素相等的最少秒数](https://leetcode.cn/problems/minimum-seconds-to-equalize-a-circular-array)

[English Version](/solution/2800-2899/2808.Minimum%20Seconds%20to%20Equalize%20a%20Circular%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>n</code>&nbsp;的数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>每一秒，你可以对数组执行以下操作：</p>

<ul>
	<li>对于范围在&nbsp;<code>[0, n - 1]</code>&nbsp;内的每一个下标&nbsp;<code>i</code>&nbsp;，将&nbsp;<code>nums[i]</code> 替换成&nbsp;<code>nums[i]</code>&nbsp;，<code>nums[(i - 1 + n) % n]</code>&nbsp;或者&nbsp;<code>nums[(i + 1) % n]</code>&nbsp;三者之一。</li>
</ul>

<p><strong>注意</strong>，所有元素会被同时替换。</p>

<p>请你返回将数组 <code>nums</code>&nbsp;中所有元素变成相等元素所需要的 <strong>最少</strong>&nbsp;秒数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,2,1,2]
<b>输出：</b>1
<b>解释：</b>我们可以在 1 秒内将数组变成相等元素：
- 第 1 秒，将每个位置的元素分别变为 [nums[3],nums[1],nums[3],nums[3]] 。变化后，nums = [2,2,2,2] 。
1 秒是将数组变成相等元素所需要的最少秒数。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [2,1,3,3,2]
<b>输出：</b>2
<b>解释：</b>我们可以在 2 秒内将数组变成相等元素：
- 第 1 秒，将每个位置的元素分别变为 [nums[0],nums[2],nums[2],nums[2],nums[3]] 。变化后，nums = [2,3,3,3,3] 。
- 第 2 秒，将每个位置的元素分别变为 [nums[1],nums[1],nums[2],nums[3],nums[4]] 。变化后，nums = [3,3,3,3,3] 。
2 秒是将数组变成相等元素所需要的最少秒数。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [5,5,5,5]
<b>输出：</b>0
<b>解释：</b>不需要执行任何操作，因为一开始数组中的元素已经全部相等。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
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
