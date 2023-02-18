# [1746. Maximum Subarray Sum After One Operation](https://leetcode.com/problems/maximum-subarray-sum-after-one-operation)

[中文文档](/solution/1700-1799/1746.Maximum%20Subarray%20Sum%20After%20One%20Operation/README.md)

## Description

<p>You are given an integer array <code>nums</code>. You must perform <strong>exactly one</strong> operation&nbsp;where you can <strong>replace</strong> one&nbsp;element <code>nums[i]</code> with <code>nums[i] * nums[i]</code>.&nbsp;</p>

<p>Return <em>the <strong>maximum</strong> possible subarray sum after <strong>exactly&nbsp;one</strong> operation</em>. The subarray must be non-empty.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> nums = [2,-1,-4,-3]

<strong>Output:</strong> 17

<strong>Explanation:</strong> You can perform the operation on index 2 (0-indexed) to make nums = [2,-1,<strong>16</strong>,-3]. Now, the maximum subarray sum is 2 + -1 + 16 = 17.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> nums = [1,-1,1,1,-1,-1,1]

<strong>Output:</strong> 4

<strong>Explanation:</strong> You can perform the operation on index 1 (0-indexed) to make nums = [1,<strong>1</strong>,1,1,-1,-1,1]. Now, the maximum subarray sum is 1 + 1 + 1 + 1 = 4.</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
    <li><code>-10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxSumAfterOperation(self, nums: List[int]) -> int:
        f = g = 0
        ans = -inf
        for x in nums:
            ff = max(f, 0) + x
            gg = max(max(f, 0) + x * x, g + x)
            f, g = ff, gg
            ans = max(ans, f, g)
        return ans
```

### **Java**

```java
class Solution {
    public int maxSumAfterOperation(int[] nums) {
        int f = 0, g = 0;
        int ans = Integer.MIN_VALUE;
        for (int x : nums) {
            int ff = Math.max(f, 0) + x;
            int gg = Math.max(Math.max(f, 0) + x * x, g + x);
            f = ff;
            g = gg;
            ans = Math.max(ans, Math.max(f, g));
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxSumAfterOperation(vector<int>& nums) {
        int f = 0, g = 0;
        int ans = INT_MIN;
        for (int x : nums) {
            int ff = max(f, 0) + x;
            int gg = max(max(f, 0) + x * x, g + x);
            f = ff;
            g = gg;
            ans = max({ans, f, g});
        }
        return ans;
    }
};
```

### **Go**

```go
func maxSumAfterOperation(nums []int) int {
	var f, g int
	ans := -(1 << 30)
	for _, x := range nums {
		ff := max(f, 0) + x
		gg := max(max(f, 0)+x*x, g+x)
		f, g = ff, gg
		ans = max(ans, max(f, g))
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

### **...**

```

```

<!-- tabs:end -->
