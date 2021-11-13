# [520. Detect Capital](https://leetcode.com/problems/detect-capital)

[中文文档](/solution/0500-0599/0520.Detect%20Capital/README.md)

## Description

<p>Given a word, you need to judge whether the usage of capitals in it is right or not.</p>

<p>We define the usage of capitals in a word to be right when one of the following cases holds:</p>

<ol>
	<li>All letters in this word are capitals, like &quot;USA&quot;.</li>
	<li>All letters in this word are not capitals, like &quot;leetcode&quot;.</li>
	<li>Only the first letter in this word is capital, like &quot;Google&quot;.</li>
</ol>

Otherwise, we define that this word doesn&#39;t use capitals in a right way.

<p>&nbsp;</p>

<p><b>Example 1:</b></p>

<pre>

<b>Input:</b> &quot;USA&quot;

<b>Output:</b> True

</pre>

<p>&nbsp;</p>

<p><b>Example 2:</b></p>

<pre>

<b>Input:</b> &quot;FlaG&quot;

<b>Output:</b> False

</pre>

<p>&nbsp;</p>

<p><b>Note:</b> The input will be a non-empty word consisting of uppercase and lowercase latin letters.</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def detectCapitalUse(self, word: str) -> bool:
        cnt = 0
        for c in word:
            if c.isupper():
                cnt += 1
        return cnt == 0 or cnt == len(word) or (cnt == 1 and word[0].isupper())
```

### **Java**

```java
class Solution {
    public boolean detectCapitalUse(String word) {
        int cnt = 0;
        for (char c : word.toCharArray()) {
            if (Character.isUpperCase(c)) {
                ++cnt;
            }
        }
        return cnt == 0 || cnt == word.length() || (cnt == 1 && Character.isUpperCase(word.charAt(0)));
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool detectCapitalUse(string word) {
        int cnt = 0;
        for (char c : word)
            if (isupper(c)) ++cnt;
        return cnt == 0 || cnt == word.size() || (cnt == 1 && isupper(word[0]));
    }
};
```

### **Go**

```go
func detectCapitalUse(word string) bool {
	cnt := 0
	for _, c := range word {
		if unicode.IsUpper(c) {
			cnt++
		}
	}
	return cnt == 0 || cnt == len(word) || (cnt == 1 && unicode.IsUpper(rune(word[0])))
}
```

### **...**

```

```

<!-- tabs:end -->
