---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2147.Number%20of%20Ways%20to%20Divide%20a%20Long%20Corridor/README_EN.md
rating: 1914
source: Biweekly Contest 70 Q4
tags:
    - Math
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [2147. Number of Ways to Divide a Long Corridor](https://leetcode.com/problems/number-of-ways-to-divide-a-long-corridor)

[中文文档](/solution/2100-2199/2147.Number%20of%20Ways%20to%20Divide%20a%20Long%20Corridor/README.md)

## Description

<!-- description:start -->

<p>Along a long library corridor, there is a line of seats and decorative plants. You are given a <strong>0-indexed</strong> string <code>corridor</code> of length <code>n</code> consisting of letters <code>&#39;S&#39;</code> and <code>&#39;P&#39;</code> where each <code>&#39;S&#39;</code> represents a seat and each <code>&#39;P&#39;</code> represents a plant.</p>

<p>One room divider has <strong>already</strong> been installed to the left of index <code>0</code>, and <strong>another</strong> to the right of index <code>n - 1</code>. Additional room dividers can be installed. For each position between indices <code>i - 1</code> and <code>i</code> (<code>1 &lt;= i &lt;= n - 1</code>), at most one divider can be installed.</p>

<p>Divide the corridor into non-overlapping sections, where each section has <strong>exactly two seats</strong> with any number of plants. There may be multiple ways to perform the division. Two ways are <strong>different</strong> if there is a position with a room divider installed in the first way but not in the second way.</p>

<p>Return <em>the number of ways to divide the corridor</em>. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>. If there is no way, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2147.Number%20of%20Ways%20to%20Divide%20a%20Long%20Corridor/images/1.png" style="width: 410px; height: 199px;" />
<pre>
<strong>Input:</strong> corridor = &quot;SSPPSPS&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 3 different ways to divide the corridor.
The black bars in the above image indicate the two room dividers already installed.
Note that in each of the ways, <strong>each</strong> section has exactly <strong>two</strong> seats.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2147.Number%20of%20Ways%20to%20Divide%20a%20Long%20Corridor/images/2.png" style="width: 357px; height: 68px;" />
<pre>
<strong>Input:</strong> corridor = &quot;PPSPSP&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is only 1 way to divide the corridor, by not installing any additional dividers.
Installing any would create some section that does not have exactly two seats.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2147.Number%20of%20Ways%20to%20Divide%20a%20Long%20Corridor/images/3.png" style="width: 115px; height: 68px;" />
<pre>
<strong>Input:</strong> corridor = &quot;S&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no way to divide the corridor because there will always be a section that does not have exactly two seats.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == corridor.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>corridor[i]</code> is either <code>&#39;S&#39;</code> or <code>&#39;P&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search

We design a function $\textit{dfs}(i, k)$, which represents the number of ways to partition the corridor at the $i$-th position, having already placed $k$ screens. Then the answer is $\textit{dfs}(0, 0)$.

The calculation process of the function $\textit{dfs}(i, k)$ is as follows:

If $i \geq \textit{len}(\textit{corridor})$, it means the corridor has been fully traversed. At this point, if $k = 2$, it indicates that a valid partitioning scheme has been found, so return $1$. Otherwise, return $0$.

Otherwise, we need to consider the situation at the current position $i$:

-   If $\textit{corridor}[i] = \text{'S'}$, it means the current position is a seat, and we increment $k$ by $1$.
-   If $k > 2$, it means the number of screens placed at the current position exceeds $2$, so return $0$.
-   Otherwise, we can choose not to place a screen, i.e., $\textit{dfs}(i + 1, k)$. If $k = 2$, we can also choose to place a screen, i.e., $\textit{dfs}(i + 1, 0)$. We add the results of these two cases and take the result modulo $10^9 + 7$, i.e., $\textit{ans} = (\textit{ans} + \textit{dfs}(i + 1, k)) \bmod \text{mod}$.

Finally, we return $\textit{dfs}(0, 0)$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the corridor.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfWays(self, corridor: str) -> int:
        @cache
        def dfs(i: int, k: int) -> int:
            if i >= len(corridor):
                return int(k == 2)
            k += int(corridor[i] == "S")
            if k > 2:
                return 0
            ans = dfs(i + 1, k)
            if k == 2:
                ans = (ans + dfs(i + 1, 0)) % mod
            return ans

        mod = 10**9 + 7
        ans = dfs(0, 0)
        dfs.cache_clear()
        return ans
```

#### Java

```java
class Solution {
    private int n;
    private char[] s;
    private Integer[][] f;
    private final int mod = (int) 1e9 + 7;

    public int numberOfWays(String corridor) {
        s = corridor.toCharArray();
        n = s.length;
        f = new Integer[n][3];
        return dfs(0, 0);
    }

    private int dfs(int i, int k) {
        if (i >= n) {
            return k == 2 ? 1 : 0;
        }
        if (f[i][k] != null) {
            return f[i][k];
        }
        k += s[i] == 'S' ? 1 : 0;
        if (k > 2) {
            return 0;
        }
        int ans = dfs(i + 1, k);
        if (k == 2) {
            ans = (ans + dfs(i + 1, 0)) % mod;
        }
        return f[i][k] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfWays(string corridor) {
        int n = corridor.size();
        int f[n][3];
        memset(f, -1, sizeof(f));
        const int mod = 1e9 + 7;
        auto dfs = [&](this auto&& dfs, int i, int k) -> int {
            if (i >= n) {
                return k == 2;
            }
            if (f[i][k] != -1) {
                return f[i][k];
            }
            k += corridor[i] == 'S';
            if (k > 2) {
                return 0;
            }
            f[i][k] = dfs(i + 1, k);
            if (k == 2) {
                f[i][k] = (f[i][k] + dfs(i + 1, 0)) % mod;
            }
            return f[i][k];
        };
        return dfs(0, 0);
    }
};
```

#### Go

```go
func numberOfWays(corridor string) int {
	n := len(corridor)
	f := make([][3]int, n)
	for i := range f {
		f[i] = [3]int{-1, -1, -1}
	}
	const mod = 1e9 + 7
	var dfs func(int, int) int
	dfs = func(i, k int) int {
		if i >= n {
			if k == 2 {
				return 1
			}
			return 0
		}
		if f[i][k] != -1 {
			return f[i][k]
		}
		if corridor[i] == 'S' {
			k++
		}
		if k > 2 {
			return 0
		}
		f[i][k] = dfs(i+1, k)
		if k == 2 {
			f[i][k] = (f[i][k] + dfs(i+1, 0)) % mod
		}
		return f[i][k]
	}
	return dfs(0, 0)
}
```

#### TypeScript

```ts
function numberOfWays(corridor: string): number {
    const n = corridor.length;
    const mod = 10 ** 9 + 7;
    const f: number[][] = Array.from({ length: n }, () => Array(3).fill(-1));
    const dfs = (i: number, k: number): number => {
        if (i >= n) {
            return k === 2 ? 1 : 0;
        }
        if (f[i][k] !== -1) {
            return f[i][k];
        }
        if (corridor[i] === 'S') {
            ++k;
        }
        if (k > 2) {
            return (f[i][k] = 0);
        }
        f[i][k] = dfs(i + 1, k);
        if (k === 2) {
            f[i][k] = (f[i][k] + dfs(i + 1, 0)) % mod;
        }
        return f[i][k];
    };
    return dfs(0, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Mathematics

We can divide every two seats into a group. Between two adjacent groups of seats, if the distance between the last seat of the previous group and the first seat of the next group is $x$, then there are $x$ ways to place the screen.

We traverse the corridor, using a variable $\textit{cnt}$ to record the current number of seats, and a variable $\textit{last}$ to record the position of the last seat.

When we encounter a seat, we increment $\textit{cnt}$ by $1$. If $\textit{cnt}$ is greater than $2$ and $\textit{cnt}$ is odd, then we need to place a screen between $\textit{last}$ and the current seat. The number of ways to do this is $\textit{ans} \times (i - \textit{last})$, where $\textit{ans}$ is the previous number of ways. Then, we update $\textit{last}$ to the current seat's position $i$.

Finally, if $\textit{cnt}$ is greater than $0$ and $\textit{cnt}$ is even, return $\textit{ans}$; otherwise, return $0$.

The time complexity is $O(n)$, where $n$ is the length of the corridor. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfWays(self, corridor: str) -> int:
        mod = 10**9 + 7
        ans, cnt, last = 1, 0, 0
        for i, c in enumerate(corridor):
            if c == "S":
                cnt += 1
                if cnt > 2 and cnt % 2:
                    ans = ans * (i - last) % mod
                last = i
        return ans if cnt and cnt % 2 == 0 else 0
```

#### Java

```java
class Solution {
    public int numberOfWays(String corridor) {
        final int mod = (int) 1e9 + 7;
        long ans = 1, cnt = 0, last = 0;
        for (int i = 0; i < corridor.length(); ++i) {
            if (corridor.charAt(i) == 'S') {
                if (++cnt > 2 && cnt % 2 == 1) {
                    ans = ans * (i - last) % mod;
                }
                last = i;
            }
        }
        return cnt > 0 && cnt % 2 == 0 ? (int) ans : 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfWays(string corridor) {
        const int mod = 1e9 + 7;
        long long ans = 1;
        int cnt = 0, last = 0;
        for (int i = 0; i < corridor.length(); ++i) {
            if (corridor[i] == 'S') {
                if (++cnt > 2 && cnt % 2) {
                    ans = ans * (i - last) % mod;
                }
                last = i;
            }
        }
        return cnt > 0 && cnt % 2 == 0 ? ans : 0;
    }
};
```

#### Go

```go
func numberOfWays(corridor string) int {
	const mod int = 1e9 + 7
	ans, cnt, last := 1, 0, 0
	for i, c := range corridor {
		if c == 'S' {
			cnt++
			if cnt > 2 && cnt%2 == 1 {
				ans = ans * (i - last) % mod
			}
			last = i
		}
	}
	if cnt > 0 && cnt%2 == 0 {
		return ans
	}
	return 0
}
```

#### TypeScript

```ts
function numberOfWays(corridor: string): number {
    const mod = 10 ** 9 + 7;
    const n = corridor.length;
    let [ans, cnt, last] = [1, 0, 0];
    for (let i = 0; i < n; ++i) {
        if (corridor[i] === 'S') {
            if (++cnt > 2 && cnt % 2) {
                ans = (ans * (i - last)) % mod;
            }
            last = i;
        }
    }
    return cnt > 0 && cnt % 2 === 0 ? ans : 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
