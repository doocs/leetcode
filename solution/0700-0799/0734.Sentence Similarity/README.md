# [734. 句子相似性](https://leetcode-cn.com/problems/sentence-similarity)

[English Version](/solution/0700-0799/0734.Sentence%20Similarity/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个句子 <code>words1, words2</code> （每个用字符串数组表示），和一个相似单词对的列表&nbsp;<code>pairs</code>&nbsp;，判断是否两个句子是相似的。</p>

<p>例如，当相似单词对是 <code>pairs = [[&quot;great&quot;, &quot;fine&quot;], [&quot;acting&quot;,&quot;drama&quot;], [&quot;skills&quot;,&quot;talent&quot;]]</code>的时候，&quot;great acting skills&quot; 和 &quot;fine drama talent&quot; 是相似的。</p>

<p>注意相似关系是不具有传递性的。例如，如果 &quot;great&quot; 和&nbsp;&quot;fine&quot; 是相似的，&quot;fine&quot; 和&nbsp;&quot;good&quot; 是相似的，但是&nbsp;&quot;great&quot; 和 &quot;good&quot; 未必是相似的。</p>

<p>但是，相似关系是具有对称性的。例如，&quot;great&quot; 和 &quot;fine&quot; 是相似的相当于&nbsp;&quot;fine&quot; 和&nbsp;&quot;great&quot; 是相似的。</p>

<p>而且，一个单词总是与其自身相似。例如，句子 <code>words1 = [&quot;great&quot;], words2 = [&quot;great&quot;], pairs = []</code> 是相似的，尽管没有输入特定的相似单词对。</p>

<p>最后，句子只会在具有相同单词个数的前提下才会相似。所以一个句子 <code>words1 = [&quot;great&quot;]</code> 永远不可能和句子 <code>words2 = [&quot;doubleplus&quot;,&quot;good&quot;]</code> 相似。</p>

<p>&nbsp;</p>

<p><strong>注：</strong></p>

<ul>
	<li><code>words1</code> and <code>words2</code> 的长度不会超过&nbsp;<code>1000</code>。</li>
	<li><code>pairs</code>&nbsp;的长度不会超过&nbsp;<code>2000</code>。</li>
	<li>每个<code>pairs[i]</code>&nbsp;的长度为&nbsp;<code>2</code>。</li>
	<li>每个&nbsp;<code>words[i]</code>&nbsp;和&nbsp;<code>pairs[i][j]</code>&nbsp;的长度范围为&nbsp;<code>[1, 20]</code>。</li>
</ul>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“哈希表”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def areSentencesSimilar(self, sentence1: List[str], sentence2: List[str], similarPairs: List[List[str]]) -> bool:
        if len(sentence1) != len(sentence2):
            return False
        pairs = {(word1, word2) for word1, word2 in similarPairs}
        for i in range(len(sentence1)):
            similar = (sentence1[i], sentence2[i]) in pairs or (sentence2[i], sentence1[i]) in pairs or sentence1[i] == sentence2[i]
            if not similar:
                return False
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) {
            return false;
        }
        Set<String> pairs = new HashSet<>();
        for (List<String> pair : similarPairs) {
            pairs.add(pair.get(0) + "." + pair.get(1));
        }
        for (int i = 0; i < sentence1.length; ++i) {
            boolean similar =  pairs.contains(sentence1[i] + "." + sentence2[i]) || pairs.contains(sentence2[i] + "." + sentence1[i]) || sentence1[i].equals(sentence2[i]);
            if (!similar) {
                return false;
            }
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
