# [1458. Max Dot Product of Two Subsequences](https://leetcode.com/problems/max-dot-product-of-two-subsequences)

[中文文档](/solution/1400-1499/1458.Max%20Dot%20Product%20of%20Two%20Subsequences/README.md)

## Description

<p>Given two arrays <code>nums1</code>&nbsp;and <code><font face="monospace">nums2</font></code><font face="monospace">.</font></p>

<p>Return the maximum dot product&nbsp;between&nbsp;<strong>non-empty</strong> subsequences of nums1 and nums2 with the same length.</p>

<p>A subsequence of a array is a new array which is formed from the original array by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie,&nbsp;<code>[2,3,5]</code>&nbsp;is a subsequence of&nbsp;<code>[1,2,3,4,5]</code>&nbsp;while <code>[1,5,3]</code>&nbsp;is not).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [2,1,-2,5], nums2 = [3,0,-6]
<strong>Output:</strong> 18
<strong>Explanation:</strong> Take subsequence [2,-2] from nums1 and subsequence [3,-6] from nums2.
Their dot product is (2*3 + (-2)*(-6)) = 18.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [3,-2], nums2 = [2,-6,7]
<strong>Output:</strong> 21
<strong>Explanation:</strong> Take subsequence [3] from nums1 and subsequence [7] from nums2.
Their dot product is (3*7) = 21.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [-1,-1], nums2 = [1,1]
<strong>Output:</strong> -1
<strong>Explanation: </strong>Take subsequence [-1] from nums1 and subsequence [1] from nums2.
Their dot product is -1.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 500</code></li>
	<li><code>-1000 &lt;= nums1[i], nums2[i] &lt;= 1000</code></li>
</ul>

## Solutions

Dynamic Programming.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxDotProduct(self, nums1: List[int], nums2: List[int]) -> int:
        m, n = len(nums1), len(nums2)
        dp = [[-inf] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                v = nums1[i - 1] * nums2[j - 1]
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1],
                               max(dp[i - 1][j - 1], 0) + v)
        return dp[-1][-1]
```

### **Java**

```java
class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int[] e : dp) {
            Arrays.fill(e, Integer.MIN_VALUE);
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                dp[i][j] = Math.max(
                    dp[i][j], Math.max(0, dp[i - 1][j - 1]) + nums1[i - 1] * nums2[j - 1]);
            }
        }
        return dp[m][n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxDotProduct(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size(), n = nums2.size();
        vector<vector<int>> dp(m + 1, vector<int>(n + 1, INT_MIN));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int v = nums1[i - 1] * nums2[j - 1];
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
                dp[i][j] = max(dp[i][j], max(0, dp[i - 1][j - 1]) + v);
            }
        }
        return dp[m][n];
    }
};
```

### **Go**

```go
func maxDotProduct(nums1 []int, nums2 []int) int {
	m, n := len(nums1), len(nums2)
	dp := make([][]int, m+1)
	for i := range dp {
		dp[i] = make([]int, n+1)
		for j := range dp[i] {
			dp[i][j] = math.MinInt32
		}
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			v := nums1[i-1] * nums2[j-1]
			dp[i][j] = max(dp[i-1][j], dp[i][j-1])
			dp[i][j] = max(dp[i][j], max(0, dp[i-1][j-1])+v)
		}
	}
	return dp[m][n]
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
