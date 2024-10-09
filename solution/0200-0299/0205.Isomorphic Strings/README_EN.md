---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0205.Isomorphic%20Strings/README_EN.md
tags:
    - Hash Table
    - String
---

<!-- problem:start -->

# [205. Isomorphic Strings](https://leetcode.com/problems/isomorphic-strings)

[中文文档](/solution/0200-0299/0205.Isomorphic%20Strings/README.md)

## Description

<!-- description:start -->

<p>Given two strings <code>s</code> and <code>t</code>, <em>determine if they are isomorphic</em>.</p>

<p>Two strings <code>s</code> and <code>t</code> are isomorphic if the characters in <code>s</code> can be replaced to get <code>t</code>.</p>

<p>All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;egg&quot;, t = &quot;add&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>The strings <code>s</code> and <code>t</code> can be made identical by:</p>

<ul>
	<li>Mapping <code>&#39;e&#39;</code> to <code>&#39;a&#39;</code>.</li>
	<li>Mapping <code>&#39;g&#39;</code> to <code>&#39;d&#39;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;foo&quot;, t = &quot;bar&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>The strings <code>s</code> and <code>t</code> can not be made identical as <code>&#39;o&#39;</code> needs to be mapped to both <code>&#39;a&#39;</code> and <code>&#39;r&#39;</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;paper&quot;, t = &quot;title&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>t.length == s.length</code></li>
	<li><code>s</code> and <code>t</code> consist of any valid ascii character.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table or Array

We can use two hash tables or arrays $d_1$ and $d_2$ to record the character mapping relationship between $s$ and $t$.

Traverse $s$ and $t$, if the corresponding character mapping relationships in $d_1$ and $d_2$ are different, return `false`, otherwise update the corresponding character mapping relationships in $d_1$ and $d_2$. After the traversal is complete, it means that $s$ and $t$ are isomorphic, and return `true`.

The time complexity is $O(n)$ and the space complexity is $O(C)$. Where $n$ is the length of the string $s$; and $C$ is the size of the character set, which is $C = 256$ in this problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isIsomorphic(self, s: str, t: str) -> bool:
        d1 = {}
        d2 = {}
        for a, b in zip(s, t):
            if (a in d1 and d1[a] != b) or (b in d2 and d2[b] != a):
                return False
            d1[a] = b
            d2[b] = a
        return True
```

#### Java

```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> d1 = new HashMap<>();
        Map<Character, Character> d2 = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char a = s.charAt(i), b = t.charAt(i);
            if (d1.containsKey(a) && d1.get(a) != b) {
                return false;
            }
            if (d2.containsKey(b) && d2.get(b) != a) {
                return false;
            }
            d1.put(a, b);
            d2.put(b, a);
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isIsomorphic(string s, string t) {
        int d1[256]{};
        int d2[256]{};
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            char a = s[i], b = t[i];
            if (d1[a] != d2[b]) {
                return false;
            }
            d1[a] = d2[b] = i + 1;
        }
        return true;
    }
};
```

#### Go

```go
func isIsomorphic(s string, t string) bool {
	d1 := [256]int{}
	d2 := [256]int{}
	for i := range s {
		if d1[s[i]] != d2[t[i]] {
			return false
		}
		d1[s[i]] = i + 1
		d2[t[i]] = i + 1
	}
	return true
}
```

#### TypeScript

```ts
function isIsomorphic(s: string, t: string): boolean {
    const d1: number[] = new Array(256).fill(0);
    const d2: number[] = new Array(256).fill(0);
    for (let i = 0; i < s.length; ++i) {
        const a = s.charCodeAt(i);
        const b = t.charCodeAt(i);
        if (d1[a] !== d2[b]) {
            return false;
        }
        d1[a] = i + 1;
        d2[b] = i + 1;
    }
    return true;
}
```

#### Rust

```rust
use std::collections::HashMap;
impl Solution {
    fn help(s: &[u8], t: &[u8]) -> bool {
        let mut map = HashMap::new();
        for i in 0..s.len() {
            if map.contains_key(&s[i]) {
                if map.get(&s[i]).unwrap() != &t[i] {
                    return false;
                }
            } else {
                map.insert(s[i], t[i]);
            }
        }
        true
    }

    pub fn is_isomorphic(s: String, t: String) -> bool {
        let (s, t) = (s.as_bytes(), t.as_bytes());
        Self::help(s, t) && Self::help(t, s)
    }
}
```

#### C#

```cs
public class Solution {
    public bool IsIsomorphic(string s, string t) {
        int[] d1 = new int[256];
        int[] d2 = new int[256];
        for (int i = 0; i < s.Length; ++i) {
            var a = s[i];
            var b = t[i];
            if (d1[a] != d2[b]) {
                return false;
            }
            d1[a] = i + 1;
            d2[b] = i + 1;
        }
        return true;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isIsomorphic(self, s: str, t: str) -> bool:
        d1, d2 = [0] * 256, [0] * 256
        for i, (a, b) in enumerate(zip(s, t), 1):
            a, b = ord(a), ord(b)
            if d1[a] != d2[b]:
                return False
            d1[a] = d2[b] = i
        return True
```

#### Java

```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] d1 = new int[256];
        int[] d2 = new int[256];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char a = s.charAt(i), b = t.charAt(i);
            if (d1[a] != d2[b]) {
                return false;
            }
            d1[a] = i + 1;
            d2[b] = i + 1;
        }
        return true;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
