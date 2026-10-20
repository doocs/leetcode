---
comments: true
difficulty: Medium
rating: 1467
source: Weekly Contest 523 Q2
---

<!-- problem:start -->

# [4079. Prime Subset Selection I](https://leetcode.com/problems/prime-subset-selection-i)

[中文文档](/solution/4000-4099/4079.Prime%20Subset%20Selection%20I/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>n</code> and <code>s</code>.</p>

<p>Consider all <strong><span data-keyword="prime-number">prime</span></strong> numbers less than or equal to <code>n</code>. Select some of these primes such that:</p>

<ul>
	<li>The <strong>sum</strong> of the selected primes is less than or equal to <code>s</code>.</li>
	<li>The number of selected primes is <strong>maximized</strong>.</li>
	<li>Each prime can be selected <strong>at most once</strong>.</li>
</ul>

<p>Return an integer array containing the selected primes in <strong>increasing</strong> order. If multiple optimal selections exist, return <strong>any</strong> of them.</p>

<p>If no prime can be selected, return an <strong>empty</strong> array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 7, s = 20</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,3,5,7]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The prime numbers less than or equal to <code>n = 7</code> are 2, 3, 5, and 7.</li>
	<li>The sum of all these primes is <code>2 + 3 + 5 + 7 = 17</code>, which is less than or equal to <code>s = 20</code>.</li>
	<li>Thus, the answer is <code>[2, 3, 5, 7]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 15, s = 15</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,5,7]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The prime numbers less than or equal to <code>n = 15</code> are 2, 3, 5, 7, 11, and 13.</li>
	<li>One possible selection is <code>[3, 5, 7]</code>, whose sum is <code>3 + 5 + 7 = 15</code>.</li>
	<li>The four smallest available primes have sum <code>2 + 3 + 5 + 7 = 17</code>, which exceeds <code>s = 15</code>. Therefore, selecting four or more primes is impossible, and the maximum number of selected primes is three.</li>
	<li>Thus, <code>[3, 5, 7]</code> is one possible answer. Other valid answers include <code>[2, 3, 5]</code>, <code>[2, 3, 7]</code>, and <code>[2, 5, 7]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, s = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The smallest prime number is 2, which is greater than <code>s = 1</code>.</li>
	<li>Thus, no prime can be selected, so the answer is an empty array.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= s &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sieve and Greedy Selection

<!-- thinking:start -->

> **Thinking**
>
> The goal is to take as many primes as possible without letting their sum exceed $s$. With $n$ up to $10^6$ and $s$ up to $10^9$, a knapsack over subsets does not fit.
>
> Among all subsets of a fixed size, the smallest sum comes from the smallest primes. The largest feasible size is therefore the longest prefix of the primes in increasing order whose sum is still at most $s$.
>
> Sieve the primes up to $10^6$ first. A query then takes them in order until the next prime is larger than $n$ or larger than the remaining budget.

<!-- thinking:end -->

Use the sieve of Eratosthenes to precompute every prime up to $10^6$ and store the primes in increasing order. For a query $(n,s)$, scan that list from small to large. Stop when the current prime $p$ is greater than $n$ or greater than the remaining budget $s$; otherwise append $p$ to the answer and subtract $p$ from $s$.

The selected primes are the smallest ones that do not exceed $n$. They have the minimum sum among all subsets of the same size, so the size is already maximum. Any optimal subset is acceptable.

The sieve takes $O(M\log\log M)$ time and $O(M)$ space, where $M=10^6$. Each query takes $O(\pi(n))$ time, where $\pi(n)$ is the number of primes not exceeding $n$.

<!-- tabs:start -->

#### Python3

```python
mx = 10**6
primes = []
is_prime = [True] * (mx + 1)
is_prime[0] = is_prime[1] = False

for i in range(2, mx + 1):
    if is_prime[i]:
        primes.append(i)
        if i * i <= mx:
            for j in range(i * i, mx + 1, i):
                is_prime[j] = False


class Solution:
    def maxPrimes(self, n: int, s: int) -> list[int]:
        ans = []
        for p in primes:
            if p > n or p > s:
                break
            ans.append(p)
            s -= p
        return ans
```

#### Java

```java
class Solution {
    static int mx = 1000000;
    static List<Integer> primes = new ArrayList<>();

    static {
        boolean[] isPrime = new boolean[mx + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i <= mx; i++) {
            if (isPrime[i]) {
                primes.add(i);
                if (i <= mx / i) {
                    for (int j = i * i; j <= mx; j += i) {
                        isPrime[j] = false;
                    }
                }
            }
        }
    }

    public List<Integer> maxPrimes(int n, int s) {
        List<Integer> ans = new ArrayList<>();
        for (int p : primes) {
            if (p > n || p > s) {
                break;
            }
            ans.add(p);
            s -= p;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
    static const int mx = 1000000;
    static vector<int> primes;

public:
    vector<int> maxPrimes(int n, int s) {
        vector<int> ans;
        for (int p : primes) {
            if (p > n || p > s) {
                break;
            }
            ans.push_back(p);
            s -= p;
        }
        return ans;
    }
};

const int Solution::mx;
vector<int> Solution::primes = [] {
    vector<bool> isPrime(mx + 1, true);
    isPrime[0] = isPrime[1] = false;
    vector<int> primes;

    for (int i = 2; i <= mx; i++) {
        if (isPrime[i]) {
            primes.push_back(i);
            if (i <= mx / i) {
                for (int j = i * i; j <= mx; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
    return primes;
}();
```

#### Go

```go
const mx = 1000000

var primes = func() []int {
	isPrime := make([]bool, mx+1)
	for i := 2; i <= mx; i++ {
		isPrime[i] = true
	}

	primes := make([]int, 0)
	for i := 2; i <= mx; i++ {
		if isPrime[i] {
			primes = append(primes, i)
			if i <= mx/i {
				for j := i * i; j <= mx; j += i {
					isPrime[j] = false
				}
			}
		}
	}
	return primes
}()

func maxPrimes(n int, s int) []int {
	ans := make([]int, 0)
	for _, p := range primes {
		if p > n || p > s {
			break
		}
		ans = append(ans, p)
		s -= p
	}
	return ans
}
```

#### TypeScript

```ts
const mx = 10 ** 6;

const primes: number[] = (() => {
    const isPrime = new Uint8Array(mx + 1).fill(1);
    isPrime[0] = isPrime[1] = 0;
    const primes: number[] = [];

    for (let i = 2; i <= mx; i++) {
        if (isPrime[i]) {
            primes.push(i);
            if (i <= mx / i) {
                for (let j = i * i; j <= mx; j += i) {
                    isPrime[j] = 0;
                }
            }
        }
    }
    return primes;
})();

function maxPrimes(n: number, s: number): number[] {
    const ans: number[] = [];
    for (const p of primes) {
        if (p > n || p > s) {
            break;
        }
        ans.push(p);
        s -= p;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
