---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3722.Lexicographically%20Smallest%20String%20After%20Reverse/README_EN.md
rating: 1414
source: Biweekly Contest 168 Q1
tags:
    - Two Pointers
    - Binary Search
    - Enumeration
---

<!-- problem:start -->

# [3722. Lexicographically Smallest String After Reverse](https://leetcode.com/problems/lexicographically-smallest-string-after-reverse)

[中文文档](/solution/3700-3799/3722.Lexicographically%20Smallest%20String%20After%20Reverse/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> of length <code>n</code> consisting of lowercase English letters.</p>

<p>You must perform <strong>exactly</strong> one operation by choosing any integer <code>k</code> such that <code>1 &lt;= k &lt;= n</code> and either:</p>

<ul>
	<li>reverse the <strong>first</strong> <code>k</code> characters of <code>s</code>, or</li>
	<li>reverse the <strong>last</strong> <code>k</code> characters of <code>s</code>.</li>
</ul>

<p>Return the <strong><span data-keyword="lexicographically-smaller-string">lexicographically smallest</span></strong> string that can be obtained after <strong>exactly</strong> one such operation.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;dcab&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;acdb&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Choose <code>k = 3</code>, reverse the first 3 characters.</li>
	<li>Reverse <code>&quot;dca&quot;</code> to <code>&quot;acd&quot;</code>, resulting string <code>s = &quot;acdb&quot;</code>, which is the lexicographically smallest string achievable.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abba&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;aabb&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Choose <code>k = 3</code>, reverse the last 3 characters.</li>
	<li>Reverse <code>&quot;bba&quot;</code> to <code>&quot;abb&quot;</code>, so the resulting string is <code>&quot;aabb&quot;</code>, which is the lexicographically smallest string achievable.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;zxy&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;xzy&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Choose <code>k = 2</code>, reverse the first 2 characters.</li>
	<li>Reverse <code>&quot;zx&quot;</code> to <code>&quot;xz&quot;</code>, so the resulting string is <code>&quot;xzy&quot;</code>, which is the lexicographically smallest string achievable.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == s.length &lt;= 1000</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We can enumerate all possible values of $k$ ($1 \leq k \leq n$). For each $k$, we compute the string obtained by reversing the first $k$ characters and the string obtained by reversing the last $k$ characters, then take the lexicographically smallest string among them as the final answer.

The time complexity is $O(n^2)$ and the space complexity is $O(n)$, where $n$ is the length of the string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lexSmallest(self, s: str) -> str:
        ans = s
        for k in range(1, len(s) + 1):
            t1 = s[:k][::-1] + s[k:]
            t2 = s[:-k] + s[-k:][::-1]
            ans = min(ans, t1, t2)
        return ans
```

#### Java

```java
class Solution {
    public String lexSmallest(String s) {
        String ans = s;
        int n = s.length();
        for (int k = 1; k <= n; ++k) {
            String t1 = new StringBuilder(s.substring(0, k)).reverse().toString() + s.substring(k);
            String t2 = s.substring(0, n - k)
                + new StringBuilder(s.substring(n - k)).reverse().toString();
            if (t1.compareTo(ans) < 0) {
                ans = t1;
            }
            if (t2.compareTo(ans) < 0) {
                ans = t2;
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
    string lexSmallest(string s) {
        string ans = s;
        int n = s.size();
        for (int k = 1; k <= n; ++k) {
            string t1 = s.substr(0, k);
            reverse(t1.begin(), t1.end());
            t1 += s.substr(k);

            string t2 = s.substr(0, n - k);
            string suffix = s.substr(n - k);
            reverse(suffix.begin(), suffix.end());
            t2 += suffix;

            ans = min({ans, t1, t2});
        }
        return ans;
    }
};
```

#### Go

```go
func lexSmallest(s string) string {
	ans := s
	n := len(s)
	for k := 1; k <= n; k++ {
		t1r := []rune(s[:k])
		slices.Reverse(t1r)
		t1 := string(t1r) + s[k:]

		t2r := []rune(s[n-k:])
		slices.Reverse(t2r)
		t2 := s[:n-k] + string(t2r)

		ans = min(ans, t1, t2)
	}
	return ans
}
```

#### TypeScript

```ts
function lexSmallest(s: string): string {
    let ans = s;
    const n = s.length;
    for (let k = 1; k <= n; ++k) {
        const t1 = reverse(s.slice(0, k)) + s.slice(k);
        const t2 = s.slice(0, n - k) + reverse(s.slice(n - k));
        ans = [ans, t1, t2].sort()[0];
    }
    return ans;
}

function reverse(s: string): string {
    return s.split('').reverse().join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
