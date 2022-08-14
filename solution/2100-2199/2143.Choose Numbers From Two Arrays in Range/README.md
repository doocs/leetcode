# [2143. 在两个数组的区间中选取数字](https://leetcode.cn/problems/choose-numbers-from-two-arrays-in-range)

[English Version](/solution/2100-2199/2143.Choose%20Numbers%20From%20Two%20Arrays%20in%20Range/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个 <strong>下标从 0 开始</strong>，长度为 <code>n</code> 的整数数组 <code>nums1</code> 和 <code>nums2</code>。</p>

<p>如果一个区间 <code>[l, r]</code> （<strong>包含左右端点</strong>，<code>0 &lt;= l &lt;= r &lt; n</code>）满足下列条件，那么这个区间就是 <strong>平衡</strong> 的：</p>

<ul>
	<li>对每个在区间 <code>[l, r]</code> 范围内的 <code>i</code>，你需要选取&nbsp;<code>nums1[i]</code> 或者&nbsp;<code>nums2[i]</code>；</li>
	<li>从 <code>nums1</code> 中选取的数字和与从 <code>nums2</code> 中选取的数字和相等。（如果你没有从某个数组中选取任何数字，那么数字和被视为 <code>0</code>）。</li>
</ul>

<p>如果两个 <strong>平衡</strong> 的区间 <code>[l<sub>1</sub>, r<sub>1</sub>]</code> 和 <code>[l<sub>2</sub>, r<sub>2</sub>]</code> 满足下列条件之一，那么它们就是 <strong>不同</strong> 的：</p>

<ul>
	<li><code>l<sub>1</sub> != l<sub>2</sub></code></li>
	<li><code>r<sub>1</sub> != r<sub>2</sub></code></li>
	<li>两个区间中的数字选取情况不同（也就是说，存在至少一个 <code>i</code>，使得在第一个区间中，<code>nums1[i]</code> 被选中, 而在第二个区间中，<code>nums2[i]</code> 被选中，或者相反的情况）。</li>
</ul>

<p>请返回 <strong>不同</strong> 的平衡的区间数目。由于答案可能很大，请返回答案 <strong>模 </strong><code>10<sup>9</sup>+7</code> 的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> nums1 = [1,2,5], nums2 = [2,6,3]
<strong>输出:</strong> 3
<strong>解释:</strong> 平衡的区间有:
- [0, 1], 我们选取 nums2[0] 和 nums2[1]。
  从 <code>nums1</code> 中选取的数字和与从 <code>nums2</code> 中选取的数字和相等: 2 = 2.
- [0, 2], 我们选取 nums1[0], nums2[1] 和 nums1[2]。
  从 <code>nums1</code> 中选取的数字和与从 <code>nums2</code> 中选取的数字和相等: 1 + 5 = 6。
- [0, 2], 我们选取 nums1[0], nums1[1] 和 nums2[2]。
  从 <code>nums1</code> 中选取的数字和与从 <code>nums2</code> 中选取的数字和相等: 1 + 2 = 3。
注意第二个区间和第三个区间时不同的。
因为在第二个平衡的区间中，我们选取了 nums2[1]，但是在第三个平衡的区间中，我们选取了 nums1[1]。
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>输入:</strong> nums1 = [0,1], nums2 = [1,0]
<strong>输出:</strong> 4
<strong>解释:</strong> 平衡的区间有:
- [0, 0], 我们选取 nums1[0]。
  从 <code>nums1</code> 中选取的数字和与从 <code>nums2</code> 中选取的数字和相等: 0 = 0。
- [1, 1], 我们选取 nums2[1]。
  从 <code>nums1</code> 中选取的数字和与从 <code>nums2</code> 中选取的数字和相等: 0 = 0。
- [0, 1], 我们选取 nums1[0] 和 nums2[1]。
  从 <code>nums1</code> 中选取的数字和与从 <code>nums2</code> 中选取的数字和相等: 0 = 0。
- [0, 1], 我们选取 nums2[0] 和 nums1[1]。
  从 <code>nums1</code> 中选取的数字和与从 <code>nums2</code> 中选取的数字和相等: 1 = 1。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 100</code></li>
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

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
