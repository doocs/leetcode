# [696. Count Binary Substrings](https://leetcode.com/problems/count-binary-substrings)

[中文文档](/solution/0600-0699/0696.Count%20Binary%20Substrings/README.md)

## Description

<p>Given a binary string <code>s</code>, return the number of non-empty substrings that have the same number of <code>0</code>&#39;s and <code>1</code>&#39;s, and all the <code>0</code>&#39;s and all the <code>1</code>&#39;s in these substrings are grouped consecutively.</p>

<p>Substrings that occur multiple times are counted the number of times they occur.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;00110011&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> There are 6 substrings that have equal number of consecutive 1&#39;s and 0&#39;s: &quot;0011&quot;, &quot;01&quot;, &quot;1100&quot;, &quot;10&quot;, &quot;0011&quot;, and &quot;01&quot;.
Notice that some of these substrings repeat and are counted the number of times they occur.
Also, &quot;00110011&quot; is not a valid substring because all the 0&#39;s (and 1&#39;s) are not grouped together.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;10101&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 4 substrings: &quot;10&quot;, &quot;01&quot;, &quot;10&quot;, &quot;01&quot; that have equal number of consecutive 1&#39;s and 0&#39;s.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countBinarySubstrings(self, s: str) -> int:
        i, n = 0, len(s)
        t = []
        while i < n:
            cnt = 1
            while i + 1 < n and s[i + 1] == s[i]:
                cnt += 1
                i += 1
            t.append(cnt)
            i += 1
        ans = 0
        for i in range(1, len(t)):
            ans += min(t[i - 1], t[i])
        return ans
```

### **Java**

```java
class Solution {
    public int countBinarySubstrings(String s) {
        int i = 0, n = s.length();
        List<Integer> t = new ArrayList<>();
        while (i < n) {
            int cnt = 1;
            while (i + 1 < n && s.charAt(i + 1) == s.charAt(i)) {
                ++i;
                ++cnt;
            }
            t.add(cnt);
            ++i;
        }
        int ans = 0;
        for (i = 1; i < t.size(); ++i) {
            ans += Math.min(t.get(i - 1), t.get(i));
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countBinarySubstrings(string s) {
        int i = 0, n = s.size();
        vector<int> t;
        while (i < n) {
            int cnt = 1;
            while (i + 1 < n && s[i + 1] == s[i]) {
                ++cnt;
                ++i;
            }
            t.push_back(cnt);
            ++i;
        }
        int ans = 0;
        for (i = 1; i < t.size(); ++i) ans += min(t[i - 1], t[i]);
        return ans;
    }
};
```

### **Go**

```go
func countBinarySubstrings(s string) int {
	i, n := 0, len(s)
	var t []int
	for i < n {
		cnt := 1
		for i+1 < n && s[i+1] == s[i] {
			i++
			cnt++
		}
		t = append(t, cnt)
		i++
	}
	ans := 0
	for i := 1; i < len(t); i++ {
		ans += min(t[i-1], t[i])
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
