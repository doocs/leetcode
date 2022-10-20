# [984. 不含 AAA 或 BBB 的字符串](https://leetcode.cn/problems/string-without-aaa-or-bbb)

[English Version](/solution/0900-0999/0984.String%20Without%20AAA%20or%20BBB/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个整数 <code>a</code>&nbsp;和 <code>b</code>&nbsp;，返回&nbsp;<strong>任意</strong>&nbsp;字符串 <code>s</code>&nbsp;，要求满足：</p>

<ul>
	<li><code>s</code>&nbsp;的长度为 <code>a + b</code>，且正好包含&nbsp;<code>a</code>&nbsp;个 <code>'a'</code>&nbsp;字母与&nbsp;<code>b</code> 个 <code>'b'</code>&nbsp;字母；</li>
	<li>子串&nbsp;<code>'aaa'</code>&nbsp;没有出现在 <code>s</code>&nbsp;中；</li>
	<li>子串&nbsp;<code>'bbb'</code> 没有出现在 <code>s</code>&nbsp;中。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>a = 1, b = 2
<strong>输出：</strong>"abb"
<strong>解释：</strong>"abb", "bab" 和 "bba" 都是正确答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>a = 4, b = 1
<strong>输出：</strong>"aabaa"</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= a, b&nbsp;&lt;= 100</code></li>
	<li>对于给定的 <code>a</code> 和 <code>b</code>，保证存在满足要求的 <code>s</code>&nbsp;</li>
</ul>
<span style="display:block"><span style="height:0px"><span style="position:absolute">​​​</span></span></span>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 构造**

循环构造字符串，当 $a$ 和 $b$ 都大于 `0` 时：

1. 如果 $a\gt b$，添加字符串 "aab"
1. 如果 $b\gt a$，添加字符串 "bba"
1. 如果 $a=b$，添加字符串 "ab"

循环结束，若 $a$ 有剩余，则添加 $a$ 个字符串 "a"；若 $b$ 有剩余，则添加 $b$ 个字符串 "b"。

时间复杂度 $O(a+b)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def strWithout3a3b(self, a: int, b: int) -> str:
        ans = []
        while a and b:
            if a > b:
                ans.append('aab')
                a, b = a - 2, b - 1
            elif a < b:
                ans.append('bba')
                a, b = a - 1, b - 2
            else:
                ans.append('ab')
                a, b = a - 1, b - 1
        if a:
            ans.append('a' * a)
        if b:
            ans.append('b' * b)
        return ''.join(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String strWithout3a3b(int a, int b) {
        StringBuilder ans = new StringBuilder();
        while (a > 0 && b > 0) {
            if (a > b) {
                ans.append("aab");
                a -= 2;
                b -= 1;
            } else if (a < b) {
                ans.append("bba");
                a -= 1;
                b -= 2;
            } else {
                ans.append("ab");
                --a;
                --b;
            }
        }
        if (a > 0) {
            ans.append("a".repeat(a));
        }
        if (b > 0) {
            ans.append("b".repeat(b));
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string strWithout3a3b(int a, int b) {
        string ans;
        while (a && b) {
            if (a > b) {
                ans += "aab";
                a -= 2;
                b -= 1;
            } else if (a < b) {
                ans += "bba";
                a -= 1;
                b -= 2;
            } else {
                ans += "ab";
                --a;
                --b;
            }
        }
        if (a) ans += string(a, 'a');
        if (b) ans += string(b, 'b');
        return ans;
    }
};
```

### **Go**

```go
func strWithout3a3b(a int, b int) string {
	var ans strings.Builder
	for a > 0 && b > 0 {
		if a > b {
			ans.WriteString("aab")
			a -= 2
			b -= 1
		} else if a < b {
			ans.WriteString("bba")
			a -= 1
			b -= 2
		} else {
			ans.WriteString("ab")
			a--
			b--
		}
	}
	if a > 0 {
		ans.WriteString(strings.Repeat("a", a))
	}
	if b > 0 {
		ans.WriteString(strings.Repeat("b", b))
	}
	return ans.String()
}
```

### **...**

```

```

<!-- tabs:end -->
