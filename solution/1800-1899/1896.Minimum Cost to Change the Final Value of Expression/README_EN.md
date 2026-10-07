---
comments: true
difficulty: Hard
rating: 2531
source: Biweekly Contest 54 Q4
tags:
    - Stack
    - Math
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [1896. Minimum Cost to Change the Final Value of Expression](https://leetcode.com/problems/minimum-cost-to-change-the-final-value-of-expression)

[中文文档](/solution/1800-1899/1896.Minimum%20Cost%20to%20Change%20the%20Final%20Value%20of%20Expression/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>valid</strong> boolean expression as a string <code>expression</code> consisting of the characters <code>&#39;1&#39;</code>,<code>&#39;0&#39;</code>,<code>&#39;&amp;&#39;</code> (bitwise <strong>AND</strong> operator),<code>&#39;|&#39;</code> (bitwise <strong>OR</strong> operator),<code>&#39;(&#39;</code>, and <code>&#39;)&#39;</code>.</p>

<ul>
	<li>For example, <code>&quot;()1|1&quot;</code> and <code>&quot;(1)&amp;()&quot;</code> are <strong>not valid</strong> while <code>&quot;1&quot;</code>, <code>&quot;(((1))|(0))&quot;</code>, and <code>&quot;1|(0&amp;(1))&quot;</code> are <strong>valid</strong> expressions.</li>
</ul>

<p>Return<em> the <strong>minimum cost</strong> to change the final value of the expression</em>.</p>

<ul>
	<li>For example, if <code>expression = &quot;1|1|(0&amp;0)&amp;1&quot;</code>, its <strong>value</strong> is <code>1|1|(0&amp;0)&amp;1 = 1|1|0&amp;1 = 1|0&amp;1 = 1&amp;1 = 1</code>. We want to apply operations so that the<strong> new</strong> expression evaluates to <code>0</code>.</li>
</ul>

<p>The <strong>cost</strong> of changing the final value of an expression is the <strong>number of operations</strong> performed on the expression. The types of <strong>operations</strong> are described as follows:</p>

<ul>
	<li>Turn a <code>&#39;1&#39;</code> into a <code>&#39;0&#39;</code>.</li>
	<li>Turn a <code>&#39;0&#39;</code> into a <code>&#39;1&#39;</code>.</li>
	<li>Turn a <code>&#39;&amp;&#39;</code> into a <code>&#39;|&#39;</code>.</li>
	<li>Turn a <code>&#39;|&#39;</code> into a <code>&#39;&amp;&#39;</code>.</li>
</ul>

<p><strong>Note:</strong> <code>&#39;&amp;&#39;</code> does <strong>not</strong> take precedence over <code>&#39;|&#39;</code> in the <strong>order of calculation</strong>. Evaluate parentheses <strong>first</strong>, then in <strong>left-to-right</strong> order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;1&amp;(0|1)&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can turn &quot;1&amp;(0<u><strong>|</strong></u>1)&quot; into &quot;1&amp;(0<u><strong>&amp;</strong></u>1)&quot; by changing the &#39;|&#39; to a &#39;&amp;&#39; using 1 operation.
The new expression evaluates to 0. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;(0&amp;0)&amp;(0&amp;0&amp;0)&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can turn &quot;(0<u><strong>&amp;0</strong></u>)<strong><u>&amp;</u></strong>(0&amp;0&amp;0)&quot; into &quot;(0<u><strong>|1</strong></u>)<u><strong>|</strong></u>(0&amp;0&amp;0)&quot; using 3 operations.
The new expression evaluates to 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;(0|(1|0&amp;1))&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can turn &quot;(0|(<u><strong>1</strong></u>|0&amp;1))&quot; into &quot;(0|(<u><strong>0</strong></u>|0&amp;1))&quot; using 1 operation.
The new expression evaluates to 0.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= expression.length &lt;= 10<sup>5</sup></code></li>
	<li><code>expression</code>&nbsp;only contains&nbsp;<code>&#39;1&#39;</code>,<code>&#39;0&#39;</code>,<code>&#39;&amp;&#39;</code>,<code>&#39;|&#39;</code>,<code>&#39;(&#39;</code>, and&nbsp;<code>&#39;)&#39;</code></li>
	<li>All parentheses&nbsp;are properly matched.</li>
	<li>There will be no empty parentheses (i.e:&nbsp;<code>&quot;()&quot;</code>&nbsp;is not a substring of&nbsp;<code>expression</code>).</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> A valid boolean expression uses $0/1$, $\&$, $|$, and parentheses. One edit flips a digit or an operator. The expression can have length $10^5$, so reevaluating every edit is impossible.
>
> Each subexpression only needs its current value and the cost of flipping it. A leaf costs $1$ to flip. When two sides are joined by $\&$ or $|$, the flip cost follows from changing the operator, one child, or both. A stack parses parentheses and operators and merges these pairs from the bottom up.

<!-- thinking:end -->

Represent each subexpression by $(\textit{val},\textit{cost})$: its current boolean value and the minimum edits that flip it. A digit costs $1$ to flip.

$\&$ and $|$ have the same precedence and associate left to right; parentheses bind tighter. Two stacks store subexpressions and operators. An operator reduces any pending operator of the same precedence, and a closing parenthesis reduces until the matching opening parenthesis.

Let the two sides be $(v_1,c_1)$ and $(v_2,c_2)$.

- For $\&$ with both sides $1$, the value is $1$ and the flip cost is $\min(c_1,c_2)$.
- With both sides $0$, the value is $0$. Flipping both operands costs $c_1+c_2$; changing $\&$ to $|$ and flipping one operand costs $1+\min(c_1,c_2)$.
- With exactly one $0$, the value is $0$. Flip that $0$, or change $\&$ to $|$, and take the cheaper cost.
- The three cases for $|$ are dual to the cases above.

After the expression is reduced, the top $\textit{cost}$ is the answer.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of the expression.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperationsToFlip(self, expression: str) -> int:
        def merge(a, b, op):
            v1, c1 = a
            v2, c2 = b
            if op == '&':
                val = v1 & v2
                if v1 == 1 and v2 == 1:
                    cost = min(c1, c2)
                elif v1 == 0 and v2 == 0:
                    cost = min(c1 + c2, 1 + min(c1, c2))
                else:
                    cost = min(c1 if v1 == 0 else c2, 1)
            else:
                val = v1 | v2
                if v1 == 0 and v2 == 0:
                    cost = min(c1, c2)
                elif v1 == 1 and v2 == 1:
                    cost = min(c1 + c2, 1 + min(c1, c2))
                else:
                    cost = min(c1 if v1 == 1 else c2, 1)
            return val, cost

        nums = []
        ops = []

        def apply():
            b = nums.pop()
            a = nums.pop()
            nums.append(merge(a, b, ops.pop()))

        for c in expression:
            if c == '(':
                ops.append(c)
            elif c in '01':
                nums.append((int(c), 1))
            elif c in '&|':
                while ops and ops[-1] in '&|':
                    apply()
                ops.append(c)
            else:
                while ops[-1] != '(':
                    apply()
                ops.pop()
        while ops:
            apply()
        return nums[-1][1]
```

#### Java

```java
class Solution {
    public int minOperationsToFlip(String expression) {
        int n = expression.length();
        int[] val = new int[n];
        int[] cost = new int[n];
        int top = 0;
        char[] ops = new char[n];
        int otop = 0;
        for (int i = 0; i < n; ++i) {
            char c = expression.charAt(i);
            if (c == '(') {
                ops[otop++] = c;
            } else if (c == '0' || c == '1') {
                val[top] = c - '0';
                cost[top++] = 1;
            } else if (c == '&' || c == '|') {
                while (otop > 0 && (ops[otop - 1] == '&' || ops[otop - 1] == '|')) {
                    merge(val, cost, top, ops[--otop]);
                    --top;
                }
                ops[otop++] = c;
            } else {
                while (ops[otop - 1] != '(') {
                    merge(val, cost, top, ops[--otop]);
                    --top;
                }
                --otop;
            }
        }
        while (otop > 0) {
            merge(val, cost, top, ops[--otop]);
            --top;
        }
        return cost[0];
    }

    private void merge(int[] val, int[] cost, int top, char op) {
        int v1 = val[top - 2], c1 = cost[top - 2];
        int v2 = val[top - 1], c2 = cost[top - 1];
        if (op == '&') {
            val[top - 2] = v1 & v2;
            if (v1 == 1 && v2 == 1) {
                cost[top - 2] = Math.min(c1, c2);
            } else if (v1 == 0 && v2 == 0) {
                cost[top - 2] = Math.min(c1 + c2, 1 + Math.min(c1, c2));
            } else {
                cost[top - 2] = Math.min(v1 == 0 ? c1 : c2, 1);
            }
        } else {
            val[top - 2] = v1 | v2;
            if (v1 == 0 && v2 == 0) {
                cost[top - 2] = Math.min(c1, c2);
            } else if (v1 == 1 && v2 == 1) {
                cost[top - 2] = Math.min(c1 + c2, 1 + Math.min(c1, c2));
            } else {
                cost[top - 2] = Math.min(v1 == 1 ? c1 : c2, 1);
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperationsToFlip(string expression) {
        vector<pair<int, int>> nums;
        vector<char> ops;
        auto merge = [&](char op) {
            auto b = nums.back();
            nums.pop_back();
            auto a = nums.back();
            nums.pop_back();
            int v1 = a.first, c1 = a.second, v2 = b.first, c2 = b.second;
            int val, cost;
            if (op == '&') {
                val = v1 & v2;
                if (v1 == 1 && v2 == 1) {
                    cost = min(c1, c2);
                } else if (v1 == 0 && v2 == 0) {
                    cost = min(c1 + c2, 1 + min(c1, c2));
                } else {
                    cost = min(v1 == 0 ? c1 : c2, 1);
                }
            } else {
                val = v1 | v2;
                if (v1 == 0 && v2 == 0) {
                    cost = min(c1, c2);
                } else if (v1 == 1 && v2 == 1) {
                    cost = min(c1 + c2, 1 + min(c1, c2));
                } else {
                    cost = min(v1 == 1 ? c1 : c2, 1);
                }
            }
            nums.emplace_back(val, cost);
        };
        for (char c : expression) {
            if (c == '(') {
                ops.push_back(c);
            } else if (c == '0' || c == '1') {
                nums.emplace_back(c - '0', 1);
            } else if (c == '&' || c == '|') {
                while (!ops.empty() && (ops.back() == '&' || ops.back() == '|')) {
                    merge(ops.back());
                    ops.pop_back();
                }
                ops.push_back(c);
            } else {
                while (ops.back() != '(') {
                    merge(ops.back());
                    ops.pop_back();
                }
                ops.pop_back();
            }
        }
        while (!ops.empty()) {
            merge(ops.back());
            ops.pop_back();
        }
        return nums[0].second;
    }
};
```

#### Go

```go
func minOperationsToFlip(expression string) int {
	type pair struct{ val, cost int }
	nums := make([]pair, 0)
	ops := make([]byte, 0)
	merge := func(op byte) {
		b := nums[len(nums)-1]
		a := nums[len(nums)-2]
		nums = nums[:len(nums)-2]
		v1, c1, v2, c2 := a.val, a.cost, b.val, b.cost
		val, cost := 0, 0
		if op == '&' {
			val = v1 & v2
			if v1 == 1 && v2 == 1 {
				cost = min(c1, c2)
			} else if v1 == 0 && v2 == 0 {
				cost = min(c1+c2, 1+min(c1, c2))
			} else if v1 == 0 {
				cost = min(c1, 1)
			} else {
				cost = min(c2, 1)
			}
		} else {
			val = v1 | v2
			if v1 == 0 && v2 == 0 {
				cost = min(c1, c2)
			} else if v1 == 1 && v2 == 1 {
				cost = min(c1+c2, 1+min(c1, c2))
			} else if v1 == 1 {
				cost = min(c1, 1)
			} else {
				cost = min(c2, 1)
			}
		}
		nums = append(nums, pair{val, cost})
	}
	for i := 0; i < len(expression); i++ {
		c := expression[i]
		if c == '(' {
			ops = append(ops, c)
		} else if c == '0' || c == '1' {
			nums = append(nums, pair{int(c - '0'), 1})
		} else if c == '&' || c == '|' {
			for len(ops) > 0 && (ops[len(ops)-1] == '&' || ops[len(ops)-1] == '|') {
				merge(ops[len(ops)-1])
				ops = ops[:len(ops)-1]
			}
			ops = append(ops, c)
		} else {
			for ops[len(ops)-1] != '(' {
				merge(ops[len(ops)-1])
				ops = ops[:len(ops)-1]
			}
			ops = ops[:len(ops)-1]
		}
	}
	for len(ops) > 0 {
		merge(ops[len(ops)-1])
		ops = ops[:len(ops)-1]
	}
	return nums[0].cost
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
