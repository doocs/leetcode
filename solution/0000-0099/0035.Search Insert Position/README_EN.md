# [35. Search Insert Position](https://leetcode.com/problems/search-insert-position)

[中文文档](/solution/0000-0099/0035.Search%20Insert%20Position/README.md)

## Description

<p>Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.</p>

<p>You may assume no duplicates in the array.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> [1,3,5,6], 5

<strong>Output:</strong> 2

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> [1,3,5,6], 2

<strong>Output:</strong> 1

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> [1,3,5,6], 7

<strong>Output:</strong> 4

</pre>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input:</strong> [1,3,5,6], 0

<strong>Output:</strong> 0

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

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
