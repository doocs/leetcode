# [1935. Maximum Number of Words You Can Type](https://leetcode.com/problems/maximum-number-of-words-you-can-type)

[中文文档](/solution/1900-1999/1935.Maximum%20Number%20of%20Words%20You%20Can%20Type/README.md)

## Description

<p>There is a malfunctioning keyboard where some letter keys do not work. All other keys on the keyboard work properly.</p>

<p>Given a string <code>text</code> of words separated by a single space (no leading or trailing spaces) and a string <code>brokenLetters</code> of all <strong>distinct</strong> letter keys that are broken, return <em>the <strong>number of words</strong> in</em> <code>text</code> <em>you can fully type using this keyboard</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;hello world&quot;, brokenLetters = &quot;ad&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> We cannot type &quot;world&quot; because the &#39;d&#39; key is broken.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;leet code&quot;, brokenLetters = &quot;lt&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> We cannot type &quot;leet&quot; because the &#39;l&#39; and &#39;t&#39; keys are broken.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;leet code&quot;, brokenLetters = &quot;e&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> We cannot type either word because the &#39;e&#39; key is broken.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= brokenLetters.length &lt;= 26</code></li>
	<li><code>text</code> consists of words separated by a single space without any leading or trailing spaces.</li>
	<li>Each word only consists of lowercase English letters.</li>
	<li><code>brokenLetters</code> consists of <strong>distinct</strong> lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
