# [2609. Find the Longest Balanced Substring of a Binary String](https://leetcode.com/problems/find-the-longest-balanced-substring-of-a-binary-string)

[中文文档](/solution/2600-2699/2609.Find%20the%20Longest%20Balanced%20Substring%20of%20a%20Binary%20String/README.md)

## Description

<p>You are given a binary string <code>s</code> consisting only of zeroes and ones.</p>

<p>A substring of <code>s</code> is considered balanced if<strong> all zeroes are before ones</strong> and the number of zeroes is equal to the number of ones inside the substring. Notice that the empty substring is considered a balanced substring.</p>

<p>Return <em>the length of the longest balanced substring of </em><code>s</code>.</p>

<p>A <b>substring</b> is a contiguous sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;01000111&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> The longest balanced substring is &quot;000111&quot;, which has length 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;00111&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest balanced substring is &quot;0011&quot;, which has length 4.&nbsp;
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;111&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no balanced substring except the empty substring, so the answer is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 50</code></li>
	<li><code>&#39;0&#39; &lt;= s[i] &lt;= &#39;1&#39;</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findTheLongestBalancedSubstring(self, s: str) -> int:
        def check(i, j):
            cnt = 0
            for k in range(i, j + 1):
                if s[k] == '1':
                    cnt += 1
                elif cnt:
                    return False
            return cnt * 2 == (j - i + 1)

        n = len(s)
        ans = 0
        for i in range(n):
            for j in range(i + 1, n):
                if check(i, j):
                    ans = max(ans, j - i + 1)
        return ans
```

### **Java**

```java
class Solution {
    public int findTheLongestBalancedSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (check(s, i, j)) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }

    private boolean check(String s, int i, int j) {
        int cnt = 0;
        for (int k = i; k <= j; ++k) {
            if (s.charAt(k) == '1') {
                ++cnt;
            } else if (cnt > 0) {
                return false;
            }
        }
        return cnt * 2 == j - i + 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findTheLongestBalancedSubstring(string s) {
        int n = s.size();
        int ans = 0;
        auto check = [&](int i, int j) -> bool {
            int cnt = 0;
            for (int k = i; k <= j; ++k) {
                if (s[k] == '1') {
                    ++cnt;
                } else if (cnt) {
                    return false;
                }
            }
            return cnt * 2 == j - i + 1;
        };
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (check(i, j)) {
                    ans = max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findTheLongestBalancedSubstring(s string) (ans int) {
	n := len(s)
	check := func(i, j int) bool {
		cnt := 0
		for k := i; k <= j; k++ {
			if s[k] == '1' {
				cnt++
			} else if cnt > 0 {
				return false
			}
		}
		return cnt*2 == j-i+1
	}
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if check(i, j) {
				ans = max(ans, j-i+1)
			}
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
