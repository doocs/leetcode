# [4. 寻找两个正序数组的中位数](https://leetcode-cn.com/problems/median-of-two-sorted-arrays)

[English Version](/solution/0000-0099/0004.Median%20of%20Two%20Sorted%20Arrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个大小分别为 <code>m</code> 和 <code>n</code> 的正序（从小到大）数组 <code>nums1</code> 和 <code>nums2</code>。请你找出并返回这两个正序数组的 <strong>中位数</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,3], nums2 = [2]
<strong>输出：</strong>2.00000
<strong>解释：</strong>合并数组 = [1,2,3] ，中位数 2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,2], nums2 = [3,4]
<strong>输出：</strong>2.50000
<strong>解释：</strong>合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [0,0], nums2 = [0,0]
<strong>输出：</strong>0.00000
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [], nums2 = [1]
<strong>输出：</strong>1.00000
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [2], nums2 = []
<strong>输出：</strong>2.00000
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums1.length == m</code></li>
	<li><code>nums2.length == n</code></li>
	<li><code>0 <= m <= 1000</code></li>
	<li><code>0 <= n <= 1000</code></li>
	<li><code>1 <= m + n <= 2000</code></li>
	<li><code>-10<sup>6</sup> <= nums1[i], nums2[i] <= 10<sup>6</sup></code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong>你能设计一个时间复杂度为 <code>O(log (m+n))</code> 的算法解决此问题吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
