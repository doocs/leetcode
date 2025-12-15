---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3770.Largest%20Prime%20from%20Consecutive%20Prime%20Sum/README_EN.md
rating: 1546
source: Weekly Contest 479 Q2
tags:
    - Array
    - Math
    - Number Theory
---

<!-- problem:start -->

# [3770. Largest Prime from Consecutive Prime Sum](https://leetcode.com/problems/largest-prime-from-consecutive-prime-sum)

[中文文档](/solution/3700-3799/3770.Largest%20Prime%20from%20Consecutive%20Prime%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>.</p>

<p>Return the <strong>largest <span data-keyword="prime-number">prime number</span></strong> less than or equal to <code>n</code> that can be expressed as the <strong>sum</strong> of one or more <strong>consecutive prime numbers</strong> starting from 2. If no such number exists, return 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 20</span></p>

<p><strong>Output:</strong> <span class="example-io">17</span></p>

<p><strong>Explanation:</strong></p>

<p>The prime numbers less than or equal to <code>n = 20</code> which are consecutive prime sums are:</p>

<ul>
	<li>
	<p><code>2 = 2</code></p>
	</li>
	<li>
	<p><code>5 = 2 + 3</code></p>
	</li>
	<li>
	<p><code>17 = 2 + 3 + 5 + 7</code></p>
	</li>
</ul>

<p>The largest is 17, so it is the answer.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The only consecutive prime sum less than or equal to 2 is 2 itself.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Preprocessing + Binary Search

We can preprocess a list of all prime numbers less than or equal to $5 \times 10^5$, then calculate the consecutive prime sums starting from 2, and store those sums that are prime numbers in an array $s$.

For each query, we simply need to use binary search in array $s$ to find the maximum value less than or equal to $n$.

In terms of time complexity, preprocessing the primes takes $O(M \log \log M)$ time, and each query takes $O(\log k)$ time, where $M$ is the upper limit of preprocessing, and $k$ is the length of array $s$. In this problem, $k \leq 40$.

<!-- tabs:start -->

#### Python3

```python
mx = 500000
is_prime = [True] * (mx + 1)
is_prime[0] = is_prime[1] = False
primes = []
for i in range(2, mx + 1):
    if is_prime[i]:
        primes.append(i)
        for j in range(i * i, mx + 1, i):
            is_prime[j] = False
s = [0]
t = 0
for x in primes:
    t += x
    if t > mx:
        break
    if is_prime[t]:
        s.append(t)


class Solution:
    def largestPrime(self, n: int) -> int:
        i = bisect_right(s, n) - 1
        return s[i]
```

#### Java

```java
class Solution {
    private static final int MX = 500000;
    private static final boolean[] IS_PRIME = new boolean[MX + 1];
    private static final List<Integer> PRIMES = new ArrayList<>();
    private static final List<Integer> S = new ArrayList<>();

    static {
        Arrays.fill(IS_PRIME, true);
        IS_PRIME[0] = false;
        IS_PRIME[1] = false;

        for (int i = 2; i <= MX; i++) {
            if (IS_PRIME[i]) {
                PRIMES.add(i);
                if ((long) i * i <= MX) {
                    for (int j = i * i; j <= MX; j += i) {
                        IS_PRIME[j] = false;
                    }
                }
            }
        }

        S.add(0);
        int t = 0;
        for (int x : PRIMES) {
            t += x;
            if (t > MX) {
                break;
            }
            if (IS_PRIME[t]) {
                S.add(t);
            }
        }
    }

    public int largestPrime(int n) {
        int i = Collections.binarySearch(S, n + 1);
        if (i < 0) {
            i = ~i;
        }
        return S.get(i - 1);
    }
}
```

#### C++

```cpp
static const int MX = 500000;
static vector<bool> IS_PRIME(MX + 1, true);
static vector<int> PRIMES;
static vector<int> S;

auto init = [] {
    IS_PRIME[0] = false;
    IS_PRIME[1] = false;

    for (int i = 2; i <= MX; i++) {
        if (IS_PRIME[i]) {
            PRIMES.push_back(i);
            if (1LL * i * i <= MX) {
                for (int j = i * i; j <= MX; j += i) {
                    IS_PRIME[j] = false;
                }
            }
        }
    }

    S.push_back(0);
    int t = 0;
    for (int x : PRIMES) {
        t += x;
        if (t > MX) break;
        if (IS_PRIME[t]) {
            S.push_back(t);
        }
    }

    return 0;
}();

class Solution {
public:
    int largestPrime(int n) {
        auto it = upper_bound(S.begin(), S.end(), n);
        return *(it - 1);
    }
};
```

#### Go

```go
const MX = 500000

var (
	isPrime = make([]bool, MX+1)
	primes  []int
	S       []int
)

func init() {
	for i := range isPrime {
		isPrime[i] = true
	}
	isPrime[0] = false
	isPrime[1] = false

	for i := 2; i <= MX; i++ {
		if isPrime[i] {
			primes = append(primes, i)
			if i*i <= MX {
				for j := i * i; j <= MX; j += i {
					isPrime[j] = false
				}
			}
		}
	}

	S = append(S, 0)
	t := 0
	for _, x := range primes {
		t += x
		if t > MX {
			break
		}
		if isPrime[t] {
			S = append(S, t)
		}
	}
}

func largestPrime(n int) int {
	i := sort.SearchInts(S, n+1)
	return S[i-1]
}
```

#### TypeScript

```ts
const MX = 500000;

const isPrime: boolean[] = Array(MX + 1).fill(true);
isPrime[0] = false;
isPrime[1] = false;

const primes: number[] = [];
const s: number[] = [];

(function init() {
    for (let i = 2; i <= MX; i++) {
        if (isPrime[i]) {
            primes.push(i);
            if (i * i <= MX) {
                for (let j = i * i; j <= MX; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    s.push(0);
    let t = 0;
    for (const x of primes) {
        t += x;
        if (t > MX) break;
        if (isPrime[t]) {
            s.push(t);
        }
    }
})();

function largestPrime(n: number): number {
    const i = _.sortedIndex(s, n + 1) - 1;
    return s[i];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
