---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1678.Goal%20Parser%20Interpretation/README_EN.md
rating: 1221
source: Weekly Contest 218 Q1
tags:
    - String
---

<!-- problem:start -->

# [1678. Goal Parser Interpretation](https://leetcode.com/problems/goal-parser-interpretation)

[中文文档](/solution/1600-1699/1678.Goal%20Parser%20Interpretation/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: String Replacement

According to the problem, we only need to replace `"()"` with `'o'` and `"(al)"` with `"al"` in the string `command`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def interpret(self, command: str) -> str:
        return command.replace('()', 'o').replace('(al)', 'al')
```

#### Java

```java
class Solution {
    public String interpret(String command) {
        return command.replace("()", "o").replace("(al)", "al");
    }
}
```

#### C++

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

#### Go

```go
func interpret(command string) string {
	command = strings.ReplaceAll(command, "()", "o")
	command = strings.ReplaceAll(command, "(al)", "al")
	return command
}
```

#### TypeScript

```ts
function interpret(command: string): string {
    return command.replace(/\(\)/g, 'o').replace(/\(al\)/g, 'al');
}
```

#### Rust

```rust
impl Solution {
    pub fn interpret(command: String) -> String {
        command.replace("()", "o").replace("(al)", "al")
    }
}
```

#### C

```c
char* interpret(char* command) {
    int n = strlen(command);
    char* ans = malloc(sizeof(char) * n + 1);
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

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: String Iteration

We can also iterate over the string `command`. For each character $c$:

-   If it is `'G'`, directly add $c$ to the result string;
-   If it is `'('`, check if the next character is `')'`. If it is, add `'o'` to the result string. Otherwise, add `"al"` to the result string.

After the iteration, return the result string.

The time complexity is $O(n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

```cpp
class Solution {
public:
    string interpret(string command) {
        string ans;
        for (int i = 0; i < command.size(); ++i) {
            char c = command[i];
            if (c == 'G')
                ans += c;
            else if (c == '(')
                ans += command[i + 1] == ')' ? "o" : "al";
        }
        return ans;
    }
};
```

#### Go

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

#### TypeScript

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

#### Rust

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
                    if bs[i + 1] == b')' { "o" } else { "al" }
                });
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
