# [剑指 Offer II 101. 分割等和子串](https://leetcode.cn/problems/NUPfPr)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非空的正整数数组 <code>nums</code> ，请判断能否将这些数字分成元素和相等的两部分。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,5,11,5]
<strong>输出：</strong>true
<strong>解释：</strong>nums<strong> </strong>可以分割成 [1, 5, 5] 和 [11] 。</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,5]
<strong>输出：</strong>false
<strong>解释：</strong>nums<strong> </strong>不可以分为和相等的两部分
</pre>

<p>&nbsp;</p>

<p><meta charset="UTF-8" /></p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 416&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/partition-equal-subset-sum/">https://leetcode.cn/problems/partition-equal-subset-sum/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

题目可以转换为 `0-1` 背包问题，在 m 个数字中选出一些数字（每个数字只能使用一次），这些数字之和恰好等于 `s / 2`（s 表示所有数字之和）。

也可以用 DFS + 记忆化搜索。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        s = sum(nums)
        if s % 2 != 0:
            return False

        m, n = len(nums), (s >> 1) + 1
        dp = [[False] * n for _ in range(m)]
        for i in range(m):
            dp[i][0] = True
        if nums[0] < n:
            dp[0][nums[0]] = True

        for i in range(1, m):
            for j in range(n):
                dp[i][j] = dp[i - 1][j]
                if not dp[i][j] and nums[i] <= j:
                    dp[i][j] = dp[i - 1][j - nums[i]]
        return dp[-1][-1]
```

空间优化：

```python
class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        s = sum(nums)
        if s % 2 != 0:
            return False

        m, n = len(nums), (s >> 1) + 1
        dp = [False] * n
        dp[0] = True
        if nums[0] < n:
            dp[nums[0]] = True

        for i in range(1, m):
            for j in range(n - 1, nums[i] - 1, -1):
                dp[j] = dp[j] or dp[j - nums[i]]
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
        for (int x : nums) {
            s += x;
        }
        if (s % 2 != 0) {
            return false;
        }
        int m = nums.length, n = (s >> 1) + 1;
        boolean[] dp = new boolean[n];
        dp[0] = true;
        if (nums[0] < n) {
            dp[nums[0]] = true;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = n - 1; j >= nums[i]; --j) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[n - 1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canPartition(vector<int>& nums) {
        int s = 0;
        for (int x : nums) s += x;
        if (s % 2 != 0) return false;
        int m = nums.size(), n = (s >> 1) + 1;
        vector<bool> dp(n);
        dp[0] = true;
        if (nums[0] < n) dp[nums[0]] = true;
        for (int i = 1; i < m; ++i) {
            for (int j = n - 1; j >= nums[i]; --j) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[n - 1];
    }
};
```

### **Go**

```go
func canPartition(nums []int) bool {
	s := 0
	for _, x := range nums {
		s += x
	}
	if s%2 != 0 {
		return false
	}
	m, n := len(nums), (s>>1)+1
	dp := make([]bool, n)
	dp[0] = true
	if nums[0] < n {
		dp[nums[0]] = true
	}
	for i := 1; i < m; i++ {
		for j := n - 1; j >= nums[i]; j-- {
			dp[j] = dp[j] || dp[j-nums[i]]
		}
	}
	return dp[n-1]
}
```

### **...**

```

```

<!-- tabs:end -->
