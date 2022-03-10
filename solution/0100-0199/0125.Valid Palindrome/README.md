# [125. 验证回文串](https://leetcode-cn.com/problems/valid-palindrome)

[English Version](/solution/0100-0199/0125.Valid%20Palindrome/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。</p>

<p><strong>说明：</strong>本题中，我们将空字符串定义为有效的回文串。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> "A man, a plan, a canal: Panama"
<strong>输出:</strong> true
<strong>解释：</strong>"amanaplanacanalpanama" 是回文串
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> "race a car"
<strong>输出:</strong> false
<strong>解释：</strong>"raceacar" 不是回文串
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 2 * 10<sup>5</sup></code></li>
	<li>字符串 <code>s</code> 由 ASCII 字符组成</li>
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
            if (!isAlphaNum(s[i])) ++i;
            else if (!isAlphaNum(s[j])) --j;
            else if ((s[i] + 32 - 'a') % 32 != (s[j] + 32 - 'a') % 32) return false;
            else {
                ++i;
                --j;
            }
        }
        return true;
    }

private:
    bool isAlphaNum(char &ch) {
        if (ch >= 'a' && ch <= 'z') return true;
        if (ch >= 'A' && ch <= 'Z') return true;
        if (ch >= '0' && ch <= '9') return true;
        return false;
    }
};
```

### **tTypeScript**

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

### **...**

```

```

<!-- tabs:end -->
