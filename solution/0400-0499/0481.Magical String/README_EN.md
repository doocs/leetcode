# [481. Magical String](https://leetcode.com/problems/magical-string)

[中文文档](/solution/0400-0499/0481.Magical%20String/README.md)

## Description

<p>A magical string <code>s</code> consists of only <code>&#39;1&#39;</code> and <code>&#39;2&#39;</code> and obeys the following rules:</p>

<ul>
	<li>The string s is magical because concatenating the number of contiguous occurrences of characters <code>&#39;1&#39;</code> and <code>&#39;2&#39;</code> generates the string <code>s</code> itself.</li>
</ul>

<p>The first few elements of <code>s</code> is <code>s = &quot;1221121221221121122&hellip;&hellip;&quot;</code>. If we group the consecutive <code>1</code>&#39;s and <code>2</code>&#39;s in <code>s</code>, it will be <code>&quot;1 22 11 2 1 22 1 22 11 2 11 22 ......&quot;</code> and the occurrences of <code>1</code>&#39;s or <code>2</code>&#39;s in each group are <code>&quot;1 2 2 1 1 2 1 2 2 1 2 2 ......&quot;</code>. You can see that the occurrence sequence is <code>s</code> itself.</p>

<p>Given an integer <code>n</code>, return the number of <code>1</code>&#39;s in the first <code>n</code> number in the magical string <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 6
<strong>Output:</strong> 3
<strong>Explanation:</strong> The first 6 elements of magical string s is &quot;122112&quot; and it contains three 1&#39;s, so return 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def magicalString(self, n: int) -> int:
        s = list('1221121')
        i = 5
        while len(s) < n:
            if s[i] == '1':
                s.append('2' if s[-1] == '1' else '1')
            else:
                s.extend(list('22' if s[-1] == '1' else '11'))
            i += 1
        return s[:n].count('1')
```

### **Java**

```java
class Solution {
    public int magicalString(int n) {
        StringBuilder s = new StringBuilder("1221121");
        int i = 5;
        while (s.length() < n) {
            char c = s.charAt(s.length() - 1);
            if (s.charAt(i) == '1') {
                s.append(c == '1' ? '2' : '1');
            } else {
                s.append(c == '1' ? "22" : "11");
            }
            ++i;
        }
        int ans = 0;
        for (i = 0; i < n; ++i) {
            if (s.charAt(i) == '1') {
                ++ans;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int magicalString(int n) {
        string s = "1221121";
        int i = 5;
        while (s.size() < n) {
            if (s[i] == '1') {
                s += s.back() == '1' ? "2" : "1";
            } else {
                s += s.back() == '1' ? "22" : "11";
            }
            ++i;
        }
        return count(s.begin(), s.begin() + n, '1');
    }
};
```

### **Go**

```go
func magicalString(n int) int {
	s := []byte("1221121")
	i := 5
	for len(s) < n {
		c := s[len(s)-1]
		if s[i] == '1' {
			if c == '1' {
				s = append(s, '2')
			} else {
				s = append(s, '1')
			}
		} else {
			if c == '1' {
				s = append(s, '2')
				s = append(s, '2')
			} else {
				s = append(s, '1')
				s = append(s, '1')
			}
		}
		i++
	}
	return bytes.Count(s[:n], []byte("1"))
}
```

### **...**

```

```

<!-- tabs:end -->
