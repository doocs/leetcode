---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2992.Number%20of%20Self-Divisible%20Permutations/README.md
tags:
    - 位运算
    - 数组
    - 动态规划
    - 回溯
    - 状态压缩
---

<!-- problem:start -->

# [2992. 自整除排列的数量 🔒](https://leetcode.cn/problems/number-of-self-divisible-permutations)

[English Version](/solution/2900-2999/2992.Number%20of%20Self-Divisible%20Permutations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数 <code>n</code>，返回 <strong>下标从 1 开始</strong> 的数组 <code>nums = [1, 2, ..., n]</code>的 <strong>可能的排列组合数量</strong>，使其满足 <strong>自整除</strong> 条件。</p>

<p>如果对于每个 <code>1 &lt;= i &lt;= n</code>，满足 <code>gcd(a[i], i) == 1</code>，数组 <code>nums</code> 就是 <strong>自整除</strong> 的。</p>

<p>数组的 <strong>排列</strong>&nbsp;是对数组元素的重新排列组合，例如，下面是数组 <code>[1, 2, 3]</code>&nbsp;的所有排列组合：</p>

<ul>
	<li><code>[1, 2, 3]</code></li>
	<li><code>[1, 3, 2]</code></li>
	<li><code>[2, 1, 3]</code></li>
	<li><code>[2, 3, 1]</code></li>
	<li><code>[3, 1, 2]</code></li>
	<li><code>[3, 2, 1]</code></li>
</ul>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<b>输入：</b>n = 1
<b>输出：</b>1
<b>解释：</b>数组 [1] 只有一个排列，它是自整除的。
</pre>

<p><b>示例 2：</b></p>

<pre>
<b>输入：</b>n = 2
<b>输出：1</b>
<b>解释：</b>数组 [1,2] 有 2 个排列，但只有其中一个是自整除的：
nums = [1,2]：这不是自整除的，因为 gcd(nums[2], 2) != 1。
nums = [2,1]：这是自整除的，因为 gcd(nums[1], 1) == 1 并且 gcd(nums[2], 2) == 1。
</pre>

<p><b>示例 3：</b></p>

<pre>
<b>输入：</b>n = 3
<b>输出：</b>3
<b>解释：</b>数组 [1,2,3] 有 3 个自整除的排列：[1,2,3]、[2,1,3]、[3,2,1]。
其他 3 个排列不能满足自整除条件。因此答案是 3。
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= n &lt;= 12</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：状态压缩 + 记忆化搜索

我们可以用一个二进制数 $mask$ 来表示当前排列的状态，其中第 $i$ 位为 $1$ 表示数字 $i$ 已经被使用，为 $0$ 表示数字 $i$ 还未被使用。

那么，我们设计一个函数 $dfs(mask)$，表示从当前排列的状态 $mask$ 开始，能够构造出的满足题目要求的排列的数量。答案即为 $dfs(0)$。

我们可以用记忆化搜索的方法来计算 $dfs(mask)$ 的值。

在计算 $dfs(mask)$ 的过程中，我们用 $i$ 表示当前要加入排列的是第几个数字，如果 $i \gt n$，说明排列已经构造完毕，我们可以返回 $1$。

否则，我们枚举当前排列中还未被使用的数字 $j$，如果 $i$ 和 $j$ 满足题目要求，那么我们就可以将 $j$ 加入排列中，此时状态变为 $mask \mid 2^j$，其中 $|$ 表示按位或运算。由于 $j$ 已经被使用，因此我们需要递归计算 $dfs(mask \mid 2^j)$ 的值，并将其累加到 $dfs(mask)$ 上。

最终，我们可以得到 $dfs(0)$ 的值，即为答案。

时间复杂度 $O(n \times 2^n)$，空间复杂度 $O(2^n)$。其中 $n$ 为排列的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def selfDivisiblePermutationCount(self, n: int) -> int:
        @cache
        def dfs(mask: int) -> int:
            i = mask.bit_count() + 1
            if i > n:
                return 1
            ans = 0
            for j in range(1, n + 1):
                if (mask >> j & 1) == 0 and (i % j == 0 or j % i == 0):
                    ans += dfs(mask | 1 << j)
            return ans

        return dfs(0)
```

#### Java

```java
class Solution {
    private int n;
    private Integer[] f;

    public int selfDivisiblePermutationCount(int n) {
        this.n = n;
        f = new Integer[1 << (n + 1)];
        return dfs(0);
    }

    private int dfs(int mask) {
        if (f[mask] != null) {
            return f[mask];
        }
        int i = Integer.bitCount(mask) + 1;
        if (i > n) {
            return 1;
        }
        f[mask] = 0;
        for (int j = 1; j <= n; ++j) {
            if ((mask >> j & 1) == 0 && (i % j == 0 || j % i == 0)) {
                f[mask] += dfs(mask | 1 << j);
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
    int selfDivisiblePermutationCount(int n) {
        int f[1 << (n + 1)];
        memset(f, -1, sizeof(f));
        function<int(int)> dfs = [&](int mask) {
            if (f[mask] != -1) {
                return f[mask];
            }
            int i = __builtin_popcount(mask) + 1;
            if (i > n) {
                return 1;
            }
            f[mask] = 0;
            for (int j = 1; j <= n; ++j) {
                if ((mask >> j & 1) == 0 && (i % j == 0 || j % i == 0)) {
                    f[mask] += dfs(mask | 1 << j);
                }
            }
            return f[mask];
        };
        return dfs(0);
    }
};
```

#### Go

```go
func selfDivisiblePermutationCount(n int) int {
	f := make([]int, 1<<(n+1))
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(mask int) int {
		if f[mask] != -1 {
			return f[mask]
		}
		i := bits.OnesCount(uint(mask)) + 1
		if i > n {
			return 1
		}
		f[mask] = 0
		for j := 1; j <= n; j++ {
			if mask>>j&1 == 0 && (i%j == 0 || j%i == 0) {
				f[mask] += dfs(mask | 1<<j)
			}
		}
		return f[mask]
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function selfDivisiblePermutationCount(n: number): number {
    const f: number[] = Array(1 << (n + 1)).fill(-1);
    const dfs = (mask: number): number => {
        if (f[mask] !== -1) {
            return f[mask];
        }
        const i = bitCount(mask) + 1;
        if (i > n) {
            return 1;
        }
        f[mask] = 0;
        for (let j = 1; j <= n; ++j) {
            if (((mask >> j) & 1) === 0 && (i % j === 0 || j % i === 0)) {
                f[mask] += dfs(mask | (1 << j));
            }
        }
        return f[mask];
    };
    return dfs(0);
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

我们可以将方法一中的记忆化搜索改写为动态规划的形式，定义 $f[mask]$ 表示当前排列的状态为 $mask$，且满足题目要求的排列的数量。初始时 $f[0]=1$，其余值均为 $0$。

我们在 $[0, 2^n)$ 的范围内枚举 $mask$，对于每个 $mask$，我们用 $i$ 表示当前最后一个加入排列的是第几个数字，然后我们枚举当前排列中最后一个加入的数字 $j$，如果 $i$ 和 $j$ 满足题目要求，那么状态 $f[mask]$ 就可以从状态 $f[mask \oplus 2^(j-1)]$ 转移而来，其中 $\oplus$ 表示按位异或运算。我们将所有转移得到的状态 $f[mask \oplus 2^(j-1)]$ 的值累加到 $f[mask]$ 上，即为 $f[mask]$ 的值。

最终，我们可以得到 $f[2^n - 1]$ 的值，即为答案。

时间复杂度 $O(n \times 2^n)$，空间复杂度 $O(2^n)$。其中 $n$ 为排列的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def selfDivisiblePermutationCount(self, n: int) -> int:
        f = [0] * (1 << n)
        f[0] = 1
        for mask in range(1 << n):
            i = mask.bit_count()
            for j in range(1, n + 1):
                if (mask >> (j - 1) & 1) == 1 and (i % j == 0 or j % i == 0):
                    f[mask] += f[mask ^ (1 << (j - 1))]
        return f[-1]
```

#### Java

```java
class Solution {
    public int selfDivisiblePermutationCount(int n) {
        int[] f = new int[1 << n];
        f[0] = 1;
        for (int mask = 0; mask < 1 << n; ++mask) {
            int i = Integer.bitCount(mask);
            for (int j = 1; j <= n; ++j) {
                if (((mask >> (j - 1)) & 1) == 1 && (i % j == 0 || j % i == 0)) {
                    f[mask] += f[mask ^ (1 << (j - 1))];
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
    int selfDivisiblePermutationCount(int n) {
        int f[1 << n];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (int mask = 0; mask < 1 << n; ++mask) {
            int i = __builtin_popcount(mask);
            for (int j = 1; j <= n; ++j) {
                if (((mask >> (j - 1)) & 1) == 1 && (i % j == 0 || j % i == 0)) {
                    f[mask] += f[mask ^ (1 << (j - 1))];
                }
            }
        }
        return f[(1 << n) - 1];
    }
};
```

#### Go

```go
func selfDivisiblePermutationCount(n int) int {
	f := make([]int, 1<<n)
	f[0] = 1
	for mask := 0; mask < 1<<n; mask++ {
		i := bits.OnesCount(uint(mask))
		for j := 1; j <= n; j++ {
			if mask>>(j-1)&1 == 1 && (i%j == 0 || j%i == 0) {
				f[mask] += f[mask^(1<<(j-1))]
			}
		}
	}
	return f[(1<<n)-1]
}
```

#### TypeScript

```ts
function selfDivisiblePermutationCount(n: number): number {
    const f: number[] = Array(1 << n).fill(0);
    f[0] = 1;
    for (let mask = 0; mask < 1 << n; ++mask) {
        const i = bitCount(mask);
        for (let j = 1; j <= n; ++j) {
            if ((mask >> (j - 1)) & 1 && (i % j === 0 || j % i === 0)) {
                f[mask] += f[mask ^ (1 << (j - 1))];
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
