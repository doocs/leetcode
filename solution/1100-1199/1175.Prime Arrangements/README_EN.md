# [1175. Prime Arrangements](https://leetcode.com/problems/prime-arrangements)

[中文文档](/solution/1100-1199/1175.Prime%20Arrangements/README.md)

## Description

<p>Return the number of permutations of 1 to <code>n</code> so that prime numbers are at prime indices (1-indexed.)</p>

<p><em>(Recall that an integer&nbsp;is prime if and only if it is greater than 1, and cannot be written as a product of two positive integers&nbsp;both smaller than it.)</em></p>

<p>Since the answer may be large, return the answer <strong>modulo <code>10^9 + 7</code></strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> 12
<strong>Explanation:</strong> For example [1,2,5,4,3] is a valid permutation, but [5,2,3,4,1] is not because the prime number 5 is at index 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 100
<strong>Output:</strong> 682289015
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
</ul>

## Solutions

### Solution 1: Mathematics

First, count the number of prime numbers within the range $[1,n]$, which we denote as $cnt$. Then, calculate the product of the factorial of $cnt$ and $n-cnt$ to get the answer, remember to perform the modulo operation.

Here, we use the "Sieve of Eratosthenes" to count prime numbers.

If $x$ is a prime number, then multiples of $x$ greater than $x$, such as $2x$, $3x$, ... are definitely not prime numbers, so we can start from here.

Let $primes[i]$ indicate whether the number $i$ is a prime number. If it is a prime number, it is $true$, otherwise it is $false$.

We sequentially traverse each number $i$ in the range $[2,n]$. If this number is a prime number, the number of prime numbers increases by $1$, and then all its multiples $j$ are marked as composite numbers (except for the prime number itself), that is, $primes[j]=false$. In this way, at the end of the run, we can know the number of prime numbers.

The time complexity is $O(n \times \log \log n)$.

<!-- tabs:start -->

```python
class Solution:
    def numPrimeArrangements(self, n: int) -> int:
        def count(n):
            cnt = 0
            primes = [True] * (n + 1)
            for i in range(2, n + 1):
                if primes[i]:
                    cnt += 1
                    for j in range(i + i, n + 1, i):
                        primes[j] = False
            return cnt

        cnt = count(n)
        ans = factorial(cnt) * factorial(n - cnt)
        return ans % (10**9 + 7)
```

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int numPrimeArrangements(int n) {
        int cnt = count(n);
        long ans = f(cnt) * f(n - cnt);
        return (int) (ans % MOD);
    }

    private long f(int n) {
        long ans = 1;
        for (int i = 2; i <= n; ++i) {
            ans = (ans * i) % MOD;
        }
        return ans;
    }

    private int count(int n) {
        int cnt = 0;
        boolean[] primes = new boolean[n + 1];
        Arrays.fill(primes, true);
        for (int i = 2; i <= n; ++i) {
            if (primes[i]) {
                ++cnt;
                for (int j = i + i; j <= n; j += i) {
                    primes[j] = false;
                }
            }
        }
        return cnt;
    }
}
```

```cpp
using ll = long long;
const int MOD = 1e9 + 7;

class Solution {
public:
    int numPrimeArrangements(int n) {
        int cnt = count(n);
        ll ans = f(cnt) * f(n - cnt);
        return (int) (ans % MOD);
    }

    ll f(int n) {
        ll ans = 1;
        for (int i = 2; i <= n; ++i) ans = (ans * i) % MOD;
        return ans;
    }

    int count(int n) {
        vector<bool> primes(n + 1, true);
        int cnt = 0;
        for (int i = 2; i <= n; ++i) {
            if (primes[i]) {
                ++cnt;
                for (int j = i + i; j <= n; j += i) primes[j] = false;
            }
        }
        return cnt;
    }
};
```

```go
func numPrimeArrangements(n int) int {
	count := func(n int) int {
		cnt := 0
		primes := make([]bool, n+1)
		for i := range primes {
			primes[i] = true
		}
		for i := 2; i <= n; i++ {
			if primes[i] {
				cnt++
				for j := i + i; j <= n; j += i {
					primes[j] = false
				}
			}
		}
		return cnt
	}

	mod := int(1e9) + 7
	f := func(n int) int {
		ans := 1
		for i := 2; i <= n; i++ {
			ans = (ans * i) % mod
		}
		return ans
	}

	cnt := count(n)
	ans := f(cnt) * f(n-cnt)
	return ans % mod
}
```

<!-- tabs:end -->

<!-- end -->
