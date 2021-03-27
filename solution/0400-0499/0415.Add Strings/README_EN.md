# [415. Add Strings](https://leetcode.com/problems/add-strings)

[中文文档](/solution/0400-0499/0415.Add%20Strings/README.md)

## Description

<p>Given two non-negative integers <code>num1</code> and <code>num2</code> represented as string, return the sum of <code>num1</code> and <code>num2</code>.</p>

<p><b>Note:</b>

<ol>

<li>The length of both <code>num1</code> and <code>num2</code> is < 5100.</li>

<li>Both <code>num1</code> and <code>num2</code> contains only digits <code>0-9</code>.</li>

<li>Both <code>num1</code> and <code>num2</code> does not contain any leading zero.</li>

<li>You <b>must not use any built-in BigInteger library</b> or <b>convert the inputs to integer</b> directly.</li>

</ol>

</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def addStrings(self, num1: str, num2: str) -> str:
        n1, n2 = len(num1) - 1, len(num2) - 1
        carry = 0
        res = []
        while n1 >= 0 or n2 >= 0 or carry > 0:
            carry += (0 if n1 < 0 else int(num1[n1])) + (0 if n2 < 0 else int(num2[n2]))
            res.append(str(carry % 10))
            carry //= 10
            n1, n2 = n1 - 1, n2 - 1
        return ''.join(res[::-1])
```

### **Java**

```java
class Solution {
    public String addStrings(String num1, String num2) {
        int n1 = num1.length() - 1, n2 = num2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (n1 >= 0 || n2 >= 0 || carry > 0) {
            carry += (n1 < 0 ? 0 : num1.charAt(n1--) - '0') + (n2 < 0 ? 0 : num2.charAt(n2--) - '0');
            sb.append(carry % 10);
            carry /= 10;
        }
        return sb.reverse().toString();
    }
}
```

### **...**

```

```

<!-- tabs:end -->
