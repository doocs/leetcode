---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1347.Minimum%20Number%20of%20Steps%20to%20Make%20Two%20Strings%20Anagram/README_EN.md
rating: 1330
source: Weekly Contest 175 Q2
tags:
    - Hash Table
    - String
    - Counting
---

<!-- problem:start -->

# [1347. Minimum Number of Steps to Make Two Strings Anagram](https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram)

[中文文档](/solution/1300-1399/1347.Minimum%20Number%20of%20Steps%20to%20Make%20Two%20Strings%20Anagram/README.md)

## Description

<!-- description:start -->

<p>You are given two strings of the same length <code>s</code> and <code>t</code>. In one step you can choose <strong>any character</strong> of <code>t</code> and replace it with <strong>another character</strong>.</p>

<p>Return <em>the minimum number of steps</em> to make <code>t</code> an anagram of <code>s</code>.</p>

<p>An <strong>Anagram</strong> of a string is a string that contains the same characters with a different (or the same) ordering.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bab&quot;, t = &quot;aba&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> Replace the first &#39;a&#39; in t with b, t = &quot;bba&quot; which is anagram of s.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leetcode&quot;, t = &quot;practice&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> Replace &#39;p&#39;, &#39;r&#39;, &#39;a&#39;, &#39;i&#39; and &#39;c&#39; from t with proper characters to make t anagram of s.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;anagram&quot;, t = &quot;mangaar&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> &quot;anagram&quot; and &quot;mangaar&quot; are anagrams.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s.length == t.length</code></li>
	<li><code>s</code> and <code>t</code> consist of lowercase English letters only.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We can use a hash table or an array $\textit{cnt}$ to count the occurrences of each character in the string $\textit{s}$. Then, we traverse the string $\textit{t}$. For each character, we decrement its count in $\textit{cnt}$. If the decremented value is less than $0$, it means that this character appears more times in the string $\textit{t}$ than in the string $\textit{s}$. In this case, we need to replace this character and increment the answer by one.

After the traversal, we return the answer.

The time complexity is $O(m + n)$, and the space complexity is $O(|\Sigma|)$, where $m$ and $n$ are the lengths of the strings $\textit{s}$ and $\textit{t}$, respectively, and $|\Sigma|$ is the size of the character set. In this problem, the character set consists of lowercase letters, so $|\Sigma| = 26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSteps(self, s: str, t: str) -> int:
        cnt = Counter(s)
        ans = 0
        for c in t:
            cnt[c] -= 1
            ans += cnt[c] < 0
        return ans
```

#### Java

```java
class Solution {
    public int minSteps(String s, String t) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        int ans = 0;
        for (char c : t.toCharArray()) {
            if (--cnt[c - 'a'] < 0) {
                ans++;
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
    int minSteps(string s, string t) {
        int cnt[26]{};
        for (char c : s) {
            ++cnt[c - 'a'];
        }
        int ans = 0;
        for (char c : t) {
            if (--cnt[c - 'a'] < 0) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minSteps(s string, t string) (ans int) {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	for _, c := range t {
		cnt[c-'a']--
		if cnt[c-'a'] < 0 {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function minSteps(s: string, t: string): number {
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 97];
    }
    let ans = 0;
    for (const c of t) {
        if (--cnt[c.charCodeAt(0) - 97] < 0) {
            ++ans;
        }
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @param {string} t
 * @return {number}
 */
var minSteps = function (s, t) {
    const cnt = Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 97];
    }
    let ans = 0;
    for (const c of t) {
        if (--cnt[c.charCodeAt(0) - 97] < 0) {
            ++ans;
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
