# [1832. 判断句子是否为全字母句](https://leetcode.cn/problems/check-if-the-sentence-is-pangram)

[English Version](/solution/1800-1899/1832.Check%20if%20the%20Sentence%20Is%20Pangram/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>全字母句</strong> 指包含英语字母表中每个字母至少一次的句子。</p>

<p>给你一个仅由小写英文字母组成的字符串 <code>sentence</code> ，请你判断 <code>sentence</code> 是否为 <strong>全字母句</strong> 。</p>

<p>如果是，返回<em> </em><code>true</code> ；否则，返回<em> </em><code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>sentence = "thequickbrownfoxjumpsoverthelazydog"
<strong>输出：</strong>true
<strong>解释：</strong><code>sentence</code> 包含英语字母表中每个字母至少一次。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>sentence = "leetcode"
<strong>输出：</strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= sentence.length <= 1000</code></li>
	<li><code>sentence</code> 由小写英语字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

转为 Set，判断 Set 长度是否等于 26。若是，说明是全字母句。也可以使用位运算。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

集合去重并计数：

```python
class Solution:
    def checkIfPangram(self, sentence: str) -> bool:
        return len(set(sentence)) == 26
```

位运算：

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

集合去重并计数：

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

位运算：

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
