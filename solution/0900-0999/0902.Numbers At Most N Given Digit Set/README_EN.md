---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README_EN.md
tags:
    - Array
    - Math
    - String
    - Binary Search
    - Dynamic Programming
---

<!-- problem:start -->

# [902. Numbers At Most N Given Digit Set](https://leetcode.com/problems/numbers-at-most-n-given-digit-set)

[中文文档](/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README.md)

## Description

<!-- description:start -->

<p>Given an array of <code>digits</code> which is sorted in <strong>non-decreasing</strong> order. You can write numbers using each <code>digits[i]</code> as many times as we want. For example, if <code>digits = [&#39;1&#39;,&#39;3&#39;,&#39;5&#39;]</code>, we may write numbers such as <code>&#39;13&#39;</code>, <code>&#39;551&#39;</code>, and <code>&#39;1351315&#39;</code>.</p>

<p>Return <em>the number of positive integers that can be generated </em>that are less than or equal to a given integer <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> digits = [&quot;1&quot;,&quot;3&quot;,&quot;5&quot;,&quot;7&quot;], n = 100
<strong>Output:</strong> 20
<strong>Explanation: </strong>
The 20 numbers that can be written are:
1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> digits = [&quot;1&quot;,&quot;4&quot;,&quot;9&quot;], n = 1000000000
<strong>Output:</strong> 29523
<strong>Explanation: </strong>
We can write 3 one digit numbers, 9 two digit numbers, 27 three digit numbers,
81 four digit numbers, 243 five digit numbers, 729 six digit numbers,
2187 seven digit numbers, 6561 eight digit numbers, and 19683 nine digit numbers.
In total, this is 29523 integers that can be written using the digits array.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> digits = [&quot;7&quot;], n = 8
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= digits.length &lt;= 9</code></li>
	<li><code>digits[i].length == 1</code></li>
	<li><code>digits[i]</code> is a digit from&nbsp;<code>&#39;1&#39;</code>&nbsp;to <code>&#39;9&#39;</code>.</li>
	<li>All the values in&nbsp;<code>digits</code> are <strong>unique</strong>.</li>
	<li><code>digits</code> is sorted in&nbsp;<strong>non-decreasing</strong> order.</li>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Digit DP

This problem essentially asks for the number of positive integers that can be generated from the digits in digits within the given range $[l, .., r]$. The count depends on the number of digits and the value of each digit. We can solve this problem using the Digit DP approach. In Digit DP, the size of the number has little impact on the complexity.

For the range $[l, .., r]$ problem, we generally convert it to the problem of $[1, .., r]$ and then subtract the result of $[1, .., l - 1]$, i.e.,

$$
ans = \sum_{i=1}^{r} ans_i -  \sum_{i=1}^{l-1} ans_i
$$

However, for this problem, we only need to find the value for the range $[1, .., r]$.

Here, we use memoization to implement Digit DP. We start searching from the top, get the number of solutions at the bottom, and return the answers layer by layer until we get the final answer from the starting point.

The basic steps are as follows:

We convert the number $n$ into a string $s$ and denote the length of the string $s$ as $m$.

Next, we design a function $\textit{dfs}(i, \textit{lead}, \textit{limit})$ to represent the number of solutions from the current $i$-th digit to the last digit of the string. Here:

- The integer $i$ represents the current position in the string $s$.
- The boolean $\textit{lead}$ indicates whether the current number contains only leading zeros.
- The boolean $\textit{limit}$ indicates whether the current position is restricted by the upper bound.

The function executes as follows:

If $i$ is greater than or equal to $m$, it means we have processed all digits. If $\textit{lead}$ is true, it means the current number is a leading zero, and we should return $0$. Otherwise, we should return $1$.

Otherwise, we calculate the upper bound $\textit{up}$. If $\textit{limit}$ is true, then $\textit{up}$ is the digit corresponding to $s[i]$. Otherwise, $\textit{up}$ is $9$.

Then, we enumerate the current digit $j$ in the range $[0, \textit{up}]$. If $j$ is $0$ and $\textit{lead}$ is true, we recursively calculate $\textit{dfs}(i + 1, \text{true}, \textit{limit} \wedge j = \textit{up})$. Otherwise, if $j$ is in $\textit{digits}$, we recursively calculate $\textit{dfs}(i + 1, \text{false}, \textit{limit} \wedge j = \textit{up})$. We accumulate all the results as the answer.

Finally, we return $\textit{dfs}(0, \text{true}, \text{true})$.

The time complexity is $O(\log n \times D)$, and the space complexity is $O(\log n)$. Here, $D = 10$.

Similar problems:

- [233. Number of Digit One](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0233.Number%20of%20Digit%20One/README_EN.md)
- [357. Count Numbers with Unique Digits](https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README_EN.md)
- [600. Non-negative Integers without Consecutive Ones](https://github.com/doocs/leetcode/blob/main/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README_EN.md)
- [788. Rotated Digits](https://github.com/doocs/leetcode/blob/main/solution/0700-0799/0788.Rotated%20Digits/README_EN.md)
- [1012. Numbers With Repeated Digits](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README_EN.md)
- [2376. Count Special Integers](https://github.com/doocs/leetcode/blob/main/solution/2300-2399/2376.Count%20Special%20Integers/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def atMostNGivenDigitSet(self, digits: List[str], n: int) -> int:
        @cache
        def dfs(i: int, lead: int, limit: bool) -> int:
            if i >= len(s):
                return lead ^ 1

            up = int(s[i]) if limit else 9
            ans = 0
            for j in range(up + 1):
                if j == 0 and lead:
                    ans += dfs(i + 1, 1, limit and j == up)
                elif j in nums:
                    ans += dfs(i + 1, 0, limit and j == up)
            return ans

        s = str(n)
        nums = {int(x) for x in digits}
        return dfs(0, 1, True)
```

#### Java

```java
class Solution {
    private Set<Integer> nums = new HashSet<>();
    private char[] s;
    private Integer[] f;

    public int atMostNGivenDigitSet(String[] digits, int n) {
        s = String.valueOf(n).toCharArray();
        f = new Integer[s.length];
        for (var x : digits) {
            nums.add(Integer.parseInt(x));
        }
        return dfs(0, true, true);
    }

    private int dfs(int i, boolean lead, boolean limit) {
        if (i >= s.length) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && f[i] != null) {
            return f[i];
        }
        int up = limit ? s[i] - '0' : 9;
        int ans = 0;
        for (int j = 0; j <= up; ++j) {
            if (j == 0 && lead) {
                ans += dfs(i + 1, true, limit && j == up);
            } else if (nums.contains(j)) {
                ans += dfs(i + 1, false, limit && j == up);
            }
        }
        if (!lead && !limit) {
            f[i] = ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int atMostNGivenDigitSet(vector<string>& digits, int n) {
        string s = to_string(n);
        unordered_set<int> nums;
        for (auto& x : digits) {
            nums.insert(stoi(x));
        }
        int m = s.size();
        int f[m];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i, bool lead, bool limit) -> int {
            if (i >= m) {
                return lead ? 0 : 1;
            }
            if (!lead && !limit && f[i] != -1) {
                return f[i];
            }
            int up = limit ? s[i] - '0' : 9;
            int ans = 0;
            for (int j = 0; j <= up; ++j) {
                if (j == 0 && lead) {
                    ans += dfs(i + 1, true, limit && j == up);
                } else if (nums.count(j)) {
                    ans += dfs(i + 1, false, limit && j == up);
                }
            }
            if (!lead && !limit) {
                f[i] = ans;
            }
            return ans;
        };
        return dfs(0, true, true);
    }
};
```

#### Go

```go
func atMostNGivenDigitSet(digits []string, n int) int {
	s := strconv.Itoa(n)
	m := len(s)
	f := make([]int, m)
	for i := range f {
		f[i] = -1
	}
	nums := map[int]bool{}
	for _, d := range digits {
		x, _ := strconv.Atoi(d)
		nums[x] = true
	}
	var dfs func(i int, lead, limit bool) int
	dfs = func(i int, lead, limit bool) int {
		if i >= m {
			if lead {
				return 0
			}
			return 1
		}
		if !lead && !limit && f[i] != -1 {
			return f[i]
		}
		up := 9
		if limit {
			up = int(s[i] - '0')
		}
		ans := 0
		for j := 0; j <= up; j++ {
			if j == 0 && lead {
				ans += dfs(i+1, true, limit && j == up)
			} else if nums[j] {
				ans += dfs(i+1, false, limit && j == up)
			}
		}
		if !lead && !limit {
			f[i] = ans
		}
		return ans
	}
	return dfs(0, true, true)
}
```

#### TypeScript

```ts
function atMostNGivenDigitSet(digits: string[], n: number): number {
    const s = n.toString();
    const m = s.length;
    const f: number[] = Array(m).fill(-1);
    const nums = new Set<number>(digits.map(d => parseInt(d)));
    const dfs = (i: number, lead: boolean, limit: boolean): number => {
        if (i >= m) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && f[i] !== -1) {
            return f[i];
        }
        const up = limit ? +s[i] : 9;
        let ans = 0;
        for (let j = 0; j <= up; ++j) {
            if (!j && lead) {
                ans += dfs(i + 1, true, limit && j === up);
            } else if (nums.has(j)) {
                ans += dfs(i + 1, false, limit && j === up);
            }
        }
        if (!lead && !limit) {
            f[i] = ans;
        }
        return ans;
    };
    return dfs(0, true, true);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
