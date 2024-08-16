---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3082.Find%20the%20Sum%20of%20the%20Power%20of%20All%20Subsequences/README.md
rating: 2241
source: 第 126 场双周赛 Q4
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [3082. 求出所有子序列的能量和](https://leetcode.cn/problems/find-the-sum-of-the-power-of-all-subsequences)

[English Version](/solution/3000-3099/3082.Find%20the%20Sum%20of%20the%20Power%20of%20All%20Subsequences/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;和一个 <strong>正</strong>&nbsp;整数&nbsp;<code>k</code>&nbsp;。</p>

<p>一个整数数组的 <strong>能量</strong>&nbsp;定义为和 <strong>等于</strong>&nbsp;<code>k</code>&nbsp;的子序列的数目。</p>

<p>请你返回 <code>nums</code>&nbsp;中所有子序列的 <strong>能量和</strong>&nbsp;。</p>

<p>由于答案可能很大，请你将它对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> nums = [1,2,3], k = 3 </span></p>

<p><strong>输出：</strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> 6 </span></p>

<p><strong>解释：</strong></p>

<p>总共有&nbsp;<code>5</code>&nbsp;个能量不为 0 的子序列：</p>

<ul>
	<li>子序列&nbsp;<code>[<u><em><strong>1</strong></em></u>,<u><em><strong>2</strong></em></u>,<u><em><strong>3</strong></em></u>]</code> 有&nbsp;<code>2</code>&nbsp;个和为&nbsp;<code>3</code>&nbsp;的子序列：<code>[1,2,<u><strong><em>3</em></strong></u>]</code> 和 <code>[<u><strong><em>1</em></strong></u>,<u><strong><em>2</em></strong></u>,3]</code>&nbsp;。</li>
	<li>子序列&nbsp;<code>[<u><em><strong>1</strong></em></u>,2,<u><em><strong>3</strong></em></u>]</code>&nbsp;有 <code>1</code>&nbsp;个和为&nbsp;<code>3</code>&nbsp;的子序列：<code>[1,2,<u><strong><em>3</em></strong></u>]</code>&nbsp;。</li>
	<li>子序列&nbsp;<code>[1,<u><em><strong>2</strong></em></u>,<u><em><strong>3</strong></em></u>]</code> 有&nbsp;<code>1</code>&nbsp;个和为&nbsp;<code>3</code>&nbsp;的子序列：<code>[1,2,<u><strong><em>3</em></strong></u>]</code>&nbsp;。</li>
	<li>子序列&nbsp;<code>[<u><em><strong>1</strong></em></u>,<u><em><strong>2</strong></em></u>,3]</code>&nbsp;有&nbsp;<code>1</code>&nbsp;个和为&nbsp;<code>3</code>&nbsp;的子序列：<code>[<u><strong><em>1</em></strong></u>,<u><strong><em>2</em></strong></u>,3]</code>&nbsp;。</li>
	<li>子序列&nbsp;<code>[1,2,<u><em><strong>3</strong></em></u>]</code>&nbsp;有&nbsp;<code>1</code>&nbsp;个和为&nbsp;<code>3</code>&nbsp;的子序列：<code>[1,2,<u><strong><em>3</em></strong></u>]</code>&nbsp;。</li>
</ul>

<p>所以答案为&nbsp;<code>2 + 1 + 1 + 1 + 1 = 6</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> nums = [2,3,3], k = 5 </span></p>

<p><strong>输出：</strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> 4 </span></p>

<p><strong>解释：</strong></p>

<p>总共有&nbsp;<code>3</code>&nbsp;个能量不为 0 的子序列：</p>

<ul>
	<li>子序列&nbsp;<code>[<u><em><strong>2</strong></em></u>,<u><em><strong>3</strong></em></u>,<u><em><strong>3</strong></em></u>]</code>&nbsp;有 2 个子序列和为&nbsp;<code>5</code>&nbsp;：<code>[<u><strong><em>2</em></strong></u>,3,<u><strong><em>3</em></strong></u>]</code> 和&nbsp;<code>[<u><strong><em>2</em></strong></u>,<u><strong><em>3</em></strong></u>,3]</code>&nbsp;。</li>
	<li>子序列&nbsp;<code>[<u><em><strong>2</strong></em></u>,3,<u><em><strong>3</strong></em></u>]</code>&nbsp;有 1 个子序列和为&nbsp;<code>5</code>&nbsp;：<code>[<u><strong><em>2</em></strong></u>,3,<u><strong><em>3</em></strong></u>]</code>&nbsp;。</li>
	<li>子序列&nbsp;<code>[<u><em><strong>2</strong></em></u>,<u><em><strong>3</strong></em></u>,3]</code>&nbsp;有 1 个子序列和为 <code>5</code>&nbsp;：<code>[<u><strong><em>2</em></strong></u>,<u><strong><em>3</em></strong></u>,3]</code>&nbsp;。</li>
</ul>

<p>所以答案为&nbsp;<code>2 + 1 + 1 = 4</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> nums = [1,2,3], k = 7 </span></p>

<p><strong>输出：</strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> 0 </span></p>

<p><strong>解释：</strong>不存在和为 <code>7</code>&nbsp;的子序列，所以 <code>nums</code>&nbsp;的能量和为&nbsp;<code>0</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

题目需要我们在给定数组 $\textit{nums}$ 中找到所有子序列 $\textit{S}$，然后计算每个 $\textit{S}$ 的每个子序列 $\textit{T}$ 的和等于 $\textit{k}$ 的方案数。

我们定义 $f[i][j]$ 表示前 $i$ 个数构成的若干个子序列中，每个子序列的子序列和等于 $j$ 的方案数。初始时 $f[0][0] = 1$，其余位置均为 $0$。

对于第 $i$ 个数 $x$，有以下三种情况：

1. 不在子序列 $\textit{S}$ 中，此时 $f[i][j] = f[i-1][j]$；
1. 在子序列 $\textit{S}$，但不在子序列 $\textit{T}$ 中，此时 $f[i][j] = f[i-1][j]$；
1. 在子序列 $\textit{S}$，且在子序列 $\textit{T}$ 中，此时 $f[i][j] = f[i-1][j-x]$。

综上，状态转移方程为：

$$
f[i][j] = f[i-1][j] \times 2 + f[i-1][j-x]
$$

最终答案为 $f[n][k]$。

时间复杂度 $O(n \times k)$，空间复杂度 $O(n \times k)$。其中 $n$ 为数组 $\textit{nums}$ 的长度，而 $k$ 为给定的正整数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumOfPower(self, nums: List[int], k: int) -> int:
        mod = 10**9 + 7
        n = len(nums)
        f = [[0] * (k + 1) for _ in range(n + 1)]
        f[0][0] = 1
        for i, x in enumerate(nums, 1):
            for j in range(k + 1):
                f[i][j] = f[i - 1][j] * 2 % mod
                if j >= x:
                    f[i][j] = (f[i][j] + f[i - 1][j - x]) % mod
        return f[n][k]
```

#### Java

```java
class Solution {
    public int sumOfPower(int[] nums, int k) {
        final int mod = (int) 1e9 + 7;
        int n = nums.length;
        int[][] f = new int[n + 1][k + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                f[i][j] = (f[i - 1][j] * 2) % mod;
                if (j >= nums[i - 1]) {
                    f[i][j] = (f[i][j] + f[i - 1][j - nums[i - 1]]) % mod;
                }
            }
        }
        return f[n][k];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int sumOfPower(vector<int>& nums, int k) {
        const int mod = 1e9 + 7;
        int n = nums.size();
        int f[n + 1][k + 1];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                f[i][j] = (f[i - 1][j] * 2) % mod;
                if (j >= nums[i - 1]) {
                    f[i][j] = (f[i][j] + f[i - 1][j - nums[i - 1]]) % mod;
                }
            }
        }
        return f[n][k];
    }
};
```

#### Go

```go
func sumOfPower(nums []int, k int) int {
	const mod int = 1e9 + 7
	n := len(nums)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	f[0][0] = 1
	for i := 1; i <= n; i++ {
		for j := 0; j <= k; j++ {
			f[i][j] = (f[i-1][j] * 2) % mod
			if j >= nums[i-1] {
				f[i][j] = (f[i][j] + f[i-1][j-nums[i-1]]) % mod
			}
		}
	}
	return f[n][k]
}
```

#### TypeScript

```ts
function sumOfPower(nums: number[], k: number): number {
    const mod = 10 ** 9 + 7;
    const n = nums.length;
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(0));
    f[0][0] = 1;
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j <= k; ++j) {
            f[i][j] = (f[i - 1][j] * 2) % mod;
            if (j >= nums[i - 1]) {
                f[i][j] = (f[i][j] + f[i - 1][j - nums[i - 1]]) % mod;
            }
        }
    }
    return f[n][k];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划（优化）

方法一中的状态转移方程中，$f[i][j]$ 的值只与 $f[i-1][j]$ 和 $f[i-1][j-x]$ 有关，因此我们可以优化第一维空间，从而将空间复杂度优化为 $O(k)$。

时间复杂度 $O(n \times k)$，空间复杂度 $O(k)$。其中 $n$ 为数组 $\textit{nums}$ 的长度，而 $k$ 为给定的正整数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumOfPower(self, nums: List[int], k: int) -> int:
        mod = 10**9 + 7
        f = [1] + [0] * k
        for x in nums:
            for j in range(k, -1, -1):
                f[j] = (f[j] * 2 + (0 if j < x else f[j - x])) % mod
        return f[k]
```

#### Java

```java
class Solution {
    public int sumOfPower(int[] nums, int k) {
        final int mod = (int) 1e9 + 7;
        int[] f = new int[k + 1];
        f[0] = 1;
        for (int x : nums) {
            for (int j = k; j >= 0; --j) {
                f[j] = (f[j] * 2 % mod + (j >= x ? f[j - x] : 0)) % mod;
            }
        }
        return f[k];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int sumOfPower(vector<int>& nums, int k) {
        const int mod = 1e9 + 7;
        int f[k + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (int x : nums) {
            for (int j = k; j >= 0; --j) {
                f[j] = (f[j] * 2 % mod + (j >= x ? f[j - x] : 0)) % mod;
            }
        }
        return f[k];
    }
};
```

#### Go

```go
func sumOfPower(nums []int, k int) int {
	const mod int = 1e9 + 7
	f := make([]int, k+1)
	f[0] = 1
	for _, x := range nums {
		for j := k; j >= 0; j-- {
			f[j] = f[j] * 2 % mod
			if j >= x {
				f[j] = (f[j] + f[j-x]) % mod
			}
		}
	}
	return f[k]
}
```

#### TypeScript

```ts
function sumOfPower(nums: number[], k: number): number {
    const mod = 10 ** 9 + 7;
    const f: number[] = Array(k + 1).fill(0);
    f[0] = 1;
    for (const x of nums) {
        for (let j = k; ~j; --j) {
            f[j] = (f[j] * 2) % mod;
            if (j >= x) {
                f[j] = (f[j] + f[j - x]) % mod;
            }
        }
    }
    return f[k];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
