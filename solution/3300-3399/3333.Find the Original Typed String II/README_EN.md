---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3333.Find%20the%20Original%20Typed%20String%20II/README_EN.md
rating: 2628
source: Biweekly Contest 142 Q4
tags:
    - String
    - Dynamic Programming
    - Prefix Sum
---

<!-- problem:start -->

# [3333. Find the Original Typed String II](https://leetcode.com/problems/find-the-original-typed-string-ii)

[中文文档](/solution/3300-3399/3333.Find%20the%20Original%20Typed%20String%20II/README.md)

## Description

<!-- description:start -->

<p>Alice is attempting to type a specific string on her computer. However, she tends to be clumsy and <strong>may</strong> press a key for too long, resulting in a character being typed <strong>multiple</strong> times.</p>

<p>You are given a string <code>word</code>, which represents the <strong>final</strong> output displayed on Alice&#39;s screen. You are also given a <strong>positive</strong> integer <code>k</code>.</p>

<p>Return the total number of <em>possible</em> original strings that Alice <em>might</em> have intended to type, if she was trying to type a string of size <strong>at least</strong> <code>k</code>.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;aabbccdd&quot;, k = 7</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>The possible strings are: <code>&quot;aabbccdd&quot;</code>, <code>&quot;aabbccd&quot;</code>, <code>&quot;aabbcdd&quot;</code>, <code>&quot;aabccdd&quot;</code>, and <code>&quot;abbccdd&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;aabbccdd&quot;, k = 8</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The only possible string is <code>&quot;aabbccdd&quot;</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;aaabbb&quot;, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>word</code> consists only of lowercase English letters.</li>
	<li><code>1 &lt;= k &lt;= 2000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming + Prefix Sum

For the constraint that the length is at least $k$, we can split it into two subproblems:

- Without length restriction, for each group of consecutive identical characters, we can choose any number from $1$ to the length of the group. Let the number of ways be $a$.
- For length less than $k$, let the number of ways be $b$.

Thus, the final answer is $a - b$.

We can group consecutive identical characters in the string $\textit{word}$. Since at least one character must be chosen from each group, if a group has more than $0$ remaining selectable characters, we add it to an array $\textit{nums}$. After initially selecting one character from each group, we update the remaining required character count $k$.

If $k < 1$, it means that after selecting one character from each group, the requirement of length at least $k$ is already satisfied, so the answer is $a$.

Otherwise, we need to calculate the value of $b$. We use a 2D array $\textit{f}$, where $\textit{f}[i][j]$ represents the number of ways to select $j$ characters from the first $i$ groups. Initially, $\textit{f}[0][0] = 1$, meaning there is $1$ way to select $0$ characters from $0$ groups. Then $b = \sum_{j=0}^{k-1} \text{f}[m][j]$, where $m$ is the length of $\textit{nums}$. The answer is $a - b$.

Consider the transition equation for $\textit{f}[i][j]$. For the $i$-th group of characters, suppose its remaining length is $x$. For each $j$, we can enumerate the number of characters $l$ chosen from this group, where $l \in [0, \min(x, j)]$. Then, $\textit{f}[i][j]$ can be transferred from $\textit{f}[i-1][j-l]$. We can use prefix sums to optimize this transition.

The time complexity is $O(n + k^2)$, and the space complexity is $O(k^2)$, where $n$ is the length of the string $\textit{word}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def possibleStringCount(self, word: str, k: int) -> int:
        mod = 10**9 + 7
        nums = []
        ans = 1
        cur = 0
        for i, c in enumerate(word):
            cur += 1
            if i == len(word) - 1 or c != word[i + 1]:
                if cur > 1:
                    if k > 0:
                        nums.append(cur - 1)
                    ans = ans * cur % mod
                cur = 0
                k -= 1
        if k < 1:
            return ans
        m = len(nums)
        f = [[0] * k for _ in range(m + 1)]
        f[0][0] = 1
        for i, x in enumerate(nums, 1):
            s = list(accumulate(f[i - 1], initial=0))
            for j in range(k):
                f[i][j] = (s[j + 1] - s[j - min(x, j)] + mod) % mod
        return (ans - sum(f[m][j] for j in range(k))) % mod
```

#### Java

```java
class Solution {
    public int possibleStringCount(String word, int k) {
        final int mod = (int) 1e9 + 7;
        List<Integer> nums = new ArrayList<>();
        long ans = 1;
        int cur = 0;
        int n = word.length();

        for (int i = 0; i < n; i++) {
            cur++;
            if (i == n - 1 || word.charAt(i) != word.charAt(i + 1)) {
                if (cur > 1) {
                    if (k > 0) {
                        nums.add(cur - 1);
                    }
                    ans = ans * cur % mod;
                }
                cur = 0;
                k--;
            }
        }

        if (k < 1) {
            return (int) ans;
        }

        int m = nums.size();
        int[][] f = new int[m + 1][k];
        f[0][0] = 1;

        for (int i = 1; i <= m; i++) {
            int x = nums.get(i - 1);
            long[] s = new long[k + 1];
            for (int j = 0; j < k; j++) {
                s[j + 1] = (s[j] + f[i - 1][j]) % mod;
            }
            for (int j = 0; j < k; j++) {
                int l = Math.max(0, j - x);
                f[i][j] = (int) ((s[j + 1] - s[l] + mod) % mod);
            }
        }

        long sum = 0;
        for (int j = 0; j < k; j++) {
            sum = (sum + f[m][j]) % mod;
        }

        return (int) ((ans - sum + mod) % mod);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int possibleStringCount(string word, int k) {
        const int mod = 1e9 + 7;
        vector<int> nums;
        long long ans = 1;
        int cur = 0;
        int n = word.size();

        for (int i = 0; i < n; ++i) {
            cur++;
            if (i == n - 1 || word[i] != word[i + 1]) {
                if (cur > 1) {
                    if (k > 0) {
                        nums.push_back(cur - 1);
                    }
                    ans = ans * cur % mod;
                }
                cur = 0;
                k--;
            }
        }

        if (k < 1) {
            return ans;
        }

        int m = nums.size();
        vector<vector<int>> f(m + 1, vector<int>(k, 0));
        f[0][0] = 1;

        for (int i = 1; i <= m; ++i) {
            int x = nums[i - 1];
            vector<long long> s(k + 1, 0);
            for (int j = 0; j < k; ++j) {
                s[j + 1] = (s[j] + f[i - 1][j]) % mod;
            }
            for (int j = 0; j < k; ++j) {
                int l = max(0, j - x);
                f[i][j] = (s[j + 1] - s[l] + mod) % mod;
            }
        }

        long long sum = 0;
        for (int j = 0; j < k; ++j) {
            sum = (sum + f[m][j]) % mod;
        }

        return (ans - sum + mod) % mod;
    }
};
```

#### Go

```go
func possibleStringCount(word string, k int) int {
	const mod = 1_000_000_007
	nums := []int{}
	ans := 1
	cur := 0
	n := len(word)

	for i := 0; i < n; i++ {
		cur++
		if i == n-1 || word[i] != word[i+1] {
			if cur > 1 {
				if k > 0 {
					nums = append(nums, cur-1)
				}
				ans = ans * cur % mod
			}
			cur = 0
			k--
		}
	}

	if k < 1 {
		return ans
	}

	m := len(nums)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, k)
	}
	f[0][0] = 1

	for i := 1; i <= m; i++ {
		x := nums[i-1]
		s := make([]int, k+1)
		for j := 0; j < k; j++ {
			s[j+1] = (s[j] + f[i-1][j]) % mod
		}
		for j := 0; j < k; j++ {
			l := j - x
			if l < 0 {
				l = 0
			}
			f[i][j] = (s[j+1] - s[l] + mod) % mod
		}
	}

	sum := 0
	for j := 0; j < k; j++ {
		sum = (sum + f[m][j]) % mod
	}

	return (ans - sum + mod) % mod
}
```

#### TypeScript

```ts
function possibleStringCount(word: string, k: number): number {
    const mod = 1_000_000_007;
    const nums: number[] = [];
    let ans = 1;
    let cur = 0;
    const n = word.length;

    for (let i = 0; i < n; i++) {
        cur++;
        if (i === n - 1 || word[i] !== word[i + 1]) {
            if (cur > 1) {
                if (k > 0) {
                    nums.push(cur - 1);
                }
                ans = (ans * cur) % mod;
            }
            cur = 0;
            k--;
        }
    }

    if (k < 1) {
        return ans;
    }

    const m = nums.length;
    const f: number[][] = Array.from({ length: m + 1 }, () => Array(k).fill(0));
    f[0][0] = 1;

    for (let i = 1; i <= m; i++) {
        const x = nums[i - 1];
        const s: number[] = Array(k + 1).fill(0);
        for (let j = 0; j < k; j++) {
            s[j + 1] = (s[j] + f[i - 1][j]) % mod;
        }
        for (let j = 0; j < k; j++) {
            const l = Math.max(0, j - x);
            f[i][j] = (s[j + 1] - s[l] + mod) % mod;
        }
    }

    let sum = 0;
    for (let j = 0; j < k; j++) {
        sum = (sum + f[m][j]) % mod;
    }

    return (ans - sum + mod) % mod;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
