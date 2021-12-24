# [415. 字符串相加](https://leetcode-cn.com/problems/add-strings)

[English Version](/solution/0400-0499/0415.Add%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串形式的非负整数&nbsp;<code>num1</code> 和<code>num2</code>&nbsp;，计算它们的和。</p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>num1</code> 和<code>num2</code>&nbsp;的长度都小于 5100</li>
	<li><code>num1</code> 和<code>num2</code> 都只包含数字&nbsp;<code>0-9</code></li>
	<li><code>num1</code> 和<code>num2</code> 都不包含任何前导零</li>
	<li><strong>你不能使用任何內建 BigInteger 库，&nbsp;也不能直接将输入的字符串转换为整数形式</strong></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
