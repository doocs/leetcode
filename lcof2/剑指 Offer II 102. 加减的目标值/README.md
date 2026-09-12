---
comments: true
---

<!-- problem:start -->

# [剑指 Offer II 102. 加减的目标值](https://leetcode.cn/problems/YaVDxD)

## 题目描述

<!-- description:start -->

<p>给定一个正整数数组 <code>nums</code> 和一个整数 <code>target</code> 。</p>

<p>向数组中的每个整数前添加&nbsp;<code>&#39;+&#39;</code> 或 <code>&#39;-&#39;</code> ，然后串联起所有整数，可以构造一个 <strong>表达式</strong> ：</p>

<ul>
	<li>例如，<code>nums = [2, 1]</code> ，可以在 <code>2</code> 之前添加 <code>&#39;+&#39;</code> ，在 <code>1</code> 之前添加 <code>&#39;-&#39;</code> ，然后串联起来得到表达式 <code>&quot;+2-1&quot;</code> 。</li>
</ul>

<p>返回可以通过上述方法构造的、运算结果等于 <code>target</code> 的不同 <strong>表达式</strong> 的数目。</p>

<p>&nbsp;</p>

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

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 20</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>0 &lt;= sum(nums[i]) &lt;= 1000</code></li>
	<li><code>-1000 &lt;= target &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 494&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/target-sum/">https://leetcode.cn/problems/target-sum/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

<!-- thinking:start -->

> **思考**
>
> 每个数前加正号或负号，使结果等于 $\textit{target}$。$n\le 20$ 时可爆搜，但题目允许用计数 DP。
>
> 前 $i$ 个数能形成的和落在 $[-1000,1000]$，用偏移 $1000$ 把下标变为非负。$dp[i][j]$ 由加上或减去 $nums[i]$ 从上一行累加而来。

<!-- thinking:end -->

用偏移量把负数下标映射到数组里。$dp[i][j]$ 表示前 $i$ 个数凑出和 $j-1000$ 的方案数。

时间复杂度 $O(n \times S)$，空间复杂度 $O(n \times S)$。其中 $S$ 为偏移后的和范围。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        if target < -1000 or target > 1000:
            return 0
        n = len(nums)
        dp = [[0] * 2001 for i in range(n)]
        dp[0][nums[0] + 1000] += 1
        dp[0][-nums[0] + 1000] += 1
        for i in range(1, n):
            for j in range(-1000, 1001):
                if dp[i - 1][j + 1000] > 0:
                    dp[i][j + nums[i] + 1000] += dp[i - 1][j + 1000]
                    dp[i][j - nums[i] + 1000] += dp[i - 1][j + 1000]
        return dp[n - 1][target + 1000]
```

#### Java

```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        if (target < -1000 || target > 1000) {
            return 0;
        }

        int n = nums.length;
        int[][] dp = new int[n][2001];

        dp[0][nums[0] + 1000] += 1;
        dp[0][-nums[0] + 1000] += 1;

        for (int i = 1; i < n; i++) {
            for (int j = -1000; j <= 1000; j++) {
                if (dp[i - 1][j + 1000] > 0) {
                    dp[i][j + nums[i] + 1000] += dp[i - 1][j + 1000];
                    dp[i][j - nums[i] + 1000] += dp[i - 1][j + 1000];
                }
            }
        }
        return dp[n - 1][target + 1000];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findTargetSumWays(vector<int>& nums, int target) {
        int s = 0;
        for (int x : nums) s += x;
        if (s - target < 0 || (s - target) % 2 != 0) return 0;
        target = (s - target) / 2 + 1;
        vector<int> dp(target);
        dp[0] = 1;
        for (int i = 1; i < nums.size() + 1; ++i) {
            for (int j = target - 1; j >= nums[i - 1]; --j) {
                dp[j] += dp[j - nums[i - 1]];
            }
        }
        return dp[target - 1];
    }
};
```

#### Go

```go
func findTargetSumWays(nums []int, target int) int {
	if target < -1000 || target > 1000 {
		return 0
	}
	n := len(nums)
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, 2001)
	}
	dp[0][nums[0]+1000] += 1
	dp[0][-nums[0]+1000] += 1
	for i := 1; i < n; i++ {
		for j := -1000; j <= 1000; j++ {
			if dp[i-1][j+1000] > 0 {
				dp[i][j+nums[i]+1000] += dp[i-1][j+1000]
				dp[i][j-nums[i]+1000] += dp[i-1][j+1000]
			}
		}
	}
	return dp[n-1][target+1000]
}
```

#### Swift

```swift
class Solution {
    func findTargetSumWays(_ nums: [Int], _ target: Int) -> Int {
        if target < -1000 || target > 1000 {
            return 0
        }

        let n = nums.count
        var dp = Array(repeating: Array(repeating: 0, count: 2001), count: n)

        dp[0][nums[0] + 1000] += 1
        dp[0][-nums[0] + 1000] += 1

        for i in 1..<n {
            for j in -1000...1000 {
                if dp[i - 1][j + 1000] > 0 {
                    dp[i][j + nums[i] + 1000] += dp[i - 1][j + 1000]
                    dp[i][j - nums[i] + 1000] += dp[i - 1][j + 1000]
                }
            }
        }

        return dp[n - 1][target + 1000]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法二：动态规划（转化 01 背包）

<!-- thinking:start -->

> **思考**
>
> 方法一把和的正负都展开。设负号子集的和为 $neg$，则 $s-2\cdot neg=\textit{target}$，化为选出和为 $(s-\textit{target})/2$ 的子集个数，即二维 $01$ 背包计数。

<!-- thinking:end -->

设总和为 $s$，将「部分取负」转化为选出一个子集使其和为 $(s-\textit{target})/2$，再用二维 $01$ 背包计数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        s = sum(nums)
        if s - target < 0 or (s - target) % 2 != 0:
            return 0
        target = (s - target) // 2 + 1
        n = len(nums) + 1
        dp = [[0] * target for _ in range(n)]
        dp[0][0] = 1
        for i in range(1, n):
            for j in range(target):
                dp[i][j] = dp[i - 1][j]
                if nums[i - 1] <= j:
                    dp[i][j] += dp[i - 1][j - nums[i - 1]]
        return dp[-1][-1]
```

#### Java

```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int s = 0;
        for (int x : nums) {
            s += x;
        }
        if (s - target < 0 || (s - target) % 2 != 0) {
            return 0;
        }
        target = (s - target) / 2 + 1;
        int[] dp = new int[target];
        dp[0] = 1;
        for (int i = 1; i < nums.length + 1; ++i) {
            for (int j = target - 1; j >= nums[i - 1]; --j) {
                dp[j] += dp[j - nums[i - 1]];
            }
        }
        return dp[target - 1];
    }
}
```

#### Go

```go
func findTargetSumWays(nums []int, target int) int {
	s := 0
	for _, x := range nums {
		s += x
	}
	if s-target < 0 || (s-target)%2 != 0 {
		return 0
	}
	target = (s-target)/2 + 1
	dp := make([]int, target)
	dp[0] = 1
	for i := 1; i < len(nums)+1; i++ {
		for j := target - 1; j >= nums[i-1]; j-- {
			dp[j] += dp[j-nums[i-1]]
		}
	}
	return dp[target-1]
}
```

#### Swift

```swift
class Solution {
    func findTargetSumWays(_ nums: [Int], _ target: Int) -> Int {
        let s = nums.reduce(0, +)
        if s - target < 0 || (s - target) % 2 != 0 {
            return 0
        }
        let target = (s - target) / 2
        var dp = [Int](repeating: 0, count: target + 1)
        dp[0] = 1

        for num in nums {
            for j in stride(from: target, through: num, by: -1) {
                dp[j] += dp[j - num]
            }
        }
        return dp[target]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法三：动态规划（空间优化）

<!-- thinking:start -->

> **思考**
>
> 方法二的背包只依赖上一行。倒序更新一维数组即可。

<!-- thinking:end -->

每个数最多选一次，内层倒序更新一维数组。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        s = sum(nums)
        if s - target < 0 or (s - target) % 2 != 0:
            return 0
        target = (s - target) // 2 + 1
        n = len(nums) + 1
        dp = [0] * target
        dp[0] = 1
        for i in range(1, n):
            for j in range(target - 1, nums[i - 1] - 1, -1):
                dp[j] += dp[j - nums[i - 1]]
        return dp[-1]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法四：记忆化搜索

<!-- thinking:start -->

> **思考**
>
> 方法三仍是递推。直接对每个数枚举取正或取负并记忆化，实现与转移最贴近题意。

<!-- thinking:end -->

枚举每个数取正或取负，记忆化搜索剩余目标和。

<!-- tabs:start -->

#### Python3

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
