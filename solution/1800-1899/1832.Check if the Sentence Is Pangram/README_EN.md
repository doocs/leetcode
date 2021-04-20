# [1832. Check if the Sentence Is Pangram](https://leetcode.com/problems/check-if-the-sentence-is-pangram)

[中文文档](/solution/1800-1899/1832.Check%20if%20the%20Sentence%20Is%20Pangram/README.md)

## Description

<p>A <strong>pangram</strong> is a sentence where every letter of the English alphabet appears at least once.</p>

<p>Given a string <code>sentence</code> containing only lowercase English letters, return<em> </em><code>true</code><em> if </em><code>sentence</code><em> is a <strong>pangram</strong>, or </em><code>false</code><em> otherwise.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;thequickbrownfoxjumpsoverthelazydog&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> sentence contains at least one of every letter of the English alphabet.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;leetcode&quot;
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= sentence.length &lt;= 1000</code></li>
	<li><code>sentence</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

Set:

```python
class Solution:
    def checkIfPangram(self, sentence: str) -> bool:
        return len(set(sentence)) == 26
```

Bit Manipulation:

```python
class Solution:
    def checkIfPangram(self, sentence: str) -> bool:
        res = 0
        for c in sentence:
            diff = ord(c) - ord('a')
            res |= (1 << diff)
            if res == 0x3ffffff:
                return True
        return False
```

### **Java**

HashSet:

```java
class Solution {
    public boolean checkIfPangram(String sentence) {
        Set<Character> s = new HashSet<>();
        for (int i = 0; i < sentence.length(); ++i) {
            s.add(sentence.charAt(i));
            if (s.size() == 26) return true;
        }
        return false;
    }
}
```

Bit Manipulation:

```java
class Solution {
    public boolean checkIfPangram(String sentence) {
        int res = 0;
        for (int i = 0; i < sentence.length(); ++i) {
            int diff = sentence.charAt(i) - 'a';
            res |= (1 << diff);
            if (res == 0x3ffffff) return true;
        }
        return false;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
