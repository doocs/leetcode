---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0022.Generate%20Parentheses/README_EN.md
tags:
    - String
    - Dynamic Programming
    - Backtracking
---

# [22. Generate Parentheses](https://leetcode.com/problems/generate-parentheses)

[中文文档](/solution/0000-0099/0022.Generate%20Parentheses/README.md)

## Description

<p>Given <code>n</code> pairs of parentheses, write a function to <em>generate all combinations of well-formed parentheses</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> n = 3
<strong>Output:</strong> ["((()))","(()())","(())()","()(())","()()()"]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> n = 1
<strong>Output:</strong> ["()"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 8</code></li>
</ul>

## Solutions

### Solution 1: DFS + Pruning

The range of $n$ in the problem is $[1, 8]$, so we can directly solve this problem through "brute force search + pruning".

We design a function $dfs(l, r, t)$, where $l$ and $r$ represent the number of left and right brackets respectively, and $t$ represents the current bracket sequence. Then we can get the following recursive structure:

-   If $l \gt n$ or $r \gt n$ or $l \lt r$, then the current bracket combination $t$ is invalid, return directly;
-   If $l = n$ and $r = n$, then the current bracket combination $t$ is valid, add it to the answer array `ans`, and return directly;
-   We can choose to add a left bracket, and recursively execute `dfs(l + 1, r, t + "(")`;
-   We can also choose to add a right bracket, and recursively execute `dfs(l, r + 1, t + ")")`.

The time complexity is $O(2^{n\times 2} \times n)$, and the space complexity is $O(n)$.

<!-- tabs:start -->

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

### Solution 2

<!-- tabs:start -->

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

<!-- end -->
