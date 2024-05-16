---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1216.Valid%20Palindrome%20III/README_EN.md
rating: 1753
source: Biweekly Contest 10 Q4
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [1216. Valid Palindrome III 🔒](https://leetcode.com/problems/valid-palindrome-iii)

[中文文档](/solution/1200-1299/1216.Valid%20Palindrome%20III/README.md)

## Description

<p>Given a string <code>s</code> and an integer <code>k</code>, return <code>true</code> if <code>s</code> is a <code>k</code><strong>-palindrome</strong>.</p>

<p>A string is <code>k</code><strong>-palindrome</strong> if it can be transformed into a palindrome by removing at most <code>k</code> characters from it.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcdeca&quot;, k = 2
<strong>Output:</strong> true
<strong>Explanation:</strong> Remove &#39;b&#39; and &#39;e&#39; characters.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abbababa&quot;, k = 1
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

The problem requires us to remove at most $k$ characters to make the remaining string a palindrome. This can be transformed into finding the longest palindromic subsequence.

We define $f[i][j]$ as the length of the longest palindromic subsequence in the substring $s[i..j]$. Initially, we have $f[i][i] = 1$ for all $i$, since each single character is a palindrome.

If $s[i] = s[j]$, then we have $f[i][j] = f[i+1][j-1] + 2$, since we can add both $s[i]$ and $s[j]$ to the longest palindromic subsequence of $s[i+1..j-1]$.

If $s[i] \neq s[j]$, then we have $f[i][j] = \max(f[i+1][j], f[i][j-1])$, since we need to remove either $s[i]$ or $s[j]$ to make the remaining substring a palindrome.

Finally, we check whether there exists $f[i][j] + k \geq n$, where $n$ is the length of the string $s$. If so, it means that we can remove at most $k$ characters to make the remaining string a palindrome.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$. Here, $n$ is the length of the string $s$.

<!-- tabs:start -->

```python
class Solution:
    def isValidPalindrome(self, s: str, k: int) -> bool:
        n = len(s)
        f = [[0] * n for _ in range(n)]
        for i in range(n):
            f[i][i] = 1
        for i in range(n - 2, -1, -1):
            for j in range(i + 1, n):
                if s[i] == s[j]:
                    f[i][j] = f[i + 1][j - 1] + 2
                else:
                    f[i][j] = max(f[i + 1][j], f[i][j - 1])
                if f[i][j] + k >= n:
                    return True
        return False
```

```java
class Solution {
    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        int[][] f = new int[n][n];
        for (int i = 0; i < n; ++i) {
            f[i][i] = 1;
        }
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                } else {
                    f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                }
                if (f[i][j] + k >= n) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    bool isValidPalindrome(string s, int k) {
        int n = s.length();
        int f[n][n];
        memset(f, 0, sizeof f);
        for (int i = 0; i < n; ++i) {
            f[i][i] = 1;
        }
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s[i] == s[j]) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                } else {
                    f[i][j] = max(f[i + 1][j], f[i][j - 1]);
                }
                if (f[i][j] + k >= n) {
                    return true;
                }
            }
        }
        return false;
    }
};
```

```go
func isValidPalindrome(s string, k int) bool {
	n := len(s)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		f[i][i] = 1
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			if s[i] == s[j] {
				f[i][j] = f[i+1][j-1] + 2
			} else {
				f[i][j] = max(f[i+1][j], f[i][j-1])
			}
			if f[i][j]+k >= n {
				return true
			}
		}
	}
	return false
}
```

```ts
function isValidPalindrome(s: string, k: number): boolean {
    const n = s.length;
    const f: number[][] = Array.from({ length: n }, () => Array.from({ length: n }, () => 0));
    for (let i = 0; i < n; ++i) {
        f[i][i] = 1;
    }
    for (let i = n - 2; ~i; --i) {
        for (let j = i + 1; j < n; ++j) {
            if (s[i] === s[j]) {
                f[i][j] = f[i + 1][j - 1] + 2;
            } else {
                f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
            }
            if (f[i][j] + k >= n) {
                return true;
            }
        }
    }
    return false;
}
```

```rust
impl Solution {
    pub fn is_valid_palindrome(s: String, k: i32) -> bool {
        let s = s.as_bytes();
        let n = s.len();
        let mut f = vec![vec![0; n]; n];

        for i in 0..n {
            f[i][i] = 1;
        }

        for i in (0..n - 2).rev() {
            for j in i + 1..n {
                if s[i] == s[j] {
                    f[i][j] = f[i + 1][j - 1] + 2;
                } else {
                    f[i][j] = std::cmp::max(f[i + 1][j], f[i][j - 1]);
                }

                if f[i][j] + k >= (n as i32) {
                    return true;
                }
            }
        }

        false
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
