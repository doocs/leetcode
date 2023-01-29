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

**方法一：双指针**

我们用两个指针 $i$ 和 $j$ 分别指向两个字符串的末尾，从末尾开始逐位相加。每次取出对应位的数字 $a$ 和 $b$，计算它们的和 $a + b + c$，其中 $c$ 表示上一次相加的进位，最后将 $a + b + c$ 的个位数添加到追加到答案字符串的末尾，然后将 $a + b + c$ 的十位数作为进位 $c$ 的值，循环此过程直至两个字符串的指针都已经指向了字符串的开头并且进位 $c$ 的值为 $0$。

最后将答案字符串反转并返回即可。

时间复杂度 $O(max(m, n))$，其中 $m$ 和 $n$ 分别是两个字符串的长度。忽略答案字符串的空间消耗，空间复杂度 $O(1)$。

以下代码还实现了字符串相减，参考 `subStrings(num1, num2)` 函数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def addStrings(self, num1: str, num2: str) -> str:
        i, j = len(num1) - 1, len(num2) - 1
        ans = []
        c = 0
        while i >= 0 or j >= 0 or c:
            a = 0 if i < 0 else int(num1[i])
            b = 0 if j < 0 else int(num2[j])
            c, v = divmod(a + b + c, 10)
            ans.append(str(v))
            i, j = i - 1, j - 1
        return "".join(ans[::-1])

    def subStrings(self, num1: str, num2: str) -> str:
        m, n = len(num1), len(num2)
        neg = m < n or (m == n and num1 < num2)
        if neg:
            num1, num2 = num2, num1
        i, j = len(num1) - 1, len(num2) - 1
        ans = []
        c = 0
        while i >= 0:
            c = int(num1[i]) - c - (0 if j < 0 else int(num2[j]))
            ans.append(str((c + 10) % 10))
            c = 1 if c < 0 else 0
            i, j = i - 1, j - 1
        while len(ans) > 1 and ans[-1] == '0':
            ans.pop()
        if neg:
            ans.append('-')
        return ''.join(ans[::-1])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1;
        StringBuilder ans = new StringBuilder();
        for (int c = 0; i >= 0 || j >= 0 || c > 0; --i, --j) {
            int a = i < 0 ? 0 : num1.charAt(i) - '0';
            int b = j < 0 ? 0 : num2.charAt(j) - '0';
            c += a + b;
            ans.append(c % 10);
            c /= 10;
        }
        return ans.reverse().toString();
    }

    public String subStrings(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        boolean neg = m < n || (m == n && num1.compareTo(num2) < 0);
        if (neg) {
            String t = num1;
            num1 = num2;
            num2 = t;
        }
        int i = num1.length() - 1, j = num2.length() - 1;
        StringBuilder ans = new StringBuilder();
        for (int c = 0; i >= 0; --i, --j) {
            c = (num1.charAt(i) - '0') - c - (j < 0 ? 0 : num2.charAt(j) - '0');
            ans.append((c + 10) % 10);
            c = c < 0 ? 1 : 0;
        }
        while (ans.length() > 1 && ans.charAt(ans.length() - 1) == '0') {
            ans.deleteCharAt(ans.length() - 1);
        }
        if (neg) {
            ans.append('-');
        }
        return ans.reverse().toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string addStrings(string num1, string num2) {
        int i = num1.size() - 1, j = num2.size() - 1;
        string ans;
        for (int c = 0; i >= 0 || j >= 0 || c; --i, --j) {
            int a = i < 0 ? 0 : num1[i] - '0';
            int b = j < 0 ? 0 : num2[j] - '0';
            c += a + b;
            ans += to_string(c % 10);
            c /= 10;
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }

    string subStrings(string num1, string num2) {
        int m = num1.size(), n = num2.size();
        bool neg = m < n || (m == n && num1 < num2);
        if (neg) {
            swap(num1, num2);
        }
        int i = num1.size() - 1, j = num2.size() - 1;
        string ans;
        for (int c = 0; i >= 0; --i, --j) {
            c = (num1[i] - '0') - c - (j < 0 ? 0 : num2[j] - '0');
            ans += to_string((c + 10) % 10);
            c = c < 0 ? 1 : 0;
        }
        while (ans.size() > 1 && ans.back() == '0') {
            ans.pop_back();
        }
        if (neg) {
            ans.push_back('-');
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
```

### **Go**

```go
func addStrings(num1 string, num2 string) string {
	i, j := len(num1)-1, len(num2)-1
	ans := []byte{}
	for c := 0; i >= 0 || j >= 0 || c > 0; i, j = i-1, j-1 {
		if i >= 0 {
			c += int(num1[i] - '0')
		}
		if j >= 0 {
			c += int(num2[j] - '0')
		}
		ans = append(ans, byte(c%10+'0'))
		c /= 10
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return string(ans)
}

func subStrings(num1 string, num2 string) string {
	m, n := len(num1), len(num2)
	neg := m < n || (m == n && num1 < num2)
	if neg {
		num1, num2 = num2, num1
	}
	i, j := len(num1)-1, len(num2)-1
	ans := []byte{}
	for c := 0; i >= 0; i, j = i-1, j-1 {
		c = int(num1[i]-'0') - c
		if j >= 0 {
			c -= int(num2[j] - '0')
		}
		ans = append(ans, byte((c+10)%10+'0'))
		if c < 0 {
			c = 1
		} else {
			c = 0
		}
	}
	for len(ans) > 1 && ans[len(ans)-1] == '0' {
		ans = ans[:len(ans)-1]
	}
	if neg {
		ans = append(ans, '-')
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return string(ans)
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
    let i = num1.length - 1;
    let j = num2.length - 1;
    const ans = [];
    for (let c = 0; i >= 0 || j >= 0 || c; --i, --j) {
        c += i < 0 ? 0 : parseInt(num1.charAt(i), 10);
        c += j < 0 ? 0 : parseInt(num2.charAt(j), 10);
        ans.push(c % 10);
        c = Math.floor(c / 10);
    }
    return ans.reverse().join('');
};

/**
 * @param {string} num1
 * @param {string} num2
 * @return {string}
 */
var subStrings = function (num1, num2) {
    const m = num1.length;
    const n = num2.length;
    const neg = m < n || (m == n && num1 < num2);
    if (neg) {
        const t = num1;
        num1 = num2;
        num2 = t;
    }
    let i = num1.length - 1;
    let j = num2.length - 1;
    const ans = [];
    for (let c = 0; i >= 0; --i, --j) {
        c = parseInt(num1.charAt(i), 10) - c;
        if (j >= 0) {
            c -= parseInt(num2.charAt(j), 10);
        }
        ans.push((c + 10) % 10);
        c = c < 0 ? 1 : 0;
    }
    while (ans.length > 1 && ans[ans.length - 1] == '0') {
        ans.pop();
    }
    if (neg) {
        ans.push('-');
    }
    return ans.reverse().join('');
};
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
