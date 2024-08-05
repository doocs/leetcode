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

This problem is essentially about finding the number of numbers in the given interval $[l,..r]$ whose binary representation does not contain consecutive $1$s. The count is related to the number of digits and the digits in each binary position. We can solve this problem using the digit DP approach. In digit DP, the size of the number has little impact on the complexity.

For the interval $[l,..r]$ problem, we generally convert it to the problem of $[0,..r]$ and then subtract the problem of $[0,..l - 1]$, i.e.:

$$
ans = \sum_{i=0}^{r} ans_i -  \sum_{i=0}^{l-1} ans_i
$$

However, for this problem, we only need to find the value for the interval $[0,..r]$.

Here we use memoization to implement digit DP. The basic steps are as follows:

1. Convert the number $n$ to a binary string $s$;
1. According to the problem information, design the function $\textit{dfs}()$. For this problem, we define $\textit{dfs}(\textit{pos}, \textit{pre}, \textit{limit})$, and the answer is $\textit{dfs}(\textit{0}, 0, \textit{true})$.

Where:

-   `pos` represents the digit position, starting from the highest digit of the number, i.e., the first character of the binary string;
-   `pre` represents the digit at the current binary position. For this problem, the initial value of `pre` is `0`;
-   `limit` represents the restriction on the digits that can be filled. If there is no restriction, then $[0,1]$ can be chosen; otherwise, only $[0,..s[\textit{pos}]]$ can be chosen.

For the implementation details of the function, refer to the code below.

Time complexity is $O(\log n)$, and space complexity is $O(\log n)$. Here, $n$ is the number given in the problem.

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
        def dfs(pos: int, pre: int, limit: bool) -> int:
            if pos == len(s):
                return 1
            up = int(s[pos]) if limit else 1
            ans = 0
            for i in range(up + 1):
                if pre == 1 and i == 1:
                    continue
                ans += dfs(pos + 1, i, limit and i == up)
            return ans

        s = bin(n)[2:]
        return dfs(0, 0, True)
```

#### Java

```java
class Solution {
    private char[] s;
    private Integer[][] f;

    public int findIntegers(int n) {
        s = Integer.toBinaryString(n).toCharArray();
        f = new Integer[s.length][2];
        return dfs(0, 0, true);
    }

    private int dfs(int pos, int pre, boolean limit) {
        if (pos >= s.length) {
            return 1;
        }
        if (!limit && f[pos][pre] != null) {
            return f[pos][pre];
        }
        int up = limit ? s[pos] - '0' : 1;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            if (!(pre == 1 && i == 1)) {
                ans += dfs(pos + 1, i, limit && i == up);
            }
        }
        if (!limit) {
            f[pos][pre] = ans;
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
        string s = bitset<32>(n).to_string();
        s = s.substr(s.find('1'));
        int m = s.size();
        int f[m][2];
        memset(f, -1, sizeof(f));
        auto dfs = [&](auto&& dfs, int pos, int pre, bool limit) -> int {
            if (pos >= m) {
                return 1;
            }
            if (!limit && f[pos][pre] != -1) {
                return f[pos][pre];
            }
            int up = limit ? s[pos] - '0' : 1;
            int ans = 0;
            for (int i = 0; i <= up; ++i) {
                if (!(pre == 1 && i == 1)) {
                    ans += dfs(dfs, pos + 1, i, limit && i == up);
                }
            }
            if (!limit) {
                f[pos][pre] = ans;
            }
            return ans;
        };
        return dfs(dfs, 0, 0, true);
    }
};
```

#### Go

```go
func findIntegers(n int) int {
	s := strconv.FormatInt(int64(n), 2)
	m := len(s)
	f := make([][]int, m)
	for i := range f {
		f[i] = []int{-1, -1}
	}
	var dfs func(int, int, bool) int
	dfs = func(pos int, pre int, limit bool) int {
		if pos >= m {
			return 1
		}
		if !limit && f[pos][pre] != -1 {
			return f[pos][pre]
		}
		up := 1
		if limit {
			up = int(s[pos] - '0')
		}
		ans := 0
		for i := 0; i <= up; i++ {
			if !(pre == 1 && i == 1) {
				ans += dfs(pos+1, i, limit && i == up)
			}
		}
		if !limit {
			f[pos][pre] = ans
		}
		return ans
	}
	return dfs(0, 0, true)
}
```

#### TypeScript

```ts
function findIntegers(n: number): number {
    const s = n.toString(2);
    const m = s.length;
    const f: number[][] = Array.from({ length: m }, () => [-1, -1]);

    function dfs(pos: number, pre: number, limit: boolean): number {
        if (pos >= m) {
            return 1;
        }
        if (!limit && f[pos][pre] !== -1) {
            return f[pos][pre];
        }
        const up = limit ? parseInt(s[pos]) : 1;
        let ans = 0;
        for (let i = 0; i <= up; ++i) {
            if (!(pre === 1 && i === 1)) {
                ans += dfs(pos + 1, i, limit && i === up);
            }
        }
        if (!limit) {
            f[pos][pre] = ans;
        }
        return ans;
    }

    return dfs(0, 0, true);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
