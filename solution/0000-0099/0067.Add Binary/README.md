# [67. 二进制求和](https://leetcode.cn/problems/add-binary)

[English Version](/solution/0000-0099/0067.Add%20Binary/README_EN.md)

<!-- tags:位运算,数学,字符串,模拟 -->

<!-- difficulty:简单 -->

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

### 方法一：模拟

我们用一个变量 $carry$ 记录当前的进位，用两个指针 $i$ 和 $j$ 分别指向 $a$ 和 $b$ 的末尾，从末尾到开头逐位相加即可。

时间复杂度 $O(\max(m, n))$，其中 $m$ 和 $n$ 分别为字符串 $a$ 和 $b$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def addBinary(self, a: str, b: str) -> str:
        return bin(int(a, 2) + int(b, 2))[2:]
```

```java
class Solution {
    public String addBinary(String a, String b) {
        var sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;
        for (int carry = 0; i >= 0 || j >= 0 || carry > 0; --i, --j) {
            carry += (i >= 0 ? a.charAt(i) - '0' : 0) + (j >= 0 ? b.charAt(j) - '0' : 0);
            sb.append(carry % 2);
            carry /= 2;
        }
        return sb.reverse().toString();
    }
}
```

```cpp
class Solution {
public:
    string addBinary(string a, string b) {
        string ans;
        int i = a.size() - 1, j = b.size() - 1;
        for (int carry = 0; i >= 0 || j >= 0 || carry; --i, --j) {
            carry += (i >= 0 ? a[i] - '0' : 0) + (j >= 0 ? b[j] - '0' : 0);
            ans.push_back((carry % 2) + '0');
            carry /= 2;
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
```

```go
func addBinary(a string, b string) string {
	i, j := len(a)-1, len(b)-1
	ans := []byte{}
	for carry := 0; i >= 0 || j >= 0 || carry > 0; i, j = i-1, j-1 {
		if i >= 0 {
			carry += int(a[i] - '0')
		}
		if j >= 0 {
			carry += int(b[j] - '0')
		}
		ans = append(ans, byte(carry%2+'0'))
		carry /= 2
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return string(ans)
}
```

```ts
function addBinary(a: string, b: string): string {
    return (BigInt('0b' + a) + BigInt('0b' + b)).toString(2);
}
```

```rust
impl Solution {
    pub fn add_binary(a: String, b: String) -> String {
        let mut i = (a.len() as i32) - 1;
        let mut j = (b.len() as i32) - 1;
        let mut carry = 0;
        let mut ans = String::new();
        let a = a.as_bytes();
        let b = b.as_bytes();
        while i >= 0 || j >= 0 || carry > 0 {
            if i >= 0 {
                carry += a[i as usize] - b'0';
                i -= 1;
            }
            if j >= 0 {
                carry += b[j as usize] - b'0';
                j -= 1;
            }
            ans.push_str(&(carry % 2).to_string());
            carry /= 2;
        }
        ans.chars().rev().collect()
    }
}
```

```cs
public class Solution {
    public string AddBinary(string a, string b) {
        int i = a.Length - 1;
        int j = b.Length - 1;
        var sb = new StringBuilder();
        for (int carry = 0; i >= 0 || j >= 0 || carry > 0; --i, --j) {
            carry += i >= 0 ? a[i] - '0' : 0;
            carry += j >= 0 ? b[j] - '0' : 0;
            sb.Append(carry % 2);
            carry /= 2;
        }
        var ans = sb.ToString().ToCharArray();
        Array.Reverse(ans);
        return new string(ans);
    }
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

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

```ts
function addBinary(a: string, b: string): string {
    let i = a.length - 1;
    let j = b.length - 1;
    let ans: number[] = [];
    for (let carry = 0; i >= 0 || j >= 0 || carry; --i, --j) {
        carry += (i >= 0 ? a[i] : '0').charCodeAt(0) - '0'.charCodeAt(0);
        carry += (j >= 0 ? b[j] : '0').charCodeAt(0) - '0'.charCodeAt(0);
        ans.push(carry % 2);
        carry >>= 1;
    }
    return ans.reverse().join('');
}
```

<!-- tabs:end -->

<!-- end -->
