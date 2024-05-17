---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0151.Reverse%20Words%20in%20a%20String/README_EN.md
tags:
    - Two Pointers
    - String
---

<!-- problem:start -->

# [151. Reverse Words in a String](https://leetcode.com/problems/reverse-words-in-a-string)

[中文文档](/solution/0100-0199/0151.Reverse%20Words%20in%20a%20String/README.md)

## Description

<!-- description:start -->

<p>Given an input string <code>s</code>, reverse the order of the <strong>words</strong>.</p>

<p>A <strong>word</strong> is defined as a sequence of non-space characters. The <strong>words</strong> in <code>s</code> will be separated by at least one space.</p>

<p>Return <em>a string of the words in reverse order concatenated by a single space.</em></p>

<p><b>Note</b> that <code>s</code> may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;the sky is blue&quot;
<strong>Output:</strong> &quot;blue is sky the&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;  hello world  &quot;
<strong>Output:</strong> &quot;world hello&quot;
<strong>Explanation:</strong> Your reversed string should not contain leading or trailing spaces.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a good   example&quot;
<strong>Output:</strong> &quot;example good a&quot;
<strong>Explanation:</strong> You need to reduce multiple spaces between two words to a single space in the reversed string.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> contains English letters (upper-case and lower-case), digits, and spaces <code>&#39; &#39;</code>.</li>
	<li>There is <strong>at least one</strong> word in <code>s</code>.</li>
</ul>

<p>&nbsp;</p>
<p><b data-stringify-type="bold">Follow-up:&nbsp;</b>If the string data type is mutable in your language, can&nbsp;you solve it&nbsp;<b data-stringify-type="bold">in-place</b>&nbsp;with&nbsp;<code data-stringify-type="code">O(1)</code>&nbsp;extra space?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Use Language Built-in Functions

We split the string into a list of strings by spaces, then reverse the list, and finally join the list into a string separated by spaces.

Time complexity $O(n)$, space complexity $O(n)$, where $n$ is the length of the string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseWords(self, s: str) -> str:
        return ' '.join(reversed(s.split()))
```

#### Java

```java
class Solution {
    public String reverseWords(String s) {
        List<String> words = Arrays.asList(s.trim().split("\\s+"));
        Collections.reverse(words);
        return String.join(" ", words);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string reverseWords(string s) {
        int i = 0;
        int j = 0;
        int n = s.size();
        while (i < n) {
            while (i < n && s[i] == ' ') {
                ++i;
            }
            if (i < n) {
                if (j != 0) {
                    s[j++] = ' ';
                }
                int k = i;
                while (k < n && s[k] != ' ') {
                    s[j++] = s[k++];
                }
                reverse(s.begin() + j - (k - i), s.begin() + j);
                i = k;
            }
        }
        s.erase(s.begin() + j, s.end());
        reverse(s.begin(), s.end());
        return s;
    }
};
```

#### Go

```go
func reverseWords(s string) string {
	words := strings.Split(s, " ")
	var ans []string
	for i := len(words) - 1; i >= 0; i-- {
		if words[i] != "" {
			ans = append(ans, words[i])
		}
	}
	return strings.Join(ans, " ")
}
```

#### TypeScript

```ts
function reverseWords(s: string): string {
    return s.trim().split(/\s+/).reverse().join(' ');
}
```

#### Rust

```rust
impl Solution {
    pub fn reverse_words(s: String) -> String {
        s.split_whitespace().rev().collect::<Vec<&str>>().join(" ")
    }
}
```

#### C#

```cs
public class Solution {
    public string ReverseWords(string s) {
         return string.Join(" ", s.Trim().Split(" ").Where(word => !string.IsNullOrEmpty(word) && !string.IsNullOrEmpty(word.Trim())).Reverse());
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Two Pointers

We can use two pointers $i$ and $j$, each time we find a word, add it to the result list, then reverse the result list, and finally join the list into a string.

Time complexity $O(n)$, space complexity $O(n)$, where $n$ is the length of the string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseWords(self, s: str) -> str:
        ans = []
        i, n = 0, len(s)
        while i < n:
            while i < n and s[i] == ' ':
                i += 1
            if i < n:
                j = i
                while j < n and s[j] != ' ':
                    j += 1
                ans.append(s[i:j])
                i = j
        return ' '.join(ans[::-1])
```

#### Java

```java
class Solution {
    public String reverseWords(String s) {
        List<String> words = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i < n;) {
            while (i < n && s.charAt(i) == ' ') {
                ++i;
            }
            if (i < n) {
                StringBuilder t = new StringBuilder();
                int j = i;
                while (j < n && s.charAt(j) != ' ') {
                    t.append(s.charAt(j++));
                }
                words.add(t.toString());
                i = j;
            }
        }
        Collections.reverse(words);
        return String.join(" ", words);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
