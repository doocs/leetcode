# [2444. Count Subarrays With Fixed Bounds](https://leetcode.com/problems/count-subarrays-with-fixed-bounds)

[中文文档](/solution/2400-2499/2444.Count%20Subarrays%20With%20Fixed%20Bounds/README.md)

## Description

<p>You are given an integer array <code>nums</code> and two integers <code>minK</code> and <code>maxK</code>.</p>

<p>A <strong>fixed-bound subarray</strong> of <code>nums</code> is a subarray that satisfies the following conditions:</p>

<ul>
	<li>The <strong>minimum</strong> value in the subarray is equal to <code>minK</code>.</li>
	<li>The <strong>maximum</strong> value in the subarray is equal to <code>maxK</code>.</li>
</ul>

<p>Return <em>the <strong>number</strong> of fixed-bound subarrays</em>.</p>

<p>A <strong>subarray</strong> is a <strong>contiguous</strong> part of an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,5,2,7,5], minK = 1, maxK = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> The fixed-bound subarrays are [1,3,5] and [1,3,5,2].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1], minK = 1, maxK = 1
<strong>Output:</strong> 10
<strong>Explanation:</strong> Every subarray of nums is a fixed-bound subarray. There are 10 possible subarrays.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], minK, maxK &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countSubarrays(self, nums: List[int], minK: int, maxK: int) -> int:
        j1 = j2 = k = -1
        ans = 0
        for i, v in enumerate(nums):
            if v < minK or v > maxK:
                k = i
            if v == minK:
                j1 = i
            if v == maxK:
                j2 = i
            ans += max(0, min(j1, j2) - k)
        return ans
```

### **Java**

```java
class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long ans = 0;
        int j1 = -1, j2 = -1, k = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] < minK || nums[i] > maxK) {
                k = i;
            }
            if (nums[i] == minK) {
                j1 = i;
            }
            if (nums[i] == maxK) {
                j2 = i;
            }
            ans += Math.max(0, Math.min(j1, j2) - k);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long countSubarrays(vector<int>& nums, int minK, int maxK) {
        long long ans = 0;
        int j1 = -1, j2 = -1, k = -1;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] < minK || nums[i] > maxK) k = i;
            if (nums[i] == minK) j1 = i;
            if (nums[i] == maxK) j2 = i;
            ans += max(0, min(j1, j2) - k);
        }
        return ans;
    }
};
```

### **Go**

```go
func countSubarrays(nums []int, minK int, maxK int) int64 {
	ans := 0
	j1, j2, k := -1, -1, -1
	for i, v := range nums {
		if v < minK || v > maxK {
			k = i
		}
		if v == minK {
			j1 = i
		}
		if v == maxK {
			j2 = i
		}
		ans += max(0, min(j1, j2)-k)
	}
	return int64(ans)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
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
