# [1190. 反转每对括号间的子串](https://leetcode.cn/problems/reverse-substrings-between-each-pair-of-parentheses)

[English Version](/solution/1100-1199/1190.Reverse%20Substrings%20Between%20Each%20Pair%20of%20Parentheses/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出一个字符串&nbsp;<code>s</code>（仅含有小写英文字母和括号）。</p>

<p>请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。</p>

<p>注意，您的结果中 <strong>不应</strong> 包含任何括号。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "(abcd)"
<strong>输出：</strong>"dcba"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "(u(love)i)"
<strong>输出：</strong>"iloveu"
<strong>解释：</strong>先反转子字符串 "love" ，然后反转整个字符串。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "(ed(et(oc))el)"
<strong>输出：</strong>"leetcode"
<strong>解释：</strong>先反转子字符串 "oc" ，接着反转 "etco" ，然后反转整个字符串。</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>s = "a(bcdefghijkl(mno)p)q"
<strong>输出：</strong>"apmnolkjihgfedcbq"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code> 中只有小写英文字母和括号</li>
	<li>题目测试用例确保所有括号都是成对出现的</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

用双端队列或者栈，模拟反转的过程。

时间复杂度 $O(n^2)$，其中 $n$ 为字符串 $s$ 的长度。

**方法二：脑筋急转弯**

我们观察发现，遍历字符串时，每一次遇到 `(` 或者 `)`，都是跳到对应的 `)` 或者 `(`，然后反转遍历的方向，继续遍历。

因此，我们可以用一个数组 $d$ 来记录每个 `(` 或者 `)` 对应的另一个括号的位置，即 $d[i]$ 表示 $i$ 处的括号对应的另一个括号的位置。直接用栈就可以求出 $d$ 数组。

然后，我们从左到右遍历字符串，遇到 `(` 或者 `)` 时，根据 $d$ 数组跳到对应的位置，然后反转方向，继续遍历，直到遍历完整个字符串。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def reverseParentheses(self, s: str) -> str:
        stk = []
        for c in s:
            if c == ')':
                t = []
                while stk[-1] != '(':
                    t.append(stk.pop())
                stk.pop()
                stk.extend(t)
            else:
                stk.append(c)
        return ''.join(stk)
```

```python
class Solution:
    def reverseParentheses(self, s: str) -> str:
        n = len(s)
        d = [0] * n
        stk = []
        for i, c in enumerate(s):
            if c == '(':
                stk.append(i)
            elif c == ')':
                j = stk.pop()
                d[i], d[j] = j, i
        i, x = 0, 1
        ans = []
        while i < n:
            if s[i] in '()':
                i = d[i]
                x = -x
            else:
                ans.append(s[i])
            i += x
        return ''.join(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] d = new int[n];
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(') {
                stk.push(i);
            } else if (s.charAt(i) == ')') {
                int j = stk.pop();
                d[i] = j;
                d[j] = i;
            }
        }
        StringBuilder ans = new StringBuilder();
        int i = 0, x = 1;
        while (i < n) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                i = d[i];
                x = -x;
            } else {
                ans.append(s.charAt(i));
            }
            i += x;
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string reverseParentheses(string s) {
        string stk;
        for (char& c : s) {
            if (c == ')') {
                string t;
                while (stk.back() != '(') {
                    t.push_back(stk.back());
                    stk.pop_back();
                }
                stk.pop_back();
                stk += t;
            } else {
                stk.push_back(c);
            }
        }
        return stk;
    }
};
```

```cpp
class Solution {
public:
    string reverseParentheses(string s) {
        int n = s.size();
        vector<int> d(n);
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            if (s[i] == '(') {
                stk.push(i);
            } else if (s[i] == ')') {
                int j = stk.top();
                stk.pop();
                d[i] = j;
                d[j] = i;
            }
        }
        int i = 0, x = 1;
        string ans;
        while (i < n) {
            if (s[i] == '(' || s[i] == ')') {
                i = d[i];
                x = -x;
            } else {
                ans.push_back(s[i]);
            }
            i += x;
        }
        return ans;
    }
};
```

### **Go**

```go
func reverseParentheses(s string) string {
	stk := []byte{}
	for i := range s {
		if s[i] == ')' {
			t := []byte{}
			for stk[len(stk)-1] != '(' {
				t = append(t, stk[len(stk)-1])
				stk = stk[:len(stk)-1]
			}
			stk = stk[:len(stk)-1]
			stk = append(stk, t...)
		} else {
			stk = append(stk, s[i])
		}
	}
	return string(stk)
}
```

```go
func reverseParentheses(s string) string {
	n := len(s)
	d := make([]int, n)
	stk := []int{}
	for i, c := range s {
		if c == '(' {
			stk = append(stk, i)
		} else if c == ')' {
			j := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			d[i], d[j] = j, i
		}
	}
	ans := []byte{}
	i, x := 0, 1
	for i < n {
		if s[i] == '(' || s[i] == ')' {
			i = d[i]
			x = -x
		} else {
			ans = append(ans, s[i])
		}
		i += x
	}
	return string(ans)
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {string}
 */
var reverseParentheses = function (s) {
    const n = s.length;
    const d = new Array(n).fill(0);
    const stk = [];
    for (let i = 0; i < n; ++i) {
        if (s[i] == '(') {
            stk.push(i);
        } else if (s[i] == ')') {
            const j = stk.pop();
            d[i] = j;
            d[j] = i;
        }
    }
    let i = 0;
    let x = 1;
    const ans = [];
    while (i < n) {
        const c = s.charAt(i);
        if (c == '(' || c == ')') {
            i = d[i];
            x = -x;
        } else {
            ans.push(c);
        }
        i += x;
    }
    return ans.join('');
};
```

### **...**

```

```

<!-- tabs:end -->
