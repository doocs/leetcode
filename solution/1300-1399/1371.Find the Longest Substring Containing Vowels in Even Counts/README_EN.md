---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1371.Find%20the%20Longest%20Substring%20Containing%20Vowels%20in%20Even%20Counts/README_EN.md
rating: 2040
source: Biweekly Contest 21 Q2
tags:
    - Bit Manipulation
    - Hash Table
    - String
    - Prefix Sum
---

<!-- problem:start -->

# [1371. Find the Longest Substring Containing Vowels in Even Counts](https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts)

[中文文档](/solution/1300-1399/1371.Find%20the%20Longest%20Substring%20Containing%20Vowels%20in%20Even%20Counts/README.md)

## Description

<!-- description:start -->

<p>Given the string <code>s</code>, return the size of the longest substring containing each vowel an even number of times. That is, &#39;a&#39;, &#39;e&#39;, &#39;i&#39;, &#39;o&#39;, and &#39;u&#39; must appear an even number of times.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;eleetminicoworoep&quot;
<strong>Output:</strong> 13
<strong>Explanation: </strong>The longest substring is &quot;leetminicowor&quot; which contains two each of the vowels: <strong>e</strong>, <strong>i</strong> and <strong>o</strong> and zero of the vowels: <strong>a</strong> and <strong>u</strong>.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leetcodeisgreat&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> The longest substring is &quot;leetc&quot; which contains two e&#39;s.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bcbcbc&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> In this case, the given string &quot;bcbcbc&quot; is the longest because all vowels: <strong>a</strong>, <strong>e</strong>, <strong>i</strong>, <strong>o</strong> and <strong>u</strong> appear zero times.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 x 10^5</code></li>
	<li><code>s</code>&nbsp;contains only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix XOR + Array or Hash Table

According to the problem description, if we use a number to represent the parity of the occurrences of each vowel in a prefix of the string $\textit{s}$, then when two prefixes have the same number, the substring between these two prefixes is a valid substring.

We can use the lower five bits of a binary number to represent the parity of the five vowels, where the $i$-th bit being $1$ means the vowel appears an odd number of times in the substring, and $0$ means it appears an even number of times.

We use $\textit{mask}$ to represent this binary number and use an array or hash table $\textit{d}$ to record the first occurrence of each $\textit{mask}$. Initially, we set $\textit{d}[0] = -1$, indicating that the start position of the empty string is $-1$.

We traverse the string $\textit{s}$, and if we encounter a vowel, we toggle the corresponding bit in $\textit{mask}$. Next, we check if $\textit{mask}$ has appeared before. If it has, we have found a valid substring, and its length is the current position minus the last occurrence of $\textit{mask}$. Otherwise, we store the current position of $\textit{mask}$ in $\textit{d}$.

After traversing the string, we will have found the longest valid substring.

The time complexity is $O(n)$, where $n$ is the length of the string $\textit{s}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findTheLongestSubstring(self, s: str) -> int:
        d = {0: -1}
        ans = mask = 0
        for i, c in enumerate(s):
            if c in "aeiou":
                mask ^= 1 << (ord(c) - ord("a"))
            if mask in d:
                j = d[mask]
                ans = max(ans, i - j)
            else:
                d[mask] = i
        return ans
```

#### Java

```java
class Solution {
    public int findTheLongestSubstring(String s) {
        String vowels = "aeiou";
        int[] d = new int[32];
        Arrays.fill(d, 1 << 29);
        d[0] = 0;
        int ans = 0, mask = 0;
        for (int i = 1; i <= s.length(); ++i) {
            char c = s.charAt(i - 1);
            for (int j = 0; j < 5; ++j) {
                if (c == vowels.charAt(j)) {
                    mask ^= 1 << j;
                    break;
                }
            }
            ans = Math.max(ans, i - d[mask]);
            d[mask] = Math.min(d[mask], i);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findTheLongestSubstring(string s) {
        string vowels = "aeiou";
        vector<int> d(32, INT_MAX);
        d[0] = 0;
        int ans = 0, mask = 0;
        for (int i = 1; i <= s.length(); ++i) {
            char c = s[i - 1];
            for (int j = 0; j < 5; ++j) {
                if (c == vowels[j]) {
                    mask ^= 1 << j;
                    break;
                }
            }
            ans = max(ans, i - d[mask]);
            d[mask] = min(d[mask], i);
        }
        return ans;
    }
};
```

#### Go

```go
func findTheLongestSubstring(s string) (ans int) {
    vowels := "aeiou"
    d := [32]int{}
    for i := range d {
        d[i] = 1 << 29
    }
    d[0] = 0
    mask := 0
    for i := 1; i <= len(s); i++ {
        c := s[i-1]
        for j := 0; j < 5; j++ {
            if c == vowels[j] {
                mask ^= 1 << j
                break
            }
        }
        ans = max(ans, i-d[mask])
        d[mask] = min(d[mask], i)
    }
    return
}
```

#### TypeScript

```ts
function findTheLongestSubstring(s: string): number {
    const vowels = 'aeiou';
    const d: number[] = Array(32).fill(1 << 29);
    d[0] = 0;
    let [ans, mask] = [0, 0];
    for (let i = 1; i <= s.length; i++) {
        const c = s[i - 1];
        for (let j = 0; j < 5; j++) {
            if (c === vowels[j]) {
                mask ^= 1 << j;
                break;
            }
        }
        ans = Math.max(ans, i - d[mask]);
        d[mask] = Math.min(d[mask], i);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
