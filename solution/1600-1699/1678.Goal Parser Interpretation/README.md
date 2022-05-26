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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
