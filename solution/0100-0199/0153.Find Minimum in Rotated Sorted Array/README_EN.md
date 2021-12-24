# [153. Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array)

[中文文档](/solution/0100-0199/0153.Find%20Minimum%20in%20Rotated%20Sorted%20Array/README.md)

## Description

<p>Suppose an array of length <code>n</code> sorted in ascending order is <strong>rotated</strong> between <code>1</code> and <code>n</code> times. For example, the array <code>nums = [0,1,2,4,5,6,7]</code> might become:</p>

<ul>
	<li><code>[4,5,6,7,0,1,2]</code> if it was rotated <code>4</code> times.</li>
	<li><code>[0,1,2,4,5,6,7]</code> if it was rotated <code>7</code> times.</li>
</ul>

<p>Notice that <strong>rotating</strong> an array <code>[a[0], a[1], a[2], ..., a[n-1]]</code> 1 time results in the array <code>[a[n-1], a[0], a[1], a[2], ..., a[n-2]]</code>.</p>

<p>Given the sorted rotated array <code>nums</code> of <strong>unique</strong> elements, return <em>the minimum element of this array</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,5,1,2]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The original array was [1,2,3,4,5] rotated 3 times.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,5,6,7,0,1,2]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [11,13,15,17]
<strong>Output:</strong> 11
<strong>Explanation:</strong> The original array was [11,13,15,17] and it was rotated 4 times. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
	<li><code>-5000 &lt;= nums[i] &lt;= 5000</code></li>
	<li>All the integers of <code>nums</code> are <strong>unique</strong>.</li>
	<li><code>nums</code> is sorted and rotated between <code>1</code> and <code>n</code> times.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findMin(self, nums: List[int]) -> int:
        l, r = 0, len(nums) - 1
        if nums[l] < nums[r]:
            return nums[0]
        while l < r:
            m = (l + r) >> 1
            if nums[m] > nums[r]:
                l = m + 1
            else:
                r = m
        return nums[l]
```

### **Java**

```java
class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        if (nums[l] < nums[r]) return nums[0];
        while (l < r) {
            int m = (l + r) >>> 1;
            if (nums[m] > nums[r]) l = m + 1;
            else r = m;
        }
        return nums[l];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findMin(vector<int>& nums) {
        int l = 0, r = nums.size() - 1;
        if (nums[l] < nums[r]) return nums[0];
        while (l < r) {
            int m = (l + r) >> 1;
            if (nums[m] > nums[r]) l = m + 1;
            else r = m;
        }
        return nums[l];
    }
};
```

### **Go**

```go
func findMin(nums []int) int {
    l, r := 0, len(nums) - 1
    if nums[l] < nums[r] {
        return nums[0]
    }
    for l < r {
        m := (l + r) >> 1
        if nums[m] > nums[r] {
            l = m + 1
        } else {
            r = m
        }
    }
    return nums[l]
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findMin = function (nums) {
    let l = 0,
        r = nums.length - 1;
    if (nums[l] < nums[r]) return nums[0];
    while (l < r) {
        const m = (l + r) >> 1;
        if (nums[m] > nums[r]) l = m + 1;
        else r = m;
    }
    return nums[l];
};
```

### **...**

```

```

<!-- tabs:end -->
