---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0557.Reverse%20Words%20in%20a%20String%20III/README.md
tags:
    - 双指针
    - 字符串
---

<!-- problem:start -->

# [557. 反转字符串中的单词 III](https://leetcode.cn/problems/reverse-words-in-a-string-iii)

[English Version](/solution/0500-0599/0557.Reverse%20Words%20in%20a%20String%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个字符串<meta charset="UTF-8" />&nbsp;<code>s</code>&nbsp;，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "Let's take LeetCode contest"
<strong>输出：</strong>"s'teL ekat edoCteeL tsetnoc"
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入：</strong> s = "Mr Ding"
<strong>输出：</strong>"rM gniD"
</pre>

<p>&nbsp;</p>

<p><strong><strong><strong><strong>提示：</strong></strong></strong></strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><meta charset="UTF-8" /><code>s</code>&nbsp;包含可打印的 <strong>ASCII</strong> 字符。</li>
	<li><meta charset="UTF-8" /><code>s</code>&nbsp;不包含任何开头或结尾空格。</li>
	<li><meta charset="UTF-8" /><code>s</code>&nbsp;里 <strong>至少</strong> 有一个词。</li>
	<li><meta charset="UTF-8" /><code>s</code>&nbsp;中的所有单词都用一个空格隔开。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以将字符串 $\textit{s}$ 按照空格分割成单词数组 $\textit{words}$，然后将每个单词反转后再拼接成字符串。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $\textit{s}$ 的长度。

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
