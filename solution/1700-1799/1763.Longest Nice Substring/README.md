# [1763. 最长的美好子字符串](https://leetcode.cn/problems/longest-nice-substring)

[English Version](/solution/1700-1799/1763.Longest%20Nice%20Substring/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>当一个字符串 <code>s</code> 包含的每一种字母的大写和小写形式 <strong>同时</strong> 出现在 <code>s</code> 中，就称这个字符串 <code>s</code> 是 <strong>美好</strong> 字符串。比方说，<code>"abABB"</code> 是美好字符串，因为 <code>'A'</code> 和 <code>'a'</code> 同时出现了，且 <code>'B'</code> 和 <code>'b'</code> 也同时出现了。然而，<code>"abA"</code> 不是美好字符串因为 <code>'b'</code> 出现了，而 <code>'B'</code> 没有出现。</p>

<p>给你一个字符串 <code>s</code> ，请你返回 <code>s</code> 最长的 <strong>美好子字符串</strong> 。如果有多个答案，请你返回 <strong>最早</strong> 出现的一个。如果不存在美好子字符串，请你返回一个空字符串。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "YazaAay"
<b>输出：</b>"aAa"
<strong>解释：</strong>"aAa" 是一个美好字符串，因为这个子串中仅含一种字母，其小写形式 'a' 和大写形式 'A' 也同时出现了。
"aAa" 是最长的美好子字符串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "Bb"
<b>输出：</b>"Bb"
<b>解释：</b>"Bb" 是美好字符串，因为 'B' 和 'b' 都出现了。整个字符串也是原字符串的子字符串。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>s = "c"
<b>输出：</b>""
<b>解释：</b>没有美好子字符串。</pre>

<p><strong>示例 4：</strong></p>

<pre>
<b>输入：</b>s = "dDzeE"
<b>输出：</b>"dD"
<strong>解释：</strong>"dD" 和 "eE" 都是最长美好子字符串。
由于有多个美好子字符串，返回 "dD" ，因为它出现得最早。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 100</code></li>
	<li><code>s</code> 只包含大写和小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestNiceSubstring(self, s: str) -> str:
        n = len(s)
        ans = ''
        for i in range(n):
            lower = upper = 0
            for j in range(i, n):
                if s[j].islower():
                    lower |= 1 << (ord(s[j]) - ord('a'))
                else:
                    upper |= 1 << (ord(s[j]) - ord('A'))
                if lower == upper and j - i + 1 > len(ans):
                    ans = s[i : j + 1]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    public String longestNiceSubstring(String s) {
        int n = s.length();
        String ans = "";
        for (int i = 0; i < n; ++i) {
            int lower = 0, upper = 0;
            for (int j = i; j < n; ++j) {
                char c = s.charAt(j);
                if (Character.isLowerCase(c)) {
                    lower |= 1 << (c - 'a');
                } else {
                    upper |= 1 << (c - 'A');
                }
                if (lower == upper && j - i + 1 > ans.length()) {
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }
}

```

### **TypeScript**

```ts
function longestNiceSubstring(s: string): string {
    const n = s.length;
    let ans = '';
    for (let i = 0; i < n; i++) {
        let lower = 0,
            upper = 0;
        for (let j = i; j < n; j++) {
            const c = s.charCodeAt(j);
            if (c > 96) {
                lower |= 1 << (c - 97);
            } else {
                upper |= 1 << (c - 65);
            }
            if (lower == upper && j - i + 1 > ans.length) {
                ans = s.substring(i, j + 1);
            }
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    string longestNiceSubstring(string s) {
        int n = s.size();
        string ans = "";
        for (int i = 0; i < n; ++i) {
            int lower = 0, upper = 0;
            for (int j = i; j < n; ++j) {
                if (islower(s[j]))
                    lower |= 1 << (s[j] - 'a');
                else
                    upper |= 1 << (s[j] - 'A');
                if (lower == upper && j - i + 1 > ans.size()) ans = s.substr(i, j - i + 1);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func longestNiceSubstring(s string) (ans string) {
	for i := range s {
		lower, upper := 0, 0
		for j := i; j < len(s); j++ {
			if unicode.IsLower(rune(s[j])) {
				lower |= 1 << (s[j] - 'a')
			} else {
				upper |= 1 << (s[j] - 'A')
			}
			if lower == upper && j-i+1 > len(ans) {
				ans = s[i : j+1]
			}
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
