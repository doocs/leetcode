---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3610.Minimum%20Number%20of%20Primes%20to%20Sum%20to%20Target/README.md
---

<!-- problem:start -->

# [3610. 目标和所需的最小质数个数 🔒](https://leetcode.cn/problems/minimum-number-of-primes-to-sum-to-target)

[English Version](/solution/3600-3699/3610.Minimum%20Number%20of%20Primes%20to%20Sum%20to%20Target/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个整数&nbsp;<code>n</code> 和&nbsp;<code>m</code>。</p>

<p>你必须从 <strong>前</strong> <code>m</code> 个 <strong><span data-keyword="prime-number">质数</span></strong> 中选择一个多重集合，使得所选质数的和 <strong>恰好</strong> 为 <code>n</code>。你可以 <strong>多次</strong> 使用每个质数。</p>

<p>返回组成 <code>n</code> 所需的最小质数个数，如果不可能，则返回 -1。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 10, m = 2</span></p>

<p><strong>输出：</strong><span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>前 2 个质数是&nbsp;[2, 3]。总和 10 可以通过&nbsp;2 + 2 + 3 + 3 构造，需要 4 个质数。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 15, m = 5</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<p>前 5 个质数是 [2, 3, 5, 7, 11]。总和 15 可以通过 5 + 5 + 5 构造，需要 3 个质数。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 7, m = 6</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<p>前 6 个质数是 [2, 3, 5, 7, 11, 13]。总和 7 可以直接通过质数 7 构造，只需要 1 个质数。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= m &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：预处理 + 动态规划

我们可以先预处理得到前 $1000$ 个素数，然后使用动态规划来求解。

定义 $f[i]$ 为和为 $i$ 的最小素数个数，初始时 $f[0] = 0$，其他 $f[i] = \infty$。对于每个素数 $p$，我们可以从 $f[i - p]$ 更新到 $f[i]$，即

$$
f[i] = \min(f[i], f[i - p] + 1)
$$

如果 $f[n]$ 仍然为 $\infty$，则说明无法用前 $m$ 个素数的和得到 $n$，返回 -1；否则返回 $f[n]$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(n + M)$，其中 $M$ 为预处理的素数个数（这里为 $1000$）。

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
