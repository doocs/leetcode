# [2741. 特别的排列](https://leetcode.cn/problems/special-permutations)

[English Version](/solution/2700-2799/2741.Special%20Permutations/README_EN.md)

<!-- tags:位运算,数组,状态压缩 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;，它包含 <code>n</code>&nbsp;个 <strong>互不相同</strong>&nbsp;的正整数。如果&nbsp;<code>nums</code>&nbsp;的一个排列满足以下条件，我们称它是一个特别的排列：</p>

<ul>
	<li>对于&nbsp;<code>0 &lt;= i &lt; n - 1</code>&nbsp;的下标 <code>i</code>&nbsp;，要么&nbsp;<code>nums[i] % nums[i+1] == 0</code>&nbsp;，要么&nbsp;<code>nums[i+1] % nums[i] == 0</code>&nbsp;。</li>
</ul>

<p>请你返回特别排列的总数目，由于答案可能很大，请将它对<strong>&nbsp;</strong><code>10<sup>9&nbsp;</sup>+ 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [2,3,6]
<b>输出：</b>2
<b>解释：</b>[3,6,2] 和 [2,6,3] 是 nums 两个特别的排列。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,4,3]
<b>输出：</b>2
<b>解释：</b>[3,1,4] 和 [4,1,3] 是 nums 两个特别的排列。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 14</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：状态压缩动态规划

我们注意到题目中数组的长度最大不超过 $14$，因此，我们可以用一个整数来表示当前的状态，其中第 $i$ 位为 $1$ 表示数组中的第 $i$ 个数已经被选取，为 $0$ 表示数组中的第 $i$ 个数还未被选取。

我们定义 $f[i][j]$ 表示当前选取的整数状态为 $i$，且最后一个选取的整数下标为 $j$ 的方案数。初始时 $f[0][0]=0$，答案为 $\sum_{j=0}^{n-1}f[2^n-1][j]$。

考虑 $f[i][j]$，如果当前只有一个数被选取，那么 $f[i][j]=1$。否则，我们可以枚举上一个选择的数的下标 $k$，如果 $k$ 与 $j$ 对应的数满足题目要求，那么 $f[i][j]$ 可以从 $f[i \oplus 2^j][k]$ 转移而来。即：

$$
f[i][j]=
\begin{cases}
1, & i=2^j\\
\sum_{k=0}^{n-1}f[i \oplus 2^j][k], & i \neq 2^j \text{且} \text{nums}[j] \text{与} \text{nums}[k] \text{满足题目要求}\\
\end{cases}
$$

最终答案即为 $\sum_{j=0}^{n-1}f[2^n-1][j]$。注意答案可能很大，需要对 $10^9+7$ 取模。

时间复杂度 $O(n^2 \times 2^n)$，空间复杂度 $O(n \times 2^n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def specialPerm(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        n = len(nums)
        m = 1 << n
        f = [[0] * n for _ in range(m)]
        for i in range(1, m):
            for j, x in enumerate(nums):
                if i >> j & 1:
                    ii = i ^ (1 << j)
                    if ii == 0:
                        f[i][j] = 1
                        continue
                    for k, y in enumerate(nums):
                        if x % y == 0 or y % x == 0:
                            f[i][j] = (f[i][j] + f[ii][k]) % mod
        return sum(f[-1]) % mod
```

```java
class Solution {
    public int specialPerm(int[] nums) {
        final int mod = (int) 1e9 + 7;
        int n = nums.length;
        int m = 1 << n;
        int[][] f = new int[m][n];
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    int ii = i ^ (1 << j);
                    if (ii == 0) {
                        f[i][j] = 1;
                        continue;
                    }
                    for (int k = 0; k < n; ++k) {
                        if (nums[j] % nums[k] == 0 || nums[k] % nums[j] == 0) {
                            f[i][j] = (f[i][j] + f[ii][k]) % mod;
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int x : f[m - 1]) {
            ans = (ans + x) % mod;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int specialPerm(vector<int>& nums) {
        const int mod = 1e9 + 7;
        int n = nums.size();
        int m = 1 << n;
        int f[m][n];
        memset(f, 0, sizeof(f));
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    int ii = i ^ (1 << j);
                    if (ii == 0) {
                        f[i][j] = 1;
                        continue;
                    }
                    for (int k = 0; k < n; ++k) {
                        if (nums[j] % nums[k] == 0 || nums[k] % nums[j] == 0) {
                            f[i][j] = (f[i][j] + f[ii][k]) % mod;
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int x : f[m - 1]) {
            ans = (ans + x) % mod;
        }
        return ans;
    }
};
```

```go
func specialPerm(nums []int) (ans int) {
	const mod int = 1e9 + 7
	n := len(nums)
	m := 1 << n
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
	}
	for i := 1; i < m; i++ {
		for j, x := range nums {
			if i>>j&1 == 1 {
				ii := i ^ (1 << j)
				if ii == 0 {
					f[i][j] = 1
					continue
				}
				for k, y := range nums {
					if x%y == 0 || y%x == 0 {
						f[i][j] = (f[i][j] + f[ii][k]) % mod
					}
				}
			}
		}
	}
	for _, x := range f[m-1] {
		ans = (ans + x) % mod
	}
	return
}
```

<!-- tabs:end -->

<!-- end -->
