# [1855. 下标对中的最大距离](https://leetcode-cn.com/problems/maximum-distance-between-a-pair-of-values)

[English Version](/solution/1800-1899/1855.Maximum%20Distance%20Between%20a%20Pair%20of%20Values/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个 <strong>非递增</strong> 的整数数组 <code>nums1</code>​​​​​​ 和 <code>nums2</code>​​​​​​ ，数组下标均 <strong>从 0 开始</strong> 计数。</p>

<p>下标对 <code>(i, j)</code> 中 <code>0 <= i < nums1.length</code> 且 <code>0 <= j < nums2.length</code> 。如果该下标对同时满足 <code>i <= j</code> 且 <code>nums1[i] <= nums2[j]</code> ，则称之为 <strong>有效</strong> 下标对，该下标对的 <strong>距离</strong> 为 <code>j - i</code>​​ 。​​</p>

<p>返回所有 <strong>有效</strong> 下标对<em> </em><code>(i, j)</code><em> </em>中的 <strong>最大距离</strong> 。如果不存在有效下标对，返回 <code>0</code> 。</p>

<p>一个数组 <code>arr</code> ，如果每个 <code>1 <= i < arr.length</code> 均有 <code>arr[i-1] >= arr[i]</code> 成立，那么该数组是一个 <strong>非递增</strong> 数组。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [55,30,5,4,2], nums2 = [100,20,10,10,5]
<strong>输出：</strong>2
<strong>解释：</strong>有效下标对是 (0,0), (2,2), (2,3), (2,4), (3,3), (3,4) 和 (4,4) 。
最大距离是 2 ，对应下标对 (2,4) 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [2,2,2], nums2 = [10,10,1]
<strong>输出：</strong>1
<strong>解释：</strong>有效下标对是 (0,0), (0,1) 和 (1,1) 。
最大距离是 1 ，对应下标对 (0,1) 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [30,29,19,5], nums2 = [25,25,25,25,25]
<strong>输出：</strong>2
<strong>解释：</strong>有效下标对是 (2,2), (2,3), (2,4), (3,3) 和 (3,4) 。
最大距离是 2 ，对应下标对 (2,4) 。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [5,4], nums2 = [3,2]
<strong>输出：</strong>0
<strong>解释：</strong>不存在有效下标对，所以返回 0 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums1.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums2.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums1[i], nums2[j] <= 10<sup>5</sup></code></li>
	<li><code>nums1</code> 和 <code>nums2</code> 都是 <strong>非递增</strong> 数组</li>
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

### **...**

```

```

<!-- tabs:end -->
