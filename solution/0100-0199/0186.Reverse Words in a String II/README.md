# [186. 反转字符串中的单词 II](https://leetcode.cn/problems/reverse-words-in-a-string-ii)

[English Version](/solution/0100-0199/0186.Reverse%20Words%20in%20a%20String%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符数组 <code>s</code> ，反转其中 <strong>单词</strong> 的顺序。</p>

<p><strong>单词</strong> 的定义为：单词是一个由非空格字符组成的序列。<code>s</code> 中的单词将会由单个空格分隔。</p>

<div class="original__bRMd">
<div>
<p>必须设计并实现 <strong>原地</strong> 解法来解决此问题，即不分配额外的空间。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
<strong>输出：</strong>["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = ["a"]
<strong>输出：</strong>["a"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 可以是一个英文字母（大写或小写）、数字、或是空格 <code>' '</code> 。</li>
	<li><code>s</code> 中至少存在一个单词</li>
	<li><code>s</code> 不含前导或尾随空格</li>
	<li>题目数据保证：<code>s</code> 中的每个单词都由单个空格分隔</li>
</ul>
</div>
</div>

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
