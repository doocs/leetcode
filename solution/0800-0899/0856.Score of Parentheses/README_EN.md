---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0856.Score%20of%20Parentheses/README_EN.md
tags:
    - Stack
    - String
---

<!-- problem:start -->

# [856. Score of Parentheses](https://leetcode.com/problems/score-of-parentheses)

[中文文档](/solution/0800-0899/0856.Score%20of%20Parentheses/README.md)

## Description

<!-- description:start -->

<p>Given a balanced parentheses string <code>s</code>, return <em>the <strong>score</strong> of the string</em>.</p>

<p>The <strong>score</strong> of a balanced parentheses string is based on the following rule:</p>

<ul>
	<li><code>&quot;()&quot;</code> has score <code>1</code>.</li>
	<li><code>AB</code> has score <code>A + B</code>, where <code>A</code> and <code>B</code> are balanced parentheses strings.</li>
	<li><code>(A)</code> has score <code>2 * A</code>, where <code>A</code> is a balanced parentheses string.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;()&quot;
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;(())&quot;
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;()()&quot;
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 50</code></li>
	<li><code>s</code> consists of only <code>&#39;(&#39;</code> and <code>&#39;)&#39;</code>.</li>
	<li><code>s</code> is a balanced parentheses string.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

By observing, we find that `()` is the only structure that contributes to the score, and the outer parentheses just add some multipliers to this structure. So, we only need to focus on `()`.

We use $d$ to maintain the current depth of parentheses. For each `(`, we increase the depth by one, and for each `)`, we decrease the depth by one. When we encounter `()`, we add $2^d$ to the answer.

Let's take `(()(()))` as an example. We first find the two closed parentheses `()` inside, and then add the corresponding $2^d$ to the score. In fact, we are calculating the score of `(()) + ((()))`.

```bash
( ( ) ( ( ) ) )
  ^ ^   ^ ^

( ( ) ) + ( ( ( ) ) )
  ^ ^         ^ ^
```

The time complexity is $O(n)$, and the space complexity is $O(1)$. Here, $n$ is the length of the string.

Related problems about parentheses:

-   [678. Valid Parenthesis String](https://github.com/doocs/leetcode/blob/main/solution/0600-0699/0678.Valid%20Parenthesis%20String/README.md)
-   [1021. Remove Outermost Parentheses](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1021.Remove%20Outermost%20Parentheses/README.md)
-   [1096. Brace Expansion II](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1096.Brace%20Expansion%20II/README.md)
-   [1249. Minimum Remove to Make Valid Parentheses](https://github.com/doocs/leetcode/blob/main/solution/1200-1299/1249.Minimum%20Remove%20to%20Make%20Valid%20Parentheses/README.md)
-   [1541. Minimum Insertions to Balance a Parentheses String](https://github.com/doocs/leetcode/blob/main/solution/1500-1599/1541.Minimum%20Insertions%20to%20Balance%20a%20Parentheses%20String/README.md)
-   [2116. Check if a Parentheses String Can Be Valid](https://github.com/doocs/leetcode/blob/main/solution/2100-2199/2116.Check%20if%20a%20Parentheses%20String%20Can%20Be%20Valid/README.md)

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
