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

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canBeTypedWords(self, text: str, brokenLetters: str) -> int:
        letters = set(brokenLetters)
        res = 0
        for word in text.split():
            find = False
            for letter in letters:
                if letter in word:
                    find = True
                    break
            if not find:
                res += 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        Set<Character> letters = new HashSet<>();
        for (char c : brokenLetters.toCharArray()) {
            letters.add(c);
        }
        int res = 0;
        for (String word : text.split(" ")) {
            boolean find = false;
            for (char c : letters) {
                if (word.indexOf(c) > -1) {
                    find = true;
                    break;
                }
            }
            if (!find) {
                ++res;
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
