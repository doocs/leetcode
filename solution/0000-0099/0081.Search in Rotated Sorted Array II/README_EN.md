# [81. Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii)

[中文文档](/solution/0000-0099/0081.Search%20in%20Rotated%20Sorted%20Array%20II/README.md)

## Description

<p>Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.</p>

<p>(i.e., <code>[0,0,1,2,2,5,6]</code> might become <code>[2,5,6,0,0,1,2]</code>).</p>

<p>You are given a target value to search. If found in the array return <code>true</code>, otherwise return <code>false</code>.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> nums = [2<code>,5,6,0,0,1,2]</code>, target = 0

<strong>Output:</strong> true

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> nums = [2<code>,5,6,0,0,1,2]</code>, target = 3

<strong>Output:</strong> false</pre>

<p><strong>Follow up:</strong></p>

<ul>
    <li>This is a follow up problem to&nbsp;<a href="/problems/search-in-rotated-sorted-array/description/">Search in Rotated Sorted Array</a>, where <code>nums</code> may contain duplicates.</li>
    <li>Would this affect the run-time complexity? How and why?</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def search(self, nums: List[int], target: int) -> bool:
        l, r = 0, len(nums) - 1
        while l <= r:
            mid = (l + r) >> 1
            if nums[mid] == target:
                return True
            if nums[mid] < nums[r] or nums[mid] < nums[l]:
                if target > nums[mid] and target <= nums[r]:
                    l = mid + 1
                else:
                    r = mid - 1
            elif nums[mid] > nums[l] or nums[mid] > nums[r]:
                if target < nums[mid] and target >= nums[l]:
                    r = mid - 1
                else:
                    l = mid + 1
            else:
                r -= 1
        return False
```

### **Java**

```java
class Solution {
    public boolean search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] == target) return true;
            if (nums[mid] < nums[r] || nums[mid] < nums[l]) {
                if (target > nums[mid] && target <= nums[r]) l = mid + 1;
                else r = mid - 1;
            } else if (nums[mid] > nums[l] || nums[mid] > nums[r]) {
                if (target < nums[mid] && target >= nums[l]) r = mid - 1;
                else l = mid + 1;
            } else r--;
        }
        return false;
    }
}
```

### **C++**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```cpp
class Solution {
public:
    bool search(vector<int>& nums, int target) {
        int l = 0, r = nums.size() - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (nums[mid] == target) return true;
            if (nums[mid] < nums[r] || nums[mid] < nums[l]) {
                if (target > nums[mid] && target <= nums[r]) l = mid + 1;
                else r = mid - 1;
            } else if (nums[mid] > nums[l] || nums[mid] > nums[r]) {
                if (target < nums[mid] && target >= nums[l]) r = mid - 1;
                else l = mid + 1;
            } else r--;
        }
        return false;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
