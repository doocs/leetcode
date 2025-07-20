---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3571.Find%20the%20Shortest%20Superstring%20II/README_EN.md
tags:
    - String
---

<!-- problem:start -->

# [3571. Find the Shortest Superstring II 🔒](https://leetcode.com/problems/find-the-shortest-superstring-ii)

[中文文档](/solution/3500-3599/3571.Find%20the%20Shortest%20Superstring%20II/README.md)

## Description

<!-- description:start -->

<p>You are given <strong>two</strong> strings, <code>s1</code> and <code>s2</code>. Return the <strong>shortest</strong> <em>possible</em> string that contains both <code>s1</code> and <code>s2</code> as substrings. If there are multiple valid answers, return <em>any </em>one of them.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s1 = &quot;aba&quot;, s2 = &quot;bab&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;abab&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p><code>&quot;abab&quot;</code> is the shortest string that contains both <code>&quot;aba&quot;</code> and <code>&quot;bab&quot;</code> as substrings.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s1 = &quot;aa&quot;, s2 = &quot;aaa&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;aaa&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p><code>&quot;aa&quot;</code> is already contained within <code>&quot;aaa&quot;</code>, so the shortest superstring is <code>&quot;aaa&quot;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li data-end="23" data-start="2"><code>1 &lt;= s1.length &lt;= 100</code></li>
	<li data-end="47" data-start="26"><code>1 &lt;= s2.length &lt;= 100</code></li>
	<li data-end="102" data-is-last-node="" data-start="50"><code>s1</code> and <code>s2</code> consist of lowercase English letters only.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumerate Overlapping Parts

We can construct the shortest string containing both `s1` and `s2` as substrings by enumerating the overlapping parts of the two strings.

Our goal is to build the shortest string that contains both `s1` and `s2` as substrings. Since substrings must be contiguous, we try to overlap the **suffix** of one string with the **prefix** of the other, thereby reducing the total length when concatenating.

Specifically, there are several cases:

1. **Containment**: If `s1` is a substring of `s2`, then `s2` itself satisfies the condition, so just return `s2`; vice versa as well.
2. **s1 concatenated before s2**: Enumerate whether a suffix of `s1` matches a prefix of `s2`, and concatenate after finding the maximum overlap.
3. **s2 concatenated before s1**: Enumerate whether a prefix of `s1` matches a suffix of `s2`, and concatenate after finding the maximum overlap.
4. **No overlap**: If there is no overlap between the suffix/prefix of the two strings, simply return `s1 + s2`.

We try both concatenation orders and return the shorter one (if the lengths are equal, either is acceptable).

The time complexity is $O(n^2)$ and the space complexity is $O(n)$, where $n$ is the maximum length of `s1` and `s2`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def shortestSuperstring(self, s1: str, s2: str) -> str:
        m, n = len(s1), len(s2)
        if m > n:
            return self.shortestSuperstring(s2, s1)
        if s1 in s2:
            return s2
        for i in range(m):
            if s2.startswith(s1[i:]):
                return s1[:i] + s2
            if s2.endswith(s1[: m - i]):
                return s2 + s1[m - i :]
        return s1 + s2
```

#### Java

```java
class Solution {
    public String shortestSuperstring(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        if (m > n) {
            return shortestSuperstring(s2, s1);
        }
        if (s2.contains(s1)) {
            return s2;
        }
        for (int i = 0; i < m; i++) {
            if (s2.startsWith(s1.substring(i))) {
                return s1.substring(0, i) + s2;
            }
            if (s2.endsWith(s1.substring(0, m - i))) {
                return s2 + s1.substring(m - i);
            }
        }
        return s1 + s2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string shortestSuperstring(string s1, string s2) {
        int m = s1.size(), n = s2.size();
        if (m > n) {
            return shortestSuperstring(s2, s1);
        }
        if (s2.find(s1) != string::npos) {
            return s2;
        }
        for (int i = 0; i < m; ++i) {
            if (s2.find(s1.substr(i)) == 0) {
                return s1.substr(0, i) + s2;
            }
            if (s2.rfind(s1.substr(0, m - i)) == s2.size() - (m - i)) {
                return s2 + s1.substr(m - i);
            }
        }
        return s1 + s2;
    }
};
```

#### Go

```go
func shortestSuperstring(s1 string, s2 string) string {
	m, n := len(s1), len(s2)

	if m > n {
		return shortestSuperstring(s2, s1)
	}

	if strings.Contains(s2, s1) {
		return s2
	}

	for i := 0; i < m; i++ {
		if strings.HasPrefix(s2, s1[i:]) {
			return s1[:i] + s2
		}
		if strings.HasSuffix(s2, s1[:m-i]) {
			return s2 + s1[m-i:]
		}
	}

	return s1 + s2
}
```

#### TypeScript

```ts
function shortestSuperstring(s1: string, s2: string): string {
    const m = s1.length,
        n = s2.length;

    if (m > n) {
        return shortestSuperstring(s2, s1);
    }

    if (s2.includes(s1)) {
        return s2;
    }

    for (let i = 0; i < m; i++) {
        if (s2.startsWith(s1.slice(i))) {
            return s1.slice(0, i) + s2;
        }
        if (s2.endsWith(s1.slice(0, m - i))) {
            return s2 + s1.slice(m - i);
        }
    }

    return s1 + s2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
