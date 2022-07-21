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

遍历字符串：

-   出现 `+`/`-` 时，位置必须是在第 0 位，或者 `e`/`E` 的后面一位
-   出现 `.` 时，在此之前不能出现 `.` 或者 `e`/`E`
-   出现 `e`/`E` 时，前面不能出现 `e`/`E`，并且必须出现过数字

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isNumber(self, s: str) -> bool:
        if not s or not s.strip():
            return False
        s = s.strip()
        find_num = find_dot = find_e = False
        for i in range(len(s)):
            if s[i] == '+' or s[i] == '-':
                if i != 0 and s[i - 1] != 'e' and s[i - 1] != 'E':
                    return False
            elif s[i] >= '0' and s[i] <= '9':
                find_num = True
            elif s[i] == '.':
                if find_dot or find_e:
                    return False
                find_dot = True
            elif s[i] == 'e' or s[i] == 'E':
                if not find_num or find_e:
                    return False
                find_e = True
                find_num = False
            else:
                return False
        return find_num
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isNumber(String s) {
        if (s == null || s.trim().length() == 0) {
            return false;
        }
        char[] chars = s.trim().toCharArray();
        boolean findNum = false;
        boolean findE = false;
        boolean findDot = false;
        for (int i = 0, n = chars.length; i < n; ++i) {
            if (chars[i] == '+' || chars[i] == '-') {
                if (i != 0 && chars[i - 1] != 'e' && chars[i - 1] != 'E') {
                    return false;
                }
            } else if (chars[i] >= '0' && chars[i] <= '9') {
                findNum = true;
            } else if (chars[i] == '.') {
                if (findDot || findE) {
                    return false;
                }
                findDot = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                if (findE || !findNum) {
                    return false;
                }
                findE = true;
                findNum = false; // 确保e之后也出现数
            } else {
                return false;
            }
        }
        return findNum;
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {boolean}
 */
var isNumber = function (s) {
    return s !== ' ' && !isNaN(+s);
};
```

### **C#**

```cs
public class Solution {
    public bool IsNumber(string s) {
        if (s == null || s.Trim() == null) {
            return false;
        }
        s = s.Trim();
        bool findNum = false, findDot = false, findE = false;
        for (int i = 0; i < s.Length; i++) {
            if (s[i] == '+' || s[i] == '-') {
                if (i != 0 && s[i-1] != 'e' && s[i-1] != 'E') {
                    return false;
                }
            } else if (s[i] >= '0' && s[i] <= '9') {
                findNum = true;
            } else if (s[i] == '.') {
                if (findDot || findE) {
                    return false;
                }
                findDot = true;
            } else if (s[i] == 'e' || s[i] == 'E') {
                if (!findNum || findE) {
                    return false;
                }
                findE = true;
                findNum = false;
            } else {
                return false;
            }
        }
        return findNum;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
