---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0241.Different%20Ways%20to%20Add%20Parentheses/README.md
tags:
    - 递归
    - 记忆化搜索
    - 数学
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [241. 为运算表达式设计优先级](https://leetcode.cn/problems/different-ways-to-add-parentheses)

[English Version](/solution/0200-0299/0241.Different%20Ways%20to%20Add%20Parentheses/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由数字和运算符组成的字符串&nbsp;<code>expression</code> ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 <strong>按任意顺序</strong> 返回答案。</p>

<p>生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 <code>10<sup>4</sup></code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>expression = "2-1-1"
<strong>输出：</strong>[0,2]
<strong>解释：</strong>
((2-1)-1) = 0 
(2-(1-1)) = 2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>expression = "2*3-4*5"
<strong>输出：</strong>[-34,-14,-10,-10,10]
<strong>解释：</strong>
(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= expression.length &lt;= 20</code></li>
	<li><code>expression</code> 由数字和算符 <code>'+'</code>、<code>'-'</code> 和 <code>'*'</code> 组成。</li>
	<li>输入表达式中的所有整数值在范围 <code>[0, 99]</code>&nbsp;</li>
	<li>输入表达式中的所有整数都没有前导&nbsp;<code>'-'</code>&nbsp;或&nbsp;<code>'+'</code> 表示符号。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def diffWaysToCompute(self, expression: str) -> List[int]:
        @cache
        def dfs(exp):
            if exp.isdigit():
                return [int(exp)]
            ans = []
            for i, c in enumerate(exp):
                if c in '-+*':
                    left, right = dfs(exp[:i]), dfs(exp[i + 1 :])
                    for a in left:
                        for b in right:
                            if c == '-':
                                ans.append(a - b)
                            elif c == '+':
                                ans.append(a + b)
                            else:
                                ans.append(a * b)
            return ans

        return dfs(expression)
```

#### Java

```java
class Solution {
    private static Map<String, List<Integer>> memo = new HashMap<>();

    public List<Integer> diffWaysToCompute(String expression) {
        return dfs(expression);
    }

    private List<Integer> dfs(String exp) {
        if (memo.containsKey(exp)) {
            return memo.get(exp);
        }
        List<Integer> ans = new ArrayList<>();
        if (exp.length() < 3) {
            ans.add(Integer.parseInt(exp));
            return ans;
        }
        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);
            if (c == '-' || c == '+' || c == '*') {
                List<Integer> left = dfs(exp.substring(0, i));
                List<Integer> right = dfs(exp.substring(i + 1));
                for (int a : left) {
                    for (int b : right) {
                        if (c == '-') {
                            ans.add(a - b);
                        } else if (c == '+') {
                            ans.add(a + b);
                        } else {
                            ans.add(a * b);
                        }
                    }
                }
            }
        }
        memo.put(exp, ans);
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> diffWaysToCompute(string expression) {
        return dfs(expression);
    }

    vector<int> dfs(string exp) {
        if (memo.count(exp)) return memo[exp];
        if (exp.size() < 3) return {stoi(exp)};
        vector<int> ans;
        int n = exp.size();
        for (int i = 0; i < n; ++i) {
            char c = exp[i];
            if (c == '-' || c == '+' || c == '*') {
                vector<int> left = dfs(exp.substr(0, i));
                vector<int> right = dfs(exp.substr(i + 1, n - i - 1));
                for (int& a : left) {
                    for (int& b : right) {
                        if (c == '-')
                            ans.push_back(a - b);
                        else if (c == '+')
                            ans.push_back(a + b);
                        else
                            ans.push_back(a * b);
                    }
                }
            }
        }
        memo[exp] = ans;
        return ans;
    }

private:
    unordered_map<string, vector<int>> memo;
};
```

#### Go

```go
var memo = map[string][]int{}

func diffWaysToCompute(expression string) []int {
	return dfs(expression)
}

func dfs(exp string) []int {
	if v, ok := memo[exp]; ok {
		return v
	}
	if len(exp) < 3 {
		v, _ := strconv.Atoi(exp)
		return []int{v}
	}
	ans := []int{}
	for i, c := range exp {
		if c == '-' || c == '+' || c == '*' {
			left, right := dfs(exp[:i]), dfs(exp[i+1:])
			for _, a := range left {
				for _, b := range right {
					if c == '-' {
						ans = append(ans, a-b)
					} else if c == '+' {
						ans = append(ans, a+b)
					} else {
						ans = append(ans, a*b)
					}
				}
			}
		}
	}
	memo[exp] = ans
	return ans
}
```

#### C#

```cs
using System.Collections.Generic;

public class Solution {
    public IList<int> DiffWaysToCompute(string input) {
        var values = new List<int>();
        var operators = new List<char>();
        var sum = 0;
        foreach (var ch in input)
        {
            if (ch == '+' || ch == '-' || ch == '*')
            {
                values.Add(sum);
                operators.Add(ch);
                sum = 0;
            }
            else
            {
                sum = sum * 10 + ch - '0';
            }
        }
        values.Add(sum);

        var f = new List<int>[values.Count, values.Count];
        for (var i = 0; i < values.Count; ++i)
        {
            f[i, i] = new List<int> { values[i] };
        }

        for (var diff = 1; diff < values.Count; ++diff)
        {
            for (var left = 0; left + diff < values.Count; ++left)
            {
                var right = left + diff;
                f[left, right] = new List<int>();
                for (var i = left; i < right; ++i)
                {
                    foreach (var leftValue in f[left, i])
                    {
                        foreach (var rightValue in f[i + 1, right])
                        {
                            switch (operators[i])
                            {
                                case '+':
                                    f[left, right].Add(leftValue + rightValue);
                                    break;
                                case '-':
                                    f[left, right].Add(leftValue - rightValue);
                                    break;
                                case '*':
                                    f[left, right].Add(leftValue * rightValue);
                                    break;
                            }
                        }
                    }
                }
            }
        }

        return f[0, values.Count - 1];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
