---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2403.Minimum%20Time%20to%20Kill%20All%20Monsters/README.md
tags:
    - 位运算
    - 数组
    - 动态规划
    - 状态压缩
---

<!-- problem:start -->

# [2403. 杀死所有怪物的最短时间 🔒](https://leetcode.cn/problems/minimum-time-to-kill-all-monsters)

[English Version](/solution/2400-2499/2403.Minimum%20Time%20to%20Kill%20All%20Monsters/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你有一个整数数组 <code>power</code>，其中&nbsp; <code>power[i]</code> 是第 <code>i</code> 个怪物的力量。</p>

<p>你从 <code>0</code> 点法力值开始，每天获取&nbsp;<code>gain</code> 点法力值，最初 <code>gain</code> 等于 <code>1</code>。</p>

<p>每天，在获得 <code>gain</code>&nbsp;点法力值后，如果你的法力值大于或等于怪物的力量，你就可以打败怪物。当你打败怪物时:</p>

<ul>
	<li>
	<p data-group="1-1">你的法力值会被重置为 <code>0</code>，并且</p>
	</li>
	<li>
	<p data-group="1-1"><code>gain</code>&nbsp;的值增加 <code>1</code>。</p>
	</li>
</ul>

<p>返回<em>打败所有怪物所需的&nbsp;<strong>最少&nbsp;</strong>天数。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> power = [3,1,4]
<strong>输出:</strong> 4
<strong>解释:</strong> 打败所有怪物的最佳方法是:
- 第 1 天: 获得 1 点法力值，现在总共拥有 1 点法力值。用尽所有法力值击杀第 2 个怪物。
- 第 2 天: 获得 2 点法力值，现在总共拥有 2 点法力值。
- 第 3 天: 获得 2 点法力值，现在总共拥有 4 点法力值。用尽所有法力值击杀第 3 个怪物。
- 第 4 天: 获得 3 点法力值，现在总共拥有 3 点法力值。 用尽所有法力值击杀第 1 个怪物。
可以证明，4 天是最少需要的天数。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> power = [1,1,4]
<strong>输出:</strong> 4
<strong>解释:</strong> 打败所有怪物的最佳方法是:
- 第 1 天: 获得 1 点法力值，现在总共拥有 1 点法力值。用尽所有法力值击杀第 1 个怪物。
- 第 2 天: 获得 2 点法力值，现在总共拥有 2 点法力值。用尽所有法力值击杀第 2 个怪物。
- 第 3 天: 获得 3 点法力值，现在总共拥有 3 点法力值。
- 第 4 天: 获得 3 点法力值，现在总共拥有 6 点法力值。用尽所有法力值击杀第 3 个怪物。
可以证明，4 天是最少需要的天数。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> power = [1,2,4,9]
<strong>输出:</strong> 6
<strong>解释:</strong> 打败所有怪物的最佳方法是:
- 第 1 天: 获得 1 点法力值，现在总共拥有 1 点法力值。用尽所有法力值击杀第 1 个怪物
- 第 2 天: 获得 2 点法力值，现在总共拥有 2 点法力值。用尽所有法力值击杀第 2 个怪物。
- 第 3 天: 获得 3 点法力值，现在总共拥有 3 点法力值。
- 第 4 天: 获得 3 点法力值，现在总共拥有 6 点法力值。
- 第 5 天: 获得 3 点法力值，现在总共拥有 9 点法力值。用尽所有法力值击杀第 4 个怪物。
- 第 6 天: 获得 4 点法力值，现在总共拥有 4 点法力值。用尽所有法力值击杀第 3 个怪物。
可以证明，6 天是最少需要的天数。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= power.length &lt;= 17</code></li>
	<li><code>1 &lt;= power[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：状态压缩 + 记忆化搜索

我们注意带，怪物的数量最多为 $17$，这意味着我们可以使用一个 $17$ 位的二进制数来表示怪物的状态，其中第 $i$ 位为 $1$ 表示第 $i$ 个怪物还活着，为 $0$ 表示第 $i$ 个怪物已经被击败。

我们设计一个函数 $\textit{dfs}(\textit{mask})$，表示当前怪物的状态为 $\textit{mask}$ 时，打败所有怪物所需的最少天数。那么答案就是 $\textit{dfs}(2^n - 1)$，其中 $n$ 为怪物的数量。

函数 $\textit{dfs}(\textit{mask})$ 的计算方式如下：

-   如果 $\textit{mask} = 0$，表示所有怪物都已经被击败，返回 $0$；
-   否则，我们枚举每个怪物 $i$，如果第 $i$ 个怪物还活着，那么我们可以选择击败第 $i$ 个怪物，然后递归计算 $\textit{dfs}(\textit{mask} \oplus 2^i)$，并更新答案为 $\textit{ans} = \min(\textit{ans}, \textit{dfs}(\textit{mask} \oplus 2^i) + \lceil \frac{x}{\textit{gain}} \rceil)$，其中 $x$ 为第 $i$ 个怪物的力量，而 $\textit{gain} = 1 + (n - \textit{mask}.\textit{bitCount}())$，表示当前每天可以获得的法力值。

最后，我们返回 $\textit{dfs}(2^n - 1)$。

时间复杂度 $O(2^n \times n)$，空间复杂度 $O(2^n)$。其中 $n$ 为怪物的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumTime(self, power: List[int]) -> int:
        @cache
        def dfs(mask: int) -> int:
            if mask == 0:
                return 0
            ans = inf
            gain = 1 + (n - mask.bit_count())
            for i, x in enumerate(power):
                if mask >> i & 1:
                    ans = min(ans, dfs(mask ^ (1 << i)) + (x + gain - 1) // gain)
            return ans

        n = len(power)
        return dfs((1 << n) - 1)
```

#### Java

```java
class Solution {
    private int n;
    private int[] power;
    private Long[] f;

    public long minimumTime(int[] power) {
        n = power.length;
        this.power = power;
        f = new Long[1 << n];
        return dfs((1 << n) - 1);
    }

    private long dfs(int mask) {
        if (mask == 0) {
            return 0;
        }
        if (f[mask] != null) {
            return f[mask];
        }
        f[mask] = Long.MAX_VALUE;
        int gain = 1 + (n - Integer.bitCount(mask));
        for (int i = 0; i < n; ++i) {
            if ((mask >> i & 1) == 1) {
                f[mask] = Math.min(f[mask], dfs(mask ^ 1 << i) + (power[i] + gain - 1) / gain);
            }
        }
        return f[mask];
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minimumTime(vector<int>& power) {
        int n = power.size();
        long long f[1 << n];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int mask) -> long long {
            if (mask == 0) {
                return 0;
            }
            if (f[mask] != -1) {
                return f[mask];
            }
            f[mask] = LLONG_MAX;
            int gain = 1 + (n - __builtin_popcount(mask));
            for (int i = 0; i < n; ++i) {
                if (mask >> i & 1) {
                    f[mask] = min(f[mask], dfs(mask ^ (1 << i)) + (power[i] + gain - 1) / gain);
                }
            }
            return f[mask];
        };
        return dfs((1 << n) - 1);
    }
};
```

#### Go

```go
func minimumTime(power []int) int64 {
	n := len(power)
	f := make([]int64, 1<<n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(mask int) int64
	dfs = func(mask int) int64 {
		if mask == 0 {
			return 0
		}
		if f[mask] != -1 {
			return f[mask]
		}
		f[mask] = 1e18
		gain := 1 + (n - bits.OnesCount(uint(mask)))
		for i, x := range power {
			if mask>>i&1 == 1 {
				f[mask] = min(f[mask], dfs(mask^(1<<i))+int64(x+gain-1)/int64(gain))
			}
		}
		return f[mask]
	}
	return dfs(1<<n - 1)
}
```

#### TypeScript

```ts
function minimumTime(power: number[]): number {
    const n = power.length;
    const f: number[] = Array(1 << n).fill(-1);
    const dfs = (mask: number): number => {
        if (mask === 0) {
            return 0;
        }
        if (f[mask] !== -1) {
            return f[mask];
        }
        f[mask] = Infinity;
        const gain = 1 + (n - bitCount(mask));
        for (let i = 0; i < n; ++i) {
            if ((mask >> i) & 1) {
                f[mask] = Math.min(f[mask], dfs(mask ^ (1 << i)) + Math.ceil(power[i] / gain));
            }
        }
        return f[mask];
    };
    return dfs((1 << n) - 1);
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：状态压缩 + 动态规划

我们可以将方法一中的记忆化搜索改为动态规划，定义 $f[\textit{mask}]$ 表示当前怪物的状态为 $\textit{mask}$ 时，打败所有怪物所需的最少天数。其中 $\textit{mask}$ 是一个 $n$ 位的二进制数，其中第 $i$ 位为 $1$ 表示第 $i$ 个怪物已被击败，为 $0$ 表示第 $i$ 个怪物还活着。初始时 $f[0] = 0$，其余 $f[\textit{mask}] = +\infty$。答案即为 $f[2^n - 1]$。

我们在 $[1, 2^n - 1]$ 的范围内枚举 $\textit{mask}$，对于每个 $\textit{mask}$，我们枚举每个怪物 $i$，如果第 $i$ 个怪物被击败，那么它可以从上一个状态 $\textit{mask} \oplus 2^i$ 转移过来，转移的代价为 $(\textit{power}[i] + \textit{gain} - 1) / \textit{gain}$，其中 $\textit{gain} = \textit{mask}.\textit{bitCount}()$。

最后，返回 $f[2^n - 1]$。

时间复杂度 $O(2^n \times n)$，空间复杂度 $O(2^n)$。其中 $n$ 为怪物的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumTime(self, power: List[int]) -> int:
        n = len(power)
        f = [inf] * (1 << n)
        f[0] = 0
        for mask in range(1, 1 << n):
            gain = mask.bit_count()
            for i, x in enumerate(power):
                if mask >> i & 1:
                    f[mask] = min(f[mask], f[mask ^ (1 << i)] + (x + gain - 1) // gain)
        return f[-1]
```

#### Java

```java
class Solution {
    public long minimumTime(int[] power) {
        int n = power.length;
        long[] f = new long[1 << n];
        Arrays.fill(f, Long.MAX_VALUE);
        f[0] = 0;
        for (int mask = 1; mask < 1 << n; ++mask) {
            int gain = Integer.bitCount(mask);
            for (int i = 0; i < n; ++i) {
                if ((mask >> i & 1) == 1) {
                    f[mask] = Math.min(f[mask], f[mask ^ 1 << i] + (power[i] + gain - 1) / gain);
                }
            }
        }
        return f[(1 << n) - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minimumTime(vector<int>& power) {
        int n = power.size();
        long long f[1 << n];
        memset(f, 0x3f, sizeof(f));
        f[0] = 0;
        for (int mask = 1; mask < 1 << n; ++mask) {
            int gain = __builtin_popcount(mask);
            for (int i = 0; i < n; ++i) {
                if (mask >> i & 1) {
                    f[mask] = min(f[mask], f[mask ^ (1 << i)] + (power[i] + gain - 1) / gain);
                }
            }
        }
        return f[(1 << n) - 1];
    }
};
```

#### Go

```go
func minimumTime(power []int) int64 {
	n := len(power)
	f := make([]int64, 1<<n)
	for i := range f {
		f[i] = 1e18
	}
	f[0] = 0
	for mask := 1; mask < 1<<n; mask++ {
		gain := bits.OnesCount(uint(mask))
		for i, x := range power {
			if mask>>i&1 == 1 {
				f[mask] = min(f[mask], f[mask^(1<<i)]+int64(x+gain-1)/int64(gain))
			}
		}
	}
	return f[1<<n-1]
}
```

#### TypeScript

```ts
function minimumTime(power: number[]): number {
    const n = power.length;
    const f: number[] = Array(1 << n).fill(Infinity);
    f[0] = 0;
    for (let mask = 1; mask < 1 << n; ++mask) {
        const gain = bitCount(mask);
        for (let i = 0; i < n; ++i) {
            if ((mask >> i) & 1) {
                f[mask] = Math.min(f[mask], f[mask ^ (1 << i)] + Math.ceil(power[i] / gain));
            }
        }
    }
    return f.at(-1)!;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
