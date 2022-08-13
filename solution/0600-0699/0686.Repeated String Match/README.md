# [686. 重复叠加字符串匹配](https://leetcode.cn/problems/repeated-string-match)

[English Version](/solution/0600-0699/0686.Repeated%20String%20Match/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串&nbsp;<code>a</code> 和 <code>b</code>，寻找重复叠加字符串 <code>a</code> 的最小次数，使得字符串 <code>b</code> 成为叠加后的字符串 <code>a</code> 的子串，如果不存在则返回 <code>-1</code>。</p>

<p><strong>注意：</strong>字符串 <code>&quot;abc&quot;</code>&nbsp;重复叠加 0 次是 <code>&quot;&quot;</code>，重复叠加 1 次是&nbsp;<code>&quot;abc&quot;</code>，重复叠加 2 次是&nbsp;<code>&quot;abcabc&quot;</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>a = &quot;abcd&quot;, b = &quot;cdabcdab&quot;
<strong>输出：</strong>3
<strong>解释：</strong>a 重复叠加三遍后为 &quot;ab<strong>cdabcdab</strong>cd&quot;, 此时 b 是其子串。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>a = &quot;a&quot;, b = &quot;aa&quot;
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>a = &quot;a&quot;, b = &quot;a&quot;
<strong>输出：</strong>1
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>a = &quot;abc&quot;, b = &quot;wxyz&quot;
<strong>输出：</strong>-1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= a.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= b.length &lt;= 10<sup>4</sup></code></li>
	<li><code>a</code> 和 <code>b</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

新串由 a 通过重复最小 ans 次得来，假设新串包含子字符串 b，那么新串的起始匹配位置一定不超过 a。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def repeatedStringMatch(self, a: str, b: str) -> int:
        m, n = len(a), len(b)
        ans = ceil(n / m)
        t = [a] * ans
        for _ in range(3):
            if b in ''.join(t):
                return ans
            ans += 1
            t.append(a)
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int repeatedStringMatch(String a, String b) {
        int m = a.length(), n = b.length();
        int ans = (n + m - 1) / m;
        StringBuilder t = new StringBuilder(a.repeat(ans));
        for (int i = 0; i < 3; ++i) {
            if (t.toString().contains(b)) {
                return ans;
            }
            ++ans;
            t.append(a);
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int repeatedStringMatch(string a, string b) {
        int m = a.size(), n = b.size();
        int ans = (n + m - 1) / m;
        string t = "";
        for (int i = 0; i < ans; ++i) t += a;
        for (int i = 0; i < 3; ++i) {
            if (t.find(b) != -1) return ans;
            ++ans;
            t += a;
        }
        return -1;
    }
};
```

### **Go**

```go
func repeatedStringMatch(a string, b string) int {
	m, n := len(a), len(b)
	ans := (n + m - 1) / m
	t := strings.Repeat(a, ans)
	for i := 0; i < 3; i++ {
		if strings.Contains(t, b) {
			return ans
		}
		ans++
		t += a
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
