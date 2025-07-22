---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3251.Find%20the%20Count%20of%20Monotonic%20Pairs%20II/README.md
rating: 2323
source: 第 410 场周赛 Q4
tags:
    - 数组
    - 数学
    - 动态规划
    - 组合数学
    - 前缀和
---

<!-- problem:start -->

# [3251. 单调数组对的数目 II](https://leetcode.cn/problems/find-the-count-of-monotonic-pairs-ii)

[English Version](/solution/3200-3299/3251.Find%20the%20Count%20of%20Monotonic%20Pairs%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为&nbsp;<code>n</code>&nbsp;的&nbsp;<strong>正</strong>&nbsp;整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>如果两个&nbsp;<strong>非负</strong>&nbsp;整数数组&nbsp;<code>(arr1, arr2)</code>&nbsp;满足以下条件，我们称它们是&nbsp;<strong>单调</strong>&nbsp;数组对：</p>

<ul>
	<li>两个数组的长度都是&nbsp;<code>n</code>&nbsp;。</li>
	<li><code>arr1</code>&nbsp;是单调<strong>&nbsp;非递减</strong>&nbsp;的，换句话说&nbsp;<code>arr1[0] &lt;= arr1[1] &lt;= ... &lt;= arr1[n - 1]</code>&nbsp;。</li>
	<li><code>arr2</code>&nbsp;是单调 <strong>非递增</strong>&nbsp;的，换句话说&nbsp;<code>arr2[0] &gt;= arr2[1] &gt;= ... &gt;= arr2[n - 1]</code>&nbsp;。</li>
	<li>对于所有的&nbsp;<code>0 &lt;= i &lt;= n - 1</code>&nbsp;都有&nbsp;<code>arr1[i] + arr2[i] == nums[i]</code>&nbsp;。</li>
</ul>

<p>请你返回所有 <strong>单调</strong>&nbsp;数组对的数目。</p>

<p>由于答案可能很大，请你将它对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [2,3,2]</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p>单调数组对包括：</p>

<ol>
	<li><code>([0, 1, 1], [2, 2, 1])</code></li>
	<li><code>([0, 1, 2], [2, 2, 0])</code></li>
	<li><code>([0, 2, 2], [2, 1, 0])</code></li>
	<li><code>([1, 2, 2], [1, 1, 0])</code></li>
</ol>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [5,5,5,5]</span></p>

<p><span class="example-io"><b>输出：</b>126</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 2000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划 + 前缀和优化

我们定义 $f[i][j]$ 表示下标 $[0,..i]$ 的单调数组对的数目，且 $arr1[i] = j$。初始时 $[i][j] = 0$，答案为 $\sum_{j=0}^{\textit{nums}[n-1]} f[n-1][j]$。

当 $i = 0$ 时，有 $[0][j] = 1$，其中 $0 \leq j \leq \textit{nums}[0]$。

当 $i > 0$ 时，我们可以根据 $f[i-1][j']$ 计算 $f[i][j]$。由于 $\textit{arr1}$ 是单调非递减的，因此 $j' \leq j$。又由于 $\textit{arr2}$ 是单调非递增的，因此 $\textit{nums}[i] - j \leq \textit{nums}[i - 1] - j'$。即 $j' \leq \min(j, j + \textit{nums}[i - 1] - \textit{nums}[i])$。

答案为 $\sum_{j=0}^{\textit{nums}[n-1]} f[n-1][j]$。

时间复杂度 $O(n \times m)$，空间复杂度 $O(n \times m)$。其中 $n$ 表示数组 $\textit{nums}$ 的长度，而 $m$ 表示数组 $\textit{nums}$ 中的最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countOfPairs(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        n, m = len(nums), max(nums)
        f = [[0] * (m + 1) for _ in range(n)]
        for j in range(nums[0] + 1):
            f[0][j] = 1
        for i in range(1, n):
            s = list(accumulate(f[i - 1]))
            for j in range(nums[i] + 1):
                k = min(j, j + nums[i - 1] - nums[i])
                if k >= 0:
                    f[i][j] = s[k] % mod
        return sum(f[-1][: nums[-1] + 1]) % mod
```

#### Java

```java
class Solution {
    public int countOfPairs(int[] nums) {
        final int mod = (int) 1e9 + 7;
        int n = nums.length;
        int m = Arrays.stream(nums).max().getAsInt();
        int[][] f = new int[n][m + 1];
        for (int j = 0; j <= nums[0]; ++j) {
            f[0][j] = 1;
        }
        int[] g = new int[m + 1];
        for (int i = 1; i < n; ++i) {
            g[0] = f[i - 1][0];
            for (int j = 1; j <= m; ++j) {
                g[j] = (g[j - 1] + f[i - 1][j]) % mod;
            }
            for (int j = 0; j <= nums[i]; ++j) {
                int k = Math.min(j, j + nums[i - 1] - nums[i]);
                if (k >= 0) {
                    f[i][j] = g[k];
                }
            }
        }
        int ans = 0;
        for (int j = 0; j <= nums[n - 1]; ++j) {
            ans = (ans + f[n - 1][j]) % mod;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countOfPairs(vector<int>& nums) {
        const int mod = 1e9 + 7;
        int n = nums.size();
        int m = *max_element(nums.begin(), nums.end());
        vector<vector<int>> f(n, vector<int>(m + 1));
        for (int j = 0; j <= nums[0]; ++j) {
            f[0][j] = 1;
        }
        vector<int> g(m + 1);
        for (int i = 1; i < n; ++i) {
            g[0] = f[i - 1][0];
            for (int j = 1; j <= m; ++j) {
                g[j] = (g[j - 1] + f[i - 1][j]) % mod;
            }
            for (int j = 0; j <= nums[i]; ++j) {
                int k = min(j, j + nums[i - 1] - nums[i]);
                if (k >= 0) {
                    f[i][j] = g[k];
                }
            }
        }
        int ans = 0;
        for (int j = 0; j <= nums[n - 1]; ++j) {
            ans = (ans + f[n - 1][j]) % mod;
        }
        return ans;
    }
};
```

#### Go

```go
func countOfPairs(nums []int) (ans int) {
	const mod int = 1e9 + 7
	n := len(nums)
	m := slices.Max(nums)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, m+1)
	}
	for j := 0; j <= nums[0]; j++ {
		f[0][j] = 1
	}
	g := make([]int, m+1)
	for i := 1; i < n; i++ {
		g[0] = f[i-1][0]
		for j := 1; j <= m; j++ {
			g[j] = (g[j-1] + f[i-1][j]) % mod
		}
		for j := 0; j <= nums[i]; j++ {
			k := min(j, j+nums[i-1]-nums[i])
			if k >= 0 {
				f[i][j] = g[k]
			}
		}
	}
	for j := 0; j <= nums[n-1]; j++ {
		ans = (ans + f[n-1][j]) % mod
	}
	return
}
```

#### TypeScript

```ts
function countOfPairs(nums: number[]): number {
    const mod = 1e9 + 7;
    const n = nums.length;
    const m = Math.max(...nums);
    const f: number[][] = Array.from({ length: n }, () => Array(m + 1).fill(0));
    for (let j = 0; j <= nums[0]; j++) {
        f[0][j] = 1;
    }
    const g: number[] = Array(m + 1).fill(0);
    for (let i = 1; i < n; i++) {
        g[0] = f[i - 1][0];
        for (let j = 1; j <= m; j++) {
            g[j] = (g[j - 1] + f[i - 1][j]) % mod;
        }
        for (let j = 0; j <= nums[i]; j++) {
            const k = Math.min(j, j + nums[i - 1] - nums[i]);
            if (k >= 0) {
                f[i][j] = g[k];
            }
        }
    }
    let ans = 0;
    for (let j = 0; j <= nums[n - 1]; j++) {
        ans = (ans + f[n - 1][j]) % mod;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
