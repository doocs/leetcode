# [8. 字符串转换整数 (atoi)](https://leetcode.cn/problems/string-to-integer-atoi)

[English Version](/solution/0000-0099/0008.String%20to%20Integer%20%28atoi%29/README_EN.md)

<!-- tags:字符串 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>请你来实现一个&nbsp;<code>myAtoi(string s)</code>&nbsp;函数，使其能将字符串转换成一个 32 位有符号整数。</p>

<p>函数&nbsp;<code>myAtoi(string s)</code> 的算法如下：</p>

<ol>
	<li><strong>空格：</strong>读入字符串并丢弃无用的前导空格（<code>" "</code>）</li>
	<li><strong>符号：</strong>检查下一个字符（假设还未到字符末尾）为&nbsp;<code>'-'</code> 还是 <code>'+'</code>。如果两者都不存在，则假定结果为正。</li>
	<li><strong>转换：</strong>通过跳过前置零来读取该整数，直到遇到非数字字符或到达字符串的结尾。如果没有读取数字，则结果为0。</li>
	<li><b>舍入：</b>如果整数数超过 32 位有符号整数范围 <code>[−2<sup>31</sup>,&nbsp; 2<sup>31&nbsp;</sup>− 1]</code> ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 <code>−2<sup>31</sup></code> 的整数应该被舍入为 <code>−2<sup>31</sup></code> ，大于 <code>2<sup>31&nbsp;</sup>− 1</code> 的整数应该被舍入为 <code>2<sup>31&nbsp;</sup>− 1</code> 。</li>
</ol>

<p>返回整数作为最终结果。</p>

<p>&nbsp;</p>

<p><strong class="example">示例&nbsp;1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "42"</span></p>

<p><strong>输出：</strong><span class="example-io">42</span></p>

<p><strong>解释：</strong>加粗的字符串为已经读入的字符，插入符号是当前读取的字符。</p>

<pre>
带下划线线的字符是所读的内容，插入符号是当前读入位置。
第 1 步："42"（当前没有读入字符，因为没有前导空格）
         ^
第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
         ^
第 3 步："<u>42</u>"（读入 "42"）
           ^
</pre>
</div>

<p><strong class="example">示例&nbsp;2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = " -042"</span></p>

<p><strong>输出：</strong><span class="example-io">-42</span></p>

<p><strong>解释：</strong></p>

<pre>
第 1 步："<u><strong>   </strong></u>-042"（读入前导空格，但忽视掉）
            ^
第 2 步："   <u>-</u>042"（读入 '-' 字符，所以结果应该是负数）
             ^
第 3 步："   <u>-042</u>"（读入 "042"，在结果中忽略前导零）
               ^
</pre>
</div>

<p><strong class="example">示例&nbsp;3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "</span>1337c0d3<span class="example-io">"</span></p>

<p><strong>输出：</strong><span class="example-io">1337</span></p>

<p><strong>解释：</strong></p>

<pre>
第 1 步："1337c0d3"（当前没有读入字符，因为没有前导空格）
         ^
第 2 步："1337c0d3"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
         ^
第 3 步："1337c0d3"（读入 "1337"；由于下一个字符不是一个数字，所以读入停止）
             ^
</pre>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "0-1"</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<pre>
第 1 步："0-1" (当前没有读入字符，因为没有前导空格)
         ^
第 2 步："0-1" (当前没有读入字符，因为这里不存在 '-' 或者 '+')
         ^
第 3 步："<u>0</u>-1" (读入 "0"；由于下一个字符不是一个数字，所以读入停止)
          ^
</pre>
</div>

<p><strong class="example">示例 5：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "words and 987"</span></p>

<p><strong>输出：</strong><span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>读取在第一个非数字字符“w”处停止。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 200</code></li>
	<li><code>s</code> 由英文字母（大写和小写）、数字（<code>0-9</code>）、<code>' '</code>、<code>'+'</code>、<code>'-'</code> 和 <code>'.'</code> 组成</li>
</ul>

## 解法

### 方法一：遍历字符串

我们首先判断字符串是否为空，如果是，直接返回 $0$。

否则，我们需要遍历字符串，跳过前导空格，判断第一个非空格字符是否为正负号。

接着遍历后面的字符，如果是数字，我们判断添加该数字是否会导致整数溢出，如果会，我们根据正负号返回结果。否则我们将数字添加到结果中。继续遍历后面的字符，直到遇到非数字字符或者遍历结束。

遍历结束后，我们根据正负号返回结果。

时间复杂度 $O(n)$，其中 $n$ 为字符串的长度。我们只需要依次处理所有字符。空间复杂度 $O(1)$。

同[面试题 67. 把字符串转换成整数](https://github.com/doocs/leetcode/blob/main/lcof/面试题67.%20把字符串转换成整数/README.md)。

<!-- tabs:start -->

```python
class Solution:
    def myAtoi(self, s: str) -> int:
        if not s:
            return 0
        n = len(s)
        if n == 0:
            return 0
        i = 0
        while s[i] == ' ':
            i += 1
            # 仅包含空格
            if i == n:
                return 0
        sign = -1 if s[i] == '-' else 1
        if s[i] in ['-', '+']:
            i += 1
        res, flag = 0, (2**31 - 1) // 10
        while i < n:
            # 非数字，跳出循环体
            if not s[i].isdigit():
                break
            c = int(s[i])
            # 溢出判断
            if res > flag or (res == flag and c > 7):
                return 2**31 - 1 if sign > 0 else -(2**31)
            res = res * 10 + c
            i += 1
        return sign * res
```

```java
class Solution {
    public int myAtoi(String s) {
        if (s == null) return 0;
        int n = s.length();
        if (n == 0) return 0;
        int i = 0;
        while (s.charAt(i) == ' ') {
            // 仅包含空格
            if (++i == n) return 0;
        }
        int sign = 1;
        if (s.charAt(i) == '-') sign = -1;
        if (s.charAt(i) == '-' || s.charAt(i) == '+') ++i;
        int res = 0, flag = Integer.MAX_VALUE / 10;
        for (; i < n; ++i) {
            // 非数字，跳出循环体
            if (s.charAt(i) < '0' || s.charAt(i) > '9') break;
            // 溢出判断
            if (res > flag || (res == flag && s.charAt(i) > '7'))
                return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            res = res * 10 + (s.charAt(i) - '0');
        }
        return sign * res;
    }
}
```

```go
func myAtoi(s string) int {
	i, n := 0, len(s)
	num := 0

	for i < n && s[i] == ' ' {
		i++
	}
	if i == n {
		return 0
	}

	sign := 1
	if s[i] == '-' {
		sign = -1
		i++
	} else if s[i] == '+' {
		i++
	}

	for i < n && s[i] >= '0' && s[i] <= '9' {
		num = num*10 + int(s[i]-'0')
		i++
		if num > math.MaxInt32 {
			break
		}
	}

	if num > math.MaxInt32 {
		if sign == -1 {
			return math.MinInt32
		}
		return math.MaxInt32
	}
	return sign * num
}
```

```js
const myAtoi = function (str) {
    str = str.trim();
    if (!str) return 0;
    let isPositive = 1;
    let i = 0,
        ans = 0;
    if (str[i] === '+') {
        isPositive = 1;
        i++;
    } else if (str[i] === '-') {
        isPositive = 0;
        i++;
    }
    for (; i < str.length; i++) {
        let t = str.charCodeAt(i) - 48;
        if (t > 9 || t < 0) break;
        if (ans > 2147483647 / 10 || ans > (2147483647 - t) / 10) {
            return isPositive ? 2147483647 : -2147483648;
        } else {
            ans = ans * 10 + t;
        }
    }
    return isPositive ? ans : -ans;
};
```

```cs
﻿// https://leetcode.com/problems/string-to-integer-atoi/

public partial class Solution
{
    public int MyAtoi(string str)
    {
        int i = 0;
        long result = 0;
        bool minus = false;
        while (i < str.Length && char.IsWhiteSpace(str[i]))
        {
            ++i;
        }
        if (i < str.Length)
        {
            if (str[i] == '+')
            {
                ++i;
            }
            else if (str[i] == '-')
            {
                minus = true;
                ++i;
            }
        }
        while (i < str.Length && char.IsDigit(str[i]))
        {
            result = result * 10 + str[i] - '0';
            if (result > int.MaxValue)
            {
                break;
            }
            ++i;
        }
        if (minus) result = -result;
        if (result > int.MaxValue)
        {
            result = int.MaxValue;
        }
        if (result < int.MinValue)
        {
            result = int.MinValue;
        }
        return (int)result;
    }
}
```

```php
class Solution {
    /**
     * @param string $s
     * @return int
     */

    function myAtoi($s) {
        $s = str_replace('e', 'x', $s);
        if (intval($s) < pow(-2, 31)) {
            return -2147483648;
        }
        if (intval($s) > pow(2, 31) - 1) {
            return 2147483647;
        }
        return intval($s);
    }
}
```

<!-- tabs:end -->

<!-- end -->
