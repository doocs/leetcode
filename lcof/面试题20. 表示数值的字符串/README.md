# [面试题 20. 表示数值的字符串](https://leetcode.cn/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>请实现一个函数用来判断字符串是否表示<strong>数值</strong>（包括整数和小数）。</p>

<p><strong>数值</strong>（按顺序）可以分成以下几个部分：</p>

<ol>
	<li>若干空格</li>
	<li>一个 <strong>小数</strong> 或者 <strong>整数</strong></li>
	<li>（可选）一个 <code>'e'</code> 或 <code>'E'</code> ，后面跟着一个 <strong>整数</strong></li>
	<li>若干空格</li>
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

<p>部分<strong>数值</strong>列举如下：</p>

<ul>
	<li><code>["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]</code></li>
</ul>

<p>部分<strong>非数值</strong>列举如下：</p>

<ul>
	<li><code>["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]</code></li>
</ul>

<p> </p>

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
<strong>输出：</strong>false</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>s = "    .1  "
<strong>输出：</strong>true
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 20</code></li>
	<li><code>s</code> 仅含英文字母（大写和小写），数字（<code>0-9</code>），加号 <code>'+'</code> ，减号 <code>'-'</code> ，空格 <code>' '</code> 或者点 <code>'.'</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：分类讨论**

我们先去除字符串 $s$ 首尾的空格，此时 $i$ 和 $j$ 分别指向字符串 $s$ 的第一个非空格字符和最后一个非空格字符。

然后我们维护以下几个变量，其中：

-   `digit`：表示是否出现过数字
-   `dot`：表示是否出现过点
-   `e`：表示是否出现过 `e` 或者 `E`

遍历 $s[i,..j]$ 范围内的每个字符，根据字符的类型进行分类讨论：

-   如果当前字符是 `+` 或者 `-`，那么该字符的前一个字符必须是 `e` 或者 `E`，或者空格，否则返回 `false`。
-   如果当前字符是数字，那么我们将 `digit` 置为 `true`。
-   如果当前字符是 `.`，那么该字符之前不能出现过 `.` 或者 `e`/`E`，否则返回 `false`，否则我们将 `dot` 置为 `true`。
-   如果当前字符是 `e` 或者 `E`，那么该字符之前不能出现过 `e`/`E`，并且必须出现过数字，否则返回 `false`，否则我们将 `e` 置为 `true`，并且将 `digit` 置为 `false`，表示 `e` 之后必须出现数字。
-   如果当前字符是其它字符，那么返回 `false`。

遍历结束后，我们返回 `digit`，即是否出现过数字。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isNumber(self, s: str) -> bool:
        i, j = 0, len(s) - 1
        while i < j and s[i] == " ":
            i += 1
        while i <= j and s[j] == " ":
            j -= 1
        if i > j:
            return False
        digit = dot = e = False
        while i <= j:
            if s[i] in "+-":
                if i and s[i - 1] not in " eE":
                    return False
            elif s[i].isdigit():
                digit = True
            elif s[i] == ".":
                if dot or e:
                    return False
                dot = True
            elif s[i] in "eE":
                if not digit or e:
                    return False
                e, digit = True, False
            else:
                return False
            i += 1
        return digit
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isNumber(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j && s.charAt(i) == ' ') {
            ++i;
        }
        while (i <= j && s.charAt(j) == ' ') {
            --j;
        }
        if (i > j) {
            return false;
        }
        boolean digit = false;
        boolean dot = false;
        boolean e = false;
        for (; i <= j; ++i) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                if (i > 0 && s.charAt(i - 1) != ' ' && s.charAt(i - 1) != 'e'
                    && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (Character.isDigit(s.charAt(i))) {
                digit = true;
            } else if (s.charAt(i) == '.') {
                if (dot || e) {
                    return false;
                }
                dot = true;
            } else if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                if (!digit || e) {
                    return false;
                }
                e = true;
                digit = false;
            } else {
                return false;
            }
        }
        return digit;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isNumber(string s) {
        int i = 0, j = s.size() - 1;
        while (i < j && s[i] == ' ') {
            ++i;
        }
        while (i <= j && s[j] == ' ') {
            --j;
        }
        if (i > j) {
            return false;
        }
        bool digit = false, dot = false, e = false;
        for (; i <= j; ++i) {
            if (s[i] == '+' || s[i] == '-') {
                if (i && s[i - 1] != ' ' && s[i - 1] != 'e' && s[i - 1] != 'E') {
                    return false;
                }
            } else if (isdigit(s[i])) {
                digit = true;
            } else if (s[i] == '.') {
                if (dot || e) {
                    return false;
                }
                dot = true;
            } else if (s[i] == 'e' || s[i] == 'E') {
                if (!digit || e) {
                    return false;
                }
                e = true;
                digit = false;
            } else {
                return false;
            }
        }
        return digit;
    }
};
```

### **Go**

```go
func isNumber(s string) bool {
	i, j := 0, len(s)-1
	for i < j && s[i] == ' ' {
		i++
	}
	for i <= j && s[j] == ' ' {
		j--
	}
	if i > j {
		return false
	}
	digit, dot, e := false, false, false
	for ; i <= j; i++ {
		if s[i] == '+' || s[i] == '-' {
			if i > 0 && s[i-1] != ' ' && s[i-1] != 'e' && s[i-1] != 'E' {
				return false
			}
		} else if s[i] >= '0' && s[i] <= '9' {
			digit = true
		} else if s[i] == '.' {
			if dot || e {
				return false
			}
			dot = true
		} else if s[i] == 'e' || s[i] == 'E' {
			if !digit || e {
				return false
			}
			digit, e = false, true
		} else {
			return false
		}
	}
	return digit
}
```

### **C#**

```cs
public class Solution {
    public bool IsNumber(string s) {
        int i = 0, j = s.Length - 1;
        while (i < j && s[i] == ' ') {
            ++i;
        }
        while (i <= j && s[j] == ' ') {
            --j;
        }
        if (i > j) {
            return false;
        }
        bool digit = false, dot = false, e = false;
        for (; i <= j; ++i) {
            if (s[i] == '+' || s[i] == '-') {
                if (i > 0 && s[i - 1] != ' ' && s[i - 1] != 'e' && s[i - 1] != 'E') {
                    return false;
                }
            } else if (s[i] >= '0' && s[i] <= '9') {
                digit = true;
            } else if (s[i] == '.') {
                if (dot || e) {
                    return false;
                }
                dot = true;
            } else if (s[i] == 'e' || s[i] == 'E') {
                if (!digit || e) {
                    return false;
                }
                e = true;
                digit = false;
            } else {
                return false;
            }
        }
        return digit;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
