# [1678. Goal Parser Interpretation](https://leetcode.com/problems/goal-parser-interpretation)

[中文文档](/solution/1600-1699/1678.Goal%20Parser%20Interpretation/README.md)

## Description

<p>You own a <strong>Goal Parser</strong> that can interpret a string <code>command</code>. The <code>command</code> consists of an alphabet of <code>&quot;G&quot;</code>, <code>&quot;()&quot;</code> and/or <code>&quot;(al)&quot;</code> in some order. The Goal Parser will interpret <code>&quot;G&quot;</code> as the string <code>&quot;G&quot;</code>, <code>&quot;()&quot;</code> as the string <code>&quot;o&quot;</code>, and <code>&quot;(al)&quot;</code> as the string <code>&quot;al&quot;</code>. The interpreted strings are then concatenated in the original order.</p>

<p>Given the string <code>command</code>, return <em>the <strong>Goal Parser</strong>&#39;s interpretation of </em><code>command</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> command = &quot;G()(al)&quot;
<strong>Output:</strong> &quot;Goal&quot;
<strong>Explanation:</strong>&nbsp;The Goal Parser interprets the command as follows:
G -&gt; G
() -&gt; o
(al) -&gt; al
The final concatenated result is &quot;Goal&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> command = &quot;G()()()()(al)&quot;
<strong>Output:</strong> &quot;Gooooal&quot;
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> command = &quot;(al)G(al)()()G&quot;
<strong>Output:</strong> &quot;alGalooG&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= command.length &lt;= 100</code></li>
	<li><code>command</code> consists of <code>&quot;G&quot;</code>, <code>&quot;()&quot;</code>, and/or <code>&quot;(al)&quot;</code> in some order.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def interpret(self, command: str) -> str:
        return command.replace('()', 'o').replace('(al)', 'al')
```

```python
class Solution:
    def interpret(self, command: str) -> str:
        res = ''
        i, n = 0, len(command)
        while i < n:
            c = command[i]
            if c == 'G':
                res += c
                i += 1
            elif c == '(' and command[i + 1] != ')':
                res += 'al'
                i += 4
            else:
                res += 'o'
                i += 2
        return res
```

### **Java**

```java
class Solution {
    public String interpret(String command) {
        StringBuilder sb = new StringBuilder();
        int p = 0, q = 1;
        for (; p < command.length(); p++, q++) {
            char c = command.charAt(p);
            if (c == 'G')
                sb.append('G');
            if (c == '(') {
                if (command.charAt(q) == ')') {
                    sb.append("o");
                    p++;
                    q++;
                } else {
                    sb.append("al");
                    p += 2;
                    q += 2;
                }
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
    string interpret(string command) {
        string res = "";
        int i = 0, n = command.size();
        while (i < n) {
            char c = command[i];
            if (c == 'G') {
                res += "G";
                i += 1;
            } else if (c == '(' && command[i + 1] != ')') {
                res += "al";
                i += 4;
            } else {
                res += "o";
                i += 2;
            }
        }
        return res;
    }
};
```

### **Go**

```go
func interpret(command string) string {
	var res string
	i, n := 0, len(command)
	for i < n {
		c := command[i]
		if c == 'G' {
			res += "G"
			i += 1
		} else if c == '(' && command[i+1] != ')' {
			res += "al"
			i += 4
		} else {
			res += "o"
			i += 2
		}
	}
	return res
}
```

### **Rust**

```rust
impl Solution {
    pub fn interpret(command: String) -> String {
        const ss: [&str; 3] = ["G", "o", "al"];
        let n = command.len();
        let bs = command.as_bytes();
        let mut res = String::new();
        let mut i = 0;
        while i < n {
            if bs[i] == b'G' {
                res.push_str(ss[0]);
                i += 1;
            } else if bs[i + 1] == b')' {
                res.push_str(ss[1]);
                i += 2
            } else {
                res.push_str(ss[2]);
                i += 4
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
