# [2499. 让数组不相等的最小总代价](https://leetcode.cn/problems/minimum-total-cost-to-make-arrays-unequal)

[English Version](/solution/2400-2499/2499.Minimum%20Total%20Cost%20to%20Make%20Arrays%20Unequal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums1</code>&nbsp;和&nbsp;<code>nums2</code>&nbsp;，两者长度都为&nbsp;<code>n</code>&nbsp;。</p>

<p>每次操作中，你可以选择交换 <code>nums1</code>&nbsp;中任意两个下标处的值。操作的 <strong>开销</strong>&nbsp;为两个下标的 <strong>和</strong>&nbsp;。</p>

<p>你的目标是对于所有的 <code>0 &lt;= i &lt;= n - 1</code>&nbsp;，都满足&nbsp;<code>nums1[i] != nums2[i]</code>&nbsp;，你可以进行 <strong>任意次</strong>&nbsp;操作，请你返回达到这个目标的 <strong>最小</strong>&nbsp;总代价。</p>

<p>请你返回让<em>&nbsp;</em><code>nums1</code> 和&nbsp;<code>nums2</code><em>&nbsp;</em>满足上述条件的 <strong>最小总代价</strong> ，如果无法达成目标，返回&nbsp;<code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums1 = [1,2,3,4,5], nums2 = [1,2,3,4,5]
<b>输出：</b>10
<b>解释：</b>
实现目标的其中一种方法为：
- 交换下标为 0 和 3 的两个值，代价为 0 + 3 = 3 。现在 nums1 = [4,2,3,1,5] 。
- 交换下标为 1 和 2 的两个值，代价为 1 + 2 = 3 。现在 nums1 = [4,3,2,1,5] 。
- 交换下标为 0 和 4 的两个值，代价为 0 + 4 = 4 。现在 nums1 = [5,3,2,1,4] 。
最后，对于每个下标 i ，都有 nums1[i] != nums2[i] 。总代价为 10 。
还有别的交换值的方法，但是无法得到代价和小于 10 的方案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums1 = [2,2,2,1,3], nums2 = [1,2,2,3,3]
<b>输出：</b>10
<b>解释：</b>
实现目标的一种方法为：
- 交换下标为 2 和 3 的两个值，代价为 2 + 3 = 5 。现在 nums1 = [2,2,1,2,3] 。
- 交换下标为 1 和 4 的两个值，代价为 1 + 4 = 5 。现在 nums1 = [2,3,1,2,2] 。
总代价为 10 ，是所有方案中的最小代价。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums1 = [1,2,2], nums2 = [1,2,2]
<b>输出：</b>-1
<b>解释：</b>
不管怎么操作，都无法满足题目要求。
所以返回 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= n</code></li>
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
