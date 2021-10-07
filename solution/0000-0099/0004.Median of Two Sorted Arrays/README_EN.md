# [4. Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays)

[中文文档](/solution/0000-0099/0004.Median%20of%20Two%20Sorted%20Arrays/README.md)

## Description

<p>Given two sorted arrays <code>nums1</code> and <code>nums2</code> of size <code>m</code> and <code>n</code> respectively, return <strong>the median</strong> of the two sorted arrays.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,3], nums2 = [2]
<strong>Output:</strong> 2.00000
<strong>Explanation:</strong> merged array = [1,2,3] and median is 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2], nums2 = [3,4]
<strong>Output:</strong> 2.50000
<strong>Explanation:</strong> merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [0,0], nums2 = [0,0]
<strong>Output:</strong> 0.00000
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [], nums2 = [1]
<strong>Output:</strong> 1.00000
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [2], nums2 = []
<strong>Output:</strong> 2.00000
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>nums1.length == m</code></li>
	<li><code>nums2.length == n</code></li>
	<li><code>0 &lt;= m &lt;= 1000</code></li>
	<li><code>0 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= m + n &lt;= 2000</code></li>
	<li><code>-10<sup>6</sup> &lt;= nums1[i], nums2[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> The overall run time complexity should be <code>O(log (m+n))</code>.

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
       # concatenate the 2 lists and sort them
        nums1 += nums2
        nums1.sort()
        length = len(nums1)
        value = length/2
        if length % 2 == 0:
            value = int(value)
            return (nums1[value-1] + nums1[value])/2
        else:
            return nums1[int(value)]
```

### **Java**

```java

```

### **Nim**

```nim
proc medianOfTwoSortedArrays(nums1: seq[int], nums2: seq[int]): float =
  var
    fullList: seq[int] = concat(nums1, nums2)
    value: int = fullList.len div 2

  fullList.sort()

  if fullList.len mod 2 == 0:
    result = (fullList[value - 1] + fullList[value]) / 2
  else:
    result = fullList[value].toFloat()
```

### **...**

```

```

<!-- tabs:end -->
