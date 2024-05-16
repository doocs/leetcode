---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2992.Number%20of%20Self-Divisible%20Permutations/README_EN.md
tags:
    - Bit Manipulation
    - Recursion
    - Array
    - Dynamic Programming
    - Bitmask
---

<!-- problem:start -->

# [2992. Number of Self-Divisible Permutations 🔒](https://leetcode.com/problems/number-of-self-divisible-permutations)

[中文文档](/solution/2900-2999/2992.Number%20of%20Self-Divisible%20Permutations/README.md)

## Description

<!-- description:start -->

<p>Given an integer <code>n</code>, return <em>the number of <strong>permutations</strong> of the <strong>1-indexed</strong> array</em> <code>nums = [1, 2, ..., n]</code><em>, such that it&#39;s <strong>self-divisible</strong></em>.</p>

<p>A <strong>1-indexed</strong> array <code>a</code> of length <code>n</code> is <strong>self-divisible</strong> if for every <code>1 &lt;= i &lt;= n</code>, <code><span data-keyword="gcd-function">gcd</span>(a[i], i) == 1</code>.</p>

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
<strong>Output:</strong> 1
<strong>Explanation:</strong> The array [1,2] has 2 permutations and only one of them is self-divisible:
nums = [1,2]: This is not self-divisible since gcd(nums[2], 2) != 1.
nums = [2,1]: This is self-divisible since gcd(nums[1], 1) == 1 and gcd(nums[2], 2) == 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> The array [1,2,3] has 3 self-divisble permutations: [1,3,2], [3,1,2], [2,3,1].
It can be shown that the other 3 permutations are not self-divisible. Hence the answer is 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 12</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: State Compression + Memoization Search

We can use a binary number $mask$ to represent the current permutation state, where the $i$-th bit is $1$ indicates that the number $i$ has been used, and $0$ indicates that the number $i$ has not been used yet.

Then, we design a function $dfs(mask)$, which represents the number of permutations that can be constructed from the current permutation state $mask$ and meet the requirements of the problem. The answer is $dfs(0)$.

We can use the method of memoization search to calculate the value of $dfs(mask)$.

In the process of calculating $dfs(mask)$, we use $i$ to indicate which number is to be added to the permutation. If $i \gt n$, it means that the permutation has been constructed, and we can return $1$.

Otherwise, we enumerate the numbers $j$ that have not been used in the current permutation. If $i$ and $j$ meet the requirements of the problem, then we can add $j$ to the permutation. At this time, the state becomes $mask \mid 2^j$, where $|$ represents bitwise OR operation. Since $j$ has been used, we need to recursively calculate the value of $dfs(mask \mid 2^j)$ and add it to $dfs(mask)$.

Finally, we can get the value of $dfs(0)$, which is the answer.

The time complexity is $O(n \times 2^n)$, and the space complexity is $O(2^n)$. Where $n$ is the length of the permutation.

<!-- tabs:start -->

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

### Solution 2: State Compression + Dynamic Programming

We can rewrite the memoization search in Solution 1 into the form of dynamic programming, define $f[mask]$ to represent the number of permutations that the current permutation state is $mask$ and meet the requirements of the problem. Initially, $f[0]=1$, and the rest are $0$.

We enumerate $mask$ in the range of $[0, 2^n)$, for each $mask$, we use $i$ to represent which number is the last one to join the permutation, then we enumerate the last number $j$ added to the current permutation. If $i$ and $j$ meet the requirements of the problem, then the state $f[mask]$ can be transferred from the state $f[mask \oplus 2^(j-1)]$, where $\oplus$ represents bitwise XOR operation. We add all the values of the transferred state $f[mask \oplus 2^(j-1)]$ to $f[mask]$, which is the value of $f[mask]$.

Finally, we can get the value of $f[2^n - 1]$, which is the answer.

The time complexity is $O(n \times 2^n)$, and the space complexity is $O(2^n)$. Where $n$ is the length of the permutation.

<!-- tabs:start -->

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
