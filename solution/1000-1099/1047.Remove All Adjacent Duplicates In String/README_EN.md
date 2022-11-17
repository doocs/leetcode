# [1047. Remove All Adjacent Duplicates In String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string)

[中文文档](/solution/1000-1099/1047.Remove%20All%20Adjacent%20Duplicates%20In%20String/README.md)

## Description

<p>You are given a string <code>s</code> consisting of lowercase English letters. A <strong>duplicate removal</strong> consists of choosing two <strong>adjacent</strong> and <strong>equal</strong> letters and removing them.</p>

<p>We repeatedly make <strong>duplicate removals</strong> on <code>s</code> until we no longer can.</p>

<p>Return <em>the final string after all such duplicate removals have been made</em>. It can be proven that the answer is <strong>unique</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abbaca&quot;
<strong>Output:</strong> &quot;ca&quot;
<strong>Explanation:</strong> 
For example, in &quot;abbaca&quot; we could remove &quot;bb&quot; since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is &quot;aaca&quot;, of which only &quot;aa&quot; is possible, so the final string is &quot;ca&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;azxxzy&quot;
<strong>Output:</strong> &quot;ay&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def removeDuplicates(self, s: str) -> str:
        stk = []
        for c in s:
            if stk and stk[-1] == c:
                stk.pop()
            else:
                stk.append(c)
        return ''.join(stk)
```

### **Java**

```java
class Solution {
    public String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == c) {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string removeDuplicates(string s) {
        string stk;
        for (char c : s) {
            if (!stk.empty() && stk[stk.size() - 1] == c) {
                stk.pop_back();
            } else {
                stk += c;
            }
        }
        return stk;
    }
};
```

### **Go**

```go
func removeDuplicates(s string) string {
	stk := []rune{}
	for _, c := range s {
		if len(stk) > 0 && stk[len(stk)-1] == c {
			stk = stk[:len(stk)-1]
		} else {
			stk = append(stk, c)
		}
	}
	return string(stk)
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {string}
 */
var removeDuplicates = function (s) {
    const stk = [];
    for (const c of s) {
        if (stk.length && stk[stk.length - 1] == c) {
            stk.pop();
        } else {
            stk.push(c);
        }
    }
    return stk.join('');
};
```

### **Rust**

```rust
impl Solution {
    pub fn remove_duplicates(s: String) -> String {
        let mut stack = Vec::new();
        for c in s.chars() {
            if !stack.is_empty() && *stack.last().unwrap() == c {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        stack.into_iter().collect()
    }
}
```

### **C**

```c
char *removeDuplicates(char *s) {
    int n = strlen(s);
    char *stack = malloc(sizeof(char) * (n + 1));
    int i = 0;
    for (int j = 0; j < n; j++) {
        char c = s[j];
        if (i && stack[i - 1] == c) {
            i--;
        } else {
            stack[i++] = c;
        }
    }
    stack[i] = '\0';
    return stack;
}
```

### **...**

```

```

<!-- tabs:end -->
