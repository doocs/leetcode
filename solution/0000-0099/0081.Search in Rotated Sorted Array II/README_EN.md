# [81. Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii)

[中文文档](/solution/0000-0099/0081.Search%20in%20Rotated%20Sorted%20Array%20II/README.md)

## Description

<p>There is an integer array <code>nums</code> sorted in non-decreasing order (not necessarily with <strong>distinct</strong> values).</p>

<p>Before being passed to your function, <code>nums</code> is <strong>rotated</strong> at an unknown pivot index <code>k</code> (<code>0 &lt;= k &lt; nums.length</code>) such that the resulting array is <code>[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]</code> (<strong>0-indexed</strong>). For example, <code>[0,1,2,4,4,4,5,6,6,7]</code> might be rotated at pivot index <code>5</code> and become <code>[4,5,6,6,7,0,1,2,4,4]</code>.</p>

<p>Given the array <code>nums</code> <strong>after</strong> the rotation and an integer <code>target</code>, return <code>true</code><em> if </em><code>target</code><em> is in </em><code>nums</code><em>, or </em><code>false</code><em> if it is not in </em><code>nums</code><em>.</em></p>

<p>You must decrease the overall operation steps as much as possible.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [2,5,6,0,0,1,2], target = 0
<strong>Output:</strong> true
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [2,5,6,0,0,1,2], target = 3
<strong>Output:</strong> false
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code> is guaranteed to be rotated at some pivot.</li>
	<li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> This problem is similar to&nbsp;<a href="/problems/search-in-rotated-sorted-array/description/" target="_blank">Search in Rotated Sorted Array</a>, but&nbsp;<code>nums</code> may contain <strong>duplicates</strong>. Would this affect the runtime complexity? How and why?</p>

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
                if (target > nums[mid] && target <= nums[r])
                    l = mid + 1;
                else
                    r = mid - 1;
            } else if (nums[mid] > nums[l] || nums[mid] > nums[r]) {
                if (target < nums[mid] && target >= nums[l])
                    r = mid - 1;
                else
                    l = mid + 1;
            } else
                r--;
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
                if (target > nums[mid] && target <= nums[r])
                    l = mid + 1;
                else
                    r = mid - 1;
            } else if (nums[mid] > nums[l] || nums[mid] > nums[r]) {
                if (target < nums[mid] && target >= nums[l])
                    r = mid - 1;
                else
                    l = mid + 1;
            } else
                r--;
        }
        return false;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
