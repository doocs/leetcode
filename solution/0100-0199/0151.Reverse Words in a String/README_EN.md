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

### Solution 1: Two Pointers

We can use two pointers $i$ and $j$ to find each word, add it to the result list, then reverse the result list, and finally concatenate it into a string.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseWords(self, s: str) -> str:
        words = []
        i, n = 0, len(s)
        while i < n:
            while i < n and s[i] == " ":
                i += 1
            if i < n:
                j = i
                while j < n and s[j] != " ":
                    j += 1
                words.append(s[i:j])
                i = j
        return " ".join(words[::-1])
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
	words := []string{}
	i, n := 0, len(s)
	for i < n {
		for i < n && s[i] == ' ' {
			i++
		}
		if i < n {
			j := i
			t := []byte{}
			for j < n && s[j] != ' ' {
				t = append(t, s[j])
				j++
			}
			words = append(words, string(t))
			i = j
		}
	}
	for i, j := 0, len(words)-1; i < j; i, j = i+1, j-1 {
		words[i], words[j] = words[j], words[i]
	}
	return strings.Join(words, " ")
}
```

#### TypeScript

```ts
function reverseWords(s: string): string {
    const words: string[] = [];
    const n = s.length;
    let i = 0;
    while (i < n) {
        while (i < n && s[i] === ' ') {
            i++;
        }
        if (i < n) {
            let j = i;
            while (j < n && s[j] !== ' ') {
                j++;
            }
            words.push(s.slice(i, j));
            i = j;
        }
    }
    return words.reverse().join(' ');
}
```

#### Rust

```rust
impl Solution {
    pub fn reverse_words(s: String) -> String {
        let mut words = Vec::new();
        let s: Vec<char> = s.chars().collect();
        let mut i = 0;
        let n = s.len();

        while i < n {
            while i < n && s[i] == ' ' {
                i += 1;
            }
            if i < n {
                let mut j = i;
                while j < n && s[j] != ' ' {
                    j += 1;
                }
                words.push(s[i..j].iter().collect::<String>());
                i = j;
            }
        }

        words.reverse();
        words.join(" ")
    }
}
```

#### C#

```cs
public class Solution {
    public string ReverseWords(string s) {
        List<string> words = new List<string>();
        int n = s.Length;
        for (int i = 0; i < n;) {
            while (i < n && s[i] == ' ') {
                ++i;
            }
            if (i < n) {
                System.Text.StringBuilder t = new System.Text.StringBuilder();
                int j = i;
                while (j < n && s[j] != ' ') {
                    t.Append(s[j++]);
                }
                words.Add(t.ToString());
                i = j;
            }
        }
        words.Reverse();
        return string.Join(" ", words);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: String Split

We can use the built-in string split function to split the string into a list of words by spaces, then reverse the list, and finally concatenate it into a string.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseWords(self, s: str) -> str:
        return " ".join(reversed(s.split()))
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

#### Go

```go
func reverseWords(s string) string {
	words := strings.Fields(s)
	for i, j := 0, len(words)-1; i < j; i, j = i+1, j-1 {
		words[i], words[j] = words[j], words[i]
	}
	return strings.Join(words, " ")
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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
