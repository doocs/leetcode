---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2376.Count%20Special%20Integers/README_EN.md
rating: 2120
source: Weekly Contest 306 Q4
tags:
    - Math
    - Dynamic Programming
---

<!-- problem:start -->

# [2376. Count Special Integers](https://leetcode.com/problems/count-special-integers)

[中文文档](/solution/2300-2399/2376.Count%20Special%20Integers/README.md)

## Description

<!-- description:start -->

<p>We call a positive integer <strong>special</strong> if all of its digits are <strong>distinct</strong>.</p>

<p>Given a <strong>positive</strong> integer <code>n</code>, return <em>the number of special integers that belong to the interval </em><code>[1, n]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 20
<strong>Output:</strong> 19
<strong>Explanation:</strong> All the integers from 1 to 20, except 11, are special. Thus, there are 19 special integers.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> 5
<strong>Explanation:</strong> All the integers from 1 to 5 are special.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 135
<strong>Output:</strong> 110
<strong>Explanation:</strong> There are 110 integers from 1 to 135 that are special.
Some of the integers that are not special are: 22, 114, and 131.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: State Compression + Digit DP

This problem essentially asks for the number of numbers in the given range $[l, ..r]$ that satisfy certain conditions. The conditions are related to the composition of the numbers rather than their size, so we can use the concept of Digit DP to solve it. In Digit DP, the size of the number has little impact on the complexity.

For the range $[l, ..r]$ problem, we generally convert it to the problem of $[1, ..r]$ and then subtract the result of $[1, ..l - 1]$, i.e.:

$$
ans = \sum_{i=1}^{r} ans_i -  \sum_{i=1}^{l-1} ans_i
$$

However, for this problem, we only need to find the value for the range $[1, ..n]$.

Here, we use memoized search to implement Digit DP. We search from the starting point downwards, and at the lowest level, we get the number of solutions. We then return the answers layer by layer upwards, and finally get the final answer from the starting point of the search.

Based on the problem information, we design a function $\textit{dfs}(i, \textit{mask}, \textit{lead}, \textit{limit})$, where:

-   The digit $i$ represents the current position being searched, starting from the highest digit, i.e., $i = 0$ represents the highest digit.
-   The digit $\textit{mask}$ represents the current state of the number, i.e., the $j$-th bit of $\textit{mask}$ being $1$ indicates that the digit $j$ has been used.
-   The boolean $\textit{lead}$ indicates whether the current number only contains leading $0$s.
-   The boolean $\textit{limit}$ indicates whether the current number is restricted by the upper bound.

The function executes as follows:

If $i$ exceeds the length of the number $n$, it means the search is over. If $\textit{lead}$ is true, it means the current number only contains leading $0$s, so return $0$. Otherwise, return $1$.

If $\textit{limit}$ is false and $\textit{lead}$ is false and the state of $\textit{mask}$ has been memoized, directly return the memoized result.

Otherwise, we calculate the current upper bound $up$. If $\textit{limit}$ is true, $up$ is the $i$-th digit of the current number. Otherwise, $up = 9$.

Then we iterate over $[0, up]$. For each digit $j$, if the $j$-th bit of $\textit{mask}$ is $1$, it means the digit $j$ has been used, so we skip it. Otherwise, if $\textit{lead}$ is true and $j = 0$, it means the current number only contains leading $0$s, so we recursively search the next digit. Otherwise, we recursively search the next digit and update the state of $\textit{mask}$.

Finally, if $\textit{limit}$ is false and $\textit{lead}$ is false, memoize the current state.

Return the final answer.

The time complexity is $O(m \times 2^D \times D)$, and the space complexity is $O(m \times 2^D)$. Here, $m$ is the length of the number $n$, and $D = 10$.

Similar Problems:

-   [233. Number of Digit One](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0233.Number%20of%20Digit%20One/README_EN.md)
-   [357. Count Numbers with Unique Digits](https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README_EN.md)
-   [600. Non-negative Integers without Consecutive Ones](https://github.com/doocs/leetcode/blob/main/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README_EN.md)
-   [788. Rotated Digits](https://github.com/doocs/leetcode/blob/main/solution/0700-0799/0788.Rotated%20Digits/README_EN.md)
-   [902. Numbers At Most N Given Digit Set](https://github.com/doocs/leetcode/blob/main/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README_EN.md)
-   [1012. Numbers with Repeated Digits](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSpecialNumbers(self, n: int) -> int:
        @cache
        def dfs(i: int, mask: int, lead: bool, limit: bool) -> int:
            if i >= len(s):
                return int(lead ^ 1)
            up = int(s[i]) if limit else 9
            ans = 0
            for j in range(up + 1):
                if mask >> j & 1:
                    continue
                if lead and j == 0:
                    ans += dfs(i + 1, mask, True, limit and j == up)
                else:
                    ans += dfs(i + 1, mask | 1 << j, False, limit and j == up)
            return ans

        s = str(n)
        return dfs(0, 0, True, True)
```

#### Java

```java
class Solution {
    private char[] s;
    private Integer[][] f;

    public int countSpecialNumbers(int n) {
        s = String.valueOf(n).toCharArray();
        f = new Integer[s.length][1 << 10];
        return dfs(0, 0, true, true);
    }

    private int dfs(int i, int mask, boolean lead, boolean limit) {
        if (i >= s.length) {
            return lead ? 0 : 1;
        }
        if (!limit && !lead && f[i][mask] != null) {
            return f[i][mask];
        }
        int up = limit ? s[i] - '0' : 9;
        int ans = 0;
        for (int j = 0; j <= up; ++j) {
            if ((mask >> j & 1) == 1) {
                continue;
            }
            if (lead && j == 0) {
                ans += dfs(i + 1, mask, true, limit && j == up);
            } else {
                ans += dfs(i + 1, mask | (1 << j), false, limit && j == up);
            }
        }
        if (!limit && !lead) {
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
    int countSpecialNumbers(int n) {
        string s = to_string(n);
        int m = s.size();
        int f[m][1 << 10];
        memset(f, -1, sizeof(f));
        auto dfs = [&](auto&& dfs, int i, int mask, bool lead, bool limit) -> int {
            if (i >= m) {
                return lead ^ 1;
            }
            if (!limit && !lead && f[i][mask] != -1) {
                return f[i][mask];
            }
            int up = limit ? s[i] - '0' : 9;
            int ans = 0;
            for (int j = 0; j <= up; ++j) {
                if (mask >> j & 1) {
                    continue;
                }
                if (lead && j == 0) {
                    ans += dfs(dfs, i + 1, mask, true, limit && j == up);
                } else {
                    ans += dfs(dfs, i + 1, mask | (1 << j), false, limit && j == up);
                }
            }
            if (!limit && !lead) {
                f[i][mask] = ans;
            }
            return ans;
        };
        return dfs(dfs, 0, 0, true, true);
    }
};
```

#### Go

```go
func countSpecialNumbers(n int) int {
	s := strconv.Itoa(n)
	m := len(s)
	f := make([][1 << 10]int, m+1)
	for i := range f {
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(int, int, bool, bool) int
	dfs = func(i, mask int, lead, limit bool) int {
		if i >= m {
			if lead {
				return 0
			}
			return 1
		}
		if !limit && !lead && f[i][mask] != -1 {
			return f[i][mask]
		}
		up := 9
		if limit {
			up = int(s[i] - '0')
		}
		ans := 0
		for j := 0; j <= up; j++ {
			if mask>>j&1 == 1 {
				continue
			}
			if lead && j == 0 {
				ans += dfs(i+1, mask, true, limit && j == up)
			} else {
				ans += dfs(i+1, mask|1<<j, false, limit && j == up)
			}
		}
		if !limit && !lead {
			f[i][mask] = ans
		}
		return ans
	}
	return dfs(0, 0, true, true)
}
```

#### TypeScript

```ts
function countSpecialNumbers(n: number): number {
    const s = n.toString();
    const m = s.length;
    const f: number[][] = Array.from({ length: m }, () => Array(1 << 10).fill(-1));
    const dfs = (i: number, mask: number, lead: boolean, limit: boolean): number => {
        if (i >= m) {
            return lead ? 0 : 1;
        }
        if (!limit && !lead && f[i][mask] !== -1) {
            return f[i][mask];
        }
        const up = limit ? +s[i] : 9;
        let ans = 0;
        for (let j = 0; j <= up; ++j) {
            if ((mask >> j) & 1) {
                continue;
            }
            if (lead && j === 0) {
                ans += dfs(i + 1, mask, true, limit && j === up);
            } else {
                ans += dfs(i + 1, mask | (1 << j), false, limit && j === up);
            }
        }
        if (!limit && !lead) {
            f[i][mask] = ans;
        }
        return ans;
    };
    return dfs(0, 0, true, true);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
