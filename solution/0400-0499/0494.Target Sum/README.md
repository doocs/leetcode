# [494. 目标和](https://leetcode.cn/problems/target-sum)

[English Version](/solution/0400-0499/0494.Target%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>target</code> 。</p>

<p>向数组中的每个整数前添加 <code>'+'</code> 或 <code>'-'</code> ，然后串联起所有整数，可以构造一个 <strong>表达式</strong> ：</p>

<ul>
	<li>例如，<code>nums = [2, 1]</code> ，可以在 <code>2</code> 之前添加 <code>'+'</code> ，在 <code>1</code> 之前添加 <code>'-'</code> ，然后串联起来得到表达式 <code>"+2-1"</code> 。</li>
</ul>

<p>返回可以通过上述方法构造的、运算结果等于 <code>target</code> 的不同 <strong>表达式</strong> 的数目。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1,1,1], target = 3
<strong>输出：</strong>5
<strong>解释：</strong>一共有 5 种方法让最终目标和为 3 。
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1], target = 1
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 20</code></li>
	<li><code>0 <= nums[i] <= 1000</code></li>
	<li><code>0 <= sum(nums[i]) <= 1000</code></li>
	<li><code>-1000 <= target <= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

题目可以转换为 `0-1` 背包问题。

设整数数组总和为 `s`，添加 `-` 号的元素之和为 `x`，则添加 `+` 号的元素之和为 `s - x`，那么 `s - x - x = target`，`2x = s - target`。左式成立需要满足 `s - target` 一定大于等于 0，并且能够被 2 整除。在此前提下，我们可以将问题抽象为： 从数组中选出若干个数，使得选出的元素之和为 x。显然这是一个 `0-1` 背包问题。

定义 `dp[i][j]` 表示从前 i 个数中选出若干个数，使得所选元素之和为 j 的所有方案数。

<!-- tabs:start -->

### **Python3**

动态规划——`0-1` 背包朴素做法：

```python
class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        s = sum(nums)
        if s < target or (s - target) % 2 != 0:
            return 0
        m, n = len(nums), (s - target) // 2
        dp = [[0] * (n + 1) for _ in range(m + 1)]
        dp[0][0] = 1
        for i in range(1, m + 1):
            for j in range(n + 1):
                dp[i][j] = dp[i - 1][j]
                if nums[i - 1] <= j:
                    dp[i][j] += dp[i - 1][j - nums[i - 1]]
        return dp[-1][-1]
```

动态规划——`0-1` 背包空间优化：

```python
class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        s = sum(nums)
        if s < target or (s - target) % 2 != 0:
            return 0
        n = (s - target) // 2
        dp = [0] * (n + 1)
        dp[0] = 1
        for v in nums:
            for j in range(n, v - 1, -1):
                dp[j] += dp[j - v]
        return dp[-1]
```

DFS：

```python
class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        @cache
        def dfs(i, t):
            if i == n:
                if t == target:
                    return 1
                return 0
            return dfs(i + 1, t + nums[i]) + dfs(i + 1, t - nums[i])

        ans, n = 0, len(nums)
        return dfs(0, 0)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int s = 0;
        for (int v : nums) {
            s += v;
        }
        if (s < target || (s - target) % 2 != 0) {
            return 0;
        }
        int m = nums.length;
        int n = (s - target) / 2;
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                dp[i][j] = dp[i - 1][j];
                if (nums[i - 1] <= j) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[m][n];
    }
}
```

```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int s = 0;
        for (int v : nums) {
            s += v;
        }
        if (s < target || (s - target) % 2 != 0) {
            return 0;
        }
        int n = (s - target) / 2;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int v : nums) {
            for (int j = n; j >= v; --j) {
                dp[j] += dp[j - v];
            }
        }
        return dp[n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findTargetSumWays(vector<int>& nums, int target) {
        int s = accumulate(nums.begin(), nums.end(), 0);
        if (s < target || (s - target) % 2 != 0) return 0;
        int m = nums.size(), n = (s - target) / 2;
        vector<vector<int>> dp(m + 1, vector<int>(n + 1));
        dp[0][0] = 1;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                dp[i][j] += dp[i - 1][j];
                if (nums[i - 1] <= j) dp[i][j] += dp[i - 1][j - nums[i - 1]];
            }
        }
        return dp[m][n];
    }
};
```

```cpp
class Solution {
public:
    int findTargetSumWays(vector<int>& nums, int target) {
        int s = accumulate(nums.begin(), nums.end(), 0);
        if (s < target || (s - target) % 2 != 0) return 0;
        int n = (s - target) / 2;
        vector<int> dp(n + 1);
        dp[0] = 1;
        for (int& v : nums)
            for (int j = n; j >= v; --j)
                dp[j] += dp[j - v];
        return dp[n];
    }
};
```

### **Go**

```go
func findTargetSumWays(nums []int, target int) int {
	s := 0
	for _, v := range nums {
		s += v
	}
	if s < target || (s-target)%2 != 0 {
		return 0
	}
	m, n := len(nums), (s-target)/2
	dp := make([][]int, m+1)
	for i := range dp {
		dp[i] = make([]int, n+1)
	}
	dp[0][0] = 1
	for i := 1; i <= m; i++ {
		for j := 0; j <= n; j++ {
			dp[i][j] = dp[i-1][j]
			if nums[i-1] <= j {
				dp[i][j] += dp[i-1][j-nums[i-1]]
			}
		}
	}
	return dp[m][n]
}
```

```go
func findTargetSumWays(nums []int, target int) int {
	s := 0
	for _, v := range nums {
		s += v
	}
	if s < target || (s-target)%2 != 0 {
		return 0
	}
	n := (s - target) / 2
	dp := make([]int, n+1)
	dp[0] = 1
	for _, v := range nums {
		for j := n; j >= v; j-- {
			dp[j] += dp[j-v]
		}
	}
	return dp[n]
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var findTargetSumWays = function (nums, target) {
    let s = 0;
    for (let v of nums) {
        s += v;
    }
    if (s < target || (s - target) % 2 != 0) {
        return 0;
    }
    const m = nums.length;
    const n = (s - target) / 2;
    let dp = new Array(n + 1).fill(0);
    dp[0] = 1;
    for (let i = 1; i <= m; ++i) {
        for (let j = n; j >= nums[i - 1]; --j) {
            dp[j] += dp[j - nums[i - 1]];
        }
    }
    return dp[n];
};
```

### **...**

```

```

<!-- tabs:end -->
