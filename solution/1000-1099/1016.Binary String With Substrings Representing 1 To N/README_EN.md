# [1016. Binary String With Substrings Representing 1 To N](https://leetcode.com/problems/binary-string-with-substrings-representing-1-to-n)

[中文文档](/solution/1000-1099/1016.Binary%20String%20With%20Substrings%20Representing%201%20To%20N/README.md)

## Description

<p>Given a binary string <code>s</code> and a positive integer <code>n</code>, return <code>true</code><em> if the binary representation of all the integers in the range </em><code>[1, n]</code><em> are <strong>substrings</strong> of </em><code>s</code><em>, or </em><code>false</code><em> otherwise</em>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = "0110", n = 3
<strong>Output:</strong> true
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> s = "0110", n = 4
<strong>Output:</strong> false
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def queryString(self, s: str, n: int) -> bool:
        for i in range(n, n // 2, -1):
            if bin(i)[2:] not in s:
                return False
        return True
```

### **Java**

```java
class Solution {
    public boolean queryString(String s, int n) {
        for (int i = n; i > n / 2; i--) {
            if (!s.contains(Integer.toBinaryString(i))) {
                return false;
            }
        }
        return true;
    }
}
```

### **Go**

```go
func queryString(s string, n int) bool {
	for i := n; i > n/2; i-- {
		if !strings.Contains(s, strconv.FormatInt(int64(i), 2)) {
			return false
		}
	}
	return true
}
```

### **C++**

```cpp
class Solution {
public:
    bool queryString(string s, int n) {
        for (int i = n; i > n / 2; --i) {
            string b = bitset<32>(i).to_string();
            b = b.substr(b.find_first_not_of('0'));
            if (s.find(b) == string::npos) return false;
        }
        return true;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
