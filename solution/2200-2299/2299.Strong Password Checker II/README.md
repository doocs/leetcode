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

**方法一：模拟 + 位运算**

根据题目描述，我们可以模拟检查密码是否满足题目要求的过程。

首先，我们检查密码的长度是否小于 $8$，如果是，则返回 `false`。

接下来，我们用一个掩码 `mask` 来记录密码是否包含小写字母、大写字母、数字和特殊字符。我们遍历密码，每次遍历到一个字符，先判断它是否和前一个字符相同，如果是，则返回 `false`。然后，根据字符的类型更新掩码 `mask`。最后，我们检查掩码 `mask` 是否为 $15$，如果是，则返回 `true`，否则返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为密码的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def strongPasswordCheckerII(self, password: str) -> bool:
        if len(password) < 8:
            return False
        mask = 0
        for i, c in enumerate(password):
            if i and c == password[i - 1]:
                return False
            if c.islower():
                mask |= 1
            elif c.isupper():
                mask |= 2
            elif c.isdigit():
                mask |= 4
            else:
                mask |= 8
        return mask == 15
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean strongPasswordCheckerII(String password) {
        if (password.length() < 8) {
            return false;
        }
        int mask = 0;
        for (int i = 0; i < password.length(); ++i) {
            char c = password.charAt(i);
            if (i > 0 && c == password.charAt(i - 1)) {
                return false;
            }
            if (Character.isLowerCase(c)) {
                mask |= 1;
            } else if (Character.isUpperCase(c)) {
                mask |= 2;
            } else if (Character.isDigit(c)) {
                mask |= 4;
            } else {
                mask |= 8;
            }
        }
        return mask == 15;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool strongPasswordCheckerII(string password) {
        if (password.size() < 8) {
            return false;
        }
        int mask = 0;
        for (int i = 0; i < password.size(); ++i) {
            char c = password[i];
            if (i && c == password[i - 1]) {
                return false;
            }
            if (c >= 'a' && c <= 'z') {
                mask |= 1;
            } else if (c >= 'A' && c <= 'Z') {
                mask |= 2;
            } else if (c >= '0' && c <= '9') {
                mask |= 4;
            } else {
                mask |= 8;
            }
        }
        return mask == 15;
    }
};
```

### **Go**

```go
func strongPasswordCheckerII(password string) bool {
	if len(password) < 8 {
		return false
	}
	mask := 0
	for i, c := range password {
		if i > 0 && byte(c) == password[i-1] {
			return false
		}
		if unicode.IsLower(c) {
			mask |= 1
		} else if unicode.IsUpper(c) {
			mask |= 2
		} else if unicode.IsDigit(c) {
			mask |= 4
		} else {
			mask |= 8
		}
	}
	return mask == 15
}
```

### **TypeScript**

```ts
function strongPasswordCheckerII(password: string): boolean {
    if (password.length < 8) {
        return false;
    }
    let mask = 0;
    for (let i = 0; i < password.length; ++i) {
        const c = password[i];
        if (i && c == password[i - 1]) {
            return false;
        }
        if (c >= 'a' && c <= 'z') {
            mask |= 1;
        } else if (c >= 'A' && c <= 'Z') {
            mask |= 2;
        } else if (c >= '0' && c <= '9') {
            mask |= 4;
        } else {
            mask |= 8;
        }
    }
    return mask == 15;
}
```

### **Rust**

```rust
impl Solution {
    pub fn strong_password_checker_ii(password: String) -> bool {
        let s = password.as_bytes();
        let n = password.len();
        if n < 8 {
            return false;
        }
        let mut mask = 0;
        let mut prev = b' ';
        for &c in s.iter() {
            if c == prev {
                return false;
            }
            mask |= if c.is_ascii_uppercase() {
                0b1000
            } else if c.is_ascii_lowercase() {
                0b100
            } else if c.is_ascii_digit() {
                0b10
            } else {
                0b1
            };
            prev = c;
        }
        mask == 0b1111
    }
}
```

### **C**

```c
bool strongPasswordCheckerII(char *password) {
    int n = strlen(password);
    if (n < 8) {
        return false;
    }
    int mask = 0;
    char prev = ' ';
    for (int i = 0; i < n; i++) {
        if (prev == password[i]) {
            return false;
        }
        if (islower(password[i])) {
            mask |= 0b1000;
        } else if (isupper(password[i])) {
            mask |= 0b100;
        } else if (isdigit(password[i])) {
            mask |= 0b10;
        } else {
            mask |= 0b1;
        }
        prev = password[i];
    }
    return mask == 0b1111;
}
```

### **...**

```

```

<!-- tabs:end -->
