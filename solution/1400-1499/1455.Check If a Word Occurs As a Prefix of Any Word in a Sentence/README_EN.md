# [1455. Check If a Word Occurs As a Prefix of Any Word in a Sentence](https://leetcode.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence)

[中文文档](/solution/1400-1499/1455.Check%20If%20a%20Word%20Occurs%20As%20a%20Prefix%20of%20Any%20Word%20in%20a%20Sentence/README.md)

## Description

<p>Given a <code>sentence</code>&nbsp;that consists of some words separated by a&nbsp;<strong>single space</strong>, and a <code>searchWord</code>.</p>

<p>You have to check if <code>searchWord</code> is a prefix of any word in <code>sentence</code>.</p>

<p>Return <em>the index of the word</em> in <code>sentence</code> where <code>searchWord</code> is a prefix of this word (<strong>1-indexed</strong>).</p>

<p>If <code>searchWord</code> is&nbsp;a prefix of more than one word, return the index of the first word <strong>(minimum index)</strong>. If there is no such word return <strong>-1</strong>.</p>

<p>A <strong>prefix</strong> of a string&nbsp;<code>S</code> is any leading contiguous substring of <code>S</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;i love eating burger&quot;, searchWord = &quot;burg&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> &quot;burg&quot; is prefix of &quot;burger&quot; which is the 4th word in the sentence.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;this problem is an easy problem&quot;, searchWord = &quot;pro&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> &quot;pro&quot; is prefix of &quot;problem&quot; which is the 2nd and the 6th word in the sentence, but we return 2 as it&#39;s the minimal index.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;i am tired&quot;, searchWord = &quot;you&quot;
<strong>Output:</strong> -1
<strong>Explanation:</strong> &quot;you&quot; is not a prefix of any word in the sentence.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;i use triple pillow&quot;, searchWord = &quot;pill&quot;
<strong>Output:</strong> 4
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;hello from the other side&quot;, searchWord = &quot;they&quot;
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= sentence.length &lt;= 100</code></li>
	<li><code>1 &lt;= searchWord.length &lt;= 10</code></li>
	<li><code>sentence</code> consists of lowercase English letters and spaces.</li>
	<li><code>searchWord</code>&nbsp;consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isPrefixOfWord(self, sentence: str, searchWord: str) -> int:
        words = sentence.split(' ')
        i, n = 0, len(words)
        while i < n:
            word = words[i]
            if word[: len(searchWord)] == searchWord:
                return i + 1
            i += 1
        return -1
```

### **Java**

```java
class Solution {
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] words = sentence.split(" ");
        int i = 0, n = words.length;
        for (; i < n; ++i) {
            if (words[i].indexOf(searchWord) == 0) {
                return i + 1;
            }
        }
        return -1;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
