# [2083. 求以相同字母开头和结尾的子串总数](https://leetcode.cn/problems/substrings-that-begin-and-end-with-the-same-letter)

[English Version](/solution/2000-2099/2083.Substrings%20That%20Begin%20and%20End%20With%20the%20Same%20Letter/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个仅由小写英文字母组成的，&nbsp; 下标从 <code>0</code> 开始的字符串 <code>s</code> 。返回 <code>s</code> 中以相同字符开头和结尾的子字符串总数。</p>

<p>子字符串是字符串中连续的非空字符序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abcba"
<strong>输出：</strong>7
<strong>解释：</strong>
以相同字母开头和结尾的长度为 1 的子串是："a"、"b"、"c"、"b" 和 "a" 。
以相同字母开头和结尾的长度为 3 的子串是："bcb" 。
以相同字母开头和结尾的长度为 5 的子串是："abcba" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abacad"
<strong>输出：</strong>9
<strong>解释：</strong>
以相同字母开头和结尾的长度为 1 的子串是："a"、"b"、"a"、"c"、"a" 和 "d" 。
以相同字母开头和结尾的长度为 3 的子串是："aba" 和 "aca" 。
以相同字母开头和结尾的长度为 5 的子串是："abaca" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "a"
<strong>输出：</strong>1
<strong>解释：</strong>
只有一个，以相同字母开头和结尾的长度为 1 的子串是："a"。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

前缀和 + 计数器。

用 counter 累计以每个字符结尾的字符串的个数。

遍历字符串 s 中每个字符 c，累加以 c 字符串结尾的字符串个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfSubstrings(self, s: str) -> int:
        counter = [0] * 26
        ans = 0
        for c in s:
            i = ord(c) - ord('a')
            counter[i] += 1
            ans += counter[i]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long numberOfSubstrings(String s) {
        int[] counter = new int[26];
        long ans = 0;
        for (char c : s.toCharArray()) {
            int i = c - 'a';
            ++counter[i];
            ans += counter[i];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long numberOfSubstrings(string s) {
        vector<int> counter(26);
        long long ans = 0;
        for (char c : s) {
            int i = c - 'a';
            ++counter[i];
            ans += counter[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func numberOfSubstrings(s string) int64 {
	var ans int64
	counter := make([]int64, 26)
	for _, c := range s {
		i := c - 'a'
		counter[i]++
		ans += counter[i]
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
