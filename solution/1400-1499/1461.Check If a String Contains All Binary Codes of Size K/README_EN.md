# [1461. Check If a String Contains All Binary Codes of Size K](https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k)

[中文文档](/solution/1400-1499/1461.Check%20If%20a%20String%20Contains%20All%20Binary%20Codes%20of%20Size%20K/README.md)

## Description

<p>Given a binary string <code>s</code> and an integer <code>k</code>, return <code>true</code> <em>if every binary code of length</em> <code>k</code> <em>is a substring of</em> <code>s</code>. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;00110110&quot;, k = 2
<strong>Output:</strong> true
<strong>Explanation:</strong> The binary codes of length 2 are &quot;00&quot;, &quot;01&quot;, &quot;10&quot; and &quot;11&quot;. They can be all found as substrings at indices 0, 1, 3 and 2 respectively.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0110&quot;, k = 1
<strong>Output:</strong> true
<strong>Explanation:</strong> The binary codes of length 1 are &quot;0&quot; and &quot;1&quot;, it is clear that both exist as a substring. 
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0110&quot;, k = 2
<strong>Output:</strong> false
<strong>Explanation:</strong> The binary code &quot;00&quot; is of length 2 and does not exist in the array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
	<li><code>1 &lt;= k &lt;= 20</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def hasAllCodes(self, s: str, k: int) -> bool:
        ss = {s[i: i + k] for i in range(len(s) - k + 1)}
        return len(ss) == 1 << k
```

```python
class Solution:
    def hasAllCodes(self, s: str, k: int) -> bool:
        if len(s) - k + 1 < (1 << k):
            return False
        vis = [False] * (1 << k)
        num = int(s[:k], 2)
        vis[num] = True
        for i in range(k, len(s)):
            a = (ord(s[i - k]) - ord('0')) << (k - 1)
            b = ord(s[i]) - ord('0')
            num = ((num - a) << 1) + b
            vis[num] = True
        return all(v for v in vis)
```

### **Java**

```java
class Solution {
    public boolean hasAllCodes(String s, int k) {
        Set<String> ss = new HashSet<>();
        for (int i = 0; i < s.length() - k + 1; ++i) {
            ss.add(s.substring(i, i + k));
        }
        return ss.size() == 1 << k;
    }
}
```

```java
class Solution {
    public boolean hasAllCodes(String s, int k) {
        int n = s.length();
        if (n - k + 1 < (1 << k)) {
            return false;
        }
        boolean[] vis = new boolean[1 << k];
        int num = Integer.parseInt(s.substring(0, k), 2);
        vis[num] = true;
        for (int i = k; i < n; ++i) {
            int a = (s.charAt(i - k) - '0') << (k - 1);
            int b = s.charAt(i) - '0';
            num = (num - a) << 1 | b;
            vis[num] = true;
        }
        for (boolean v : vis) {
            if (!v) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool hasAllCodes(string s, int k) {
        unordered_set<string> ss;
        for (int i = 0; i + k <= s.size(); ++i) {
            ss.insert(move(s.substr(i, k)));
        }
        return ss.size() == 1 << k;
    }
};
```

```cpp
class Solution {
public:
    bool hasAllCodes(string s, int k) {
        int n = s.size();
        if (n - k + 1 < (1 << k)) return false;
        vector<bool> vis(1 << k);
        int num = stoi(s.substr(0, k), nullptr, 2);
        vis[num] = true;
        for (int i = k; i < n; ++i) {
            int a = (s[i - k] - '0') << (k - 1);
            int b = s[i] - '0';
            num = (num - a) << 1 | b;
            vis[num] = true;
        }
        for (bool v : vis) if (!v) return false;
        return true;
    }
};
```

### **Go**

```go
func hasAllCodes(s string, k int) bool {
	ss := map[string]bool{}
	for i := 0; i+k <= len(s); i++ {
		ss[s[i:i+k]] = true
	}
	return len(ss) == 1<<k
}
```

```go
func hasAllCodes(s string, k int) bool {
	n := len(s)
	if n-k+1 < (1 << k) {
		return false
	}
	vis := make([]bool, 1<<k)
	num := 0
	for i := 0; i < k; i++ {
		num = num<<1 | int(s[i]-'0')
	}
	vis[num] = true
	for i := k; i < n; i++ {
		a := int(s[i-k]-'0') << (k - 1)
		num = (num-a)<<1 | int(s[i]-'0')
		vis[num] = true
	}
	for _, v := range vis {
		if !v {
			return false
		}
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
