# [2992. Number of Self-Divisible Permutations](https://leetcode.cn/problems/number-of-self-divisible-permutations)

[English Version](/solution/2900-2999/2992.Number%20of%20Self-Divisible%20Permutations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Given an integer <code>n</code>, return <em>the number of <strong>permutations</strong> of the <strong>1-indexed</strong> array</em> <code>nums = [1, 2, ..., n]</code><em>, such that it&#39;s <strong>self-divisible</strong></em>.</p>

<p>Array <code>nums</code> is <strong>self-divisible</strong> if for every <code>1 &lt;= i &lt;= n</code>, <strong>at least</strong> one of the following conditions holds:</p>

<ul>
	<li><code>nums[i] % i == 0</code></li>
	<li><code>i % nums[i] == 0</code></li>
</ul>

<p>A <strong>permutation</strong> of an array is a rearrangement of the elements of that array, for example here are all of the permutations of the array <code>[1, 2, 3]</code>:</p>

<ul>
	<li><code>[1, 2, 3]</code></li>
	<li><code>[1, 3, 2]</code></li>
	<li><code>[2, 1, 3]</code></li>
	<li><code>[2, 3, 1]</code></li>
	<li><code>[3, 1, 2]</code></li>
	<li><code>[3, 2, 1]</code></li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> The array [1] has only 1 permutation which is self-divisible.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> The array [1,2] has 2 permutations both of which are self-divisible:
nums = [1,2]: This is self-divisible since nums[1] % 1 == 0 and nums[2] % 2 == 0.
nums = [2,1]: This is self-divisible since nums[1] % 1 == 0 and 2 % nums[2] == 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> The array [1,2,3] has 3 self-divisble permutations: [1,2,3], [2,1,3], [3,2,1].
It can be shown that the other 3 permutations are not self-divisible. Hence the answer is 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 15</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：状态压缩 + 记忆化搜索**

我们可以用一个二进制数 $mask$ 来表示当前排列的状态，其中第 $i$ 位为 $1$ 表示数字 $i$ 已经被使用，为 $0$ 表示数字 $i$ 还未被使用。

那么，我们设计一个函数 $dfs(mask)$，表示从当前排列的状态 $mask$ 开始，能够构造出的满足题目要求的排列的数量。答案即为 $dfs(0)$。

我们可以用记忆化搜索的方法来计算 $dfs(mask)$ 的值。

在计算 $dfs(mask)$ 的过程中，我们用 $i$ 表示当前要加入排列的是第几个数字，如果 $i \gt n$，说明排列已经构造完毕，我们可以返回 $1$。

否则，我们枚举当前排列中还未被使用的数字 $j$，如果 $i$ 和 $j$ 满足题目要求，那么我们就可以将 $j$ 加入排列中，此时状态变为 $mask \mid 2^j$，其中 $|$ 表示按位或运算。由于 $j$ 已经被使用，因此我们需要递归计算 $dfs(mask \mid 2^j)$ 的值，并将其累加到 $dfs(mask)$ 上。

最终，我们可以得到 $dfs(0)$ 的值，即为答案。

时间复杂度 $O(n \times 2^n)$，空间复杂度 $O(2^n)$。其中 $n$ 为排列的长度。

**方法二：状态压缩 + 动态规划**

我们可以将方法一中的记忆化搜索改写为动态规划的形式，定义 $f[mask]$ 表示当前排列的状态为 $mask$，且满足题目要求的排列的数量。初始时 $f[0]=1$，其余值均为 $0$。

我们在 $[0, 2^n)$ 的范围内枚举 $mask$，对于每个 $mask$，我们用 $i$ 表示当前最后一个加入排列的是第几个数字，然后我们枚举当前排列中最后一个加入的数字 $j$，如果 $i$ 和 $j$ 满足题目要求，那么状态 $f[mask]$ 就可以从状态 $f[mask \oplus 2^(j-1)]$ 转移而来，其中 $\oplus$ 表示按位异或运算。我们将所有转移得到的状态 $f[mask \oplus 2^(j-1)]$ 的值累加到 $f[mask]$ 上，即为 $f[mask]$ 的值。

最终，我们可以得到 $f[2^n - 1]$ 的值，即为答案。

时间复杂度 $O(n \times 2^n)$，空间复杂度 $O(2^n)$。其中 $n$ 为排列的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

### **TypeScript**

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

### **...**

```

```

<!-- tabs:end -->
