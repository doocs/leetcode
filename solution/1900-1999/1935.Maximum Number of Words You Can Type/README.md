---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1935.Maximum%20Number%20of%20Words%20You%20Can%20Type/README.md
rating: 1226
source: 第 250 场周赛 Q1
tags:
    - 哈希表
    - 字符串
---

# [1935. 可以输入的最大单词数](https://leetcode.cn/problems/maximum-number-of-words-you-can-type)

[English Version](/solution/1900-1999/1935.Maximum%20Number%20of%20Words%20You%20Can%20Type/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>键盘出现了一些故障，有些字母键无法正常工作。而键盘上所有其他键都能够正常工作。</p>

<p>给你一个由若干单词组成的字符串 <code>text</code> ，单词间由单个空格组成（不含前导和尾随空格）；另有一个字符串 <code>brokenLetters</code> ，由所有已损坏的不同字母键组成，返回你可以使用此键盘完全输入的 <code>text</code> 中单词的数目。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>text = "hello world", brokenLetters = "ad"
<strong>输出：</strong>1
<strong>解释：</strong>无法输入 "world" ，因为字母键 'd' 已损坏。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>text = "leet code", brokenLetters = "lt"
<strong>输出：</strong>1
<strong>解释：</strong>无法输入 "leet" ，因为字母键 'l' 和 't' 已损坏。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>text = "leet code", brokenLetters = "e"
<strong>输出：</strong>0
<strong>解释：</strong>无法输入任何单词，因为字母键 'e' 已损坏。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= brokenLetters.length &lt;= 26</code></li>
	<li><code>text</code> 由若干用单个空格分隔的单词组成，且不含任何前导和尾随空格</li>
	<li>每个单词仅由小写英文字母组成</li>
	<li><code>brokenLetters</code> 由 <strong>互不相同</strong> 的小写英文字母组成</li>
</ul>

## 解法

### 方法一：数组或哈希表

我们可以用哈希表或者一个长度为 $26$ 的数组 $s$ 来记录所有损坏的字母键。

然后，我们遍历字符串 $text$ 中的每个单词 $w$，如果 $w$ 中的某个字母 $c$ 在 $s$ 中出现过，那么说明这个单词无法输入，答案不需要加一，否则答案需要加一。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(|\Sigma|)$，其中 $n$ 是字符串 $text$ 的长度，而 $|\Sigma|$ 是字母表的大小，本题中 $|\Sigma|=26$。

<!-- tabs:start -->

```python
class Solution:
    def canBeTypedWords(self, text: str, brokenLetters: str) -> int:
        s = set(brokenLetters)
        return sum(all(c not in s for c in w) for w in text.split())
```

```java
class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        boolean[] s = new boolean[26];
        for (char c : brokenLetters.toCharArray()) {
            s[c - 'a'] = true;
        }
        int ans = 0;
        for (String w : text.split(" ")) {
            for (char c : w.toCharArray()) {
                if (s[c - 'a']) {
                    --ans;
                    break;
                }
            }
            ++ans;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int canBeTypedWords(string text, string brokenLetters) {
        bool s[26]{};
        for (char& c : brokenLetters) {
            s[c - 'a'] = true;
        }
        int ans = 0;
        for (auto& w : split(text, ' ')) {
            for (char& c : w) {
                if (s[c - 'a']) {
                    --ans;
                    break;
                }
            }
            ++ans;
        }
        return ans;
    }

    vector<string> split(const string& s, char c) {
        vector<string> ans;
        string t;
        for (char d : s) {
            if (d == c) {
                ans.push_back(t);
                t.clear();
            } else {
                t.push_back(d);
            }
        }
        ans.push_back(t);
        return ans;
    }
};
```

```go
func canBeTypedWords(text string, brokenLetters string) (ans int) {
	s := [26]bool{}
	for _, c := range brokenLetters {
		s[c-'a'] = true
	}
	for _, w := range strings.Split(text, " ") {
		for _, c := range w {
			if s[c-'a'] {
				ans--
				break
			}
		}
		ans++
	}
	return
}
```

```ts
function canBeTypedWords(text: string, brokenLetters: string): number {
    const s: boolean[] = Array(26).fill(false);
    for (const c of brokenLetters) {
        s[c.charCodeAt(0) - 'a'.charCodeAt(0)] = true;
    }
    let ans = 0;
    for (const w of text.split(' ')) {
        for (const c of w) {
            if (s[c.charCodeAt(0) - 'a'.charCodeAt(0)]) {
                --ans;
                break;
            }
        }
        ++ans;
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn can_be_typed_words(text: String, broken_letters: String) -> i32 {
        let mut s = vec![false; 26];
        for c in broken_letters.chars() {
            s[(c as usize) - ('a' as usize)] = true;
        }
        let mut ans = 0;
        let words = text.split_whitespace();
        for w in words {
            for c in w.chars() {
                if s[(c as usize) - ('a' as usize)] {
                    ans -= 1;
                    break;
                }
            }
            ans += 1;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
