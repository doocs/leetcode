---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2403.Minimum%20Time%20to%20Kill%20All%20Monsters/README_EN.md
tags:
    - Bit Manipulation
    - Array
    - Dynamic Programming
    - Bitmask
---

<!-- problem:start -->

# [2403. Minimum Time to Kill All Monsters 🔒](https://leetcode.com/problems/minimum-time-to-kill-all-monsters)

[中文文档](/solution/2400-2499/2403.Minimum%20Time%20to%20Kill%20All%20Monsters/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>power</code> where <code>power[i]</code> is the power of the <code>i<sup>th</sup></code> monster.</p>

<p>You start with <code>0</code> mana points, and each day you increase your mana points by <code>gain</code> where <code>gain</code> initially is equal to <code>1</code>.</p>

<p>Each day, after gaining <code>gain</code> mana, you can defeat a monster if your mana points are greater than or equal to the power of that monster. When you defeat a monster:</p>

<ul>
	<li>your mana points will be reset to <code>0</code>, and</li>
	<li>the value of <code>gain</code> increases by <code>1</code>.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> number of days needed to defeat all the monsters.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> power = [3,1,4]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The optimal way to beat all the monsters is to:
- Day 1: Gain 1 mana point to get a total of 1 mana point. Spend all mana points to kill the 2<sup>nd</sup> monster.
- Day 2: Gain 2 mana points to get a total of 2 mana points.
- Day 3: Gain 2 mana points to get a total of 4 mana points. Spend all mana points to kill the 3<sup>rd</sup> monster.
- Day 4: Gain 3 mana points to get a total of 3 mana points. Spend all mana points to kill the 1<sup>st</sup> monster.
It can be proven that 4 is the minimum number of days needed. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> power = [1,1,4]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The optimal way to beat all the monsters is to:
- Day 1: Gain 1 mana point to get a total of 1 mana point. Spend all mana points to kill the 1<sup>st</sup> monster.
- Day 2: Gain 2 mana points to get a total of 2 mana points. Spend all mana points to kill the 2<sup>nd</sup> monster.
- Day 3: Gain 3 mana points to get a total of 3 mana points.
- Day 4: Gain 3 mana points to get a total of 6 mana points. Spend all mana points to kill the 3<sup>rd</sup> monster.
It can be proven that 4 is the minimum number of days needed. 
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> power = [1,2,4,9]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The optimal way to beat all the monsters is to:
- Day 1: Gain 1 mana point to get a total of 1 mana point. Spend all mana points to kill the 1st monster.
- Day 2: Gain 2 mana points to get a total of 2 mana points. Spend all mana points to kill the 2nd monster.
- Day 3: Gain 3 mana points to get a total of 3 mana points.
- Day 4: Gain 3 mana points to get a total of 6 mana points.
- Day 5: Gain 3 mana points to get a total of 9 mana points. Spend all mana points to kill the 4th monster.
- Day 6: Gain 4 mana points to get a total of 4 mana points. Spend all mana points to kill the 3rd monster.
It can be proven that 6 is the minimum number of days needed.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= power.length &lt;= 17</code></li>
	<li><code>1 &lt;= power[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: State Compression + Memoization Search

We note that the number of monsters is at most $17$, which means we can use a 17-bit binary number to represent the state of the monsters. The $i$-th bit being $1$ indicates that the $i$-th monster is still alive, and $0$ indicates that the $i$-th monster has been defeated.

We design a function $\textit{dfs}(\textit{mask})$ to represent the minimum number of days needed to defeat all monsters when the current state of the monsters is $\textit{mask}$. The answer is $\textit{dfs}(2^n - 1)$, where $n$ is the number of monsters.

The calculation of the function $\textit{dfs}(\textit{mask})$ is as follows:

-   If $\textit{mask} = 0$, it means all monsters have been defeated, return $0$;
-   Otherwise, we enumerate each monster $i$. If the $i$-th monster is still alive, we can choose to defeat the $i$-th monster, then recursively calculate $\textit{dfs}(\textit{mask} \oplus 2^i)$, and update the answer to $\textit{ans} = \min(\textit{ans}, \textit{dfs}(\textit{mask} \oplus 2^i) + \lceil \frac{x}{\textit{gain}} \rceil)$, where $x$ is the strength of the $i$-th monster, and $\textit{gain} = 1 + (n - \textit{mask}.\textit{bit\_count}())$ represents the current daily mana gain.

Finally, we return $\textit{dfs}(2^n - 1)$.

The time complexity is $O(2^n \times n)$, and the space complexity is $O(2^n)$. Here, $n$ is the number of monsters.

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
        auto dfs = [&](auto&& dfs, int mask) -> long long {
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
                    f[mask] = min(f[mask], dfs(dfs, mask ^ (1 << i)) + (power[i] + gain - 1) / gain);
                }
            }
            return f[mask];
        };
        return dfs(dfs, (1 << n) - 1);
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

### Solution 2: State Compression + Dynamic Programming

We can convert the memoization search in Solution 1 to dynamic programming. Define $f[\textit{mask}]$ to represent the minimum number of days needed to defeat all monsters when the current state of the monsters is $\textit{mask}$. Here, $\textit{mask}$ is an $n$-bit binary number, where the $i$-th bit being $1$ indicates that the $i$-th monster has been defeated, and $0$ indicates that the $i$-th monster is still alive. Initially, $f[0] = 0$, and the rest $f[\textit{mask}] = +\infty$. The answer is $f[2^n - 1]$.

We enumerate $\textit{mask}$ in the range $[1, 2^n - 1]$. For each $\textit{mask}$, we enumerate each monster $i$. If the $i$-th monster is defeated, it can be transferred from the previous state $\textit{mask} \oplus 2^i$, with a transfer cost of $(\textit{power}[i] + \textit{gain} - 1) / \textit{gain}$, where $\textit{gain} = \textit{mask}.\textit{bitCount}()$.

Finally, return $f[2^n - 1]$.

The time complexity is $O(2^n \times n)$, and the space complexity is $O(2^n)$. Here, $n$ is the number of monsters.

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
