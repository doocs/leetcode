# [405. 数字转换为十六进制数](https://leetcode.cn/problems/convert-a-number-to-hexadecimal)

[English Version](/solution/0400-0499/0405.Convert%20a%20Number%20to%20Hexadecimal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用&nbsp;<a href="https://baike.baidu.com/item/%E8%A1%A5%E7%A0%81/6854613?fr=aladdin">补码运算</a>&nbsp;方法。</p>

<p><strong>注意:</strong></p>

<ol>
	<li>十六进制中所有字母(<code>a-f</code>)都必须是小写。</li>
	<li>十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符<code>&#39;0&#39;</code>来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。&nbsp;</li>
	<li>给定的数确保在32位有符号整数范围内。</li>
	<li><strong>不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。</strong></li>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
输入:
26

输出:
&quot;1a&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre>
输入:
-1

输出:
&quot;ffffffff&quot;
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

将数字的二进制位每 4 个一组转换为 16 进制即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def toHex(self, num: int) -> str:
        if num == 0:
            return '0'
        chars = '0123456789abcdef'
        s = []
        for i in range(7, -1, -1):
            x = (num >> (4 * i)) & 0xF
            if s or x != 0:
                s.append(chars[x])
        return ''.join(s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            int x = num & 15;
            if (x < 10) {
                sb.append(x);
            } else {
                sb.append((char) (x - 10 + 'a'));
            }
            num >>>= 4;
        }
        return sb.reverse().toString();
    }
}
```

```java
class Solution {
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 7; i >= 0; --i) {
            int x = (num >> (4 * i)) & 0xf;
            if (sb.length() > 0 || x != 0) {
                char c = x < 10 ? (char) (x + '0') : (char) (x - 10 + 'a');
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string toHex(int num) {
        if (num == 0) return "0";
        string s = "";
        for (int i = 7; i >= 0; --i) {
            int x = (num >> (4 * i)) & 0xf;
            if (s.size() > 0 || x != 0) {
                char c = x < 10 ? (char)(x + '0') : (char)(x - 10 + 'a');
                s += c;
            }
        }
        return s;
    }
};
```

### **Go**

```go
func toHex(num int) string {
	if num == 0 {
		return "0"
	}
	sb := &strings.Builder{}
	for i := 7; i >= 0; i-- {
		x := num >> (4 * i) & 0xf
		if x > 0 || sb.Len() > 0 {
			var c byte
			if x < 10 {
				c = '0' + byte(x)
			} else {
				c = 'a' + byte(x-10)
			}
			sb.WriteByte(c)
		}
	}
	return sb.String()
}
```

### **...**

```

```

<!-- tabs:end -->
