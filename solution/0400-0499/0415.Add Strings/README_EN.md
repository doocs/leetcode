# [415. Add Strings](https://leetcode.com/problems/add-strings)

[中文文档](/solution/0400-0499/0415.Add%20Strings/README.md)

## Description

<p>Given two non-negative integers, <code>num1</code> and <code>num2</code> represented as string, return <em>the sum of</em> <code>num1</code> <em>and</em> <code>num2</code> <em>as a string</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> num1 = &quot;11&quot;, num2 = &quot;123&quot;
<strong>Output:</strong> &quot;134&quot;
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> num1 = &quot;456&quot;, num2 = &quot;77&quot;
<strong>Output:</strong> &quot;533&quot;
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> num1 = &quot;0&quot;, num2 = &quot;0&quot;
<strong>Output:</strong> &quot;0&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num1.length, num2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>num1</code> and <code>num2</code> consist of only digits.</li>
	<li><code>num1</code> and <code>num2</code> don&#39;t have any leading zeros except for the zero itself.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you solve it without using any built-in <code>BigInteger</code> library or converting the inputs to integer directly?</p>

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

### **TypeScript**

```ts
/**
 * @param {string} num1
 * @param {string} num2
 * @return {string}
 */
var addStrings = function (num1, num2) {
    let ans = [];
    for (
        let i = num1.length - 1, j = num2.length - 1, sum = 0;
        i >= 0 || j >= 0 || sum > 0;
        i--, j--
    ) {
        const a = i >= 0 ? parseInt(num1.charAt(i), 10) : 0;
        const b = j >= 0 ? parseInt(num2.charAt(j), 10) : 0;
        sum += a + b;
        ans.unshift(sum % 10);
        sum = Math.floor(sum / 10);
    }
    return ans.join("");
};
```

### **...**

```

```

<!-- tabs:end -->
