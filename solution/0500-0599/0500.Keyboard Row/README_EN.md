# [500. Keyboard Row](https://leetcode.com/problems/keyboard-row)

[中文文档](/solution/0500-0599/0500.Keyboard%20Row/README.md)

## Description

<p>Given a List of words, return the words that can be typed using letters of <b>alphabet</b> on only one row&#39;s of American keyboard like the image below.</p>

<p>&nbsp;</p>

![](./images/keyboard.png)

&nbsp;

<p><b>Example:</b></p>

<pre>

<b>Input:</b> [&quot;Hello&quot;, &quot;Alaska&quot;, &quot;Dad&quot;, &quot;Peace&quot;]

<b>Output:</b> [&quot;Alaska&quot;, &quot;Dad&quot;]

</pre>

<p>&nbsp;</p>

<p><b>Note:</b></p>

<ol>
	<li>You may use one character in the keyboard more than once.</li>
	<li>You may assume the input string will only contain letters of alphabet.</li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findWords(self, words: List[str]) -> List[str]:
        s1 = set('qwertyuiop')
        s2 = set('asdfghjkl')
        s3 = set('zxcvbnm')
        res = []
        for word in words:
            t = set(word.lower())
            if t <= s1 or t <= s2 or t <= s3:
                res.append(word)
        return res
```

### **Java**

```java
class Solution {
    public String[] findWords(String[] words) {
        String s1 = "qwertyuiopQWERTYUIOP";
        String s2 = "asdfghjklASDFGHJKL";
        String s3 = "zxcvbnmZXCVBNM";
        List<String> res = new ArrayList<>();
        for (String word : words) {
            int n1 = 0, n2 = 0, n3 = 0;
            int n = word.length();
            for (int i = 0; i < n; ++i) {
                if (s1.contains(String.valueOf(word.charAt(i)))) {
                    ++n1;
                } else if (s2.contains(String.valueOf(word.charAt(i)))) {
                    ++n2;
                } else {
                    ++n3;
                }
            }
            if (n1 == n || n2 == n || n3 == n) {
                res.add(word);
            }
        }
        return res.toArray(new String[0]);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
