# [520. Detect Capital](https://leetcode.com/problems/detect-capital)

[中文文档](/solution/0500-0599/0520.Detect%20Capital/README.md)

## Description

<p>We define the usage of capitals in a word to be right when one of the following cases holds:</p>

<ul>
	<li>All letters in this word are capitals, like <code>&quot;USA&quot;</code>.</li>
	<li>All letters in this word are not capitals, like <code>&quot;leetcode&quot;</code>.</li>
	<li>Only the first letter in this word is capital, like <code>&quot;Google&quot;</code>.</li>
</ul>

<p>Given a string <code>word</code>, return <code>true</code> if the usage of capitals in it is right.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> word = "USA"
<strong>Output:</strong> true
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> word = "FlaG"
<strong>Output:</strong> false
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 100</code></li>
	<li><code>word</code> consists of lowercase and uppercase English letters.</li>
</ul>

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
        return cnt == 0 || cnt == word.length()
            || (cnt == 1 && Character.isUpperCase(word.charAt(0)));
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
