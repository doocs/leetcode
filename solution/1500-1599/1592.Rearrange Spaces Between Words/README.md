---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1592.Rearrange%20Spaces%20Between%20Words/README.md
rating: 1362
source: 第 207 场周赛 Q1
tags:
    - 字符串
---

<!-- problem:start -->

# [1592. 重新排列单词间的空格](https://leetcode.cn/problems/rearrange-spaces-between-words)

[English Version](/solution/1500-1599/1592.Rearrange%20Spaces%20Between%20Words/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>text</code> ，该字符串由若干被空格包围的单词组成。每个单词由一个或者多个小写英文字母组成，并且两个单词之间至少存在一个空格。题目测试用例保证 <code>text</code> <strong>至少包含一个单词</strong> 。</p>

<p>请你重新排列空格，使每对相邻单词之间的空格数目都 <strong>相等</strong> ，并尽可能 <strong>最大化</strong> 该数目。如果不能重新平均分配所有空格，请 <strong>将多余的空格放置在字符串末尾</strong> ，这也意味着返回的字符串应当与原 <code>text</code> 字符串的长度相等。</p>

<p>返回 <strong>重新排列空格后的字符串</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>text = &quot;  this   is  a sentence &quot;
<strong>输出：</strong>&quot;this   is   a   sentence&quot;
<strong>解释：</strong>总共有 9 个空格和 4 个单词。可以将 9 个空格平均分配到相邻单词之间，相邻单词间空格数为：9 / (4-1) = 3 个。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>text = &quot; practice   makes   perfect&quot;
<strong>输出：</strong>&quot;practice   makes   perfect &quot;
<strong>解释：</strong>总共有 7 个空格和 3 个单词。7 / (3-1) = 3 个空格加上 1 个多余的空格。多余的空格需要放在字符串的末尾。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>text = &quot;hello   world&quot;
<strong>输出：</strong>&quot;hello   world&quot;
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>text = &quot;  walks  udp package   into  bar a&quot;
<strong>输出：</strong>&quot;walks  udp  package  into  bar  a &quot;
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>text = &quot;a&quot;
<strong>输出：</strong>&quot;a&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 100</code></li>
	<li><code>text</code> 由小写英文字母和 <code>&#39; &#39;</code> 组成</li>
	<li><code>text</code> 中至少包含一个单词</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：字符串模拟

我们先统计字符串 $\textit{text}$ 中的空格数，记为 $\textit{spaces}$。将 $\textit{text}$ 按空格分割成字符串数组 $\textit{words}$。然后计算相邻字符串之间需要拼接的空格数，进行拼接。最后将剩余的空格拼接在末尾。

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 表示字符串 $\textit{text}$ 的长度。

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
