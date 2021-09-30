# [1332. 删除回文子序列](https://leetcode-cn.com/problems/remove-palindromic-subsequences)

[English Version](/solution/1300-1399/1332.Remove%20Palindromic%20Subsequences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>，它仅由字母&nbsp;<code>&#39;a&#39;</code> 和 <code>&#39;b&#39;</code>&nbsp;组成。每一次删除操作都可以从 <code>s</code> 中删除一个回文 <strong>子序列</strong>。</p>

<p>返回删除给定字符串中所有字符（字符串为空）的最小删除次数。</p>

<p>「子序列」定义：如果一个字符串可以通过删除原字符串某些字符而不改变原字符顺序得到，那么这个字符串就是原字符串的一个子序列。</p>

<p>「回文」定义：如果一个字符串向后和向前读是一致的，那么这个字符串就是一个回文。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;ababa&quot;
<strong>输出：</strong>1
<strong>解释：</strong>字符串本身就是回文序列，只需要删除一次。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;abb&quot;
<strong>输出：</strong>2
<strong>解释：</strong>&quot;<strong>a</strong>bb&quot; -&gt; &quot;<strong>bb</strong>&quot; -&gt; &quot;&quot;. 
先删除回文子序列 &quot;a&quot;，然后再删除 &quot;bb&quot;。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;baabb&quot;
<strong>输出：</strong>2
<strong>解释：</strong>&quot;<strong>baa</strong>b<strong>b</strong>&quot; -&gt; &quot;b&quot; -&gt; &quot;&quot;. 
先删除回文子序列 &quot;baab&quot;，然后再删除 &quot;b&quot;。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>s = &quot;&quot;
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> 仅包含字母&nbsp;&#39;a&#39;&nbsp; 和 &#39;b&#39;</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

- 如果字符串 s 是个空串，直接返回 0。
- 如果字符串 s 本身是个回文串，那么只需要删除 1 次。
- 如果字符串 s 不是个回文串，我们注意到 s 最多只有两种字母 "a", "b"，并且删除的是一个子序列，因此可以先删除所有字母 "a" (`"aaa...aaa"` 是个回文串)，再删除所有字母 "b"，即可使得字符串变为空。因此需要的删除次数是 2 次。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def removePalindromeSub(self, s: str) -> int:
        if not s:
            return 0
        if s[::-1] == s:
            return 1
        return 2
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int removePalindromeSub(String s) {
        if (s.length() == 0) {
            return 0;
        }
        if (new StringBuilder(s).reverse().toString().equals(s)) {
            return 1;
        }
        return 2;
    }
}
```

### **TypeScript**

```ts
function removePalindromeSub(s: string): number {
    if (s.length == 0) return 0;
    if (s == s.split('').reverse().join('')) return 1;
    return 2;
};
```

### **C++**

```cpp
class Solution {
public:
    int removePalindromeSub(string s) {
        if (s.empty())
            return 0;
        string t = s;
        reverse(s.begin(), s.end());
        if (s == t)
            return 1;
        return 2;
    }
};
```

### **Go**

```go
func removePalindromeSub(s string) int {
	if len(s) == 0 {
		return 0
	}
	if s == reverse(s) {
		return 1
	}
	return 2
}

func reverse(s string) string {
	r := []byte(s)
	for i, j := 0, len(r)-1; i < j; i, j = i+1, j-1 {
		r[i], r[j] = r[j], r[i]
	}
	return string(r)
}
```

### **...**

```

```

<!-- tabs:end -->
