# [1698. Number of Distinct Substrings in a String](https://leetcode.com/problems/number-of-distinct-substrings-in-a-string)

[中文文档](/solution/1600-1699/1698.Number%20of%20Distinct%20Substrings%20in%20a%20String/README.md)

## Description

<p>Given a string <code>s</code>, return <em>the number of <strong>distinct</strong> substrings of</em>&nbsp;<code>s</code>.</p>

<p>A <strong>substring</strong> of a string is obtained by deleting any number of characters (possibly zero) from the front of the string and any number (possibly zero) from the back of the string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabbaba&quot;
<strong>Output:</strong> 21
<strong>Explanation:</strong> The set of distinct strings is [&quot;a&quot;,&quot;b&quot;,&quot;aa&quot;,&quot;bb&quot;,&quot;ab&quot;,&quot;ba&quot;,&quot;aab&quot;,&quot;abb&quot;,&quot;bab&quot;,&quot;bba&quot;,&quot;aba&quot;,&quot;aabb&quot;,&quot;abba&quot;,&quot;bbab&quot;,&quot;baba&quot;,&quot;aabba&quot;,&quot;abbab&quot;,&quot;bbaba&quot;,&quot;aabbab&quot;,&quot;abbaba&quot;,&quot;aabbaba&quot;]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcdefg&quot;
<strong>Output:</strong> 28
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Can you solve this problem in <code>O(n)</code> time complexity?

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countDistinct(self, s: str) -> int:
        n = len(s)
        return len({s[i:j] for i in range(n) for j in range(i + 1, n + 1)})
```

```python
class Solution:
    def countDistinct(self, s: str) -> int:
        base = 131
        n = len(s)
        p = [0] * (n + 10)
        h = [0] * (n + 10)
        p[0] = 1
        for i, c in enumerate(s):
            p[i + 1] = p[i] * base
            h[i + 1] = h[i] * base + ord(c)
        ss = set()
        for i in range(1, n + 1):
            for j in range(i, n + 1):
                t = h[j] - h[i - 1] * p[j - i + 1]
                ss.add(t)
        return len(ss)
```

### **Java**

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

```java
class Solution {
    public int countDistinct(String s) {
        int base = 131;
        int n = s.length();
        long[] p = new long[n + 10];
        long[] h = new long[n + 10];
        p[0] = 1;
        for (int i = 0; i < n; ++i) {
            p[i + 1] = p[i] * base;
            h[i + 1] = h[i] * base + s.charAt(i);
        }
        Set<Long> ss = new HashSet<>();
        for (int i = 1; i <= n; ++i) {
            for (int j = i; j <= n; ++j) {
                long t = h[j] - h[i - 1] * p[j - i + 1];
                ss.add(t);
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

```cpp
class Solution {
public:
    int countDistinct(string s) {
        using ull = unsigned long long;
        int n = s.size();
        ull p[n + 10];
        ull h[n + 10];
        int base = 131;
        p[0] = 1;
        for (int i = 0; i < n; ++i) {
            p[i + 1] = p[i] * base;
            h[i + 1] = h[i] * base + s[i];
        }
        unordered_set<ull> ss;
        for (int i = 1; i <= n; ++i) {
            for (int j = i; j <= n; ++j) {
                ss.insert(h[j] - h[i - 1] * p[j - i + 1]);
            }
        }
        return ss.size();
    }
};
```

### **Go**

```go
func countDistinct(s string) int {
	ss := map[string]struct{}{}
	for i := range s {
		for j := i + 1; j <= len(s); j++ {
			ss[s[i:j]] = struct{}{}
		}
	}
	return len(ss)
}
```

```go
func countDistinct(s string) int {
	n := len(s)
	p := make([]int, n+10)
	h := make([]int, n+10)
	p[0] = 1
	base := 131
	for i, c := range s {
		p[i+1] = p[i] * base
		h[i+1] = h[i]*base + int(c)
	}
	ss := map[int]struct{}{}
	for i := 1; i <= n; i++ {
		for j := i; j <= n; j++ {
			ss[h[j]-h[i-1]*p[j-i+1]] = struct{}{}
		}
	}
	return len(ss)
}
```

### **...**

```

```

<!-- tabs:end -->
