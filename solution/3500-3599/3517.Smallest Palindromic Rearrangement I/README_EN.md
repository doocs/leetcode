---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3517.Smallest%20Palindromic%20Rearrangement%20I/README_EN.md
rating: 1357
source: Weekly Contest 445 Q2
tags:
    - String
    - Counting Sort
    - Sorting
---

<!-- problem:start -->

# [3517. Smallest Palindromic Rearrangement I](https://leetcode.com/problems/smallest-palindromic-rearrangement-i)

[中文文档](/solution/3500-3599/3517.Smallest%20Palindromic%20Rearrangement%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong><span data-keyword="palindrome-string">palindromic</span></strong> string <code>s</code>.</p>

<p>Return the <strong><span data-keyword="lexicographically-smaller-string">lexicographically smallest</span></strong> palindromic <span data-keyword="permutation-string">permutation</span> of <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;z&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;z&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>A string of only one character is already the lexicographically smallest palindrome.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;babab&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;abbba&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Rearranging <code>&quot;babab&quot;</code> &rarr; <code>&quot;abbba&quot;</code> gives the smallest lexicographic palindrome.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;daccad&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;acddca&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Rearranging <code>&quot;daccad&quot;</code> &rarr; <code>&quot;acddca&quot;</code> gives the smallest lexicographic palindrome.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>s</code> is guaranteed to be palindromic.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We first count the occurrence of each character in the string and record it in a hash table or array $\textit{cnt}$. Since the string is a palindrome, the count of each character is either even, or there is exactly one character with an odd count.

Next, starting from the lexicographically smallest character, we sequentially add half of each character's count to the first half of the result string $\textit{t}$. If a character appears an odd number of times, we record it as the middle character $\textit{ch}$. Finally, we concatenate $\textit{t}$, $\textit{ch}$, and the reverse of $\textit{t}$ to obtain the final lexicographically smallest palindromic rearrangement.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(|\Sigma|)$, where $|\Sigma|$ is the size of the character set, which is $26$ in this problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestPalindrome(self, s: str) -> str:
        cnt = Counter(s)
        t = []
        ch = ""
        for c in ascii_lowercase:
            v = cnt[c] // 2
            t.append(c * v)
            cnt[c] -= v * 2
            if cnt[c] == 1:
                ch = c
        ans = "".join(t)
        ans = ans + ch + ans[::-1]
        return ans
```

#### Java

```java
class Solution {
    public String smallestPalindrome(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        StringBuilder t = new StringBuilder();
        String ch = "";

        for (char c = 'a'; c <= 'z'; c++) {
            int idx = c - 'a';
            int v = cnt[idx] / 2;
            if (v > 0) {
                t.append(String.valueOf(c).repeat(v));
            }
            cnt[idx] -= v * 2;
            if (cnt[idx] == 1) {
                ch = String.valueOf(c);
            }
        }

        String ans = t.toString();
        ans = ans + ch + new StringBuilder(ans).reverse();
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string smallestPalindrome(string s) {
        vector<int> cnt(26);
        for (char c : s) {
            cnt[c - 'a']++;
        }
        string t = "";
        string ch = "";
        for (char c = 'a'; c <= 'z'; ++c) {
            int v = cnt[c - 'a'] / 2;
            if (v > 0) {
                t.append(v, c);
            }
            cnt[c - 'a'] -= v * 2;
            if (cnt[c - 'a'] == 1) {
                ch = string(1, c);
            }
        }
        string ans = t;
        ans += ch;
        string rev = t;
        reverse(rev.begin(), rev.end());
        ans += rev;
        return ans;
    }
};
```

#### Go

```go
func smallestPalindrome(s string) string {
	cnt := make([]int, 26)
	for i := 0; i < len(s); i++ {
		cnt[s[i]-'a']++
	}

	t := make([]byte, 0, len(s)/2)
	var ch byte
	for c := byte('a'); c <= 'z'; c++ {
		v := cnt[c-'a'] / 2
		for i := 0; i < v; i++ {
			t = append(t, c)
		}
		cnt[c-'a'] -= v * 2
		if cnt[c-'a'] == 1 {
			ch = c
		}
	}

	totalLen := len(t) * 2
	if ch != 0 {
		totalLen++
	}
	var sb strings.Builder
	sb.Grow(totalLen)

	sb.Write(t)
	if ch != 0 {
		sb.WriteByte(ch)
	}
	for i := len(t) - 1; i >= 0; i-- {
		sb.WriteByte(t[i])
	}
	return sb.String()
}
```

#### TypeScript

```ts
function smallestPalindrome(s: string): string {
    const ascii_lowercase = 'abcdefghijklmnopqrstuvwxyz';
    const cnt = new Array<number>(26).fill(0);
    for (const chKey of s) {
        cnt[chKey.charCodeAt(0) - 97]++;
    }

    const t: string[] = [];
    let ch = '';
    for (let i = 0; i < 26; i++) {
        const c = ascii_lowercase[i];
        const v = Math.floor(cnt[i] / 2);
        t.push(c.repeat(v));
        cnt[i] -= v * 2;
        if (cnt[i] === 1) {
            ch = c;
        }
    }

    let ans = t.join('');
    ans = ans + ch + ans.split('').reverse().join('');
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
