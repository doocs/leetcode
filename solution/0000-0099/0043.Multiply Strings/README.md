# [43. 字符串相乘](https://leetcode.cn/problems/multiply-strings)

[English Version](/solution/0000-0099/0043.Multiply%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个以字符串形式表示的非负整数&nbsp;<code>num1</code>&nbsp;和&nbsp;<code>num2</code>，返回&nbsp;<code>num1</code>&nbsp;和&nbsp;<code>num2</code>&nbsp;的乘积，它们的乘积也表示为字符串形式。</p>

<p><strong>注意：</strong>不能使用任何内置的 BigInteger 库或直接将输入转换为整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> num1 = "2", num2 = "3"
<strong>输出:</strong> "6"</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> num1 = "123", num2 = "456"
<strong>输出:</strong> "56088"</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num1.length, num2.length &lt;= 200</code></li>
	<li><code>num1</code>&nbsp;和 <code>num2</code>&nbsp;只能由数字组成。</li>
	<li><code>num1</code>&nbsp;和 <code>num2</code>&nbsp;都不包含任何前导零，除了数字0本身。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学乘法模拟**

假设 `num1` 和 `num2` 的长度分别为 $m$ 和 $n$，则它们的乘积的长度最多为 $m + n$。

证明如下：

-   如果 `num1` 和 `num2` 都取最小值，那么它们的乘积为 ${10}^{m - 1} \times {10}^{n - 1} = {10}^{m + n - 2}$，长度为 $m + n - 1$。
-   如果 `num1` 和 `num2` 都取最大值，那么它们的乘积为 $({10}^m - 1) \times ({10}^n - 1) = {10}^{m + n} - {10}^m - {10}^n + 1$，长度为 $m + n$。

因此，我们可以申请一个长度为 $m + n$ 的数组，用于存储乘积的每一位。

从低位到高位，依次计算乘积的每一位，最后将数组转换为字符串即可。

注意判断最高位是否为 $0$，如果是，则去掉。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m + n)$。其中 $m$ 和 $n$ 分别为 `num1` 和 `num2` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def multiply(self, num1: str, num2: str) -> str:
        if num1 == "0" or num2 == "0":
            return "0"
        m, n = len(num1), len(num2)
        arr = [0] * (m + n)
        for i in range(m - 1, -1, -1):
            a = int(num1[i])
            for j in range(n - 1, -1, -1):
                b = int(num2[j])
                arr[i + j + 1] += a * b
        for i in range(m + n - 1, 0, -1):
            arr[i - 1] += arr[i] // 10
            arr[i] %= 10
        i = 0 if arr[0] else 1
        return "".join(str(x) for x in arr[i:])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] arr = new int[m + n];
        for (int i = m - 1; i >= 0; --i) {
            int a = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; --j) {
                int b = num2.charAt(j) - '0';
                arr[i + j + 1] += a * b;
            }
        }
        for (int i = arr.length - 1; i > 0; --i) {
            arr[i - 1] += arr[i] / 10;
            arr[i] %= 10;
        }
        int i = arr[0] == 0 ? 1 : 0;
        StringBuilder ans = new StringBuilder();
        for (; i < arr.length; ++i) {
            ans.append(arr[i]);
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string multiply(string num1, string num2) {
        if (num1 == "0" || num2 == "0") {
            return "0";
        }
        int m = num1.size(), n = num2.size();
        vector<int> arr(m + n);
        for (int i = m - 1; i >= 0; --i) {
            int a = num1[i] - '0';
            for (int j = n - 1; j >= 0; --j) {
                int b = num2[j] - '0';
                arr[i + j + 1] += a * b;
            }
        }
        for (int i = arr.size() - 1; i; --i) {
            arr[i - 1] += arr[i] / 10;
            arr[i] %= 10;
        }
        int i = arr[0] ? 0 : 1;
        string ans;
        for (; i < arr.size(); ++i) {
            ans += '0' + arr[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func multiply(num1 string, num2 string) string {
	m, n := len(num1), len(num2)
	res := make([]int, m+n)
	mul := func(b, i int) {
		for j, t := m-1, 0; j >= 0 || t > 0; i, j = i+1, j-1 {
			if j >= 0 {
				a := int(num1[j] - '0')
				t += a * b
			}
			res[i] += t % 10
			if res[i] >= 10 {
				res[i] %= 10
				res[i+1]++
			}
			t /= 10
		}
	}
	for i := 0; i < n; i++ {
		b := num2[n-1-i] - '0'
		mul(int(b), i)
	}
	var ans []byte
	for _, v := range res {
		ans = append(ans, byte(v+'0'))
	}
	for len(ans) > 1 && ans[len(ans)-1] == '0' {
		ans = ans[:len(ans)-1]
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return string(ans)
}
```

### **TypeScript**

```ts
function multiply(num1: string, num2: string): string {
    if ([num1, num2].includes('0')) return '0';
    const n1 = num1.length,
        n2 = num2.length;
    let ans = '';
    for (let i = 0; i < n1; i++) {
        let cur1 = parseInt(num1.charAt(n1 - i - 1), 10);
        let sum = '';
        for (let j = 0; j < n2; j++) {
            let cur2 = parseInt(num2.charAt(n2 - j - 1), 10);
            sum = addString(sum, cur1 * cur2 + '0'.repeat(j));
        }
        ans = addString(ans, sum + '0'.repeat(i));
    }
    return ans;
}

function addString(s1: string, s2: string): string {
    const n1 = s1.length,
        n2 = s2.length;
    let ans = [];
    let sum = 0;
    for (let i = 0; i < n1 || i < n2 || sum > 0; i++) {
        let num1 = i < n1 ? parseInt(s1.charAt(n1 - i - 1), 10) : 0;
        let num2 = i < n2 ? parseInt(s2.charAt(n2 - i - 1), 10) : 0;
        sum += num1 + num2;
        ans.unshift(sum % 10);
        sum = Math.floor(sum / 10);
    }
    return ans.join('');
}
```

```ts
function multiply(num1: string, num2: string): string {
    if (num1 === '0' || num2 === '0') {
        return '0';
    }

    const n = num1.length;
    const m = num2.length;
    const res = [];
    for (let i = 0; i < n; i++) {
        const a = Number(num1[n - i - 1]);
        let sum = 0;
        for (let j = 0; j < m || sum !== 0; j++) {
            const b = Number(num2[m - j - 1] ?? 0);
            sum += a * b + (res[i + j] ?? 0);
            res[i + j] = sum % 10;
            sum = Math.floor(sum / 10);
        }
    }

    return res.reverse().join('');
}
```

### **Rust**

```rust
impl Solution {
    pub fn multiply(num1: String, num2: String) -> String {
        if num1 == "0" || num2 == "0" {
            return String::from("0");
        }
        let (num1, num2) = (num1.as_bytes(), num2.as_bytes());
        let (n, m) = (num1.len(), num2.len());
        let mut res = vec![];
        for i in 0..n {
            let a = num1[n - i - 1] - b'0';
            let mut sum = 0;
            let mut j = 0;
            while j < m || sum != 0 {
                if i + j == res.len() {
                    res.push(0)
                }
                let b = num2.get(m - j - 1).unwrap_or(&b'0') - b'0';
                sum += a * b + res[i + j];
                res[i + j] = sum % 10;
                sum /= 10;
                j += 1;
            }
        }
        res.into_iter()
            .rev()
            .map(|v| char::from(v + b'0'))
            .collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
