# [1455. 检查单词是否为句中其他单词的前缀](https://leetcode.cn/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence)

[English Version](/solution/1400-1499/1455.Check%20If%20a%20Word%20Occurs%20As%20a%20Prefix%20of%20Any%20Word%20in%20a%20Sentence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>sentence</code> 作为句子并指定检索词为 <code>searchWord</code> ，其中句子由若干用 <strong>单个空格</strong> 分隔的单词组成。请你检查检索词 <code>searchWord</code> 是否为句子 <code>sentence</code> 中任意单词的前缀。</p>

<p>如果&nbsp;<code>searchWord</code> 是某一个单词的前缀，则返回句子&nbsp;<code>sentence</code> 中该单词所对应的下标（<strong>下标从 1 开始</strong>）。如果 <code>searchWord</code> 是多个单词的前缀，则返回匹配的第一个单词的下标（<strong>最小下标</strong>）。如果 <code>searchWord</code> 不是任何单词的前缀，则返回 <code>-1</code><strong> </strong>。</p>

<p>字符串 <code>s</code> 的 <strong>前缀</strong> 是 <code>s</code> 的任何前导连续子字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>sentence = "i love eating burger", searchWord = "burg"
<strong>输出：</strong>4
<strong>解释：</strong>"burg" 是 "burger" 的前缀，而 "burger" 是句子中第 4 个单词。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>sentence = "this problem is an easy problem", searchWord = "pro"
<strong>输出：</strong>2
<strong>解释：</strong>"pro" 是 "problem" 的前缀，而 "problem" 是句子中第 2 个也是第 6 个单词，但是应该返回最小下标 2 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>sentence = "i am tired", searchWord = "you"
<strong>输出：</strong>-1
<strong>解释：</strong>"you" 不是句子中任何单词的前缀。

</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= sentence.length &lt;= 100</code></li>
	<li><code>1 &lt;= searchWord.length &lt;= 10</code></li>
	<li><code>sentence</code> 由小写英文字母和空格组成。</li>
	<li><code>searchWord</code> 由小写英文字母组成。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：字符串分割**

将 $sentence$ 按空格分割为 $words$，然后遍历 $words$，检查 $words[i]$ 是否是 $searchWord$ 的前缀，是则返回 $i+1$。若遍历结束，所有单词都不满足，返回 $-1$。

时间复杂度 $O(mn)$。其中 $m$ 是 $sentence$ 的长度，而 $n$ 是 $searchWord$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isPrefixOfWord(self, sentence: str, searchWord: str) -> int:
        for i, s in enumerate(sentence.split(), 1):
            if s.startswith(searchWord):
                return i
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; ++i) {
            if (words[i].startsWith(searchWord)) {
                return i + 1;
            }
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int isPrefixOfWord(string sentence, string searchWord) {
        stringstream ss(sentence);
        string s;
        for (int i = 1; ss >> s; ++i) {
            if (s.find(searchWord) == 0) {
                return i;
            }
        }
        return -1;
    }
};
```

### **Go**

```go
func isPrefixOfWord(sentence string, searchWord string) int {
	for i, s := range strings.Split(sentence, " ") {
		if strings.HasPrefix(s, searchWord) {
			return i + 1
		}
	}
	return -1
}
```

### **TypeScript**

```ts
function isPrefixOfWord(sentence: string, searchWord: string): number {
    const ss = sentence.split(/\s/);
    const n = ss.length;
    for (let i = 0; i < n; i++) {
        if (ss[i].startsWith(searchWord)) {
            return i + 1;
        }
    }
    return -1;
}
```

### **Rust**

```rust
impl Solution {
    pub fn is_prefix_of_word(sentence: String, search_word: String) -> i32 {
        let ss = sentence.split_whitespace().collect::<Vec<&str>>();
        for i in 0..ss.len() {
            if ss[i].starts_with(&search_word) {
                return (i + 1) as i32;
            }
        }
        -1
    }
}
```

### **...**

```

```

<!-- tabs:end -->
