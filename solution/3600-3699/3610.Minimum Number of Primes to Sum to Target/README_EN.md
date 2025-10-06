---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3610.Minimum%20Number%20of%20Primes%20to%20Sum%20to%20Target/README_EN.md
tags:
    - Array
    - Math
    - Dynamic Programming
    - Number Theory
---

<!-- problem:start -->

# [3610. Minimum Number of Primes to Sum to Target 🔒](https://leetcode.com/problems/minimum-number-of-primes-to-sum-to-target)

[中文文档](/solution/3600-3699/3610.Minimum%20Number%20of%20Primes%20to%20Sum%20to%20Target/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>n</code> and <code>m</code>.</p>

<p>You have to select a multiset of <strong><span data-keyword="prime-number">prime numbers</span></strong> from the <strong>first</strong> <code>m</code> prime numbers such that the sum of the selected primes is <strong>exactly</strong> <code>n</code>. You may use each prime number <strong>multiple</strong> times.</p>

<p>Return the <strong>minimum</strong> number of prime numbers needed to sum up to <code>n</code>, or -1 if it is not possible.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 10, m = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The first 2 primes are [2, 3]. The sum 10 can be formed as 2 + 2 + 3 + 3, requiring 4 primes.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 15, m = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The first 5 primes are [2, 3, 5, 7, 11]. The sum 15 can be formed as 5 + 5 + 5, requiring 3 primes.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 7, m = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The first 6 primes are [2, 3, 5, 7, 11, 13]. The sum 7 can be formed directly by prime 7, requiring only 1 prime.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= m &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Preprocessing + Dynamic Programming

We can first preprocess to obtain the first $1000$ prime numbers, and then use dynamic programming to solve the problem.

Define $f[i]$ as the minimum number of primes needed to sum up to $i$. Initially, set $f[0] = 0$ and all other $f[i] = \infty$. For each prime $p$, we can update $f[i]$ from $f[i - p]$ as follows:

$$
f[i] = \min(f[i], f[i - p] + 1)
$$

If $f[n]$ is still $\infty$, it means it is impossible to obtain $n$ as the sum of the first $m$ primes, so return -1; otherwise, return $f[n]$.

The time complexity is $O(m \times n)$, and the space complexity is $O(n + M)$, where $M$ is the number of preprocessed primes (here it is $1000$).

<!-- tabs:start -->

#### Python3

```python
primes = []
x = 2
M = 1000
while len(primes) < M:
    is_prime = True
    for p in primes:
        if p * p > x:
            break
        if x % p == 0:
            is_prime = False
            break
    if is_prime:
        primes.append(x)
    x += 1


class Solution:
    def minNumberOfPrimes(self, n: int, m: int) -> int:
        min = lambda x, y: x if x < y else y
        f = [0] + [inf] * n
        for x in primes[:m]:
            for i in range(x, n + 1):
                f[i] = min(f[i], f[i - x] + 1)
        return f[n] if f[n] < inf else -1
```

#### Java

```java
class Solution {
    static List<Integer> primes = new ArrayList<>();
    static {
        int x = 2;
        int M = 1000;
        while (primes.size() < M) {
            boolean is_prime = true;
            for (int p : primes) {
                if (p * p > x) {
                    break;
                }
                if (x % p == 0) {
                    is_prime = false;
                    break;
                }
            }
            if (is_prime) {
                primes.add(x);
            }
            x++;
        }
    }

    public int minNumberOfPrimes(int n, int m) {
        int[] f = new int[n + 1];
        final int inf = 1 << 30;
        Arrays.fill(f, inf);
        f[0] = 0;
        for (int x : primes.subList(0, m)) {
            for (int i = x; i <= n; i++) {
                f[i] = Math.min(f[i], f[i - x] + 1);
            }
        }
        return f[n] < inf ? f[n] : -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minNumberOfPrimes(int n, int m) {
        static vector<int> primes;
        if (primes.empty()) {
            int x = 2;
            int M = 1000;
            while ((int) primes.size() < M) {
                bool is_prime = true;
                for (int p : primes) {
                    if (p * p > x) break;
                    if (x % p == 0) {
                        is_prime = false;
                        break;
                    }
                }
                if (is_prime) primes.push_back(x);
                x++;
            }
        }

        vector<int> f(n + 1, INT_MAX);
        f[0] = 0;
        for (int x : vector<int>(primes.begin(), primes.begin() + m)) {
            for (int i = x; i <= n; ++i) {
                if (f[i - x] != INT_MAX) {
                    f[i] = min(f[i], f[i - x] + 1);
                }
            }
        }
        return f[n] < INT_MAX ? f[n] : -1;
    }
};
```

#### Go

```go
var primes []int

func init() {
	x := 2
	M := 1000
	for len(primes) < M {
		is_prime := true
		for _, p := range primes {
			if p*p > x {
				break
			}
			if x%p == 0 {
				is_prime = false
				break
			}
		}
		if is_prime {
			primes = append(primes, x)
		}
		x++
	}
}

func minNumberOfPrimes(n int, m int) int {
	const inf = int(1e9)
	f := make([]int, n+1)
	for i := 1; i <= n; i++ {
		f[i] = inf
	}
	f[0] = 0

	for _, x := range primes[:m] {
		for i := x; i <= n; i++ {
			if f[i-x] < inf && f[i-x]+1 < f[i] {
				f[i] = f[i-x] + 1
			}
		}
	}

	if f[n] < inf {
		return f[n]
	}
	return -1
}
```

#### TypeScript

```ts
const primes: number[] = [];
let x = 2;
const M = 1000;
while (primes.length < M) {
    let is_prime = true;
    for (const p of primes) {
        if (p * p > x) break;
        if (x % p === 0) {
            is_prime = false;
            break;
        }
    }
    if (is_prime) primes.push(x);
    x++;
}

function minNumberOfPrimes(n: number, m: number): number {
    const inf = 1e9;
    const f: number[] = Array(n + 1).fill(inf);
    f[0] = 0;

    for (const x of primes.slice(0, m)) {
        for (let i = x; i <= n; i++) {
            if (f[i - x] < inf) {
                f[i] = Math.min(f[i], f[i - x] + 1);
            }
        }
    }

    return f[n] < inf ? f[n] : -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
