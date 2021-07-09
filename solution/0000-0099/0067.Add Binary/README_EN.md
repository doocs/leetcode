# [67. Add Binary](https://leetcode.com/problems/add-binary)

[中文文档](/solution/0000-0099/0067.Add%20Binary/README.md)

## Description

<p>Given two binary strings <code>a</code> and <code>b</code>, return <em>their sum as a binary string</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> a = "11", b = "1"
<strong>Output:</strong> "100"
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> a = "1010", b = "1011"
<strong>Output:</strong> "10101"
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length &lt;= 10<sup>4</sup></code></li>
	<li><code>a</code> and <code>b</code> consist&nbsp;only of <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code> characters.</li>
	<li>Each string does not contain leading zeros except for the zero itself.</li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def addBinary(self, a: str, b: str) -> str:
        return bin(int(a, 2) + int(b, 2))[2:]
```

### **Java**

```java
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int s = carry + (i >= 0 ? a.charAt(i) - '0' : 0) + (j >= 0 ? b.charAt(j) - '0' : 0);
            sb.append(s % 2);
            carry = s / 2;
            --i;
            --j;
        }
        return sb.reverse().toString();
    }
}
```

### **C#**

```cs
using System;
using System.Collections.Generic;

public class Solution {
    public string AddBinary(string a, string b) {
        var list = new List<char>(Math.Max(a.Length, b.Length) + 1);
        var i = a.Length - 1;
        var j = b.Length - 1;
        var carry = 0;
        while (i >= 0 || j >= 0)
        {
            if (i >= 0)
            {
                carry += a[i] - '0';
            }
            if (j >= 0)
            {
                carry += b[j] - '0';
            }
            list.Add((char)((carry % 2) + '0'));
            carry /= 2;
            --i;
            --j;
        }
        if (carry > 0) list.Add((char) (carry + '0'));
        list.Reverse();
        return new string(list.ToArray());
    }
}
```

### **Go**

```go
func addBinary(a string, b string) string {
	for len(a) > len(b) {
		b = "0" + b
	}
	for len(a) < len(b) {
		a = "0" + a
	}
	zero := []byte("0")[0]
	ret := make([]byte, len(a))
	for right := len(a) - 1; right > 0; right-- {
		t := ret[right] + a[right] + b[right] - zero*2
		ret[right] = t%2 + zero
		if t >= 2 {
			ret[right-1] = 1
		}
	}
	t := ret[0] + a[0] + b[0] - zero*2
	ret[0] = t%2 + zero
	if t >= 2 {
		ret = append([]byte("1"), ret...)
	}

	return string(ret)
}
```

### **...**

```

```

<!-- tabs:end -->
