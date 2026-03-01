---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3856.Trim%20Trailing%20Vowels/README_EN.md
---

<!-- problem:start -->

# [3856. Trim Trailing Vowels](https://leetcode.com/problems/trim-trailing-vowels)

[中文文档](/solution/3800-3899/3856.Trim%20Trailing%20Vowels/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> that consists of lowercase English letters.</p>

<p>Return the string obtained by removing <strong>all</strong> trailing <strong>vowels</strong> from <code>s</code>.</p>

<p>The <strong>vowels</strong> consist of the characters <code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, and <code>&#39;u&#39;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;idea&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;id&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Removing <code>&quot;id<u><strong>ea</strong></u>&quot;</code>, we obtain the string <code>&quot;id&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;day&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;day&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no trailing vowels in the string <code>&quot;day&quot;</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aeiou&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Removing <code>&quot;<u><strong>aeiou</strong></u>&quot;</code>, we obtain the string <code>&quot;&quot;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Reverse Traversal

We traverse the string from the end in reverse order until we encounter the first non-vowel character. Then we return the substring from the beginning of the string up to that position.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def trimTrailingVowels(self, s: str) -> str:
        i = len(s) - 1
        while i >= 0 and s[i] in "aeiou":
            i -= 1
        return s[: i + 1]
```

#### Java

```java
class Solution {
    public String trimTrailingVowels(String s) {
        int i = s.length() - 1;
        while (i >= 0 && "aeiou".indexOf(s.charAt(i)) != -1) {
            i--;
        }
        return s.substring(0, i + 1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string trimTrailingVowels(string s) {
        int i = s.size() - 1;
        while (i >= 0 && string("aeiou").find(s[i]) != string::npos) {
            i--;
        }
        return s.substr(0, i + 1);
    }
};
```

#### Go

```go
func trimTrailingVowels(s string) string {
	i := len(s) - 1
	for i >= 0 && strings.IndexByte("aeiou", s[i]) != -1 {
		i--
	}
	return s[:i+1]
}
```

#### TypeScript

```ts
function trimTrailingVowels(s: string): string {
    let i = s.length - 1;
    while (i >= 0 && 'aeiou'.indexOf(s[i]) !== -1) {
        i--;
    }
    return s.slice(0, i + 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
