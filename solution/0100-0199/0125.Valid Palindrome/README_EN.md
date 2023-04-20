# [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome)

[中文文档](/solution/0100-0199/0125.Valid%20Palindrome/README.md)

## Description

<p>A phrase is a <strong>palindrome</strong> if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.</p>

<p>Given a string <code>s</code>, return <code>true</code><em> if it is a <strong>palindrome</strong>, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;A man, a plan, a canal: Panama&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> &quot;amanaplanacanalpanama&quot; is a palindrome.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;race a car&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> &quot;raceacar&quot; is not a palindrome.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot; &quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> s is an empty string &quot;&quot; after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of printable ASCII characters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isPalindrome(self, s: str) -> bool:
        i, j = 0, len(s) - 1
        while i < j:
            if not s[i].isalnum():
                i += 1
            elif not s[j].isalnum():
                j -= 1
            elif s[i].lower() != s[j].lower():
                return False
            else:
                i, j = i + 1, j - 1
        return True
```

### **Java**

```java
class Solution {
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                ++i;
            } else if (!Character.isLetterOrDigit(s.charAt(j))) {
                --j;
            } else if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            } else {
                ++i;
                --j;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isPalindrome(string s) {
        int i = 0, j = s.size() - 1;
        while (i < j) {
            if (!isalnum(s[i])) {
                ++i;
            } else if (!isalnum(s[j])) {
                --j;
            } else if (tolower(s[i]) != tolower(s[j])) {
                return false;
            } else {
                ++i;
                --j;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func isPalindrome(s string) bool {
	i, j := 0, len(s)-1
	for i < j {
		if !isalnum(s[i]) {
			i++
		} else if !isalnum(s[j]) {
			j--
		} else if tolower(s[i]) != tolower(s[j]) {
			return false
		} else {
			i, j = i+1, j-1
		}
	}
	return true
}

func isalnum(ch byte) bool {
	return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')
}

func tolower(ch byte) byte {
	if ch >= 'A' && ch <= 'Z' {
		return ch + 32
	}
	return ch
}
```

### **C#**

```cs
public class Solution {
    public bool IsPalindrome(string s) {
        int i = 0, j = s.Length - 1;
        while (i < j) {
            if (!char.IsLetterOrDigit(s[i])) {
                ++i;
            } else if (!char.IsLetterOrDigit(s[j])) {
                --j;
            } else if (char.ToLower(s[i++]) != char.ToLower(s[j--])) {
                return false;
            }
        }
        return true;
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {boolean}
 */
var isPalindrome = function (s) {
    let i = 0;
    let j = s.length - 1;
    while (i < j) {
        if (!/[a-zA-Z0-9]/.test(s[i])) {
            ++i;
        } else if (!/[a-zA-Z0-9]/.test(s[j])) {
            --j;
        } else if (s[i].toLowerCase() !== s[j].toLowerCase()) {
            return false;
        } else {
            ++i;
            --j;
        }
    }
    return true;
};
```

### **TypeScript**

```ts
function isPalindrome(s: string): boolean {
    let i = 0;
    let j = s.length - 1;
    while (i < j) {
        if (!/[a-zA-Z0-9]/.test(s[i])) {
            ++i;
        } else if (!/[a-zA-Z0-9]/.test(s[j])) {
            --j;
        } else if (s[i].toLowerCase() !== s[j].toLowerCase()) {
            return false;
        } else {
            ++i;
            --j;
        }
    }
    return true;
}
```

### **Rust**

```rust
impl Solution {
    pub fn is_palindrome(s: String) -> bool {
        let s = s.to_lowercase();
        let s = s.as_bytes();
        let n = s.len();
        let (mut l, mut r) = (0, n - 1);
        while l < r {
            while l < r && !s[l].is_ascii_alphanumeric() {
                l += 1;
            }
            while l < r && !s[r].is_ascii_alphanumeric() {
                r -= 1;
            }
            if s[l] != s[r] {
                return false;
            }
            l += 1;
            if r != 0 {
                r -= 1;
            }
        }
        true
    }
}
```

### **PHP**

```php
class Solution {
    /**
     * @param String $s
     * @return Boolean
     */
    function isPalindrome($s) {
        $regex = "/[a-z0-9]/";
        $s = strtolower($s);
        preg_match_all($regex, $s, $matches);
        if ($matches[0] == Null) return true;
        $len = floor(count($matches[0]) / 2);
        for ($i = 0; $i < $len; $i++) {
            if ($matches[0][$i] != $matches[0][count($matches[0]) - 1 - $i]) {
                return false;
            }
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
