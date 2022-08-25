# [2330. 有效的回文 IV](https://leetcode.cn/problems/valid-palindrome-iv)

[English Version](/solution/2300-2399/2330.Valid%20Palindrome%20IV/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始、仅由小写英文字母组成的字符串 <code>s</code> 。在一步操作中，你可以将 <code>s</code> 中的任一字符更改为其他任何字符。</p>

<p>如果你能在 <strong>恰</strong> 执行一到两步操作后使 <code>s</code> 变成一个回文，则返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> s = "abcdba"
<strong>输出:</strong> true
<strong>解释:</strong> 能让 s 变成回文，且只用了一步操作的方案如下:
- 将 s[2] 变成 'd' ，得到 s = "abddba" 。
执行一步操作让 s 变成一个回文，所以返回 true 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入:</strong> s = "aa"
<strong>输出:</strong> true
<strong>解释:</strong> 能让 s 变成回文，且只用了两步操作的方案如下:
- 将 s[0] 变成 'b' ，得到 s = "ba" 。
- 将 s[1] 变成 'b' ，得到s = "bb" 。
执行两步操作让 s 变成一个回文，所以返回 true 。 
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入:</strong> s = "abcdef"
<strong>输出:</strong> false
<strong>解释:</strong> 不存在能在两步操作以内将 s 变成回文的办法，所以返回 false 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def makePalindrome(self, s: str) -> bool:
        i, j = 0, len(s) - 1
        t = 0
        while i < j:
            if s[i] != s[j]:
                t += 1
            i, j = i + 1, j - 1
        return t <= 2
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean makePalindrome(String s) {
        int t = 0;
        for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                ++t;
            }
        }
        return t <= 2;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool makePalindrome(string s) {
        int t = 0;
        for (int i = 0, j = s.size() - 1; i < j; ++i, --j) t += s[i] != s[j];
        return t <= 2;
    }
};
```

### **Go**

```go
func makePalindrome(s string) bool {
	t := 0
	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		if s[i] != s[j] {
			t++
		}
	}
	return t <= 2
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
