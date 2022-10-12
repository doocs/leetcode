# [984. String Without AAA or BBB](https://leetcode.com/problems/string-without-aaa-or-bbb)

[中文文档](/solution/0900-0999/0984.String%20Without%20AAA%20or%20BBB/README.md)

## Description

<p>Given two integers <code>a</code> and <code>b</code>, return <strong>any</strong> string <code>s</code> such that:</p>

<ul>
	<li><code>s</code> has length <code>a + b</code> and contains exactly <code>a</code> <code>&#39;a&#39;</code> letters, and exactly <code>b</code> <code>&#39;b&#39;</code> letters,</li>
	<li>The substring <code>&#39;aaa&#39;</code> does not occur in <code>s</code>, and</li>
	<li>The substring <code>&#39;bbb&#39;</code> does not occur in <code>s</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = 1, b = 2
<strong>Output:</strong> &quot;abb&quot;
<strong>Explanation:</strong> &quot;abb&quot;, &quot;bab&quot; and &quot;bba&quot; are all correct answers.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = 4, b = 1
<strong>Output:</strong> &quot;aabaa&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= a, b &lt;= 100</code></li>
	<li>It is guaranteed such an <code>s</code> exists for the given <code>a</code> and <code>b</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def strWithout3a3b(self, a: int, b: int) -> str:
        ans = []
        while a and b:
            if a > b:
                ans.append('aab')
                a, b = a - 2, b - 1
            elif a < b:
                ans.append('bba')
                a, b = a - 1, b - 2
            else:
                ans.append('ab')
                a, b = a - 1, b - 1
        if a:
            ans.append('a' * a)
        if b:
            ans.append('b' * b)
        return ''.join(ans)
```

### **Java**

```java
class Solution {
    public String strWithout3a3b(int a, int b) {
        StringBuilder ans = new StringBuilder();
        while (a > 0 && b > 0) {
            if (a > b) {
                ans.append("aab");
                a -= 2;
                b -= 1;
            } else if (a < b) {
                ans.append("bba");
                a -= 1;
                b -= 2;
            } else {
                ans.append("ab");
                --a;
                --b;
            }
        }
        if (a > 0) {
            ans.append("a".repeat(a));
        }
        if (b > 0) {
            ans.append("b".repeat(b));
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string strWithout3a3b(int a, int b) {
        string ans;
        while (a && b) {
            if (a > b) {
                ans += "aab";
                a -= 2;
                b -= 1;
            } else if (a < b) {
                ans += "bba";
                a -= 1;
                b -= 2;
            } else {
                ans += "ab";
                --a;
                --b;
            }
        }
        if (a) ans += string(a, 'a');
        if (b) ans += string(b, 'b');
        return ans;
    }
};
```

### **Go**

```go
func strWithout3a3b(a int, b int) string {
	var ans strings.Builder
	for a > 0 && b > 0 {
		if a > b {
			ans.WriteString("aab")
			a -= 2
			b -= 1
		} else if a < b {
			ans.WriteString("bba")
			a -= 1
			b -= 2
		} else {
			ans.WriteString("ab")
			a--
			b--
		}
	}
	if a > 0 {
		ans.WriteString(strings.Repeat("a", a))
	}
	if b > 0 {
		ans.WriteString(strings.Repeat("b", b))
	}
	return ans.String()
}
```

### **...**

```

```

<!-- tabs:end -->
