# [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring)

[中文文档](/solution/0000-0099/0005.Longest%20Palindromic%20Substring/README.md)

<!-- tags:Two Pointers,String,Dynamic Programming -->

## Description

<p>Given a string <code>s</code>, return <em>the longest</em> <span data-keyword="palindromic-string"><em>palindromic</em></span> <span data-keyword="substring-nonempty"><em>substring</em></span> in <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;babad&quot;
<strong>Output:</strong> &quot;bab&quot;
<strong>Explanation:</strong> &quot;aba&quot; is also a valid answer.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cbbd&quot;
<strong>Output:</strong> &quot;bb&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consist of only digits and English letters.</li>
</ul>

## Solutions

### Solution 1: Dynamic Programming

We define $f[i][j]$ to represent whether the string $s[i..j]$ is a palindrome, initially $f[i][j] = true$.

Next, we define variables $k$ and $mx$, where $k$ represents the starting position of the longest palindrome, and $mx$ represents the length of the longest palindrome. Initially, $k = 0$, $mx = 1$.

Considering $f[i][j]$, if $s[i] = s[j]$, then $f[i][j] = f[i + 1][j - 1]$; otherwise, $f[i][j] = false$. If $f[i][j] = true$ and $mx < j - i + 1$, then we update $k = i$, $mx = j - i + 1$.

Since $f[i][j]$ depends on $f[i + 1][j - 1]$, we need to ensure that $i + 1$ is before $j - 1$, so we need to enumerate $i$ from large to small, and enumerate $j$ from small to large.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$. Here, $n$ is the length of the string $s$.

<!-- tabs:start -->

```python
class Solution:
    def longestPalindrome(self, s: str) -> str:
        n = len(s)
        f = [[True] * n for _ in range(n)]
        k, mx = 0, 1
        for i in range(n - 2, -1, -1):
            for j in range(i + 1, n):
                f[i][j] = False
                if s[i] == s[j]:
                    f[i][j] = f[i + 1][j - 1]
                    if f[i][j] and mx < j - i + 1:
                        k, mx = i, j - i + 1
        return s[k : k + mx]
```

```java
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] f = new boolean[n][n];
        for (var g : f) {
            Arrays.fill(g, true);
        }
        int k = 0, mx = 1;
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = false;
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1];
                    if (f[i][j] && mx < j - i + 1) {
                        mx = j - i + 1;
                        k = i;
                    }
                }
            }
        }
        return s.substring(k, k + mx);
    }
}
```

```cpp
class Solution {
public:
    string longestPalindrome(string s) {
        int n = s.size();
        vector<vector<bool>> f(n, vector<bool>(n, true));
        int k = 0, mx = 1;
        for (int i = n - 2; ~i; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = false;
                if (s[i] == s[j]) {
                    f[i][j] = f[i + 1][j - 1];
                    if (f[i][j] && mx < j - i + 1) {
                        mx = j - i + 1;
                        k = i;
                    }
                }
            }
        }
        return s.substr(k, mx);
    }
};
```

```go
func longestPalindrome(s string) string {
	n := len(s)
	f := make([][]bool, n)
	for i := range f {
		f[i] = make([]bool, n)
		for j := range f[i] {
			f[i][j] = true
		}
	}
	k, mx := 0, 1
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			f[i][j] = false
			if s[i] == s[j] {
				f[i][j] = f[i+1][j-1]
				if f[i][j] && mx < j-i+1 {
					mx = j - i + 1
					k = i
				}
			}
		}
	}
	return s[k : k+mx]
}
```

```ts
function longestPalindrome(s: string): string {
    const n = s.length;
    const f: boolean[][] = Array(n)
        .fill(0)
        .map(() => Array(n).fill(true));
    let k = 0;
    let mx = 1;
    for (let i = n - 2; i >= 0; --i) {
        for (let j = i + 1; j < n; ++j) {
            f[i][j] = false;
            if (s[i] === s[j]) {
                f[i][j] = f[i + 1][j - 1];
                if (f[i][j] && mx < j - i + 1) {
                    mx = j - i + 1;
                    k = i;
                }
            }
        }
    }
    return s.slice(k, k + mx);
}
```

```rust
impl Solution {
    pub fn longest_palindrome(s: String) -> String {
        let (n, mut ans) = (s.len(), &s[..1]);
        let mut dp = vec![vec![false; n]; n];
        let data: Vec<char> = s.chars().collect();

        for end in 1..n {
            for start in 0..=end {
                if data[start] == data[end] {
                    dp[start][end] = end - start < 2 || dp[start + 1][end - 1];
                    if dp[start][end] && end - start + 1 > ans.len() {
                        ans = &s[start..=end];
                    }
                }
            }
        }
        ans.to_string()
    }
}
```

```js
/**
 * @param {string} s
 * @return {string}
 */
var longestPalindrome = function (s) {
    const n = s.length;
    const f = Array(n)
        .fill(0)
        .map(() => Array(n).fill(true));
    let k = 0;
    let mx = 1;
    for (let i = n - 2; i >= 0; --i) {
        for (let j = i + 1; j < n; ++j) {
            f[i][j] = false;
            if (s[i] === s[j]) {
                f[i][j] = f[i + 1][j - 1];
                if (f[i][j] && mx < j - i + 1) {
                    mx = j - i + 1;
                    k = i;
                }
            }
        }
    }
    return s.slice(k, k + mx);
};
```

```cs
public class Solution {
    public string LongestPalindrome(string s) {
        int n = s.Length;
        bool[,] f = new bool[n, n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; ++j) {
                f[i, j] = true;
            }
        }
        int k = 0, mx = 1;
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i, j] = false;
                if (s[i] == s[j]) {
                    f[i, j] = f[i + 1, j - 1];
                    if (f[i, j] && mx < j - i + 1) {
                        mx = j - i + 1;
                        k = i;
                    }
                }
            }
        }
        return s.Substring(k, mx);
    }
}
```

```nim
import std/sequtils

proc longestPalindrome(s: string): string =
  let n: int = s.len()
  var
    dp = newSeqWith[bool](n, newSeqWith[bool](n, false))
    start: int = 0
    mx: int = 1

  for j in 0 ..< n:
    for i in 0 .. j:
      if j - i < 2:
        dp[i][j] = s[i] == s[j]
      else:
        dp[i][j] = dp[i + 1][j - 1] and s[i] == s[j]

      if dp[i][j] and mx < j - i + 1:
        start = i
        mx = j - i + 1

  result = s[start ..< start+mx]
```

<!-- tabs:end -->

### Solution 2: Enumerate Palindrome Midpoint

We can enumerate the midpoint of the palindrome, spread to both sides, and find the longest palindrome.

The time complexity is $O(n^2)$, and the space complexity is $O(1)$. Here, $n$ is the length of the string $s$.

<!-- tabs:start -->

```python
class Solution:
    def longestPalindrome(self, s: str) -> str:
        def f(l, r):
            while l >= 0 and r < n and s[l] == s[r]:
                l, r = l - 1, r + 1
            return r - l - 1

        n = len(s)
        start, mx = 0, 1
        for i in range(n):
            a = f(i, i)
            b = f(i, i + 1)
            t = max(a, b)
            if mx < t:
                mx = t
                start = i - ((t - 1) >> 1)
        return s[start : start + mx]
```

```java
class Solution {
    private String s;
    private int n;

    public String longestPalindrome(String s) {
        this.s = s;
        n = s.length();
        int start = 0, mx = 1;
        for (int i = 0; i < n; ++i) {
            int a = f(i, i);
            int b = f(i, i + 1);
            int t = Math.max(a, b);
            if (mx < t) {
                mx = t;
                start = i - ((t - 1) >> 1);
            }
        }
        return s.substring(start, start + mx);
    }

    private int f(int l, int r) {
        while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
            --l;
            ++r;
        }
        return r - l - 1;
    }
}
```

```cpp
class Solution {
public:
    string longestPalindrome(string s) {
        int n = s.size();
        int start = 0, mx = 1;
        auto f = [&](int l, int r) {
            while (l >= 0 && r < n && s[l] == s[r]) {
                l--, r++;
            }
            return r - l - 1;
        };
        for (int i = 0; i < n; ++i) {
            int a = f(i, i);
            int b = f(i, i + 1);
            int t = max(a, b);
            if (mx < t) {
                mx = t;
                start = i - (t - 1 >> 1);
            }
        }
        return s.substr(start, mx);
    }
};
```

```go
func longestPalindrome(s string) string {
	n := len(s)
	start, mx := 0, 1
	f := func(l, r int) int {
		for l >= 0 && r < n && s[l] == s[r] {
			l, r = l-1, r+1
		}
		return r - l - 1
	}
	for i := range s {
		a, b := f(i, i), f(i, i+1)
		t := max(a, b)
		if mx < t {
			mx = t
			start = i - ((t - 1) >> 1)
		}
	}
	return s[start : start+mx]
}
```

```rust
impl Solution {
    pub fn is_palindrome(s: &str) -> bool {
        let mut chars = s.chars();
        while let (Some(c1), Some(c2)) = (chars.next(), chars.next_back()) {
            if c1 != c2 {
                return false;
            }
        }
        true
    }

    pub fn longest_palindrome(s: String) -> String {
        let size = s.len();
        let mut ans = &s[..1];
        for i in 0..size - 1 {
            for j in (i + 1..size).rev() {
                if ans.len() > j - i + 1 {
                    break;
                }
                if Solution::is_palindrome(&s[i..=j]) {
                    ans = &s[i..=j];
                }
            }
        }
        return ans.to_string();
    }
}
```

```php
class Solution {
    /**
     * @param string $s
     * @return string
     */
    function longestPalindrome($s) {
        $start = 0;
        $maxLength = 0;

        for ($i = 0; $i < strlen($s); $i++) {
            $len1 = $this->expandFromCenter($s, $i, $i);
            $len2 = $this->expandFromCenter($s, $i, $i + 1);

            $len = max($len1, $len2);

            if ($len > $maxLength) {
                $start = $i - intval(($len - 1) / 2);
                $maxLength = $len;
            }
        }

        return substr($s, $start, $maxLength);
    }

    function expandFromCenter($s, $left, $right) {
        while ($left >= 0 && $right < strlen($s) && $s[$left] === $s[$right]) {
            $left--;
            $right++;
        }

        return $right - $left - 1;
    }
}
```

<!-- tabs:end -->

<!-- end -->
