# [面试题 67. 把字符串转换成整数](https://leetcode.cn/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。</p>

<p>&nbsp;</p>

<p>首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。</p>

<p>当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。</p>

<p>该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。</p>

<p>注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。</p>

<p>在任何情况下，若函数不能进行有效的转换时，请返回 0。</p>

<p><strong>说明：</strong></p>

<p>假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为&nbsp;[&minus;2<sup>31</sup>,&nbsp; 2<sup>31&nbsp;</sup>&minus; 1]。如果数值超过这个范围，请返回 &nbsp;INT_MAX (2<sup>31&nbsp;</sup>&minus; 1) 或&nbsp;INT_MIN (&minus;2<sup>31</sup>) 。</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:</strong> &quot;42&quot;
<strong>输出:</strong> 42
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> &quot;   -42&quot;
<strong>输出:</strong> -42
<strong>解释: </strong>第一个非空白字符为 &#39;-&#39;, 它是一个负号。
&nbsp;    我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre><strong>输入:</strong> &quot;4193 with words&quot;
<strong>输出:</strong> 4193
<strong>解释:</strong> 转换截止于数字 &#39;3&#39; ，因为它的下一个字符不为数字。
</pre>

<p><strong>示例&nbsp;4:</strong></p>

<pre><strong>输入:</strong> &quot;words and 987&quot;
<strong>输出:</strong> 0
<strong>解释:</strong> 第一个非空字符是 &#39;w&#39;, 但它不是数字或正、负号。
     因此无法执行有效的转换。</pre>

<p><strong>示例&nbsp;5:</strong></p>

<pre><strong>输入:</strong> &quot;-91283472332&quot;
<strong>输出:</strong> -2147483648
<strong>解释:</strong> 数字 &quot;-91283472332&quot; 超过 32 位有符号整数范围。 
&nbsp;    因此返回 INT_MIN (&minus;2<sup>31</sup>) 。
</pre>

<p>&nbsp;</p>

<p>注意：本题与主站 8 题相同：<a href="https://leetcode.cn/problems/string-to-integer-atoi/">https://leetcode.cn/problems/string-to-integer-atoi/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

遍历字符串，注意做溢出处理。

同[字符串转换整数 (atoi)](/solution/0000-0099/0008.String%20to%20Integer%20%28atoi%29/README.md)。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def strToInt(self, str: str) -> int:
        if not str:
            return 0
        n = len(str)
        if n == 0:
            return 0
        i = 0
        while str[i] == ' ':
            i += 1
            # 仅包含空格
            if i == n:
                return 0
        sign = -1 if str[i] == '-' else 1
        if str[i] in ['-', '+']:
            i += 1
        res, flag = 0, (2**31 - 1) // 10
        while i < n:
            # 非数字，跳出循环体
            if not str[i].isdigit():
                break
            c = int(str[i])
            # 溢出判断
            if res > flag or (res == flag and c > 7):
                return 2**31 - 1 if sign > 0 else -(2**31)
            res = res * 10 + c
            i += 1
        return sign * res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int strToInt(String str) {
        if (str == null) return 0;
        int n = str.length();
        if (n == 0) return 0;
        int i = 0;
        while (str.charAt(i) == ' ') {
            // 仅包含空格
            if (++i == n) return 0;
        }
        int sign = 1;
        if (str.charAt(i) == '-') sign = -1;
        if (str.charAt(i) == '-' || str.charAt(i) == '+') ++i;
        int res = 0, flag = Integer.MAX_VALUE / 10;
        for (; i < n; ++i) {
            // 非数字，跳出循环体
            if (str.charAt(i) < '0' || str.charAt(i) > '9') break;
            // 溢出判断
            if (res > flag || (res == flag) && str.charAt(i) > '7') return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            res = res * 10 + (str.charAt(i) - '0');
        }
        return sign * res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} str
 * @return {number}
 */
var strToInt = function (str) {
    let res = '';
    let l = 1;
    for (let i = 0; i < str.length; i++) {
        if (l && str[i] === ' ') continue;
        if (l && (str[i] === '+' || str[i] === '-')) {
            l = 0;
            res += str[i];
            continue;
        }
        if (str[i].match(/[0-9]/)) {
            l = 0;
            res += str[i];
        } else break;
    }
    res = isNaN(+res) ? 0 : +res;
    if (res > 2147483647) return 2147483647;
    if (res < -2147483648) return -2147483648;
    return res;
};
```

### **Go**

```go
func strToInt(str string) int {
	n, sign, i, x := len(str), 1, 0, 0

	// 去除开头的空格
	for i < n && str[i] == ' ' {
		i++
	}

	// 如果只有空格
	if i == n {
		return 0
	}

	// 负数
	if str[i] == '-' {
		sign = -1
	}

	// 跳过符号
	if str[i] == '-' || str[i] == '+' {
		i++
	}

	// 如果符号后紧跟的不是数字
	if i == n || str[i] < '0' || str[i] > '9' {
		return 0
	}

	// golang 在 64 位平台下 int 就是 int64，所以不用对 x 进行特殊处理
	for ; i < n && str[i] >= '0' && str[i] <= '9'; i++ {
		x = x*10 + int(str[i]) - '0'
		if x > math.MaxInt32 {
			break
		}
	}

	if x > math.MaxInt32 {
		if sign == 1 {
			return math.MaxInt32
		}
		return math.MinInt32
	}
	return sign * x
}
```

### **C#**

```cs
public class Solution {
    public int StrToInt(string str) {
        if (string.IsNullOrWhiteSpace(str))
        {
            return 0;
        }

        str = str.Trim();
        int currentIndex = 0;
        bool minus = false;
        if (str[currentIndex] == '+')
        {
            minus = false;
            currentIndex++;
        }
        else if (str[currentIndex] == '-')
        {
            minus = true;
            currentIndex++;
        }

        long result = 0;
        for (; currentIndex < str.Length; currentIndex++)
        {
            if (!char.IsDigit(str[currentIndex]))
            {
                break;
            }

            if (minus && result - 1 > int.MaxValue)
            {
                return int.MinValue;
            }
            else if (!minus && result > int.MaxValue)
            {
                return int.MaxValue;
            }

            result = result * 10 + (str[currentIndex] - '0');
        }

        if (minus && result - 1 > int.MaxValue)
        {
            return int.MinValue;
        }
        else if (!minus && result > int.MaxValue)
        {
            return int.MaxValue;
        }
        else
        {
            return (minus ? -1 : 1) * (int)result;
        }

    }
}
```

### **...**

```

```

<!-- tabs:end -->
