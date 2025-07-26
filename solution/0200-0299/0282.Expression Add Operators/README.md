---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0282.Expression%20Add%20Operators/README.md
tags:
    - 数学
    - 字符串
    - 回溯
---

<!-- problem:start -->

# [282. 给表达式添加运算符](https://leetcode.cn/problems/expression-add-operators)

[English Version](/solution/0200-0299/0282.Expression%20Add%20Operators/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个仅包含数字&nbsp;<code>0-9</code>&nbsp;的字符串 <code>num</code> 和一个目标值整数 <code>target</code> ，在 <code>num</code> 的数字之间添加 <strong>二元 </strong>运算符（不是一元）<code>+</code>、<code>-</code>&nbsp;或&nbsp;<code>*</code>&nbsp;，返回 <strong>所有</strong> 能够得到 <code>target</code>&nbsp;的表达式。</p>

<p>注意，返回表达式中的操作数 <strong>不应该</strong> 包含前导零。</p>

<p><strong>注意</strong>，一个数字可以包含多个数位。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> <code>num = </code>"123", target = 6
<strong>输出: </strong>["1+2+3", "1*2*3"] 
<strong>解释: </strong>“1*2*3” 和 “1+2+3” 的值都是6。
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> <code>num = </code>"232", target = 8
<strong>输出: </strong>["2*3+2", "2+3*2"]
<strong>解释:</strong> “2*3+2” 和 “2+3*2” 的值都是8。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> <code>num = </code>"3456237490", target = 9191
<strong>输出: </strong>[]
<strong>解释: </strong>表达式 “3456237490” 无法得到 9191 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 10</code></li>
	<li><code>num</code> 仅含数字</li>
	<li><code>-2<sup>31</sup> &lt;= target &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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
