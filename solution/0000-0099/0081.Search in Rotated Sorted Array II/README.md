# [81. 搜索旋转排序数组 II](https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii)

[English Version](/solution/0000-0099/0081.Search%20in%20Rotated%20Sorted%20Array%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>假设按照升序排序的数组在预先未知的某个点上进行了旋转。</p>

<p>( 例如，数组&nbsp;<code>[0,0,1,2,2,5,6]</code>&nbsp;可能变为&nbsp;<code>[2,5,6,0,0,1,2]</code>&nbsp;)。</p>

<p>编写一个函数来判断给定的目标值是否存在于数组中。若存在返回&nbsp;<code>true</code>，否则返回&nbsp;<code>false</code>。</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:</strong> nums = [2<code>,5,6,0,0,1,2]</code>, target = 0
<strong>输出:</strong> true
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> nums = [2<code>,5,6,0,0,1,2]</code>, target = 3
<strong>输出:</strong> false</pre>

<p><strong>进阶:</strong></p>

<ul>
	<li>这是 <a href="https://leetcode-cn.com/problems/search-in-rotated-sorted-array/description/">搜索旋转排序数组</a>&nbsp;的延伸题目，本题中的&nbsp;<code>nums</code>&nbsp; 可能包含重复元素。</li>
	<li>这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二分查找。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
