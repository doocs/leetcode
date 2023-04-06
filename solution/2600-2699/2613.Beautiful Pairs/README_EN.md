# [2613. Beautiful Pairs](https://leetcode.com/problems/beautiful-pairs)

[中文文档](/solution/2600-2699/2613.Beautiful%20Pairs/README.md)

## Description

<p>You are given two <strong>0-indexed</strong> integer arrays <code>nums1</code> and <code>nums2</code> of the same length. A pair of indices <code>(i,j)</code> is called <strong>beautiful</strong> if<code>|nums1[i] - nums1[j]| + |nums2[i] - nums2[j]|</code> is the smallest amongst all possible indices pairs where <code>i &lt; j</code>.</p>

<p>Return <em>the beautiful pair. In the case that there are multiple beautiful pairs, return the lexicographically smallest pair.</em></p>

<p>Note that</p>

<ul>
	<li><code>|x|</code> denotes the absolute value of <code>x</code>.</li>
	<li>A pair of indices <code>(i<sub>1</sub>, j<sub>1</sub>)</code> is lexicographically smaller than <code>(i<sub>2</sub>, j<sub>2</sub>)</code> if <code>i<sub>1</sub> &lt; i<sub>2</sub></code> or <code>i<sub>1</sub> == i<sub>2</sub></code> and <code>j<sub>1</sub> &lt; j<sub>2</sub></code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,3,2,4], nums2 = [2,3,1,2,3]
<strong>Output:</strong> [0,3]
<strong>Explanation:</strong> Consider index 0 and index 3. The value of |nums1[i]-nums1[j]| + |nums2[i]-nums2[j]| is 1, which is the smallest value we can achieve.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,4,3,2,5], nums2 = [1,4,2,3,5,1]
<strong>Output:</strong> [1,4]
<strong>Explanation:</strong> Consider index 1 and index 4. The value of |nums1[i]-nums1[j]| + |nums2[i]-nums2[j]| is 1, which is the smallest value we can achieve.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums1.length, nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums1.length == nums2.length</code></li>
	<li><code>0 &lt;= nums1<sub>i</sub><sub>&nbsp;</sub>&lt;= nums1.length</code></li>
	<li><code>0 &lt;= nums2<sub>i</sub>&nbsp;&lt;= nums2.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

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
