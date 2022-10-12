# [2419. Longest Subarray With Maximum Bitwise AND](https://leetcode.com/problems/longest-subarray-with-maximum-bitwise-and)

[中文文档](/solution/2400-2499/2419.Longest%20Subarray%20With%20Maximum%20Bitwise%20AND/README.md)

## Description

<p>You are given an integer array <code>nums</code> of size <code>n</code>.</p>

<p>Consider a <strong>non-empty</strong> subarray from <code>nums</code> that has the <strong>maximum</strong> possible <strong>bitwise AND</strong>.</p>

<ul>
	<li>In other words, let <code>k</code> be the maximum value of the bitwise AND of <strong>any</strong> subarray of <code>nums</code>. Then, only subarrays with a bitwise AND equal to <code>k</code> should be considered.</li>
</ul>

<p>Return <em>the length of the <strong>longest</strong> such subarray</em>.</p>

<p>The bitwise AND of an array is the bitwise AND of all the numbers in it.</p>

<p>A <strong>subarray</strong> is a contiguous sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,3,2,2]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
The maximum possible bitwise AND of a subarray is 3.
The longest subarray with that value is [3,3], so we return 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
The maximum possible bitwise AND of a subarray is 4.
The longest subarray with that value is [4], so we return 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestSubarray(self, nums: List[int]) -> int:
        mx = max(nums)
        ans = cnt = 0
        for v in nums:
            if v == mx:
                cnt += 1
                ans = max(ans, cnt)
            else:
                cnt = 0
        return ans
```

### **Java**

```java
class Solution {
    public int longestSubarray(int[] nums) {
        int mx = 0;
        for (int v : nums) {
            mx = Math.max(mx, v);
        }
        int ans = 0, cnt = 0;
        for (int v : nums) {
            if (v == mx) {
                ++cnt;
                ans = Math.max(ans, cnt);
            } else {
                cnt = 0;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestSubarray(vector<int>& nums) {
        int mx = *max_element(nums.begin(), nums.end());
        int ans = 0, cnt = 0;
        for (int v : nums) {
            if (v == mx) {
                ++cnt;
                ans = max(ans, cnt);
            } else {
                cnt = 0;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func longestSubarray(nums []int) int {
	mx := 0
	for _, v := range nums {
		mx = max(mx, v)
	}
	ans, cnt := 0, 0
	for _, v := range nums {
		if v == mx {
			cnt++
			ans = max(ans, cnt)
		} else {
			cnt = 0
		}
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
