---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1592.Rearrange%20Spaces%20Between%20Words/README_EN.md
rating: 1362
source: Weekly Contest 207 Q1
tags:
    - String
---

<!-- problem:start -->

# [1592. Rearrange Spaces Between Words](https://leetcode.com/problems/rearrange-spaces-between-words)

[中文文档](/solution/1500-1599/1592.Rearrange%20Spaces%20Between%20Words/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>text</code> of words that are placed among some number of spaces. Each word consists of one or more lowercase English letters and are separated by at least one space. It&#39;s guaranteed that <code>text</code> <strong>contains at least one word</strong>.</p>

<p>Rearrange the spaces so that there is an <strong>equal</strong> number of spaces between every pair of adjacent words and that number is <strong>maximized</strong>. If you cannot redistribute all the spaces equally, place the <strong>extra spaces at the end</strong>, meaning the returned string should be the same length as <code>text</code>.</p>

<p>Return <em>the string after rearranging the spaces</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;  this   is  a sentence &quot;
<strong>Output:</strong> &quot;this   is   a   sentence&quot;
<strong>Explanation:</strong> There are a total of 9 spaces and 4 words. We can evenly divide the 9 spaces between the words: 9 / (4-1) = 3 spaces.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> text = &quot; practice   makes   perfect&quot;
<strong>Output:</strong> &quot;practice   makes   perfect &quot;
<strong>Explanation:</strong> There are a total of 7 spaces and 3 words. 7 / (3-1) = 3 spaces plus 1 extra space. We place this extra space at the end of the string.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 100</code></li>
	<li><code>text</code> consists of lowercase English letters and <code>&#39; &#39;</code>.</li>
	<li><code>text</code> contains at least one word.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: String Simulation

First, we count the number of spaces in the string $\textit{text}$, denoted as $\textit{spaces}$. Then, we split $\textit{text}$ by spaces into an array of strings $\textit{words}$. Next, we calculate the number of spaces that need to be inserted between adjacent words and perform the concatenation. Finally, we append the remaining spaces to the end.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ represents the length of the string $\textit{text}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reorderSpaces(self, text: str) -> str:
        spaces = text.count(" ")
        words = text.split()
        if len(words) == 1:
            return words[0] + " " * spaces
        cnt, mod = divmod(spaces, len(words) - 1)
        return (" " * cnt).join(words) + " " * mod
```

#### Java

```java
class Solution {
    public String reorderSpaces(String text) {
        int spaces = 0;
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                ++spaces;
            }
        }
        String[] words = text.trim().split("\\s+");
        if (words.length == 1) {
            return words[0] + " ".repeat(spaces);
        }
        int cnt = spaces / (words.length - 1);
        int mod = spaces % (words.length - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; ++i) {
            sb.append(words[i]);
            if (i < words.length - 1) {
                sb.append(" ".repeat(cnt));
            }
        }
        sb.append(" ".repeat(mod));
        return sb.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string reorderSpaces(string text) {
        int spaces = ranges::count(text, ' ');
        auto words = split(text);

        if (words.size() == 1) {
            return words[0] + string(spaces, ' ');
        }

        int cnt = spaces / (words.size() - 1);
        int mod = spaces % (words.size() - 1);

        string result = join(words, string(cnt, ' '));
        result += string(mod, ' ');

        return result;
    }

private:
    vector<string> split(const string& text) {
        vector<string> words;
        istringstream stream(text);
        string word;
        while (stream >> word) {
            words.push_back(word);
        }
        return words;
    }

    string join(const vector<string>& words, const string& separator) {
        ostringstream result;
        for (size_t i = 0; i < words.size(); ++i) {
            result << words[i];
            if (i < words.size() - 1) {
                result << separator;
            }
        }
        return result.str();
    }
};
```

#### Go

```go
func reorderSpaces(text string) string {
	cnt := strings.Count(text, " ")
	words := strings.Fields(text)
	m := len(words) - 1
	if m == 0 {
		return words[0] + strings.Repeat(" ", cnt)
	}
	return strings.Join(words, strings.Repeat(" ", cnt/m)) + strings.Repeat(" ", cnt%m)
}
```

#### TypeScript

```ts
function reorderSpaces(text: string): string {
    const spaces = (text.match(/ /g) || []).length;
    const words = text.split(/\s+/).filter(Boolean);
    if (words.length === 1) {
        return words[0] + ' '.repeat(spaces);
    }
    const cnt = Math.floor(spaces / (words.length - 1));
    const mod = spaces % (words.length - 1);
    const result = words.join(' '.repeat(cnt));
    return result + ' '.repeat(mod);
}
```

#### Rust

```rust
impl Solution {
    pub fn reorder_spaces(text: String) -> String {
        let spaces = text.chars().filter(|&c| c == ' ').count();
        let words: Vec<&str> = text.split_whitespace().collect();
        if words.len() == 1 {
            return format!("{}{}", words[0], " ".repeat(spaces));
        }
        let cnt = spaces / (words.len() - 1);
        let mod_spaces = spaces % (words.len() - 1);
        let result = words.join(&" ".repeat(cnt));
        result + &" ".repeat(mod_spaces)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
