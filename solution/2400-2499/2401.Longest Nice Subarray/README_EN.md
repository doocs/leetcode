# [2401. Longest Nice Subarray](https://leetcode.com/problems/longest-nice-subarray)

[中文文档](/solution/2400-2499/2401.Longest%20Nice%20Subarray/README.md)

## Description

<p>You are given an array <code>nums</code> consisting of <strong>positive</strong> integers.</p>

<p>We call a subarray of <code>nums</code> <strong>nice</strong> if the bitwise <strong>AND</strong> of every pair of elements that are in <strong>different</strong> positions in the subarray is equal to <code>0</code>.</p>

<p>Return <em>the length of the <strong>longest</strong> nice subarray</em>.</p>

<p>A <strong>subarray</strong> is a <strong>contiguous</strong> part of an array.</p>

<p><strong>Note</strong> that subarrays of length <code>1</code> are always considered nice.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,8,48,10]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The longest nice subarray is [3,8,48]. This subarray satisfies the conditions:
- 3 AND 8 = 0.
- 3 AND 48 = 0.
- 8 AND 48 = 0.
It can be proven that no longer nice subarray can be obtained, so we return 3.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,5,11,13]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The length of the longest nice subarray is 1. Any subarray of length 1 can be chosen.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestNiceSubarray(self, nums: List[int]) -> int:
        ans = j = t = 0
        for i, v in enumerate(nums):
            while t & v:
                t ^= nums[j]
                j += 1
            t |= v
            ans = max(ans, i - j + 1)
        return ans
```

### **Java**

```java
class Solution {
    public int longestNiceSubarray(int[] nums) {
        int j = 0, t = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            while ((t & nums[i]) != 0) {
                t ^= nums[j++];
            }
            t |= nums[i];
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestNiceSubarray(vector<int>& nums) {
        int t = 0, j = 0;
        int ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            while (t & nums[i]) {
                t ^= nums[j++];
            }
            t |= nums[i];
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

### **Go**

```go
func longestNiceSubarray(nums []int) int {
	t, j := 0, 0
	ans := 0
	for i, v := range nums {
		for (t & v) != 0 {
			t ^= nums[j]
			j++
		}
		t |= v
		ans = max(ans, i-j+1)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
