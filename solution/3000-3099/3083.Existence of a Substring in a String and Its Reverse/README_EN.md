# [3083. Existence of a Substring in a String and Its Reverse](https://leetcode.com/problems/existence-of-a-substring-in-a-string-and-its-reverse)

[中文文档](/solution/3000-3099/3083.Existence%20of%20a%20Substring%20in%20a%20String%20and%20Its%20Reverse/README.md)

<!-- tags:Hash Table,String -->

<!-- difficulty:Easy -->

## Description

<p>Given a<strong> </strong>string <code>s</code>, find any <span data-keyword="substring">substring</span> of length <code>2</code> which is also present in the reverse of <code>s</code>.</p>

<p>Return <code>true</code><em> if such a substring exists, and </em><code>false</code><em> otherwise.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">s = &quot;leetcode&quot;</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">true</span></p>

<p><strong>Explanation:</strong> Substring <code>&quot;ee&quot;</code> is of length <code>2</code> which is also present in <code>reverse(s) == &quot;edocteel&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">s = &quot;abcba&quot;</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">true</span></p>

<p><strong>Explanation:</strong> All of the substrings of length <code>2</code> <code>&quot;ab&quot;</code>, <code>&quot;bc&quot;</code>, <code>&quot;cb&quot;</code>, <code>&quot;ba&quot;</code> are also present in <code>reverse(s) == &quot;abcba&quot;</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">s = &quot;abcd&quot;</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">false</span></p>

<p><strong>Explanation:</strong> There is no substring of length <code>2</code> in <code>s</code>, which is also present in the reverse of <code>s</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: Hash Table or Array

We can use a hash table or a two-dimensional array $st$ to store all substrings of length $2$ of the reversed string $s$.

Then we traverse the string $s$. For each substring of length $2$, we check whether it has appeared in $st$. If it has, we return `true`. Otherwise, we return `false` after the traversal.

The time complexity is $O(n)$ and the space complexity is $O(|\Sigma|^2)$. Here, $n$ is the length of the string $s$, and $\Sigma$ is the character set of the string $s$. In this problem, $\Sigma$ consists of lowercase English letters, so $|\Sigma| = 26$.

<!-- tabs:start -->

```python
class Solution:
    def isSubstringPresent(self, s: str) -> bool:
        st = {(a, b) for a, b in pairwise(s[::-1])}
        return any((a, b) in st for a, b in pairwise(s))
```

```java
class Solution {
    public boolean isSubstringPresent(String s) {
        boolean[][] st = new boolean[26][26];
        int n = s.length();
        for (int i = 0; i < n - 1; ++i) {
            st[s.charAt(i + 1) - 'a'][s.charAt(i) - 'a'] = true;
        }
        for (int i = 0; i < n - 1; ++i) {
            if (st[s.charAt(i) - 'a'][s.charAt(i + 1) - 'a']) {
                return true;
            }
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    bool isSubstringPresent(string s) {
        bool st[26][26]{};
        int n = s.size();
        for (int i = 0; i < n - 1; ++i) {
            st[s[i + 1] - 'a'][s[i] - 'a'] = true;
        }
        for (int i = 0; i < n - 1; ++i) {
            if (st[s[i] - 'a'][s[i + 1] - 'a']) {
                return true;
            }
        }
        return false;
    }
};
```

```go
func isSubstringPresent(s string) bool {
	st := [26][26]bool{}
	for i := 0; i < len(s)-1; i++ {
		st[s[i+1]-'a'][s[i]-'a'] = true
	}
	for i := 0; i < len(s)-1; i++ {
		if st[s[i]-'a'][s[i+1]-'a'] {
			return true
		}
	}
	return false
}
```

```ts
function isSubstringPresent(s: string): boolean {
    const st: boolean[][] = Array.from({ length: 26 }, () => Array(26).fill(false));
    for (let i = 0; i < s.length - 1; ++i) {
        st[s.charCodeAt(i + 1) - 97][s.charCodeAt(i) - 97] = true;
    }
    for (let i = 0; i < s.length - 1; ++i) {
        if (st[s.charCodeAt(i) - 97][s.charCodeAt(i + 1) - 97]) {
            return true;
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- end -->
