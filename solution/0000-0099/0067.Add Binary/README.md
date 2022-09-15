# [67. 二进制求和](https://leetcode.cn/problems/add-binary)

[English Version](/solution/0000-0099/0067.Add%20Binary/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个二进制字符串 <code>a</code> 和 <code>b</code> ，以二进制字符串的形式返回它们的和。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入:</strong>a = "11", b = "1"
<strong>输出：</strong>"100"</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>a = "1010", b = "1011"
<strong>输出：</strong>"10101"</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length &lt;= 10<sup>4</sup></code></li>
	<li><code>a</code> 和 <code>b</code> 仅由字符 <code>'0'</code> 或 <code>'1'</code> 组成</li>
	<li>字符串如果不是 <code>"0"</code> ，就不含前导零</li>
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

```python
class Solution:
    def addBinary(self, a: str, b: str) -> str:
        ans = []
        i, j, carry = len(a) - 1, len(b) - 1, 0
        while i >= 0 or j >= 0 or carry:
            carry += (0 if i < 0 else int(a[i])) + (0 if j < 0 else int(b[j]))
            carry, v = divmod(carry, 2)
            ans.append(str(v))
            i, j = i - 1, j - 1
        return ''.join(ans[::-1])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        for (int i = a.length() - 1, j = b.length() - 1, carry = 0; i >= 0 || j >= 0 || carry > 0;
             --i, --j) {
            carry += (i >= 0 ? a.charAt(i) - '0' : 0) + (j >= 0 ? b.charAt(j) - '0' : 0);
            sb.append(carry % 2);
            carry /= 2;
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

### **TypeScript**

```ts
function addBinary(a: string, b: string): string {
    const n = Math.max(a.length, b.length);
    const res = [];
    let isOver = false;
    for (let i = 0; i < n || isOver; i++) {
        let val = isOver ? 1 : 0;
        isOver = false;
        if (a[a.length - i - 1] === '1') {
            val++;
        }
        if (b[b.length - i - 1] === '1') {
            val++;
        }
        if (val > 1) {
            isOver = true;
            val -= 2;
        }
        res.push(val);
    }
    return res.reverse().join('');
}
```

### **Rust**

```rust
impl Solution {
    pub fn add_binary(a: String, b: String) -> String {
        let n = a.len().max(b.len());
        let (a, b) = (a.as_bytes(), b.as_bytes());
        let mut res = vec![];
        let mut is_over = false;
        let mut i = 0;
        while i < n || is_over {
            let mut val = if is_over { 1 } else { 0 };
            is_over = false;
            if a.get(a.len() - i - 1).unwrap_or(&b'0') == &b'1' {
                val += 1;
            }
            if b.get(b.len() - i - 1).unwrap_or(&b'0') == &b'1' {
                val += 1;
            }
            if val > 1 {
                is_over = true;
                val -= 2;
            }
            i += 1;
            res.push(char::from(b'0' + val));
        }
        res.iter().rev().collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
