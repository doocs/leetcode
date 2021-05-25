# [1813. 句子相似性 III](https://leetcode-cn.com/problems/sentence-similarity-iii)

[English Version](/solution/1800-1899/1813.Sentence%20Similarity%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个句子是由一些单词与它们之间的单个空格组成，且句子的开头和结尾没有多余空格。比方说，<code>"Hello World"</code> ，<code>"HELLO"</code> ，<code>"hello world hello world"</code> 都是句子。每个单词都 <strong>只</strong> 包含大写和小写英文字母。</p>

<p>如果两个句子 <code>sentence1</code> 和 <code>sentence2</code> ，可以通过往其中一个句子插入一个任意的句子（<strong>可以是空句子</strong>）而得到另一个句子，那么我们称这两个句子是 <strong>相似的</strong> 。比方说，<code>sentence1 = "Hello my name is Jane"</code> 且 <code>sentence2 = "Hello Jane"</code> ，我们可以往 <code>sentence2</code> 中 <code>"Hello"</code> 和 <code>"Jane"</code> 之间插入 <code>"my name is"</code> 得到 <code>sentence1</code> 。</p>

<p>给你两个句子 <code>sentence1</code> 和 <code>sentence2</code> ，如果<em> </em><code>sentence1</code> 和<em> </em><code>sentence2</code> 是相似的，请你返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>sentence1 = "My name is Haley", sentence2 = "My Haley"
<b>输出：</b>true
<b>解释：</b>可以往 sentence2 中 "My" 和 "Haley" 之间插入 "name is" ，得到 sentence1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>sentence1 = "of", sentence2 = "A lot of words"
<b>输出：</b>false
<strong>解释：</strong>没法往这两个句子中的一个句子只插入一个句子就得到另一个句子。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>sentence1 = "Eating right now", sentence2 = "Eating"
<b>输出：</b>true
<b>解释：</b>可以往 sentence2 的结尾插入 "right now" 得到 sentence1 。
</pre>

<p><strong>示例 4：</strong></p>

<pre><b>输入：</b>sentence1 = "Luky", sentence2 = "Lucccky"
<b>输出：</b>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= sentence1.length, sentence2.length &lt;= 100</code></li>
	<li><code>sentence1</code> 和 <code>sentence2</code> 都只包含大小写英文字母和空格。</li>
	<li><code>sentence1</code> 和 <code>sentence2</code> 中的单词都只由单个空格隔开。</li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def areSentencesSimilar(self, sentence1: str, sentence2: str) -> bool:
        if sentence1 == sentence2:
            return True
        n1, n2 = len(sentence1), len(sentence2)
        if n1 == n2:
            return False
        if n1 < n2:
            sentence1, sentence2 = sentence2, sentence1
        words1, words2 = sentence1.split(), sentence2.split()
        i = j = 0
        while i < len(words2) and words1[i] == words2[i]:
            i += 1
        if i == len(words2):
            return True
        while j < len(words2) and words1[len(words1) - 1 - j] == words2[len(words2) - 1 - j]:
            j += 1
        if j == len(words2):
            return True
        return i + j == len(words2)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        if (Objects.equals(sentence1, sentence2)) {
            return true;
        }
        int n1 = sentence1.length(), n2 = sentence2.length();
        if (n1 == n2) {
            return false;
        }
        if (n1 < n2) {
            String t = sentence1;
            sentence1 = sentence2;
            sentence2 = t;
        }
        String[] words1 = sentence1.split(" ");
        String[] words2 = sentence2.split(" ");
        int i = 0, j = 0;
        while (i < words2.length &&  Objects.equals(words1[i], words2[i])) {
            ++i;
        }
        if (i == words2.length) {
            return true;
        }
        while (j < words2.length && Objects.equals(words1[words1.length - 1 - j], words2[words2.length - 1 - j])) {
            ++j;
        }
        if (j == words2.length) {
            return true;
        }
        return i + j == words2.length;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
