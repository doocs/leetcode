---
comments: true
difficulty: Medium
tags:
    - Hash Table
    - String
    - Sliding Window
---

<!-- problem:start -->

# [340. Longest Substring with At Most K Distinct Characters 🔒](https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters)

[中文文档](/solution/0300-0399/0340.Longest%20Substring%20with%20At%20Most%20K%20Distinct%20Characters/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code> and an integer <code>k</code>, return <em>the length of the longest </em><span data-keyword="substring-nonempty"><em>substring</em></span><em> of</em> <code>s</code> <em>that contains at most</em> <code>k</code> <em><strong>distinct</strong> characters</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;eceba&quot;, k = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> The substring is &quot;ece&quot; with length 3.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aa&quot;, k = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> The substring is &quot;aa&quot; with length 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= k &lt;= 50</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sliding Window + Hash Table

<!-- thinking:start -->

> **Thinking**
>
> Enumerating each starting position, then extending to the right while counting distinct characters, takes $O(n^2)$ time. To avoid repeated enumeration, we can maintain character counts in a sliding window using a hash table.
>
> Since only the maximum length is required, we only try to increase the best length found so far by $1$. After adding the right character, if the number of distinct characters exceeds $k$, the length cannot grow in this iteration, so we move the left boundary once to preserve the previous length. The window may temporarily be invalid, but its length always equals the optimum for the processed prefix.

<!-- thinking:end -->

We maintain a sliding window whose length never decreases, with a hash table $\textit{cnt}$ to record the occurrence count of each character within the window, and $l$ to denote the left boundary of the window.

Iterate through the string, incrementing the count of the character at the right boundary by $1$. If the number of distinct characters in the hash table exceeds $k$, decrement the count of the character at the left boundary, delete it when its count becomes $0$, and move $l$ one position to the right. This uses a single `if`, moving the left boundary at most once per iteration, so the window length either increases by $1$ or stays the same, even though the window may still contain more than $k$ distinct characters.

We prove that after each iteration, the window length equals the longest valid substring length in the processed prefix. Suppose both the previous window length and the previous optimum are $L$. After adding one character, the optimum is at most $L+1$, since removing the last character from any valid substring ending at the new character leaves a valid substring. The current window is the unique substring of length $L+1$ ending at the new character: if it is valid, both the optimum and the window length increase to $L+1$; otherwise, the optimum remains $L$, and moving the left boundary once also keeps the window length at $L$. By induction from the empty prefix, the final $n-l$ is the answer, where $n$ is the string length.

For example, with the string `eceba` and $k=2$, the windows after each iteration are `e`, `ec`, `ece`, `ceb`, and `eba`, with lengths $1,2,3,3,3$. Although the last two windows are invalid, their length still records the length $3$ of the valid substring `ece`. When $k=0$, each added character is immediately removed, giving an answer of $0$.

Assuming hash table operations take $O(1)$ time on average, the time complexity is $O(n)$. The hash table uses $O(\min(n, |\Sigma|))$ space, where $\Sigma$ denotes the character set; for a fixed character set, it uses $O(1)$ space.

The space cannot generally be bounded by $O(k)$: for example, with $k=1$, first read $m$ copies of one character, followed by $m$ distinct characters different from the first one. The final window contains $m$ distinct characters. In addition, `toCharArray()` in the Java implementation requires $O(n)$ extra space.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lengthOfLongestSubstringKDistinct(self, s: str, k: int) -> int:
        l = 0
        cnt = Counter()
        for c in s:
            cnt[c] += 1
            if len(cnt) > k:
                cnt[s[l]] -= 1
                if cnt[s[l]] == 0:
                    del cnt[s[l]]
                l += 1
        return len(s) - l
```

#### Java

```java
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> cnt = new HashMap<>();
        int l = 0;
        char[] cs = s.toCharArray();
        for (char c : cs) {
            cnt.merge(c, 1, Integer::sum);
            if (cnt.size() > k) {
                if (cnt.merge(cs[l], -1, Integer::sum) == 0) {
                    cnt.remove(cs[l]);
                }
                ++l;
            }
        }
        return cs.length - l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int lengthOfLongestSubstringKDistinct(string s, int k) {
        unordered_map<char, int> cnt;
        int l = 0;
        for (char& c : s) {
            ++cnt[c];
            if (cnt.size() > k) {
                if (--cnt[s[l]] == 0) {
                    cnt.erase(s[l]);
                }
                ++l;
            }
        }
        return s.size() - l;
    }
};
```

#### Go

```go
func lengthOfLongestSubstringKDistinct(s string, k int) int {
	cnt := map[byte]int{}
	l := 0
	for _, c := range s {
		cnt[byte(c)]++
		if len(cnt) > k {
			cnt[s[l]]--
			if cnt[s[l]] == 0 {
				delete(cnt, s[l])
			}
			l++
		}
	}
	return len(s) - l
}
```

#### TypeScript

```ts
function lengthOfLongestSubstringKDistinct(s: string, k: number): number {
    const cnt: Map<string, number> = new Map();
    let l = 0;
    for (const c of s) {
        cnt.set(c, (cnt.get(c) ?? 0) + 1);
        if (cnt.size > k) {
            cnt.set(s[l], cnt.get(s[l])! - 1);
            if (cnt.get(s[l]) === 0) {
                cnt.delete(s[l]);
            }
            l++;
        }
    }
    return s.length - l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
