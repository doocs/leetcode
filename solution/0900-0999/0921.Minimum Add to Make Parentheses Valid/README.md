# [921. 使括号有效的最少添加](https://leetcode.cn/problems/minimum-add-to-make-parentheses-valid)

[English Version](/solution/0900-0999/0921.Minimum%20Add%20to%20Make%20Parentheses%20Valid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>只有满足下面几点之一，括号字符串才是有效的：</p>

<ul>
	<li>它是一个空字符串，或者</li>
	<li>它可以被写成&nbsp;<code>AB</code>&nbsp;（<code>A</code>&nbsp;与&nbsp;<code>B</code>&nbsp;连接）, 其中&nbsp;<code>A</code> 和&nbsp;<code>B</code>&nbsp;都是有效字符串，或者</li>
	<li>它可以被写作&nbsp;<code>(A)</code>，其中&nbsp;<code>A</code>&nbsp;是有效字符串。</li>
</ul>

<p>给定一个括号字符串 <code>s</code> ，在每一次操作中，你都可以在字符串的任何位置插入一个括号</p>

<ul>
	<li>例如，如果 <code>s = "()))"</code> ，你可以插入一个开始括号为 <code>"(()))"</code> 或结束括号为 <code>"())))"</code> 。</li>
</ul>

<p>返回 <em>为使结果字符串 <code>s</code> 有效而必须添加的最少括号数</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "())"
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "((("
<strong>输出：</strong>3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> 只包含&nbsp;<code>'('</code> 和&nbsp;<code>')'</code>&nbsp;字符。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 栈**

这个问题属于经典的括号匹配问题，可以使用“贪心 + 栈”来解决。

遍历字符串 $s$ 的每个字符 $c$：

-   若 $c$ 为左括号，直接将 $c$ 入栈；
-   若 $c$ 为右括号，此时如果栈不为空，且栈顶元素为左括号，则将栈顶元素出栈，表示匹配成功；否则将 $c$ 入栈。

遍历结束后，栈中剩余的元素个数即为需要添加的括号数。

时间复杂度为 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

**方法二：贪心 + 计数**

方法一借助了栈来实现括号匹配，也可以直接通过计数来实现。

定义变量 `cnt` 表示当前待匹配的左括号个数，变量 `ans` 记录答案。初始时两个变量的值均为 $0$。

同样遍历字符串 $s$ 的每个字符 $c$：

-   若 $c$ 为左括号，将 `cnt` 的值增加 $1$；
-   若 $c$ 为右括号，此时如果 $cnt \gt 0$，说明当前有左括号可以匹配，将 `cnt` 的值减 $1$；否则说明当前右括号无法匹配，将 `ans` 的值增加 $1$。

遍历结束后，将 `cnt` 的值加到 `ans` 中，即为答案。

时间复杂度为 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minAddToMakeValid(self, s: str) -> int:
        stk = []
        for c in s:
            if c == ')' and stk and stk[-1] == '(':
                stk.pop()
            else:
                stk.append(c)
        return len(stk)
```

```python
class Solution:
    def minAddToMakeValid(self, s: str) -> int:
        ans = cnt = 0
        for c in s:
            if c == '(':
                cnt += 1
            elif cnt:
                cnt -= 1
            else:
                ans += 1
        ans += cnt
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minAddToMakeValid(String s) {
        Deque<Character> stk = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == ')' && !stk.isEmpty() && stk.peek() == '(') {
                stk.pop();
            } else {
                stk.push(c);
            }
        }
        return stk.size();
    }
}
```

```java
class Solution {
    public int minAddToMakeValid(String s) {
        int ans = 0, cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                ++cnt;
            } else if (cnt > 0) {
                --cnt;
            } else {
                ++ans;
            }
        }
        ans += cnt;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minAddToMakeValid(string s) {
        string stk;
        for (char c : s) {
            if (c == ')' && stk.size() && stk.back() == '(') stk.pop_back();
            else stk.push_back(c);
        }
        return stk.size();
    }
};
```

```cpp
class Solution {
public:
    int minAddToMakeValid(string s) {
        int ans = 0, cnt = 0;
        for (char c : s) {
            if (c == '(')
                ++cnt;
            else if (cnt)
                --cnt;
            else
                ++ans;
        }
        ans += cnt;
        return ans;
    }
};
```

### **Go**

```go
func minAddToMakeValid(s string) int {
	stk := []rune{}
	for _, c := range s {
		if c == ')' && len(stk) > 0 && stk[len(stk)-1] == '(' {
			stk = stk[:len(stk)-1]
		} else {
			stk = append(stk, c)
		}
	}
	return len(stk)
}
```

```go
func minAddToMakeValid(s string) int {
	ans, cnt := 0, 0
	for _, c := range s {
		if c == '(' {
			cnt++
		} else if cnt > 0 {
			cnt--
		} else {
			ans++
		}
	}
	ans += cnt
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
