---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0003.Longest%20Substring%20Without%20Repeating%20Characters/README_EN.md
tags:
    - Hash Table
    - String
    - Sliding Window
---

<!-- problem:start -->

# [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters)

[中文文档](/solution/0000-0099/0003.Longest%20Substring%20Without%20Repeating%20Characters/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code>, find the length of the <strong>longest</strong> <span data-keyword="substring-nonempty"><strong>substring</strong></span> without repeating characters.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcabcbb&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The answer is &quot;abc&quot;, with the length of 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bbbbb&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> The answer is &quot;b&quot;, with the length of 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;pwwkew&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The answer is &quot;wke&quot;, with the length of 3.
Notice that the answer must be a substring, &quot;pwke&quot; is a subsequence and not a substring.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code> consists of English letters, digits, symbols and spaces.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two pointers + Hash Table

Define a hash table to record the characters in the current window. Let $i$ and $j$ represent the start and end positions of the non-repeating substring, respectively. The length of the longest non-repeating substring is recorded by `ans`.

For each character $s[j]$ in the string `s`, we call it $c$. If $c$ exists in the window $s[i..j-1]$, we move $i$ to the right until $s[i..j-1]$ does not contain `c`. Then we add `c` to the hash table. At this time, the window $s[i..j]$ does not contain repeated elements, and we update the maximum value of `ans`.

Finally, return `ans`.

The time complexity is $O(n)$, where $n$ represents the length of the string `s`.

Two pointers algorithm template:

```java
for (int i = 0, j = 0; i < n; ++i) {
    while (j < i && check(j, i)) {
        ++j;
    }
    // logic of specific problem
}
```

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        ss = set()
        ans = i = 0
        for j, c in enumerate(s):
            while c in ss:
                ss.remove(s[i])
                i += 1
            ss.add(c)
            ans = max(ans, j - i + 1)
        return ans
```

#### Java

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        boolean[] ss = new boolean[128];
        int ans = 0;
        for (int i = 0, j = 0; j < s.length(); ++j) {
            char c = s.charAt(j);
            while (ss[c]) {
                ss[s.charAt(i++)] = false;
            }
            ss[c] = true;
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        bool ss[128]{};
        int ans = 0;
        for (int i = 0, j = 0; j < s.size(); ++j) {
            while (ss[s[j]]) {
                ss[s[i++]] = false;
            }
            ss[s[j]] = true;
            ans = max(ans, j - i + 1);
        }
        return ans;
    }
};
```

#### Go

```go
func lengthOfLongestSubstring(s string) (ans int) {
	ss := [128]bool{}
	for i, j := 0, 0; j < len(s); j++ {
		for ss[s[j]] {
			ss[s[i]] = false
			i++
		}
		ss[s[j]] = true
		ans = max(ans, j-i+1)
	}
	return
}
```

#### TypeScript

```ts
function lengthOfLongestSubstring(s: string): number {
    let ans = 0;
    const ss: Set<string> = new Set();
    for (let i = 0, j = 0; j < s.length; ++j) {
        while (ss.has(s[j])) {
            ss.delete(s[i++]);
        }
        ss.add(s[j]);
        ans = Math.max(ans, j - i + 1);
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashSet;

impl Solution {
    pub fn length_of_longest_substring(s: String) -> i32 {
        let s = s.as_bytes();
        let mut ss = HashSet::new();
        let mut i = 0;
        s
            .iter()
            .map(|c| {
                while ss.contains(&c) {
                    ss.remove(&s[i]);
                    i += 1;
                }
                ss.insert(c);
                ss.len()
            })
            .max()
            .unwrap_or(0) as i32
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {number}
 */
var lengthOfLongestSubstring = function (s) {
    let ans = 0;
    const ss = new Set();
    for (let i = 0, j = 0; j < s.length; ++j) {
        while (ss.has(s[j])) {
            ss.delete(s[i++]);
        }
        ss.add(s[j]);
        ans = Math.max(ans, j - i + 1);
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int LengthOfLongestSubstring(string s) {
        int ans = 0;
        var ss = new HashSet<char>();
        for (int i = 0, j = 0; j < s.Length; ++j) {
            while (ss.Contains(s[j])) {
                ss.Remove(s[i++]);
            }
            ss.Add(s[j]);
            ans = Math.Max(ans, j - i + 1);
        }
        return ans;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function lengthOfLongestSubstring($s) {
        $ans = 0;
        $ss = [];
        for ($i = 0, $j = 0; $j < strlen($s); ++$j) {
            while (in_array($s[$j], $ss)) {
                unset($ss[array_search($s[$i++], $ss)]);
            }
            $ss[] = $s[$j];
            $ans = max($ans, $j - $i + 1);
        }
        return $ans;
    }
}
```

#### Swift

```swift
class Solution {
    func lengthOfLongestSubstring(_ s: String) -> Int {
        var map = [Character: Int]()
        var currentStartingIndex = 0
        var i = 0
        var maxLength = 0
        for char in s {
            if map[char] != nil {
                if map[char]! >= currentStartingIndex {
                    maxLength = max(maxLength, i - currentStartingIndex)
                    currentStartingIndex = map[char]! + 1
                }
            }
            map[char] = i
            i += 1
        }
        return max(maxLength, i - currentStartingIndex)
    }
}
```

#### Nim

```nim
proc lengthOfLongestSubstring(s: string): int =
  var
    i = 0
    j = 0
    res = 0
    literals: set[char] = {}

  while i < s.len:
    while s[i] in literals:
      if s[j] in literals:
        excl(literals, s[j])
      j += 1
    literals.incl(s[i]) # Uniform Function Call Syntax f(x) = x.f
    res = max(res, i - j + 1)
    i += 1

  result = res # result has the default return value
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
