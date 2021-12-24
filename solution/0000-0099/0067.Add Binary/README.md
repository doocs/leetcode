# [67. 二进制求和](https://leetcode-cn.com/problems/add-binary)

[English Version](/solution/0000-0099/0067.Add%20Binary/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个二进制字符串，返回它们的和（用二进制表示）。</p>

<p>输入为 <strong>非空 </strong>字符串且只包含数字&nbsp;<code>1</code>&nbsp;和&nbsp;<code>0</code>。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:</strong> a = &quot;11&quot;, b = &quot;1&quot;
<strong>输出:</strong> &quot;100&quot;</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> a = &quot;1010&quot;, b = &quot;1011&quot;
<strong>输出:</strong> &quot;10101&quot;</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>每个字符串仅由字符 <code>&#39;0&#39;</code> 或 <code>&#39;1&#39;</code> 组成。</li>
	<li><code>1 &lt;= a.length, b.length &lt;= 10^4</code></li>
	<li>字符串如果不是 <code>&quot;0&quot;</code> ，就都不含前导零。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def addBinary(self, a: str, b: str) -> str:
        return bin(int(a, 2) + int(b, 2))[2:]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

```cpp
class Solution {
public:
    string addBinary(string a, string b) {
        string res;
        int carry = 0;

        int i = a.size() - 1;
        int j = b.size() - 1;

        while (i >= 0 || j >= 0) {
            int digitA = i >= 0 ? a.at(i--) - '0' : 0;
            int digitB = j >= 0 ? b.at(j--) - '0' : 0;
            int sum = digitA + digitB + carry;
            carry = sum >= 2 ? 1 : 0;
            sum = sum >= 2 ? sum - 2 : sum;
            res += to_string(sum);
        }

        if (carry == 1) res.push_back('1');
        reverse(res.begin(), res.end());
        return res;
    }
};
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
