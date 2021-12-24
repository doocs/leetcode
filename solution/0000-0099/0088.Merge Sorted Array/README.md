# [88. 合并两个有序数组](https://leetcode-cn.com/problems/merge-sorted-array)

[English Version](/solution/0000-0099/0088.Merge%20Sorted%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个有序整数数组 <code>nums1</code><em> </em>和 <code>nums2</code>，请你将 <code>nums2</code><em> </em>合并到 <code>nums1</code><em> </em>中<em>，</em>使 <code>nums1</code><em> </em>成为一个有序数组。</p>

<p>初始化 <code>nums1</code> 和 <code>nums2</code> 的元素数量分别为 <code>m</code> 和 <code>n</code><em> </em>。你可以假设 <code>nums1</code><em> </em>的空间大小等于 <code>m + n</code>，这样它就有足够的空间保存来自 <code>nums2</code> 的元素。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
<strong>输出：</strong>[1,2,2,3,5,6]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1], m = 1, nums2 = [], n = 0
<strong>输出：</strong>[1]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums1.length == m + n</code></li>
	<li><code>nums2.length == n</code></li>
	<li><code>0 <= m, n <= 200</code></li>
	<li><code>1 <= m + n <= 200</code></li>
	<li><code>-10<sup>9</sup> <= nums1[i], nums2[i] <= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

双指针解决。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        """
        Do not return anything, modify nums1 in-place instead.
        """
        i, j, k = m - 1, n - 1, m + n - 1
        while j >= 0:
            if i >= 0 and nums1[i] > nums2[j]:
                nums1[k] = nums1[i]
                i -= 1
            else:
                nums1[k] = nums2[j]
                j -= 1
            k -= 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
    }
}
```

### **Go**

```go
func merge(nums1 []int, m int, nums2 []int, n int)  {
    i, j, k := m - 1, n - 1, m + n - 1
    for j >= 0 {
        if i >= 0 && nums1[i] > nums2[j] {
            nums1[k] = nums1[i]
            i--
        } else {
            nums1[k] = nums2[j]
            j--
        }
        k--
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums1
 * @param {number} m
 * @param {number[]} nums2
 * @param {number} n
 * @return {void} Do not return anything, modify nums1 in-place instead.
 */
var merge = function (nums1, m, nums2, n) {
    let i = m - 1,
        j = n - 1,
        k = m + n - 1;
    while (j >= 0) {
        if (i >= 0 && nums1[i] > nums2[j]) {
            nums1[k--] = nums1[i--];
        } else {
            nums1[k--] = nums2[j--];
        }
    }
};
```

### **...**

```

```

<!-- tabs:end -->
