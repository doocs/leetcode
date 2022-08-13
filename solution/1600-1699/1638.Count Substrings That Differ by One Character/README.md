# [1638. 统计只差一个字符的子串数目](https://leetcode.cn/problems/count-substrings-that-differ-by-one-character)

[English Version](/solution/1600-1699/1638.Count%20Substrings%20That%20Differ%20by%20One%20Character/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串 <code>s</code> 和 <code>t</code> ，请你找出 <code>s</code> 中的非空子串的数目，这些子串满足替换 <strong>一个不同字符</strong> 以后，是 <code>t</code> 串的子串。换言之，请你找到 <code>s</code> 和 <code>t</code> 串中 <strong>恰好</strong> 只有一个字符不同的子字符串对的数目。</p>

<p>比方说， <code>"<strong>compute</strong>r"</code> 和 <code>"<strong>computa</strong>tion"</code> 加粗部分只有一个字符不同： <code>'e'</code>/<code>'a'</code> ，所以这一对子字符串会给答案加 1 。</p>

<p>请你返回满足上述条件的不同子字符串对数目。</p>

<p>一个 <strong>子字符串</strong> 是一个字符串中连续的字符。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "aba", t = "baba"
<b>输出：</b>6
<strong>解释：</strong>以下为只相差 1 个字符的 s 和 t 串的子字符串对：
("<strong>a</strong>ba", "<strong>b</strong>aba")
("<strong>a</strong>ba", "ba<strong>b</strong>a")
("ab<strong>a</strong>", "<strong>b</strong>aba")
("ab<strong>a</strong>", "ba<strong>b</strong>a")
("a<strong>b</strong>a", "b<strong>a</strong>ba")
("a<strong>b</strong>a", "bab<strong>a</strong>")
加粗部分分别表示 s 和 t 串选出来的子字符串。
</pre>

<strong>示例 2：</strong>

<pre>
<b>输入：</b>s = "ab", t = "bb"
<b>输出：</b>3
<strong>解释：</strong>以下为只相差 1 个字符的 s 和 t 串的子字符串对：
("<strong>a</strong>b", "<strong>b</strong>b")
("<strong>a</strong>b", "b<strong>b</strong>")
("<strong>ab</strong>", "<strong>bb</strong>")
加粗部分分别表示 s 和 t 串选出来的子字符串。
</pre>

<strong>示例 3：</strong>

<pre>
<b>输入：</b>s = "a", t = "a"
<b>输出：</b>0
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<b>输入：</b>s = "abe", t = "bbc"
<b>输出：</b>10
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length, t.length <= 100</code></li>
	<li><code>s</code> 和 <code>t</code> 都只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举**

枚举不同的那个字符，然后向两边扩展。

时间复杂度 $O(m \times n \times min(m, n))$ 。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countSubstrings(self, s: str, t: str) -> int:
        m, n = len(s), len(t)
        ans = 0
        for i in range(m):
            for j in range(n):
                if s[i] != t[j]:
                    l = r = 1
                    while i - l >= 0 and j - l >= 0 and s[i - l] == t[j - l]:
                        l += 1
                    while i + r < m and j + r < n and s[i + r] == t[j + r]:
                        r += 1
                    ans += l * r
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countSubstrings(String s, String t) {
        int m = s.length(), n = t.length();
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (s.charAt(i) != t.charAt(j)) {
                    int l = 1, r = 1;
                    while (i - l >= 0 && j - l >= 0 && s.charAt(i - l) == t.charAt(j - l)) {
                        ++l;
                    }
                    while (i + r < m && j + r < n && s.charAt(i + r) == t.charAt(j + r)) {
                        ++r;
                    }
                    ans += l * r;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countSubstrings(string s, string t) {
        int m = s.size(), n = t.size();
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (s[i] == t[j]) continue;
                int l = 1, r = 1;
                while (i - l >= 0 && j - l >= 0 && s[i - l] == t[j - l]) ++l;
                while (i + r < m && j + r < n && s[i + r] == t[j + r]) ++r;
                ans += l * r;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countSubstrings(s string, t string) int {
	m, n := len(s), len(t)
	ans := 0
	for i := range s {
		for j := range t {
			if s[i] == t[j] {
				continue
			}
			l, r := 1, 1
			for i-l >= 0 && j-l >= 0 && s[i-l] == t[j-l] {
				l++
			}
			for i+r < m && j+r < n && s[i+r] == t[j+r] {
				r++
			}
			ans += l * r
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
