# [2934. 最大化数组末位元素的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-maximize-last-elements-in-arrays)

[English Version](/solution/2900-2999/2934.Minimum%20Operations%20to%20Maximize%20Last%20Elements%20in%20Arrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong> 开始的整数数组 <code>nums1</code> 和 <code>nums2</code> ，这两个数组的长度都是 <code>n</code> 。</p>

<p>你可以执行一系列<strong> 操作（可能不执行）</strong>。</p>

<p>在每次操作中，你可以选择一个在范围 <code>[0, n - 1]</code> 内的下标 <code>i</code> ，并交换 <code>nums1[i]</code> 和 <code>nums2[i]</code> 的值。</p>

<p>你的任务是找到满足以下条件所需的 <strong>最小</strong> 操作次数：</p>

<ul>
	<li><code>nums1[n - 1]</code> 等于 <code>nums1</code> 中所有元素的 <strong>最大值</strong> ，即 <code>nums1[n - 1] = max(nums1[0], nums1[1], ..., nums1[n - 1])</code> 。</li>
	<li><code>nums2[n - 1]</code> 等于 <code>nums2</code> 中所有元素的 <strong>最大值</strong> ，即 <code>nums2[n - 1] = max(nums2[0], nums2[1], ..., nums2[n - 1])</code> 。</li>
</ul>

<p>以整数形式，表示并返回满足上述 <strong>全部</strong> 条件所需的 <strong>最小</strong> 操作次数，如果无法同时满足两个条件，则返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,2,7]，nums2 = [4,5,3]
<strong>输出：</strong>1
<strong>解释：</strong>在这个示例中，可以选择下标 i = 2 执行一次操作。
交换 nums1[2] 和 nums2[2] 的值，nums1 变为 [1,2,3] ，nums2 变为 [4,5,7] 。
同时满足两个条件。
可以证明，需要执行的最小操作次数为 1 。
因此，答案是 1 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [2,3,4,5,9]，nums2 = [8,8,4,4,4]
<strong>输出：</strong>2
<strong>解释：</strong>在这个示例中，可以执行以下操作：
首先，选择下标 i = 4 执行操作。
交换 nums1[4] 和 nums2[4] 的值，nums1 变为 [2,3,4,5,4] ，nums2 变为 [8,8,4,4,9] 。
然后，选择下标 i = 3 执行操作。
交换 nums1[3] 和 nums2[3] 的值，nums1 变为 [2,3,4,4,4] ，nums2 变为 [8,8,4,5,9] 。
同时满足两个条件。 
可以证明，需要执行的最小操作次数为 2 。 
因此，答案是 2 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,5,4]，nums2 = [2,5,3]
<strong>输出：</strong>-1
<strong>解释：</strong>在这个示例中，无法同时满足两个条件。
因此，答案是 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums1.length == nums2.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums1[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= nums2[i] &lt;= 10<sup>9</sup></code></li>
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
