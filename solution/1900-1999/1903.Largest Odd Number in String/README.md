# [1903. 字符串中的最大奇数](https://leetcode.cn/problems/largest-odd-number-in-string)

[English Version](/solution/1900-1999/1903.Largest%20Odd%20Number%20in%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>num</code> ，表示一个大整数。请你在字符串 <code>num</code> 的所有 <strong>非空子字符串</strong> 中找出 <strong>值最大的奇数</strong> ，并以字符串形式返回。如果不存在奇数，则返回一个空字符串<em> </em><code>""</code><em> </em>。</p>

<p><strong>子字符串</strong> 是字符串中的一个连续的字符序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num = "52"
<strong>输出：</strong>"5"
<strong>解释：</strong>非空子字符串仅有 "5"、"2" 和 "52" 。"5" 是其中唯一的奇数。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num = "4206"
<strong>输出：</strong>""
<strong>解释：</strong>在 "4206" 中不存在奇数。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>num = "35427"
<strong>输出：</strong>"35427"
<strong>解释：</strong>"35427" 本身就是一个奇数。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= num.length <= 10<sup>5</sup></code></li>
	<li><code>num</code> 仅由数字组成且不含前导零</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

从后往前遍历字符串中的每个数字，遇到奇数则直接返回结果。若遍历结束仍未遇到奇数，返回空字符串。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def largestOddNumber(self, num: str) -> str:
        for i in range(len(num) - 1, -1, -1):
            if (int(num[i]) & 1) == 1:
                return num[: i + 1]
        return ''
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; --i) {
            int c = num.charAt(i) - '0';
            if ((c & 1) == 1) {
                return num.substring(0, i + 1);
            }
        }
        return "";
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} num
 * @return {string}
 */
var largestOddNumber = function (num) {
    let n = num.length;
    for (let j = n - 1; j >= 0; j--) {
        if (num.charAt(j) & (1 == 1)) {
            return num.slice(0, j + 1);
        }
    }
    return '';
};
```

### **C++**

```cpp
class Solution {
public:
    string largestOddNumber(string num) {
        for (int i = num.size() - 1; i >= 0; --i) {
            int c = num[i] - '0';
            if ((c & 1) == 1) {
                return num.substr(0, i + 1);
            }
        }
        return "";
    }
};
```

### **Go**

```go
func largestOddNumber(num string) string {
	for i := len(num) - 1; i >= 0; i-- {
		c := num[i] - '0'
		if (c & 1) == 1 {
			return num[:i+1]
		}
	}
	return ""
}
```

### **...**

```

```

<!-- tabs:end -->
