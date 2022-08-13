# [844. Backspace String Compare](https://leetcode.com/problems/backspace-string-compare)

[中文文档](/solution/0800-0899/0844.Backspace%20String%20Compare/README.md)

## Description

<p>Given two strings <code>s</code> and <code>t</code>, return <code>true</code> <em>if they are equal when both are typed into empty text editors</em>. <code>&#39;#&#39;</code> means a backspace character.</p>

<p>Note that after backspacing an empty text, the text will continue empty.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ab#c&quot;, t = &quot;ad#c&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> Both s and t become &quot;ac&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ab##&quot;, t = &quot;c#d#&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> Both s and t become &quot;&quot;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a#c&quot;, t = &quot;b&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> s becomes &quot;c&quot; while t becomes &quot;b&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code><span>1 &lt;= s.length, t.length &lt;= 200</span></code></li>
	<li><span><code>s</code> and <code>t</code> only contain lowercase letters and <code>&#39;#&#39;</code> characters.</span></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Can you solve it in <code>O(n)</code> time and <code>O(1)</code> space?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def backspaceCompare(self, s: str, t: str) -> bool:
        i, j, skip1, skip2 = len(s) - 1, len(t) - 1, 0, 0
        while i >= 0 or j >= 0:
            while i >= 0:
                if s[i] == '#':
                    skip1 += 1
                    i -= 1
                elif skip1:
                    skip1 -= 1
                    i -= 1
                else:
                    break
            while j >= 0:
                if t[j] == '#':
                    skip2 += 1
                    j -= 1
                elif skip2:
                    skip2 -= 1
                    j -= 1
                else:
                    break
            if i >= 0 and j >= 0:
                if s[i] != t[j]:
                    return False
            elif i >= 0 or j >= 0:
                return False
            i, j = i - 1, j - 1
        return True
```

### **Java**

```java
class Solution {
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1, j = t.length() - 1;
        int skip1 = 0, skip2 = 0;
        for (; i >= 0 || j >= 0; --i, --j) {
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    ++skip1;
                    --i;
                } else if (skip1 > 0) {
                    --skip1;
                    --i;
                } else {
                    break;
                }
            }
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    ++skip2;
                    --j;
                } else if (skip2 > 0) {
                    --skip2;
                    --j;
                } else {
                    break;
                }
            }
            if (i >= 0 && j >= 0) {
                if (s.charAt(i) != t.charAt(j)) {
                    return false;
                }
            } else if (i >= 0 || j >= 0) {
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
    bool backspaceCompare(string s, string t) {
        int i = s.size() - 1, j = t.size() - 1;
        int skip1 = 0, skip2 = 0;
        for (; i >= 0 || j >= 0; --i, --j) {
            while (i >= 0) {
                if (s[i] == '#') {
                    ++skip1;
                    --i;
                } else if (skip1) {
                    --skip1;
                    --i;
                } else
                    break;
            }
            while (j >= 0) {
                if (t[j] == '#') {
                    ++skip2;
                    --j;
                } else if (skip2) {
                    --skip2;
                    --j;
                } else
                    break;
            }
            if (i >= 0 && j >= 0) {
                if (s[i] != t[j]) return false;
            } else if (i >= 0 || j >= 0)
                return false;
        }
        return true;
    }
};
```

### **Go**

```go
func backspaceCompare(s string, t string) bool {
	i, j := len(s)-1, len(t)-1
	skip1, skip2 := 0, 0
	for ; i >= 0 || j >= 0; i, j = i-1, j-1 {
		for i >= 0 {
			if s[i] == '#' {
				skip1++
				i--
			} else if skip1 > 0 {
				skip1--
				i--
			} else {
				break
			}
		}
		for j >= 0 {
			if t[j] == '#' {
				skip2++
				j--
			} else if skip2 > 0 {
				skip2--
				j--
			} else {
				break
			}
		}
		if i >= 0 && j >= 0 {
			if s[i] != t[j] {
				return false
			}
		} else if i >= 0 || j >= 0 {
			return false
		}
	}
	return true
}
```

### **TypeScript**

```ts
function backspaceCompare(s: string, t: string): boolean {
    let i = s.length - 1;
    let j = t.length - 1;
    while (i >= 0 || j >= 0) {
        let skip = 0;
        while (i >= 0) {
            if (s[i] === '#') {
                skip++;
            } else if (skip !== 0) {
                skip--;
            } else {
                break;
            }
            i--;
        }
        skip = 0;
        while (j >= 0) {
            if (t[j] === '#') {
                skip++;
            } else if (skip !== 0) {
                skip--;
            } else {
                break;
            }
            j--;
        }
        if (s[i] !== t[j]) {
            return false;
        }
        i--;
        j--;
    }
    return true;
}
```

### **Rust**

```rust
impl Solution {
    pub fn backspace_compare(s: String, t: String) -> bool {
        let (s, t) = (s.as_bytes(), t.as_bytes());
        let (mut i, mut j) = (s.len(), t.len());
        while i != 0 || j != 0 {
            let mut skip = 0;
            while i != 0 {
                if s[i - 1] == b'#' {
                    skip += 1;
                } else if skip != 0 {
                    skip -= 1;
                } else {
                    break;
                }
                i -= 1
            }
            skip = 0;
            while j != 0 {
                if t[j - 1] == b'#' {
                    skip += 1;
                } else if skip != 0 {
                    skip -= 1;
                } else {
                    break;
                }
                j -= 1
            }
            if i == 0 && j == 0 {
                break;
            }
            if i == 0 || j == 0 {
                return false;
            }
            if s[i - 1] != t[j - 1] {
                return false;
            }
            i -= 1;
            j -= 1;
        }
        true
    }
}
```

### **...**

```

```

<!-- tabs:end -->
