# [416. 分割等和子集](https://leetcode.cn/problems/partition-equal-subset-sum)

[English Version](/solution/0400-0499/0416.Partition%20Equal%20Subset%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>只包含正整数 </strong>的 <strong>非空 </strong>数组 <code>nums</code> 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,5,11,5]
<strong>输出：</strong>true
<strong>解释：</strong>数组可以分割成 [1, 5, 5] 和 [11] 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,5]
<strong>输出：</strong>false
<strong>解释：</strong>数组不能分割成两个元素和相等的子集。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 200</code></li>
	<li><code>1 <= nums[i] <= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

题目可以转换为 `0-1` 背包问题。

设整数数组总和为 `s`，要使得数组分割成两个元素和相等的子数组，需要满足 s 能够被 2 整除。在此前提下，我们可以将问题抽象为： 从数组中选出若干个数，使得选出的元素之和为 `s/2`。显然这是一个 `0-1` 背包问题。

定义 `dp[i][j]` 表示是否可以从前 i 个数中选出若干个数，使得所选元素之和为 j。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

动态规划——`0-1` 背包朴素做法：

```python
class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        s = sum(nums)
        if s % 2 != 0:
            return False
        m, n = len(nums), s >> 1
        dp = [[False] * (n + 1) for _ in range(m + 1)]
        dp[0][0] = True
        for i in range(1, m + 1):
            for j in range(n + 1):
                dp[i][j] = dp[i - 1][j]
                if not dp[i][j] and nums[i - 1] <= j:
                    dp[i][j] = dp[i - 1][j - nums[i - 1]]
        return dp[-1][-1]
```

动态规划——`0-1` 背包空间优化：

```python
class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        s = sum(nums)
        if s % 2 != 0:
            return False
        n = s >> 1
        dp = [False] * (n + 1)
        dp[0] = True
        for v in nums:
            for j in range(n, v - 1, -1):
                dp[j] = dp[j] or dp[j - v]
        return dp[-1]
```

DFS + 记忆化搜索：

```python
class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        s = sum(nums)
        if s % 2 != 0:
            return False
        target = s >> 1

        @cache
        def dfs(i, s):
            nonlocal target
            if s > target or i >= len(nums):
                return False
            if s == target:
                return True
            return dfs(i + 1, s) or dfs(i + 1, s + nums[i])

        return dfs(0, 0)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int s = 0;
        for (int v : nums) {
            s += v;
        }
        if (s % 2 != 0) {
            return false;
        }
        int m = nums.length;
        int n = s >> 1;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                dp[i][j] = dp[i - 1][j];
                if (!dp[i][j] && nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[m][n];
    }
}
```

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int s = 0;
        for (int v : nums) {
            s += v;
        }
        if (s % 2 != 0) {
            return false;
        }
        int n = s >> 1;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int v : nums) {
            for (int j = n; j >= v; --j) {
                dp[j] = dp[j] || dp[j - v];
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
    bool canPartition(vector<int>& nums) {
        int s = accumulate(nums.begin(), nums.end(), 0);
        if (s % 2 != 0) return false;
        int m = nums.size(), n = s >> 1;
        vector<vector<bool>> dp(m + 1, vector<bool>(n + 1));
        dp[0][0] = true;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                dp[i][j] = dp[i - 1][j];
                if (!dp[i][j] && nums[i - 1] <= j) dp[i][j] = dp[i - 1][j - nums[i - 1]];
            }
        }
        return dp[m][n];
    }
};
```

```cpp
class Solution {
public:
    bool canPartition(vector<int>& nums) {
        int s = accumulate(nums.begin(), nums.end(), 0);
        if (s % 2 != 0) return false;
        int n = s >> 1;
        vector<bool> dp(n + 1);
        dp[0] = true;
        for (int& v : nums)
            for (int j = n; j >= v; --j)
                dp[j] = dp[j] || dp[j - v];
        return dp[n];
    }
};
```

### **Go**

```go
func canPartition(nums []int) bool {
	s := 0
	for _, v := range nums {
		s += v
	}
	if s%2 != 0 {
		return false
	}
	m, n := len(nums), s>>1
	dp := make([][]bool, m+1)
	for i := range dp {
		dp[i] = make([]bool, n+1)
	}
	dp[0][0] = true
	for i := 1; i <= m; i++ {
		for j := 0; j < n; j++ {
			dp[i][j] = dp[i-1][j]
			if !dp[i][j] && nums[i-1] <= j {
				dp[i][j] = dp[i-1][j-nums[i-1]]
			}
		}
	}
	return dp[m][n]
}
```

```go
func canPartition(nums []int) bool {
	s := 0
	for _, v := range nums {
		s += v
	}
	if s%2 != 0 {
		return false
	}
	n := s >> 1
	dp := make([]bool, n+1)
	dp[0] = true
	for _, v := range nums {
		for j := n; j >= v; j-- {
			dp[j] = dp[j] || dp[j-v]
		}
	}
	return dp[n]
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {boolean}
 */
var canPartition = function (nums) {
    let s = 0;
    for (let v of nums) {
        s += v;
    }
    if (s % 2 != 0) {
        return false;
    }
    const m = nums.length;
    const n = s >> 1;
    const dp = new Array(n + 1).fill(false);
    dp[0] = true;
    for (let i = 1; i <= m; ++i) {
        for (let j = n; j >= nums[i - 1]; --j) {
            dp[j] = dp[j] || dp[j - nums[i - 1]];
        }
    }
    return dp[n];
};
```

### **...**

```

```

<!-- tabs:end -->
