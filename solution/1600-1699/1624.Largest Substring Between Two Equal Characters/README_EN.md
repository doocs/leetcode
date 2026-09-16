---
comments: true
difficulty: Easy
rating: 1281
source: Weekly Contest 211 Q1
tags:
    - Hash Table
    - String
---

<!-- problem:start -->

# [1624. Largest Substring Between Two Equal Characters](https://leetcode.com/problems/largest-substring-between-two-equal-characters)

[中文文档](/solution/1600-1699/1624.Largest%20Substring%20Between%20Two%20Equal%20Characters/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code>, return <em>the length of the longest substring between two equal characters, excluding the two characters.</em> If there is no such substring return <code>-1</code>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aa&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> The optimal substring here is an empty substring between the two <code>&#39;a&#39;s</code>.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abca&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The optimal substring here is &quot;bc&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cbzxy&quot;
<strong>Output:</strong> -1
<strong>Explanation:</strong> There are no characters that appear twice in s.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 300</code></li>
	<li><code>s</code> contains only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Array

<!-- thinking:start -->

> **Thinking**
>
> The length between two equal letters is the gap between that letter's first occurrence and a later one. The string is short, but keeping only the first index of each letter already yields a linear solution.
>
> On seeing a character again, update the answer with $i - d[j] - 1$ and do not overwrite the first index, so the span stays maximal.
>
> Because $s$ contains only lowercase letters, a length-$26$ array is enough; if nothing appears twice, the answer stays $-1$.

<!-- thinking:end -->

Since $s$ contains only lowercase English letters, we can use an array $d$ of length $26$ to store the first index of each character, initially filled with $-1$.

Traverse $s$. For the character $c$ at index $i$, let $j$ be the offset of $c$ from `a`. If $d[j] = -1$, this is the first time we see $c$, so set $d[j] = i$; otherwise update the answer with $i - d[j] - 1$, i.e. $ans = \max(ans, i - d[j] - 1)$.

The time complexity is $O(n)$, and the space complexity is $O(C)$, where $n$ is the length of $s$ and $C = 26$ is the size of the alphabet.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxLengthBetweenEqualCharacters(self, s: str) -> int:
        d = [-1] * 26
        ans = -1
        for i, c in enumerate(s):
            j = ord(c) - ord("a")
            if d[j] == -1:
                d[j] = i
            else:
                ans = max(ans, i - d[j] - 1)
        return ans
```

#### Java

```java
class Solution {
    public int maxLengthBetweenEqualCharacters(String s) {
        int[] d = new int[26];
        Arrays.fill(d, -1);
        int ans = -1;
        for (int i = 0; i < s.length(); ++i) {
            int j = s.charAt(i) - 'a';
            if (d[j] == -1) {
                d[j] = i;
            } else {
                ans = Math.max(ans, i - d[j] - 1);
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
    int maxLengthBetweenEqualCharacters(string s) {
        vector<int> d(26, -1);
        int ans = -1;
        for (int i = 0; i < s.size(); ++i) {
            int j = s[i] - 'a';
            if (d[j] == -1) {
                d[j] = i;
            } else {
                ans = max(ans, i - d[j] - 1);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxLengthBetweenEqualCharacters(s string) int {
	d := make([]int, 26)
	for i := range d {
		d[i] = -1
	}
	ans := -1
	for i := range s {
		j := int(s[i] - 'a')
		if d[j] == -1 {
			d[j] = i
		} else {
			ans = max(ans, i-d[j]-1)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxLengthBetweenEqualCharacters(s: string): number {
    const d = Array(26).fill(-1);
    let ans = -1;
    for (let i = 0; i < s.length; ++i) {
        const j = s.charCodeAt(i) - 97;
        if (d[j] === -1) {
            d[j] = i;
        } else {
            ans = Math.max(ans, i - d[j] - 1);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_length_between_equal_characters(s: String) -> i32 {
        let s = s.as_bytes();
        let mut d = [-1; 26];
        let mut ans = -1;
        for i in 0..s.len() {
            let j = (s[i] - b'a') as usize;
            if d[j] == -1 {
                d[j] = i as i32;
            } else {
                ans = ans.max(i as i32 - d[j] - 1);
            }
        }
        ans
    }
}
```

#### C

```c
#define max(a, b) (((a) > (b)) ? (a) : (b))

int maxLengthBetweenEqualCharacters(char* s) {
    int d[26];
    memset(d, -1, sizeof(d));
    int ans = -1;
    for (int i = 0; s[i]; ++i) {
        int j = s[i] - 'a';
        if (d[j] == -1) {
            d[j] = i;
        } else {
            ans = max(ans, i - d[j] - 1);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
