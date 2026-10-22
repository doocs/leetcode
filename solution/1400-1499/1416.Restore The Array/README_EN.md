---
comments: true
difficulty: Hard
rating: 1919
source: Biweekly Contest 24 Q4
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [1416. Restore The Array](https://leetcode.com/problems/restore-the-array)

[中文文档](/solution/1400-1499/1416.Restore%20The%20Array/README.md)

## Description

<!-- description:start -->

<p>A program was supposed to print an array of integers. The program forgot to print whitespaces and the array is printed as a string of digits <code>s</code> and all we know is that all integers in the array were in the range <code>[1, k]</code> and there are no leading zeros in the array.</p>

<p>Given the string <code>s</code> and the integer <code>k</code>, return <em>the number of the possible arrays that can be printed as </em><code>s</code><em> using the mentioned program</em>. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1000&quot;, k = 10000
<strong>Output:</strong> 1
<strong>Explanation:</strong> The only possible array is [1000]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1000&quot;, k = 10
<strong>Output:</strong> 0
<strong>Explanation:</strong> There cannot be an array that was printed this way and has all integer &gt;= 1 and &lt;= 10.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1317&quot;, k = 2000
<strong>Output:</strong> 8
<strong>Explanation:</strong> Possible arrays are [1317],[131,7],[13,17],[1,317],[13,1,7],[1,31,7],[1,3,17],[1,3,1,7]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only digits and does not contain leading zeros.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> We must split $s$ into integers in $[1,k]$ with no leading zeros. $n\le 10^5$ rules out enumerating cuts. $k\le 10^9$, so a number starting at $i$ spans at most $10$ digits.
>
> Let $f(i)$ be the number of ways to restore $s[i:]$. A leading zero dies; otherwise try end indices $j$ while the value is $\le k$ and add $f(j+1)$. Memoize or compute right to left.

<!-- thinking:end -->

Compute $f(i)$ from the right, with $f(n)=1$. If $s[i]$ is `0`, then $f(i)=0$. Otherwise extend a number from index $i$ and stop once it exceeds $k$, adding $f(j+1)$ for every valid cut. The answer is $f(0)$ modulo $10^9+7$.

The time complexity is $O(n \times d)$ and the space complexity is $O(n)$, where $n$ is the length of $s$ and $d$ is the number of decimal digits of $k$, at most $10$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfArrays(self, s: str, k: int) -> int:
        mod = 10**9 + 7
        n = len(s)
        f = [0] * (n + 1)
        f[n] = 1
        for i in range(n - 1, -1, -1):
            if s[i] == '0':
                continue
            x = 0
            for j in range(i, n):
                x = x * 10 + int(s[j])
                if x > k:
                    break
                f[i] = (f[i] + f[j + 1]) % mod
        return f[0]
```

#### Java

```java
class Solution {
    public int numberOfArrays(String s, int k) {
        final int mod = 1_000_000_007;
        int n = s.length();
        int[] f = new int[n + 1];
        f[n] = 1;
        for (int i = n - 1; i >= 0; --i) {
            if (s.charAt(i) == '0') {
                continue;
            }
            long x = 0;
            for (int j = i; j < n; ++j) {
                x = x * 10 + s.charAt(j) - '0';
                if (x > k) {
                    break;
                }
                f[i] = (int) ((f[i] + f[j + 1]) % mod);
            }
        }
        return f[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfArrays(string s, int k) {
        const int mod = 1e9 + 7;
        int n = s.size();
        vector<int> f(n + 1);
        f[n] = 1;
        for (int i = n - 1; i >= 0; --i) {
            if (s[i] == '0') {
                continue;
            }
            long long x = 0;
            for (int j = i; j < n; ++j) {
                x = x * 10 + s[j] - '0';
                if (x > k) {
                    break;
                }
                f[i] = (f[i] + f[j + 1]) % mod;
            }
        }
        return f[0];
    }
};
```

#### Go

```go
func numberOfArrays(s string, k int) int {
	const mod = int(1e9 + 7)
	n := len(s)
	f := make([]int, n+1)
	f[n] = 1
	for i := n - 1; i >= 0; i-- {
		if s[i] == '0' {
			continue
		}
		x := 0
		for j := i; j < n; j++ {
			x = x*10 + int(s[j]-'0')
			if x > k {
				break
			}
			f[i] = (f[i] + f[j+1]) % mod
		}
	}
	return f[0]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Rolling Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> Solution 1 stores a value for every suffix, although a number starting at a position can use at most $d$ digits, where $d$ is the number of digits in $k$. While scanning from right to left, the next state therefore reads only the following $d$ suffix counts. We can keep those counts in a circular array of length $d+1$: the slot for the current position cannot overlap any slot that it still needs to read. This preserves the same transitions while removing the $O(n)$ DP array.

<!-- thinking:end -->

Let $f(i)$ be the number of valid partitions of $s[i:]$, with $f(n)=1$. If $s[i]$ is `0`, then $f(i)=0$. Otherwise, extend the first number by at most $d$ digits; for every value at most $k$, add $f(j+1)$ modulo $10^9+7$.

Store $f(i)$ at index $i \bmod (d+1)$. Every value read while computing $f(i)$ has an index in $[i+1,i+d]$, so none shares the slot for $i$. The time complexity is $O(nd)$, as in Solution 1, while the auxiliary space drops from $O(n)$ to $O(d)$; here $d\le 10$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfArrays(self, s: str, k: int) -> int:
        mod = 10**9 + 7
        n = len(s)
        d = len(str(k))
        f = [0] * (d + 1)
        f[n % (d + 1)] = 1
        for i in range(n - 1, -1, -1):
            ways = 0
            if s[i] != '0':
                value = 0
                for j in range(i, min(n, i + d)):
                    value = value * 10 + int(s[j])
                    if value > k:
                        break
                    ways = (ways + f[(j + 1) % (d + 1)]) % mod
            f[i % (d + 1)] = ways
        return f[0]
```

#### C++

```cpp
class Solution {
public:
    int numberOfArrays(string s, int k) {
        constexpr int mod = 1e9 + 7;
        int n = s.size(), d = to_string(k).size();
        vector<int> f(d + 1);
        f[n % (d + 1)] = 1;
        for (int i = n - 1; i >= 0; --i) {
            int ways = 0;
            if (s[i] != '0') {
                long long value = 0;
                for (int j = i; j < n && j < i + d; ++j) {
                    value = value * 10 + s[j] - '0';
                    if (value > k) break;
                    ways = (ways + f[(j + 1) % (d + 1)]) % mod;
                }
            }
            f[i % (d + 1)] = ways;
        }
        return f[0];
    }
};
```

#### TypeScript

```ts
function numberOfArrays(s: string, k: number): number {
    const mod = 1e9 + 7;
    const n = s.length;
    const d = String(k).length;
    const f = Array(d + 1).fill(0);
    f[n % (d + 1)] = 1;
    for (let i = n - 1; i >= 0; --i) {
        let ways = 0;
        if (s[i] !== '0') {
            let value = 0;
            for (let j = i; j < n && j < i + d; ++j) {
                value = value * 10 + s.charCodeAt(j) - 48;
                if (value > k) {
                    break;
                }
                ways = (ways + f[(j + 1) % (d + 1)]) % mod;
            }
        }
        f[i % (d + 1)] = ways;
    }
    return f[0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
