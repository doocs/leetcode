# [2264. 字符串中最大的 3 位相同数字](https://leetcode.cn/problems/largest-3-same-digit-number-in-string)

[English Version](/solution/2200-2299/2264.Largest%203-Same-Digit%20Number%20in%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>num</code> ，表示一个大整数。如果一个整数满足下述所有条件，则认为该整数是一个 <strong>优质整数</strong> ：</p>

<ul>
	<li>该整数是 <code>num</code> 的一个长度为 <code>3</code> 的 <strong>子字符串</strong> 。</li>
	<li>该整数由唯一一个数字重复 <code>3</code> 次组成。</li>
</ul>

<p>以字符串形式返回 <strong>最大的优质整数</strong> 。如果不存在满足要求的整数，则返回一个空字符串 <code>""</code> 。</p>

<p><strong>注意：</strong></p>

<ul>
	<li><strong>子字符串</strong> 是字符串中的一个连续字符序列。</li>
	<li><code>num</code> 或优质整数中可能存在 <strong>前导零</strong> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num = "6<em><strong>777</strong></em>133339"
<strong>输出：</strong>"777"
<strong>解释：</strong>num 中存在两个优质整数："777" 和 "333" 。
"777" 是最大的那个，所以返回 "777" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num = "23<em><strong>000</strong></em>19"
<strong>输出：</strong>"000"
<strong>解释：</strong>"000" 是唯一一个优质整数。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>num = "42352338"
<strong>输出：</strong>""
<strong>解释：</strong>不存在长度为 3 且仅由一个唯一数字组成的整数。因此，不存在优质整数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= num.length &lt;= 1000</code></li>
	<li><code>num</code> 仅由数字（<code>0</code> - <code>9</code>）组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def largestGoodInteger(self, num: str) -> str:
        for i in range(9, -1, -1):
            t = str(i) * 3
            if t in num:
                return t
        return ''
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String largestGoodInteger(String num) {
        for (int i = 9; i >= 0; i--) {
            String ret = String.valueOf(i).repeat(3);
            if (num.contains(ret)) {
                return ret;
            }
        }
        return "";
    }
}
```

### **TypeScript**

```ts
function largestGoodInteger(num: string): string {
    for (let i = 9; i >= 0; i--) {
        const c = String(i).repeat(3);
        if (num.includes(c)) return c;
    }
    return '';
}
```

### **C++**

```cpp
class Solution {
public:
    string largestGoodInteger(string num) {
        for (char i = '9'; i >= '0'; --i) {
            string t(3, i);
            if (num.find(t) != string::npos) return t;
        }
        return "";
    }
};
```

### **Go**

```go
func largestGoodInteger(num string) string {
	for c := '9'; c >= '0'; c-- {
		t := strings.Repeat(string(c), 3)
		if strings.Contains(num, t) {
			return t
		}
	}
	return ""
}
```

### **...**

```

```

<!-- tabs:end -->
