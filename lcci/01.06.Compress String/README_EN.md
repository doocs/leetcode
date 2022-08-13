# [01.06. Compress String](https://leetcode.cn/problems/compress-string-lcci)

[中文文档](/lcci/01.06.Compress%20String/README.md)

## Description

<p>Implement a method to perform basic string compression using the counts of repeated characters. For example, the string aabcccccaaa would become a2blc5a3. If the &quot;compressed&quot; string would not become smaller than the original string, your method should return the original string. You can assume the string has only uppercase and lowercase letters (a - z).</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>&quot;aabcccccaaa&quot;

<strong>Output: </strong>&quot;a2b1c5a3&quot;

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>&quot;abbccd&quot;

<strong>Output: </strong>&quot;abbccd&quot;

<strong>Explanation: </strong>

The compressed string is &quot;a1b2c2d1&quot;, which is longer than the original string.

</pre>

<p><strong>Note:</strong></p>

-   `0 <= S.length <= 50000`

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def compressString(self, S: str) -> str:
        if len(S) < 2:
            return S
        p, q = 0, 1
        res = ''
        while q < len(S):
            if S[p] != S[q]:
                res += S[p] + str(q - p)
                p = q
            q += 1
        res += S[p] + str(q - p)
        return res if len(res) < len(S) else S
```

### **Java**

```java
class Solution {
    public String compressString(String S) {
        int n;
        if (S == null || (n = S.length()) < 2) {
            return S;
        }
        int p = 0, q = 1;
        StringBuilder sb = new StringBuilder();
        while (q < n) {
            if (S.charAt(p) != S.charAt(q)) {
                sb.append(S.charAt(p)).append(q - p);
                p = q;
            }
            ++q;
        }
        sb.append(S.charAt(p)).append(q - p);
        String res = sb.toString();
        return res.length() < n ? res : S;
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} S
 * @return {string}
 */
var compressString = function (S) {
    if (!S) return S;
    let p = 0,
        q = 1;
    let res = '';
    while (q < S.length) {
        if (S[p] != S[q]) {
            res += S[p] + (q - p);
            p = q;
        }
        ++q;
    }
    res += S[p] + (q - p);
    return res.length < S.length ? res : S;
};
```

### **Go**

```go
func compressString(S string) string {
	n := len(S)
	if n == 0 {
		return S
	}
	var builder strings.Builder
	pre, cnt := S[0], 1
	for i := 1; i < n; i++ {
		if S[i] != pre {
			builder.WriteByte(pre)
			builder.WriteString(strconv.Itoa(cnt))
			cnt = 1
		} else {
			cnt++
		}
		pre = S[i]
	}
	builder.WriteByte(pre)
	builder.WriteString(strconv.Itoa(cnt))
	if builder.Len() >= n {
		return S
	}
	return builder.String()
}
```

### **Rust**

```rust
impl Solution {
    pub fn compress_string(s: String) -> String {
        let mut cs: Vec<char> = s.chars().collect();
        cs.push(' ');
        let mut res = vec![];
        let mut l = 0;
        let mut cur = cs[0];
        for i in 1..cs.len() {
            if cs[i] != cur {
                let count = (i - l).to_string();
                l = i;
                res.push(cur);
                cur = cs[i];
                for c in count.chars() {
                    res.push(c);
                }
            }
        }
        if res.len() >= cs.len() - 1 {
            s
        } else {
            res.iter().collect()
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
