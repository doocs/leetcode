# [2143. Choose Numbers From Two Arrays in Range](https://leetcode.com/problems/choose-numbers-from-two-arrays-in-range)

[中文文档](/solution/2100-2199/2143.Choose%20Numbers%20From%20Two%20Arrays%20in%20Range/README.md)

## Description

<p>You are given two <strong>0-indexed</strong> integer arrays <code>nums1</code> and <code>nums2</code> of length <code>n</code>.</p>

<p>A range <code>[l, r]</code> (<strong>inclusive</strong>) where <code>0 &lt;= l &lt;= r &lt; n</code> is <strong>balanced</strong> if:</p>

<ul>
	<li>For every <code>i</code> in the range <code>[l, r]</code>, you pick either <code>nums1[i]</code> or <code>nums2[i]</code>.</li>
	<li>The sum of the numbers you pick from <code>nums1</code> equals to the sum of the numbers you pick from <code>nums2</code> (the sum is considered to be <code>0</code> if you pick no numbers from an array).</li>
</ul>

<p>Two <strong>balanced</strong> ranges from <code>[l<sub>1</sub>, r<sub>1</sub>]</code> and <code>[l<sub>2</sub>, r<sub>2</sub>]</code> are considered to be <strong>different</strong> if at least one of the following is true:</p>

<ul>
	<li><code>l<sub>1</sub> != l<sub>2</sub></code></li>
	<li><code>r<sub>1</sub> != r<sub>2</sub></code></li>
	<li><code>nums1[i]</code> is picked in the first range, and <code>nums2[i]</code> is picked in the second range or <strong>vice versa</strong> for at least one <code>i</code>.</li>
</ul>

<p>Return <em>the number of <strong>different</strong> ranges that are balanced. </em>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,5], nums2 = [2,6,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The balanced ranges are:
- [0, 1] where we choose nums2[0], and nums1[1].
  The sum of the numbers chosen from nums1 equals the sum of the numbers chosen from nums2: 2 = 2.
- [0, 2] where we choose nums1[0], nums2[1], and nums1[2].
  The sum of the numbers chosen from nums1 equals the sum of the numbers chosen from nums2: 1 + 5 = 6.
- [0, 2] where we choose nums1[0], nums1[1], and nums2[2].
  The sum of the numbers chosen from nums1 equals the sum of the numbers chosen from nums2: 1 + 2 = 3.
Note that the second and third balanced ranges are different.
In the second balanced range, we choose nums2[1] and in the third balanced range, we choose nums1[1].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [0,1], nums2 = [1,0]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The balanced ranges are:
- [0, 0] where we choose nums1[0].
  The sum of the numbers chosen from nums1 equals the sum of the numbers chosen from nums2: 0 = 0.
- [1, 1] where we choose nums2[1].
  The sum of the numbers chosen from nums1 equals the sum of the numbers chosen from nums2: 0 = 0.
- [0, 1] where we choose nums1[0] and nums2[1].
  The sum of the numbers chosen from nums1 equals the sum of the numbers chosen from nums2: 0 = 0.
- [0, 1] where we choose nums2[0] and nums1[1].
  The sum of the numbers chosen from nums1 equals the sum of the numbers chosen from nums2: 1 = 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
