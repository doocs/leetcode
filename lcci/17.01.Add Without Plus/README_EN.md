# [17.01. Add Without Plus](https://leetcode.cn/problems/add-without-plus-lcci)

[中文文档](/lcci/17.01.Add%20Without%20Plus/README.md)

## Description

<p>Write a function that adds two numbers. You should not use + or any arithmetic operators.</p>

<p><strong>Example:</strong></p>

<pre>



<strong>Input:</strong> a = 1, b = 1



<strong>Output:</strong> 2</pre>

<p>&nbsp;</p>

<p><strong>Note: </strong></p>

<ul>
	<li><code>a</code>&nbsp;and&nbsp;<code>b</code>&nbsp;may be 0 or negative.</li>
	<li>The result fits in 32-bit integer.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python


```

### **Java**

```java
class Solution {
    public int add(int a, int b) {
        int sum = 0, carry = 0;
        while (b != 0) {
            sum = a ^ b;
            carry = (a & b) << 1;
            a = sum;
            b = carry;
        }
        return a;
    }
}
```

### **...**

```


```

<!-- tabs:end -->
