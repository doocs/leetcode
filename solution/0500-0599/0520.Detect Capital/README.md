# [520. 检测大写字母](https://leetcode-cn.com/problems/detect-capital)

[English Version](/solution/0500-0599/0520.Detect%20Capital/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个单词，你需要判断单词的大写使用是否正确。</p>

<p>我们定义，在以下情况时，单词的大写用法是正确的：</p>

<ol>
	<li>全部字母都是大写，比如&quot;USA&quot;。</li>
	<li>单词中所有字母都不是大写，比如&quot;leetcode&quot;。</li>
	<li>如果单词不只含有一个字母，只有首字母大写，&nbsp;比如&nbsp;&quot;Google&quot;。</li>
</ol>

<p>否则，我们定义这个单词没有正确使用大写字母。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> &quot;USA&quot;
<strong>输出:</strong> True
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> &quot;FlaG&quot;
<strong>输出:</strong> False
</pre>

<p><strong>注意:</strong> 输入是由大写和小写拉丁字母组成的非空单词。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
