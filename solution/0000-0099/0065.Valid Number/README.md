# [65. 有效数字](https://leetcode.cn/problems/valid-number)

[English Version](/solution/0000-0099/0065.Valid%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>有效数字</strong>（按顺序）可以分成以下几个部分：</p>

<ol>
	<li>一个 <strong>小数</strong> 或者 <strong>整数</strong></li>
	<li>（可选）一个 <code>'e'</code> 或 <code>'E'</code> ，后面跟着一个 <strong>整数</strong></li>
</ol>

<p><strong>小数</strong>（按顺序）可以分成以下几个部分：</p>

<ol>
	<li>（可选）一个符号字符（<code>'+'</code> 或 <code>'-'</code>）</li>
	<li>下述格式之一：
	<ol>
		<li>至少一位数字，后面跟着一个点 <code>'.'</code></li>
		<li>至少一位数字，后面跟着一个点 <code>'.'</code> ，后面再跟着至少一位数字</li>
		<li>一个点 <code>'.'</code> ，后面跟着至少一位数字</li>
	</ol>
	</li>
</ol>

<p><strong>整数</strong>（按顺序）可以分成以下几个部分：</p>

<ol>
	<li>（可选）一个符号字符（<code>'+'</code> 或 <code>'-'</code>）</li>
	<li>至少一位数字</li>
</ol>

<p>部分有效数字列举如下：<code>["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]</code></p>

<p>部分无效数字列举如下：<code>["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]</code></p>

<p>给你一个字符串 <code>s</code> ，如果 <code>s</code> 是一个 <strong>有效数字</strong> ，请返回 <code>true</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "0"
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "e"
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "."
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 20</code></li>
	<li><code>s</code> 仅含英文字母（大写和小写），数字（<code>0-9</code>），加号 <code>'+'</code> ，减号 <code>'-'</code> ，或者点 <code>'.'</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：分情况讨论**

首先，我们判断字符串是否以正负号开头，如果是，将指针 $i$ 向后移动一位。如果此时指针 $i$ 已经到达字符串末尾，说明字符串只有一个正负号，返回 `false`。

如果当前指针 $i$ 指向的字符是小数点，并且小数点后面没有数字，或者小数点后是一个 `e` 或 `E`，返回 `false`。

接着，我们用两个变量 $dot$ 和 $e$ 分别记录小数点和 `e` 或 `E` 的个数。

用指针 $j$ 指向当前字符：

-   如果当前字符是小数点，并且此前出现过小数点或者 `e` 或 `E`，返回 `false`。否则，我们将 $dot$ 加一；
-   如果当前字符是 `e` 或 `E`，并且此前出现过 `e` 或 `E`，或者当前字符是开头或结尾，返回 `false`。否则，我们将 $e$ 加一；然后判断下一个字符是否是正负号，如果是，将指针 $j$ 向后移动一位。如果此时指针 $j$ 已经到达字符串末尾，返回 `false`；
-   如果当前字符不是数字，返回 `false`。

遍历完字符串后，返回 `true`。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isNumber(self, s: str) -> bool:
        n = len(s)
        i = 0
        if s[i] in '+-':
            i += 1
        if i == n:
            return False
        if s[i] == '.' and (i + 1 == n or s[i + 1] in 'eE'):
            return False
        dot = e = 0
        j = i
        while j < n:
            if s[j] == '.':
                if e or dot:
                    return False
                dot += 1
            elif s[j] in 'eE':
                if e or j == i or j == n - 1:
                    return False
                e += 1
                if s[j + 1] in '+-':
                    j += 1
                    if j == n - 1:
                        return False
            elif not s[j].isnumeric():
                return False
            j += 1
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isNumber(String s) {
        int n = s.length();
        int i = 0;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            ++i;
        }
        if (i == n) {
            return false;
        }
        if (s.charAt(i) == '.'
            && (i + 1 == n || s.charAt(i + 1) == 'e' || s.charAt(i + 1) == 'E')) {
            return false;
        }
        int dot = 0, e = 0;
        for (int j = i; j < n; ++j) {
            if (s.charAt(j) == '.') {
                if (e > 0 || dot > 0) {
                    return false;
                }
                ++dot;
            } else if (s.charAt(j) == 'e' || s.charAt(j) == 'E') {
                if (e > 0 || j == i || j == n - 1) {
                    return false;
                }
                ++e;
                if (s.charAt(j + 1) == '+' || s.charAt(j + 1) == '-') {
                    if (++j == n - 1) {
                        return false;
                    }
                }
            } else if (s.charAt(j) < '0' || s.charAt(j) > '9') {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isNumber(string s) {
        int n = s.size();
        int i = 0;
        if (s[i] == '+' || s[i] == '-') ++i;
        if (i == n) return false;
        if (s[i] == '.' && (i + 1 == n || s[i + 1] == 'e' || s[i + 1] == 'E')) return false;
        int dot = 0, e = 0;
        for (int j = i; j < n; ++j) {
            if (s[j] == '.') {
                if (e || dot) return false;
                ++dot;
            } else if (s[j] == 'e' || s[j] == 'E') {
                if (e || j == i || j == n - 1) return false;
                ++e;
                if (s[j + 1] == '+' || s[j + 1] == '-') {
                    if (++j == n - 1) return false;
                }
            } else if (s[j] < '0' || s[j] > '9') return false;
        }
        return true;
    }
};
```

### **Go**

```go
func isNumber(s string) bool {
	i, n := 0, len(s)
	if s[i] == '+' || s[i] == '-' {
		i++
	}
	if i == n {
		return false
	}
	if s[i] == '.' && (i+1 == n || s[i+1] == 'e' || s[i+1] == 'E') {
		return false
	}
	var dot, e int
	for j := i; j < n; j++ {
		if s[j] == '.' {
			if e > 0 || dot > 0 {
				return false
			}
			dot++
		} else if s[j] == 'e' || s[j] == 'E' {
			if e > 0 || j == i || j == n-1 {
				return false
			}
			e++
			if s[j+1] == '+' || s[j+1] == '-' {
				j++
				if j == n-1 {
					return false
				}
			}
		} else if s[j] < '0' || s[j] > '9' {
			return false
		}
	}
	return true
}
```

### **C#**

```cs
using System.Text.RegularExpressions;

public class Solution {
    private readonly Regex _isNumber_Regex = new Regex(@"^\s*[+-]?(\d+(\.\d*)?|\.\d+)([Ee][+-]?\d+)?\s*$");

    public bool IsNumber(string s) {
        return _isNumber_Regex.IsMatch(s);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
