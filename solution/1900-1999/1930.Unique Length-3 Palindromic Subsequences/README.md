# [1930. 长度为 3 的不同回文子序列](https://leetcode.cn/problems/unique-length-3-palindromic-subsequences)

[English Version](/solution/1900-1999/1930.Unique%20Length-3%20Palindromic%20Subsequences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，返回 <code>s</code> 中 <strong>长度为 3 </strong>的<strong>不同回文子序列</strong> 的个数。</p>

<p>即便存在多种方法来构建相同的子序列，但相同的子序列只计数一次。</p>

<p><strong>回文</strong> 是正着读和反着读一样的字符串。</p>

<p><strong>子序列</strong> 是由原字符串删除其中部分字符（也可以不删除）且不改变剩余字符之间相对顺序形成的一个新字符串。</p>

<ul>
	<li>例如，<code>"ace"</code> 是 <code>"<strong><em>a</em></strong>b<strong><em>c</em></strong>d<strong><em>e</em></strong>"</code> 的一个子序列。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aabca"
<strong>输出：</strong>3
<strong>解释：</strong>长度为 3 的 3 个回文子序列分别是：
- "aba" ("<strong><em>a</em></strong>a<strong><em>b</em></strong>c<strong><em>a</em></strong>" 的子序列)
- "aaa" ("<strong><em>aa</em></strong>bc<strong><em>a</em></strong>" 的子序列)
- "aca" ("<strong><em>a</em></strong>ab<strong><em>ca</em></strong>" 的子序列)
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "adc"
<strong>输出：</strong>0
<strong>解释：</strong>"adc" 不存在长度为 3 的回文子序列。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "bbcbaba"
<strong>输出：</strong>4
<strong>解释：</strong>长度为 3 的 4 个回文子序列分别是：
- "bbb" ("<strong><em>bb</em></strong>c<strong><em>b</em></strong>aba" 的子序列)
- "bcb" ("<strong><em>b</em></strong>b<strong><em>cb</em></strong>aba" 的子序列)
- "bab" ("<strong><em>b</em></strong>bcb<strong><em>ab</em></strong>a" 的子序列)
- "aba" ("bbcb<strong><em>aba</em></strong>" 的子序列)
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 <= s.length <= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countPalindromicSubsequence(self, s: str) -> int:
        res = 0
        for i in range(26):
            c = chr(ord('a') + i)
            if c in s:
                l, r = s.index(c), s.rindex(c)
                chars = {s[j] for j in range(l + 1, r)}
                res += len(chars)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countPalindromicSubsequence(String s) {
        int res = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            int l = s.indexOf(c), r = s.lastIndexOf(c);
            Set<Character> chars = new HashSet<>();
            for (int i = l + 1; i < r; ++i) {
                chars.add(s.charAt(i));
            }
            res += chars.size();
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countPalindromicSubsequence(string s) {
        int res = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            int l = s.find_first_of(c), r = s.find_last_of(c);
            unordered_set<char> chars;
            for (int i = l + 1; i < r; ++i) {
                chars.insert(s[i]);
            }
            res += chars.size();
        }
        return res;
    }
};
```

### **Go**

```go
func countPalindromicSubsequence(s string) int {
	res := 0
	for c := 'a'; c <= 'z'; c++ {
		l, r := strings.Index(s, string(c)), strings.LastIndex(s, string(c))
		chars := make(map[byte]bool)
		for i := l + 1; i < r; i++ {
			chars[s[i]] = true
		}
		res += len(chars)
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
