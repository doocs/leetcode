# [1678. Goal Parser Interpretation](https://leetcode.com/problems/goal-parser-interpretation)

[中文文档](/solution/1600-1699/1678.Goal%20Parser%20Interpretation/README.md)

## Description

<p>You own a <strong>Goal Parser</strong> that can interpret a string <code>command</code>. The <code>command</code> consists of an alphabet of <code>&quot;G&quot;</code>, <code>&quot;()&quot;</code> and/or <code>&quot;(al)&quot;</code> in some order. The Goal Parser will interpret <code>&quot;G&quot;</code> as the string <code>&quot;G&quot;</code>, <code>&quot;()&quot;</code> as the string <code>&quot;o&quot;</code>, and <code>&quot;(al)&quot;</code> as the string <code>&quot;al&quot;</code>. The interpreted strings are then concatenated in the original order.</p>

<p>Given the string <code>command</code>, return <em>the <strong>Goal Parser</strong>&#39;s interpretation of </em><code>command</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> command = &quot;G()(al)&quot;
<strong>Output:</strong> &quot;Goal&quot;
<strong>Explanation:</strong>&nbsp;The Goal Parser interprets the command as follows:
G -&gt; G
() -&gt; o
(al) -&gt; al
The final concatenated result is &quot;Goal&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> command = &quot;G()()()()(al)&quot;
<strong>Output:</strong> &quot;Gooooal&quot;
</pre>

<p><strong class="example">Example 3:</strong></p>

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
        ans = []
        for i, c in enumerate(command):
            if c == 'G':
                ans.append(c)
            elif c == '(':
                ans.append('o' if command[i + 1] == ')' else 'al')
        return ''.join(ans)
```

### **Java**

```java
class Solution {
    public String interpret(String command) {
        return command.replace("()", "o").replace("(al)", "al");
    }
}
```

```java
class Solution {
    public String interpret(String command) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < command.length(); ++i) {
            char c = command.charAt(i);
            if (c == 'G') {
                ans.append(c);
            } else if (c == '(') {
                ans.append(command.charAt(i + 1) == ')' ? "o" : "al");
            }
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string interpret(string command) {
        while (command.find("()") != -1) command.replace(command.find("()"), 2, "o");
        while (command.find("(al)") != -1) command.replace(command.find("(al)"), 4, "al");
        return command;
    }
};
```

```cpp
class Solution {
public:
    string interpret(string command) {
        string ans;
        for (int i = 0; i < command.size(); ++i) {
            char c = command[i];
            if (c == 'G') ans += c;
            else if (c == '(') ans += command[i + 1] == ')' ? "o" : "al";
        }
        return ans;
    }
};
```

### **Go**

```go
func interpret(command string) string {
    command = strings.ReplaceAll(command, "()", "o")
    command = strings.ReplaceAll(command, "(al)", "al")
    return command
}
```

```go
func interpret(command string) string {
	ans := &strings.Builder{}
	for i, c := range command {
		if c == 'G' {
			ans.WriteRune(c)
		} else if c == '(' {
			if command[i+1] == ')' {
				ans.WriteByte('o')
			} else {
				ans.WriteString("al")
			}
		}
	}
	return ans.String()
}
```

### **TypeScript**

```ts
function interpret(command: string): string {
    return command.replace(/\(\)/g, 'o').replace(/\(al\)/g, 'al');
}
```

```ts
function interpret(command: string): string {
    const n = command.length;
    const ans: string[] = [];
    for (let i = 0; i < n; i++) {
        const c = command[i];
        if (c === 'G') {
            ans.push(c);
        } else if (c === '(') {
            ans.push(command[i + 1] === ')' ? 'o' : 'al');
        }
    }
    return ans.join('');
}
```

### **Rust**

```rust
impl Solution {
    pub fn interpret(command: String) -> String {
        command.replace("()", "o").replace("(al)", "al")
    }
}
```

```rust
impl Solution {
    pub fn interpret(command: String) -> String {
        let mut ans = String::new();
        let bs = command.as_bytes();
        for i in 0..bs.len() {
            if bs[i] == b'G' {
                ans.push_str("G");
            }
            if bs[i] == b'(' {
                ans.push_str({
                    if bs[i + 1] == b')' {
                        "o"
                    } else {
                        "al"
                    }
                })
            }
        }
        ans
    }
}
```

### **C**

```c
char *interpret(char *command) {
    int n = strlen(command);
    char *ans = malloc(sizeof(char) * n + 1);
    int i = 0;
    for (int j = 0; j < n; j++) {
        char c = command[j];
        if (c == 'G') {
            ans[i++] = 'G';
        } else if (c == '(') {
            if (command[j + 1] == ')') {
                ans[i++] = 'o';
            } else {
                ans[i++] = 'a';
                ans[i++] = 'l';
            }
        }
    }
    ans[i] = '\0';
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
