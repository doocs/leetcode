---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0022.Generate%20Parentheses/README.md
tags:
    - 字符串
    - 动态规划
    - 回溯
---

<!-- problem:start -->

# [22. 括号生成](https://leetcode.cn/problems/generate-parentheses)

[English Version](/solution/0000-0099/0022.Generate%20Parentheses/README_EN.md)

## 题目描述

<!-- description:start -->

<p>数字 <code>n</code>&nbsp;代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 <strong>有效的 </strong>括号组合。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>["((()))","(()())","(())()","()(())","()()()"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>["()"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 8</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS + 剪枝

题目中 $n$ 的范围为 $[1, 8]$，因此我们直接通过“暴力搜索 + 剪枝”的方式通过本题。

我们设计一个函数 $dfs(l, r, t)$，其中 $l$ 和 $r$ 分别表示左括号和右括号的数量，而 $t$ 表示当前的括号序列。那么我们可以得到如下的递归结构：

-   如果 $l \gt n$ 或者 $r \gt n$ 或者 $l \lt r$，那么当前括号组合 $t$ 不合法，直接返回；
-   如果 $l = n$ 且 $r = n$，那么当前括号组合 $t$ 合法，将其加入答案数组 `ans` 中，直接返回；
-   我们可以选择添加一个左括号，递归执行 `dfs(l + 1, r, t + "(")`；
-   我们也可以选择添加一个右括号，递归执行 `dfs(l, r + 1, t + ")")`。

时间复杂度 $O(2^{n\times 2} \times n)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        def dfs(l, r, t):
            if l > n or r > n or l < r:
                return
            if l == n and r == n:
                ans.append(t)
                return
            dfs(l + 1, r, t + '(')
            dfs(l, r + 1, t + ')')

        ans = []
        dfs(0, 0, '')
        return ans
```

#### Java

```java
class Solution {
    private List<String> ans = new ArrayList<>();
    private int n;

    public List<String> generateParenthesis(int n) {
        this.n = n;
        dfs(0, 0, "");
        return ans;
    }

    private void dfs(int l, int r, String t) {
        if (l > n || r > n || l < r) {
            return;
        }
        if (l == n && r == n) {
            ans.add(t);
            return;
        }
        dfs(l + 1, r, t + "(");
        dfs(l, r + 1, t + ")");
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> generateParenthesis(int n) {
        vector<string> ans;
        function<void(int, int, string)> dfs = [&](int l, int r, string t) {
            if (l > n || r > n || l < r) return;
            if (l == n && r == n) {
                ans.push_back(t);
                return;
            }
            dfs(l + 1, r, t + "(");
            dfs(l, r + 1, t + ")");
        };
        dfs(0, 0, "");
        return ans;
    }
};
```

#### Go

```go
func generateParenthesis(n int) (ans []string) {
	var dfs func(int, int, string)
	dfs = func(l, r int, t string) {
		if l > n || r > n || l < r {
			return
		}
		if l == n && r == n {
			ans = append(ans, t)
			return
		}
		dfs(l+1, r, t+"(")
		dfs(l, r+1, t+")")
	}
	dfs(0, 0, "")
	return ans
}
```

#### TypeScript

```ts
function generateParenthesis(n: number): string[] {
    function dfs(l, r, t) {
        if (l > n || r > n || l < r) {
            return;
        }
        if (l == n && r == n) {
            ans.push(t);
            return;
        }
        dfs(l + 1, r, t + '(');
        dfs(l, r + 1, t + ')');
    }
    let ans = [];
    dfs(0, 0, '');
    return ans;
}
```

#### Rust

```rust
impl Solution {
    fn dfs(left: i32, right: i32, s: &mut String, res: &mut Vec<String>) {
        if left == 0 && right == 0 {
            res.push(s.clone());
            return;
        }
        if left > 0 {
            s.push('(');
            Self::dfs(left - 1, right, s, res);
            s.pop();
        }
        if right > left {
            s.push(')');
            Self::dfs(left, right - 1, s, res);
            s.pop();
        }
    }

    pub fn generate_parenthesis(n: i32) -> Vec<String> {
        let mut res = Vec::new();
        Self::dfs(n, n, &mut String::new(), &mut res);
        res
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {string[]}
 */
var generateParenthesis = function (n) {
    function dfs(l, r, t) {
        if (l > n || r > n || l < r) {
            return;
        }
        if (l == n && r == n) {
            ans.push(t);
            return;
        }
        dfs(l + 1, r, t + '(');
        dfs(l, r + 1, t + ')');
    }
    let ans = [];
    dfs(0, 0, '');
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Rust

```rust
impl Solution {
    pub fn generate_parenthesis(n: i32) -> Vec<String> {
        let mut dp: Vec<Vec<String>> = vec![vec![]; n as usize + 1];

        // Initialize the dp vector
        dp[0].push(String::from(""));
        dp[1].push(String::from("()"));

        // Begin the actual dp process
        for i in 2..=n as usize {
            for j in 0..i as usize {
                let dp_c = dp.clone();
                let first_half = &dp_c[j];
                let second_half = &dp_c[i - j - 1];

                for f in first_half {
                    for s in second_half {
                        let f_c = f.clone();
                        let cur_str = f_c + "(" + &*s + ")";
                        dp[i].push(cur_str);
                    }
                }
            }
        }

        dp[n as usize].clone()
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param int $n
     * @return string[]
     */

    function generateParenthesis($n) {
        $result = [];
        $this->backtrack($result, '', 0, 0, $n);
        return $result;
    }

    function backtrack(&$result, $current, $open, $close, $max) {
        if (strlen($current) === $max * 2) {
            $result[] = $current;
            return;
        }
        if ($open < $max) {
            $this->backtrack($result, $current . '(', $open + 1, $close, $max);
        }
        if ($close < $open) {
            $this->backtrack($result, $current . ')', $open, $close + 1, $max);
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
