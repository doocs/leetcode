# [1592. Rearrange Spaces Between Words](https://leetcode.com/problems/rearrange-spaces-between-words)

[中文文档](/solution/1500-1599/1592.Rearrange%20Spaces%20Between%20Words/README.md)

## Description

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

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reorderSpaces(self, text: str) -> str:
        cnt = text.count(' ')
        words = text.split()
        m = len(words) - 1
        if m == 0:
            return words[0] + ' ' * cnt
        return (' ' * (cnt // m)).join(words) + ' ' * (cnt % m)
```

### **Java**

```java
class Solution {
    public String reorderSpaces(String text) {
        int cnt = 0;
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                ++cnt;
            }
        }
        String[] words = text.split("\\s+");
        List<String> res = new ArrayList<>();
        for (String w : words) {
            if (!"".equals(w)) {
                res.add(w);
            }
        }
        int m = res.size() - 1;
        if (m == 0) {
            return res.get(0) + " ".repeat(cnt);
        }
        String ans = String.join(" ".repeat(cnt / m), res);
        ans += " ".repeat(cnt % m);
        return ans;
    }
}
```

### **Go**

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

### **TypeScript**

```ts
function reorderSpaces(text: string): string {
    let count = 0;
    for (const c of text) {
        if (c === ' ') {
            count++;
        }
    }

    const words = text.trim().split(/\s+/g);
    const n = words.length;
    if (n === 1) {
        return words.join('') + ''.padStart(count);
    }

    const rest = count % (words.length - 1);
    const per = (count - rest) / (words.length - 1);
    return words.join(''.padStart(per)) + ''.padStart(rest);
}
```

### **Rust**

```rust
impl Solution {
    fn create_spaces(n: usize) -> String {
        let mut res = String::new();
        for _ in 0..n {
            res.push(' ');
        }
        res
    }

    pub fn reorder_spaces(text: String) -> String {
        let count = {
            let mut res = 0;
            for c in text.as_bytes() {
                if c == &b' ' {
                    res += 1;
                }
            }
            res
        };

        let works = text.split_whitespace().collect::<Vec<&str>>();
        let n = works.len();
        if n == 1 {
            return works[0].to_string() + &Self::create_spaces(count);
        }
        works.join(&Self::create_spaces((count / (n - 1)))) + &Self::create_spaces(count % (n - 1))
    }
}
```

### **...**

```

```

<!-- tabs:end -->
