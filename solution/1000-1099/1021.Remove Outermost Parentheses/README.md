# [1021. 删除最外层的括号](https://leetcode.cn/problems/remove-outermost-parentheses)

[English Version](/solution/1000-1099/1021.Remove%20Outermost%20Parentheses/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有效括号字符串为空 <code>""</code>、<code>"(" + A + ")"</code> 或 <code>A + B</code> ，其中 <code>A</code> 和 <code>B</code> 都是有效的括号字符串，<code>+</code> 代表字符串的连接。</p>

<ul>
	<li>例如，<code>""</code>，<code>"()"</code>，<code>"(())()"</code> 和 <code>"(()(()))"</code> 都是有效的括号字符串。</li>
</ul>

<p>如果有效字符串 <code>s</code> 非空，且不存在将其拆分为 <code>s = A + B</code> 的方法，我们称其为<strong>原语（primitive）</strong>，其中 <code>A</code> 和 <code>B</code> 都是非空有效括号字符串。</p>

<p>给出一个非空有效字符串 <code>s</code>，考虑将其进行原语化分解，使得：<code>s = P_1 + P_2 + ... + P_k</code>，其中 <code>P_i</code> 是有效括号字符串原语。</p>

<p>对 <code>s</code> 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 <code>s</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "(()())(())"
<strong>输出：</strong>"()()()"
<strong>解释：
</strong>输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "(()())(())(()(()))"
<strong>输出：</strong>"()()()()(())"
<strong>解释：</strong>
输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
删除每个部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "()()"
<strong>输出：</strong>""
<strong>解释：</strong>
输入字符串为 "()()"，原语化分解得到 "()" + "()"，
删除每个部分中的最外层括号后得到 "" + "" = ""。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 为 <code>'('</code> 或 <code>')'</code></li>
	<li><code>s</code> 是一个有效括号字符串</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数**

遍历字符串，遇到左括号 `(` 计数器加一，此时计数器不为 1 时，说明当前括号不是最外层括号，将其加入结果字符串。遇到右括号 `)` 计数器减一，此时计数器不为 0 时，说明当前括号不是最外层括号，将其加入结果字符串。

时间复杂度 $O(n)$，忽略答案字符串的空间开销，空间复杂度 $O(1)$。其中 $n$ 为字符串长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def removeOuterParentheses(self, s: str) -> str:
        ans = []
        cnt = 0
        for c in s:
            if c == '(':
                cnt += 1
                if cnt > 1:
                    ans.append(c)
            else:
                cnt -= 1
                if cnt > 0:
                    ans.append(c)
        return ''.join(ans)
```

```python
class Solution:
    def removeOuterParentheses(self, s: str) -> str:
        ans = []
        cnt = 0
        for c in s:
            if c == '(':
                cnt += 1
            if cnt > 1:
                ans.append(c)
            if c == ')':
                cnt -= 1
        return ''.join(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder ans = new StringBuilder();
        int cnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                if (++cnt > 1) {
                    ans.append(c);
                }
            } else {
                if (--cnt > 0) {
                    ans.append(c);
                }
            }
        }
        return ans.toString();
    }
}
```

```java
class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder ans = new StringBuilder();
        int cnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                ++cnt;
            }
            if (cnt > 1) {
                ans.append(c);
            }
            if (c == ')') {
                --cnt;
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
    string removeOuterParentheses(string s) {
        string ans;
        int cnt = 0;
        for (char& c : s) {
            if (c == '(') {
                if (++cnt > 1) {
                    ans.push_back(c);
                }
            } else {
                if (--cnt) {
                    ans.push_back(c);
                }
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    string removeOuterParentheses(string s) {
        string ans;
        int cnt = 0;
        for (char& c : s) {
            if (c == '(') {
                ++cnt;
            }
            if (cnt > 1) {
                ans.push_back(c);
            }
            if (c == ')') {
                --cnt;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func removeOuterParentheses(s string) string {
	ans := []rune{}
	cnt := 0
	for _, c := range s {
		if c == '(' {
			cnt++
			if cnt > 1 {
				ans = append(ans, c)
			}
		} else {
			cnt--
			if cnt > 0 {
				ans = append(ans, c)
			}
		}
	}
	return string(ans)
}
```

```go
func removeOuterParentheses(s string) string {
	ans := []rune{}
	cnt := 0
	for _, c := range s {
		if c == '(' {
			cnt++
		}
		if cnt > 1 {
			ans = append(ans, c)
		}
		if c == ')' {
			cnt--
		}
	}
	return string(ans)
}
```

### **TypeScript**

```ts
function removeOuterParentheses(s: string): string {
    let res = '';
    let depth = 0;
    for (const c of s) {
        if (c === '(') {
            depth++;
        }
        if (depth !== 1) {
            res += c;
        }
        if (c === ')') {
            depth--;
        }
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn remove_outer_parentheses(s: String) -> String {
        let mut res = String::new();
        let mut depth = 0;
        for c in s.chars() {
            if c == '(' {
                depth += 1;
            }
            if depth != 1 {
                res.push(c);
            }
            if c == ')' {
                depth -= 1;
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
