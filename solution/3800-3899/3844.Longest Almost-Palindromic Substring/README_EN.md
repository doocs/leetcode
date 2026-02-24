---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3844.Longest%20Almost-Palindromic%20Substring/README_EN.md
rating: 1989
source: Weekly Contest 489 Q3
tags:
    - Two Pointers
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [3844. Longest Almost-Palindromic Substring](https://leetcode.com/problems/longest-almost-palindromic-substring)

[中文文档](/solution/3800-3899/3844.Longest%20Almost-Palindromic%20Substring/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of lowercase English letters.</p>

<p>A <span data-keyword="substring-nonempty">substring</span> is <strong>almost-palindromic</strong> if it becomes a <span data-keyword="palindrome-string">palindrome</span> after removing <strong>exactly</strong> one character from it.</p>

<p>Return an integer denoting the length of the <strong>longest</strong> <strong>almost-palindromic</strong> substring in <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abca&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Choose the substring <code>&quot;<u><strong>abca</strong></u>&quot;</code>.</p>

<ul>
	<li>Remove <code>&quot;ab<u><strong>c</strong></u>a&quot;</code>.</li>
	<li>The string becomes <code>&quot;aba&quot;</code>, which is a palindrome.</li>
	<li>Therefore, <code>&quot;abca&quot;</code> is almost-palindromic.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abba&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Choose the substring <code>&quot;<u><strong>abba</strong></u>&quot;</code>.</p>

<ul>
	<li>Remove <code>&quot;a<u><strong>b</strong></u>ba&quot;</code>.</li>
	<li>The string becomes <code>&quot;aba&quot;</code>, which is a palindrome.</li>
	<li>Therefore, <code>&quot;abba&quot;</code> is almost-palindromic.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;zzabba&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>Choose the substring <code>&quot;z<u><strong>zabba</strong></u>&quot;</code>.</p>

<ul>
	<li>Remove <code>&quot;<u><strong>z</strong></u>abba&quot;</code>.</li>
	<li>The string becomes <code>&quot;abba&quot;</code>, which is a palindrome.</li>
	<li>Therefore, <code>&quot;zabba&quot;</code> is almost-palindromic.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 2500</code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumerate the Center Position of the Palindrome

Let's denote the length of string $s$ as $n$.

We define a function $f(l, r)$, which represents calculating the length of the longest almost-palindromic substring that can be obtained by starting from $l$ and $r$, expanding towards both sides of the string, and deleting one character.

In the function $f(l, r)$, we first expand towards both sides until the conditions $l \geq 0$, $r \lt n$, and $s[l] = s[r]$ are no longer satisfied. At this point, we can choose to skip $l$ or skip $r$. If we skip $l$, then we continue to expand from $(l - 1, r)$ towards both sides; if we skip $r$, then we continue to expand from $(l, r + 1)$ towards both sides. We calculate the length of the longest almost-palindromic substring for both cases and take the maximum value. Note that the length of the longest almost-palindromic substring cannot exceed $n$.

Finally, we enumerate the center position $i$ of the palindrome, and calculate the length of the longest almost-palindromic substring obtained by starting from $(i, i)$ and $(i, i + 1)$, expanding towards both sides, and deleting one character, taking the maximum value among them.

The time complexity is $O(n^2)$, where $n$ is the length of string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def almostPalindromic(self, s: str) -> int:
        def f(l: int, r: int) -> int:
            while l >= 0 and r < n and s[l] == s[r]:
                l -= 1
                r += 1
            l1, r1 = l - 1, r
            l2, r2 = l, r + 1
            while l1 >= 0 and r1 < n and s[l1] == s[r1]:
                l1 -= 1
                r1 += 1
            while l2 >= 0 and r2 < n and s[l2] == s[r2]:
                l2 -= 1
                r2 += 1
            return min(n, max(r1 - l1 - 1, r2 - l2 - 1))

        n = len(s)
        ans = 0
        for i in range(n):
            a = f(i, i)
            b = f(i, i + 1)
            ans = max(ans, a, b)
        return ans
```

#### Java

```java
class Solution {
    private int n;
    private char[] s;

    public int almostPalindromic(String s) {
        n = s.length();
        this.s = s.toCharArray();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, f(i, i));
            ans = Math.max(ans, f(i, i + 1));
        }
        return ans;
    }

    private int f(int l, int r) {
        while (l >= 0 && r < n && s[l] == s[r]) {
            l--;
            r++;
        }

        int l1 = l - 1, r1 = r;
        int l2 = l, r2 = r + 1;

        while (l1 >= 0 && r1 < n && s[l1] == s[r1]) {
            l1--;
            r1++;
        }
        while (l2 >= 0 && r2 < n && s[l2] == s[r2]) {
            l2--;
            r2++;
        }

        return Math.min(n, Math.max(r1 - l1 - 1, r2 - l2 - 1));
    }
}
```

#### C++

```cpp
class Solution {
public:
    int almostPalindromic(string s) {
        int n = s.size();

        auto f = [&](int l, int r) {
            while (l >= 0 && r < n && s[l] == s[r]) {
                --l;
                ++r;
            }

            int l1 = l - 1, r1 = r;
            int l2 = l, r2 = r + 1;

            while (l1 >= 0 && r1 < n && s[l1] == s[r1]) {
                --l1;
                ++r1;
            }
            while (l2 >= 0 && r2 < n && s[l2] == s[r2]) {
                --l2;
                ++r2;
            }

            return min(n, max(r1 - l1 - 1, r2 - l2 - 1));
        };

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = max(ans, f(i, i));
            ans = max(ans, f(i, i + 1));
        }

        return ans;
    }
};
```

#### Go

```go
func almostPalindromic(s string) int {
	n := len(s)

	f := func(l, r int) int {
		for l >= 0 && r < n && s[l] == s[r] {
			l--
			r++
		}

		l1, r1 := l-1, r
		l2, r2 := l, r+1

		for l1 >= 0 && r1 < n && s[l1] == s[r1] {
			l1--
			r1++
		}
		for l2 >= 0 && r2 < n && s[l2] == s[r2] {
			l2--
			r2++
		}
		return min(n, max(r1-l1-1, r2-l2-1))
	}

	ans := 0
	for i := 0; i < n; i++ {
		ans = max(ans, f(i, i), f(i, i+1))
	}
	return ans
}
```

#### TypeScript

```ts
function almostPalindromic(s: string): number {
    const n = s.length;

    const f = (l: number, r: number): number => {
        while (l >= 0 && r < n && s[l] === s[r]) {
            l--;
            r++;
        }

        let l1 = l - 1,
            r1 = r;
        let l2 = l,
            r2 = r + 1;

        while (l1 >= 0 && r1 < n && s[l1] === s[r1]) {
            l1--;
            r1++;
        }
        while (l2 >= 0 && r2 < n && s[l2] === s[r2]) {
            l2--;
            r2++;
        }

        return Math.min(n, Math.max(r1 - l1 - 1, r2 - l2 - 1));
    };

    let ans = 0;
    for (let i = 0; i < n; i++) {
        ans = Math.max(ans, f(i, i));
        ans = Math.max(ans, f(i, i + 1));
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
