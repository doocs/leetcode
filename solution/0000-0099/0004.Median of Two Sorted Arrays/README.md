# [4. 寻找两个正序数组的中位数](https://leetcode.cn/problems/median-of-two-sorted-arrays)

[English Version](/solution/0000-0099/0004.Median%20of%20Two%20Sorted%20Arrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个大小分别为 <code>m</code> 和 <code>n</code> 的正序（从小到大）数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>。请你找出并返回这两个正序数组的 <strong>中位数</strong> 。</p>

<p>算法的时间复杂度应该为 <code>O(log (m+n))</code> 。</p>

<p>&nbsp;</p>

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

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums1.length == m</code></li>
	<li><code>nums2.length == n</code></li>
	<li><code>0 &lt;= m &lt;= 1000</code></li>
	<li><code>0 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= m + n &lt;= 2000</code></li>
	<li><code>-10<sup>6</sup> &lt;= nums1[i], nums2[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

本题限制了时间复杂度为 `O(log (m+n))`，看到这个时间复杂度，自然而然的想到了应该使用二分查找法来求解。那么回顾一下中位数的定义，如果某个有序数组长度是奇数，那么其中位数就是最中间那个，如果是偶数，那么就是最中间两个数字的平均值。这里对于两个有序数组也是一样的，假设两个有序数组的长度分别为 m 和 n，由于两个数组长度之和 m+n 的奇偶不确定，因此需要分情况来讨论，对于奇数的情况，直接找到最中间的数即可，偶数的话需要求最中间两个数的平均值。为了简化代码，不分情况讨论，我们使用一个小 trick，我们分别找第 `(m+n+1) / 2` 个，和 `(m+n+2) / 2` 个，然后求其平均值即可，这对奇偶数均适用。假如 m+n 为奇数的话，那么其实 `(m+n+1) / 2` 和 `(m+n+2) / 2` 的值相等，相当于两个相同的数字相加再除以 2，还是其本身。

这里我们需要定义一个函数来在两个有序数组中找到第 K 个元素，下面重点来看如何实现找到第 K 个元素。

首先，为了避免产生新的数组从而增加时间复杂度，我们使用两个变量 i 和 j 分别来标记数组 nums1 和 nums2 的起始位置。然后来处理一些边界问题，比如当某一个数组的起始位置大于等于其数组长度时，说明其所有数字均已经被淘汰了，相当于一个空数组了，那么实际上就变成了在另一个数组中找数字，直接就可以找出来了。还有就是如果 K=1 的话，那么我们只要比较 nums1 和 nums2 的起始位置 i 和 j 上的数字就可以了。

难点就在于一般的情况怎么处理？因为我们需要在两个有序数组中找到第 K 个元素，为了加快搜索的速度，我们要使用二分法，对 K 二分，意思是我们需要分别在 nums1 和 nums2 中查找第 K/2 个元素，注意这里由于两个数组的长度不定，所以有可能某个数组没有第 K/2 个数字，所以我们需要先检查一下，数组中到底存不存在第 K/2 个数字，如果存在就取出来，否则就赋值上一个整型最大值。如果某个数组没有第 K/2 个数字，那么我们就淘汰另一个数字的前 K/2 个数字即可。有没有可能两个数组都不存在第 K/2 个数字呢，这道题里是不可能的，因为我们的 K 不是任意给的，而是给的 m+n 的中间值，所以必定至少会有一个数组是存在第 K/2 个数字的。

最后是二分法的核心，比较这两个数组的第 K/2 小的数字 midVal1 和 midVal2 的大小，如果第一个数组的第 K/2 个数字小的话，那么说明我们要找的数字肯定不在 nums1 中的前 K/2 个数字，所以我们可以将其淘汰，将 nums1 的起始位置向后移动 K/2 个，并且此时的 K 也自减去 K/2，调用递归。反之，我们淘汰 nums2 中的前 K/2 个数字，并将 nums2 的起始位置向后移动 K/2 个，并且此时的 K 也自减去 K/2，调用递归即可。

> 实际是比较两个数组中的第 K/2 个数字哪一个可能到达最后合并后排序数组中的第 K 个元素的位置，其中小的那个数字注定不可能到达，所以可以直接将小的元素对应的数组的前 K/2 个数字淘汰。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        def findKth(i, j, k):
            if i >= m:
                return nums2[j + k - 1]
            if j >= n:
                return nums1[i + k - 1]
            if k == 1:
                return min(nums1[i], nums2[j])
            midVal1 = nums1[i + k // 2 - 1] if i + k // 2 - 1 < m else inf
            midVal2 = nums2[j + k // 2 - 1] if j + k // 2 - 1 < n else inf
            if midVal1 < midVal2:
                return findKth(i + k // 2, j, k - k // 2)
            return findKth(i, j + k // 2, k - k // 2)

        m, n = len(nums1), len(nums2)
        left, right = (m + n + 1) // 2, (m + n + 2) // 2
        return (findKth(0, 0, left) + findKth(0, 0, right)) / 2
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    private int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        if (i >= nums1.length) {
            return nums2[j + k - 1];
        }
        if (j >= nums2.length) {
            return nums1[i + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[i], nums2[j]);
        }
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if (midVal1 < midVal2) {
            return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
        }
        return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size();
        int n = nums2.size();
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    int findKth(vector<int>& nums1, int i, vector<int>& nums2, int j, int k) {
        if (i >= nums1.size()) return nums2[j + k - 1];
        if (j >= nums2.size()) return nums1[i + k - 1];
        if (k == 1) return min(nums1[i], nums2[j]);
        int midVal1 = i + k / 2 - 1 < nums1.size() ? nums1[i + k / 2 - 1] : INT_MAX;
        int midVal2 = j + k / 2 - 1 < nums2.size() ? nums2[j + k / 2 - 1] : INT_MAX;
        if (midVal1 < midVal2) return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
        return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
    }
};
```

### **Go**

```go
func findMedianSortedArrays(nums1 []int, nums2 []int) float64 {
	m, n := len(nums1), len(nums2)
	left, right := (m+n+1)/2, (m+n+2)/2
	var findKth func(i, j, k int) int
	findKth = func(i, j, k int) int {
		if i >= m {
			return nums2[j+k-1]
		}
		if j >= n {
			return nums1[i+k-1]
		}
		if k == 1 {
			return min(nums1[i], nums2[j])
		}
		midVal1 := math.MaxInt32
		midVal2 := math.MaxInt32
		if i+k/2-1 < m {
			midVal1 = nums1[i+k/2-1]
		}
		if j+k/2-1 < n {
			midVal2 = nums2[j+k/2-1]
		}
		if midVal1 < midVal2 {
			return findKth(i+k/2, j, k-k/2)
		}
		return findKth(i, j+k/2, k-k/2)
	}
	return (float64(findKth(0, 0, left)) + float64(findKth(0, 0, right))) / 2.0
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
