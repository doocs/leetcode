# [434. 字符串中的单词数](https://leetcode.cn/problems/number-of-segments-in-a-string)

[English Version](/solution/0400-0499/0434.Number%20of%20Segments%20in%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。</p>

<p>请注意，你可以假定字符串里不包括任何不可打印的字符。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> &quot;Hello, my name is John&quot;
<strong>输出:</strong> 5
<strong>解释: </strong>这里的单词是指连续的不是空格的字符，所以 &quot;Hello,&quot; 算作 1 个单词。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

split 切分字符串，或者直接遍历计数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countSegments(self, s: str) -> int:
        return len(s.split())
```

```python
class Solution:
    def countSegments(self, s: str) -> int:
        res = 0
        for i in range(len(s)):
            if s[i] != ' ' and (i == 0 or s[i - 1] == ' '):
                res += 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countSegments(String s) {
        int res = 0;
        for (String t : s.split(" ")) {
            if (!"".equals(t)) {
                ++res;
            }
        }
        return res;
    }
}
```

```java
class Solution {
    public int countSegments(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != ' ' && (i == 0 || s.charAt(i - 1) == ' ')) {
                ++res;
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countSegments(string s) {
        int res = 0;
        for (int i = 0; i < s.size(); ++i) {
            if (s[i] != ' ' && (i == 0 || s[i - 1] == ' '))
                ++res;
        }
        return res;
    }
};
```

### **Go**

```go
func countSegments(s string) int {
	res := 0
	for i, c := range s {
		if c != ' ' && (i == 0 || s[i-1] == ' ') {
			res++
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
