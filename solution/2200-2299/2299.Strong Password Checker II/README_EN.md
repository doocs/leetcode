# [2299. Strong Password Checker II](https://leetcode.com/problems/strong-password-checker-ii)

[中文文档](/solution/2200-2299/2299.Strong%20Password%20Checker%20II/README.md)

## Description

<p>A password is said to be <strong>strong</strong> if it satisfies all the following criteria:</p>

<ul>
	<li>It has at least <code>8</code> characters.</li>
	<li>It contains at least <strong>one lowercase</strong> letter.</li>
	<li>It contains at least <strong>one uppercase</strong> letter.</li>
	<li>It contains at least <strong>one digit</strong>.</li>
	<li>It contains at least <strong>one special character</strong>. The special characters are the characters in the following string: <code>&quot;!@#$%^&amp;*()-+&quot;</code>.</li>
	<li>It does <strong>not</strong> contain <code>2</code> of the same character in adjacent positions (i.e., <code>&quot;aab&quot;</code> violates this condition, but <code>&quot;aba&quot;</code> does not).</li>
</ul>

<p>Given a string <code>password</code>, return <code>true</code><em> if it is a <strong>strong</strong> password</em>. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> password = &quot;IloveLe3tcode!&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The password meets all the requirements. Therefore, we return true.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> password = &quot;Me+You--IsMyDream&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> The password does not contain a digit and also contains 2 of the same character in adjacent positions. Therefore, we return false.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> password = &quot;1aB!&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> The password does not meet the length requirement. Therefore, we return false.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= password.length &lt;= 100</code></li>
	<li><code>password</code> consists of letters, digits, and special characters: <code>&quot;!@#$%^&amp;*()-+&quot;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
