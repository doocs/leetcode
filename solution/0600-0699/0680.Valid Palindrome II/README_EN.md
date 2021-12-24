# [680. Valid Palindrome II](https://leetcode.com/problems/valid-palindrome-ii)

[中文文档](/solution/0600-0699/0680.Valid%20Palindrome%20II/README.md)

## Description

<p>

Given a non-empty string <code>s</code>, you may delete <b>at most</b> one character. Judge whether you can make it a palindrome.

</p>

<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> "aba"

<b>Output:</b> True

</pre>

</p>

<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> "abca"

<b>Output:</b> True

<b>Explanation:</b> You could delete the character 'c'.

</pre>

</p>

<p><b>Note:</b><br>

<ol>

<li>The string will only contain lowercase characters a-z.

The maximum length of the string is 50000.</li>

</ol>

</p>

## Solutions

<!-- tabs:start -->

### **Python3**

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
            return (
                isPalinddrome(s.slice(i, j)) ||
                isPalinddrome(s.slice(i + 1, j + 1))
            );
        }
    }
    return true;
}

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
