# [953. Verifying an Alien Dictionary](https://leetcode.com/problems/verifying-an-alien-dictionary)

[中文文档](/solution/0900-0999/0953.Verifying%20an%20Alien%20Dictionary/README.md)

## Description

<p>In an alien language, surprisingly they also use english lowercase letters, but possibly&nbsp;in a different <code>order</code>. The&nbsp;<code>order</code> of the alphabet&nbsp;is some permutation&nbsp;of lowercase letters.</p>

<p>Given a sequence of <code>words</code>&nbsp;written in the alien language,&nbsp;and the <code>order</code> of the alphabet,&nbsp;return <code>true</code> if and only if the given <code>words</code>&nbsp;are sorted lexicographicaly in this alien language.</p>
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;hello&quot;,&quot;leetcode&quot;], order = &quot;hlabcdefgijkmnopqrstuvwxyz&quot;
<strong>Output:</strong> true
<strong>Explanation: </strong>As &#39;h&#39; comes before &#39;l&#39; in this language, then the sequence is sorted.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;word&quot;,&quot;world&quot;,&quot;row&quot;], order = &quot;worldabcefghijkmnpqstuvxyz&quot;
<strong>Output:</strong> false
<strong>Explanation: </strong>As &#39;d&#39; comes after &#39;l&#39; in this language, then words[0] &gt; words[1], hence the sequence is unsorted.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;apple&quot;,&quot;app&quot;], order = &quot;abcdefghijklmnopqrstuvwxyz&quot;
<strong>Output:</strong> false
<strong>Explanation: </strong>The first three characters &quot;app&quot; match, and the second string is shorter (in size.) According to lexicographical rules &quot;apple&quot; &gt; &quot;app&quot;, because &#39;l&#39; &gt; &#39;&empty;&#39;, where &#39;&empty;&#39; is defined as the blank character which is less than any other character (<a href="https://en.wikipedia.org/wiki/Lexicographical_order" target="_blank">More info</a>).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 20</code></li>
	<li><code>order.length == 26</code></li>
	<li>All characters in <code>words[i]</code> and <code>order</code> are English lowercase letters.</li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        index = {v: k for k, v in enumerate(order)}
        for i in range(len(words) - 1):
            word1, word2 = words[i], words[i + 1]
            len1, len2 = len(word1), len(word2)
            flag = True
            for j in range(min(len1, len2)):
                diff = index[word1[j]] - index[word2[j]]
                if diff > 0:
                    return False
                if diff < 0:
                    flag = False
                    break
            if flag and len1 > len2:
                return False
        return True
```

### **Java**

```java
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] index = new int[26];
        for (int i = 0; i < 26; ++i) {
            index[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0, m = words.length; i < m - 1; ++i) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int len1 = word1.length();
            int len2 = word2.length();
            boolean flag = true;
            for (int j = 0, n = Math.min(len1, len2); j < n && flag; ++j) {
                int diff = index[word1.charAt(j) - 'a'] - index[word2.charAt(j) - 'a'];
                if (diff > 0) return false;
                if (diff < 0) flag = false;
            }
            if (flag && len1 > len2) return false;
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
