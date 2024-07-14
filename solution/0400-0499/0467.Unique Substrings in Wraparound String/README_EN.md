---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0467.Unique%20Substrings%20in%20Wraparound%20String/README_EN.md
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [467. Unique Substrings in Wraparound String](https://leetcode.com/problems/unique-substrings-in-wraparound-string)

[中文文档](/solution/0400-0499/0467.Unique%20Substrings%20in%20Wraparound%20String/README.md)

## Description

<!-- description:start -->

<p>We define the string <code>base</code> to be the infinite wraparound string of <code>&quot;abcdefghijklmnopqrstuvwxyz&quot;</code>, so <code>base</code> will look like this:</p>

<ul>
	<li><code>&quot;...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....&quot;</code>.</li>
</ul>

<p>Given a string <code>s</code>, return <em>the number of <strong>unique non-empty substrings</strong> of </em><code>s</code><em> are present in </em><code>base</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> Only the substring &quot;a&quot; of s is in base.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cac&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are two substrings (&quot;a&quot;, &quot;c&quot;) of s in base.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;zab&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> There are six substrings (&quot;z&quot;, &quot;a&quot;, &quot;b&quot;, &quot;za&quot;, &quot;ab&quot;, and &quot;zab&quot;) of s in base.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We can define an array $f$ of length $26$, where $f[i]$ represents the length of the longest consecutive substring ending with the $i$th character. The answer is the sum of all elements in $f$.

We define a variable $k$ to represent the length of the longest consecutive substring ending with the current character. We iterate through the string $s$. For each character $c$, if the difference between $c$ and the previous character $s[i - 1]$ is $1$, then we increment $k$ by $1$, otherwise, we reset $k$ to $1$. Then we update $f[c]$ to be the larger value of $f[c]$ and $k$.

Finally, we return the sum of all elements in $f$.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(|\Sigma|)$, where $\Sigma$ is the character set, in this case, the set of lowercase letters.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findSubstringInWraproundString(self, s: str) -> int:
        f = defaultdict(int)
        k = 0
        for i, c in enumerate(s):
            if i and (ord(c) - ord(s[i - 1])) % 26 == 1:
                k += 1
            else:
                k = 1
            f[c] = max(f[c], k)
        return sum(f.values())
```

#### Java

```java
class Solution {
    public int findSubstringInWraproundString(String s) {
        int[] f = new int[26];
        int n = s.length();
        for (int i = 0, k = 0; i < n; ++i) {
            if (i > 0 && (s.charAt(i) - s.charAt(i - 1) + 26) % 26 == 1) {
                ++k;
            } else {
                k = 1;
            }
            f[s.charAt(i) - 'a'] = Math.max(f[s.charAt(i) - 'a'], k);
        }
        return Arrays.stream(f).sum();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findSubstringInWraproundString(string s) {
        int f[26]{};
        int n = s.length();
        for (int i = 0, k = 0; i < n; ++i) {
            if (i && (s[i] - s[i - 1] + 26) % 26 == 1) {
                ++k;
            } else {
                k = 1;
            }
            f[s[i] - 'a'] = max(f[s[i] - 'a'], k);
        }
        return accumulate(begin(f), end(f), 0);
    }
};
```

#### Go

```go
func findSubstringInWraproundString(s string) (ans int) {
	f := [26]int{}
	k := 0
	for i := range s {
		if i > 0 && (s[i]-s[i-1]+26)%26 == 1 {
			k++
		} else {
			k = 1
		}
		f[s[i]-'a'] = max(f[s[i]-'a'], k)
	}
	for _, x := range f {
		ans += x
	}
	return
}
```

#### TypeScript

```ts
function findSubstringInWraproundString(s: string): number {
    const idx = (c: string): number => c.charCodeAt(0) - 97;
    const f: number[] = Array(26).fill(0);
    const n = s.length;
    for (let i = 0, k = 0; i < n; ++i) {
        const j = idx(s[i]);
        if (i && (j - idx(s[i - 1]) + 26) % 26 === 1) {
            ++k;
        } else {
            k = 1;
        }
        f[j] = Math.max(f[j], k);
    }
    return f.reduce((acc, cur) => acc + cur, 0);
}
```

#### Rust

```rust
impl Solution {
    pub fn find_substring_in_wrapround_string(s: String) -> i32 {
        let idx = |c: u8| -> usize { (c - b'a') as usize };
        let mut f = vec![0; 26];
        let n = s.len();
        let s = s.as_bytes();
        let mut k = 0;
        for i in 0..n {
            let j = idx(s[i]);
            if i > 0 && ((j as i32) - (idx(s[i - 1]) as i32) + 26) % 26 == 1 {
                k += 1;
            } else {
                k = 1;
            }
            f[j] = f[j].max(k);
        }

        f.iter().sum()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
