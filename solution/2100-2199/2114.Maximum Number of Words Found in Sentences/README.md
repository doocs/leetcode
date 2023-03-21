# [2114. 句子中的最多单词数](https://leetcode.cn/problems/maximum-number-of-words-found-in-sentences)

[English Version](/solution/2100-2199/2114.Maximum%20Number%20of%20Words%20Found%20in%20Sentences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个 <strong>句子</strong>&nbsp;由一些 <strong>单词</strong>&nbsp;以及它们之间的单个空格组成，句子的开头和结尾不会有多余空格。</p>

<p>给你一个字符串数组&nbsp;<code>sentences</code>&nbsp;，其中&nbsp;<code>sentences[i]</code>&nbsp;表示单个 <strong>句子</strong>&nbsp;。</p>

<p>请你返回单个句子里 <strong>单词的最多数目</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>sentences = ["alice and bob love leetcode", "i think so too", <em><strong>"this is great thanks very much"</strong></em>]
<b>输出：</b>6
<b>解释：</b>
- 第一个句子 "alice and bob love leetcode" 总共有 5 个单词。
- 第二个句子 "i think so too" 总共有 4 个单词。
- 第三个句子 "this is great thanks very much" 总共有 6 个单词。
所以，单个句子中有最多单词数的是第三个句子，总共有 6 个单词。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>sentences = ["please wait", <em><strong>"continue to fight"</strong></em>, <em><strong>"continue to win"</strong></em>]
<b>输出：</b>3
<b>解释：</b>可能有多个句子有相同单词数。
这个例子中，第二个句子和第三个句子（加粗斜体）有相同数目的单词数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= sentences.length &lt;= 100</code></li>
	<li><code>1 &lt;= sentences[i].length &lt;= 100</code></li>
	<li><code>sentences[i]</code>&nbsp;只包含小写英文字母和&nbsp;<code>' '</code>&nbsp;。</li>
	<li><code>sentences[i]</code>&nbsp;的开头和结尾都没有空格。</li>
	<li><code>sentences[i]</code>&nbsp;中所有单词由单个空格隔开。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：空格计数**

我们遍历数组 `sentences`，对于每个句子，我们计算其中的空格数，那么单词数就是空格数加 $1$。最后返回最大的单词数即可。

时间复杂度 $O(L)$，空间复杂度 $O(1)$。其中 $L$ 是数组 `sentences` 中所有字符串的长度之和。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def mostWordsFound(self, sentences: List[str]) -> int:
        return 1 + max(s.count(' ') for s in sentences)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int mostWordsFound(String[] sentences) {
        int ans = 0;
        for (var s : sentences) {
            int cnt = 1;
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == ' ') {
                    ++cnt;
                }
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int mostWordsFound(vector<string>& sentences) {
        int ans = 0;
        for (auto& s : sentences) {
            int cnt = 1 + count(s.begin(), s.end(), ' ');
            ans = max(ans, cnt);
        }
        return ans;
    }
};
```

### **Go**

```go
func mostWordsFound(sentences []string) (ans int) {
	for _, s := range sentences {
		cnt := 1 + strings.Count(s, " ")
		if ans < cnt {
			ans = cnt
		}
	}
	return
}
```

### **TypeScript**

```ts
function mostWordsFound(sentences: string[]): number {
    return sentences.reduce(
        (r, s) =>
            Math.max(
                r,
                [...s].reduce((r, c) => r + (c === ' ' ? 1 : 0), 1),
            ),
        0,
    );
}
```

### **Rust**

```rust
impl Solution {
    pub fn most_words_found(sentences: Vec<String>) -> i32 {
        let mut ans = 0;
        for s in sentences.iter() {
            let mut count = 1;
            for c in s.as_bytes() {
                if *c == b' ' {
                    count += 1;
                }
            }
            ans = ans.max(count);
        }
        ans
    }
}
```

### **C**

```c
#define max(a, b) (((a) > (b)) ? (a) : (b))

int mostWordsFound(char **sentences, int sentencesSize) {
    int ans = 0;
    for (int i = 0; i < sentencesSize; i++) {
        char *s = sentences[i];
        int count = 1;
        for (int j = 0; s[j]; j++) {
            if (s[j] == ' ') {
                count++;
            }
        }
        ans = max(ans, count);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
