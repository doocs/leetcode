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
            res |= (1 << (ord(c) - ord('a')))
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
        for (char c : sentence.toCharArray()) {
            s.add(c);
            if (s.size() == 26) {
                return true;
            }
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
        for (char c : sentence.toCharArray()) {
            res |= (1 << (c - 'a'));
            if (res == 0x3ffffff) {
                return true;
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkIfPangram(string sentence) {
        int res = 0;
        for (char c : sentence) {
            res |= (1 << (c - 'a'));
            if (res == 0x3ffffff) return true;
        }
        return false;
    }
};
```

### **Go**

```go
func checkIfPangram(sentence string) bool {
	res := 0
	for _, c := range sentence {
		res |= (1 << (c - 'a'))
		if res == 0x3ffffff {
			return true
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
