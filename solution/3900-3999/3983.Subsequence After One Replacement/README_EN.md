---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3983.Subsequence%20After%20One%20Replacement/README_EN.md
rating: 1754
source: Weekly Contest 509 Q2
---

<!-- problem:start -->

# [3983. Subsequence After One Replacement](https://leetcode.com/problems/subsequence-after-one-replacement)

[中文文档](/solution/3900-3999/3983.Subsequence%20After%20One%20Replacement/README.md)

## Description

<!-- description:start -->

<p>You are given two strings <code>s</code> and <code>t</code> consisting of lowercase English letters.</p>

<p>You may choose <strong>at most</strong> one index in <code>s</code> and replace the character at that index with any lowercase English letter.</p>

<p>Return <code>true</code> if it is possible to make <code>s</code> a <span data-keyword="subsequence-string">subsequence</span> of <code>t</code>; otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;cat&quot;, t = &quot;chat&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Replace <code>s[1]</code> from <code>&#39;a&#39;</code> to <code>&#39;h&#39;</code>. The resulting string is <code>&quot;cht&quot;</code>.</li>
	<li><code>&quot;cht&quot;</code> is a subsequence of <code>&quot;chat&quot;</code> because we can match <code>&#39;c&#39;</code>, <code>&#39;h&#39;</code>, and <code>&#39;t&#39;</code> in order.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;plane&quot;, t = &quot;apple&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The characters <code>&#39;p&#39;</code>, <code>&#39;l&#39;</code>, and <code>&#39;e&#39;</code> can be matched in <code>t</code>, but the remaining characters cannot be matched while preserving the required order.</li>
	<li>Even after replacing any one character in <code>s</code>, it is impossible to make <code>s</code> a subsequence of <code>t</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> and <code>t</code> consist only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

The problem is equivalent to asking whether we can greedily match $s$ as a subsequence of $t$ while allowing at most one character in $s$ to mismatch, since that character can be replaced with any letter.

We scan $s$ with two pointers $i_0$ and $i_1$, and scan $t$ with pointer $j$:

- $i_0$ is the current position in $s$ when matching without using the replacement.
- $i_1$ is the current position in $s$ when matching with at most one replacement available.

For each character $t[j]$:

1. If $s[i_1] = t[j]$, move $i_1$ forward by one.
2. Set $i_1 = \max(i_1, i_0 + 1)$ so the replacement position is never before $i_0$, reserving one character for the replacement.
3. If $s[i_0] = t[j]$, move $i_0$ forward by one.
4. Move $j$ forward by one.

After the scan, if $i_1 = |s|$, then all characters of $s$ can be matched in order within $t$ using at most one replacement, so return `true`; otherwise return `false`.

The time complexity is $O(|s| + |t|)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canMakeSubsequence(self, s: str, t: str) -> bool:
        m, n = len(s), len(t)
        i0 = i1 = j = 0

        while i1 < m and j < n:
            if s[i1] == t[j]:
                i1 += 1
            i1 = max(i1, i0 + 1)

            if s[i0] == t[j]:
                i0 += 1

            j += 1

        return i1 == m
```

#### Java

```java
class Solution {
    public boolean canMakeSubsequence(String s, String t) {
        int m = s.length(), n = t.length();
        int i0 = 0, i1 = 0, j = 0;

        while (i1 < m && j < n) {
            if (s.charAt(i1) == t.charAt(j)) {
                i1++;
            }

            i1 = Math.max(i1, i0 + 1);

            if (s.charAt(i0) == t.charAt(j)) {
                i0++;
            }

            j++;
        }

        return i1 == m;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canMakeSubsequence(string s, string t) {
        int m = s.size(), n = t.size();
        int i0 = 0, i1 = 0, j = 0;

        while (i1 < m && j < n) {
            if (s[i1] == t[j]) {
                i1++;
            }

            i1 = max(i1, i0 + 1);

            if (s[i0] == t[j]) {
                i0++;
            }

            j++;
        }

        return i1 == m;
    }
};
```

#### Go

```go
func canMakeSubsequence(s string, t string) bool {
	m, n := len(s), len(t)
	i0, i1, j := 0, 0, 0

	for i1 < m && j < n {
		if s[i1] == t[j] {
			i1++
		}

		if i1 < i0+1 {
			i1 = i0 + 1
		}

		if s[i0] == t[j] {
			i0++
		}

		j++
	}

	return i1 == m
}
```

#### TypeScript

```ts
function canMakeSubsequence(s: string, t: string): boolean {
    const m = s.length,
        n = t.length;
    let i0 = 0,
        i1 = 0,
        j = 0;

    while (i1 < m && j < n) {
        if (s[i1] === t[j]) {
            i1++;
        }

        i1 = Math.max(i1, i0 + 1);

        if (s[i0] === t[j]) {
            i0++;
        }

        j++;
    }

    return i1 === m;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
