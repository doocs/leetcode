---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1977.Number%20of%20Ways%20to%20Separate%20Numbers/README_EN.md
rating: 2817
source: Biweekly Contest 59 Q4
tags:
    - String
    - Dynamic Programming
    - Suffix Array
---

<!-- problem:start -->

# [1977. Number of Ways to Separate Numbers](https://leetcode.com/problems/number-of-ways-to-separate-numbers)

[中文文档](/solution/1900-1999/1977.Number%20of%20Ways%20to%20Separate%20Numbers/README.md)

## Description

<!-- description:start -->

<p>You wrote down many <strong>positive</strong> integers in a string called <code>num</code>. However, you realized that you forgot to add commas to seperate the different numbers. You remember that the list of integers was <strong>non-decreasing</strong> and that <strong>no</strong> integer had leading zeros.</p>

<p>Return <em>the <strong>number of possible lists of integers</strong> that you could have written down to get the string </em><code>num</code>. Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;327&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> You could have written down the numbers:
3, 27
327
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;094&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> No numbers can have leading zeros and all numbers must be positive.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;0&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> No numbers can have leading zeros and all numbers must be positive.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 3500</code></li>
	<li><code>num</code> consists of digits <code>&#39;0&#39;</code> through <code>&#39;9&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming + Prefix Sum

Define $dp[i][j]$ to represent the number of ways to partition the first $i$ characters of the string `num` such that the length of the last number is $j$. Clearly, the answer is $\sum_{j=0}^{n} dp[n][j]$. The initial value is $dp[0][0] = 1$.

For $dp[i][j]$, the end of the previous number should be $i-j$. We can enumerate $dp[i-j][k]$, where $k \le j$. For the part where $k < j$, i.e., the number of ways with a length less than $j$ can be directly added to $dp[i][j]$, i.e., $dp[i][j] = \sum_{k=0}^{j-1} dp[i-j][k]$. Because the previous number is shorter, it means it is smaller than the current number. Here, prefix sum can be used for optimization.

However, when $k = j$, we need to compare the sizes of the two numbers of the same length. If the previous number is larger than the current number, this situation is invalid, and we should not add it to $dp[i][j]$. Otherwise, we can add it to $dp[i][j]$. Here, we can preprocess the "longest common prefix" in $O(n^2)$ time, and then compare the sizes of two numbers of the same length in $O(1)$ time.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$. Where $n$ is the length of the string `num`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfCombinations(self, num: str) -> int:
        def cmp(i, j, k):
            x = lcp[i][j]
            return x >= k or num[i + x] >= num[j + x]

        mod = 10**9 + 7
        n = len(num)
        lcp = [[0] * (n + 1) for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                if num[i] == num[j]:
                    lcp[i][j] = 1 + lcp[i + 1][j + 1]

        dp = [[0] * (n + 1) for _ in range(n + 1)]
        dp[0][0] = 1
        for i in range(1, n + 1):
            for j in range(1, i + 1):
                v = 0
                if num[i - j] != '0':
                    if i - j - j >= 0 and cmp(i - j, i - j - j, j):
                        v = dp[i - j][j]
                    else:
                        v = dp[i - j][min(j - 1, i - j)]
                dp[i][j] = (dp[i][j - 1] + v) % mod
        return dp[n][n]
```

#### Java

```java
class Solution {
    public int numberOfCombinations(String num) {
        final int mod = (int) 1e9 + 7;
        int n = num.length();
        int[][] lcp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (num.charAt(i) == num.charAt(j)) {
                    lcp[i][j] = 1 + lcp[i + 1][j + 1];
                }
            }
        }
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                int v = 0;
                if (num.charAt(i - j) != '0') {
                    if (i - j - j >= 0) {
                        int x = lcp[i - j][i - j - j];
                        if (x >= j || num.charAt(i - j + x) >= num.charAt(i - j - j + x)) {
                            v = dp[i - j][j];
                        }
                    }
                    if (v == 0) {
                        v = dp[i - j][Math.min(j - 1, i - j)];
                    }
                }
                dp[i][j] = (dp[i][j - 1] + v) % mod;
            }
        }
        return dp[n][n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfCombinations(string num) {
        const int mod = 1e9 + 7;
        int n = num.size();
        vector<vector<int>> lcp(n + 1, vector<int>(n + 1));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (num[i] == num[j]) {
                    lcp[i][j] = 1 + lcp[i + 1][j + 1];
                }
            }
        }
        auto cmp = [&](int i, int j, int k) {
            int x = lcp[i][j];
            return x >= k || num[i + x] >= num[j + x];
        };
        vector<vector<int>> dp(n + 1, vector<int>(n + 1));
        dp[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                int v = 0;
                if (num[i - j] != '0') {
                    if (i - j - j >= 0 && cmp(i - j, i - j - j, j)) {
                        v = dp[i - j][j];
                    } else {
                        v = dp[i - j][min(j - 1, i - j)];
                    }
                }
                dp[i][j] = (dp[i][j - 1] + v) % mod;
            }
        }
        return dp[n][n];
    }
};
```

#### Go

```go
func numberOfCombinations(num string) int {
	n := len(num)
	lcp := make([][]int, n+1)
	dp := make([][]int, n+1)
	for i := range lcp {
		lcp[i] = make([]int, n+1)
		dp[i] = make([]int, n+1)
	}
	for i := n - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			if num[i] == num[j] {
				lcp[i][j] = 1 + lcp[i+1][j+1]
			}
		}
	}
	cmp := func(i, j, k int) bool {
		x := lcp[i][j]
		return x >= k || num[i+x] >= num[j+x]
	}
	dp[0][0] = 1
	var mod int = 1e9 + 7
	for i := 1; i <= n; i++ {
		for j := 1; j <= i; j++ {
			v := 0
			if num[i-j] != '0' {
				if i-j-j >= 0 && cmp(i-j, i-j-j, j) {
					v = dp[i-j][j]
				} else {
					v = dp[i-j][min(j-1, i-j)]
				}
			}
			dp[i][j] = (dp[i][j-1] + v) % mod
		}
	}
	return dp[n][n]
}
```

#### TypeScript

```ts
function numberOfCombinations(num: string): number {
    const n: number = num.length;
    const mod: number = 1_000_000_007;

    const lcp: number[][] = Array.from({ length: n + 1 }, () => Array(n + 1).fill(0));
    const dp: number[][] = Array.from({ length: n + 1 }, () => Array(n + 1).fill(0));

    for (let i = n - 1; i >= 0; i--) {
        for (let j = n - 1; j >= 0; j--) {
            if (num[i] === num[j]) {
                lcp[i][j] = 1 + lcp[i + 1][j + 1];
            }
        }
    }

    function cmp(i: number, j: number, k: number): boolean {
        const x: number = lcp[i][j];
        return x >= k || num[i + x] >= num[j + x];
    }

    dp[0][0] = 1;

    for (let i = 1; i <= n; i++) {
        for (let j = 1; j <= i; j++) {
            let v: number = 0;
            if (num[i - j] !== '0') {
                if (i - j - j >= 0 && cmp(i - j, i - j - j, j)) {
                    v = dp[i - j][j];
                } else {
                    v = dp[i - j][Math.min(j - 1, i - j)];
                }
            }
            dp[i][j] = (dp[i][j - 1] + v) % mod;
        }
    }

    return dp[n][n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
