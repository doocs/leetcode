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

**方法一：枚举两端字符 + 哈希表**

由于字符串中只包含小写字母，因此我们可以直接枚举所有的两端字符。对于每一对两端字符 $c$，我们找出它们在字符串中第一次和最后一次出现的位置 $l$ 和 $r$，如果 $r - l > 1$，说明找到了满足条件的回文序列，我们将 $[l+1,..r-1]$ 之间的字符去重后统计个数，即为以 $c$ 为两端字符的回文序列个数，加入答案中。

枚举结束后，即可得到答案。

时间复杂度 $O(n \times C)$，空间复杂度 $O(C)$，其中 $n$ 为字符串长度，而 $C$ 为字符集大小。本题中 $C = 26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countPalindromicSubsequence(self, s: str) -> int:
        ans = 0
        for c in ascii_lowercase:
            l, r = s.find(c), s.rfind(c)
            if r - l > 1:
                ans += len(set(s[l + 1: r]))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countPalindromicSubsequence(String s) {
        int ans = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            int l = s.indexOf(c), r = s.lastIndexOf(c);
            Set<Character> cs = new HashSet<>();
            for (int i = l + 1; i < r; ++i) {
                cs.add(s.charAt(i));
            }
            ans += cs.size();
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countPalindromicSubsequence(string s) {
        int ans = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            int l = s.find_first_of(c), r = s.find_last_of(c);
            unordered_set<char> cs;
            for (int i = l + 1; i < r; ++i) cs.insert(s[i]);
            ans += cs.size();
        }
        return ans;
    }
};
```

### **Go**

```go
func countPalindromicSubsequence(s string) (ans int) {
	for c := 'a'; c <= 'z'; c++ {
		l, r := strings.Index(s, string(c)), strings.LastIndex(s, string(c))
		cs := map[byte]struct{}{}
		for i := l + 1; i < r; i++ {
			cs[s[i]] = struct{}{}
		}
		ans += len(cs)
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
