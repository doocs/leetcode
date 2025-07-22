---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1930.Unique%20Length-3%20Palindromic%20Subsequences/README_EN.md
rating: 1533
source: Weekly Contest 249 Q2
tags:
    - Bit Manipulation
    - Hash Table
    - String
    - Prefix Sum
---

<!-- problem:start -->

# [1930. Unique Length-3 Palindromic Subsequences](https://leetcode.com/problems/unique-length-3-palindromic-subsequences)

[中文文档](/solution/1900-1999/1930.Unique%20Length-3%20Palindromic%20Subsequences/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code>, return <em>the number of <strong>unique palindromes of length three</strong> that are a <strong>subsequence</strong> of </em><code>s</code>.</p>

<p>Note that even if there are multiple ways to obtain the same subsequence, it is still only counted <strong>once</strong>.</p>

<p>A <strong>palindrome</strong> is a string that reads the same forwards and backwards.</p>

<p>A <strong>subsequence</strong> of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.</p>

<ul>
	<li>For example, <code>&quot;ace&quot;</code> is a subsequence of <code>&quot;<u>a</u>b<u>c</u>d<u>e</u>&quot;</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabca&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The 3 palindromic subsequences of length 3 are:
- &quot;aba&quot; (subsequence of &quot;<u>a</u>a<u>b</u>c<u>a</u>&quot;)
- &quot;aaa&quot; (subsequence of &quot;<u>aa</u>bc<u>a</u>&quot;)
- &quot;aca&quot; (subsequence of &quot;<u>a</u>ab<u>ca</u>&quot;)
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;adc&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no palindromic subsequences of length 3 in &quot;adc&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bbcbaba&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> The 4 palindromic subsequences of length 3 are:
- &quot;bbb&quot; (subsequence of &quot;<u>bb</u>c<u>b</u>aba&quot;)
- &quot;bcb&quot; (subsequence of &quot;<u>b</u>b<u>cb</u>aba&quot;)
- &quot;bab&quot; (subsequence of &quot;<u>b</u>bcb<u>ab</u>a&quot;)
- &quot;aba&quot; (subsequence of &quot;bbcb<u>aba</u>&quot;)
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumerate Both End Characters + Hash Table

Since the string contains only lowercase letters, we can directly enumerate all pairs of end characters. For each pair of end characters $c$, we find their first and last occurrence positions $l$ and $r$ in the string. If $r - l > 1$, it means we have found a palindromic subsequence that meets the conditions. We then count the number of unique characters between $[l+1,..r-1]$, which gives the number of palindromic subsequences with $c$ as the end characters, and add it to the answer.

After enumerating all pairs, we get the answer.

The time complexity is $O(n \times |\Sigma|)$, where $n$ is the length of the string and $\Sigma$ is the size of the character set. In this problem, $|\Sigma| = 26$. The space complexity is $O(|\Sigma|)$ or $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPalindromicSubsequence(self, s: str) -> int:
        ans = 0
        for c in ascii_lowercase:
            l, r = s.find(c), s.rfind(c)
            if r - l > 1:
                ans += len(set(s[l + 1 : r]))
        return ans
```

#### Java

```java
class Solution {
    public int countPalindromicSubsequence(String s) {
        int ans = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            int l = s.indexOf(c), r = s.lastIndexOf(c);
            int mask = 0;
            for (int i = l + 1; i < r; ++i) {
                int j = s.charAt(i) - 'a';
                if ((mask >> j & 1) == 0) {
                    mask |= 1 << j;
                    ++ans;
                }
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
    int countPalindromicSubsequence(string s) {
        int ans = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            int l = s.find_first_of(c), r = s.find_last_of(c);
            int mask = 0;
            for (int i = l + 1; i < r; ++i) {
                int j = s[i] - 'a';
                if (mask >> j & 1 ^ 1) {
                    mask |= 1 << j;
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countPalindromicSubsequence(s string) (ans int) {
	for c := 'a'; c <= 'z'; c++ {
		l, r := strings.Index(s, string(c)), strings.LastIndex(s, string(c))
		mask := 0
		for i := l + 1; i < r; i++ {
			j := int(s[i] - 'a')
			if mask>>j&1 == 0 {
				mask |= 1 << j
				ans++
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function countPalindromicSubsequence(s: string): number {
    let ans = 0;
    const a = 'a'.charCodeAt(0);
    for (let ch = 0; ch < 26; ++ch) {
        const c = String.fromCharCode(ch + a);
        const l = s.indexOf(c);
        const r = s.lastIndexOf(c);
        let mask = 0;
        for (let i = l + 1; i < r; ++i) {
            const j = s.charCodeAt(i) - a;
            if (((mask >> j) & 1) ^ 1) {
                mask |= 1 << j;
                ++ans;
            }
        }
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {number}
 */
var countPalindromicSubsequence = function (s) {
    let ans = 0;
    const a = 'a'.charCodeAt(0);
    for (let ch = 0; ch < 26; ++ch) {
        const c = String.fromCharCode(ch + a);
        const l = s.indexOf(c);
        const r = s.lastIndexOf(c);
        let mask = 0;
        for (let i = l + 1; i < r; ++i) {
            const j = s.charCodeAt(i) - a;
            if (((mask >> j) & 1) ^ 1) {
                mask |= 1 << j;
                ++ans;
            }
        }
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int CountPalindromicSubsequence(string s) {
        int ans = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            int l = s.IndexOf(c), r = s.LastIndexOf(c);
            int mask = 0;
            for (int i = l + 1; i < r; ++i) {
                int j = s[i] - 'a';
                if ((mask >> j & 1) == 0) {
                    mask |= 1 << j;
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
