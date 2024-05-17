---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2947.Count%20Beautiful%20Substrings%20I/README_EN.md
rating: 1450
source: Weekly Contest 373 Q2
tags:
    - Hash Table
    - Math
    - String
    - Enumeration
    - Number Theory
    - Prefix Sum
---

<!-- problem:start -->

# [2947. Count Beautiful Substrings I](https://leetcode.com/problems/count-beautiful-substrings-i)

[中文文档](/solution/2900-2999/2947.Count%20Beautiful%20Substrings%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and a positive integer <code>k</code>.</p>

<p>Let <code>vowels</code> and <code>consonants</code> be the number of vowels and consonants in a string.</p>

<p>A string is <strong>beautiful</strong> if:</p>

<ul>
	<li><code>vowels == consonants</code>.</li>
	<li><code>(vowels * consonants) % k == 0</code>, in other terms the multiplication of <code>vowels</code> and <code>consonants</code> is divisible by <code>k</code>.</li>
</ul>

<p>Return <em>the number of <strong>non-empty beautiful substrings</strong> in the given string</em> <code>s</code>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters in a string.</p>

<p><strong>Vowel letters</strong> in English are <code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, and <code>&#39;u&#39;</code>.</p>

<p><strong>Consonant letters</strong> in English are every letter except vowels.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;baeyh&quot;, k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 2 beautiful substrings in the given string.
- Substring &quot;b<u>aeyh</u>&quot;, vowels = 2 ([&quot;a&quot;,e&quot;]), consonants = 2 ([&quot;y&quot;,&quot;h&quot;]).
You can see that string &quot;aeyh&quot; is beautiful as vowels == consonants and vowels * consonants % k == 0.
- Substring &quot;<u>baey</u>h&quot;, vowels = 2 ([&quot;a&quot;,e&quot;]), consonants = 2 ([&quot;b&quot;,&quot;y&quot;]). 
You can see that string &quot;baey&quot; is beautiful as vowels == consonants and vowels * consonants % k == 0.
It can be shown that there are only 2 beautiful substrings in the given string.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abba&quot;, k = 1
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 3 beautiful substrings in the given string.
- Substring &quot;<u>ab</u>ba&quot;, vowels = 1 ([&quot;a&quot;]), consonants = 1 ([&quot;b&quot;]). 
- Substring &quot;ab<u>ba</u>&quot;, vowels = 1 ([&quot;a&quot;]), consonants = 1 ([&quot;b&quot;]).
- Substring &quot;<u>abba</u>&quot;, vowels = 2 ([&quot;a&quot;,&quot;a&quot;]), consonants = 2 ([&quot;b&quot;,&quot;b&quot;]).
It can be shown that there are only 3 beautiful substrings in the given string.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bcdf&quot;, k = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no beautiful substrings in the given string.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= 1000</code></li>
	<li><code>s</code> consists of only English lowercase letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def beautifulSubstrings(self, s: str, k: int) -> int:
        n = len(s)
        vs = set("aeiou")
        ans = 0
        for i in range(n):
            vowels = 0
            for j in range(i, n):
                vowels += s[j] in vs
                consonants = j - i + 1 - vowels
                if vowels == consonants and vowels * consonants % k == 0:
                    ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int beautifulSubstrings(String s, int k) {
        int n = s.length();
        int[] vs = new int[26];
        for (char c : "aeiou".toCharArray()) {
            vs[c - 'a'] = 1;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int vowels = 0;
            for (int j = i; j < n; ++j) {
                vowels += vs[s.charAt(j) - 'a'];
                int consonants = j - i + 1 - vowels;
                if (vowels == consonants && vowels * consonants % k == 0) {
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
    int beautifulSubstrings(string s, int k) {
        int n = s.size();
        int vs[26]{};
        string t = "aeiou";
        for (char c : t) {
            vs[c - 'a'] = 1;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int vowels = 0;
            for (int j = i; j < n; ++j) {
                vowels += vs[s[j] - 'a'];
                int consonants = j - i + 1 - vowels;
                if (vowels == consonants && vowels * consonants % k == 0) {
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
func beautifulSubstrings(s string, k int) (ans int) {
	n := len(s)
	vs := [26]int{}
	for _, c := range "aeiou" {
		vs[c-'a'] = 1
	}
	for i := 0; i < n; i++ {
		vowels := 0
		for j := i; j < n; j++ {
			vowels += vs[s[j]-'a']
			consonants := j - i + 1 - vowels
			if vowels == consonants && vowels*consonants%k == 0 {
				ans++
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function beautifulSubstrings(s: string, k: number): number {
    const n = s.length;
    const vs: number[] = Array(26).fill(0);
    for (const c of 'aeiou') {
        vs[c.charCodeAt(0) - 'a'.charCodeAt(0)] = 1;
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        let vowels = 0;
        for (let j = i; j < n; ++j) {
            vowels += vs[s.charCodeAt(j) - 'a'.charCodeAt(0)];
            const consonants = j - i + 1 - vowels;
            if (vowels === consonants && (vowels * consonants) % k === 0) {
                ++ans;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
