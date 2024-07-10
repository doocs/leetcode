---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0482.License%20Key%20Formatting/README.md
tags:
    - 字符串
---

<!-- problem:start -->

# [482. 密钥格式化](https://leetcode.cn/problems/license-key-formatting)

[English Version](/solution/0400-0499/0482.License%20Key%20Formatting/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们先统计出字符串 $s$ 中除去破折号之外的字符个数，并对 $k$ 取模，得到第一组字符的个数。如果为 $0$，则第一组字符个数为 $k$，否则为取模的结果。

接着我们遍历字符串 $s$，对于每个字符，如果是破折号，则跳过；否则将其转换为大写字母，并将其加入答案字符串中。同时，我们维护一个计数器 $cnt$，表示当前组还剩余的字符个数，当 $cnt$ 减为 $0$ 时，我们需要更新 $cnt$ 为 $k$，并且如果当前字符不是最后一个字符，我们需要在答案字符串中加入一个破折号。

最后，我们移除答案字符串末尾的破折号，并返回答案字符串。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。忽略答案字符串的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def licenseKeyFormatting(self, s: str, k: int) -> str:
        n = len(s)
        cnt = (n - s.count("-")) % k or k
        ans = []
        for i, c in enumerate(s):
            if c == "-":
                continue
            ans.append(c.upper())
            cnt -= 1
            if cnt == 0:
                cnt = k
                if i != n - 1:
                    ans.append("-")
        return "".join(ans).rstrip("-")
```

#### Java

```java
class Solution {
    public String licenseKeyFormatting(String s, int k) {
        int n = s.length();
        int cnt = (int) (n - s.chars().filter(ch -> ch == '-').count()) % k;
        if (cnt == 0) {
            cnt = k;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '-') {
                continue;
            }
            ans.append(Character.toUpperCase(c));
            --cnt;
            if (cnt == 0) {
                cnt = k;
                if (i != n - 1) {
                    ans.append('-');
                }
            }
        }
        if (ans.length() > 0 && ans.charAt(ans.length() - 1) == '-') {
            ans.deleteCharAt(ans.length() - 1);
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string licenseKeyFormatting(string s, int k) {
        int n = s.length();
        int cnt = (n - count(s.begin(), s.end(), '-')) % k;
        if (cnt == 0) {
            cnt = k;
        }
        string ans;
        for (int i = 0; i < n; ++i) {
            char c = s[i];
            if (c == '-') {
                continue;
            }
            ans += toupper(c);
            if (--cnt == 0) {
                cnt = k;
                if (i != n - 1) {
                    ans += '-';
                }
            }
        }
        if (!ans.empty() && ans.back() == '-') {
            ans.pop_back();
        }
        return ans;
    }
};
```

#### Go

```go
func licenseKeyFormatting(s string, k int) string {
	n := len(s)
	cnt := (n - strings.Count(s, "-")) % k
	if cnt == 0 {
		cnt = k
	}

	var ans strings.Builder
	for i := 0; i < n; i++ {
		c := s[i]
		if c == '-' {
			continue
		}
		if cnt == 0 {
			cnt = k
			ans.WriteByte('-')
		}
		ans.WriteRune(unicode.ToUpper(rune(c)))
		cnt--
	}

	return ans.String()
}
```

#### TypeScript

```ts
function licenseKeyFormatting(s: string, k: number): string {
    const n = s.length;
    let cnt = (n - (s.match(/-/g) || []).length) % k || k;
    const ans: string[] = [];
    for (let i = 0; i < n; i++) {
        const c = s[i];
        if (c === '-') {
            continue;
        }
        ans.push(c.toUpperCase());
        if (--cnt === 0) {
            cnt = k;
            if (i !== n - 1) {
                ans.push('-');
            }
        }
    }
    while (ans.at(-1) === '-') {
        ans.pop();
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
