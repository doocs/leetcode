# [1790. Check if One String Swap Can Make Strings Equal](https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal)

[中文文档](/solution/1700-1799/1790.Check%20if%20One%20String%20Swap%20Can%20Make%20Strings%20Equal/README.md)

## Description

<p>You are given two strings <code>s1</code> and <code>s2</code> of equal length. A <strong>string swap</strong> is an operation where you choose two indices in a string (not necessarily different) and swap the characters at these indices.</p>

<p>Return <code>true</code> <em>if it is possible to make both strings equal by performing <strong>at most one string swap </strong>on <strong>exactly one</strong> of the strings. </em>Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;bank&quot;, s2 = &quot;kanb&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> For example, swap the first character with the last character of s2 to make &quot;bank&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;attack&quot;, s2 = &quot;defend&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> It is impossible to make them equal with one string swap.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;kelb&quot;, s2 = &quot;kelb&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The two strings are already equal, so no string swap operation is required.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 100</code></li>
	<li><code>s1.length == s2.length</code></li>
	<li><code>s1</code> and <code>s2</code> consist of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def areAlmostEqual(self, s1: str, s2: str) -> bool:
        cnt = 0
        c1 = c2 = None
        for a, b in zip(s1, s2):
            if a != b:
                cnt += 1
                if cnt > 2 or (cnt == 2 and (a != c2 or b != c1)):
                    return False
                c1, c2 = a, b
        return cnt != 1
```

### **Java**

```java
class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        int cnt = 0;
        char c1 = 0, c2 = 0;
        for (int i = 0; i < s1.length(); ++i) {
            char a = s1.charAt(i), b = s2.charAt(i);
            if (a != b) {
                if (++cnt > 2 || (cnt == 2 && (a != c2 || b != c1))) {
                    return false;
                }
                c1 = a;
                c2 = b;
            }
        }
        return cnt != 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool areAlmostEqual(string s1, string s2) {
        int cnt = 0;
        char c1 = 0, c2 = 0;
        for (int i = 0; i < s1.size(); ++i) {
            char a = s1[i], b = s2[i];
            if (a != b) {
                if (++cnt > 2 || (cnt == 2 && (a != c2 || b != c1))) {
                    return false;
                }
                c1 = a, c2 = b;
            }
        }
        return cnt != 1;
    }
};
```

### **Go**

```go
func areAlmostEqual(s1 string, s2 string) bool {
	cnt := 0
	var c1, c2 byte
	for i := range s1 {
		a, b := s1[i], s2[i]
		if a != b {
			cnt++
			if cnt > 2 || (cnt == 2 && (a != c2 || b != c1)) {
				return false
			}
			c1, c2 = a, b
		}
	}
	return cnt != 1
}
```

### **C**

```c
bool areAlmostEqual(char *s1, char *s2) {
    int n = strlen(s1);
    int i1 = -1;
    int i2 = -1;
    for (int i = 0; i < n; i++) {
        if (s1[i] != s2[i]) {
            if (i1 == -1) {
                i1 = i;
            } else if (i2 == -1) {
                i2 = i;
            } else {
                return 0;
            }
        }
    }
    if (i1 == -1 && i2 == -1) {
        return 1;
    }
    if (i1 == -1 || i2 == -1) {
        return 0;
    }
    return s1[i1] == s2[i2] && s1[i2] == s2[i1];
}

```

### **TypeScript**

```ts
function areAlmostEqual(s1: string, s2: string): boolean {
    let c1, c2;
    let cnt = 0;
    for (let i = 0; i < s1.length; ++i) {
        const a = s1.charAt(i);
        const b = s2.charAt(i);
        if (a != b) {
            if (++cnt > 2 || (cnt == 2 && (a != c2 || b != c1))) {
                return false;
            }
            c1 = a;
            c2 = b;
        }
    }
    return cnt != 1;
}
```

### **Rust**

```rust
impl Solution {
    pub fn are_almost_equal(s1: String, s2: String) -> bool {
        if s1 == s2 {
            return true;
        }
        let (s1, s2) = (s1.as_bytes(), s2.as_bytes());
        let mut idxs = vec![];
        for i in 0..s1.len() {
            if s1[i] != s2[i] {
                idxs.push(i);
            }
        }
        if idxs.len() != 2 {
            return false;
        }
        s1[idxs[0]] == s2[idxs[1]] && s2[idxs[0]] == s1[idxs[1]]
    }
}
```

### **...**

```

```

<!-- tabs:end -->
