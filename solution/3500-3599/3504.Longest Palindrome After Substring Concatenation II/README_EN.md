---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3504.Longest%20Palindrome%20After%20Substring%20Concatenation%20II/README_EN.md
tags:
    - Two Pointers
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [3504. Longest Palindrome After Substring Concatenation II](https://leetcode.com/problems/longest-palindrome-after-substring-concatenation-ii)

[中文文档](/solution/3500-3599/3504.Longest%20Palindrome%20After%20Substring%20Concatenation%20II/README.md)

## Description

<!-- description:start -->

<p>You are given two strings, <code>s</code> and <code>t</code>.</p>

<p>You can create a new string by selecting a <span data-keyword="substring">substring</span> from <code>s</code> (possibly empty) and a substring from <code>t</code> (possibly empty), then concatenating them <strong>in order</strong>.</p>

<p>Return the length of the <strong>longest</strong> <span data-keyword="palindrome-string">palindrome</span> that can be formed this way.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;a&quot;, t = &quot;a&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Concatenating <code>&quot;a&quot;</code> from <code>s</code> and <code>&quot;a&quot;</code> from <code>t</code> results in <code>&quot;aa&quot;</code>, which is a palindrome of length 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abc&quot;, t = &quot;def&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>Since all characters are different, the longest palindrome is any single character, so the answer is 1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;b&quot;, t = &quot;aaaa&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Selecting &quot;<code>aaaa</code>&quot; from <code>t</code> is the longest palindrome, so the answer is 4.</p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcde&quot;, t = &quot;ecdba&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>Concatenating <code>&quot;abc&quot;</code> from <code>s</code> and <code>&quot;ba&quot;</code> from <code>t</code> results in <code>&quot;abcba&quot;</code>, which is a palindrome of length 5.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 1000</code></li>
	<li><code>s</code> and <code>t</code> consist of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumerate Palindrome Centers + Dynamic Programming

According to the problem description, the concatenated palindrome string can be composed entirely of string $s$, entirely of string $t$, or a combination of both strings $s$ and $t$. Additionally, there may be extra palindromic substrings in either string $s$ or $t$.

Therefore, we first reverse string $t$ and preprocess arrays $\textit{g1}$ and $\textit{g2}$, where $\textit{g1}[i]$ represents the length of the longest palindromic substring starting at index $i$ in string $s$, and $\textit{g2}[i]$ represents the length of the longest palindromic substring starting at index $i$ in string $t$.

We can initialize the answer $\textit{ans}$ as the maximum value in $\textit{g1}$ and $\textit{g2}$.

Next, we define $\textit{f}[i][j]$ as the length of the palindromic substring ending at the $i$-th character of string $s$ and the $j$-th character of string $t$.

For $\textit{f}[i][j]$, if $s[i - 1]$ equals $t[j - 1]$, then $\textit{f}[i][j] = \textit{f}[i - 1][j - 1] + 1$. We then update the answer:

$$
\textit{ans} = \max(\textit{ans}, \textit{f}[i][j] \times 2 + (0 \text{ if } i \geq m \text{ else } \textit{g1}[i])) \\
\textit{ans} = \max(\textit{ans}, \textit{f}[i][j] \times 2 + (0 \text{ if } j \geq n \text{ else } \textit{g2}[j]))
$$

Finally, we return the answer $\textit{ans}$.

The time complexity is $O(m \times (m + n))$, and the space complexity is $O(m \times n)$, where $m$ and $n$ are the lengths of strings $s$ and $t$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestPalindrome(self, s: str, t: str) -> int:
        def expand(s: str, g: List[int], l: int, r: int):
            while l >= 0 and r < len(s) and s[l] == s[r]:
                g[l] = max(g[l], r - l + 1)
                l, r = l - 1, r + 1

        def calc(s: str) -> List[int]:
            n = len(s)
            g = [0] * n
            for i in range(n):
                expand(s, g, i, i)
                expand(s, g, i, i + 1)
            return g

        m, n = len(s), len(t)
        t = t[::-1]
        g1, g2 = calc(s), calc(t)
        ans = max(*g1, *g2)
        f = [[0] * (n + 1) for _ in range(m + 1)]
        for i, a in enumerate(s, 1):
            for j, b in enumerate(t, 1):
                if a == b:
                    f[i][j] = f[i - 1][j - 1] + 1
                    ans = max(ans, f[i][j] * 2 + (0 if i >= m else g1[i]))
                    ans = max(ans, f[i][j] * 2 + (0 if j >= n else g2[j]))
        return ans
```

#### Java

```java
class Solution {
    public int longestPalindrome(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = new StringBuilder(T).reverse().toString().toCharArray();
        int m = s.length, n = t.length;
        int[] g1 = calc(s), g2 = calc(t);
        int ans = Math.max(Arrays.stream(g1).max().getAsInt(), Arrays.stream(g2).max().getAsInt());
        int[][] f = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (s[i - 1] == t[j - 1]) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                    ans = Math.max(ans, f[i][j] * 2 + (i < m ? g1[i] : 0));
                    ans = Math.max(ans, f[i][j] * 2 + (j < n ? g2[j] : 0));
                }
            }
        }
        return ans;
    }

    private void expand(char[] s, int[] g, int l, int r) {
        while (l >= 0 && r < s.length && s[l] == s[r]) {
            g[l] = Math.max(g[l], r - l + 1);
            --l;
            ++r;
        }
    }

    private int[] calc(char[] s) {
        int n = s.length;
        int[] g = new int[n];
        for (int i = 0; i < n; ++i) {
            expand(s, g, i, i);
            expand(s, g, i, i + 1);
        }
        return g;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestPalindrome(string s, string t) {
        int m = s.size(), n = t.size();
        ranges::reverse(t);
        vector<int> g1 = calc(s), g2 = calc(t);
        int ans = max(ranges::max(g1), ranges::max(g2));
        vector<vector<int>> f(m + 1, vector<int>(n + 1));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (s[i - 1] == t[j - 1]) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                    ans = max(ans, f[i][j] * 2 + (i < m ? g1[i] : 0));
                    ans = max(ans, f[i][j] * 2 + (j < n ? g2[j] : 0));
                }
            }
        }
        return ans;
    }

private:
    void expand(const string& s, vector<int>& g, int l, int r) {
        while (l >= 0 && r < s.size() && s[l] == s[r]) {
            g[l] = max(g[l], r - l + 1);
            --l;
            ++r;
        }
    }

    vector<int> calc(const string& s) {
        int n = s.size();
        vector<int> g(n, 0);
        for (int i = 0; i < n; ++i) {
            expand(s, g, i, i);
            expand(s, g, i, i + 1);
        }
        return g;
    }
};
```

#### Go

```go
func longestPalindrome(s, t string) int {
	m, n := len(s), len(t)
	t = reverse(t)

	g1, g2 := calc(s), calc(t)
	ans := max(slices.Max(g1), slices.Max(g2))

	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}

	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if s[i-1] == t[j-1] {
				f[i][j] = f[i-1][j-1] + 1
				a, b := 0, 0
				if i < m {
					a = g1[i]
				}
				if j < n {
					b = g2[j]
				}
				ans = max(ans, f[i][j]*2+a)
				ans = max(ans, f[i][j]*2+b)
			}
		}
	}
	return ans
}

func calc(s string) []int {
	n, g := len(s), make([]int, len(s))
	for i := 0; i < n; i++ {
		expand(s, g, i, i)
		expand(s, g, i, i+1)
	}
	return g
}

func expand(s string, g []int, l, r int) {
	for l >= 0 && r < len(s) && s[l] == s[r] {
		g[l] = max(g[l], r-l+1)
		l, r = l-1, r+1
	}
}

func reverse(s string) string {
	r := []rune(s)
	slices.Reverse(r)
	return string(r)
}
```

#### TypeScript

```ts
function longestPalindrome(s: string, t: string): number {
    function expand(s: string, g: number[], l: number, r: number): void {
        while (l >= 0 && r < s.length && s[l] === s[r]) {
            g[l] = Math.max(g[l], r - l + 1);
            l--;
            r++;
        }
    }

    function calc(s: string): number[] {
        const n = s.length;
        const g: number[] = Array(n).fill(0);
        for (let i = 0; i < n; i++) {
            expand(s, g, i, i);
            expand(s, g, i, i + 1);
        }
        return g;
    }

    const m = s.length,
        n = t.length;
    t = t.split('').reverse().join('');
    const g1 = calc(s);
    const g2 = calc(t);
    let ans = Math.max(...g1, ...g2);

    const f: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));

    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            if (s[i - 1] === t[j - 1]) {
                f[i][j] = f[i - 1][j - 1] + 1;
                ans = Math.max(ans, f[i][j] * 2 + (i >= m ? 0 : g1[i]));
                ans = Math.max(ans, f[i][j] * 2 + (j >= n ? 0 : g2[j]));
            }
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
