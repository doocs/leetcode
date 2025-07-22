---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0788.Rotated%20Digits/README_EN.md
tags:
    - Math
    - Dynamic Programming
---

<!-- problem:start -->

# [788. Rotated Digits](https://leetcode.com/problems/rotated-digits)

[中文文档](/solution/0700-0799/0788.Rotated%20Digits/README.md)

## Description

<!-- description:start -->

<p>An integer <code>x</code> is a <strong>good</strong> if after rotating each digit individually by 180 degrees, we get a valid number that is different from <code>x</code>. Each digit must be rotated - we cannot choose to leave it alone.</p>

<p>A number is valid if each digit remains a digit after rotation. For example:</p>

<ul>
	<li><code>0</code>, <code>1</code>, and <code>8</code> rotate to themselves,</li>
	<li><code>2</code> and <code>5</code> rotate to each other (in this case they are rotated in a different direction, in other words, <code>2</code> or <code>5</code> gets mirrored),</li>
	<li><code>6</code> and <code>9</code> rotate to each other, and</li>
	<li>the rest of the numbers do not rotate to any other number and become invalid.</li>
</ul>

<p>Given an integer <code>n</code>, return <em>the number of <strong>good</strong> integers in the range </em><code>[1, n]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 0
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Direct Enumeration

An intuitive and effective approach is to directly enumerate each number in $[1,2,..n]$ and determine whether it is a good number. If it is a good number, increment the answer by one.

The key to the problem is how to determine whether a number $x$ is a good number. The logic is as follows:

We first use an array $d$ of length 10 to record the rotated digits corresponding to each valid digit. In this problem, the valid digits are $[0, 1, 8, 2, 5, 6, 9]$, which correspond to the rotated digits $[0, 1, 8, 5, 2, 9, 6]$ respectively. If a digit is not valid, we set the corresponding rotated digit to $-1$.

Then, we traverse each digit $v$ of the number $x$. If $v$ is not a valid digit, it means $x$ is not a good number, and we directly return $\textit{false}$. Otherwise, we add the rotated digit $d[v]$ corresponding to the digit $v$ to $y$. Finally, we check whether $x$ and $y$ are equal. If they are not equal, it means $x$ is a good number, and we return $\textit{true}$.

The time complexity is $O(n \times \log n)$, where $n$ is the given number. The space complexity is $O(1)$.

Similar problems:

-   [1056. Confusing Number](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1056.Confusing%20Number/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rotatedDigits(self, n: int) -> int:
        def check(x):
            y, t = 0, x
            k = 1
            while t:
                v = t % 10
                if d[v] == -1:
                    return False
                y = d[v] * k + y
                k *= 10
                t //= 10
            return x != y

        d = [0, 1, 5, -1, -1, 2, 9, -1, 8, 6]
        return sum(check(i) for i in range(1, n + 1))
```

#### Java

```java
class Solution {
    private int[] d = new int[] {0, 1, 5, -1, -1, 2, 9, -1, 8, 6};

    public int rotatedDigits(int n) {
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            if (check(i)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(int x) {
        int y = 0, t = x;
        int k = 1;
        while (t > 0) {
            int v = t % 10;
            if (d[v] == -1) {
                return false;
            }
            y = d[v] * k + y;
            k *= 10;
            t /= 10;
        }
        return x != y;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int rotatedDigits(int n) {
        int d[10] = {0, 1, 5, -1, -1, 2, 9, -1, 8, 6};
        auto check = [&](int x) -> bool {
            int y = 0, t = x;
            int k = 1;
            while (t) {
                int v = t % 10;
                if (d[v] == -1) {
                    return false;
                }
                y = d[v] * k + y;
                k *= 10;
                t /= 10;
            }
            return x != y;
        };
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            ans += check(i);
        }
        return ans;
    }
};
```

#### Go

```go
func rotatedDigits(n int) int {
	d := []int{0, 1, 5, -1, -1, 2, 9, -1, 8, 6}
	check := func(x int) bool {
		y, t := 0, x
		k := 1
		for ; t > 0; t /= 10 {
			v := t % 10
			if d[v] == -1 {
				return false
			}
			y = d[v]*k + y
			k *= 10
		}
		return x != y
	}
	ans := 0
	for i := 1; i <= n; i++ {
		if check(i) {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function rotatedDigits(n: number): number {
    const d: number[] = [0, 1, 5, -1, -1, 2, 9, -1, 8, 6];
    const check = (x: number): boolean => {
        let y = 0;
        let t = x;
        let k = 1;

        while (t > 0) {
            const v = t % 10;
            if (d[v] === -1) {
                return false;
            }
            y = d[v] * k + y;
            k *= 10;
            t = Math.floor(t / 10);
        }
        return x !== y;
    };
    return Array.from({ length: n }, (_, i) => i + 1).filter(check).length;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Digit DP

Solution 1 is sufficient to solve this problem, but its time complexity is relatively high. If the data range of the problem reaches the level of $10^9$, the approach in Solution 1 will exceed the time limit.

This problem essentially asks for the number of numbers in the given range $[l, ..r]$ that satisfy certain conditions. The conditions are related to the composition of the numbers rather than their size, so we can use the concept of Digit DP to solve it. In Digit DP, the size of the number has little impact on the complexity.

For the range $[l, ..r]$ problem, we generally convert it to the problem of $[1, ..r]$ and then subtract the result of $[1, ..l - 1]$, i.e.:

$$
ans = \sum_{i=1}^{r} ans_i -  \sum_{i=1}^{l-1} ans_i
$$

However, for this problem, we only need to find the value for the range $[1, ..r]$.

Here, we use memoized search to implement Digit DP. We search from the starting point downwards, and at the lowest level, we get the number of solutions. We then return the answers layer by layer upwards, and finally get the final answer from the starting point of the search.

The basic steps are as follows:

We convert the number $n$ to a string $s$. Then we define a function $\textit{dfs}(i, \textit{ok}, \textit{limit})$, where $i$ represents the digit position, $\textit{ok}$ indicates whether the current number satisfies the problem's conditions, and $\textit{limit}$ is a boolean indicating whether the digits that can be filled are restricted.

The function executes as follows:

If $i$ is greater than or equal to the length of the string $s$, return $\textit{ok}$;

Otherwise, we get the current digit $up$. If $\textit{limit}$ is $\textit{true}$, $up$ is the current digit; otherwise, $up$ is $9$;

Next, we iterate over $[0, ..up]$. If $j$ is a valid digit $[0, 1, 8]$, we recursively call $\textit{dfs}(i + 1, \textit{ok}, \textit{limit} \land j = \textit{up})$; if $j$ is a valid digit $[2, 5, 6, 9]$, we recursively call $\textit{dfs}(i + 1, 1, \textit{limit} \land j = \textit{up})$. We sum all the results of the recursive calls and return.

The time complexity is $O(\log n \times D)$, and the space complexity is $O(\log n)$. Here, $D = 10$.

Similar problems:

-   [233. Number of Digit One](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0233.Number%20of%20Digit%20One/README_EN.md)
-   [357. Count Numbers with Unique Digits](https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README_EN.md)
-   [600. Non-negative Integers without Consecutive Ones](https://github.com/doocs/leetcode/blob/main/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README_EN.md)
-   [902. Numbers At Most N Given Digit Set](https://github.com/doocs/leetcode/blob/main/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README_EN.md)
-   [1012. Numbers with Repeated Digits](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README_EN.md)
-   [2376. Count Special Integers](https://github.com/doocs/leetcode/blob/main/solution/2300-2399/2376.Count%20Special%20Integers/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rotatedDigits(self, n: int) -> int:
        @cache
        def dfs(i: int, ok: int, limit: bool) -> int:
            if i >= len(s):
                return ok
            up = int(s[i]) if limit else 9
            ans = 0
            for j in range(up + 1):
                if j in (0, 1, 8):
                    ans += dfs(i + 1, ok, limit and j == up)
                elif j in (2, 5, 6, 9):
                    ans += dfs(i + 1, 1, limit and j == up)
            return ans

        s = str(n)
        return dfs(0, 0, True)
```

#### Java

```java
class Solution {
    private char[] s;
    private Integer[][] f;

    public int rotatedDigits(int n) {
        s = String.valueOf(n).toCharArray();
        f = new Integer[s.length][2];
        return dfs(0, 0, true);
    }

    private int dfs(int i, int ok, boolean limit) {
        if (i >= s.length) {
            return ok;
        }
        if (!limit && f[i][ok] != null) {
            return f[i][ok];
        }
        int up = limit ? s[i] - '0' : 9;
        int ans = 0;
        for (int j = 0; j <= up; ++j) {
            if (j == 0 || j == 1 || j == 8) {
                ans += dfs(i + 1, ok, limit && j == up);
            } else if (j == 2 || j == 5 || j == 6 || j == 9) {
                ans += dfs(i + 1, 1, limit && j == up);
            }
        }
        if (!limit) {
            f[i][ok] = ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int rotatedDigits(int n) {
        string s = to_string(n);
        int m = s.size();
        int f[m][2];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i, int ok, bool limit) -> int {
            if (i >= m) {
                return ok;
            }
            if (!limit && f[i][ok] != -1) {
                return f[i][ok];
            }
            int up = limit ? s[i] - '0' : 9;
            int ans = 0;
            for (int j = 0; j <= up; ++j) {
                if (j == 0 || j == 1 || j == 8) {
                    ans += dfs(i + 1, ok, limit && j == up);
                } else if (j == 2 || j == 5 || j == 6 || j == 9) {
                    ans += dfs(i + 1, 1, limit && j == up);
                }
            }
            if (!limit) {
                f[i][ok] = ans;
            }
            return ans;
        };
        return dfs(0, 0, true);
    }
};
```

#### Go

```go
func rotatedDigits(n int) int {
	s := strconv.Itoa(n)
	m := len(s)
	f := make([][2]int, m)
	for i := range f {
		f[i] = [2]int{-1, -1}
	}
	var dfs func(i, ok int, limit bool) int
	dfs = func(i, ok int, limit bool) int {
		if i >= m {
			return ok
		}
		if !limit && f[i][ok] != -1 {
			return f[i][ok]
		}
		up := 9
		if limit {
			up = int(s[i] - '0')
		}
		ans := 0
		for j := 0; j <= up; j++ {
			if j == 0 || j == 1 || j == 8 {
				ans += dfs(i+1, ok, limit && j == up)
			} else if j == 2 || j == 5 || j == 6 || j == 9 {
				ans += dfs(i+1, 1, limit && j == up)
			}
		}
		if !limit {
			f[i][ok] = ans
		}
		return ans
	}
	return dfs(0, 0, true)
}
```

#### TypeScript

```ts
function rotatedDigits(n: number): number {
    const s = n.toString();
    const m = s.length;
    const f: number[][] = Array.from({ length: m }, () => Array(2).fill(-1));
    const dfs = (i: number, ok: number, limit: boolean): number => {
        if (i >= m) {
            return ok;
        }
        if (!limit && f[i][ok] !== -1) {
            return f[i][ok];
        }
        const up = limit ? +s[i] : 9;
        let ans = 0;
        for (let j = 0; j <= up; ++j) {
            if ([0, 1, 8].includes(j)) {
                ans += dfs(i + 1, ok, limit && j === up);
            } else if ([2, 5, 6, 9].includes(j)) {
                ans += dfs(i + 1, 1, limit && j === up);
            }
        }
        if (!limit) {
            f[i][ok] = ans;
        }
        return ans;
    };
    return dfs(0, 0, true);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
