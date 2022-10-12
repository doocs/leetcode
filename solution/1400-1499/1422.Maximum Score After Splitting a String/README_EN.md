# [1422. Maximum Score After Splitting a String](https://leetcode.com/problems/maximum-score-after-splitting-a-string)

[中文文档](/solution/1400-1499/1422.Maximum%20Score%20After%20Splitting%20a%20String/README.md)

## Description

<p>Given a&nbsp;string <code>s</code>&nbsp;of zeros and ones, <em>return the maximum score after splitting the string into two <strong>non-empty</strong> substrings</em> (i.e. <strong>left</strong> substring and <strong>right</strong> substring).</p>

<p>The score after splitting a string is the number of <strong>zeros</strong> in the <strong>left</strong> substring plus the number of <strong>ones</strong> in the <strong>right</strong> substring.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;011101&quot;
<strong>Output:</strong> 5 
<strong>Explanation:</strong> 
All possible ways of splitting s into two non-empty substrings are:
left = &quot;0&quot; and right = &quot;11101&quot;, score = 1 + 4 = 5 
left = &quot;01&quot; and right = &quot;1101&quot;, score = 1 + 3 = 4 
left = &quot;011&quot; and right = &quot;101&quot;, score = 1 + 2 = 3 
left = &quot;0111&quot; and right = &quot;01&quot;, score = 1 + 1 = 2 
left = &quot;01110&quot; and right = &quot;1&quot;, score = 2 + 1 = 3
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;00111&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> When left = &quot;00&quot; and right = &quot;111&quot;, we get the maximum score = 2 + 3 = 5
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1111&quot;
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 500</code></li>
	<li>The string <code>s</code> consists of characters <code>&#39;0&#39;</code> and <code>&#39;1&#39;</code> only.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxScore(self, s: str) -> int:
        return max(s[:i].count('0') + s[i:].count('1') for i in range(1, len(s)))
```

```python
class Solution:
    def maxScore(self, s: str) -> int:
        ans = t = (s[0] == '0') + s[1:].count('1')
        for i in range(1, len(s) - 1):
            t += 1 if s[i] == '0' else -1
            ans = max(ans, t)
        return ans
```

### **Java**

```java
class Solution {
    public int maxScore(String s) {
        int ans = 0;
        for (int i = 1; i < s.length(); ++i) {
            int t = 0;
            for (int j = 0; j < i; ++j) {
                if (s.charAt(j) == '0') {
                    ++t;
                }
            }
            for (int j = i; j < s.length(); ++j) {
                if (s.charAt(j) == '1') {
                    ++t;
                }
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int maxScore(String s) {
        int t = 0;
        if (s.charAt(0) == '0') {
            t++;
        }
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == '1') {
                t++;
            }
        }
        int ans = t;
        for (int i = 1; i < s.length() - 1; ++i) {
            t += s.charAt(i) == '0' ? 1 : -1;
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxScore(string s) {
        int ans = 0;
        for (int i = 1, n = s.size(); i < n; ++i) {
            int t = 0;
            for (int j = 0; j < i; ++j) {
                t += s[j] == '0';
            }
            for (int j = i; j < n; ++j) {
                t += s[j] == '1';
            }
            ans = max(ans, t);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int maxScore(string s) {
        int t = 0;
        if (s[0] == '0') ++t;
        for (int i = 1; i < s.size(); ++i) t += s[i] == '1';
        int ans = t;
        for (int i = 1; i < s.size() - 1; ++i) {
            t += s[i] == '0' ? 1 : -1;
            ans = max(ans, t);
        }
        return ans;
    }
};
```

### **Go**

```go
func maxScore(s string) int {
	ans := 0
	for i, n := 1, len(s); i < n; i++ {
		t := 0
		for j := 0; j < i; j++ {
			if s[j] == '0' {
				t++
			}
		}
		for j := i; j < n; j++ {
			if s[j] == '1' {
				t++
			}
		}
		ans = max(ans, t)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func maxScore(s string) int {
	t := 0
	if s[0] == '0' {
		t++
	}
	n := len(s)
	for i := 1; i < n; i++ {
		if s[i] == '1' {
			t++
		}
	}
	ans := t
	for i := 1; i < n-1; i++ {
		if s[i] == '0' {
			t++
		} else {
			t--
		}
		ans = max(ans, t)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function maxScore(s: string): number {
    const n = s.length;
    let res = 0;
    let score = 0;
    if (s[0] === '0') {
        score++;
    }
    for (let i = 1; i < n; i++) {
        if (s[i] === '1') {
            score++;
        }
    }
    res = Math.max(res, score);
    for (let i = 1; i < n - 1; i++) {
        if (s[i] === '0') {
            score++;
        } else if (s[i] === '1') {
            score--;
        }
        res = Math.max(res, score);
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_score(s: String) -> i32 {
        let n = s.len();
        let mut res = 0;
        let mut score = 0;
        let bs = s.as_bytes();
        if bs[0] == b'0' {
            score += 1;
        }
        for i in 1..n {
            if bs[i] == b'1' {
                score += 1;
            }
        }
        res = res.max(score);
        for i in 1..n - 1 {
            if bs[i] == b'0' {
                score += 1;
            } else if bs[i] == b'1' {
                score -= 1;
            }
            res = res.max(score);
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
