# [415. 字符串相加](https://leetcode.cn/problems/add-strings)

[English Version](/solution/0400-0499/0415.Add%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串形式的非负整数&nbsp;<code>num1</code> 和<code>num2</code>&nbsp;，计算它们的和并同样以字符串形式返回。</p>

<p>你不能使用任何內建的用于处理大整数的库（比如 <code>BigInteger</code>），&nbsp;也不能直接将输入的字符串转换为整数形式。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num1 = "11", num2 = "123"
<strong>输出：</strong>"134"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num1 = "456", num2 = "77"
<strong>输出：</strong>"533"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>num1 = "0", num2 = "0"
<strong>输出：</strong>"0"
</pre>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num1.length, num2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>num1</code> 和<code>num2</code> 都只包含数字&nbsp;<code>0-9</code></li>
	<li><code>num1</code> 和<code>num2</code> 都不包含任何前导零</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def addStrings(self, num1: str, num2: str) -> str:
        i, j, carry = len(num1) - 1, len(num2) - 1, 0
        ans = []
        while i >= 0 or j >= 0 or carry:
            carry += (0 if i < 0 else int(num1[i])) + (0 if j < 0 else int(num2[j]))
            carry, v = divmod(carry, 10)
            ans.append(str(v))
            i, j = i - 1, j - 1
        return ''.join(ans[::-1])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder ans = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        for (; i >= 0 || j >= 0 || carry > 0; --i, --j) {
            carry += (i < 0 ? 0 : num1.charAt(i) - '0') + (j < 0 ? 0 : num2.charAt(j) - '0');
            ans.append(carry % 10);
            carry /= 10;
        }
        return ans.reverse().toString();
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} num1
 * @param {string} num2
 * @return {string}
 */
var addStrings = function (num1, num2) {
    let ans = [];
    let [i, j, carry] = [num1.length - 1, num2.length - 1, 0];
    for (; i >= 0 || j >= 0 || carry; --i, --j) {
        carry += i < 0 ? 0 : parseInt(num1.charAt(i), 10);
        carry += j < 0 ? 0 : parseInt(num2.charAt(j), 10);
        ans.push(carry % 10);
        carry = Math.floor(carry / 10);
    }
    return ans.reverse().join('');
};
```

### **C++**

```cpp
class Solution {
public:
    string addStrings(string num1, string num2) {
        string ans;
        int i = num1.size() - 1, j = num2.size() - 1, carry = 0;
        for (; i >= 0 || j >= 0 || carry; --i, --j) {
            carry += (i < 0 ? 0 : num1[i] - '0') + (j < 0 ? 0 : num2[j] - '0');
            ans += to_string(carry % 10);
            carry /= 10;
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
```

### **Go**

```go
func addStrings(num1 string, num2 string) string {
	ans := ""
	i, j, carry := len(num1)-1, len(num2)-1, 0
	for ; i >= 0 || j >= 0 || carry != 0; i, j = i-1, j-1 {
		if i >= 0 {
			carry += int(num1[i] - '0')
		}
		if j >= 0 {
			carry += int(num2[j] - '0')
		}
		ans = strconv.Itoa(carry%10) + ans
		carry /= 10
	}
	return ans
}
```

### **TypeScript**

```ts
function addStrings(num1: string, num2: string): string {
    const res = [];
    let i = num1.length - 1;
    let j = num2.length - 1;
    let isOver = false;
    while (i >= 0 || j >= 0 || isOver) {
        const x = Number(num1[i--]) || 0;
        const y = Number(num2[j--]) || 0;
        const sum = x + y + (isOver ? 1 : 0);
        isOver = sum >= 10;
        res.push(sum % 10);
    }
    return res.reverse().join('');
}
```

### **Rust**

```rust
impl Solution {
    pub fn add_strings(num1: String, num2: String) -> String {
        let mut res = vec![];
        let s1 = num1.as_bytes();
        let s2 = num2.as_bytes();
        let (mut i, mut j) = (s1.len(), s2.len());
        let mut is_over = false;
        while i != 0 || j != 0 || is_over {
            let mut sum = if is_over { 1 } else { 0 };
            if i != 0 {
                sum += (s1[i - 1] - b'0') as i32;
                i -= 1;
            }
            if j != 0 {
                sum += (s2[j - 1] - b'0') as i32;
                j -= 1;
            }
            is_over = sum >= 10;
            res.push((sum % 10).to_string());
        }
        res.into_iter().rev().collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
