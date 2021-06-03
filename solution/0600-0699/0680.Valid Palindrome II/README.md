# [680. 验证回文字符串 Ⅱ](https://leetcode-cn.com/problems/valid-palindrome-ii)

[English Version](/solution/0600-0699/0680.Valid%20Palindrome%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非空字符串&nbsp;<code>s</code>，<strong>最多</strong>删除一个字符。判断是否能成为回文字符串。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> &quot;aba&quot;
<strong>输出:</strong> True
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> &quot;abca&quot;
<strong>输出:</strong> True
<strong>解释:</strong> 你可以删除c字符。
</pre>

<p><strong>注意:</strong></p>

<ol>
	<li>字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def validPalindrome(self, s: str) -> bool:
        def isPalindrome(s):
            i, j = 0, len(s) - 1
            while i < j:
                if s[i] != s[j]:
                    return False
                i += 1
                j -= 1
            return True

        i, j = 0, len(s) - 1
        while i < j:
            if s[i] != s[j]:
                return isPalindrome(s[i: j]) or isPalindrome(s[i + 1: j + 1])
            i += 1
            j -= 1
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean validPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return isPalindrome(s.substring(i, j)) || isPalindrome(s.substring(i + 1, j + 1));
            }
        }
        return true;
    }

    private boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
```

### **TypeScript**

```ts
function validPalindrome(s: string): boolean {
    for (let i: number = 0, j = s.length - 1; i < j; ++i, --j) {
        if (s.charAt(i) != s.charAt(j)) {
            return isPalinddrome(s.slice(i, j)) || isPalinddrome(s.slice(i + 1, j + 1));
        }
    }
    return true;
};

function isPalinddrome(s: string): boolean {
    for (let i: number = 0, j = s.length - 1; i < j; ++i, --j) {
        if (s.charAt(i) != s.charAt(j)) {
            return false;
        }
    }
    return true;
}
```

### **...**

```

```

<!-- tabs:end -->
