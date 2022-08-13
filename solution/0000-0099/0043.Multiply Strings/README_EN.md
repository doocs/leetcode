# [43. Multiply Strings](https://leetcode.com/problems/multiply-strings)

[中文文档](/solution/0000-0099/0043.Multiply%20Strings/README.md)

## Description

<p>Given two non-negative integers <code>num1</code> and <code>num2</code> represented as strings, return the product of <code>num1</code> and <code>num2</code>, also represented as a string.</p>

<p><strong>Note:</strong>&nbsp;You must not use any built-in BigInteger library or convert the inputs to integer directly.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> num1 = "2", num2 = "3"
<strong>Output:</strong> "6"
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> num1 = "123", num2 = "456"
<strong>Output:</strong> "56088"
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num1.length, num2.length &lt;= 200</code></li>
	<li><code>num1</code> and <code>num2</code> consist of digits only.</li>
	<li>Both <code>num1</code> and <code>num2</code>&nbsp;do not contain any leading zero, except the number <code>0</code> itself.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def multiply(self, num1: str, num2: str) -> str:
        def mul(b, i):
            j, t = m - 1, 0
            while j >= 0 or t:
                if j >= 0:
                    a = int(num1[j])
                    t += a * b
                res[i] += t % 10
                if res[i] >= 10:
                    res[i] %= 10
                    res[i + 1] += 1
                i, j = i + 1, j - 1
                t //= 10

        m, n = len(num1), len(num2)
        res = [0] * (m + n)
        for i in range(n):
            b = int(num2[n - 1 - i])
            mul(b, i)
        while len(res) > 1 and res[-1] == 0:
            res.pop()
        return ''.join([str(v) for v in res[::-1]])
```

### **Java**

```java
class Solution {
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] res = new int[m + n];
        for (int i = 0; i < n; ++i) {
            int b = num2.charAt(n - 1 - i) - '0';
            mul(num1, b, i, res);
        }
        StringBuilder ans = new StringBuilder();
        for (int v : res) {
            ans.append(v);
        }
        while (ans.length() > 1 && ans.charAt(ans.length() - 1) == '0') {
            ans.deleteCharAt(ans.length() - 1);
        }
        return ans.reverse().toString();
    }

    private void mul(String A, int b, int i, int[] res) {
        for (int j = A.length() - 1, t = 0; j >= 0 || t > 0; --j) {
            if (j >= 0) {
                int a = A.charAt(j) - '0';
                t += a * b;
            }
            res[i++] += t % 10;
            if (res[i - 1] >= 10) {
                res[i - 1] %= 10;
                ++res[i];
            }
            t /= 10;
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string multiply(string num1, string num2) {
        int m = num1.size(), n = num2.size();
        vector<int> res(m + n);
        for (int i = 0; i < n; ++i) {
            int b = num2[n - 1 - i] - '0';
            mul(num1, b, i, res);
        }
        string ans = "";
        for (int v : res) ans.push_back(v + '0');
        while (ans.size() > 1 && ans.back() == '0') ans.pop_back();
        reverse(ans.begin(), ans.end());
        return ans;
    }

    void mul(string A, int b, int i, vector<int>& res) {
        for (int j = A.size() - 1, t = 0; j >= 0 || t > 0; --j) {
            if (j >= 0) {
                int a = A[j] - '0';
                t += a * b;
            }
            res[i++] += t % 10;
            if (res[i - 1] >= 10) {
                res[i - 1] %= 10;
                ++res[i];
            }
            t /= 10;
        }
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
