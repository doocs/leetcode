# [312. 戳气球](https://leetcode.cn/problems/burst-balloons)

[English Version](/solution/0300-0399/0312.Burst%20Balloons/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>n</code> 个气球，编号为<code>0</code> 到 <code>n - 1</code>，每个气球上都标有一个数字，这些数字存在数组&nbsp;<code>nums</code>&nbsp;中。</p>

<p>现在要求你戳破所有的气球。戳破第 <code>i</code> 个气球，你可以获得&nbsp;<code>nums[i - 1] * nums[i] * nums[i + 1]</code> 枚硬币。&nbsp;这里的 <code>i - 1</code> 和 <code>i + 1</code> 代表和&nbsp;<code>i</code>&nbsp;相邻的两个气球的序号。如果 <code>i - 1</code>或 <code>i + 1</code> 超出了数组的边界，那么就当它是一个数字为 <code>1</code> 的气球。</p>

<p>求所能获得硬币的最大数量。</p>

<p>&nbsp;</p>
<strong>示例 1：</strong>

<pre>
<strong>输入：</strong>nums = [3,1,5,8]
<strong>输出：</strong>167
<strong>解释：</strong>
nums = [3,1,5,8] --&gt; [3,5,8] --&gt; [3,8] --&gt; [8] --&gt; []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,5]
<strong>输出：</strong>10
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 300</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

区间 DP。

-   状态表示：`dp[i][j]` 表示戳破区间 `(i, j)` 内所有气球获得的最大硬币数。
-   状态计算：枚举开区间 `(i, j)` 中以气球 k 作为最后戳破的气球。那么 `dp[i][j] = max(dp[i][k] + dp[k][j] + nums[i] * nums[k] * nums[j]), k ∈ [i + 1, j)`。

以区间长度 l 从小到大开始处理每个状态值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
