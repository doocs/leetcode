# [186. 翻转字符串里的单词 II](https://leetcode-cn.com/problems/reverse-words-in-a-string-ii)

[English Version](/solution/0100-0199/0186.Reverse%20Words%20in%20a%20String%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串，逐个翻转字符串中的每个单词。</p>

<p><strong>示例：</strong></p>

<pre><strong>输入: </strong>[&quot;t&quot;,&quot;h&quot;,&quot;e&quot;,&quot; &quot;,&quot;s&quot;,&quot;k&quot;,&quot;y&quot;,&quot; &quot;,&quot;i&quot;,&quot;s&quot;,&quot; &quot;,&quot;b&quot;,&quot;l&quot;,&quot;u&quot;,&quot;e&quot;]
<strong>输出: </strong>[&quot;b&quot;,&quot;l&quot;,&quot;u&quot;,&quot;e&quot;,&quot; &quot;,&quot;i&quot;,&quot;s&quot;,&quot; &quot;,&quot;s&quot;,&quot;k&quot;,&quot;y&quot;,&quot; &quot;,&quot;t&quot;,&quot;h&quot;,&quot;e&quot;]</pre>

<p><strong>注意：</strong></p>

<ul>
	<li>单词的定义是不包含空格的一系列字符</li>
	<li>输入字符串中不会包含前置或尾随的空格</li>
	<li>单词与单词之间永远是以单个空格隔开的</li>
</ul>

<p><strong>进阶：</strong>使用&nbsp;<em>O</em>(1) 额外空间复杂度的原地解法。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先翻转里面每个单词，最后再将字符串整体翻转。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def reverseWords(self, s: List[str]) -> None:
        """
        Do not return anything, modify s in-place instead.
        """
        def reverse(s, i, j):
            while i < j:
                s[i], s[j] = s[j], s[i]
                i += 1
                j -= 1

        i, j, n = 0, 0, len(s)
        while j < n:
            if s[j] == ' ':
                reverse(s, i, j - 1)
                i = j + 1
            elif j == n - 1:
                reverse(s, i, j)
            j += 1
        reverse(s, 0, n - 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public void reverseWords(char[] s) {
        int n = s.length;
        for (int i = 0, j = 0; j < n; ++j) {
            if (s[j] == ' ') {
                reverse(s, i, j - 1);
                i = j + 1;
            } else if (j == n - 1) {
                reverse(s, i, j);
            }
        }
        reverse(s, 0, n - 1);
    }

    private void reverse(char[] s, int i, int j) {
        for (; i < j; ++i, --j) {
            char t = s[i];
            s[i] = s[j];
            s[j] = t;
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    void reverseWords(vector<char>& s) {
        int n = s.size();
        for (int i = 0, j = 0; j < n; ++j) {
            if (s[j] == ' ') {
                reverse(s, i, j - 1);
                i = j + 1;
            } else if (j == n - 1) {
                reverse(s, i, j);
            }
        }
        reverse(s, 0, n - 1);
    }

    void reverse(vector<char>& s, int i, int j) {
        for (; i < j; ++i, --j) {
            swap(s[i], s[j]);
        }
    }
};
```

### **Go**

```go
func reverseWords(s []byte) {
	n := len(s)
	for i, j := 0, 0; j < n; j++ {
		if s[j] == ' ' {
			reverse(s, i, j-1)
			i = j + 1
		} else if j == n-1 {
			reverse(s, i, j)
		}
	}
	reverse(s, 0, n-1)
}

func reverse(s []byte, i, j int) {
	for i < j {
		s[i], s[j] = s[j], s[i]
		i++
		j--
	}
}
```

### **...**

```

```

<!-- tabs:end -->
