---
comments: true
difficulty: 简单
tags:
    - 位运算
    - 数学
    - 字符串
---

<!-- problem:start -->

# [405. 数字转换为十六进制数](https://leetcode.cn/problems/convert-a-number-to-hexadecimal)

[English Version](/solution/0400-0499/0405.Convert%20a%20Number%20to%20Hexadecimal/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用&nbsp;<a href="https://baike.baidu.com/item/%E8%A1%A5%E7%A0%81/6854613?fr=aladdin">补码运算</a>&nbsp;方法。</p>

<p>答案字符串中的所有字母都应该是小写字符，并且除了 0 本身之外，答案中不应该有任何前置零。</p>

<p><strong>注意: </strong>不允许使用任何由库提供的将数字直接转换或格式化为十六进制的方法来解决这个问题。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>num = 26
<b>输出：</b>"1a"
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>num = -1
<b>输出：</b>"ffffffff"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;= num &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 补码 $32$ 位整数转十六进制，负数亦按位解释。逐位模拟十进制转码需要处理符号，不如直接按 $4$ 位一组取出。
>
> 从高到低共 $8$ 组，每组与 $0\text{xF}$ 得到 $0$–$15$，再映射到字符表。跳过尚未写出的前导零；$0$ 单独返回。
>
> 从高位开始扫描，才能在不反转缓冲区的前提下丢掉前导零。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

```cpp
class Solution {
public:
    string toHex(int num) {
        if (num == 0) return "0";
        string s = "";
        for (int i = 7; i >= 0; --i) {
            int x = (num >> (4 * i)) & 0xf;
            if (s.size() > 0 || x != 0) {
                char c = x < 10 ? (char) (x + '0') : (char) (x - 10 + 'a');
                s += c;
            }
        }
        return s;
    }
};
```

#### Go

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- thinking:start -->

> **思考**
>
> 方法一已经按 $4$ 位分组。方法二用算术把 $0$–$9$、$10$–$15$ 转成字符，不再依赖字符表，逻辑相同。

<!-- thinking:end -->

<!-- tabs:start -->

#### Java

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
