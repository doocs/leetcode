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
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> password = &quot;IloveLe3tcode!&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The password meets all the requirements. Therefore, we return true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> password = &quot;Me+You--IsMyDream&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> The password does not contain a digit and also contains 2 of the same character in adjacent positions. Therefore, we return false.
</pre>

<p><strong class="example">Example 3:</strong></p>

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
