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

<p>Given a string <code>s</code> consisting of lowercase English letters. Perform the following operation:</p>

<ul>
	<li>Select any non-empty <span data-keyword="substring-nonempty">substring</span> then replace every letter of the substring with the preceding letter of the English alphabet. For example, &#39;b&#39; is converted to &#39;a&#39;, and &#39;a&#39; is converted to &#39;z&#39;.</li>
</ul>

<p>Return the <span data-keyword="lexicographically-smaller-string"><strong>lexicographically smallest</strong></span> string <strong>after performing the operation</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;cbabc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;baabc&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Perform the operation on the substring starting at index 0, and ending at index 1 inclusive.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aa&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;az&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Perform the operation on the last letter.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;acbbc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;abaab&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Perform the operation on the substring starting at index 1, and ending at index 4 inclusive.</p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;leetcode&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;kddsbncd&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Perform the operation on the entire string.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy Algorithm

We can traverse the string $s$ from left to right, find the position $i$ of the first character that is not 'a', and then find the position $j$ of the first 'a' character starting from $i$. We decrement each character in $s[i:j]$, and finally return the processed string.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $s$.

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
