# [面试题 08.09. 括号](https://leetcode.cn/problems/bracket-lcci)

[English Version](/lcci/08.09.Bracket/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。</p>

<p>说明：解集不能包含重复的子集。</p>

<p>例如，给出 n = 3，生成结果为：</p>

<pre>
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS + 剪枝**

题目中 $n$ 的范围为 $[1, 8]$，因此我们直接通过“暴力搜索 + 剪枝”的方式快速解决本题。

我们设计函数 `dfs(l, r, t)`，其中 $l$ 和 $r$ 分别表示左括号和右括号的数量，而 $t$ 表示当前的括号序列。那么我们可以得到如下的递归结构：

-   如果 $l \gt n$ 或者 $r \gt n$ 或者 $l \lt r$，那么当前括号组合 $t$ 不合法，直接返回；
-   如果 $l = n$ 且 $r = n$，那么当前括号组合 $t$ 合法，将其加入答案数组 `ans` 中，直接返回；
-   我们可以选择添加一个左括号，递归执行 `dfs(l + 1, r, t + "(")`；
-   我们也可以选择添加一个右括号，递归执行 `dfs(l, r + 1, t + ")")`。

时间复杂度 $O(2^{n\times 2} \times n)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

```cpp
class Solution {
public:
    vector<string> generateParenthesis(int n) {
        vector<string> ans;
        function<void(int, int, string)> dfs;
        dfs = [&](int l, int r, string t) {
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

### **Go**

```go
func generateParenthesis(n int) []string {
	ans := []string{}
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

### **JavaScript**

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

### **TypeScript**

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

### **Rust**

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

### **...**

```

```

<!-- tabs:end -->
