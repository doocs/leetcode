---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/08.09.Bracket/README_EN.md
---

<!-- problem:start -->

# [08.09. Bracket](https://leetcode.cn/problems/bracket-lcci)

[中文文档](/lcci/08.09.Bracket/README.md)

## Description

<!-- description:start -->

<p>Implement an algorithm to print all valid (e.g., properly opened and closed) combinations of n pairs of parentheses.</p>

<p>Note: The result set should not contain duplicated subsets.</p>

<p>For example, given&nbsp;n = 3, the result should be:</p>

<pre>

[

  &quot;((()))&quot;,

  &quot;(()())&quot;,

  &quot;(())()&quot;,

  &quot;()(())&quot;,

  &quot;()()()&quot;

]

</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS + Pruning

The range of $n$ in the problem is $[1, 8]$, so we can directly solve this problem quickly through "brute force search + pruning".

We design a function `dfs(l, r, t)`, where $l$ and $r$ represent the number of left and right parentheses respectively, and $t$ represents the current parentheses sequence. Then we can get the following recursive structure:

-   If $l > n$ or $r > n$ or $l < r$, then the current parentheses combination $t$ is illegal, return directly;
-   If $l = n$ and $r = n$, then the current parentheses combination $t$ is legal, add it to the answer array `ans`, and return directly;
-   We can choose to add a left parenthesis, and recursively execute `dfs(l + 1, r, t + "(")`;
-   We can also choose to add a right parenthesis, and recursively execute `dfs(l, r + 1, t + ")")`.

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

```swift
class Solution {
    private var ans: [String] = []
    private var n: Int = 0

    func generateParenthesis(_ n: Int) -> [String] {
        self.n = n
        dfs(l: 0, r: 0, t: "")
        return ans
    }

    private func dfs(l: Int, r: Int, t: String) {
        if l > n || r > n || l < r {
            return
        }
        if l == n && r == n {
            ans.append(t)
            return
        }
        dfs(l: l + 1, r: r, t: t + "(")
        dfs(l: l, r: r + 1, t: t + ")")
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
