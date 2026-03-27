---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0647.Palindromic%20Substrings/README_EN.md
tags:
    - Two Pointers
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [647. Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings)

[中文文档](/solution/0600-0699/0647.Palindromic%20Substrings/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code>, return <em>the number of <strong>palindromic substrings</strong> in it</em>.</p>

<p>A string is a <strong>palindrome</strong> when it reads the same backward as forward.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within the string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abc&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> Three palindromic strings: &quot;a&quot;, &quot;b&quot;, &quot;c&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaa&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> Six palindromic strings: &quot;a&quot;, &quot;a&quot;, &quot;a&quot;, &quot;aa&quot;, &quot;aa&quot;, &quot;aaa&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Expand Around Center

We can enumerate the center position of each palindrome and expand outward to count the number of palindromic substrings. For a string of length $n$, there are $2n-1$ possible center positions (covering both odd-length and even-length palindromes). For each center, we expand outward until the palindrome condition is no longer satisfied, and count the number of palindromic substrings.

The time complexity is $O(n^2)$, where $n$ is the length of string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSubstrings(self, s: str) -> int:
        ans, n = 0, len(s)
        for k in range(n * 2 - 1):
            i, j = k // 2, (k + 1) // 2
            while ~i and j < n and s[i] == s[j]:
                ans += 1
                i, j = i - 1, j + 1
        return ans
```

#### Java

```java
class Solution {
    public int countSubstrings(String s) {
        int ans = 0;
        int n = s.length();
        for (int k = 0; k < n * 2 - 1; ++k) {
            int i = k / 2, j = (k + 1) / 2;
            while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
                ++ans;
                --i;
                ++j;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countSubstrings(string s) {
        int ans = 0;
        int n = s.size();
        for (int k = 0; k < n * 2 - 1; ++k) {
            int i = k / 2, j = (k + 1) / 2;
            while (~i && j < n && s[i] == s[j]) {
                ++ans;
                --i;
                ++j;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countSubstrings(s string) int {
	ans, n := 0, len(s)
	for k := 0; k < n*2-1; k++ {
		i, j := k/2, (k+1)/2
		for i >= 0 && j < n && s[i] == s[j] {
			ans++
			i, j = i-1, j+1
		}
	}
	return ans
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {number}
 */
var countSubstrings = function (s) {
    let ans = 0;
    const n = s.length;
    for (let k = 0; k < n * 2 - 1; ++k) {
        let i = k >> 1;
        let j = (k + 1) >> 1;
        while (~i && j < n && s[i] == s[j]) {
            ++ans;
            --i;
            ++j;
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Manacher's Algorithm

In Manacher's algorithm, $p[i] - 1$ represents the maximum palindrome length centered at position $i$, and the number of palindromic substrings centered at position $i$ is $\left \lceil \frac{p[i]-1}{2} \right \rceil$.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSubstrings(self, s: str) -> int:
        t = '^#' + '#'.join(s) + '#$'
        n = len(t)
        p = [0 for _ in range(n)]
        pos, maxRight = 0, 0
        ans = 0
        for i in range(1, n - 1):
            p[i] = min(maxRight - i, p[2 * pos - i]) if maxRight > i else 1
            while t[i - p[i]] == t[i + p[i]]:
                p[i] += 1
            if i + p[i] > maxRight:
                maxRight = i + p[i]
                pos = i
            ans += p[i] // 2
        return ans
```

#### Java

```java
class Solution {
    public int countSubstrings(String s) {
        StringBuilder sb = new StringBuilder("^#");
        for (char ch : s.toCharArray()) {
            sb.append(ch).append('#');
        }
        String t = sb.append('$').toString();
        int n = t.length();
        int[] p = new int[n];
        int pos = 0, maxRight = 0;
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            p[i] = maxRight > i ? Math.min(maxRight - i, p[2 * pos - i]) : 1;
            while (t.charAt(i - p[i]) == t.charAt(i + p[i])) {
                p[i]++;
            }
            if (i + p[i] > maxRight) {
                maxRight = i + p[i];
                pos = i;
            }
            ans += p[i] / 2;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countSubstrings(string s) {
        string t = "^#";
        for (char c : s) {
            t += c;
            t += '#';
        }
        t += "$";

        int n = t.size();
        vector<int> p(n, 0);
        int pos = 0, maxRight = 0;
        int ans = 0;

        for (int i = 1; i < n - 1; ++i) {
            if (maxRight > i) {
                p[i] = min(maxRight - i, p[2 * pos - i]);
            } else {
                p[i] = 1;
            }

            while (t[i - p[i]] == t[i + p[i]]) {
                ++p[i];
            }

            if (i + p[i] > maxRight) {
                maxRight = i + p[i];
                pos = i;
            }

            ans += p[i] / 2;
        }

        return ans;
    }
};
```

#### Go

```go
func countSubstrings(s string) int {
	t := "^#"
	for _, c := range s {
		t += string(c)
		t += "#"
	}
	t += "$"

	n := len(t)
	p := make([]int, n)
	pos, maxRight := 0, 0
	ans := 0

	for i := 1; i < n-1; i++ {
		if maxRight > i {
			mirror := 2*pos - i
			if p[mirror] < maxRight-i {
				p[i] = p[mirror]
			} else {
				p[i] = maxRight - i
			}
		} else {
			p[i] = 1
		}

		for t[i-p[i]] == t[i+p[i]] {
			p[i]++
		}

		if i+p[i] > maxRight {
			maxRight = i + p[i]
			pos = i
		}

		ans += p[i] / 2
	}

	return ans
}
```

#### TypeScript

```ts
function countSubstrings(s: string): number {
    let t = "^#";
    for (const c of s) {
        t += c + "#";
    }
    t += "$";

    const n = t.length;
    const p: number[] = new Array(n).fill(0);
    let pos = 0, maxRight = 0;
    let ans = 0;

    for (let i = 1; i < n - 1; i++) {
        if (maxRight > i) {
            p[i] = Math.min(maxRight - i, p[2 * pos - i]);
        } else {
            p[i] = 1;
        }

        while (t[i - p[i]] === t[i + p[i]]) {
            p[i]++;
        }

        if (i + p[i] > maxRight) {
            maxRight = i + p[i];
            pos = i;
        }

        ans += Math.floor(p[i] / 2);
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
