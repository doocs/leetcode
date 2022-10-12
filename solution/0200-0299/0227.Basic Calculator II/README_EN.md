# [227. Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii)

[中文文档](/solution/0200-0299/0227.Basic%20Calculator%20II/README.md)

## Description

<p>Given a string <code>s</code> which represents an expression, <em>evaluate this expression and return its value</em>.&nbsp;</p>

<p>The integer division should truncate toward zero.</p>

<p>You may assume that the given expression is always valid. All intermediate results will be in the range of <code>[-2<sup>31</sup>, 2<sup>31</sup> - 1]</code>.</p>

<p><strong>Note:</strong> You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as <code>eval()</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> s = "3+2*2"
<strong>Output:</strong> 7
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> s = " 3/2 "
<strong>Output:</strong> 1
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> s = " 3+5 / 2 "
<strong>Output:</strong> 5
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>s</code> consists of integers and operators <code>(&#39;+&#39;, &#39;-&#39;, &#39;*&#39;, &#39;/&#39;)</code> separated by some number of spaces.</li>
	<li><code>s</code> represents <strong>a valid expression</strong>.</li>
	<li>All the integers in the expression are non-negative integers in the range <code>[0, 2<sup>31</sup> - 1]</code>.</li>
	<li>The answer is <strong>guaranteed</strong> to fit in a <strong>32-bit integer</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def calculate(self, s: str) -> int:
        v, n = 0, len(s)
        sign = '+'
        stk = []
        for i, c in enumerate(s):
            if c.isdigit():
                v = v * 10 + int(c)
            if i == n - 1 or c in '+-*/':
                match sign:
                    case '+':
                        stk.append(v)
                    case '-':
                        stk.append(-v)
                    case '*':
                        stk.append(stk.pop() * v)
                    case '/':
                        stk.append(int(stk.pop() / v))
                sign = c
                v = 0
        return sum(stk)
```

### **Java**

```java
class Solution {
    public int calculate(String s) {
        Deque<Integer> stk = new ArrayDeque<>();
        char sign = '+';
        int v = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                v = v * 10 + (c - '0');
            }
            if (i == s.length() - 1 || c == '+' || c == '-' || c == '*' || c == '/') {
                if (sign == '+') {
                    stk.push(v);
                } else if (sign == '-') {
                    stk.push(-v);
                } else if (sign == '*') {
                    stk.push(stk.pop() * v);
                } else {
                    stk.push(stk.pop() / v);
                }
                sign = c;
                v = 0;
            }
        }
        int ans = 0;
        while (!stk.isEmpty()) {
            ans += stk.pop();
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int calculate(string s) {
        int v = 0, n = s.size();
        char sign = '+';
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            char c = s[i];
            if (isdigit(c)) v = v * 10 + (c - '0');
            if (i == n - 1 || c == '+' || c == '-' || c == '*' || c == '/') {
                if (sign == '+') stk.push(v);
                else if (sign == '-') stk.push(-v);
                else if (sign == '*') {
                    int t = stk.top();
                    stk.pop();
                    stk.push(t * v);
                } else {
                    int t = stk.top();
                    stk.pop();
                    stk.push(t / v);
                }
                sign = c;
                v = 0;
            }
        }
        int ans = 0;
        while (!stk.empty()) {
            ans += stk.top();
            stk.pop();
        }
        return ans;
    }
};
```

### **Go**

```go
func calculate(s string) int {
	sign := '+'
	stk := []int{}
	v := 0
	for i, c := range s {
		digit := '0' <= c && c <= '9'
		if digit {
			v = v*10 + int(c-'0')
		}
		if i == len(s)-1 || !digit && c != ' ' {
			switch sign {
			case '+':
				stk = append(stk, v)
			case '-':
				stk = append(stk, -v)
			case '*':
				stk[len(stk)-1] *= v
			case '/':
				stk[len(stk)-1] /= v
			}
			sign = c
			v = 0
		}
	}
	ans := 0
	for _, v := range stk {
		ans += v
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
