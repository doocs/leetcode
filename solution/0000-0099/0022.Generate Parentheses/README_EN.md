# [22. Generate Parentheses](https://leetcode.com/problems/generate-parentheses)

[中文文档](/solution/0000-0099/0022.Generate%20Parentheses/README.md)

## Description

<p>Given <code>n</code> pairs of parentheses, write a function to <em>generate all combinations of well-formed parentheses</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> n = 3
<strong>Output:</strong> ["((()))","(()())","(())()","()(())","()()()"]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> n = 1
<strong>Output:</strong> ["()"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 8</code></li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        def dfs(left, right, t):
            if left == n and right == n:
                ans.append(t)
                return
            if left < n:
                dfs(left + 1, right, t + '(')
            if right < left:
                dfs(left, right + 1, t + ')')

        ans = []
        dfs(0, 0, '')
        return ans
```

### **Java**

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(0, 0, n, "", ans);
        return ans;
    }

    private void dfs(int left, int right, int n, String t, List<String> ans) {
        if (left == n && right == n) {
            ans.add(t);
            return;
        }
        if (left < n) {
            dfs(left + 1, right, n, t + "(", ans);
        }
        if (right < left) {
            dfs(left, right + 1, n, t + ")", ans);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> generateParenthesis(int n) {
        vector<string> ans;
        dfs(0, 0, n, "", ans);
        return ans;
    }

    void dfs(int left, int right, int n, string t, vector<string>& ans) {
        if (left == n && right == n) {
            ans.push_back(t);
            return;
        }
        if (left < n) dfs(left + 1, right, n, t + "(", ans);
        if (right < left) dfs(left, right + 1, n, t + ")", ans);
    }
};
```

### **Go**

```go
func generateParenthesis(n int) []string {
	var ans []string
	var dfs func(left, right int, t string)
	dfs = func(left, right int, t string) {
		if left == n && right == n {
			ans = append(ans, t)
			return
		}
		if left < n {
			dfs(left+1, right, t+"(")
		}
		if right < left {
			dfs(left, right+1, t+")")
		}
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
    let ans = [];
    let dfs = function (left, right, t) {
        if (left == n && right == n) {
            ans.push(t);
            return;
        }
        if (left < n) {
            dfs(left + 1, right, t + '(');
        }
        if (right < left) {
            dfs(left, right + 1, t + ')');
        }
    };
    dfs(0, 0, '');
    return ans;
};
```

### **TypeScript**

```ts
function generateParenthesis(n: number): string[] {
    const ans: string[] = [];
    const dfs = (left: number, right: number, t: string) => {
        if (left == n && right == n) {
            ans.push(t);
            return;
        }
        if (left < n) {
            dfs(left + 1, right, t + '(');
        }
        if (right < left) {
            dfs(left, right + 1, t + ')');
        }
    };
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
