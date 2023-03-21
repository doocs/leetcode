# [2401. Longest Nice Subarray](https://leetcode.com/problems/longest-nice-subarray)

[中文文档](/solution/2400-2499/2401.Longest%20Nice%20Subarray/README.md)

## Description

<p>You are given an array <code>nums</code> consisting of <strong>positive</strong> integers.</p>

<p>We call a subarray of <code>nums</code> <strong>nice</strong> if the bitwise <strong>AND</strong> of every pair of elements that are in <strong>different</strong> positions in the subarray is equal to <code>0</code>.</p>

<p>Return <em>the length of the <strong>longest</strong> nice subarray</em>.</p>

<p>A <strong>subarray</strong> is a <strong>contiguous</strong> part of an array.</p>

<p><strong>Note</strong> that subarrays of length <code>1</code> are always considered nice.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,8,48,10]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The longest nice subarray is [3,8,48]. This subarray satisfies the conditions:
- 3 AND 8 = 0.
- 3 AND 48 = 0.
- 8 AND 48 = 0.
It can be proven that no longer nice subarray can be obtained, so we return 3.</pre>

<p><strong class="example">Example 2:</strong></p>

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
        ans = j = mask = 0
        for i, x in enumerate(nums):
            while mask & x:
                mask ^= nums[j]
                j += 1
            ans = max(ans, i - j + 1)
            mask |= x
        return ans
```

### **Java**

```java
class Solution {
    public int longestNiceSubarray(int[] nums) {
        int ans = 0, mask = 0;
        for (int i = 0, j = 0; i < nums.length; ++i) {
            while ((mask & nums[i]) != 0) {
                mask ^= nums[j++];
            }
            ans = Math.max(ans, i - j + 1);
            mask |= nums[i];
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
        int ans = 0, mask = 0;
        for (int i = 0, j = 0; i < nums.size(); ++i) {
            while (mask & nums[i]) {
                mask ^= nums[j++];
            }
            ans = max(ans, i - j + 1);
            mask |= nums[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func longestNiceSubarray(nums []int) (ans int) {
	mask, j := 0, 0
	for i, x := range nums {
		for ; mask&x != 0; j++ {
			mask ^= nums[j]
		}
		if k := i - j + 1; ans < k {
			ans = k
		}
		mask |= x
	}
	return
}
```

### **TypeScript**

```ts
function longestNiceSubarray(nums: number[]): number {
    let mask = 0;
    let ans = 0;
    for (let i = 0, j = 0; i < nums.length; ++i) {
        while ((mask & nums[i]) !== 0) {
            mask ^= nums[j++];
        }
        ans = Math.max(ans, i - j + 1);
        mask |= nums[i];
    }
    return ans;
}
```

### **...**

```


```

<!-- tabs:end -->
