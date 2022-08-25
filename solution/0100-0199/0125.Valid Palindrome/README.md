# [125. 验证回文串](https://leetcode.cn/problems/valid-palindrome)

[English Version](/solution/0100-0199/0125.Valid%20Palindrome/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个回文串。</p>

<p>字母和数字都属于字母数字字符。</p>

<p>给你一个字符串 <code>s</code>，如果它是回文串，返回 <code>true</code><em> </em>；否则，返回<em> </em><code>false</code><em> </em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> "A man, a plan, a canal: Panama"
<strong>输出：</strong>true
<strong>解释：</strong>"amanaplanacanalpanama" 是回文串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>"race a car"
<strong>输出：</strong>false
<strong>解释：</strong>"raceacar" 不是回文串。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = " "
<strong>输出：</strong>true
<strong>解释：</strong>在移除非字母数字字符之后，s 是一个空字符串 "" 。
由于空字符串正着反着读都一样，所以是回文串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>s</code> 仅由可打印的 ASCII 字符组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
