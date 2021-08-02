# [154. Find Minimum in Rotated Sorted Array II](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii)

[中文文档](/solution/0100-0199/0154.Find%20Minimum%20in%20Rotated%20Sorted%20Array%20II/README.md)

## Description

<p>Suppose an array of length <code>n</code> sorted in ascending order is <strong>rotated</strong> between <code>1</code> and <code>n</code> times. For example, the array <code>nums = [0,1,4,4,5,6,7]</code> might become:</p>

<ul>
	<li><code>[4,5,6,7,0,1,4]</code> if it was rotated <code>4</code> times.</li>
	<li><code>[0,1,4,4,5,6,7]</code> if it was rotated <code>7</code> times.</li>
</ul>

<p>Notice that <strong>rotating</strong> an array <code>[a[0], a[1], a[2], ..., a[n-1]]</code> 1 time results in the array <code>[a[n-1], a[0], a[1], a[2], ..., a[n-2]]</code>.</p>

<p>Given the sorted rotated array <code>nums</code> that may contain <strong>duplicates</strong>, return <em>the minimum element of this array</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,3,5]
<strong>Output:</strong> 1
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [2,2,2,0,1]
<strong>Output:</strong> 0
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
	<li><code>-5000 &lt;= nums[i] &lt;= 5000</code></li>
	<li><code>nums</code> is sorted and rotated between <code>1</code> and <code>n</code> times.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> This is the same as <a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/" target="_blank">Find Minimum in Rotated Sorted Array</a> but with duplicates. Would allow duplicates affect the run-time complexity? How and why?

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findMin(self, nums: List[int]) -> int:
        l, r = 0, len(nums) - 1
        while l < r:
            m = (l + r) >> 1
            if nums[m] > nums[r]:
                l = m + 1
            elif nums[m] < nums[r]:
                r = m
            else:
                r -= 1
        return nums[l]
```

### **Java**

```java
class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (nums[m] > nums[r]) l = m + 1;
            else if (nums[m] < nums[r]) r = m;
            else --r;
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
        while (l < r) {
            int m = (l + r) >> 1;
            if (nums[m] > nums[r]) l = m + 1;
            else if (nums[m] < nums[r]) r = m;
            else --r;
        }
        return nums[l];
    }
};
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
  while (l < r) {
    const m = (l + r) >> 1;
    if (nums[m] > nums[r]) l = m + 1;
    else if (nums[m] < nums[r]) r = m;
    else --r;
  }
  return nums[l];
};
```

### **Go**

```go
func findMin(nums []int) int {
	left, right := 0, len(nums)-1
	for left+1 < right {
		mid := int(uint(left+right) >> 1)
		if nums[mid] > nums[right] {
			left = mid
		} else if nums[mid] < nums[right] {
			right = mid
		} else {
			right--
		}
	}
	if nums[left] < nums[right] {
		return nums[left]
	}
	return nums[right]
}
```

### **...**

```

```

<!-- tabs:end -->
