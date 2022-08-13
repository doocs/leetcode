# [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome)

[中文文档](/solution/0100-0199/0125.Valid%20Palindrome/README.md)

## Description

<p>A phrase is a <strong>palindrome</strong> if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.</p>

<p>Given a string <code>s</code>, return <code>true</code><em> if it is a <strong>palindrome</strong>, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;A man, a plan, a canal: Panama&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> &quot;amanaplanacanalpanama&quot; is a palindrome.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;race a car&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> &quot;raceacar&quot; is not a palindrome.
</pre>

<p><strong>Example 3:</strong></p>

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
                i += 1
                j -= 1
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
            } else if (Character.toUpperCase(s.charAt(i)) != Character.toUpperCase(s.charAt(j))) {
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
            if (!isAlphaNum(s[i]))
                ++i;
            else if (!isAlphaNum(s[j]))
                --j;
            else if ((s[i] + 32 - 'a') % 32 != (s[j] + 32 - 'a') % 32)
                return false;
            else {
                ++i;
                --j;
            }
        }
        return true;
    }

private:
    bool isAlphaNum(char& ch) {
        if (ch >= 'a' && ch <= 'z') return true;
        if (ch >= 'A' && ch <= 'Z') return true;
        if (ch >= '0' && ch <= '9') return true;
        return false;
    }
};
```

### **C#**

```cs
using System.Linq;

public class Solution {
    public bool IsPalindrome(string s) {
        var chars = s.Where(ch => char.IsLetterOrDigit(ch)).Select(char.ToLower).ToList();
        var i = 0;
        var j = chars.Count - 1;
        for (; i < j; ++i, --j)
        {
            if (chars[i] != chars[j]) return false;
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
    let arr1 = [],
        arr2 = [];
    for (let i = 0; i < s.length; i++) {
        if (s[i] >= 'A' && s[i] <= 'Z') {
            arr1.push(s[i].toLowerCase());
        }
        if ((s[i] >= '0' && s[i] <= '9') || (s[i] >= 'a' && s[i] <= 'z')) {
            arr1.push(s[i]);
        }
    }
    arr2 = [...arr1];
    arr2.reverse();
    return arr1.join('') === arr2.join('');
};
```

```js
/**
 * @param {string} s
 * @return {boolean}
 */
var isPalindrome = function (s) {
    function isNumOrAl(a) {
        if (
            (a >= 'A' && a <= 'Z') ||
            (a >= '0' && a <= '9') ||
            (a >= 'a' && a <= 'z')
        ) {
            return true;
        } else {
            return false;
        }
    }

    if (s.length === 0) {
        return true;
    }
    let i = 0,
        j = s.length - 1;
    while (i < j) {
        while (i < j && !isNumOrAl(s[i])) {
            i++;
        }
        while (i < j && !isNumOrAl(s[j])) {
            j--;
        }
        if (s[i].toLowerCase() !== s[j].toLowerCase()) {
            return false;
        } else {
            i++;
            j--;
        }
    }
    return true;
};
```

### **TypeScript**

```ts
function isPalindrome(s: string): boolean {
    let left: number = 0,
        right: number = s.length - 1;
    while (left < right) {
        let char1: string = s.charAt(left);
        let char2: string = s.charAt(right);
        if (!/[a-zA-Z0-9]/.test(char1)) {
            ++left;
        } else if (!/[a-zA-Z0-9]/.test(char2)) {
            --right;
        } else if (char1.toLocaleLowerCase() != char2.toLocaleLowerCase()) {
            return false;
        } else {
            ++left;
            --right;
        }
    }
    return true;
}
```

```ts
function isPalindrome(s: string): boolean {
    const isAlphanumeric = (c: string) => {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    };
    const cs = s.toLocaleLowerCase().split('').filter(isAlphanumeric);
    return cs.join('') === cs.reverse().join('');
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

### **...**

```

```

<!-- tabs:end -->
