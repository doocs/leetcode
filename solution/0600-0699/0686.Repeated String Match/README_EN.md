# [686. Repeated String Match](https://leetcode.com/problems/repeated-string-match)

[中文文档](/solution/0600-0699/0686.Repeated%20String%20Match/README.md)

## Description

<p>Given two strings&nbsp;<code>a</code> and <code>b</code>, return the minimum number of times you should repeat string&nbsp;<code>a</code>&nbsp;so that string&nbsp;<code>b</code>&nbsp;is a substring of it. If it is&nbsp;impossible for&nbsp;<code>b</code>​​​​​​ to be a substring of&nbsp;<code>a</code> after repeating it, return&nbsp;<code>-1</code>.</p>

<p><strong>Notice:</strong>&nbsp;string&nbsp;<code>&quot;abc&quot;</code>&nbsp;repeated 0 times is&nbsp;<code>&quot;&quot;</code>,&nbsp; repeated 1 time is&nbsp;<code>&quot;abc&quot;</code>&nbsp;and repeated 2 times is&nbsp;<code>&quot;abcabc&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;abcd&quot;, b = &quot;cdabcdab&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> We return 3 because by repeating a three times &quot;ab<strong>cdabcdab</strong>cd&quot;, b is a substring of it.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;a&quot;, b = &quot;aa&quot;
<strong>Output:</strong> 2
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;a&quot;, b = &quot;a&quot;
<strong>Output:</strong> 1
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;abc&quot;, b = &quot;wxyz&quot;
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= b.length &lt;= 10<sup>4</sup></code></li>
	<li><code>a</code>&nbsp;and&nbsp;<code>b</code>&nbsp;consist of lower-case English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
        for (int i = 0; i < 3; ++i)
        {
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
