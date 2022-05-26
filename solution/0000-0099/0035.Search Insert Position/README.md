# [35. 搜索插入位置](https://leetcode-cn.com/problems/search-insert-position)

[English Version](/solution/0000-0099/0035.Search%20Insert%20Position/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。</p>

<p>你可以假设数组中无重复元素。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> [1,3,5,6], 5
<strong>输出:</strong> 2
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> [1,3,5,6], 2
<strong>输出:</strong> 1
</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入:</strong> [1,3,5,6], 7
<strong>输出:</strong> 4
</pre>

<p><strong>示例 4:</strong></p>

<pre><strong>输入:</strong> [1,3,5,6], 0
<strong>输出:</strong> 0
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二分查找。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        l, h = 0, len(nums) - 1
        while l <= h:
            m = l + ((h - l) >> 1)
            if nums[m] == target:
                return m
            if nums[m] < target:
                l = m + 1
            else:
                h = m - 1
        return l
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            int m = l + ((h - l) >> 1);
            if (nums[m] == target) return m;
            if (nums[m] < target) l = m + 1;
            else h = m - 1;
        }
        return l;
    }
}
```

### **Go**

```go
func searchInsert(nums []int, target int) int {
    l, h := 0, len(nums) - 1
    for l <= h {
        m := l + ((h - l) >> 1)
        if nums[m] == target {
            return m
        }
        if nums[m] < target {
            l = m + 1
        } else {
            h = m - 1
        }
    }
    return l
}
```

### **C++**

```cpp
class Solution {
public:
    int searchInsert(vector<int>& nums, int target) {
        int l = 0, h = nums.size() - 1;
        while (l <= h) {
            int m = l + ((h - l) >> 1);
            if (nums[m] == target) return m;
            if (nums[m] < target) l = m + 1;
            else h = m - 1;
        }
        return l;
    }
};
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var searchInsert = function (nums, target) {
  let l = 0,
    h = nums.length;
  while (l <= h) {
    const m = l + ((h - l) >> 1);
    if (nums[m] == target) return m;
    if (nums[m] < target) l = m + 1;
    else h = m - 1;
  }
  return l;
};
```

### **...**

```

```

<!-- tabs:end -->
