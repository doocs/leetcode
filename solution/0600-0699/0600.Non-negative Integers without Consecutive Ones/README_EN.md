---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README_EN.md
tags:
    - Dynamic Programming
---

<!-- problem:start -->

# [600. Non-negative Integers without Consecutive Ones](https://leetcode.com/problems/non-negative-integers-without-consecutive-ones)

[中文文档](/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README.md)

## Description

<!-- description:start -->

<p>Given a positive integer <code>n</code>, return the number of the integers in the range <code>[0, n]</code> whose binary representations <strong>do not</strong> contain consecutive ones.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> 5
<strong>Explanation:</strong>
Here are the non-negative integers &lt;= 5 with their corresponding binary representations:
0 : 0
1 : 1
2 : 10
3 : 11
4 : 100
5 : 101
Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Digit DP

This problem essentially asks for the number of numbers in the given range $[l, ..r]$ whose binary representation does not contain consecutive $1$s. The count is related to the number of digits and the value of each binary digit. We can use the concept of Digit DP to solve this problem. In Digit DP, the size of the number has little impact on the complexity.

For the range $[l, ..r]$ problem, we generally convert it to the problem of $[0, ..r]$ and then subtract the result of $[0, ..l - 1]$, i.e.:

$$
ans = \sum_{i=0}^{r} ans_i -  \sum_{i=0}^{l-1} ans_i
$$

However, for this problem, we only need to find the value for the range $[0, ..r]$.

Here, we use memoized search to implement Digit DP. The basic steps are as follows:

First, we get the binary length of the number $n$, denoted as $m$. Then, based on the problem information, we design a function $\textit{dfs}(i, \textit{pre}, \textit{limit})$, where:

-   The digit $i$ represents the current position being searched, starting from the highest digit, i.e., the first character of the binary string.
-   The digit $\textit{pre}$ represents the digit at the previous binary position. For this problem, the initial value of $\textit{pre}$ is $0$.
-   The boolean $\textit{limit}$ indicates whether the digits that can be filled are restricted. If there is no restriction, then we can choose $[0,1]$. Otherwise, we can only choose $[0, \textit{up}]$.

The function executes as follows:

If $i$ exceeds the length of the number $n$, i.e., $i < 0$, it means the search is over, directly return $1$. Otherwise, we enumerate the digits $j$ from $0$ to $\textit{up}$ for the position $i$. For each $j$:

-   If both $\textit{pre}$ and $j$ are $1$, it means there are consecutive $1$, so we skip it.
-   Otherwise, we recurse to the next level, update $\textit{pre}$ to $j$, and update $\textit{limit}$ to the logical AND of $\textit{limit}$ and whether $j$ equals $\textit{up}$.

Finally, we sum all the results from the recursive calls to the next level, which is the answer.

The time complexity is $O(\log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the given positive integer.

Similar problems:

-   [233. Number of Digit One](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0233.Number%20of%20Digit%20One/README_EN.md)
-   [357. Count Numbers with Unique Digits](https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README_EN.md)
-   [788. Rotated Digits](https://github.com/doocs/leetcode/blob/main/solution/0700-0799/0788.Rotated%20Digits/README_EN.md)
-   [902. Numbers At Most N Given Digit Set](https://github.com/doocs/leetcode/blob/main/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README_EN.md)
-   [1012. Numbers With Repeated Digits](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README_EN.md)
-   [2376. Count Special Integers](https://github.com/doocs/leetcode/blob/main/solution/2300-2399/2376.Count%20Special%20Integers/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findIntegers(self, n: int) -> int:
        @cache
        def dfs(i: int, pre: int, limit: bool) -> int:
            if i < 0:
                return 1
            up = (n >> i & 1) if limit else 1
            ans = 0
            for j in range(up + 1):
                if pre and j:
                    continue
                ans += dfs(i - 1, j, limit and j == up)
            return ans

        return dfs(n.bit_length() - 1, 0, True)
```

#### Java

```java
class Solution {
    private int n;
    private Integer[][] f;

    public int findIntegers(int n) {
        this.n = n;
        int m = Integer.SIZE - Integer.numberOfLeadingZeros(n);
        f = new Integer[m][2];
        return dfs(m - 1, 0, true);
    }

    private int dfs(int i, int pre, boolean limit) {
        if (i < 0) {
            return 1;
        }
        if (!limit && f[i][pre] != null) {
            return f[i][pre];
        }
        int up = limit ? (n >> i & 1) : 1;
        int ans = 0;
        for (int j = 0; j <= up; ++j) {
            if (j == 1 && pre == 1) {
                continue;
            }
            ans += dfs(i - 1, j, limit && j == up);
        }
        if (!limit) {
            f[i][pre] = ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findIntegers(int n) {
        int m = 32 - __builtin_clz(n);
        int f[m][2];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i, int pre, bool limit) -> int {
            if (i < 0) {
                return 1;
            }
            if (!limit && f[i][pre] != -1) {
                return f[i][pre];
            }
            int up = limit ? (n >> i & 1) : 1;
            int ans = 0;
            for (int j = 0; j <= up; ++j) {
                if (j && pre) {
                    continue;
                }
                ans += dfs(i - 1, j, limit && j == up);
            }
            if (!limit) {
                f[i][pre] = ans;
            }
            return ans;
        };
        return dfs(m - 1, 0, true);
    }
};
```

#### Go

```go
func findIntegers(n int) int {
	m := bits.Len(uint(n))
	f := make([][2]int, m)
	for i := range f {
		f[i] = [2]int{-1, -1}
	}
	var dfs func(i, pre int, limit bool) int
	dfs = func(i, pre int, limit bool) int {
		if i < 0 {
			return 1
		}
		if !limit && f[i][pre] != -1 {
			return f[i][pre]
		}
		up := 1
		if limit {
			up = n >> i & 1
		}
		ans := 0
		for j := 0; j <= up; j++ {
			if j == 1 && pre == 1 {
				continue
			}
			ans += dfs(i-1, j, limit && j == up)
		}
		if !limit {
			f[i][pre] = ans
		}
		return ans
	}
	return dfs(m-1, 0, true)
}
```

#### TypeScript

```ts
function findIntegers(n: number): number {
    const m = n.toString(2).length;
    const f: number[][] = Array.from({ length: m }, () => Array(2).fill(-1));
    const dfs = (i: number, pre: number, limit: boolean): number => {
        if (i < 0) {
            return 1;
        }
        if (!limit && f[i][pre] !== -1) {
            return f[i][pre];
        }
        const up = limit ? (n >> i) & 1 : 1;
        let ans = 0;
        for (let j = 0; j <= up; ++j) {
            if (pre === 1 && j === 1) {
                continue;
            }
            ans += dfs(i - 1, j, limit && j === up);
        }
        if (!limit) {
            f[i][pre] = ans;
        }
        return ans;
    };
    return dfs(m - 1, 0, true);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
