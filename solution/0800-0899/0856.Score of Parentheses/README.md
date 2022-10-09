# [856. 括号的分数](https://leetcode.cn/problems/score-of-parentheses)

[English Version](/solution/0800-0899/0856.Score%20of%20Parentheses/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个平衡括号字符串&nbsp;<code>S</code>，按下述规则计算该字符串的分数：</p>

<ul>
	<li><code>()</code> 得 1 分。</li>
	<li><code>AB</code> 得&nbsp;<code>A + B</code>&nbsp;分，其中 A 和 B 是平衡括号字符串。</li>
	<li><code>(A)</code> 得&nbsp;<code>2 * A</code>&nbsp;分，其中 A 是平衡括号字符串。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入： </strong>&quot;()&quot;
<strong>输出： </strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入： </strong>&quot;(())&quot;
<strong>输出： </strong>2
</pre>

<p><strong>示例&nbsp;3：</strong></p>

<pre><strong>输入： </strong>&quot;()()&quot;
<strong>输出： </strong>2
</pre>

<p><strong>示例&nbsp;4：</strong></p>

<pre><strong>输入： </strong>&quot;(()(()))&quot;
<strong>输出： </strong>6
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>S</code>&nbsp;是平衡括号字符串，且只含有&nbsp;<code>(</code>&nbsp;和&nbsp;<code>)</code>&nbsp;。</li>
	<li><code>2 &lt;= S.length &lt;= 50</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数**

我们通过观察发现，`()` 是唯一贡献分数的结构，外括号只是为该结构添加了一些乘数。所以我们只需要关心 `()`。

我们用 $d$ 维护当前括号的深度，对于每个 `(`，我们将深度加一，对于每个 `)`，我们将深度减一。当我们遇到 `()` 时，我们将 $2^d$ 加到答案中。

我们举个实际的例子，以 `(()(()))` 为例，我们首先找到内部两个闭合括号 `()`，然后将分数加上对应的 $2^d$。实际上，我们是在计算 `(()) + ((()))` 的分数。

```bash
( ( ) ( ( ) ) )
  ^ ^   ^ ^

( ( ) ) + ( ( ( ) ) )
  ^ ^         ^ ^
```

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是字符串的长度。

括号相关类型题：

-   [678. 有效的括号字符串](https://github.com/doocs/leetcode/tree/main/solution/0600-0699/0678.Valid%20Parenthesis%20String/README.md)
-   [1021. 删除最外层的括号](https://github.com/doocs/leetcode/tree/main/solution/1000-1099/1021.Remove%20Outermost%20Parentheses/README.md)
-   [1096. 花括号展开 II](https://github.com/doocs/leetcode/tree/main/solution/1000-1099/1096.Brace%20Expansion%20II/README.md)
-   [1249. 移除无效的括号](https://github.com/doocs/leetcode/tree/main/solution/1200-1299/1249.Minimum%20Remove%20to%20Make%20Valid%20Parentheses/README.md)
-   [1541. 平衡括号字符串的最少插入次数](https://github.com/doocs/leetcode/tree/main/solution/1500-1599/1541.Minimum%20Insertions%20to%20Balance%20a%20Parentheses%20String/README.md)
-   [2116. 判断一个括号字符串是否有效](https://github.com/doocs/leetcode/tree/main/solution/2100-2199/2116.Check%20if%20a%20Parentheses%20String%20Can%20Be%20Valid/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def scoreOfParentheses(self, s: str) -> int:
        ans = d = 0
        for i, c in enumerate(s):
            if c == '(':
                d += 1
            else:
                d -= 1
                if s[i - 1] == '(':
                    ans += 1 << d
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int scoreOfParentheses(String s) {
        int ans = 0, d = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                ++d;
            } else {
                --d;
                if (s.charAt(i - 1) == '(') {
                    ans += 1 << d;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int scoreOfParentheses(string s) {
        int ans = 0, d = 0;
        for (int i = 0; i < s.size(); ++i) {
            if (s[i] == '(') {
                ++d;
            } else {
                --d;
                if (s[i - 1] == '(') {
                    ans += 1 << d;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func scoreOfParentheses(s string) int {
	ans, d := 0, 0
	for i, c := range s {
		if c == '(' {
			d++
		} else {
			d--
			if s[i-1] == '(' {
				ans += 1 << d
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
