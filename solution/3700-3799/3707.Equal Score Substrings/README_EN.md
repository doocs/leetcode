---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3707.Equal%20Score%20Substrings/README_EN.md
rating: 1262
source: Biweekly Contest 167 Q1
tags:
    - String
    - Prefix Sum
---

<!-- problem:start -->

# [3707. Equal Score Substrings](https://leetcode.com/problems/equal-score-substrings)

[中文文档](/solution/3700-3799/3707.Equal%20Score%20Substrings/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of lowercase English letters.</p>

<p>The <strong>score</strong> of a string is the sum of the positions of its characters in the alphabet, where <code>&#39;a&#39; = 1</code>, <code>&#39;b&#39; = 2</code>, ..., <code>&#39;z&#39; = 26</code>.</p>

<p>Determine whether there exists an index <code>i</code> such that the string can be split into two <strong>non-empty</strong> <strong><strong><span data-keyword="substring-nonempty">substrings</span></strong></strong> <code>s[0..i]</code> and <code>s[(i + 1)..(n - 1)]</code> that have <strong>equal</strong> scores.</p>

<p>Return <code>true</code> if such a split exists, otherwise return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;adcb&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>Split at index <code>i = 1</code>:</p>

<ul>
	<li>Left substring = <code>s[0..1] = &quot;ad&quot;</code> with <code>score = 1 + 4 = 5</code></li>
	<li>Right substring = <code>s[2..3] = &quot;cb&quot;</code> with <code>score = 3 + 2 = 5</code></li>
</ul>

<p>Both substrings have equal scores, so the output is <code>true</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;bace&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:​​​​​​</strong></p>

<p><strong>​​​​​​​</strong>No split produces equal scores, so the output is <code>false</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum

We first calculate the total score of the string, denoted as $r$. Then we traverse the first $n-1$ characters from left to right, calculating the prefix score $l$ and updating the suffix score $r$. If at some position $i$, the prefix score $l$ equals the suffix score $r$, it means there exists an index $i$ that can split the string into two substrings with equal scores, so we return $\textit{true}$. If we finish traversing without finding such an index, we return $\textit{false}$.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def scoreBalance(self, s: str) -> bool:
        l = 0
        r = sum(ord(c) - ord("a") + 1 for c in s)
        for c in s[:-1]:
            x = ord(c) - ord("a") + 1
            l += x
            r -= x
            if l == r:
                return True
        return False
```

#### Java

```java
class Solution {
    public boolean scoreBalance(String s) {
        int n = s.length();
        int l = 0, r = 0;
        for (int i = 0; i < n; ++i) {
            int x = s.charAt(i) - 'a' + 1;
            r += x;
        }
        for (int i = 0; i < n - 1; ++i) {
            int x = s.charAt(i) - 'a' + 1;
            l += x;
            r -= x;
            if (l == r) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool scoreBalance(string s) {
        int l = 0, r = 0;
        for (char c : s) {
            int x = c - 'a' + 1;
            r += x;
        }
        for (int i = 0; i < s.size() - 1; ++i) {
            int x = s[i] - 'a' + 1;
            l += x;
            r -= x;
            if (l == r) {
                return true;
            }
        }
        return false;
    }
};
```

#### Go

```go
func scoreBalance(s string) bool {
	var l, r int
	for _, c := range s {
		x := int(c-'a') + 1
		r += x
	}
	for _, c := range s[:len(s)-1] {
		x := int(c-'a') + 1
		l += x
		r -= x
		if l == r {
			return true
		}
	}
	return false
}
```

#### TypeScript

```ts
function scoreBalance(s: string): boolean {
    let [l, r] = [0, 0];
    for (const c of s) {
        const x = c.charCodeAt(0) - 96;
        r += x;
    }
    for (let i = 0; i < s.length - 1; ++i) {
        const x = s[i].charCodeAt(0) - 96;
        l += x;
        r -= x;
        if (l === r) {
            return true;
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
