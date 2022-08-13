# [482. 密钥格式化](https://leetcode.cn/problems/license-key-formatting)

[English Version](/solution/0400-0499/0482.License%20Key%20Formatting/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个许可密钥字符串 <code>s</code>，仅由字母、数字字符和破折号组成。字符串由 <code>n</code> 个破折号分成 <code>n + 1</code> 组。你也会得到一个整数 <code>k</code> 。</p>

<p>我们想要重新格式化字符串&nbsp;<code>s</code>，使每一组包含 <code>k</code> 个字符，除了第一组，它可以比 <code>k</code> 短，但仍然必须包含至少一个字符。此外，两组之间必须插入破折号，并且应该将所有小写字母转换为大写字母。</p>

<p>返回 <em>重新格式化的许可密钥</em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>S = "5F3Z-2e-9-w", k = 4
<strong>输出：</strong>"5F3Z-2E9W"
<strong>解释：</strong>字符串 S 被分成了两个部分，每部分 4 个字符；
&nbsp;    注意，两个额外的破折号需要删掉。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>S = "2-5g-3-J", k = 2
<strong>输出：</strong>"2-5G-3J"
<strong>解释：</strong>字符串 S 被分成了 3 个部分，按照前面的规则描述，第一部分的字符可以少于给定的数量，其余部分皆为 2 个字符。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含字母、数字和破折号&nbsp;<code>'-'</code>.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

简单模拟。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def licenseKeyFormatting(self, s: str, k: int) -> str:
        s = s.replace('-', '').upper()
        res = []
        cnt = (len(s) % k) or k
        t = 0
        for i, c in enumerate(s):
            res.append(c)
            t += 1
            if t == cnt:
                t = 0
                cnt = k
                if i != len(s) - 1:
                    res.append('-')
        return ''.join(res)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String licenseKeyFormatting(String s, int k) {
        s = s.replace("-", "").toUpperCase();
        StringBuilder sb = new StringBuilder();
        int t = 0;
        int cnt = s.length() % k;
        if (cnt == 0) {
            cnt = k;
        }
        for (int i = 0; i < s.length(); ++i) {
            sb.append(s.charAt(i));
            ++t;
            if (t == cnt) {
                t = 0;
                cnt = k;
                if (i != s.length() - 1) {
                    sb.append('-');
                }
            }
        }
        return sb.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string licenseKeyFormatting(string s, int k) {
        string ss = "";
        for (char c : s) {
            if (c == '-') continue;
            if ('a' <= c && c <= 'z') c += 'A' - 'a';
            ss += c;
        }
        int cnt = ss.size() % k;
        if (cnt == 0) cnt = k;
        int t = 0;
        string res = "";
        for (int i = 0; i < ss.size(); ++i) {
            res += ss[i];
            ++t;
            if (t == cnt) {
                t = 0;
                cnt = k;
                if (i != ss.size() - 1) res += '-';
            }
        }
        return res;
    }
};
```

### **Go**

```go
func licenseKeyFormatting(s string, k int) string {
	s = strings.ReplaceAll(s, "-", "")
	cnt := len(s) % k
	if cnt == 0 {
		cnt = k
	}
	t := 0
	res := []byte{}
	for i, c := range s {
		res = append(res, byte(unicode.ToUpper(c)))
		t++
		if t == cnt {
			t = 0
			cnt = k
			if i != len(s)-1 {
				res = append(res, byte('-'))
			}
		}
	}
	return string(res)
}
```

### **...**

```

```

<!-- tabs:end -->
