# [2299. 强密码检验器 II](https://leetcode.cn/problems/strong-password-checker-ii)

[English Version](/solution/2200-2299/2299.Strong%20Password%20Checker%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果一个密码满足以下所有条件，我们称它是一个 <strong>强</strong>&nbsp;密码：</p>

<ul>
	<li>它有至少 <code>8</code>&nbsp;个字符。</li>
	<li>至少包含 <strong>一个小写英文</strong>&nbsp;字母。</li>
	<li>至少包含 <strong>一个大写英文</strong>&nbsp;字母。</li>
	<li>至少包含 <strong>一个数字</strong>&nbsp;。</li>
	<li>至少包含 <strong>一个特殊字符</strong>&nbsp;。特殊字符为：<code>"!@#$%^&amp;*()-+"</code>&nbsp;中的一个。</li>
	<li>它 <strong>不</strong>&nbsp;包含&nbsp;<code>2</code>&nbsp;个连续相同的字符（比方说&nbsp;<code>"aab"</code>&nbsp;不符合该条件，但是&nbsp;<code>"aba"</code>&nbsp;符合该条件）。</li>
</ul>

<p>给你一个字符串&nbsp;<code>password</code>&nbsp;，如果它是一个&nbsp;<strong>强</strong>&nbsp;密码，返回&nbsp;<code>true</code>，否则返回&nbsp;<code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>password = "IloveLe3tcode!"
<b>输出：</b>true
<b>解释：</b>密码满足所有的要求，所以我们返回 true 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>password = "Me+You--IsMyDream"
<b>输出：</b>false
<b>解释：</b>密码不包含数字，且包含 2 个连续相同的字符。所以我们返回 false 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>password = "1aB!"
<b>输出：</b>false
<b>解释：</b>密码不符合长度要求。所以我们返回 false 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= password.length &lt;= 100</code></li>
	<li><code>password</code>&nbsp;包含字母，数字和&nbsp;<code>"!@#$%^&amp;*()-+"</code>&nbsp;这些特殊字符。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：直接遍历判断**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def strongPasswordCheckerII(self, password: str) -> bool:
        if len(password) < 8:
            return False
        ans = 0
        for i, c in enumerate(password):
            if i and password[i - 1] == c:
                return False
            if c.islower():
                ans |= 1
            elif c.isupper():
                ans |= 2
            elif c.isdigit():
                ans |= 4
            else:
                ans |= 8
        return ans == 15
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean strongPasswordCheckerII(String password) {
        if (password.length() < 8) {
            return false;
        }
        int ans = 0;
        char prev = '.';
        for (char c : password.toCharArray()) {
            if (prev == c) {
                return false;
            }
            prev = c;
            if (Character.isLowerCase(c)) {
                ans |= 1;
            } else if (Character.isUpperCase(c)) {
                ans |= 2;
            } else if (Character.isDigit(c)) {
                ans |= 4;
            } else {
                ans |= 8;
            }
        }
        return ans == 15;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool strongPasswordCheckerII(string password) {
        if (password.size() < 8) return false;
        int ans = 0;
        char prev = '.';
        for (char& c : password) {
            if (c == prev) return false;
            prev = c;
            if (c >= 'a' && c <= 'z')
                ans |= 1;
            else if (c >= 'A' && c <= 'Z')
                ans |= 2;
            else if (c >= '0' && c <= '9')
                ans |= 4;
            else
                ans |= 8;
        }
        return ans == 15;
    }
};
```

### **Go**

```go
func strongPasswordCheckerII(password string) bool {
	if len(password) < 8 {
		return false
	}
	ans := 0
	for i, c := range password {
		if i > 0 && password[i] == password[i-1] {
			return false
		}
		if unicode.IsLower(c) {
			ans |= 1
		} else if unicode.IsUpper(c) {
			ans |= 2
		} else if unicode.IsDigit(c) {
			ans |= 4
		} else {
			ans |= 8
		}
	}
	return ans == 15
}
```

### **TypeScript**

```ts
function strongPasswordCheckerII(password: string): boolean {
    if (password.length < 8) return false;
    if (!/[a-z]+/g.test(password)) return false;
    if (!/[A-Z]+/g.test(password)) return false;
    if (!/[0-9]+/g.test(password)) return false;
    if (!/[\!\@\#\$\%\^\&\*\(\)\-\+]+/g.test(password)) return false;
    const n = password.length;
    for (let i = 1; i < n; i++) {
        if (password.charAt(i) == password.charAt(i - 1)) return false;
    }
    return true;
}
```

### **...**

```

```

<!-- tabs:end -->
