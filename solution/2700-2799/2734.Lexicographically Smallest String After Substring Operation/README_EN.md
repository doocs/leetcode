---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2734.Lexicographically%20Smallest%20String%20After%20Substring%20Operation/README_EN.md
rating: 1405
source: Weekly Contest 349 Q2
tags:
    - Greedy
    - String
---

<!-- problem:start -->

# [2734. Lexicographically Smallest String After Substring Operation](https://leetcode.com/problems/lexicographically-smallest-string-after-substring-operation)

[中文文档](/solution/2700-2799/2734.Lexicographically%20Smallest%20String%20After%20Substring%20Operation/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of only lowercase English letters. In one operation, you can do the following:</p>

<ul>
	<li>Select any non-empty substring of <code>s</code>, possibly the entire string, then replace each one of its characters with the previous character of the English alphabet. For example, &#39;b&#39; is converted to &#39;a&#39;, and &#39;a&#39; is converted to &#39;z&#39;.</li>
</ul>

<p>Return <em>the <strong>lexicographically smallest</strong> string you can obtain after performing the above operation <strong>exactly once</strong>.</em></p>

<p>A <strong>substring</strong> is a contiguous sequence of characters in a string.</p>
A string <code>x</code> is <strong>lexicographically smaller</strong> than a string <code>y</code> of the same length if <code>x[i]</code> comes before <code>y[i]</code> in alphabetic order for the first position <code>i</code> such that <code>x[i] != y[i]</code>.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cbabc&quot;
<strong>Output:</strong> &quot;baabc&quot;
<strong>Explanation:</strong> We apply the operation on the substring starting at index 0, and ending at index 1 inclusive. 
It can be proven that the resulting string is the lexicographically smallest. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;acbbc&quot;
<strong>Output:</strong> &quot;abaab&quot;
<strong>Explanation:</strong> We apply the operation on the substring starting at index 1, and ending at index 4 inclusive. 
It can be proven that the resulting string is the lexicographically smallest. 
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leetcode&quot;
<strong>Output:</strong> &quot;kddsbncd&quot;
<strong>Explanation:</strong> We apply the operation on the entire string. 
It can be proven that the resulting string is the lexicographically smallest. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestString(self, s: str) -> str:
        n = len(s)
        i = 0
        while i < n and s[i] == "a":
            i += 1
        if i == n:
            return s[:-1] + "z"
        j = i
        while j < n and s[j] != "a":
            j += 1
        return s[:i] + "".join(chr(ord(c) - 1) for c in s[i:j]) + s[j:]
```

#### Java

```java
class Solution {
    public String smallestString(String s) {
        int n = s.length();
        int i = 0;
        while (i < n && s.charAt(i) == 'a') {
            ++i;
        }
        if (i == n) {
            return s.substring(0, n - 1) + "z";
        }
        int j = i;
        char[] cs = s.toCharArray();
        while (j < n && cs[j] != 'a') {
            cs[j] = (char) (cs[j] - 1);
            ++j;
        }
        return String.valueOf(cs);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string smallestString(string s) {
        int n = s.size();
        int i = 0;
        while (i < n && s[i] == 'a') {
            ++i;
        }
        if (i == n) {
            s[n - 1] = 'z';
            return s;
        }
        int j = i;
        while (j < n && s[j] != 'a') {
            s[j] = s[j] - 1;
            ++j;
        }
        return s;
    }
};
```

#### Go

```go
func smallestString(s string) string {
	n := len(s)
	i := 0
	for i < n && s[i] == 'a' {
		i++
	}
	cs := []byte(s)
	if i == n {
		cs[n-1] = 'z'
		return string(cs)
	}
	j := i
	for j < n && cs[j] != 'a' {
		cs[j] = cs[j] - 1
		j++
	}
	return string(cs)
}
```

#### TypeScript

```ts
function smallestString(s: string): string {
    const cs: string[] = s.split('');
    const n: number = cs.length;
    let i: number = 0;
    while (i < n && cs[i] === 'a') {
        i++;
    }

    if (i === n) {
        cs[n - 1] = 'z';
        return cs.join('');
    }

    let j: number = i;
    while (j < n && cs[j] !== 'a') {
        const c: number = cs[j].charCodeAt(0);
        cs[j] = String.fromCharCode(c - 1);
        j++;
    }

    return cs.join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn smallest_string(s: String) -> String {
        let mut cs: Vec<char> = s.chars().collect();
        let n = cs.len();
        let mut i = 0;

        while i < n && cs[i] == 'a' {
            i += 1;
        }

        if i == n {
            cs[n - 1] = 'z';
            return cs.into_iter().collect();
        }

        let mut j = i;
        while j < n && cs[j] != 'a' {
            cs[j] = ((cs[j] as u8) - 1) as char;
            j += 1;
        }

        cs.into_iter().collect()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
