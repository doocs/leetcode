# [1678. 设计 Goal 解析器](https://leetcode.cn/problems/goal-parser-interpretation)

[English Version](/solution/1600-1699/1678.Goal%20Parser%20Interpretation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你设计一个可以解释字符串 <code>command</code> 的 <strong>Goal 解析器</strong> 。<code>command</code> 由 <code>"G"</code>、<code>"()"</code> 和/或 <code>"(al)"</code> 按某种顺序组成。Goal 解析器会将 <code>"G"</code> 解释为字符串 <code>"G"</code>、<code>"()"</code> 解释为字符串 <code>"o"</code> ，<code>"(al)"</code> 解释为字符串 <code>"al"</code> 。然后，按原顺序将经解释得到的字符串连接成一个字符串。</p>

<p>给你字符串 <code>command</code> ，返回<em> </em><strong>Goal<em><strong> </strong></em>解析器 </strong>对<em> </em><code>command</code> 的解释结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>command = "G()(al)"
<strong>输出：</strong>"Goal"
<strong>解释：</strong>Goal 解析器解释命令的步骤如下所示：
G -&gt; G
() -&gt; o
(al) -&gt; al
最后连接得到的结果是 "Goal"
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>command = "G()()()()(al)"
<strong>输出：</strong>"Gooooal"
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>command = "(al)G(al)()()G"
<strong>输出：</strong>"alGalooG"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= command.length &lt;= 100</code></li>
	<li><code>command</code> 由 <code>"G"</code>、<code>"()"</code> 和/或 <code>"(al)"</code> 按某种顺序组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：字符串替换**

根据题意，只需要将字符串 `command` 中的 `"()"` 替换为 `'o'`，`"(al)"` 替换为 `"al"` 即可。

**方法二：字符串遍历**

我们也可以遍历字符串 `command`，对于每个字符 $c$：

-   如果是 `'G'`，直接将 $c$ 添加到结果串中；
-   如果是 `'('`，判断下一个字符是否是 `')'`，若是，将 `'o'` 添加到结果串中，否则，将 `"al"` 添加到结果串中。

遍历结束，返回结果串即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
