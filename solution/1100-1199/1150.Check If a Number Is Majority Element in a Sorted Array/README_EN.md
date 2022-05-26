# [1150. Check If a Number Is Majority Element in a Sorted Array](https://leetcode.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array)

[中文文档](/solution/1100-1199/1150.Check%20If%20a%20Number%20Is%20Majority%20Element%20in%20a%20Sorted%20Array/README.md)

## Description

<p>Given an integer array <code>nums</code> sorted in non-decreasing order and an integer <code>target</code>, return <code>true</code> <em>if</em> <code>target</code> <em>is a <strong>majority</strong> element, or </em><code>false</code><em> otherwise</em>.</p>

<p>A <strong>majority</strong> element in an array <code>nums</code> is an element that appears more than <code>nums.length / 2</code> times in the array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,4,5,5,5,5,5,6,6], target = 5
<strong>Output:</strong> true
<strong>Explanation:</strong> The value 5 appears 5 times and the length of the array is 9.
Thus, 5 is a majority element because 5 &gt; 9/2 is true.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,100,101,101], target = 101
<strong>Output:</strong> false
<strong>Explanation:</strong> The value 101 appears 2 times and the length of the array is 4.
Thus, 101 is not a majority element because 2 &gt; 4/2 is false.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i], target &lt;= 10<sup>9</sup></code></li>
	<li><code>nums</code> is sorted in non-decreasing order.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isMajorityElement(self, nums: List[int], target: int) -> bool:
        def bsearch_left(nums, target, left, right):
            while left < right:
                mid = (left + right) >> 1
                if nums[mid] >= target:
                    right = mid
                else:
                    left = mid + 1
            return left if nums[left] == target else -1

        def bsearch_right(nums, target, left, right):
            while left < right:
                mid = (left + right + 1) >> 1
                if nums[mid] <= target:
                    left = mid
                else:
                    right = mid - 1
            return left if nums[left] == target else -1

        n = len(nums)
        left = bsearch_left(nums, target, 0, n - 1)
        if left == -1:
            return False
        right = bsearch_right(nums, target, left, n - 1)
        if right == -1:
            return False
        return right - left + 1 > n >> 1
```

```python
class Solution:
    def isMajorityElement(self, nums: List[int], target: int) -> bool:
        left, right = bisect_left(nums, target), bisect_right(nums, target)
        return right - left > (len(nums) >> 1)
```

### **Java**

```java
class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        int n = nums.length;
        int left = bsearchLeft(nums, target, 0, n - 1);
        if (left == -1) {
            return false;
        }
        int right = bsearchRight(nums, target, left, n - 1);
        if (right == -1) {
            return false;
        }
        return right - left + 1 > (n >> 1);
    }

    private int bsearchLeft(int[] nums, int target, int left, int right) {
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left] == target ? left : -1;
    }

    private int bsearchRight(int[] nums, int target, int left, int right) {
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return nums[left] == target ? left : -1;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
