# [面试题 01.06. 字符串压缩](https://leetcode.cn/problems/compress-string-lcci)

[English Version](/lcci/01.06.Compress%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串<code>aabcccccaaa</code>会变为<code>a2b1c5a3</code>。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。</p>

<p> <strong>示例1:</strong></p>

<pre>
<strong> 输入</strong>："aabcccccaaa"
<strong> 输出</strong>："a2b1c5a3"
</pre>

<p> <strong>示例2:</strong></p>

<pre>
<strong> 输入</strong>："abbccd"
<strong> 输出</strong>："abbccd"
<strong> 解释</strong>："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
</pre>

<p><strong>提示：</strong></p>

<ol>
<li>字符串长度在[0, 50000]范围内。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

双指针遍历字符串求解。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
