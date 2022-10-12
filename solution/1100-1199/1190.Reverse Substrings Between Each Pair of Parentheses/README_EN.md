# [1190. Reverse Substrings Between Each Pair of Parentheses](https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses)

[中文文档](/solution/1100-1199/1190.Reverse%20Substrings%20Between%20Each%20Pair%20of%20Parentheses/README.md)

## Description

<p>You are given a string <code>s</code> that consists of lower case English letters and brackets.</p>

<p>Reverse the strings in each pair of matching parentheses, starting from the innermost one.</p>

<p>Your result should <strong>not</strong> contain any brackets.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;(abcd)&quot;
<strong>Output:</strong> &quot;dcba&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;(u(love)i)&quot;
<strong>Output:</strong> &quot;iloveu&quot;
<strong>Explanation:</strong> The substring &quot;love&quot; is reversed first, then the whole string is reversed.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;(ed(et(oc))el)&quot;
<strong>Output:</strong> &quot;leetcode&quot;
<strong>Explanation:</strong> First, we reverse the substring &quot;oc&quot;, then &quot;etco&quot;, and finally, the whole string.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code> only contains lower case English characters and parentheses.</li>
	<li>It is guaranteed that all parentheses are balanced.</li>
</ul>

## Solutions

Use deque or stack to simulate the reversal process.

<!-- tabs:start -->

### **Python3**

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
