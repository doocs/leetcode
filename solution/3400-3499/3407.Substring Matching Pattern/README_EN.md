---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3407.Substring%20Matching%20Pattern/README_EN.md
rating: 1472
source: Biweekly Contest 147 Q1
tags:
    - String
    - String Matching
---

<!-- problem:start -->

# [3407. Substring Matching Pattern](https://leetcode.com/problems/substring-matching-pattern)

[中文文档](/solution/3400-3499/3407.Substring%20Matching%20Pattern/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and a pattern string <code>p</code>, where <code>p</code> contains <strong>exactly one</strong> <code>&#39;*&#39;</code> character.</p>

<p>The <code>&#39;*&#39;</code> in <code>p</code> can be replaced with any sequence of zero or more characters.</p>

<p>Return <code>true</code> if <code>p</code> can be made a <span data-keyword="substring-nonempty">substring</span> of <code>s</code>, and <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;leetcode&quot;, p = &quot;ee*e&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>By replacing the <code>&#39;*&#39;</code> with <code>&quot;tcod&quot;</code>, the substring <code>&quot;eetcode&quot;</code> matches the pattern.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;car&quot;, p = &quot;c*v&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no substring matching the pattern.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;luck&quot;, p = &quot;u*&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>The substrings <code>&quot;u&quot;</code>, <code>&quot;uc&quot;</code>, and <code>&quot;uck&quot;</code> match the pattern.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 50</code></li>
	<li><code>1 &lt;= p.length &lt;= 50 </code></li>
	<li><code>s</code> contains only lowercase English letters.</li>
	<li><code>p</code> contains only lowercase English letters and exactly one <code>&#39;*&#39;</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: String Matching

According to the problem description, `*` can be replaced by any sequence of zero or more characters, so we can split the pattern string $p$ by `*` into several substrings. If these substrings appear in order in the string $s$, then $p$ can become a substring of $s$.

Therefore, we first initialize a pointer $i$ to the start of string $s$, then iterate over each substring $t$ obtained by splitting the pattern string $p$ by `*`. For each $t$, we search for it in $s$ starting from position $i$. If it is found, we move the pointer $i$ to the end of $t$ and continue searching for the next substring. If it is not found, it means the pattern string $p$ cannot become a substring of $s$, and we return $\text{false}$. If all substrings are found, we return $\text{true}$.

The time complexity is $O(n \times m)$, and the space complexity is $O(m)$, where $n$ and $m$ are the lengths of strings $s$ and $p$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def hasMatch(self, s: str, p: str) -> bool:
        i = 0
        for t in p.split("*"):
            j = s.find(t, i)
            if j == -1:
                return False
            i = j + len(t)
        return True
```

#### Java

```java
class Solution {
    public boolean hasMatch(String s, String p) {
        int i = 0;
        for (String t : p.split("\\*")) {
            int j = s.indexOf(t, i);
            if (j == -1) {
                return false;
            }
            i = j + t.length();
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool hasMatch(string s, string p) {
        int i = 0;
        int pos = 0;
        int start = 0, end;
        while ((end = p.find("*", start)) != string::npos) {
            string t = p.substr(start, end - start);
            pos = s.find(t, i);
            if (pos == string::npos) {
                return false;
            }
            i = pos + t.length();
            start = end + 1;
        }
        string t = p.substr(start);
        pos = s.find(t, i);
        if (pos == string::npos) {
            return false;
        }
        return true;
    }
};
```

#### Go

```go
func hasMatch(s string, p string) bool {
	i := 0
	for _, t := range strings.Split(p, "*") {
		j := strings.Index(s[i:], t)
		if j == -1 {
			return false
		}
		i += j + len(t)
	}
	return true
}
```

#### TypeScript

```ts
function hasMatch(s: string, p: string): boolean {
    let i = 0;
    for (const t of p.split('*')) {
        const j = s.indexOf(t, i);
        if (j === -1) {
            return false;
        }
        i = j + t.length;
    }
    return true;
}
```

#### Rust

```rust
impl Solution {
    pub fn has_match(s: String, p: String) -> bool {
        let mut i = 0usize;
        for t in p.split('*') {
            if let Some(j) = s[i..].find(t) {
                i += j + t.len();
            } else {
                return false;
            }
        }
        true
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
