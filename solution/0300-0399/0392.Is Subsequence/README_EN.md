---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0392.Is%20Subsequence/README_EN.md
tags:
    - Two Pointers
    - String
    - Dynamic Programming
---

# [392. Is Subsequence](https://leetcode.com/problems/is-subsequence)

[中文文档](/solution/0300-0399/0392.Is%20Subsequence/README.md)

## Description

<p>Given two strings <code>s</code> and <code>t</code>, return <code>true</code><em> if </em><code>s</code><em> is a <strong>subsequence</strong> of </em><code>t</code><em>, or </em><code>false</code><em> otherwise</em>.</p>

<p>A <strong>subsequence</strong> of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., <code>&quot;ace&quot;</code> is a subsequence of <code>&quot;<u>a</u>b<u>c</u>d<u>e</u>&quot;</code> while <code>&quot;aec&quot;</code> is not).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> s = "abc", t = "ahbgdc"
<strong>Output:</strong> true
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> s = "axc", t = "ahbgdc"
<strong>Output:</strong> false
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 100</code></li>
	<li><code>0 &lt;= t.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> and <code>t</code> consist only of lowercase English letters.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Suppose there are lots of incoming <code>s</code>, say <code>s<sub>1</sub>, s<sub>2</sub>, ..., s<sub>k</sub></code> where <code>k &gt;= 10<sup>9</sup></code>, and you want to check one by one to see if <code>t</code> has its subsequence. In this scenario, how would you change your code?

## Solutions

### Solution 1: Two Pointers

We define two pointers $i$ and $j$ to point to the initial position of the string $s$ and $t$ respectively. Each time we compare the two characters pointed to by the two pointers, if they are the same, both pointers move right at the same time; if they are not the same, only $j$ moves right. When the pointer $i$ moves to the end of the string $s$, it means that $s$ is the subsequence of $t$.

The time complexity is $O(m + n)$, where $m$ and $n$ are the lengths of the strings $s$ and $t$ respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def isSubsequence(self, s: str, t: str) -> bool:
        i = j = 0
        while i < len(s) and j < len(t):
            if s[i] == t[j]:
                i += 1
            j += 1
        return i == len(s)
```

```java
class Solution {
    public boolean isSubsequence(String s, String t) {
        int m = s.length(), n = t.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (s.charAt(i) == t.charAt(j)) {
                ++i;
            }
            ++j;
        }
        return i == m;
    }
}
```

```cpp
class Solution {
public:
    bool isSubsequence(string s, string t) {
        int m = s.size(), n = t.size();
        int i = 0, j = 0;
        for (; i < m && j < n; ++j) {
            if (s[i] == t[j]) {
                ++i;
            }
        }
        return i == m;
    }
};
```

```go
func isSubsequence(s string, t string) bool {
	i, j, m, n := 0, 0, len(s), len(t)
	for i < m && j < n {
		if s[i] == t[j] {
			i++
		}
		j++
	}
	return i == m
}
```

```ts
function isSubsequence(s: string, t: string): boolean {
    const m = s.length;
    const n = t.length;
    let i = 0;
    for (let j = 0; i < m && j < n; ++j) {
        if (s[i] === t[j]) {
            ++i;
        }
    }
    return i === m;
}
```

```rust
impl Solution {
    pub fn is_subsequence(s: String, t: String) -> bool {
        let (s, t) = (s.as_bytes(), t.as_bytes());
        let n = t.len();
        let mut i = 0;
        for &c in s.iter() {
            while i < n && t[i] != c {
                i += 1;
            }
            if i == n {
                return false;
            }
            i += 1;
        }
        true
    }
}
```

```cs
public class Solution {
    public bool IsSubsequence(string s, string t) {
        int m = s.Length, n = t.Length;
        int i = 0, j = 0;
        for (; i < m && j < n; ++j) {
            if (s[i] == t[j]) {
                ++i;
            }
        }
        return i == m;
    }
}
```

```c
bool isSubsequence(char* s, char* t) {
    int m = strlen(s);
    int n = strlen(t);
    int i = 0;
    for (int j = 0; i < m && j < n; ++j) {
        if (s[i] == t[j]) {
            ++i;
        }
    }
    return i == m;
}
```

<!-- tabs:end -->

<!-- end -->
