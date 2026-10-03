---
comments: true
difficulty: 困难
rating: 2019
source: 第 329 场周赛 Q4
tags:
    - 数组
    - 哈希表
    - 动态规划
    - 计数
---

<!-- problem:start -->

# [2547. 拆分数组的最小代价](https://leetcode.cn/problems/minimum-cost-to-split-an-array)

[English Version](/solution/2500-2599/2547.Minimum%20Cost%20to%20Split%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> 。</p>

<p>将数组拆分成一些非空子数组。拆分的 <strong>代价</strong> 是每个子数组中的 <strong>重要性</strong> 之和。</p>

<p>令 <code>trimmed(subarray)</code> 作为子数组的一个特征，其中所有仅出现一次的数字将会被移除。</p>

<ul>
	<li>例如，<code>trimmed([3,1,2,4,3,4]) = [3,4,3,4]</code> 。</li>
</ul>

<p>子数组的 <strong>重要性</strong> 定义为 <code>k + trimmed(subarray).length</code> 。</p>

<ul>
	<li>例如，如果一个子数组是 <code>[1,2,3,3,3,4,4]</code> ，<code>trimmed([1,2,3,3,3,4,4]) = [3,3,3,4,4]</code> 。这个子数组的重要性就是 <code>k + 5</code> 。</li>
</ul>

<p>找出并返回拆分 <code>nums</code> 的所有可行方案中的最小代价。</p>

<p><strong>子数组</strong> 是数组的一个连续 <strong>非空</strong> 元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1,2,1,3,3], k = 2
<strong>输出：</strong>8
<strong>解释：</strong>将 nums 拆分成两个子数组：[1,2], [1,2,1,3,3]
[1,2] 的重要性是 2 + (0) = 2 。
[1,2,1,3,3] 的重要性是 2 + (2 + 2) = 6 。
拆分的代价是 2 + 6 = 8 ，可以证明这是所有可行的拆分方案中的最小代价。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1,2,1], k = 2
<strong>输出：</strong>6
<strong>解释：</strong>将 nums 拆分成两个子数组：[1,2], [1,2,1] 。
[1,2] 的重要性是 2 + (0) = 2 。
[1,2,1] 的重要性是 2 + (2) = 4 。
拆分的代价是 2 + 4 = 6 ，可以证明这是所有可行的拆分方案中的最小代价。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1,2,1], k = 5
<strong>输出：</strong>10
<strong>解释：</strong>将 nums 拆分成一个子数组：[1,2,1,2,1].
[1,2,1,2,1] 的重要性是 5 + (3 + 2) = 10 。
拆分的代价是 10 ，可以证明这是所有可行的拆分方案中的最小代价。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt; nums.length</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

<!-- thinking:start -->

> **思考**
>
> 把数组切成若干段，每段代价为 $k$ 加上其中出现超过一次的元素个数（即长度减去只出现一次的个数）。 $n\le 10^3$，枚举全部切法过多，但切点只有 $n$ 个。
>
> 令 $\textit{dfs}(i)$ 为从 $i$ 切到末尾的最小代价。枚举本段右端 $j$，用计数器维护“出现恰好一次”的个数 $\textit{one}$，本段代价为 $k+(j-i+1)-\textit{one}$，再加上 $\textit{dfs}(j+1)$。记忆化后状态 $O(n)$、转移 $O(n)$。

<!-- thinking:end -->

我们设计一个函数 $dfs(i)$，表示从下标 $i$ 开始拆分的最小代价。那么答案就是 $dfs(0)$。

函数 $dfs(i)$ 的计算过程如下：

- 如果 $i \ge n$，说明已经拆分到了数组末尾，此时返回 $0$。
- 否则，我们枚举子数组的末尾 $j$，过程中用一个数组或哈希表 `cnt` 统计子数组中每个数字出现的次数，用一个变量 `one` 统计子数组中出现次数为 $1$ 的数字的个数。那么子数组的重要性就是 $k + j - i + 1 - one$，拆分的代价就是 $k + j - i + 1 - one + dfs(j + 1)$。我们枚举所有的 $j$，取其中的最小值作为 $dfs(i)$ 的返回值。

过程中，我们可以使用记忆化搜索，即使用一个数组 $f$ 记忆化函数 $dfs(i)$ 的返回值，避免重复计算。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(self, nums: List[int], k: int) -> int:
        @cache
        def dfs(i):
            if i >= n:
                return 0
            cnt = Counter()
            one = 0
            ans = inf
            for j in range(i, n):
                cnt[nums[j]] += 1
                if cnt[nums[j]] == 1:
                    one += 1
                elif cnt[nums[j]] == 2:
                    one -= 1
                ans = min(ans, k + j - i + 1 - one + dfs(j + 1))
            return ans

        n = len(nums)
        return dfs(0)
```

#### Java

```java
class Solution {
    private Integer[] f;
    private int[] nums;
    private int n, k;

    public int minCost(int[] nums, int k) {
        n = nums.length;
        this.k = k;
        this.nums = nums;
        f = new Integer[n];
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int[] cnt = new int[n];
        int one = 0;
        int ans = 1 << 30;
        for (int j = i; j < n; ++j) {
            int x = ++cnt[nums[j]];
            if (x == 1) {
                ++one;
            } else if (x == 2) {
                --one;
            }
            ans = Math.min(ans, k + j - i + 1 - one + dfs(j + 1));
        }
        return f[i] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCost(vector<int>& nums, int k) {
        int n = nums.size();
        int f[n];
        memset(f, 0, sizeof f);
        function<int(int)> dfs = [&](int i) {
            if (i >= n) {
                return 0;
            }
            if (f[i]) {
                return f[i];
            }
            int cnt[n];
            memset(cnt, 0, sizeof cnt);
            int one = 0;
            int ans = 1 << 30;
            for (int j = i; j < n; ++j) {
                int x = ++cnt[nums[j]];
                if (x == 1) {
                    ++one;
                } else if (x == 2) {
                    --one;
                }
                ans = min(ans, k + j - i + 1 - one + dfs(j + 1));
            }
            return f[i] = ans;
        };
        return dfs(0);
    }
};
```

#### Go

```go
func minCost(nums []int, k int) int {
	n := len(nums)
	f := make([]int, n)
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] > 0 {
			return f[i]
		}
		ans, one := 1<<30, 0
		cnt := make([]int, n)
		for j := i; j < n; j++ {
			cnt[nums[j]]++
			x := cnt[nums[j]]
			if x == 1 {
				one++
			} else if x == 2 {
				one--
			}
			ans = min(ans, k+j-i+1-one+dfs(j+1))
		}
		f[i] = ans
		return ans
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function minCost(nums: number[], k: number): number {
    const n = nums.length;
    const f = new Array(n).fill(0);
    const dfs = (i: number) => {
        if (i >= n) {
            return 0;
        }
        if (f[i]) {
            return f[i];
        }
        const cnt = new Array(n).fill(0);
        let one = 0;
        let ans = 1 << 30;
        for (let j = i; j < n; ++j) {
            const x = ++cnt[nums[j]];
            if (x == 1) {
                ++one;
            } else if (x == 2) {
                --one;
            }
            ans = Math.min(ans, k + j - i + 1 - one + dfs(j + 1));
        }
        f[i] = ans;
        return f[i];
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划

<!-- thinking:start -->

> **思考**
>
> 枚举全部切法是指数级的。数组长度可以到 $1000$，而本段右端从当前位置开始试，第一次总会先算紧邻后缀，递归深度就是 $n$。从下标 $i$ 起的最小代价只依赖更靠右的答案。于是令 $f[i]$ 为从 $i$ 拆到末尾的最小代价，$f[n]=0$，从右往左枚举右端点 $j$，用计数维护恰好出现一次的个数 $\textit{one}$，以 $k+(j-i+1)-\textit{one}+f[j+1]$ 更新。

<!-- thinking:end -->

设 $f[i]$ 为从下标 $i$ 开始拆分的最小代价，边界 $f[n]=0$。从 $i=n-1$ 递减到 $0$，枚举本段右端 $j$。用数组 $\textit{cnt}$ 统计 $nums[i..j]$ 里每个值的出现次数，用 $\textit{one}$ 统计恰好出现一次的值的个数。这一段的重要性是 $k + j - i + 1 - \textit{one}$，再加上后缀代价 $f[j+1]$，$f[i]$ 取所有右端点中的最小值。答案为 $f[0]$。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(self, nums: List[int], k: int) -> int:
        n = len(nums)
        f = [0] * (n + 1)
        for i in range(n - 1, -1, -1):
            cnt = Counter()
            one = 0
            ans = inf
            for j in range(i, n):
                cnt[nums[j]] += 1
                if cnt[nums[j]] == 1:
                    one += 1
                elif cnt[nums[j]] == 2:
                    one -= 1
                ans = min(ans, k + j - i + 1 - one + f[j + 1])
            f[i] = ans
        return f[0]
```

#### Java

```java
class Solution {
    public int minCost(int[] nums, int k) {
        int n = nums.length;
        int[] f = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            int[] cnt = new int[n];
            int one = 0;
            long ans = Long.MAX_VALUE;
            for (int j = i; j < n; ++j) {
                int x = ++cnt[nums[j]];
                if (x == 1) {
                    ++one;
                } else if (x == 2) {
                    --one;
                }
                ans = Math.min(ans, (long) k + j - i + 1 - one + f[j + 1]);
            }
            f[i] = (int) ans;
        }
        return f[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCost(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> f(n + 1);
        for (int i = n - 1; i >= 0; --i) {
            vector<int> cnt(n);
            int one = 0;
            long long ans = 1LL << 60;
            for (int j = i; j < n; ++j) {
                int x = ++cnt[nums[j]];
                if (x == 1) {
                    ++one;
                } else if (x == 2) {
                    --one;
                }
                ans = min(ans, (long long) k + j - i + 1 - one + f[j + 1]);
            }
            f[i] = (int) ans;
        }
        return f[0];
    }
};
```

#### Go

```go
func minCost(nums []int, k int) int {
	n := len(nums)
	f := make([]int, n+1)
	for i := n - 1; i >= 0; i-- {
		cnt := make([]int, n)
		one := 0
		ans := k + n + 1
		for j := i; j < n; j++ {
			cnt[nums[j]]++
			x := cnt[nums[j]]
			if x == 1 {
				one++
			} else if x == 2 {
				one--
			}
			ans = min(ans, k+j-i+1-one+f[j+1])
		}
		f[i] = ans
	}
	return f[0]
}
```

#### TypeScript

```ts
function minCost(nums: number[], k: number): number {
    const n = nums.length;
    const f: number[] = Array(n + 1).fill(0);
    for (let i = n - 1; i >= 0; --i) {
        const cnt: number[] = Array(n).fill(0);
        let one = 0;
        let ans = Infinity;
        for (let j = i; j < n; ++j) {
            const x = ++cnt[nums[j]];
            if (x == 1) {
                ++one;
            } else if (x == 2) {
                --one;
            }
            ans = Math.min(ans, k + j - i + 1 - one + f[j + 1]);
        }
        f[i] = ans;
    }
    return f[0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
