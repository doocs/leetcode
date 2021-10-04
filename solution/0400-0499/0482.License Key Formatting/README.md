# [482. 密钥格式化](https://leetcode-cn.com/problems/license-key-formatting)

[English Version](/solution/0400-0499/0482.License%20Key%20Formatting/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个密钥字符串 S ，只包含字母，数字以及 &#39;-&#39;（破折号）。其中， N 个 &#39;-&#39; 将字符串分成了 N+1 组。</p>

<p>给你一个数字 K，请你重新格式化字符串，使每个分组恰好包含 K 个字符。特别地，第一个分组包含的字符个数必须小于等于 K，但至少要包含 1 个字符。两个分组之间需要用 &#39;-&#39;（破折号）隔开，并且将所有的小写字母转换为大写字母。</p>

<p>给定非空字符串 S 和数字 K，按照上面描述的规则进行格式化。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>S = &quot;5F3Z-2e-9-w&quot;, K = 4
<strong>输出：</strong>&quot;5F3Z-2E9W&quot;
<strong>解释：</strong>字符串 S 被分成了两个部分，每部分 4 个字符；
&nbsp;    注意，两个额外的破折号需要删掉。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>S = &quot;2-5g-3-J&quot;, K = 2
<strong>输出：</strong>&quot;2-5G-3J&quot;
<strong>解释：</strong>字符串 S 被分成了 3 个部分，按照前面的规则描述，第一部分的字符可以少于给定的数量，其余部分皆为 2 个字符。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ol>
	<li>S 的长度可能很长，请按需分配大小。K 为正整数。</li>
	<li>S 只包含字母数字（a-z，A-Z，0-9）以及破折号&#39;-&#39;</li>
	<li>S 非空</li>
</ol>

<p>&nbsp;</p>


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
        for (char c : s)
        {
            if (c == '-') continue;
            if ('a' <= c && c <= 'z') c += 'A' - 'a';
            ss += c;
        }
        int cnt = ss.size() % k;
        if (cnt == 0) cnt = k;
        int t = 0;
        string res = "";
        for (int i = 0; i < ss.size(); ++i)
        {
            res += ss[i];
            ++t;
            if (t == cnt)
            {
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
