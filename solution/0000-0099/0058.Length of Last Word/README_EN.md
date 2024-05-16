---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0058.Length%20of%20Last%20Word/README_EN.md
tags:
    - String
---

<!-- problem:start -->

# [58. Length of Last Word](https://leetcode.com/problems/length-of-last-word)

[中文文档](/solution/0000-0099/0058.Length%20of%20Last%20Word/README.md)

## Description

<p>Given a string <code>s</code> consisting of words and spaces, return <em>the length of the <strong>last</strong> word in the string.</em></p>

<p>A <strong>word</strong> is a maximal <span data-keyword="substring-nonempty">substring</span> consisting of non-space characters only.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;Hello World&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> The last word is &quot;World&quot; with length 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;   fly me   to   the moon  &quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> The last word is &quot;moon&quot; with length 4.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;luffy is still joyboy&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> The last word is &quot;joyboy&quot; with length 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of only English letters and spaces <code>&#39; &#39;</code>.</li>
	<li>There will be at least one word in <code>s</code>.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Reverse Traversal + Two Pointers

We start traversing from the end of the string $s$, find the first character that is not a space, which is the last character of the last word, and mark the index as $i$. Then continue to traverse forward, find the first character that is a space, which is the character before the first character of the last word, and mark it as $j$. Then the length of the last word is $i - j$.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def lengthOfLastWord(self, s: str) -> int:
        i = len(s) - 1
        while i >= 0 and s[i] == ' ':
            i -= 1
        j = i
        while j >= 0 and s[j] != ' ':
            j -= 1
        return i - j
```

```java
class Solution {
    public int lengthOfLastWord(String s) {
        int i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == ' ') {
            --i;
        }
        int j = i;
        while (j >= 0 && s.charAt(j) != ' ') {
            --j;
        }
        return i - j;
    }
}
```

```cpp
class Solution {
public:
    int lengthOfLastWord(string s) {
        int i = s.size() - 1;
        while (~i && s[i] == ' ') {
            --i;
        }
        int j = i;
        while (~j && s[j] != ' ') {
            --j;
        }
        return i - j;
    }
};
```

```go
func lengthOfLastWord(s string) int {
	i := len(s) - 1
	for i >= 0 && s[i] == ' ' {
		i--
	}
	j := i
	for j >= 0 && s[j] != ' ' {
		j--
	}
	return i - j
}
```

```ts
function lengthOfLastWord(s: string): number {
    let i = s.length - 1;
    while (i >= 0 && s[i] === ' ') {
        --i;
    }
    let j = i;
    while (j >= 0 && s[j] !== ' ') {
        --j;
    }
    return i - j;
}
```

```rust
impl Solution {
    pub fn length_of_last_word(s: String) -> i32 {
        let s = s.trim_end();
        let n = s.len();
        for (i, c) in s.char_indices().rev() {
            if c == ' ' {
                return (n - i - 1) as i32;
            }
        }
        n as i32
    }
}
```

```js
/**
 * @param {string} s
 * @return {number}
 */
var lengthOfLastWord = function (s) {
    let i = s.length - 1;
    while (i >= 0 && s[i] === ' ') {
        --i;
    }
    let j = i;
    while (j >= 0 && s[j] !== ' ') {
        --j;
    }
    return i - j;
};
```

```cs
public class Solution {
    public int LengthOfLastWord(string s) {
        int i = s.Length - 1;
        while (i >= 0 && s[i] == ' ') {
            --i;
        }
        int j = i;
        while (j >= 0 && s[j] != ' ') {
            --j;
        }
        return i - j;
    }
}
```

```php
class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function lengthOfLastWord($s) {
        $count = 0;
        while ($s[strlen($s) - 1] == ' ') {
            $s = substr($s, 0, -1);
        }
        while (strlen($s) != 0 && $s[strlen($s) - 1] != ' ') {
            $count++;
            $s = substr($s, 0, -1);
        }
        return $count;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
