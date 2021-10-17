# [43. 字符串相乘](https://leetcode-cn.com/problems/multiply-strings)

[English Version](/solution/0000-0099/0043.Multiply%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个以字符串形式表示的非负整数&nbsp;<code>num1</code>&nbsp;和&nbsp;<code>num2</code>，返回&nbsp;<code>num1</code>&nbsp;和&nbsp;<code>num2</code>&nbsp;的乘积，它们的乘积也表示为字符串形式。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> num1 = &quot;2&quot;, num2 = &quot;3&quot;
<strong>输出:</strong> &quot;6&quot;</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> num1 = &quot;123&quot;, num2 = &quot;456&quot;
<strong>输出:</strong> &quot;56088&quot;</pre>

<p><strong>说明：</strong></p>

<ol>
	<li><code>num1</code>&nbsp;和&nbsp;<code>num2</code>&nbsp;的长度小于110。</li>
	<li><code>num1</code> 和&nbsp;<code>num2</code> 只包含数字&nbsp;<code>0-9</code>。</li>
	<li><code>num1</code> 和&nbsp;<code>num2</code>&nbsp;均不以零开头，除非是数字 0 本身。</li>
	<li><strong>不能使用任何标准库的大数类型（比如 BigInteger）</strong>或<strong>直接将输入转换为整数来处理</strong>。</li>
</ol>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **TypeScript**

```ts
function multiply(num1: string, num2: string): string {
    if ([num1, num2].includes('0')) return '0';
    const n1 = num1.length, n2 = num2.length;
    let ans = '';
    for(let i = 0; i < n1; i++) {
        let cur1 = parseInt(num1.charAt(n1 - i - 1), 10);
        let sum = '';
        for(let j = 0; j < n2; j++) {
            let cur2 = parseInt(num2.charAt(n2 - j - 1), 10);
            sum = addString(sum, cur1 * cur2 + ('0'.repeat(j)));
        }
        ans = addString(ans, sum + ('0'.repeat(i)));
    }
    return ans;
};

function addString(s1: string, s2: string): string {
    const n1 = s1.length, n2 = s2.length;
    let ans = [];
    let sum = 0;
    for (let i = 0; i < n1 || i < n2 || sum > 0; i++) {
        let num1 = s1.charAt(i) ? parseInt(s1.charAt(i), 10) : 0;
        let num2 = s2.charAt(i) ? parseInt(s2.charAt(i), 10) : 0;
        sum += (num1 + num2);
        ans.unshift(sum % 10);
        sum = Math.floor(sum / 10);
    }
    return ans.join('');
}
```

### **...**

```

```

<!-- tabs:end -->
