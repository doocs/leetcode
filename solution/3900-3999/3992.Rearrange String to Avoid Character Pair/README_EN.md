---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3992.Rearrange%20String%20to%20Avoid%20Character%20Pair/README_EN.md
---

<!-- problem:start -->

# [3992. Rearrange String to Avoid Character Pair](https://leetcode.com/problems/rearrange-string-to-avoid-character-pair)

[中文文档](/solution/3900-3999/3992.Rearrange%20String%20to%20Avoid%20Character%20Pair/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and two distinct lowercase English letters <code>x</code> and <code>y</code>.</p>

<p>Rearrange the characters of <code>s</code> to construct a new string <code>t</code> such that:</p>

<ul>
	<li><code>t</code> is a <span data-keyword="permutation-string">permutation</span> of <code>s</code>.</li>
	<li>Every occurrence of <code>y</code> appears before every occurrence of <code>x</code> in <code>t</code>.</li>
</ul>

<p>Return any valid string <code>t</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aabc&quot;, x = &quot;a&quot;, y = &quot;c&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;cbaa&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>The string <code>&quot;cbaa&quot;</code> is a permutation of <code>&quot;aabc&quot;</code>, and every occurrence of <code>&#39;c&#39;</code> appears before every occurrence of <code>&#39;a&#39;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;dcab&quot;, x = &quot;d&quot;, y = &quot;b&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;cabd&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>The string <code>&quot;cabd&quot;</code> is a permutation of <code>&quot;dcab&quot;</code>, and every occurrence of <code>&#39;b&#39;</code> appears before every occurrence of <code>&#39;d&#39;</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;axe&quot;, x = &quot;o&quot;, y = &quot;x&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;axe&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>The string <code>&quot;axe&quot;</code> is already valid. Since <code>&#39;o&#39;</code> does not occur in the string, the required condition is automatically satisfied.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>x</code> and <code>y</code> are lowercase English letters.</li>
	<li><code>x != y</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

We need to construct a permutation $t$ of $s$ such that every occurrence of $y$ appears before every occurrence of $x$. There are no extra constraints on the other characters.

Therefore, it suffices to move all occurrences of $y$ to the front of the string. Traverse the string with two pointers: $i$ points to the next position where a $y$ should be placed, and $j$ scans from left to right. Whenever $t[j] = y$, swap $t[i]$ with $t[j]$ and increment $i$. After the scan, the prefix of $t$ consists entirely of $y$, which naturally satisfies the requirement that all $y$ appear before all $x$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rearrangeString(self, s: str, x: str, y: str) -> str:
        t = list(s)
        i = 0
        for j, c in enumerate(t):
            if c == y:
                t[i], t[j] = c, t[i]
                i += 1
        return ''.join(t)
```

#### Java

```java
class Solution {
    public String rearrangeString(String s, char x, char y) {
        char[] t = s.toCharArray();
        int i = 0;
        for (int j = 0; j < t.length; j++) {
            if (t[j] == y) {
                char tmp = t[i];
                t[i] = t[j];
                t[j] = tmp;
                i++;
            }
        }
        return new String(t);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string rearrangeString(string s, char x, char y) {
        int i = 0;
        for (int j = 0; j < s.size(); j++) {
            if (s[j] == y) {
                swap(s[i], s[j]);
                i++;
            }
        }
        return s;
    }
};
```

#### Go

```go
func rearrangeString(s string, x byte, y byte) string {
	t := []byte(s)
	i := 0
	for j, c := range t {
		if c == y {
			t[i], t[j] = t[j], t[i]
			i++
		}
	}
	return string(t)
}
```

#### TypeScript

```ts
function rearrangeString(s: string, x: string, y: string): string {
    const t = s.split('');
    let i = 0;
    for (let j = 0; j < t.length; j++) {
        if (t[j] === y) {
            [t[i], t[j]] = [t[j], t[i]];
            i++;
        }
    }
    return t.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
