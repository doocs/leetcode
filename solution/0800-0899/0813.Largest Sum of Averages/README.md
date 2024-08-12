---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0813.Largest%20Sum%20of%20Averages/README.md
tags:
    - 数组
    - 动态规划
    - 前缀和
---

<!-- problem:start -->

# [813. 最大平均值和的分组](https://leetcode.cn/problems/largest-sum-of-averages)

[English Version](/solution/0800-0899/0813.Largest%20Sum%20of%20Averages/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。我们将给定的数组&nbsp;<code>nums</code>&nbsp;分成 <strong>最多</strong>&nbsp;<code>k</code>&nbsp;个非空子数组，且数组内部是连续的&nbsp;。&nbsp;<strong>分数</strong> 由每个子数组内的平均值的总和构成。</p>

<p>注意我们必须使用 <code>nums</code> 数组中的每一个数进行分组，并且分数不一定需要是整数。</p>

<p>返回我们所能得到的最大 <strong>分数</strong> 是多少。答案误差在&nbsp;<code>10<sup>-6</sup></code>&nbsp;内被视为是正确的。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [9,1,2,3,9], k = 3
<strong>输出:</strong> 20.00000
<strong>解释:</strong>
nums 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 + 3) / 3 + 9 = 20.
我们也可以把 nums 分成[9, 1], [2], [3, 9].
这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值.
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,3,4,5,6,7], k = 4
<strong>输出:</strong> 20.50000
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和 + 记忆化搜索

我们可以先预处理得到前缀和数组 $s$，方便快速得到子数组的和。

接下来，我们设计一个函数 $\textit{dfs}(i, k)$，表示从数组下标 $i$ 开始，最多分成 $k$ 组的最大平均值和。答案为 $\textit{dfs}(0, k)$。

函数 $\textit{dfs}(i, k)$ 的执行逻辑如下：

当 $i = n$ 时，表示已经遍历到数组末尾，此时返回 $0$。

当 $k = 1$ 时，表示只剩下一组，此时返回从下标 $i$ 开始到数组末尾的平均值。

否则，我们在 $[i + 1, n)$ 的区间内枚举下一个分组的开始位置 $j$，计算从 $i$ 到 $j - 1$ 的平均值 $\frac{s[j] - s[i]}{j - i}$，加上 $\textit{dfs}(j, k - 1)$ 的结果，取所有结果的最大值。

时间复杂度 $O(n^2 \times k)$，空间复杂度 $O(n \times k)$。其中 $n$ 表示数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestSumOfAverages(self, nums: List[int], k: int) -> float:
        @cache
        def dfs(i: int, k: int) -> float:
            if i == n:
                return 0
            if k == 1:
                return (s[n] - s[i]) / (n - i)
            ans = 0
            for j in range(i + 1, n):
                ans = max(ans, (s[j] - s[i]) / (j - i) + dfs(j, k - 1))
            return ans

        n = len(nums)
        s = list(accumulate(nums, initial=0))
        return dfs(0, k)
```

#### Java

```java
class Solution {
    private Double[][] f;
    private int[] s;
    private int n;

    public double largestSumOfAverages(int[] nums, int k) {
        n = nums.length;
        s = new int[n + 1];
        f = new Double[n][k + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        return dfs(0, k);
    }

    private double dfs(int i, int k) {
        if (i == n) {
            return 0;
        }
        if (k == 1) {
            return (s[n] - s[i]) * 1.0 / (n - i);
        }
        if (f[i][k] != null) {
            return f[i][k];
        }
        double ans = 0;
        for (int j = i + 1; j < n; ++j) {
            ans = Math.max(ans, (s[j] - s[i]) * 1.0 /(j - i) + dfs(j, k - 1));
        }
        return f[i][k] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    double largestSumOfAverages(vector<int>& nums, int k) {
        int n = nums.size();
        int s[n + 1];
        double f[n][k + 1];
        memset(f, 0, sizeof(f));
        s[0] = 0;
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        auto dfs = [&](auto&& dfs, int i, int k) -> double {
            if (i == n) {
                return 0;
            }
            if (k == 1) {
                return (s[n] - s[i]) * 1.0 / (n - i);
            }
            if (f[i][k] > 0) {
                return f[i][k];
            }
            double ans = 0;
            for (int j = i + 1; j < n; ++j) {
                ans = max(ans, (s[j] - s[i]) * 1.0 / (j - i) + dfs(dfs, j, k - 1));
            }
            return f[i][k] = ans;
        };
        return dfs(dfs, 0, k);
    }
};
```

#### Go

```go
func largestSumOfAverages(nums []int, k int) float64 {
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	f := make([][]float64, n)
	for i := range f {
		f[i] = make([]float64, k+1)
	}
	var dfs func(int, int) float64
	dfs = func(i, k int) float64 {
		if i == n {
			return 0
		}
		if f[i][k] > 0 {
			return f[i][k]
		}
		if k == 1 {
			return float64(s[n]-s[i]) / float64(n-i)
		}
		ans := 0.0
		for j := i + 1; j < n; j++ {
			ans = math.Max(ans, float64(s[j]-s[i])/float64(j-i)+dfs(j, k-1))
		}
		f[i][k] = ans
		return ans
	}
	return dfs(0, k)
}
```

#### TypeScript

```ts
function largestSumOfAverages(nums: number[], k: number): number {
    const n = nums.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; i++) {
        s[i + 1] = s[i] + nums[i];
    }
    const f: number[][] = Array.from({ length: n }, () => Array(k + 1).fill(0));
    const dfs = (i: number, k: number): number => {
        if (i === n) {
            return 0;
        }
        if (f[i][k] > 0) {
            return f[i][k];
        }
        if (k === 1) {
            return (s[n] - s[i]) / (n - i);
        }
        for (let j = i + 1; j < n; j++) {
            f[i][k] = Math.max(f[i][k], dfs(j, k - 1) + (s[j] - s[i]) / (j - i));
        }
        return f[i][k];
    };
    return dfs(0, k);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划

我们可以将方法一的记忆化搜索转化为动态规划。

定义 $f[i][j]$ 表示数组 $\textit{nums}$ 的前 $i$ 个元素最多分成 $j$ 组的最大平均值和。答案为 $f[n][k]$。

对于 $f[i][j]$，我们可以枚举上一组的结束位置 $h$，计算 $f[h][j-1]$，加上 $\frac{s[i]-s[h]}{i-h}$ 的结果，取所有结果的最大值。

时间复杂度 $O(n^2 \times k)$，空间复杂度 $O(n \times k)$。其中 $n$ 表示数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestSumOfAverages(self, nums: List[int], k: int) -> float:
        n = len(nums)
        f = [[0] * (k + 1) for _ in range(n + 1)]
        s = list(accumulate(nums, initial=0))
        for i in range(1, n + 1):
            f[i][1] = s[i] / i
            for j in range(2, min(i + 1, k + 1)):
                for h in range(i):
                    f[i][j] = max(f[i][j], f[h][j - 1] + (s[i] - s[h]) / (i - h))
        return f[n][k]
```

#### Java

```java
class Solution {
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[][] f = new double[n + 1][k + 1];
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        for (int i = 1; i <= n; ++i) {
            f[i][1] = s[i] * 1.0 / i;
            for (int j = 2; j <= Math.min(i, k); ++j) {
                for (int h = 0; h < i; ++h) {
                    f[i][j] = Math.max(f[i][j], f[h][j - 1] + (s[i] - s[h]) * 1.0 / (i - h));
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
    double largestSumOfAverages(vector<int>& nums, int k) {
        int n = nums.size();
        int s[n + 1];
        s[0] = 0;
        double f[n + 1][k + 1];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        for (int i = 1; i <= n; ++i) {
            f[i][1] = s[i] * 1.0 / i;
            for (int j = 2; j <= min(i, k); ++j) {
                for (int h = 0; h < i; ++h) {
                    f[i][j] = max(f[i][j], f[h][j - 1] + (s[i] - s[h]) * 1.0 / (i - h));
                }
            }
        }
        return f[n][k];
    }
};
```

#### Go

```go
func largestSumOfAverages(nums []int, k int) float64 {
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	f := make([][]float64, n+1)
	for i := range f {
		f[i] = make([]float64, k+1)
	}
	for i := 1; i <= n; i++ {
		f[i][1] = float64(s[i]) / float64(i)
		for j := 2; j <= min(i, k); j++ {
			for h := 0; h < i; h++ {
				f[i][j] = max(f[i][j], f[h][j-1]+float64(s[i]-s[h])/float64(i-h))
			}
		}
	}
	return f[n][k]
}
```

#### TypeScript

```ts
function largestSumOfAverages(nums: number[], k: number): number {
    const n = nums.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; i++) {
        s[i + 1] = s[i] + nums[i];
    }
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(0));
    for (let i = 1; i <= n; ++i) {
        f[i][1] = s[i] / i;
        for (let j = 2; j <= Math.min(i, k); ++j) {
            for (let h = 0; h < i; ++h) {
                f[i][j] = Math.max(f[i][j], f[h][j - 1] + (s[i] - s[h]) / (i - h));
            }
        }
    }
    return f[n][k];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
