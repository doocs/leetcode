# [1736. 替换隐藏数字得到的最晚时间](https://leetcode.cn/problems/latest-time-by-replacing-hidden-digits)

[English Version](/solution/1700-1799/1736.Latest%20Time%20by%20Replacing%20Hidden%20Digits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>time</code> ，格式为 <code> hh:mm</code>（小时：分钟），其中某几位数字被隐藏（用 <code>?</code> 表示）。</p>

<p>有效的时间为 <code>00:00</code> 到 <code>23:59</code> 之间的所有时间，包括 <code>00:00</code> 和 <code>23:59</code> 。</p>

<p>替换 <code>time</code> 中隐藏的数字，返回你可以得到的最晚有效时间。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>time = "2?:?0"
<strong>输出：</strong>"23:50"
<strong>解释：</strong>以数字 '2' 开头的最晚一小时是 23 ，以 '0' 结尾的最晚一分钟是 50 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>time = "0?:3?"
<strong>输出：</strong>"09:39"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>time = "1?:22"
<strong>输出：</strong>"19:22"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>time</code> 的格式为 <code>hh:mm</code></li>
	<li>题目数据保证你可以由输入的字符串生成有效的时间</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

我们依次处理字符串的每一位，处理的规则如下：

1. 第一位：若第二位的值已确定，且值落在区间 $[4, 9]$ 内，那么第一位只能取 $1$，否则第一位最大取 $2$；
1. 第二位：若第一位的值已确定，且值为 $2$，那么第二位最大取 $3$，否则第二位最大取 $9$；
1. 第三位：第三位最大取 $5$；
1. 第四位：第四位最大取 $9$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumTime(self, time: str) -> str:
        t = list(time)
        if t[0] == '?':
            t[0] = '1' if '4' <= t[1] <= '9' else '2'
        if t[1] == '?':
            t[1] = '3' if t[0] == '2' else '9'
        if t[3] == '?':
            t[3] = '5'
        if t[4] == '?':
            t[4] = '9'
        return ''.join(t)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String maximumTime(String time) {
        char[] t = time.toCharArray();
        if (t[0] == '?') {
            t[0] = t[1] >= '4' && t[1] <= '9' ? '1' : '2';
        }
        if (t[1] == '?') {
            t[1] = t[0] == '2' ? '3' : '9';
        }
        if (t[3] == '?') {
            t[3] = '5';
        }
        if (t[4] == '?') {
            t[4] = '9';
        }
        return new String(t);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string maximumTime(string time) {
        if (time[0] == '?') {
            time[0] = (time[1] >= '4' && time[1] <= '9') ? '1' : '2';
        }
        if (time[1] == '?') {
            time[1] = (time[0] == '2') ? '3' : '9';
        }
        if (time[3] == '?') {
            time[3] = '5';
        }
        if (time[4] == '?') {
            time[4] = '9';
        }
        return time;
    }
};
```

### **Go**

```go
func maximumTime(time string) string {
	t := []byte(time)
	if t[0] == '?' {
		if t[1] >= '4' && t[1] <= '9' {
			t[0] = '1'
		} else {
			t[0] = '2'
		}
	}
	if t[1] == '?' {
		if t[0] == '2' {
			t[1] = '3'
		} else {
			t[1] = '9'
		}
	}
	if t[3] == '?' {
		t[3] = '5'
	}
	if t[4] == '?' {
		t[4] = '9'
	}
	return string(t)
}
```

### **JavaScript**

```js
/**
 * @param {string} time
 * @return {string}
 */
var maximumTime = function (time) {
    const t = Array.from(time);
    if (t[0] === '?') {
        t[0] = t[1] >= '4' && t[1] <= '9' ? '1' : '2';
    }
    if (t[1] === '?') {
        t[1] = t[0] == '2' ? '3' : '9';
    }
    if (t[3] === '?') {
        t[3] = '5';
    }
    if (t[4] === '?') {
        t[4] = '9';
    }
    return t.join('');
};
```

### **...**

```

```

<!-- tabs:end -->
