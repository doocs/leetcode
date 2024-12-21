---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README_EN.md
rating: 2230
source: Weekly Contest 128 Q4
tags:
    - Math
    - Dynamic Programming
---

<!-- problem:start -->

# [1012. Numbers With Repeated Digits](https://leetcode.com/problems/numbers-with-repeated-digits)

[中文文档](/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README.md)

## Description

<!-- description:start -->

<p>Given an integer <code>n</code>, return <em>the number of positive integers in the range </em><code>[1, n]</code><em> that have <strong>at least one</strong> repeated digit</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 20
<strong>Output:</strong> 1
<strong>Explanation:</strong> The only positive number (&lt;= 20) with at least 1 repeated digit is 11.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 100
<strong>Output:</strong> 10
<strong>Explanation:</strong> The positive numbers (&lt;= 100) with atleast 1 repeated digit are 11, 22, 33, 44, 55, 66, 77, 88, 99, and 100.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 1000
<strong>Output:</strong> 262
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: State Compression + Digit DP

The problem requires counting the number of integers in the range $[1, .., n]$ that have at least one repeated digit. We can approach this by defining a function $f(n)$ that counts the number of integers in the range $[1, .., n]$ with no repeated digits. Then, the answer is $n - f(n)$.

Additionally, we can use a binary number to record the digits that have appeared in the number. For example, if the digits $1$, $2$, and $4$ have appeared, the corresponding binary number is $\underline{1}0\underline{1}\underline{1}0$.

Next, we use memoization to implement digit DP. We start searching from the top, get the number of solutions at the bottom, and return the answers layer by layer until we get the final answer from the starting point.

The basic steps are as follows:

We convert the number $n$ into a string $s$. Next, we design a function $\textit{dfs}(i, \textit{mask}, \textit{lead}, \textit{limit})$, where:

-   The integer $i$ represents the current digit index, starting from $0$.
-   The integer $\textit{mask}$ represents the digits that have appeared so far, using a binary number. The $j$-th bit of $\textit{mask}$ being $1$ indicates that digit $j$ has appeared, while $0$ indicates it has not.
-   The boolean $\textit{lead}$ indicates whether the current number contains only leading zeros.
-   The boolean $\textit{limit}$ indicates whether the current position is restricted by the upper bound.

The function executes as follows:

If $i$ is greater than or equal to $m$, it means we have processed all digits. If $\textit{lead}$ is true, it means the current number is a leading zero, and we should return $0$. Otherwise, we should return $1$.

Otherwise, we calculate the upper bound $\textit{up}$. If $\textit{limit}$ is true, then $\textit{up}$ is the digit corresponding to $s[i]$. Otherwise, $\textit{up}$ is $9$.

Then, we enumerate the current digit $j$ in the range $[0, \textit{up}]$. If $j$ is $0$ and $\textit{lead}$ is true, we recursively calculate $\textit{dfs}(i + 1, \textit{mask}, \text{true}, \textit{limit} \wedge j = \textit{up})$. Otherwise, if the $j$-th bit of $\textit{mask}$ is $0$, we recursively calculate $\textit{dfs}(i + 1, \textit{mask} \,|\, 2^j, \text{false}, \textit{limit} \wedge j = \textit{up})$. We accumulate all the results as the answer.

The answer is $n - \textit{dfs}(0, 0, \text{true}, \text{true})$.

The time complexity is $O(\log n \times 2^D \times D)$, and the space complexity is $O(\log n \times 2^D)$. Here, $D = 10$.

Similar problems:

-   [233. Number of Digit One](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0233.Number%20of%20Digit%20One/README_EN.md)
-   [357. Count Numbers with Unique Digits](https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README_EN.md)
-   [600. Non-negative Integers without Consecutive Ones](https://github.com/doocs/leetcode/blob/main/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README_EN.md)
-   [788. Rotated Digits](https://github.com/doocs/leetcode/blob/main/solution/0700-0799/0788.Rotated%20Digits/README_EN.md)
-   [902. Numbers At Most N Given Digit Set](https://github.com/doocs/leetcode/blob/main/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README_EN.md)
-   [2376. Count Special Integers](https://github.com/doocs/leetcode/blob/main/solution/2300-2399/2376.Count%20Special%20Integers/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numDupDigitsAtMostN(self, n: int) -> int:
        @cache
        def dfs(i: int, mask: int, lead: bool, limit: bool) -> int:
            if i >= len(s):
                return lead ^ 1
            up = int(s[i]) if limit else 9
            ans = 0
            for j in range(up + 1):
                if lead and j == 0:
                    ans += dfs(i + 1, mask, True, False)
                elif mask >> j & 1 ^ 1:
                    ans += dfs(i + 1, mask | 1 << j, False, limit and j == up)
            return ans

        s = str(n)
        return n - dfs(0, 0, True, True)
```

#### Java

```java
class Solution {
    private char[] s;
    private Integer[][] f;

    public int numDupDigitsAtMostN(int n) {
        s = String.valueOf(n).toCharArray();
        f = new Integer[s.length][1 << 10];
        return n - dfs(0, 0, true, true);
    }

    private int dfs(int i, int mask, boolean lead, boolean limit) {
        if (i >= s.length) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && f[i][mask] != null) {
            return f[i][mask];
        }
        int up = limit ? s[i] - '0' : 9;
        int ans = 0;
        for (int j = 0; j <= up; ++j) {
            if (lead && j == 0) {
                ans += dfs(i + 1, mask, true, false);
            } else if ((mask >> j & 1) == 0) {
                ans += dfs(i + 1, mask | 1 << j, false, limit && j == up);
            }
        }
        if (!lead && !limit) {
            f[i][mask] = ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numDupDigitsAtMostN(int n) {
        string s = to_string(n);
        int m = s.size();
        int f[m][1 << 10];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i, int mask, bool lead, bool limit) -> int {
            if (i >= m) {
                return lead ^ 1;
            }
            if (!lead && !limit && f[i][mask] != -1) {
                return f[i][mask];
            }
            int up = limit ? s[i] - '0' : 9;
            int ans = 0;
            for (int j = 0; j <= up; ++j) {
                if (lead && j == 0) {
                    ans += dfs(i + 1, mask, true, limit && j == up);
                } else if (mask >> j & 1 ^ 1) {
                    ans += dfs(i + 1, mask | (1 << j), false, limit && j == up);
                }
            }
            if (!lead && !limit) {
                f[i][mask] = ans;
            }
            return ans;
        };
        return n - dfs(0, 0, true, true);
    }
};
```

#### Go

```go
func numDupDigitsAtMostN(n int) int {
	s := []byte(strconv.Itoa(n))
	m := len(s)
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, 1<<10)
		for j := range f[i] {
			f[i][j] = -1
		}
	}

	var dfs func(i, mask int, lead, limit bool) int
	dfs = func(i, mask int, lead, limit bool) int {
		if i >= m {
			if lead {
				return 0
			}
			return 1
		}
		if !lead && !limit && f[i][mask] != -1 {
			return f[i][mask]
		}
		up := 9
		if limit {
			up = int(s[i] - '0')
		}
		ans := 0
		for j := 0; j <= up; j++ {
			if lead && j == 0 {
				ans += dfs(i+1, mask, true, limit && j == up)
			} else if mask>>j&1 == 0 {
				ans += dfs(i+1, mask|(1<<j), false, limit && j == up)
			}
		}
		if !lead && !limit {
			f[i][mask] = ans
		}
		return ans
	}
	return n - dfs(0, 0, true, true)
}
```

#### TypeScript

```ts
function numDupDigitsAtMostN(n: number): number {
    const s = n.toString();
    const m = s.length;
    const f = Array.from({ length: m }, () => Array(1 << 10).fill(-1));

    const dfs = (i: number, mask: number, lead: boolean, limit: boolean): number => {
        if (i >= m) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && f[i][mask] !== -1) {
            return f[i][mask];
        }
        const up = limit ? parseInt(s[i]) : 9;
        let ans = 0;
        for (let j = 0; j <= up; j++) {
            if (lead && j === 0) {
                ans += dfs(i + 1, mask, true, limit && j === up);
            } else if (((mask >> j) & 1) === 0) {
                ans += dfs(i + 1, mask | (1 << j), false, limit && j === up);
            }
        }
        if (!lead && !limit) {
            f[i][mask] = ans;
        }
        return ans;
    };

    return n - dfs(0, 0, true, true);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
