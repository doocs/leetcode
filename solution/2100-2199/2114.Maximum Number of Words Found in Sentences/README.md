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

返回最大空格数量再加 1 即可。

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
        for (String s : sentences) {
            ans = Math.max(ans, 1 + count(s, ' '));
        }
        return ans;
    }

    private int count(String s, char c) {
        int cnt = 0;
        for (char ch : s.toCharArray()) {
            if (ch == c) {
                ++cnt;
            }
        }
        return cnt;
    }
}

```

### **C++**

```cpp
class Solution {
public:
    int mostWordsFound(vector<string>& sentences) {
        int ans = 0;
        for (string& s : sentences)
            ans = max(ans, 1 + count(s, ' '));
        return ans;
    }

    int count(string s, char c) {
        int cnt = 0;
        for (char& ch : s)
            if (ch == c)
                ++cnt;
        return cnt;
    }
};
```

### **Go**

```go
func mostWordsFound(sentences []string) int {
	count := func(s string, c rune) int {
		cnt := 0
		for _, ch := range s {
			if ch == c {
				cnt++
			}
		}
		return cnt
	}
	ans := 0
	for _, s := range sentences {
		ans = max(ans, 1+count(s, ' '))
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
