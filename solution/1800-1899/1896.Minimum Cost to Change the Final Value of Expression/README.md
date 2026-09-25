---
comments: true
difficulty: 困难
rating: 2531
source: 第 54 场双周赛 Q4
tags:
    - 栈
    - 数学
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [1896. 反转表达式值的最少操作次数](https://leetcode.cn/problems/minimum-cost-to-change-the-final-value-of-expression)

[English Version](/solution/1800-1899/1896.Minimum%20Cost%20to%20Change%20the%20Final%20Value%20of%20Expression/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <strong>有效的</strong> 布尔表达式，用字符串 <code>expression</code> 表示。这个字符串包含字符 <code>'1'</code>，<code>'0'</code>，<code>'&amp;'</code>（按位 <strong>与</strong> 运算），<code>'|'</code>（按位 <strong>或</strong> 运算），<code>'('</code> 和 <code>')'</code> 。</p>

<ul>
	<li>比方说，<code>"()1|1"</code> 和 <code>"(1)&amp;()"</code> <strong>不是有效</strong> 布尔表达式。而 <code>"1"</code>， <code>"(((1))|(0))"</code> 和 <code>"1|(0&amp;(1))"</code> 是 <strong>有效</strong> 布尔表达式。</li>
</ul>

<p>你的目标是将布尔表达式的 <strong>值</strong> <strong>反转 </strong>（也就是将 <code>0</code> 变为 <code>1</code> ，或者将 <code>1</code> 变为 <code>0</code>），请你返回达成目标需要的 <strong>最少操作</strong> 次数。</p>

<ul>
	<li>比方说，如果表达式 <code>expression = "1|1|(0&amp;0)&amp;1"</code> ，它的 <strong>值</strong> 为 <code>1|1|(0&amp;0)&amp;1 = 1|1|0&amp;1 = 1|0&amp;1 = 1&amp;1 = 1</code> 。我们想要执行操作将 <strong>新的</strong> 表达式的值变成 <code>0</code> 。</li>
</ul>

<p>可执行的 <strong>操作</strong> 如下：</p>

<ul>
	<li>将一个 <code>'1'</code> 变成一个 <code>'0'</code> 。</li>
	<li>将一个 <code>'0'</code> 变成一个 <code>'1'</code> 。</li>
	<li>将一个 <code>'&amp;'</code> 变成一个 <code>'|'</code> 。</li>
	<li>将一个 <code>'|'</code> 变成一个 <code>'&amp;'</code> 。</li>
</ul>

<p><strong>注意：</strong><code>'&amp;'</code> 的 <strong>运算优先级</strong> 与 <code>'|'</code> <strong>相同</strong> 。计算表达式时，括号优先级 <strong>最高</strong> ，然后按照 <strong>从左到右</strong> 的顺序运算。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>expression = "1&amp;(0|1)"
<b>输出：</b>1
<b>解释：</b>我们可以将 "1&amp;(0<strong>|</strong>1)" 变成 "1&amp;(0<strong>&amp;</strong>1)" ，执行的操作为将一个 '|' 变成一个 '&amp;' ，执行了 1 次操作。
新表达式的值为 0 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>expression = "(0&amp;0)&amp;(0&amp;0&amp;0)"
<b>输出：</b>3
<b>解释：</b>我们可以将 "(0<strong>&amp;0</strong>)<strong>&amp;</strong>(0&amp;0&amp;0)" 变成 "(0<strong>|1</strong>)<strong>|</strong>(0&amp;0&amp;0)" ，执行了 3 次操作。
新表达式的值为 1 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>expression = "(0|(1|0&amp;1))"
<b>输出：</b>1
<b>解释：</b>我们可以将 "(0|(<strong>1</strong>|0&amp;1))" 变成 "(0|(<strong>0</strong>|0&amp;1))" ，执行了 1 次操作。
新表达式的值为 0 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= expression.length &lt;= 10<sup>5</sup></code></li>
	<li><code>expression</code> 只包含 <code>'1'</code>，<code>'0'</code>，<code>'&amp;'</code>，<code>'|'</code>，<code>'('</code> 和 <code>')'</code></li>
	<li>所有括号都有与之匹配的对应括号。</li>
	<li>不会有空的括号（也就是说 <code>"()"</code> 不是 <code>expression</code> 的子字符串）。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 合法布尔表达式由 $0/1$、$\&$、$|$ 与括号组成，一次操作可把数字或运算符改成另一种，求改变整个表达式值的最少次数。表达式长度达 $10^5$，不能对每种改法重新求值。
>
> 每个子表达式只需知道当前值，以及把它变成相反值的代价。叶子 $0/1$ 翻转为 $1$；两个子式用 $\&$ 或 $|$ 连接时，可根据左右值与运算符分类讨论：改运算符、只改一侧或两侧。用栈按括号与优先级解析，自底向上合并即可。

<!-- thinking:end -->

每个子表达式记为二元组 $(\textit{val},\textit{cost})$：当前布尔值，以及把它改成相反值的最少操作次数。数字 $0/1$ 的翻转代价是 $1$。

$\&$ 与 $|$ 优先级相同，按从左到右计算，括号优先。两个栈分别保存子表达式和运算符。读到运算符时，先把栈里已有的同级运算归约；读到右括号时，归约到对应的左括号。

设左右子式为 $(v_1,c_1)$、$(v_2,c_2)$。

- 运算符是 $\&$，两端都是 $1$：结果为 $1$，把其中一端改成 $0$，代价 $\min(c_1,c_2)$。
- 两端都是 $0$：结果为 $0$。两端都改成 $1$ 的代价是 $c_1+c_2$；把 $\&$ 改成 $|$ 并至少改一端的代价是 $1+\min(c_1,c_2)$。
- 只有一端是 $0$：结果为 $0$。把这个 $0$ 改成 $1$，或把 $\&$ 改成 $|$，取较小代价。
- 运算符是 $|$ 时，三种情况与上面对偶。

归约结束后，栈顶的 $\textit{cost}$ 就是答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是表达式的长度。

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
