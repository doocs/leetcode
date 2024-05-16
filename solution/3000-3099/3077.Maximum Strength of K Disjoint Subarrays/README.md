---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3077.Maximum%20Strength%20of%20K%20Disjoint%20Subarrays/README.md
rating: 2556
source: 第 388 场周赛 Q4
tags:
    - 数组
    - 动态规划
    - 前缀和
---

<!-- problem:start -->

# [3077. K 个不相交子数组的最大能量值](https://leetcode.cn/problems/maximum-strength-of-k-disjoint-subarrays)

[English Version](/solution/3000-3099/3077.Maximum%20Strength%20of%20K%20Disjoint%20Subarrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code>&nbsp;下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;和一个 <strong>正奇数</strong>&nbsp;整数&nbsp;<code>k</code>&nbsp;。</p>

<p><code>x</code> 个子数组的能量值定义为&nbsp;<code>strength = sum[1] * x - sum[2] * (x - 1) + sum[3] * (x - 2) - sum[4] * (x - 3) + ... + sum[x] * 1</code> ，其中&nbsp;<code>sum[i]</code>&nbsp;是第 <code>i</code>&nbsp;个子数组的和。更正式的，能量值是满足&nbsp;<code>1 &lt;= i &lt;= x</code>&nbsp;的所有&nbsp;<code>i</code>&nbsp;对应的&nbsp;<code>(-1)<sup>i+1</sup> * sum[i] * (x - i + 1)</code>&nbsp;之和。</p>

<p>你需要在 <code>nums</code>&nbsp;中选择 <code>k</code>&nbsp;个 <strong>不相交</strong><strong>子数组</strong>&nbsp;，使得&nbsp;<strong>能量值最大</strong>&nbsp;。</p>

<p>请你返回可以得到的 <strong>最大</strong><strong>能量值</strong>&nbsp;。</p>

<p><strong>注意</strong>，选出来的所有子数组&nbsp;<strong>不</strong>&nbsp;需要覆盖整个数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,-1,2], k = 3
<b>输出：</b>22
<b>解释：</b>选择 3 个子数组的最好方式是选择：nums[0..2] ，nums[3..3] 和 nums[4..4] 。能量值为 (1 + 2 + 3) * 3 - (-1) * 2 + 2 * 1 = 22 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [12,-2,-2,-2,-2], k = 5
<b>输出：</b>64
<b>解释：</b>唯一一种选 5 个不相交子数组的方案是：nums[0..0] ，nums[1..1] ，nums[2..2] ，nums[3..3] 和 nums[4..4] 。能量值为 12 * 5 - (-2) * 4 + (-2) * 3 - (-2) * 2 + (-2) * 1 = 64 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [-1,-2,-3], k = 1
<b>输出：</b>-1
<b>解释：</b>选择 1 个子数组的最优方案是：nums[0..0] 。能量值为 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li><code>1 &lt;= n * k &lt;= 10<sup>6</sup></code></li>
	<li><code>k</code> 是奇数。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

对于第 $i$ 个数 $nums[i - 1]$，如果它被选择，且位于第 $j$ 个子数组，那么它对答案的贡献是 $nums[i - 1] \times (k - j + 1) \times (-1)^{j+1}$，我们不妨将 $(-1)^{j+1}$ 记为 $sign$，那么它对答案的贡献是 $sign \times nums[i - 1] \times (k - j + 1)$。

我们定义 $f[i][j][0]$ 表示从前 $i 个数中选择 $j$ 个子数组，且第 $i$ 个数不被选的最大能量值，定义 $f[i][j][1]$ 表示从前 $i$ 个数中选择 $j$ 个子数组，且第 $i$ 个数被选的最大能量值。初始时 $f[0][0][1] = 0$，其余的值都是 $-\infty$。

当 $i > 0$ 时，我们考虑 $f[i][j]$ 如何进行状态转移。

如果第 $i$ 个数不被选，那么第 $i-1$ 个数既可以被选，也可以不被选，所以 $f[i][j][0] = \max(f[i-1][j][0], f[i-1][j][1])$。

如果第 $i$ 个数被选，此时如果第 $i-1$ 个数与第 $i$ 个数位于同一个子数组中，那么 $f[i][j][1] = \max(f[i][j][1], f[i-1][j][1] + sign \times nums[i-1] \times (k - j + 1))$，否则 $f[i][j][1] = \max(f[i][j][1], \max(f[i-1][j-1][0], f[i-1][j-1][1]) + sign \times nums[i-1] \times (k - j + 1))$。我们取这两种情况的最大值作为 $f[i][j][1]$。

最终答案是 $\max(f[n][k][0], f[n][k][1])$。

时间复杂度 $O(n \times k)$，空间复杂度 $O(n \times k)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maximumStrength(self, nums: List[int], k: int) -> int:
        n = len(nums)
        f = [[[-inf, -inf] for _ in range(k + 1)] for _ in range(n + 1)]
        f[0][0][0] = 0
        for i, x in enumerate(nums, 1):
            for j in range(k + 1):
                sign = 1 if j & 1 else -1
                f[i][j][0] = max(f[i - 1][j][0], f[i - 1][j][1])
                f[i][j][1] = max(f[i][j][1], f[i - 1][j][1] + sign * x * (k - j + 1))
                if j:
                    f[i][j][1] = max(
                        f[i][j][1], max(f[i - 1][j - 1]) + sign * x * (k - j + 1)
                    )
        return max(f[n][k])
```

```java
class Solution {
    public long maximumStrength(int[] nums, int k) {
        int n = nums.length;
        long[][][] f = new long[n + 1][k + 1][2];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                Arrays.fill(f[i][j], Long.MIN_VALUE / 2);
            }
        }
        f[0][0][0] = 0;
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            for (int j = 0; j <= k; j++) {
                long sign = (j & 1) == 1 ? 1 : -1;
                long val = sign * x * (k - j + 1);
                f[i][j][0] = Math.max(f[i - 1][j][0], f[i - 1][j][1]);
                f[i][j][1] = Math.max(f[i][j][1], f[i - 1][j][1] + val);
                if (j > 0) {
                    long t = Math.max(f[i - 1][j - 1][0], f[i - 1][j - 1][1]) + val;
                    f[i][j][1] = Math.max(f[i][j][1], t);
                }
            }
        }
        return Math.max(f[n][k][0], f[n][k][1]);
    }
}
```

```cpp
class Solution {
public:
    long long maximumStrength(vector<int>& nums, int k) {
        int n = nums.size();
        long long f[n + 1][k + 1][2];
        memset(f, -0x3f3f3f3f3f3f3f3f, sizeof(f));
        f[0][0][0] = 0;
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            for (int j = 0; j <= k; j++) {
                long long sign = (j & 1) == 1 ? 1 : -1;
                long long val = sign * x * (k - j + 1);
                f[i][j][0] = max(f[i - 1][j][0], f[i - 1][j][1]);
                f[i][j][1] = max(f[i][j][1], f[i - 1][j][1] + val);
                if (j > 0) {
                    long long t = max(f[i - 1][j - 1][0], f[i - 1][j - 1][1]) + val;
                    f[i][j][1] = max(f[i][j][1], t);
                }
            }
        }
        return max(f[n][k][0], f[n][k][1]);
    }
};
```

```go
func maximumStrength(nums []int, k int) int64 {
	n := len(nums)
	f := make([][][]int64, n+1)
	const inf int64 = math.MinInt64 / 2
	for i := range f {
		f[i] = make([][]int64, k+1)
		for j := range f[i] {
			f[i][j] = []int64{inf, inf}
		}
	}
	f[0][0][0] = 0
	for i := 1; i <= n; i++ {
		x := nums[i-1]
		for j := 0; j <= k; j++ {
			sign := int64(-1)
			if j&1 == 1 {
				sign = 1
			}
			val := sign * int64(x) * int64(k-j+1)
			f[i][j][0] = max(f[i-1][j][0], f[i-1][j][1])
			f[i][j][1] = max(f[i][j][1], f[i-1][j][1]+val)
			if j > 0 {
				t := max(f[i-1][j-1][0], f[i-1][j-1][1]) + val
				f[i][j][1] = max(f[i][j][1], t)
			}
		}
	}
	return max(f[n][k][0], f[n][k][1])
}
```

```ts
function maximumStrength(nums: number[], k: number): number {
    const n: number = nums.length;
    const f: number[][][] = Array.from({ length: n + 1 }, () =>
        Array.from({ length: k + 1 }, () => [-Infinity, -Infinity]),
    );
    f[0][0][0] = 0;
    for (let i = 1; i <= n; i++) {
        const x: number = nums[i - 1];
        for (let j = 0; j <= k; j++) {
            const sign: number = (j & 1) === 1 ? 1 : -1;
            const val: number = sign * x * (k - j + 1);
            f[i][j][0] = Math.max(f[i - 1][j][0], f[i - 1][j][1]);
            f[i][j][1] = Math.max(f[i][j][1], f[i - 1][j][1] + val);
            if (j > 0) {
                f[i][j][1] = Math.max(f[i][j][1], Math.max(...f[i - 1][j - 1]) + val);
            }
        }
    }
    return Math.max(...f[n][k]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
