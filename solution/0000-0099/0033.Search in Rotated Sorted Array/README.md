# [33. 搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array)

[English Version](/solution/0000-0099/0033.Search%20in%20Rotated%20Sorted%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>假设按照升序排序的数组在预先未知的某个点上进行了旋转。</p>

<p>( 例如，数组&nbsp;<code>[0,1,2,4,5,6,7]</code>&nbsp;可能变为&nbsp;<code>[4,5,6,7,0,1,2]</code>&nbsp;)。</p>

<p>搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回&nbsp;<code>-1</code>&nbsp;。</p>

<p>你可以假设数组中不存在重复的元素。</p>

<p>你的算法时间复杂度必须是&nbsp;<em>O</em>(log&nbsp;<em>n</em>) 级别。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> nums = [<code>4,5,6,7,0,1,2]</code>, target = 0
<strong>输出:</strong> 4
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> nums = [<code>4,5,6,7,0,1,2]</code>, target = 3
<strong>输出:</strong> -1</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二分查找。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def search(self, nums: List[int], target: int) -> int:
        l, r = 0, len(nums) - 1
        while l <= r:
            mid = (l + r) >> 1
            if nums[mid] == target:
                return mid
            if nums[mid] > target:
                if nums[mid] >= nums[r] and target < nums[l]:
                    l = mid + 1
                else:
                    r = mid - 1
            else:
                if nums[mid] <= nums[l] and target > nums[r]:
                    r = mid - 1
                else:
                    l = mid + 1
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) {
                if (nums[mid] >= nums[r] && target < nums[l]) l = mid + 1;
                else r = mid - 1;
            } else {
                if (nums[mid] <= nums[l] && target > nums[r]) r = mid - 1;
                else l = mid + 1;
            }
        }
        return -1;
    }
}
```

### **C++**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```cpp
class Solution {
public:
    int search(vector<int>& nums, int target) {
        int l = 0, r = nums.size() - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) {
                if (nums[mid] >= nums[r] && target < nums[l]) l = mid + 1;
                else r = mid - 1;
            } else {
                if (nums[mid] <= nums[l] && target > nums[r]) r = mid - 1;
                else l = mid + 1;
            }
        }
        return -1;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
