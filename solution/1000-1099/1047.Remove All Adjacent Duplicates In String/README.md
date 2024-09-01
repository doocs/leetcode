---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1047.Remove%20All%20Adjacent%20Duplicates%20In%20String/README.md
rating: 1286
source: 第 137 场周赛 Q2
tags:
    - 栈
    - 字符串
---

<!-- problem:start -->

# [1047. 删除字符串中的所有相邻重复项](https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string)

[English Version](/solution/1000-1099/1047.Remove%20All%20Adjacent%20Duplicates%20In%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给出由小写字母组成的字符串&nbsp;<code>s</code>，<strong>重复项删除操作</strong>会选择两个相邻且相同的字母，并删除它们。</p>

<p>在 <code>s</code> 上反复执行重复项删除操作，直到无法继续删除。</p>

<p>在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>"abbaca"
<strong>输出：</strong>"ca"
<strong>解释：</strong>
例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅由小写英文字母组成。</li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：栈

遍历字符串 `s` 中的每个字符 `c`，若栈为空或者栈顶值不等于字符 `c`，将 `c` 入栈，否则栈顶元素出栈。

最后返回栈中元素所组成的字符串。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 `s` 的长度。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### Rust

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

#### JavaScript

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

#### C

```c
char* removeDuplicates(char* s) {
    int n = strlen(s);
    char* stack = malloc(sizeof(char) * (n + 1));
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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
