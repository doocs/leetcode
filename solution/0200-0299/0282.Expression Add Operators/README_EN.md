---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0282.Expression%20Add%20Operators/README_EN.md
tags:
    - Math
    - String
    - Backtracking
---

<!-- problem:start -->

# [282. Expression Add Operators](https://leetcode.com/problems/expression-add-operators)

[中文文档](/solution/0200-0299/0282.Expression%20Add%20Operators/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>num</code> that contains only digits and an integer <code>target</code>, return <em><strong>all possibilities</strong> to insert the binary operators </em><code>&#39;+&#39;</code><em>, </em><code>&#39;-&#39;</code><em>, and/or </em><code>&#39;*&#39;</code><em> between the digits of </em><code>num</code><em> so that the resultant expression evaluates to the </em><code>target</code><em> value</em>.</p>

<p>Note that operands in the returned expressions <strong>should not</strong> contain leading zeros.</p>

<p><strong>Note</strong> that a number can contain multiple digits.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;123&quot;, target = 6
<strong>Output:</strong> [&quot;1*2*3&quot;,&quot;1+2+3&quot;]
<strong>Explanation:</strong> Both &quot;1*2*3&quot; and &quot;1+2+3&quot; evaluate to 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;232&quot;, target = 8
<strong>Output:</strong> [&quot;2*3+2&quot;,&quot;2+3*2&quot;]
<strong>Explanation:</strong> Both &quot;2*3+2&quot; and &quot;2+3*2&quot; evaluate to 8.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;3456237490&quot;, target = 9191
<strong>Output:</strong> []
<strong>Explanation:</strong> There are no expressions that can be created from &quot;3456237490&quot; to evaluate to 9191.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 10</code></li>
	<li><code>num</code> consists of only digits.</li>
	<li><code>-2<sup>31</sup> &lt;= target &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def addOperators(self, num: str, target: int) -> List[str]:
        ans = []

        def dfs(u, prev, curr, path):
            if u == len(num):
                if curr == target:
                    ans.append(path)
                return
            for i in range(u, len(num)):
                if i != u and num[u] == '0':
                    break
                next = int(num[u : i + 1])
                if u == 0:
                    dfs(i + 1, next, next, path + str(next))
                else:
                    dfs(i + 1, next, curr + next, path + "+" + str(next))
                    dfs(i + 1, -next, curr - next, path + "-" + str(next))
                    dfs(
                        i + 1,
                        prev * next,
                        curr - prev + prev * next,
                        path + "*" + str(next),
                    )

        dfs(0, 0, 0, "")
        return ans
```

#### Java

```java
class Solution {
    private List<String> ans;
    private String num;
    private int target;

    public List<String> addOperators(String num, int target) {
        ans = new ArrayList<>();
        this.num = num;
        this.target = target;
        dfs(0, 0, 0, "");
        return ans;
    }

    private void dfs(int u, long prev, long curr, String path) {
        if (u == num.length()) {
            if (curr == target) ans.add(path);
            return;
        }
        for (int i = u; i < num.length(); i++) {
            if (i != u && num.charAt(u) == '0') {
                break;
            }
            long next = Long.parseLong(num.substring(u, i + 1));
            if (u == 0) {
                dfs(i + 1, next, next, path + next);
            } else {
                dfs(i + 1, next, curr + next, path + "+" + next);
                dfs(i + 1, -next, curr - next, path + "-" + next);
                dfs(i + 1, prev * next, curr - prev + prev * next, path + "*" + next);
            }
        }
    }
}
```

#### C#

```cs
using System;
using System.Collections.Generic;

public class Expression
{
    public long Value;

    public override string ToString()
    {
        return Value.ToString();
    }
}

public class BinaryExpression : Expression
{
    public char Operator;

    public Expression LeftChild;
    public Expression RightChild;

    public override string ToString()
    {
        return string.Format("{0}{1}{2}", LeftChild, Operator, RightChild);
    }
}

public class Solution {
    public IList<string> AddOperators(string num, int target) {
        var results = new List<string>();
        if (string.IsNullOrEmpty(num)) return results;
        this.num = num;
        this.results = new List<Expression>[num.Length, num.Length, 3];
        foreach (var ex in Search(0, num.Length - 1, 0))
        {
            if (ex.Value == target)
            {
                results.Add(ex.ToString());
            }
        }
        return results;
    }

    private string num;
    private List<Expression>[,,] results;

    private List<Expression> Search(int left, int right, int level)
    {
        if (results[left, right, level] != null)
        {
            return results[left, right, level];
        }
        var result = new List<Expression>();
        if (level < 2)
        {
            for (var i = left + 1; i <= right; ++i)
            {
                List<Expression> leftResult, rightResult;
                leftResult = Search(left, i - 1, level);
                rightResult = Search(i, right, level + 1);
                foreach (var l in leftResult)
                {
                    foreach (var r in rightResult)
                    {
                        var newObjects = new List<Tuple<char, long>>();
                        if (level == 0)
                        {
                            newObjects.Add(Tuple.Create('+', l.Value + r.Value));
                            newObjects.Add(Tuple.Create('-', l.Value - r.Value));
                        }
                        else
                        {
                            newObjects.Add(Tuple.Create('*', l.Value * r.Value));
                        }
                        foreach (var newObject in newObjects)
                        {
                            result.Add(new BinaryExpression
                            {
                                Value = newObject.Item2,
                                Operator = newObject.Item1,
                                LeftChild = l,
                                RightChild = r
                            });
                        }
                    }
                }
            }
        }
        else
        {
            if (left == right || num[left] != '0')
            {
                long x = 0;
                for (var i = left; i <= right; ++i)
                {
                    x = x * 10 + num[i] - '0';
                }
                result.Add(new Expression
                {
                    Value = x
                });
            }
        }
        if (level < 2)
        {
            result.AddRange(Search(left, right, level + 1));
        }
        return results[left, right, level] = result;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
