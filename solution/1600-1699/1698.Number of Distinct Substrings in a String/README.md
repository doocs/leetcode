# [1698. 字符串的不同子字符串个数](https://leetcode.cn/problems/number-of-distinct-substrings-in-a-string)

[English Version](/solution/1600-1699/1698.Number%20of%20Distinct%20Substrings%20in%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串 <code>s</code>，返回 <code>s</code> 的不同子字符串的个数。</p>

<p>字符串的 <strong>子字符串 </strong>是由原字符串删除开头若干个字符（可能是 0 个）并删除结尾若干个字符（可能是 0 个）形成的字符串。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aabbaba"
<strong>输出：</strong>21
<strong>解释：</strong>不同子字符串的集合是 ["a","b","aa","bb","ab","ba","aab","abb","bab","bba","aba","aabb","abba","bbab","baba","aabba","abbab","bbaba","aabbab","abbaba","aabbaba"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abcdefg"
<strong>输出：</strong>28
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 500</code></li>
	<li><code>s</code> 由小写英文字母组成。</li>
</ul>

<p><b>进阶：</b>你可以以 <code>O(n)</code> 时间复杂度解决此问题吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countDistinct(self, s: str) -> int:
        n = len(s)
        return len({s[i:j] for i in range(n) for j in range(i + 1, n + 1)})
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countDistinct(String s) {
        Set<String> ss = new HashSet<>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j <= n; ++j) {
                ss.add(s.substring(i, j));
            }
        }
        return ss.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countDistinct(string s) {
        unordered_set<string_view> ss;
        int n = s.size();
        string_view t, v = s;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j <= n; ++j) {
                t = v.substr(i, j - i);
                ss.insert(t);
            }
        }
        return ss.size();
    }
};
```

### **Go**

```go
func countDistinct(s string) int {
	ss := map[string]bool{}
	for i := range s {
		for j := i + 1; j <= len(s); j++ {
			ss[s[i:j]] = true
		}
	}
	return len(ss)
}
```

### **...**

```

```

<!-- tabs:end -->
