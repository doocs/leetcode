# [125. 验证回文串](https://leetcode.cn/problems/valid-palindrome)

[English Version](/solution/0100-0199/0125.Valid%20Palindrome/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 <strong>回文串</strong> 。</p>

<p>字母和数字都属于字母数字字符。</p>

<p>给你一个字符串 <code>s</code>，如果它是 <strong>回文串</strong> ，返回 <code>true</code><em> </em>；否则，返回<em> </em><code>false</code><em> </em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> s = "A man, a plan, a canal: Panama"
<strong>输出：</strong>true
<strong>解释：</strong>"amanaplanacanalpanama" 是回文串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "race a car"
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

**方法一：双指针**

我们用双指针 $i$ 和 $j$ 分别指向字符串 $s$ 的两端，接下来循环以下过程，直至 $i \geq j$：

1. 如果 $s[i]$ 不是字母或数字，指针 $i$ 右移一位，继续下一次循环；
1. 如果 $s[j]$ 不是字母或数字，指针 $j$ 左移一位，继续下一次循环；
1. 如果 $s[i]$ 和 $s[j]$ 的小写形式不相等，返回 `false`；
1. 否则，指针 $i$ 右移一位，指针 $j$ 左移一位，继续下一次循环。

循环结束，返回 `true`。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。空间复杂度 $O(1)$。

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
                i, j = i + 1, j - 1
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
