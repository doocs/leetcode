# [413. Arithmetic Slices](https://leetcode.com/problems/arithmetic-slices)

[中文文档](/solution/0400-0499/0413.Arithmetic%20Slices/README.md)

## Description

<p>An integer array is called arithmetic if it consists of <strong>at least three elements</strong> and if the difference between any two consecutive elements is the same.</p>

<ul>
	<li>For example, <code>[1,3,5,7,9]</code>, <code>[7,7,7,7]</code>, and <code>[3,-1,-5,-9]</code> are arithmetic sequences.</li>
</ul>

<p>Given an integer array <code>nums</code>, return <em>the number of arithmetic <strong>subarrays</strong> of</em> <code>nums</code>.</p>

<p>A <strong>subarray</strong> is a contiguous subsequence of the array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We have 3 arithmetic slices in nums: [1, 2, 3], [2, 3, 4] and [1,2,3,4] itself.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## Solutions

Dynamic programming.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numberOfArithmeticSlices(self, nums: List[int]) -> int:
        n = len(nums)
        dp = [0] * n
        for i in range(2, n):
            if nums[i] + nums[i - 2] == (nums[i - 1] << 1):
                dp[i] = 1 + dp[i - 1]
        return sum(dp)
```

### **Java**

```java
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 2; i < n; ++i) {
            if (nums[i] + nums[i - 2] == (nums[i - 1] << 1)) {
                dp[i] = 1 + dp[i - 1];
            }
        }
        int res = 0;
        for (int e : dp) {
            res += e;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfArithmeticSlices(vector<int>& nums) {
        int n = nums.size();
        vector<int> dp(n, 0);
        for (int i = 2; i < n; ++i) {
            if (nums[i] + nums[i - 2] == (nums[i - 1] * 2)) {
                dp[i] = 1 + dp[i - 1];
            }
        }
        int res = 0;
        for (auto e : dp) {
            res += e;
        }
        return res;
    }
};
```

### **Go**

```go
func numberOfArithmeticSlices(nums []int) int {
	n := len(nums)
	dp := make([]int, n)
	for i := 2; i < n; i++ {
		if nums[i]-nums[i-1] == nums[i-1]-nums[i-2] {
			dp[i] = 1 + dp[i-1]
		}
	}
	res := 0
	for _, e := range dp {
		res += e
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
