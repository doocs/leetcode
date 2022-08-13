# [面试题 08.14. 布尔运算](https://leetcode.cn/problems/boolean-evaluation-lcci)

[English Version](/lcci/08.14.Boolean%20Evaluation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 <code>0</code> (false)、<code>1</code> (true)、<code>&amp;</code> (AND)、 <code>|</code> (OR) 和 <code>^</code> (XOR) 符号组成。实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入: </strong>s = &quot;1^0|0|1&quot;, result = 0

<strong>输出: </strong>2
<strong>解释:</strong>&nbsp;两种可能的括号方法是
1^(0|(0|1))
1^((0|0)|1)
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入: </strong>s = &quot;0&amp;0&amp;0&amp;1^1|0&quot;, result = 1

<strong>输出: </strong>10</pre>

<p><strong>提示：</strong></p>

<ul>
	<li>运算符的数量不超过 19 个</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countEval(self, s: str, result: int) -> int:
        @cache
        def dfs(s):
            res = [0] * 2
            if s in '01':
                res[int(s)] = 1
                return res
            for k, op in enumerate(s):
                if op in '&^|':
                    left, right = dfs(s[:k]), dfs(s[k + 1 :])
                    for i, v1 in enumerate(left):
                        for j, v2 in enumerate(right):
                            if op == '&':
                                v = i & j
                            elif op == '^':
                                v = i ^ j
                            elif op == '|':
                                v = i | j
                            res[v] += v1 * v2
            return res

        ans = dfs(s)
        return ans[result] if 0 <= result < 2 else 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Map<String, int[]> memo;

    public int countEval(String s, int result) {
        memo = new HashMap<>();
        int[] ans = dfs(s);
        return result == 0 || result == 1 ? ans[result] : 0;
    }

    private int[] dfs(String s) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        int[] res = new int[2];
        if (s.length() == 1) {
            res[Integer.parseInt(s)] = 1;
            return res;
        }
        for (int k = 0; k < s.length(); ++k) {
            char op = s.charAt(k);
            if (op == '&' || op == '|' || op == '^') {
                int[] left = dfs(s.substring(0, k));
                int[] right = dfs(s.substring(k + 1));
                for (int i = 0; i < 2; ++i) {
                    for (int j = 0; j < 2; ++j) {
                        int v = 0;
                        if (op == '&') {
                            v = i & j;
                        } else if (op == '|') {
                            v = i | j;
                        } else if (op == '^') {
                            v = i ^ j;
                        }
                        res[v] += left[i] * right[j];
                    }
                }
            }
        }
        memo.put(s, res);
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    unordered_map<string, vector<int>> memo;

    int countEval(string s, int result) {
        vector<int> ans = dfs(s);
        return result == 0 || result == 1 ? ans[result] : 0;
    }

    vector<int> dfs(string s) {
        if (memo.count(s)) return memo[s];
        vector<int> res(2);
        if (s.size() == 1) {
            res[s[0] - '0'] = 1;
            return res;
        }
        for (int k = 0; k < s.size(); ++k) {
            if (s[k] == '0' || s[k] == '1') continue;
            vector<int> left = dfs(s.substr(0, k));
            vector<int> right = dfs(s.substr(k + 1, s.size() - k));
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < 2; ++j) {
                    int v = 0;
                    if (s[k] == '&')
                        v = i & j;
                    else if (s[k] == '|')
                        v = i | j;
                    else if (s[k] == '^')
                        v = i ^ j;
                    res[v] += left[i] * right[j];
                }
            }
        }
        memo[s] = res;
        return res;
    }
};
```

### **Go**

```go
func countEval(s string, result int) int {
	memo := map[string][]int{}
	var dfs func(string) []int
	dfs = func(s string) []int {
		if v, ok := memo[s]; ok {
			return v
		}
		res := make([]int, 2)
		if len(s) == 1 {
			res[s[0]-'0'] = 1
			return res
		}
		for k, c := range s {
			if c == '0' || c == '1' {
				continue
			}
			left, right := dfs(s[:k]), dfs(s[k+1:])
			for i, v1 := range left {
				for j, v2 := range right {
					v := 0
					if c == '&' {
						v = i & j
					} else if c == '|' {
						v = i | j
					} else if c == '^' {
						v = i ^ j
					}
					res[v] += v1 * v2
				}
			}
		}
		memo[s] = res
		return res
	}
	ans := dfs(s)
	if result == 0 || result == 1 {
		return ans[result]
	}
	return 0
}
```

### **...**

```

```

<!-- tabs:end -->
