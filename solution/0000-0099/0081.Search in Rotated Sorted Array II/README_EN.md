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

```

### **Java**

```java

```

### **C++**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```cpp
class Solution {
public:
    bool search(vector<int>& nums, int target) {
        if(nums.size() < 1) {
            return false;
        }
        if(nums.size() == 1) {
            return nums[0] == target;
        }
        int n = nums.size();
        int l = 0, r = n - 1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(nums[mid] == target) {
                return true;
            }
            if(nums[l] == nums[mid] && nums[mid] == nums[r]) {
                l++;
                r--;
            }else if(nums[l] <= nums[mid]) {
                //
                if(nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                }else {
                    l = mid + 1;
                }
            }else {
                //
                if(nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                }else {
                    r = mid - 1;
                }
            }
        }
        return false;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
