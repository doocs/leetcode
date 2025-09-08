---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2327.Number%20of%20People%20Aware%20of%20a%20Secret/README_EN.md
rating: 1893
source: Weekly Contest 300 Q3
tags:
    - Queue
    - Dynamic Programming
    - Simulation
---

<!-- problem:start -->

# [2327. Number of People Aware of a Secret](https://leetcode.com/problems/number-of-people-aware-of-a-secret)

[中文文档](/solution/2300-2399/2327.Number%20of%20People%20Aware%20of%20a%20Secret/README.md)

## Description

<!-- description:start -->

<p>On day <code>1</code>, one person discovers a secret.</p>

<p>You are given an integer <code>delay</code>, which means that each person will <strong>share</strong> the secret with a new person <strong>every day</strong>, starting from <code>delay</code> days after discovering the secret. You are also given an integer <code>forget</code>, which means that each person will <strong>forget</strong> the secret <code>forget</code> days after discovering it. A person <strong>cannot</strong> share the secret on the same day they forgot it, or on any day afterwards.</p>

<p>Given an integer <code>n</code>, return<em> the number of people who know the secret at the end of day </em><code>n</code>. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 6, delay = 2, forget = 4
<strong>Output:</strong> 5
<strong>Explanation:</strong>
Day 1: Suppose the first person is named A. (1 person)
Day 2: A is the only person who knows the secret. (1 person)
Day 3: A shares the secret with a new person, B. (2 people)
Day 4: A shares the secret with a new person, C. (3 people)
Day 5: A forgets the secret, and B shares the secret with a new person, D. (3 people)
Day 6: B shares the secret with E, and C shares the secret with F. (5 people)
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4, delay = 1, forget = 3
<strong>Output:</strong> 6
<strong>Explanation:</strong>
Day 1: The first person is named A. (1 person)
Day 2: A shares the secret with B. (2 people)
Day 3: A and B share the secret with 2 new people, C and D. (4 people)
Day 4: A forgets the secret. B, C, and D share the secret with 3 new people. (6 people)
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= delay &lt; forget &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Difference Array

We use a difference array $d[i]$ to record the change in the number of people who know the secret on day $i$, and an array $cnt[i]$ to record the number of people who newly learn the secret on day $i$.

For the $cnt[i]$ people who newly learn the secret on day $i$, they can share the secret with another $cnt[i]$ people each day during the interval $[i+\text{delay}, i+\text{forget})$.

The answer is $\sum_{i=1}^{n} d[i]$.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$, where $n$ is the given integer.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def peopleAwareOfSecret(self, n: int, delay: int, forget: int) -> int:
        m = (n << 1) + 10
        d = [0] * m
        cnt = [0] * m
        cnt[1] = 1
        for i in range(1, n + 1):
            if cnt[i]:
                d[i] += cnt[i]
                d[i + forget] -= cnt[i]
                nxt = i + delay
                while nxt < i + forget:
                    cnt[nxt] += cnt[i]
                    nxt += 1
        mod = 10**9 + 7
        return sum(d[: n + 1]) % mod
```

#### Java

```java
class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        final int mod = (int) 1e9 + 7;
        int m = (n << 1) + 10;
        long[] d = new long[m];
        long[] cnt = new long[m];
        cnt[1] = 1;
        for (int i = 1; i <= n; ++i) {
            if (cnt[i] > 0) {
                d[i] = (d[i] + cnt[i]) % mod;
                d[i + forget] = (d[i + forget] - cnt[i] + mod) % mod;
                int nxt = i + delay;
                while (nxt < i + forget) {
                    cnt[nxt] = (cnt[nxt] + cnt[i]) % mod;
                    ++nxt;
                }
            }
        }
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            ans = (ans + d[i]) % mod;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int peopleAwareOfSecret(int n, int delay, int forget) {
        const int mod = 1e9 + 7;
        int m = (n << 1) + 10;
        vector<long long> d(m), cnt(m);
        cnt[1] = 1;
        for (int i = 1; i <= n; i++) {
            if (cnt[i]) {
                d[i] = (d[i] + cnt[i]) % mod;
                d[i + forget] = (d[i + forget] - cnt[i] + mod) % mod;
                int nxt = i + delay;
                while (nxt < i + forget) {
                    cnt[nxt] = (cnt[nxt] + cnt[i]) % mod;
                    nxt++;
                }
            }
        }
        long long ans = 0;
        for (int i = 0; i <= n; i++) {
            ans += d[i];
        }
        return ans % mod;
    }
};
```

#### Go

```go
func peopleAwareOfSecret(n int, delay int, forget int) int {
	m := (n << 1) + 10
	d := make([]int, m)
	cnt := make([]int, m)
	mod := int(1e9) + 7
	cnt[1] = 1
	for i := 1; i <= n; i++ {
		if cnt[i] == 0 {
			continue
		}
		d[i] = (d[i] + cnt[i]) % mod
		d[i+forget] = (d[i+forget] - cnt[i] + mod) % mod
		nxt := i + delay
		for nxt < i+forget {
			cnt[nxt] = (cnt[nxt] + cnt[i]) % mod
			nxt++
		}
	}
	ans := 0
	for i := 1; i <= n; i++ {
		ans = (ans + d[i]) % mod
	}
	return ans
}
```

#### TypeScript

```ts
function peopleAwareOfSecret(n: number, delay: number, forget: number): number {
    const mod = 1e9 + 7;
    const m = (n << 1) + 10;
    const d: number[] = Array(m).fill(0);
    const cnt: number[] = Array(m).fill(0);

    cnt[1] = 1;
    for (let i = 1; i <= n; ++i) {
        if (cnt[i] > 0) {
            d[i] = (d[i] + cnt[i]) % mod;
            d[i + forget] = (d[i + forget] - cnt[i] + mod) % mod;
            let nxt = i + delay;
            while (nxt < i + forget) {
                cnt[nxt] = (cnt[nxt] + cnt[i]) % mod;
                ++nxt;
            }
        }
    }

    let ans = 0;
    for (let i = 1; i <= n; ++i) {
        ans = (ans + d[i]) % mod;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn people_aware_of_secret(n: i32, delay: i32, forget: i32) -> i32 {
        let n = n as usize;
        let delay = delay as usize;
        let forget = forget as usize;
        let m = (n << 1) + 10;
        let modulo: i64 = 1_000_000_007;

        let mut d = vec![0i64; m];
        let mut cnt = vec![0i64; m];

        cnt[1] = 1;
        for i in 1..=n {
            if cnt[i] > 0 {
                d[i] = (d[i] + cnt[i]) % modulo;
                d[i + forget] = (d[i + forget] - cnt[i] + modulo) % modulo;
                let mut nxt = i + delay;
                while nxt < i + forget {
                    cnt[nxt] = (cnt[nxt] + cnt[i]) % modulo;
                    nxt += 1;
                }
            }
        }

        let mut ans: i64 = 0;
        for i in 1..=n {
            ans = (ans + d[i]) % modulo;
        }
        ans as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
