# [312. Burst Balloons](https://leetcode.com/problems/burst-balloons)

[中文文档](/solution/0300-0399/0312.Burst%20Balloons/README.md)

## Description

<p>You are given <code>n</code> balloons, indexed from <code>0</code> to <code>n - 1</code>. Each balloon is painted with a number on it represented by an array <code>nums</code>. You are asked to burst all the balloons.</p>

<p>If you burst the <code>i<sup>th</sup></code> balloon, you will get <code>nums[i - 1] * nums[i] * nums[i + 1]</code> coins. If <code>i - 1</code> or <code>i + 1</code> goes out of bounds of the array, then treat it as if there is a balloon with a <code>1</code> painted on it.</p>

<p>Return <em>the maximum coins you can collect by bursting the balloons wisely</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,5,8]
<strong>Output:</strong> 167
<strong>Explanation:</strong>
nums = [3,1,5,8] --&gt; [3,5,8] --&gt; [3,8] --&gt; [8] --&gt; []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,5]
<strong>Output:</strong> 10
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 300</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxCoins(self, nums: List[int]) -> int:
        nums = [1] + nums + [1]
        n = len(nums)
        dp = [[0] * n for _ in range(n)]
        for l in range(2, n):
            for i in range(n - l):
                j = i + l
                for k in range(i + 1, j):
                    dp[i][j] = max(
                        dp[i][j], dp[i][k] + dp[k][j] + nums[i] * nums[k] * nums[j]
                    )
        return dp[0][-1]
```

### **Java**

```java
class Solution {
    public int maxCoins(int[] nums) {
        int[] vals = new int[nums.length + 2];
        vals[0] = 1;
        vals[vals.length - 1] = 1;
        System.arraycopy(nums, 0, vals, 1, nums.length);
        int n = vals.length;
        int[][] dp = new int[n][n];
        for (int l = 2; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;
                for (int k = i + 1; k < j; ++k) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + vals[i] * vals[k] * vals[j]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
```

### **TypeScript**

```ts
function maxCoins(nums: number[]): number {
    let n = nums.length;
    let dp = Array.from({ length: n + 1 }, v => new Array(n + 2).fill(0));
    nums.unshift(1);
    nums.push(1);
    for (let i = n - 1; i >= 0; --i) {
        for (let j = i + 2; j < n + 2; ++j) {
            for (let k = i + 1; k < j; ++k) {
                dp[i][j] = Math.max(
                    nums[i] * nums[k] * nums[j] + dp[i][k] + dp[k][j],
                    dp[i][j],
                );
            }
        }
    }
    return dp[0][n + 1];
}
```

### **C++**

```cpp
class Solution {
public:
    int maxCoins(vector<int>& nums) {
        nums.insert(nums.begin(), 1);
        nums.push_back(1);
        int n = nums.size();
        vector<vector<int>> dp(n, vector<int>(n));
        for (int l = 2; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;
                for (int k = i + 1; k < j; ++k) {
                    dp[i][j] = max(dp[i][j], dp[i][k] + dp[k][j] + nums[i] * nums[k] * nums[j]);
                }
            }
        }
        return dp[0][n - 1];
    }
};
```

### **Go**

```go
func maxCoins(nums []int) int {
	vals := make([]int, len(nums)+2)
	for i := 0; i < len(nums); i++ {
		vals[i+1] = nums[i]
	}
	n := len(vals)
	vals[0], vals[n-1] = 1, 1
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, n)
	}
	for l := 2; l < n; l++ {
		for i := 0; i+l < n; i++ {
			j := i + l
			for k := i + 1; k < j; k++ {
				dp[i][j] = max(dp[i][j], dp[i][k]+dp[k][j]+vals[i]*vals[k]*vals[j])
			}
		}
	}
	return dp[0][n-1]
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
