# [43. Multiply Strings](https://leetcode.com/problems/multiply-strings)

[中文文档](/solution/0000-0099/0043.Multiply%20Strings/README.md)

## Description

<p>Given two non-negative integers <code>num1</code> and <code>num2</code> represented as strings, return the product of <code>num1</code> and <code>num2</code>, also represented as a string.</p>

<p><strong>Note:</strong>&nbsp;You must not use any built-in BigInteger library or convert the inputs to integer directly.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> num1 = "2", num2 = "3"
<strong>Output:</strong> "6"
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> num1 = "123", num2 = "456"
<strong>Output:</strong> "56088"
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num1.length, num2.length &lt;= 200</code></li>
	<li><code>num1</code> and <code>num2</code> consist of digits only.</li>
	<li>Both <code>num1</code> and <code>num2</code>&nbsp;do not contain any leading zero, except the number <code>0</code> itself.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def multiply(self, num1: str, num2: str) -> str:
        def add(s1, s2):
            n1, n2 = len(s1), len(s2)
            i = carry = 0
            res = []
            while i < max(n1, n2) or carry > 0:
                a = int(s1[n1 - i - 1]) if i < n1 else 0
                b = int(s2[n2 - i - 1]) if i < n2 else 0
                carry, t = divmod(a + b + carry, 10)
                res.append(str(t))
                i += 1
            return ''.join(res[::-1])

        if '0' in [num1, num2]:
            return '0'
        n1, n2 = len(num1), len(num2)
        ans = ''
        for i in range(n1):
            a = int(num1[n1 - i - 1])
            t = ''
            for j in range(n2):
                b = int(num2[n2 - j - 1])
                t = add(t, str(a * b) + '0' * j)
            ans = add(ans, t + '0' * i)
        return ans
```

### **Java**

```java
class Solution {
    public String multiply(String num1, String num2) {
        if (Objects.equals(num1, "0") || Objects.equals(num2, "0")) {
            return "0";
        }
        int n1 = num1.length(), n2 = num2.length();
        String ans = "";
        for (int i = 0; i < n1; ++i) {
            int a = num1.charAt(n1 - i - 1) - '0';
            String t = "";
            for (int j = 0; j < n2; ++j) {
                int b = num2.charAt(n2 - j - 1) - '0';
                StringBuilder sb = new StringBuilder(String.valueOf(a * b));
                for (int k = 0; k < j; ++k) {
                    sb.append(0);
                }
                t = add(t, sb.toString());
            }
            StringBuilder sb = new StringBuilder(t);
            for (int k = 0; k < i; ++k) {
                sb.append(0);
            }
            ans = add(ans, sb.toString());
        }
        return ans;
    }

    private String add(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        StringBuilder res = new StringBuilder();
        for (int i = 0, carry = 0; i < Math.max(n1, n2) || carry > 0; ++i) {
            int a = i < n1 ? (s1.charAt(n1 - i - 1) - '0') : 0;
            int b = i < n2 ? (s2.charAt(n2 - i - 1) - '0') : 0;
            int s = a + b + carry;
            carry = s / 10;
            res.append(s % 10);
        }
        return res.reverse().toString();
    }
}
```

### **TypeScript**

```ts
function multiply(num1: string, num2: string): string {
    if ([num1, num2].includes("0")) return "0";
    const n1 = num1.length,
        n2 = num2.length;
    let ans = "";
    for (let i = 0; i < n1; i++) {
        let cur1 = parseInt(num1.charAt(n1 - i - 1), 10);
        let sum = "";
        for (let j = 0; j < n2; j++) {
            let cur2 = parseInt(num2.charAt(n2 - j - 1), 10);
            sum = addString(sum, cur1 * cur2 + "0".repeat(j));
        }
        ans = addString(ans, sum + "0".repeat(i));
    }
    return ans;
}

function addString(s1: string, s2: string): string {
    const n1 = s1.length,
        n2 = s2.length;
    let ans = [];
    let sum = 0;
    for (let i = 0; i < n1 || i < n2 || sum > 0; i++) {
        let num1 = i < n1 ? parseInt(s1.charAt(n1 - i - 1), 10) : 0;
        let num2 = i < n2 ? parseInt(s2.charAt(n2 - i - 1), 10) : 0;
        sum += num1 + num2;
        ans.unshift(sum % 10);
        sum = Math.floor(sum / 10);
    }
    return ans.join("");
}
```

### **...**

```

```

<!-- tabs:end -->
