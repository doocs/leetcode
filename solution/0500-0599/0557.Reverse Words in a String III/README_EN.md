---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0557.Reverse%20Words%20in%20a%20String%20III/README_EN.md
tags:
    - Two Pointers
    - String
---

<!-- problem:start -->

# [557. Reverse Words in a String III](https://leetcode.com/problems/reverse-words-in-a-string-iii)

[中文文档](/solution/0500-0599/0557.Reverse%20Words%20in%20a%20String%20III/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code>, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;Let&#39;s take LeetCode contest&quot;
<strong>Output:</strong> &quot;s&#39;teL ekat edoCteeL tsetnoc&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;Mr Ding&quot;
<strong>Output:</strong> &quot;rM gniD&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code> contains printable <strong>ASCII</strong> characters.</li>
	<li><code>s</code> does not contain any leading or trailing spaces.</li>
	<li>There is <strong>at least one</strong> word in <code>s</code>.</li>
	<li>All the words in <code>s</code> are separated by a single space.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can split the string $\textit{s}$ into an array of words $\textit{words}$ by spaces, then reverse each word and concatenate them back into a string.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $\textit{s}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseWords(self, s: str) -> str:
        return " ".join(t[::-1] for t in s.split())
```

#### Java

```java
class Solution {
    public String reverseWords(String s) {
        String[] words = s.split(" ");
        for (int i = 0; i < words.length; ++i) {
            words[i] = new StringBuilder(words[i]).reverse().toString();
        }
        return String.join(" ", words);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string reverseWords(string s) {
        stringstream ss(s);
        string t;
        string ans;
        while (ss >> t) {
            reverse(t.begin(), t.end());
            ans += t;
            ans.push_back(' ');
        }
        ans.pop_back();
        return ans;
    }
};
```

#### Go

```go
func reverseWords(s string) string {
	words := strings.Fields(s)
	for i, w := range words {
		t := []byte(w)
		slices.Reverse(t)
		words[i] = string(t)
	}
	return strings.Join(words, " ")
}
```

#### TypeScript

```ts
function reverseWords(s: string): string {
    return s
        .split(' ')
        .map(t => t.split('').reverse().join(''))
        .join(' ');
}
```

#### Rust

```rust
impl Solution {
    pub fn reverse_words(s: String) -> String {
        s.split(' ')
            .map(|s| s.chars().rev().collect::<String>())
            .collect::<Vec<_>>()
            .join(" ")
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {string}
 */
var reverseWords = function (s) {
    return s
        .split(' ')
        .map(t => t.split('').reverse().join(''))
        .join(' ');
};
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @return String
     */
    function reverseWords($s) {
        $words = explode(' ', $s);
        foreach ($words as $i => $word) {
            $words[$i] = strrev($word);
        }
        return implode(' ', $words);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
