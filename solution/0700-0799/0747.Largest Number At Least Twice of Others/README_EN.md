# [747. Largest Number At Least Twice of Others](https://leetcode.com/problems/largest-number-at-least-twice-of-others)

[中文文档](/solution/0700-0799/0747.Largest%20Number%20At%20Least%20Twice%20of%20Others/README.md)

## Description

<p>You are given an integer array <code>nums</code> where the largest integer is <strong>unique</strong>.</p>

<p>Determine whether the largest element in the array is <strong>at least twice</strong> as much as every other number in the array. If it is, return <em>the <strong>index</strong> of the largest element, or return </em><code>-1</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,6,1,0]
<strong>Output:</strong> 1
<strong>Explanation:</strong> 6 is the largest integer.
For every other number in the array x, 6 is at least twice as big as x.
The index of value 6 is 1, so we return 1.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> -1
<strong>Explanation:</strong> 4 is less than twice the value of 3, so we return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 50</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
	<li>The largest element in <code>nums</code> is unique.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def dominantIndex(self, nums: List[int]) -> int:
        mx = mid = 0
        ans = -1
        for i, v in enumerate(nums):
            if v > mx:
                mid, mx = mx, v
                ans = i
            elif v > mid:
                mid = v
        return ans if mx >= 2 * mid else -1
```

### **Java**

```java
class Solution {
    public int dominantIndex(int[] nums) {
        int mx = 0, mid = 0;
        int ans = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > mx) {
                mid = mx;
                mx = nums[i];
                ans = i;
            } else if (nums[i] > mid) {
                mid = nums[i];
            }
        }
        return mx >= mid * 2 ? ans : -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int dominantIndex(vector<int>& nums) {
        int mx = 0, mid = 0;
        int ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] > mx) {
                mid = mx;
                mx = nums[i];
                ans = i;
            } else if (nums[i] > mid)
                mid = nums[i];
        }
        return mx >= mid * 2 ? ans : -1;
    }
};
```

### **Go**

```go
func dominantIndex(nums []int) int {
	mx, mid := 0, 0
	ans := 0
	for i, v := range nums {
		if v > mx {
			mid, mx = mx, v
			ans = i
		} else if v > mid {
			mid = v
		}
	}
	if mx >= mid*2 {
		return ans
	}
	return -1
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var dominantIndex = function (nums) {
    let mx = 0,
        mid = 0;
    let ans = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i] > mx) {
            mid = mx;
            mx = nums[i];
            ans = i;
        } else if (nums[i] > mid) {
            mid = nums[i];
        }
    }
    return mx >= mid * 2 ? ans : -1;
};
```

### **...**

```

```

<!-- tabs:end -->
